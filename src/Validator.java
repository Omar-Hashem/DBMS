

import java.util.ArrayList;

public class Validator {

	// SCHEMA

	
	/*
	 * validates if the columns in the user's query exist in the table through
	 * schema file
	 */
	public boolean Validate_Parameters(ArrayList<String> columnNames,
			String TablePath) {
		
		Schema schema = new Schema();
		ArrayList<Column> tableColumns = schema.schemaParsing(TablePath);
		ArrayList<String> temp = new ArrayList<String>();

		for (int i = 0; i < tableColumns.size(); i++) {
			temp.add(tableColumns.get(i).getColName().toLowerCase());
		}

		for (int i = 0; i < columnNames.size(); i++) {
			if (!temp.contains(columnNames.get(i).toLowerCase())){
				return false;
			}
		}
		return true;
	}

	public boolean Validate_DataTypes(ArrayList<String> columnNames,
			ArrayList<String> values, String TableName,String TablePath,DB b,boolean isCondition) {
		
		Schema sch = new Schema();
		ArrayList<Column> cols = sch.schemaParsing(TablePath);
		
		if(isCondition){
			String type ="";
			String colName = columnNames.get(0);
			for(int i=0;i<cols.size();i++){
				if(cols.get(i).getColName().toLowerCase().equals(colName.toLowerCase())){
					type=cols.get(i).getdataType();
					break;
				}
			}
			String value = values.get(0);

			if(b.isNumeric(value) && !type.equals("byte") ){
				System.out.println(value+"in con"+type);
				return false;
			}
			if(!b.isNumeric(value) && !type.equals("string")){
				System.out.println(value+"in con"+type);
				return false;
			}
			return true;
		}
		b.arrangeColumnNamesWithValues(columnNames, values, TableName);
		
		for(int i=0;i<cols.size();i++){
			
			
			for (int j = 0; j < columnNames.size(); j++) {
				if (cols.get(i).getColName().equals(columnNames.get(j))&&b.isNumeric(values.get(j))
						&& !cols.get(i).getdataType().equals("byte")) {
					
					return false;
				}
				if (cols.get(i).getColName().equals(columnNames.get(j))&&!b.isNumeric(values.get(j))
						&& !cols.get(i).getdataType().equals("string")) {
					

					return false;
				}
			}
			
		}
		
		
		return true;
	}

	
	// Commands
		public boolean Validate_Expression(String input) {
			input = input.toLowerCase();
			input = input.trim();
			if (input.charAt(0) == 'u')
				return IsUpdateValid(input);
			if (input.charAt(0) == 's')
				return IsSelectValid(input);
			if (input.charAt(0) == 'i')
				return IsInsertValid(input);
			if (input.charAt(0) == 'd'){
				return IsDeleteValid(input);
			}
			if (input.charAt(0) == 'c')
				return IsCreateValid(input);
			return false;
		}

		private boolean IsUpdateValid(String inp) {
			return inp
					.matches("^(update)\\ +[_a-z][a-z0-9_]*\\ +(set)\\ +([_a-z][a-z0-9_]*\\ *=\\ *(('.*')|([0-9]+))\\ *,\\ *)*([_a-z][a-z0-9_]*\\ *=\\ *(('.*'\\ *)|([0-9]+\\ +)))+where\\ +([_a-z][a-z0-9_]*\\ *=\\ *(('.*')|([0-9]+)))\\ *;?");
		}

		private boolean IsSelectValid(String inp) {
			String tmp= inp ; 
			tmp=tmp.toLowerCase();
			String[] temp =tmp.split(" ") ;
			
			if ((temp.length==4&&temp[0].equals("select")&&temp[1].equals("*")&&temp[2].equals("from"))||(temp.length==3&&temp[0].equals("select")&&temp[1].equals("from"))||(temp.length==7 && temp[0].equals("select") && temp[1].equals("*") && temp[2].equals("from") && temp[4].equals("order") && temp[5].equals("by"))){
				return true ; 
			}
			return inp
					.matches("^(select)((\\ *\\*\\ *)|(\\ +[_a-z][a-z0-9_]*\\ +))from\\ +[_a-z][a-z0-9_]*\\ +where\\ +[_a-z][a-z0-9_]*\\ *((>)|(<)|(=)|(>=)|(<=)|(==))\\ *(('.*')|([0-9]+))\\ *;?");
		}

		private boolean IsInsertValid(String inp) {
			return inp
					.matches("^(insert)\\ +(into)\\ +[_a-z][a-z0-9_]*\\ *(\\(\\ *(\\ *[_a-z][a-z0-9_]*\\ *,)*\\ *[_a-z][a-z0-9_]*\\ *\\))?\\ *values\\ *\\(\\ *(\\ *(('.*')|([0-9]+))\\ *,)*\\ *(('.*')|([0-9]+))\\ *\\)\\ *;?");
		}

		private boolean IsDeleteValid(String inp) {
			String tmp= inp ; 
			tmp=tmp.toLowerCase();
			String[] temp =tmp.split(" ") ;
			if ((temp.length==4&&temp[0].equals("delete")&&temp[1].equals("*")&&temp[2].equals("from"))||(temp.length==3&&temp[0].equals("delete")&&temp[1].equals("from"))){
				return true ; 
			}
			return inp
					.matches("^(delete)\\ +(from)\\ +[_a-z][a-z0-9_]*\\ +where\\ +[_a-z][a-z0-9_]*\\ *=\\ *(('.*')|([0-9]+))\\ *;?");
		}

		private boolean IsCreateValid(String inp) {
			if (inp.matches("^(create)\\ +(database)\\ +[_a-z][a-z0-9_]*\\ *;?"))
				return true;
			return inp
					.matches("^(create)\\ +(table)\\ +[_a-z][a-z0-9_]*\\ *\\(\\ *(\\ *[_a-z][a-z0-9_]*\\ +((varchar\\(255\\))|(int))\\ *,)*\\ *[_a-z][a-z0-9_]*\\ +((varchar\\(255\\))|(int))\\ *\\)\\ *;?");
		}
}
