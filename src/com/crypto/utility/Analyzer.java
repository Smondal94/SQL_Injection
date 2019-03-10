package com.crypto.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.xml.sax.SAXException;


public class Analyzer {

	public static String allowedChars = ".+-0123456789#*abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@$";

	public static boolean sanitize(String input) {
		boolean status=false;
		String result = "";
		for (int i = 0; i < input.length(); i++) {
			if (allowedChars.indexOf(input.charAt(i)) >= 0) {
				result += input.charAt(i);
			}
		}
		System.out.println(input);
		System.out.println(result);
		
		if(result.length()==input.length()){
			status=true;	//not attack
		}else{
			status=false;	//attack
		}
		return status;
	}
	
	public static boolean process(String input,String email,String password) throws FileNotFoundException, SAXException, IOException{
		if(StaticInfo.sanitizer.equalsIgnoreCase("yes")){
			boolean status=sanitizer(email, password);
			if(status){
				return true; //Attack found
			}else{
				//Next Process
				return false;
			}	
		}else{
			boolean st=convertXml(input);
			if(st){
				return true; //Attack found
			}else{
				//Next Process
				return false;
			}
		}
		
	}
	
	public static boolean sanitizer(String email,String password){
		boolean flag=false;
		if(sanitize(email) && sanitize(password)){
			//Next process
			flag=false; //Attack not found
		}else{
			flag=true;//Attack Found
		}
		return flag;
	}
	
	public static boolean convertXml(String sql) throws FileNotFoundException, SAXException, IOException{
		ConvertXML.mainMethod(sql, StaticInfo.path+"temp.xml");
		
		String mainf=sql.substring(0,sql.indexOf(" "));
		
		boolean fileOk = checkSum(StaticInfo.path+"masterfiles/"+mainf+".xml", mainf);
		if(!fileOk){
			System.out.println("File "+ StaticInfo.path+"masterfiles/"+mainf+".xml" +" has been changed");
			return true;	//attack
		}
		int i=XMLComparator.mainMethod(StaticInfo.path+"masterfiles/"+mainf+".xml", StaticInfo.path+"temp.xml");
		int j=StaticInfo.threshold;
		System.out.println("i= " + i + " j=" + j);
		if(i==0){
			System.out.println("Control going to appmatching");
			j=AppMatching.mainMethod(sql);
			if(j>=85){
				return false;	//not attack
			}else{
				return true;	//attack
			}
		}else{
			return true;	//attack
		}
	}
	
	public static boolean checkSum(String path, String root){
		
		File file = new File(path);
		
		//Use SHA-1 algorithm
		MessageDigest shaDigest= null;
		try {
			shaDigest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		 
		//SHA-1 checksum
		String shaChecksum= null;
		try {
			shaChecksum = getFileChecksum(shaDigest, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("checkSum of "+path+" = "+shaChecksum);
		System.out.println("checkSum of "+path+" = "+StaticInfo.hashTable.get(root)+ " From StaticInfo file");
		
		if(StaticInfo.hashTable.get(root).equals(shaChecksum))
			return true;	//file not changed
		else
			return false;	//file changed
	}
	
	private static String getFileChecksum(MessageDigest digest, File file) throws IOException
	{
	    //Get file input stream for reading the file content
	    FileInputStream fis = new FileInputStream(file);
	     
	    //Create byte array to read data in chunks
	    byte[] byteArray = new byte[1024];
	    int bytesCount = 0; 
	      
	    //Read file data and update in message digest
	    while ((bytesCount = fis.read(byteArray)) != -1) {
	        digest.update(byteArray, 0, bytesCount);
	    };
	     
	    //close the stream; We don't need it now.
	    fis.close();
	     
	    //Get the hash's bytes
	    byte[] bytes = digest.digest();
	     
	    //This bytes[] has bytes in decimal format;
	    //Convert it to hexadecimal format
	    StringBuilder sb = new StringBuilder();
	    for(int i=0; i< bytes.length ;i++)
	    {
	        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	    }
	     
	    //return complete hash
	   return sb.toString();
	}
	
}
