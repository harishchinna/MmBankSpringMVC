<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AddNewAccountForm</title>
<style type="text/css">
.error {
	color: red;
}
</style>
</head>
<body>
	<h1 align="center">ADD NEW ACCOUNT FORM</h1>
	<spring:form action="createNewAccount" modelAttribute="account">

		<table align="center">
			<tr>
				<td><label>Account Holder Name:</label></td>
				<td><spring:input path="bankAccount.accountHolderName"/>
				<spring:errors path="bankAccount.accountHolderName" cssClass="error"/></td>
			</tr>
			<tr>
				<td><label>Initial Balance:</label></td>
				<td><spring:input type="number" path="bankAccount.accountBalance"/>
				<spring:errors path="bankAccount.accountBalance" cssClass="error"/></td>
				</td>
			</tr>
			<tr>
				<td><label>Salary Type:</label></td>
				<td><spring:radiobutton path="salary" value="Yes"/>YES</td>
				<td><spring:radiobutton  path="salary" value="No"/>NO</td>
				
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="SUBMIT"></td>
				<td><input type="reset" name="reset" value="RESET"></td>
			</tr>
		</table>
	</spring:form>
</body>
</html>