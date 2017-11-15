<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="../css/loginstyle.css">
</head>

<body>
	<form action="../login.obj" method="post">
		<table align="center">
			<tr>
				<td><input type="number" name="username" placeholder="User Id"
					class="input" required /></td>
			</tr>
			<tr>
				<td><input type="password" name="password"
					placeholder="Password" class="input" required /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Log in" class="login" required /><a
					href="../createAnAccount.obj"><button class="signup" type="button">Sign
							up</button></a></td>
			</tr>
		</table>
	</form>
</body>
</html>