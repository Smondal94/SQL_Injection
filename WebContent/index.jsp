<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="keywords" content="Red Blog Theme, Contact Information, Free CSS Templates" />
<meta name="description" content="Red Blog Theme (contact page) - Free CSS Templates by templatemo.com" />
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">

function callLogin(){
	 document.cloudForm.action='goto_login_page.action';
  	 document.cloudForm.submit();
}

</script>


</head>
<body>
<s:form name="cloudForm" id="cloudForm" theme="simple" method="post">
</s:form>

<script>
	callLogin();
</script>
</body>
</html>