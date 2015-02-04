
import java.util.ArrayList;

public class Parser {
	private DB onFocus = new DB();

	public String perform(String command) throws Exception {
		String original = new String(command);
		command = wellFormatted(command);
		String[] parts = command.split(" ");
		Condition con = getCon(parts, original);
		String TableName = getTableName(parts);

		if (parts[0].toUpperCase().equals("SELECT")) {
			if (onFocus == null)
				return dbms.TABLE_NOT_FOUND;
			if (parts[1].equals("*")) {
				// System.out.println(TableName);
				return onFocus.selectAll(TableName, con,
						orderingColumns(parts), original.toUpperCase()
								.contains("DESC"));
			} else if (parts[1].toUpperCase().equals("DISTINCT")) {
				ArrayList<String> coluNames = new ArrayList<String>();
				for (int i = 2; i < parts.length
						&& !parts[i].toUpperCase().equals("FROM"); i++)
					coluNames.add(parts[i]);
				return onFocus.selectColumn(coluNames, TableName, con,
						orderingColumns(parts), original.toUpperCase()
								.contains("DESC"));
			} else {
				ArrayList<String> coluNames = new ArrayList<String>();
				for (int i = 1; i < parts.length
						&& !parts[i].toString().equals("FROM"); i++)
					coluNames.add(parts[i]);
				return onFocus.selectColumn(coluNames, TableName, con,
						orderingColumns(parts), original.toUpperCase()
								.contains("DESC"));
			}
		} else if (parts[0].toUpperCase().equals("CREATE")) {
			if (parts[1].toUpperCase().equals("DATABASE")) {
				onFocus = new DB();
				return onFocus.createDatabase(parts[2]);
			} else if (parts[1].toUpperCase().equals("TABLE")) {
				if (onFocus == null)
					return dbms.DB_NOT_FOUND;
				return onFocus.createTable(getColumnsData(parts), parts[2]);
			}
		} else if (parts[0].toUpperCase().equals("DELETE")) {
			if (onFocus == null)
				return dbms.TABLE_NOT_FOUND;
			if (con != null)
				
			return onFocus.delete(TableName, con);

		} else if (parts[0].toUpperCase().equals("INSERT")) {
			if (onFocus == null)
				return dbms.TABLE_NOT_FOUND;
			ArrayList<String> columns = getBetweenBrackets(original
					.substring(original.toUpperCase().indexOf("VALUES") + 6));
			// for(int i = 0;i<columns.size();i++){
			// System.out.print(columns.get(i)+" ");
			// }
			// System.out.println("*******************************************************");
			if (parts[3].equals("(")) {
				return onFocus.insert(getBetweenBrackets(original.substring(
						original.indexOf("("), original.indexOf(")"))),
						getBetweenBrackets(original.substring(original
								.toUpperCase().indexOf("VALUES") + 6)),
						parts[2]);
			} else {
				return onFocus.insert(null,
						getBetweenBrackets(original.substring(original
								.toUpperCase().indexOf("VALUES") + 6)),
						parts[2]);
			}
		} else if (parts[0].toUpperCase().equals("UPDATE")) {
			if (onFocus == null)
				return dbms.TABLE_NOT_FOUND;
			ArrayList<String> ColuNames = new ArrayList<String>(), values = new ArrayList<String>();
			getData(ColuNames, values, parts);
			// System.out.println(con.getValue());
			// System.out.println("******************************************");

			return onFocus.update(ColuNames, values, TableName, con);
		}
		return dbms.PARSING_ERROR;
	}

	private Condition getCon(String parts[], String d) {
		if (d.toUpperCase().contains("WHERE")) {
			d = d.substring(d.toUpperCase().indexOf("WHERE") + 6).trim();

			if (d.contains("'")) {
				
				d = d.substring(d.indexOf("'"));
				d.replaceAll("[';]", "");

			} else {
				d = null;
			}
		}

		for (int i = 3; i < parts.length; i++)
			if (parts[i].toUpperCase().equals("WHERE")) {
				return new Condition(parts[i + 1], parts[i + 2],
						d == null ? parts[i + 3] : d.replaceAll("[';]", ""));
			}

		return null;
	}

	private String getTableName(String parts[]) {
		for (int i = 0; i < parts.length; i++)
			if (parts[i].toLowerCase().equals("FROM".toLowerCase())
					|| parts[i].toUpperCase().equals("INTO")
					|| parts[i].toUpperCase().equals("UPDATE"))
				return parts[i + 1];
		return null;
	}

	private ArrayList<Column> getColumnsData(String parts[]) {
		ArrayList<Column> myColumns = new ArrayList<Column>();
		for (int i = 4; i < parts.length && !parts[i].equals(")"); i += 5)
			if (parts[i + 1].equals("int")) {
				myColumns.add(new Column(parts[i], "int", 255));
				i -= 3;
			} else {
				myColumns.add(new Column(parts[i], parts[i + 1], Integer
						.parseInt(parts[i + 3].trim())));
			}

		return myColumns;
	}

	private ArrayList<String> getBetweenBrackets(String original) {
		original = original.replaceAll("[\\(\\)\\;]", "");
		original = original.replaceAll("\\'( )*\\,( )*\\'", ",");
		original = original.replaceAll("'", "");
		String[] parts = original.split(",");
		ArrayList<String> Values = new ArrayList<String>();
		for (String s : parts)
			Values.add(s.trim());
		return Values.size() == 0 ? null : Values;
	}

	private void getData(ArrayList<String> names, ArrayList<String> values,
			String[] p) {
		for (int i = 3; i < p.length && !p[i].toUpperCase().equals("WHERE"); i += 3) {
			names.add(p[i]);
			values.add(p[i + 2].replaceAll("'", ""));
		}
	}

	private String wellFormatted(String command) {
		command = command.replaceAll(",", " ");
		command = command.replaceAll("=", " = ");
		command = command.replaceAll(">", " > ");
		command = command.replaceAll("<", " < ");
		command = command.replaceAll("\\)", " \\) ");
		command = command.replaceAll("\\(", " \\( ");
		command = command.replaceAll("[\t;]", "");
		return command.replaceAll("^ +| +$|( )+", "$1");
	}

	private ArrayList<String> orderingColumns(String[] parts) {
		ArrayList<String> res = null;
		for (int i = 5; i < parts.length; i++) {
			if (parts[i].equals("BY")) {
				res = new ArrayList<String>();
				for (int j = i + 1; j < parts.length
						&& !parts[j].toUpperCase().equals("DESC")
						&& !parts[j].toUpperCase().equals("ASC"); j++) {
					res.add(parts[j]);

				}
				break;
			}
		}
		return res;
	}

	public static void main(String[] args) throws Exception {
		Parser e = new Parser();
		e.perform("select * from persons where LastName='Mohamed'");

	}
}
