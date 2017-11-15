<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
<link rel="stylesheet" type="text/css" href="css/loginstyle.css">
</head>
<body>

	<form action="accountCreation.obj" method="post">
		<table align="center">
			<tr>
				
				<td><input type="password" name="password"
					pattern="[A-Za-z0-9._@]{1,15}"
					title="Password should contain alphanumeric . @ _ only" placeholder="Set Password"
					class="input" required></td>
			</tr>
			<tr>
				
				<td><input type="password" name="cpassword"
					pattern="[A-Za-z0-9._@]{1,15}"
					title="Password should contain alphanumeric . @ _ only" placeholder="Confirm Password"
					class="input" required></td>
			</tr>
			<tr>
				<td><input type="submit" value="Create account" class="login1"></td>
			</tr>
		</table>
		<br> <font color="red">${message}</font>
	</form>

	<font id="outPopUp" color="red">${message}</font>
</body>
</html>