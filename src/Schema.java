
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;


public class Schema {
	public void schemaCreating (ArrayList<Column> columns  , String tablepath , String tablename , String rowname){
		try {
//			File  myFile = new File("C:\\Users\\Omar\\Desktop\\schema.xsd");
			File myFile = new File (tablepath) ;
		    myFile.createNewFile();
//		    FileWriter write = new FileWriter("C:\\Users\\Omar\\Desktop\\schema.xsd");
		    FileWriter write = new FileWriter(tablepath);
		    write.write("<?xml version=\"1.0\"?>\n");
		    write.write ("<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">\n");
		    write.write("<xs:element name="+"\""+tablename +"\">\n");
		    write.write ("\t<xs:complexType>\n") ;
		    write.write("\t\t<xs:sequence>\n");
		    
		    
		    write.write("\t\t\t<xs:element name="+"\""+rowname +"\">\n");
		    write.write ("\t\t\t\t<xs:complexType>\n") ;
		    write.write("\t\t\t\t\t<xs:sequence>\n");	
		    for (int  i=0 ;i<columns.size() ;i++ ){
		    	if (columns.get(i).getdataType().equals("varchar")){
			    	write.write("\t\t\t\t\t\t<xs:element name="+"\""+columns.get(i).getColName()+"\""+" type=\"xs:string"+"\"/>\n");

		    	}
		    	else {
			    	write.write("\t\t\t\t\t\t<xs:element name="+"\""+columns.get(i).getColName()+"\""+" type=\"xs:byte"+"\"/>\n");
		    	}
		    }
		    
		    write.write("\t\t\t\t\t</xs:sequence>\n");	
		    write.write ("\t\t\t\t</xs:complexType>\n") ;
		    write.write ("\t\t\t</xs:element>\n") ;
	
	
		    
		    write.write("\t\t</xs:sequence>\n");
		    write.write("\t</xs:complexType>\n");
		    write.write ("</xs:element>\n") ;
		    write.flush();
		    write.close();
		}catch (Exception e ) {
			
		}
	    
	}
	public ArrayList<Column> schemaParsing (String tablepath ){
		ArrayList <Column> ret = new ArrayList <Column>() ; 
		try {
			File myFile = new File (tablepath) ;
			FileReader read = new FileReader (myFile) ;
		    BufferedReader br = new BufferedReader(read);
		    int counter =0 ;
		    while (br.ready()){
			   String line = br.readLine() ; 
			   counter++ ; 
			   if (counter <9){
				   continue ; 
			   }
			   if (line.contains("sequence")){
				   break ;
			   }
			   
			   //processing 
			   line = line.trim() ;
			   String[] nametype= line . split ("\\\"");
			   String name = nametype[1]; 
			   String type = nametype[3].substring(3) ;
			   String [] tmp = type.split("\\(");
			   if (type.contains("(")){
				   Column temp = new Column(name, type.split("\\(")[0], Integer.parseInt(type.split("\\(")[1].replaceAll("\\)",""))) ;
				   ret.add(temp) ;
			   }
			   else {
				   Column temp = new Column(name, type, 0) ;
				   ret.add(temp) ;
			   }
			   
		    }
		}catch  (Exception e ){
			e.printStackTrace();
//			System.out.println("exception");
		}
		return  ret ; 
	}
}
