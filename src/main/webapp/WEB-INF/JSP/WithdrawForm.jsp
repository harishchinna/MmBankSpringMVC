<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Withdraw Form</title>
<style type="text/css">
table{
text-align: center;
}
</style>
</head>
<body>
<h1 align="center">Withdraw Form</h1>
<form action="withdrawamount">
<table>
<tr>
				<td><label>Account Number:</label></td>
				<td><input type="number" name="accountnumber"></td>
			</tr>
			<tr>
				<td><label>Amount To Withdraw:</label></td>
				<td><input type="number" name="amount"></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="SUBMIT"></td>
				<td><input type="reset" name="reset" value="RESET"></td>
			</tr>
			
</table>
</form>
</body>
</html>