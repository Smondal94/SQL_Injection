package com.crypto.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.struts2.ServletActionContext;
import org.xml.sax.SAXException;
import com.crypto.bo.User;
import com.crypto.dao.UserDAO;
import com.crypto.utility.StaticInfo;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

	private User user;
	
	private String msg;
	
	private String userType;
		
	public String registerPage(){		//used
		if(ServletActionContext.getRequest().getParameter("userType")!=null){
			userType=ServletActionContext.getRequest().getParameter("userType");
		}
		user=new User();
		return("success");
	}
	
	public String transfer(){		//used
		user=new User();
		return("success");
	}
	
	public String loginPage(){		//used
		if(ServletActionContext.getRequest().getParameter("userType")!=null){
			userType=ServletActionContext.getRequest().getParameter("userType");
			
		}
		user=new User();
		return("success");
	}
	
	public String logout(){		//used
		user=new User();
		ServletActionContext.getRequest().getSession().invalidate();
		return("success");
	}
	
	public String getdetails(){		//used
		
		UserDAO dao=new UserDAO();
		String result="failure";
		
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
			
		if(!dao.getdetails(user.getId())){
				msg="No Such account Exists";
				result="failure";
			}else{
				msg="Data found";
				result="success";
			}
		
		return result;
	}
	
	
	public String login() throws FileNotFoundException, SAXException, IOException{		//used
		String result="failure";
		
		UserDAO dao=new UserDAO();
		String type="Injection";
		String sani="yes";
		if(ServletActionContext.getRequest().getParameter("type")!=null){
			type=ServletActionContext.getRequest().getParameter("type").toString();
		}
		if(ServletActionContext.getRequest().getParameter("sanitizer")!=null){
			sani=ServletActionContext.getRequest().getParameter("sanitizer").toString();
			StaticInfo.sanitizer=sani;
		}
		
		if(user.getEmail()==null || user.getEmail().trim().equals("") ||
			user.getPassword()==null || user.getPassword().trim().equals("")){
			msg="Empty field not allowed";
			result="failure";
		}else if(!dao.checkLogin(user.getEmail(), user.getPassword(),type)){ //attack found
			System.out.println("Edit-Value:"+StaticInfo.threshold);
			msg="Invalid Username and Password";
			if(StaticInfo.threshold<100 && type.equals("Detection") && sani.equals("No"))
				msg+="( Edit-Value : "+StaticInfo.threshold+" )";
			result="failure";
		}else{
			user.setId(StaticInfo.userId);
			user.setName(StaticInfo.userName);
			ServletActionContext.getRequest().getSession().setAttribute("user",user);
			result="success";
		}
		return(result);
	}
	

	public String register(){		//used
		
		UserDAO dao=new UserDAO();
		String result="failure";
		if(user.getName()==null || user.getName().trim().equals("") ||
				user.getEmail()==null || user.getEmail().trim().equals("") ||
				user.getPassword()==null || user.getPassword().trim().equals("") ||
				user.getMobile()==null || user.getMobile().trim().equals("") 
				){
			
			msg="Empty field not allowed";
			result="failure";
			
		}/*else if(!StaticInfo.emailValidation(user.getEmail())){
			msg="Enter valid email address";
			result="failure";
		}else if(dao.checkRegisterNumberAlreadyExists(user.getRegisterNumber(),user.getEmail())){
			msg="Register number / Email already exists";
			result="failure";
		}*/else if(!user.getPassword().equalsIgnoreCase(user.getRepassword())){
			msg="Passowrd Mismatch";
			result="failure";
		}else if(!StaticInfo.mobileValidation(user.getMobile())){
			msg="Enter valid mobile number";
			result="failure";
		}else{
			String pass=StaticInfo.generatePassword();
			dao.insertRegister(user.getName(),user.getEmail(),user.getPassword(), user.getMobile(), user.getAddress(),user.getAccountType(),user.getAmount());
			msg="Register successfully";
			ServletActionContext.getRequest().getSession().setAttribute("pass",pass);
			//User userSession=dao.getUserDetailsForUsername(user.getUsername(),userType);
			user=new User();
			result="success";
		}
		
		return(result);
		
	}
	
	public String maketransfer(){		//used
		UserDAO dao=new UserDAO();
		String result="failure";
		
		User user1=(User)ServletActionContext.getRequest().getSession().getAttribute("user");
		
		if(user.getId()==0 || user.getTid()==0 || user.getAmount()==null || user.getAmount().trim().equals("")){
			msg="Empty field not allowed";
			result="failure";
		}else if(user1.getId()==user.getId()){
			if(dao.maketransfer(user.getId(),user.getTid(),user.getAmount())){
				msg="Transfer Successfull";
				user=new User();
				result="success";
			}else
			{
				msg="Transfer Not Successfull";
				result="failure";
			}
		}else{
			msg="Enter Correct Account no";
			result="failure";
		}	
		return result;
		
	}
	
	
	
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}	
}
