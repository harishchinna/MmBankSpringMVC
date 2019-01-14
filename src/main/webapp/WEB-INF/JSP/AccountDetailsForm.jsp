<%@ page isELIgnored="false" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
<tr>
			<th>Account Number</th>
			<th><a href="sortByName">Holder Name</a></th>
			<th><a href="sortByBalance">Account Balance</a></th>
			<th><a href="sortBySalaryType">Salary</a></th>
			<th>Credit Limit</th>
			<th>Account Type</th>
		
		</tr>
			<jstl:forEach var="account" items="${account}">
				<tr>
					<td>${account.bankAccount.accountNumber}</td>
					<td>${account.bankAccount.accountHolderName }</td>
					<td>${account.bankAccount.accountBalance}</td>
					<td>${account.salary==true?"Yes":"No"}</td>
					<td>${"N/A"}</td>
					<td>${"Savings"}</td>
				</tr>
			</jstl:forEach>
</table>
</body>
</html>