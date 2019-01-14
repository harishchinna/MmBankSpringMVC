<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Form</title>
<style type="text/css">
table{
text-align: center;
}
</style>
</head>
<body>
	<h1 align="center">Update Form</h1>
	<form action="updateForm">
		<table>
			<tr>
				<td><label>Account Number:</label></td>
				<td><input type="number" name="accountNumber"></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="SUBMIT"></td>
				<td><input type="reset" name="reset" value="RESET"></td>
			</tr>
			</table>
			</form>
</body>
</html>