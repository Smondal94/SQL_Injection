package com.crypto.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StaticInfo {

	public static String url;
	
	public static String dbUser;
	
	public static int userId;
	
	public static String path="E:/Deep/My Documents/8th Sem/Project/Code/sqllogs/";
	
	public static int threshold=100;
	
	public static String userName;

	public static String dbPass;
	
	public static String checkSums[] = {"a554eb8da81aa0a390afffeb51bef832acd0c5e3"};
	
	public static String keypath;
	
	public static Hashtable<String, String> hashTable= new Hashtable<String, String>();
	
	public static String storagePath;
	
	public static boolean result;
	
	public static String sanitizer;
	
	public static ArrayList<String> formatList;
	
	public static HashMap<Integer, Long> timeMap=new HashMap<Integer, Long>();
	
	
	public static boolean emailValidation(String email){
		
		Pattern p=Pattern.compile("[a-zA-Z]*[0-9]*@[a-zA-Z]*.[a-zA-Z]*.[a-zA-Z]*");
		Matcher m=p.matcher(email);
		//Matcher m=p.matcher(args[0]);
		boolean b=m.matches();
		
		return(b);
	}
	
	public static boolean mobileValidation(String mobile){
		
		Pattern p=Pattern.compile("[0-9]*");
		Matcher m=p.matcher(mobile);
		boolean b=m.matches();
		if(mobile.length()==10 && b){
			System.out.println("Match");
			return(true);
		}else{
			System.out.println("Not Match");
			return(false);
		}
	}
	
	public static String publicKeyFileName(int docId){
		return(keypath+""+docId+"-public.key");
	}
	
	public static String privateKeyFileName(int docId){
		return(keypath+""+docId+"-private.key");
	}
	
	public static String groupPrivateKeyFileName(int groupId){
		return(keypath+""+groupId+"-group-private.key");
	}
	
	public static String groupPublicKeyFileName(int groupId){
		return(keypath+""+groupId+"-group-public.key");
	}
	
	/*public static String decShareKeyFileName(int shareID){
		return(keypath+""+shareID+"-share-dec.key");
	}*/
	
	public static String cloudFileName(int docId,String ext){
		return(storagePath+""+docId+"."+ext);
	}
	
	public static String generateKey(){
		String str="abcdefghijklmnopqrstuvwxyz1234567890";
		String key="";
		for(int i=0;i<16;i++){
			key=key+""+str.charAt(new Random().nextInt(36));
		}
		return(key);
	}
	public static String generatePassword(){
		String str="1234567890";
		String key="";
		for(int i=0;i<6;i++){
			key=key+""+str.charAt(new Random().nextInt(10));
		}
		return(key);
	}
	
	public static void main(String args[]){
		String str="9896 r9545";
		//mobileValidation(str);
		//System.out.println(generateKey());
		//System.out.println(generateKey());
		//System.out.println(generateKey());
		System.out.println(StaticInfo.emailValidation("vmram87@cse.sastra.edu"));
	}
	
}
