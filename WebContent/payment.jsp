
<%@ page import="model.payments"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>payment Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Componets/jquery-3.2.1.min.js"></script>
<script src="Componets/payment.js"></script>
 


</head>
<body>
<h1>payment Management</h1>
   <form id="formItem" name="formItem" method="post" action="payment.jsp">
		 
		 payment ID:
		<input id="paymentid" name="paymentid" type="text"
		 class="form-control form-control-sm">
		<br> payment date:
		<input id="paymentdate" name="paymentdate" type="text"
		 class="form-control form-control-sm">
		<br> payment time:
		<input id="paymenttime" name="paymenttime" type="text"
		 class="form-control form-control-sm">
		<br> payment amount:
		<input id="amount" name="amount" type="text"
		 class="form-control form-control-sm">
		<br>
		<input id="btnSave" name="btnSave" type="button" value="Save"
		 class="btn btn-primary">
		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
  </form>

	<div id="alertSuccess" class="alert alert-success"></div>
    <div id="alertError" class="alert alert-danger"></div>
	
		<br> 
		<div id="divItemsGrid">
		 <%
		 
		 payments paymentObj = new payments();
		 out.print(paymentObj.readPayments());
		 
		 %>
		</div>

 
 
 
    </div>

</div>


<% out.print(session.getAttribute("statusMsg")); %>
</body>
</html>
