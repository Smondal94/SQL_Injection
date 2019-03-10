package com.crypto.servlet;
import javax.servlet.*;

import com.crypto.utility.StaticInfo;

public class StartUp implements ServletContextListener{


	public void contextInitialized(ServletContextEvent sce){

		ServletContext sc=sce.getServletContext();
		try{

			StaticInfo.url=sc.getInitParameter("url");
			StaticInfo.dbUser=sc.getInitParameter("dbuser");
			StaticInfo.dbPass=sc.getInitParameter("dbpass");
			
			StaticInfo.hashTable.put("select", sc.getInitParameter("select"));
			StaticInfo.hashTable.put("delete", sc.getInitParameter("delete"));
			StaticInfo.hashTable.put("insert", sc.getInitParameter("insert"));
			StaticInfo.hashTable.put("update", sc.getInitParameter("update"));
			
			System.out.println("Initialize");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent sce){

		try{
			System.out.println("Destroy");
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}