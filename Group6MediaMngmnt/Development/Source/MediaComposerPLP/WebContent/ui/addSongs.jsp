<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Songs</title>
<link rel="stylesheet" type="text/css" href="css/adminstyle.css">
</head>
<body>
	<div class="header">
		<h1>Hello Admin</h1>
		<a href="ui/login.jsp">Logout</a>
	</div>
	<div class="side-nav">
		<div class="side-nav1">
			<a href="retrieveAllComposer.obj">Composers</a><br> <a
				href="retrieveAllArtist.obj">Artists</a><br> <a
				href="retrieveSongs.obj">Songs</a><br> <a
				href="retrieveComposerSong.obj">Composer Song Association</a><br>
			<a href="retrieveArtistSong.obj">Artist Song Association</a><br>
			<a href="retrieveComposerListForSongs.obj">Show songs composed by
				Composer</a><br> <a href="retrieveArtistListForSongs.obj">Show
				songs sung by Artist</a><br>
		</div>
		<div class="side-nav1">
			<h1>Enter Song Details</h1>
			<form:form action="addSongs.obj" modelAttribute="songMasterDTO"
				method="post">

				<form:label path="songName">Song Name:</form:label>
				<form:input path="songName" required="true"
					pattern="[A-Za-z0-9]{1}[A-Za-z0-9 ]{0,}" />
				<br />

				<form:label path="songDuration">Song Duration:</form:label>
				<form:input type="text" path="songDuration" required="true"
					placeholder="hh:mm:ss" pattern="[0-9]{2}[:][0-5][0-9][:][0-5][0-9]" />
				<br />

				<input type="submit" value="Add Song" />
			</form:form>
		</div>
	</div>
</body>
</html>