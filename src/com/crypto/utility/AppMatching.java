package com.crypto.utility;

public class AppMatching {

	private static String strarray[]={" or "," order by "," group by ","\\0","\\b","\\t","\\n","\\r","\\Z","\\%","\\\\","\\_",","};
	private static char fpchar;
	private static char lpchar;
	private static int plength;
	
	public static int mainMethod(String sql){
	
		int threshold=100;
		int value=0;
		System.out.println(sql);
		
		//search for each pattern
		for(int p=0; p<strarray.length; p++){	//loop for each pattern
			value = process(sql,strarray[p]);
			if(value>0)	//attack, if 0 then not attack
				break;
		}
		
		StaticInfo.threshold=threshold-value;
		System.out.println("Threshold returned by appmatching: "+ (threshold-value));
		return threshold-value;
	}
	
	private static int process(String text,String pattern){
		
		fpchar = pattern.charAt(0);
		plength = pattern.length();
		lpchar = pattern.charAt(plength-1);
		
		System.out.println("Pattern: "+pattern);
		System.out.println("fpchar: "+fpchar);
		System.out.println("lpchar: "+lpchar);
		System.out.println("plength: "+plength);
		
		String temp=null;
		int value=0;
		
		for(int i=0; (i+plength)<text.length(); i++){
			
			if(text.charAt(i)==fpchar && text.charAt(i+plength-1)==lpchar){
				temp=text.substring(i, i+plength);
				
				System.out.println("Pattern After possible matching:-'"+pattern+"'");
				System.out.println("temp After possible matching:-'"+temp+"'");
				if(pattern.equals(temp)){
					value=30;
					break;
				}
			}
		}
		
		System.out.println("value: "+value);
		return value;
	}
}