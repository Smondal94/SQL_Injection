<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>


<html>
<head>
<link href="bootstrap.min.css" rel="stylesheet">
<title>E Banking | Contact :: w3layouts</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href='//fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
<link href="web/css/style.css" rel="stylesheet" type="text/css" media="all" />
<script src='web/js/jquery-1.8.1.min.js' type='text/javascript'></script>
<script src='web/js/jquery.kwicks.js' type='text/javascript'></script>
</head>
<script type="text/javascript">

function callRegister(){
	 document.cloudForm.action='goto_register_page.action';
	 document.cloudForm.submit();
}

function registerSubmit(){
	 document.cloudForm.action='register.action';
  	 document.cloudForm.submit();
}
function logout(){
	
	document.cloudForm.action="logout.action";
	document.cloudForm.submit();
}


</script>


</head>
<body >

		<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    
    <ul class="nav navbar-nav">
    <li><img src="vass.jpg" width="110" height="50"></li>
   <li><a href="#" onclick="logout()">LOGIN</a></li>
    
    
      
    </ul>
  </div>
</nav>
	 
				  <div class="contact-form">
				  	<center><h3>Register </h3>
                <div class="cleaner_h50">
               	<s:if test="%{msg!=null}">
					<font color="red"><s:property value="%{msg}"/></font>
				</s:if>
                </div>
                <s:form name="cloudForm" id="cloudForm" theme="simple" method="post" class="form-horizontal">
				<table>
				
				
				<tr>	
					<td> <label for="author">Name</label></td><td><s:textfield cssStyle="width:400px;" name="user.name" id="name" cssClass="textbox"></s:textfield> </td>
				</tr>
				
				<tr>	
					<td style="padding-top: 15px;"> <label for="author">Email Id</label></td><td><s:textfield cssStyle="width:400px;" name="user.email" id="email" cssClass="textbox"></s:textfield> </td>
				</tr>
				
				<tr>
						
					<td style="padding-top: 15px;"> <label for="author">Password</label></td><td><s:password cssStyle="width:400px;" class="form-control" name="user.password" id="password" cssClass="textbox"></s:password> </td>
			
				</tr>
				<tr>
					<td  style="padding-top: 15px;"> <label for="author">Repassword</label></td>
				
					
					<td><s:password cssStyle="width:400px;" name="user.repassword" id="repassword" cssClass="textbox"></s:password> </td>
				</tr>
				<tr>
					<td  style="padding-top: 15px;"> <label for="author">Mobile</label></td>
					
					<td><s:textfield cssStyle="width:400px;" name="user.mobile" id="mobile" cssClass="textbox"></s:textfield> </td>
				</tr>
				<tr>
					<td  style="padding-top: 15px;"> <label for="author">Address</label></td>
					
					<td><s:textfield cssStyle="width:400px;" name="user.address" id="address" cssClass="textbox"></s:textfield> </td>
				</tr>
				
				<tr>
					<td style="padding-top: 15px;"> <label for="author">Account Type</label></td>
					
					<td><s:textfield cssStyle="width:400px;" name="user.accountType" id="accountType" cssClass="textbox"></s:textfield> </td>
				</tr>
				
				<tr>
					<td  style="padding-top: 15px;"> <label for="author">Amount</label></td>
					
					<td><s:textfield cssStyle="width:400px;" name="user.amount" id="amount" cssClass="textbox"></s:textfield> </td>
				</tr>
				
				
			
				
							
					
				
				</table>
			</s:form><br><br><input type="button" class="btn btn-primary"onclick="registerSubmit()" class="submit_btn" value="Submit ">	</center>
		       </div>
  				</div>				
		  </div>
</div>	
</div>

</body>
</html>