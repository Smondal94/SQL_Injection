<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>

<html>
<head>
    <link href="bootstrap.min.css" rel="stylesheet">
<SCRIPT LANGUAGE="JavaScript">
function dil(form)
{
   for(var i=0; i<form.elements.length; i++)
   {
		if(form.elements[i].value == "")
		{
		   alert("Fill out all Fields")
		   document.F1.accountno.focus()
		   return false
		}
   }

   if(isNaN(document.F1.accountno.value))
   {
       alert("A/C No.  must  be  number & can't be null")
	   document.F1.accountno.value=""
	   document.F1.accountno.focus()
	   return false
   }

   if(!isNaN(document.F1.username.value))
   {
       alert("User Name  must  be  char's & can't be null")
	   document.F1.username.value=""
	   document.F1.username.focus()
	   return false
   }

   if(!isNaN(document.F1.password.value))
   {
       alert("Password  must  be  char's & can't be null")
	   document.F1.password.value=""
	   document.F1.password.focus()
	   return false
   }
   
    if(isNaN(document.F1.taccountno.value))
   {
       alert("target account  must  be  number & can't be null")
	   document.F1.taccountno.value=""
	   document.F1.taccountno.focus()
	   return false
   }
 if(document.F1.accountno.value == document.F1.taccountno.value)
    {
	   alert("Change target accountno"); 
	   document.F1.taccountno.value=""
	   document.F1.taccountno.focus()	
	   return false
	}


   if(isNaN(document.F1.amount.value))
   {
       alert("Amount  must  be  number & can't be null")
	   document.F1.amount.value=""
	   document.F1.amount.focus()
	   return false
   }

   return true   
}

function details(){
	
	document.cloudForm.action="getdetails.action";
	document.cloudForm.submit();
}

function logout(){
	
	document.cloudForm.action="logout.action";
	document.cloudForm.submit();
}

function maketransfer(){
	
	document.cloudForm.action="maketransfer.action";
	document.cloudForm.submit();
}

</SCRIPT>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Global Banking ..</title>
</head>

<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="home.jsp">Home</a>
    </div>
    <ul class="nav navbar-nav">
    <li><a href="#" onclick="logout()">LOGOUT</a></li>
    <li><a href="#" onclick="details()">DETAILS</a></li>
    <li><a href="aboutus.jsp">ABOUT US</a></li>
      
    </ul>
  </div>
</nav>

<center><h2><b><u>TRANSFER AMOUNT</u></b></h2></center><br><br>

<s:if test="%{msg!=null}">
	<font color="red"><s:property value="%{msg}"/></font>
</s:if>
<form name="cloudForm" id="cloudForm" class="form-horizontal" onSubmit="return dil(this)">
				    
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-5 control-label">ACCOUNT NO.</label>
    <div class="col-sm-4">
      <s:textfield class="form-control" id="inputEmail3" placeholder="Accountno" name="user.id"></s:textfield>
    </div>
  </div>
	<div class="form-group">
	<label for="inputPassword3" class="col-sm-5 control-label">TARGET ACCOUNT</label>
	<div class="col-sm-4">
		<s:textfield class="form-control" id="inputPassword3" placeholder="AccNo." name="user.tid"></s:textfield>
    </div>
  </div>
                                    
<div class="form-group">
    <label for="inputPassword3" class="col-sm-5 control-label">AMOUNT</label>
    <div class="col-sm-4">
      <s:textfield class="form-control" id="inputPassword3" placeholder="Amount" name="user.amount"></s:textfield>
    </div>
  </div>
<br>
                                                           
  <div class="form-group">
    <div class="col-sm-offset-5 col-sm-8">
        <INPUT TYPE=RESET VALUE="CLEAR" class="btn btn-default">
      <button type="button" class="btn btn-primary" value="submit" onclick="maketransfer()">Transfer</button>
    </div>
  </div>
<form>
</center>
</body>
</html>
