<%@ page isELIgnored="false" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="spring"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<spring:form action="updateAccountDetails" modelAttribute="account">
		<h1>UPDATE AN ACCOUNT</h1>
		<label>Account Number : <br> <spring:input
				path="bankAccount.accountNumber" readonly="readonly" /></label>
		<br>
		<br>
		<label>Name :<br> <spring:input
				path="bankAccount.accountHolderName" /></label>
		<br>
		<br>
		<label>AccountBalance :<br> <spring:input
				path="bankAccount.accountBalance" readonly="readonly" /></label>
		<br>
		<br>
		<label>Salaried :</label>
		<label><spring:radiobutton path="salary" value="${account.salary}"/>YES</label>
		<label><spring:radiobutton path="salary" value="${account.salary}" />NO</label>
		<br>
		<br>
		<label><input type="submit" name="submit" value="Submit"></label>
		<label><input type="reset" name="reset" value="Reset"></label>


	</spring:form>
</body>
</html>