<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Songs Associated with composerId: ${composerId }</h2>
	<br>
	<table>
		<tr>
			<th>SongId</th>
			<th>Song Name</th>
			<th>Song Duration</th>
		</tr>
		<c:forEach items="${songList }" var="song">
			<tr>
				<td>${song.songId}</td>
				<td>${song.songName}</td>
				<td>${song.songDuration}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
<a href="ui/admin.jsp">Return to admin home page</a><br><br>
<a href="ui/login.jsp">Logout</a>
</body>
</html>