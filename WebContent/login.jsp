<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->

<html>
<head>
<title>E Banking</title>
<link href="bootstrap.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
	<link href='//fonts.googleapis.com/css?family=Open+Sans'
		rel='stylesheet' type='text/css'>
		<link href="web/css/style.css" rel="stylesheet" type="text/css"
			media="all" />
		<script src='web/js/jquery-1.8.1.min.js' type='text/javascript'></script>
		<script src='web/js/jquery.kwicks.js' type='text/javascript'></script>
</head>

<script type="text/javascript">
	function callRegister() {
		document.cloudForm.action = 'goto_register_page.action';
		document.cloudForm.submit();
	}
	
	function callLogin(userType) {
		document.cloudForm.action = 'goto_login_page.action?userType='
				+ userType;
		document.cloudForm.submit();
	}

	function loginSubmit() {
		if (document.getElementById("email").value != ""
				&& document.getElementById("password").value != "") {
			document.cloudForm.action = 'login.action';
			document.cloudForm.submit();
		} else {
			alert("Invalid username and password");
		}
	}
</script>



</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    
    <ul class="nav navbar-nav">
    <li><img src="vass.jpg" width="110" height="50"></li>
        
   <li><a href="#" onclick="callRegister()">REGISTER</a></li>
    
   
    </ul>
  </div>
</nav>
	
	<div class="wrap">
	
			<center>
				<h1>
					<B>Login Page</B>
				</h1>
				<br>

					<div class="cleaner_h50" style="color: red;">
						<s:if test="%{msg!=null}">
							<s:property value="%{msg}" />
						</s:if> 
					</div>
					<div id="contact_form">


						<s:form name="cloudForm" id="cloudForm" theme="simple" class="form-horizontal"
							method="post">
							<table>
								
								<tr >
									<td  style="padding-top: 15px;"><label for="inputEmail3" class="col-sm-2 control-label">Email</label></td><td><input type="text" name="user.email" id="email"
											 class="form-control"><div class="col-sm-10"></td>
								</tr>
								
								<br>
								<tr>
									<td style="padding-top: 15px;"><label for="inputPassword3" class="col-sm-2 control-label">Password</label>
    <div class="col-sm-10"></td><td><input type="password" name="user.password"
											class="form-control" id="password"></td>
								</tr>
								
								<tr><td style="padding-top:15px;"><label for="inputPassword3" class="col-sm-2 control-label">Type</label></td>
									<td >
									<select name="type" class="form-control" >
									<option>Injection</option>
									<option>Detection </option>
									</select>
									
									</td>
								</tr>
								
								<tr><td style="padding-top:15px;"><label for="inputPassword3" class="col-sm-2 control-label">Sanitizer</label></td>
									<td >
									<select name="sanitizer" class="form-control" >
									<option>Yes</option>
									<option>No </option>
									</select>
									
									</td>
								</tr>
								
								
							</table>
							<center>
							<br><br>
									<input
										type="button" onclick="loginSubmit()" class="btn btn-primary"
										value="Login">
						
</center>
							<br><br><br><br><br><br><br></br></br></br>
						</s:form>
					</div>
			</div>
		</div>
</body>
</html>