<%@page contentType="text/html" pageEncoding="UTF-8"%><!DOCTYPE html>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>

<%@page import="org.apache.struts2.ServletActionContext" %>
<%@page import="com.crypto.bo.User" %>


<html>
    <head>
            <link href="bootstrap.min.css" rel="stylesheet">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
<script type="text/javascript">

function logout(){
	
	document.cloudForm.action="logout.action";
	document.cloudForm.submit();
}

function transfer(){
	document.cloudForm.action="transfer.action";
	document.cloudForm.submit();
}

</script>
        
        
        
   </head>
    <body>
        
        <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="home.jsp">Home</a>
    </div>
    <ul class="nav navbar-nav">
    <li><a href="#" onclick="logout()">LOGOUT</a></li>
    <li><a href="#" onclick="transfer()">TRANSFER</a></li>
    <li><a href="aboutus.jsp">ABOUT US</a></li>
      
    </ul>
  </div>
</nav>
  <%
    User user=(User)ServletActionContext.getRequest().getSession().getAttribute("user");
  %>
  
    <center><br><br><h4></h4>
                    <div class="container">
                     
                        <div class="panel panel-default">
  
  <div class="panel-heading"><h4>Hi,<%=user.getName() %>   <b></b></h4></div>
  <%
  if(user.getAmount()!=null)
  	{
  %>
  
                    <table class="table table-bordered"> 
                        <thead>
                            <tr><th>Acc no.</th><th>Name</th><th>Amount</th><th>Address</th><th>Mobile No.</th><th>Email-id</th><th>Account Type</th></tr>
                        </thead>
                        <tbody>
                  
                            <tr>
                                <td><%=user.getId() %></td>
                                <td><%=user.getName() %></td>
                                <td><%=user.getAmount()%></td> 
                                <td><%=user.getAddress() %></td>
                                <td><%=user.getMobile() %></td>
                                <td><%=user.getEmail() %></td>
                                <td><%=user.getAccountType() %></td>

                            </tr>
                </tbody>
                    </table>
                        </div>
                            </div>
        

 <%}else { %> <center><b><br><br>
            <h4>No Data Found or Input Error</h4>
            </b></center><br><br></div>
        <center>
        <%} %>
            <s:form action="details.jsp" method="POST"> 
                &nbsp;
                <input type="submit" class="btn btn-primary  " value="Print" onClick="window.print()"/> <br>
                
            </s:form>
            <s:form name="cloudForm" id="cloudForm" theme="simple" method="post">
			</s:form>
            <br>
                
        </center>
        
    </center>
    </body>
</html>
