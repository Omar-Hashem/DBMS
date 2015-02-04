//
//
//import java.awt.Point;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.util.ArrayList;
//
//import javax.swing.JFileChooser;
//import javax.swing.JFrame;
//
//
//public class test {
//	public static void main (String args  [] ) {
//////		Boolean  x ; 
//////		System.out.println(x.getClass());
//////		File f= new File ("xm.txt");
//////		XStream x= new XStream () ;
//////		FileOutputStream out = new FileOutputStream("xm.txt");
//////		x.toXML("hamada", out);
//////		FileOutputStream out= new FileOutputStream("omar.txt"); 
////		JFrame parentFrame = new JFrame();
////		JFileChooser fileChooser = new JFileChooser();
////		fileChooser.setDialogTitle("Specify a file to save");   
////		int userSelection = fileChooser.showSaveDialog(parentFrame);
////		if (userSelection == JFileChooser.APPROVE_OPTION) {
////		    File fileToSave = fileChooser.getSelectedFile();
////		    String tmp =  fileToSave.getAbsolutePath();
////		    tmp+=".xml" ;
////		    System.out.println( tmp );
////		}
////		System.out.println( "cancel" )  ;
////		
////		Validator o = new Validator() ;
//		
////		System.out.println(o.Validate_Expression("CREATE TABLE Persons PersonID int,LastName varchar(255),FirstName varchar(255),Address varchar(255),City varchar(255);"));
////		System.out.println(o.Validate_Expression("CRETE DATABASE LAB3;"));
////		System.out.println(o.Validate_Expression("INSERT Persons (PersonID,LastName,FirstName,MiddleName)VALUES (1,'Mohamed','Tamer','Ali');"));
////		System.out.println(o.Validate_Expression("SELECT *  Customers;"));
////		System.out.println(o.Validate_Expression("UPDATE Customers  ContactName='Alfred Schmidt', City='Hamburg' WHERE CustomerName='Alfreds Futterkiste'; "));
////		
////		
////		System.out.println(o.Validate_Expression("DELETE FROM Persons;"));
////		System.out.println(o.Validate_Expression("CREATE TABLE Persons(PersonID int,LastName varchar(255),FirstName varchar(255),Address varchar(255),City varchar(255));"));
////		System.out.println(o.Validate_Expression("CREATE DATABASE LAB3;"));
////		System.out.println(o.Validate_Expression("INSERT INTO O (PersonID,LastName,FirstName)VALUES (1, 23,'Tamer');"));
////		System.out.println(o.Validate_Expression("CREATE TABLE Persons(PersonID int,LastName varchar(255),FirstName varchar(255),Address varchar(255),City varchar(255));"));
////		System.out.println(o.Validate_Expression("CREATE TABLE Persons(PersonID int,LastName varchar(255),FirstName varchar(255),Address varchar(255),City varchar(255));"));
////		System.out.println(o.Validate_Expression("INSERT INTO Persons (PersonID,LastName,FirstName,MiddleName)VALUES (1,'Mohamed','Tamer','Ali');"));
////		System.out.println(o.Validate_Expression("INSERT INTO Persons (PersonID,LastName,FirstName)VALUES (1, 23,'Tamer');"));
////		System.out.println(o.Validate_Expression("INSERT INTO O (PersonID,LastName,FirstName)VALUES (1, 23,'Tamer');"));
////		System.out.println(o.Validate_Expression("Delete * from persons;"));
////		System.out.println(o.Validate_Expression("INSERT INTO Persons (PersonID,LastName,FirstName,address,city) VALUES (1,'Mohamed','Ali','11 street','Tanta');"));
////		System.out.println(o.Validate_Expression("INSERT INTO Persons (PersonID,LastName,FirstName,address,city) VALUES (2,'Mohamed','Tamer','11 street','Alexandria');"));
////		System.out.println(o.Validate_Expression("INSERT INTO Persons VALUES (3,'Ahmad','Mohsen','12 street','Cairo');"));
////		System.out.println(o.Validate_Expression("INSERT INTO Persons VALUES (4,'Bassem','Yasser','43 street','Banha');"));
////		System.out.println(o.Validate_Expression("SELECT * from Persons"));
////		System.out.println(o.Validate_Expression("select * from persons where PersonID > 1"));
////		System.out.println(o.Validate_Expression("select * from persons where LastName='Mohamed'"));
////		System.out.println(o.Validate_Expression("select FirstName from persons where address='12 street'"));
////		System.out.println(o.Validate_Expression("UPDATE persons SET Lastname='Salem', City='Hamburg' WHERE personid= 1;"));
////		System.out.println(o.Validate_Expression("select FirstName from persons where City='Hamburg'"));
////		System.out.println(o.Validate_Expression("select * from persons where City='Hamburg'"));
////		System.out.println(o.Validate_Expression("DELETE FROM persons WHERE lastname='salem';"));
////		System.out.println(o.Validate_Expression("select * from persons where City='Hamburg'"));
//		
////		ArrayList<String> a = new ArrayList<String>();
////
////		a.add("PersonID");
////		a.add("LastName");
////		System.out.println(o.Validate_Parameters(a, "Persons"));
////	}
//
//
//
////
////import java.util.ArrayList;
////import java.util.List;
////
////
////public class test {
////	public static void main(String[] args) throws Exception {
//		DB test = new DB();
//////
//////		Parser d = new Parser();
//////		d.perform("CREATE TABLE Persons(PersonID int,LastName varchar(255),FirstName varchar(255),Address varchar(255),City varchar(255));");
//////		
//		Condition con = new Condition("PersonID",">","1");
//		System.out.println(test.conditionMatch("2", con.getOperator(), con.getValue()));
//	
////////
////////		ArrayList<String> a0 = new ArrayList<String>();
//
////		a.add("FirstName");
////		a.add("City");
////		a.add("Address");
////////		
////////		a0.add("6");
////////		a0.add("aya");
////////		a0.add("moneim");
////////		a0.add("dsfsfd");
////////		a0.add("dsfsdfa");
//////		ArrayList<String> a = new ArrayList<String>();
//////		ArrayList<String> a0 = new ArrayList<String>();
//////		a0.add("PersonID");
////////		a0.add("LastName");
////////		a0.add("FirstName");
////////		a0.add("address");
////////		a0.add("city");
////////		a.add("address");
////////		a.add("PersonID");
////////		a.add("FirstName");
////////		a.add("city");
////		ArrayList<Column> col = new ArrayList<Column>();
////		for(int i=0;i<a.size();i++){
////			if(i==0)
////				col.add(new Column(a.get(i) , "int", 255));
////			else
////				col.add(new Column(a.get(i) , "varchar", 255));
////		}
////		System.out.println(test.createTable(col, "Persons"));
////////		System.out.println(test.selectAll("Persons", null,a0,true));
//////
////////		System.out.println(test.insert(null,a0,"Persons"));
////////		System.out.println(test.delete("Persons", con));
////////		System.out.println(test.selectAll("Persons", null));
//////	}
////////	public static void main (String args [] ){
////////
//////////		int x = 0;
//////////		ArrayList<Object>a = new ArrayList<Object>();
//////////		a.add(x);
//////////		System.out.println(a.get(0).getClass().toString());
////////		ArrayList<Column> columns = new ArrayList <Column> (); 
////////		columns.add(new Column("firstname", "varchar(255)", 0 )) ;
////////		columns.add (new Column("ID", "int",  0 )) ;
////////		Schema o = new Schema () ; 
////////		o.schemaCreating(columns, "C:\\Users\\Omar\\Desktop\\schema.xsd", "persons", "person");
////////		columns.clear(); 
////////		columns=o.schemaParsing("C:\\Users\\Omar\\Desktop\\schema.xsd") ;
////////		for (int i=0;i<columns.size();i++ ){
////////			System.out.println(columns.get(i).getColName()+" " +columns.get(i).getdataType());
////////		}
////////	}
////////	public static void main(String[] args) throws Exception {
////////		DB test = new DB();
//////////
////////		Condition con = new Condition("PersonID",">","1");
////////		ArrayList<String> a = new ArrayList<String>();
////////		ArrayList<String> a0 = new ArrayList<String>();
////////		a0.add("PersonID");
//////////		a0.add("LastName");
//////////		a0.add("FirstName");
////////		a0.add("address");
////////		a0.add("city");
//////////		a.add("address");
////////		a.add("PersonID");
//////////		a.add("FirstName");
//////////		a.add("city");
//////////		a0.add("6");
////////////		a0.add("null");
////////////		a0.add("moneim");
////////////		a0.add("dsfsfd");
//////////		a0.add("dsfsdfa");
////////////		test.parseSchema();
////////////		System.out.println(test.insert(a,a0,"Persons"));
//////////		Integer x = 0;
//////////		System.out.println(x.getClass().getName());
////////////		System.out.println(test.delete("Persons", con));
//////////		(String TableName, Condition con,
//////////				ArrayList<String> orderingColumns, boolean DESC)
//////////		int[]arr = test.getIndicesArray(a, a0);
////////		int[]arr = {3};
//////////		System.out.println(arr.length);
//////////		for(int i = 0;i<arr.length;i++)
//////////			System.out.println(arr[i]);
//////////		test.parseString("1*Mohamed*Ali*11 street*Tanta\n1*Mohamed*Tamer*11 street*Alexandria\n3*Ahmed*Mohsen*12 street*Cairo\n4*Bassem*Yasser*43 street*Banha", arr, true);
////////	
//////////		System.out.println(test.selectAll("Persons", null,a0,false));
////////////
//////////		System.out.println("********************************************");
//////////		System.out.println(test.selectColumn(a0,"Persons", null,a,false));
//	}
//}