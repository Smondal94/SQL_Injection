package com.crypto.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.struts2.ServletActionContext;
import org.xml.sax.SAXException;
import com.crypto.bo.User;
import com.crypto.database.DatabaseConnection;
import com.crypto.utility.Analyzer;
import com.crypto.utility.SendMailSSL;
import com.crypto.utility.StaticInfo;
import com.mysql.jdbc.Statement;

public class UserDAO {

	
	DatabaseConnection dbCon=new DatabaseConnection();
	Connection conn=null;
	ResultSet rs=null;
	
	
	public boolean checkLogin(String email,String password,String type) throws FileNotFoundException, SAXException, IOException{
		boolean status=false;
		 try {
			  Connection con =	conn=dbCon.getConnection();
			  //CallableStatement call = con.prepareCall();
			  java.sql.Statement stmt = conn.createStatement();
		      String sql;
		      
		      if(type.equalsIgnoreCase("Injection")){
		    	  sql = "select * from users where email='"+email+"' and password='"+password+"'";
		    	  System.out.println(sql);
			      rs = stmt.executeQuery(sql);
			      if(rs.next()){
					  status=true;
					  StaticInfo.userId=rs.getInt("id");
					  StaticInfo.userName=rs.getString("name");
					  //System.out.println(StaticInfo.userId);
				  }
		      }else{
		    	  sql = "select * from users where email='"+email+"' and password='"+password+"'";
		    	  System.out.println(sql);
		    	  boolean st=Analyzer.process(sql,email,password);
		    	  System.out.println(st);
		    	  // st=true Attack found  st=false Normal
		    	  if(st){
		    		status=false;
		    		SendMailSSL.mainMethod();
		    	  }else{
		    		  rs = stmt.executeQuery(sql);
				      if(rs.next()){
						  status=true;
						  StaticInfo.userId=rs.getInt("id");
						  StaticInfo.userName=rs.getString("name");
						  //System.out.println(StaticInfo.userId);
					  }
		    	  }
		      }
			  con.close();
			  
			} catch (SQLException e) {
			  e.printStackTrace();
			} catch (ClassNotFoundException e) {
			  e.printStackTrace();
			}
			
		return(status);
	}
	
	public void insertRegister(String name,String email,String password,String mobile,String address,String accounttype,String amount){

		try {
			
			  Connection con =	conn=dbCon.getConnection();
			  Statement st = (Statement) con.createStatement();
			  
			  int  acntno;
			  String query = "select max(id) from users";
			  rs=st.executeQuery(query);

			  if(rs.next())
	                acntno=rs.getInt(1)+1;
	            else
	                acntno=1;
			  
			  CallableStatement call = con.prepareCall("{call insert_register(?,?,?,?,?,?,?,?)}");
			  call.setInt(1,acntno);
			  call.setString(2,name);
			  call.setString(3,mobile);
			  call.setString(4,email);
			  call.setString(5,password);
			  call.setString(6,amount);
			  call.setString(7,address);
			  call.setString(8,accounttype);
			  call.execute();
			  con.close();
			  
			} catch (SQLException e) {
			  e.printStackTrace();
			} catch (ClassNotFoundException e) {
			  e.printStackTrace();
			}
	}
	
	public boolean getdetails(int id){
		boolean status=false; 
		try{
			Connection con =conn=dbCon.getConnection();
			Statement st = (Statement) con.createStatement();
			
			String query="select * from users where id='"+ id +"'";
			rs=st.executeQuery(query);
			
			if(rs.next()){
				User user=(User)ServletActionContext.getRequest().getSession().getAttribute("user");
				user.setAmount(rs.getString(6));
				user.setMobile(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setAddress(rs.getString(7));
				user.setAccountType(rs.getString(8));
				status=true;
			}else{
				status=false;
			}
			
			
		}catch (SQLException e) {
			  e.printStackTrace();
			} catch (ClassNotFoundException e) {
			  e.printStackTrace();
			}
		
		return status;
	}
	
	public boolean checkRegisterNumberAlreadyExists(String regNo,String email){
		boolean status=false;
		 try {
			  Connection con =	conn=dbCon.getConnection();
			  CallableStatement call = con.prepareCall("{call check_register_number_already_exists(?,?)}");
			  call.setString(1,regNo);
			  call.setString(2,email);
			  rs = call.executeQuery();
			  if(rs.next()){
				  status=true;
			  }
			  con.close();
			  
			} catch (SQLException e) {
			  e.printStackTrace();
			} catch (ClassNotFoundException e) {
			  e.printStackTrace();
			}
			
			return(status);
	}
	
	public boolean maketransfer(int fid,int tid,String amount){
		
		boolean status=false;
		
		try{
			Connection con =conn=dbCon.getConnection();
			
			Statement st1 = (Statement)con.createStatement();
			Statement st2 = (Statement)con.createStatement();
            
            String query1 = "select * from users where id='"+fid+"'";
            ResultSet rs1 = st1.executeQuery(query1);
            
            String query2 = "select * from users where id='"+tid+"'";
            ResultSet rs2 = st2.executeQuery(query2);
			
            if(rs1.next() && rs2.next()){
            	
            	String amnt1=rs1.getString(6);
            	if(Integer.parseInt(amnt1)>=Integer.parseInt(amount)){
            		
            		String namnt1 =Integer.toString(Integer.parseInt(amnt1) - Integer.parseInt(amount));
                    String query3 = "update users set amount = '"+ namnt1+"' WHERE id = '"+ fid +"'";
                    
                    if(st1.executeUpdate(query3)>=1)
                    	System.out.println("Amount " +amount+ " Debited from account no=" + fid);
                    
                    String amnt2=rs2.getString(6);
                    String namnt2 = Integer.toString(Integer.parseInt(amnt2) + Integer.parseInt(amount));
                    
                    String query4 = "update users set amount = '"+ namnt2 +"' WHERE id = '"+ tid +"'";
                    if(st2.executeUpdate(query4)>=1)
                        System.out.println("Amount "+amount+" Credited To account no=" + tid);	
                    
                    status=true;
                    
            	}else{
            		status=false;
            	}	
            }else{
            	status=false;
            }	
		}catch (SQLException e) {
			  e.printStackTrace();
			} catch (ClassNotFoundException e) {
			  e.printStackTrace();
			}
		return status;
	}
}
