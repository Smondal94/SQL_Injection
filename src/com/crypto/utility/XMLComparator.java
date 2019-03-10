package com.crypto.utility;

import java.io.BufferedReader; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.io.Reader; 
import java.util.List;

import org.custommonkey.xmlunit.DetailedDiff; 
import org.custommonkey.xmlunit.Diff;		//implements DifferenceListener, ComparisonController
import org.custommonkey.xmlunit.Difference; //
import org.custommonkey.xmlunit.XMLUnit;
import org.xml.sax.SAXException; 

public class XMLComparator {
	
	public static int mainMethod(String path1,String path2) throws FileNotFoundException, SAXException, IOException {
		
		// reading two xml file to compare 
		FileInputStream fis1 = new FileInputStream(path1);	//from master file
		FileInputStream fis2 = new FileInputStream(path2); 	//temp.xml file
		
		//InputStreamReader is a bridge from byte streams to character streams.
		//It reads bytes and translates them into characters according to a specified character encoding.
		//Read text from a character-input stream, buffering characters so as to provide for the efficient reading of characters.
		//Without buffering,cause bytes to be read from the file, converted into characters, and then returned.
		BufferedReader source = new BufferedReader(new InputStreamReader(fis1));	//from master file 
		BufferedReader target = new BufferedReader(new InputStreamReader(fis2));	//temp.xml file
		 
		// access to project control parameters
		XMLUnit.setIgnoreWhitespace(true);	//ignore the white space when comparing, do not change the content of the file.
		
		//comparing two XML using XMLUnit in Java 
		List differences = compareXML(source, target); 
		
		//showing differences found in two xml files 
		return printDifferences(differences); 
	}
	
	public static List compareXML(Reader source, Reader target) throws SAXException, IOException{

		//Compares and describes differences between XML documents.
		//The difference between compared documents is contained in a message buffer held in this class
		Diff xmlDiff = new Diff(source, target); 
		
		//For a list of all differences between the documents DetailedDiff class used. 
		DetailedDiff detailXmlDiff = new DetailedDiff(xmlDiff);
		return detailXmlDiff.getAllDifferences(); 
	} 
	
	public static int printDifferences(List<Difference> differences){
		
		int totalDifferences = differences.size();
		int threshold=100;
		
		System.out.println("Total differences : " + totalDifferences); 
		
		for(Difference difference : differences){
			
			//NodeDetail from the Node where this difference was encountered
			System.out.println("Difference at node:-"+difference.getTestNodeDetail().getNode().getParentNode().getNodeName());
			System.out.println(difference);	
			
			String nodename=difference.getTestNodeDetail().getNode().getParentNode().getNodeName();
			
			if(nodename.equalsIgnoreCase("mainkeys")){
				threshold=threshold-15;
			}else if(nodename.equalsIgnoreCase("tablename")){
				threshold=threshold-5;
			}else if(nodename.equalsIgnoreCase("fieldname")){
				threshold=threshold-10;
			}else if(nodename.equalsIgnoreCase("querycount")){
				threshold=threshold-35;
			}else if(nodename.equalsIgnoreCase("where")){
				threshold=threshold-10;
			}else if(nodename.equalsIgnoreCase("condition")){
				threshold=threshold-25;
			}
			
		}
		
		StaticInfo.threshold=threshold;
		return totalDifferences;
	} 
}

