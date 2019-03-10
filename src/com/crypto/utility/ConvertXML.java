package com.crypto.utility;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.dom4j.Document;			//interface
import org.dom4j.DocumentHelper;	//interface
import org.dom4j.Element;			//interface
import org.dom4j.io.OutputFormat;	//class
import org.dom4j.io.XMLWriter;		//class

public class ConvertXML {
   public static void mainMethod(String query,String path) {
      try {
    	  
    	  String condition="no";
    	  String mainkeys="";
    	  int querycount=1;
    	  String where="false";
    	  String nxtquery=query;
    	  //select * from users where email='' and password=''; update users set password='the' where id=2 or '1'='1' ; 
    	  
    	  String mainnode=query.substring(0,query.indexOf(" "));
    	  
    	  //counts query
    	  while(nxtquery.indexOf(";")!=-1){			//for Piggy backed attacks
    		  querycount++;
    		  nxtquery=nxtquery.substring(nxtquery.indexOf(";")+1, nxtquery.length());
    	  }
    	  
    	  if(query.indexOf(";")!=-1)
    		  query=query.substring(0,query.indexOf(";"));
    	  
    	  if(mainnode.equalsIgnoreCase("select")){
    		  mainkeys="select,";
    		  
    		  if(query.indexOf("from")!=-1 ){
    			mainkeys=mainkeys+"from"+",";  
    		  }
    		  
    		  if(query.indexOf("where")!=-1 ){
    			  mainkeys=mainkeys+"where";
    			  where="true";
    		  }
    		  
    		  if(query.indexOf("1=1")!=-1){
    			  condition="same";
    		  }else{
    			  condition="diff";
    		  }
    		  
    	  }else if(mainnode.equalsIgnoreCase("insert")){
    		  	mainkeys="insert,";
    		  
    		  if(query.indexOf("into")!=-1 ){
    			mainkeys=mainkeys+"into"+",";  
    		  }
    		  
    		  if(query.indexOf("values")!=-1 ){
      			mainkeys=mainkeys+"values";  
      		  }
    		  condition="no";
    		  
    	  }else if(mainnode.equalsIgnoreCase("delete")){
    		  mainkeys="delete,";
    		  
    		  if(query.indexOf("from")!=-1 ){
    			mainkeys=mainkeys+"from"+",";  
    		  }
    		  
    		  if(query.indexOf("where")!=-1 ){
    			  mainkeys=mainkeys+"where";
    			  where="true";
    		  }
    		  
    		  if(query.indexOf("1=1")!=-1){
    			  condition="same";
    		  }else{
    			  condition="diff";
    		  }
    		  
    	  }else if(mainnode.equalsIgnoreCase("update")){
    		  mainkeys="update,";
    		  
    		  if(query.indexOf("set")!=-1 ){
    			mainkeys=mainkeys+"set"+",";  
    		  }
    		  
    		  if(query.indexOf("where")!=-1 ){
    			  mainkeys=mainkeys+"where";
    			  where="true";
    		  }
    		  
    		  
    		  if(query.indexOf("1=1")!=-1){
    			  condition="same";
    		  }else{
    			  condition="diff";
    		  }
    		  
    	  }
    	  
    	  
    	  
         Document document = DocumentHelper.createDocument();	//creates a document tree
         Element root = document.addElement( "querys" );
         Element supercarElement= root.addElement(mainnode);
         
         supercarElement.addElement("mainkeys").addText(mainkeys);
         supercarElement.addElement("tablename").addText("text");
         supercarElement.addElement("fieldname").addText("text");
         supercarElement.addElement("querycount").addText(""+querycount);
         supercarElement.addElement("where").addText(where);
         supercarElement.addElement("condition").addText(condition);


         // Pretty print the document to System.out
         //A static helper method to create the default pretty printing format. This format consists of an indent of 2 spaces, 
         //newlines after each element and all other whitespace trimmed, and XMTML is false.
         OutputFormat format = OutputFormat.createPrettyPrint();
         
         //XMLWriter takes a DOM4J tree and formats it to a stream as XML.
         //creates the temp.xml file
         XMLWriter writer = new XMLWriter( new FileOutputStream(path), format );
         
         //write(...) method prints any of the standard DOM4J classes, including Document and Element, to either a Writer or an OutputStream. 
         writer.write( document );
         
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}