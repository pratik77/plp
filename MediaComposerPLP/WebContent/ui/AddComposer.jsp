<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Composer</title>
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
				href="retrieveComposerSong.obj">Songs</a><br> <a
				href="retrieveComposerSong.obj">Composer Song Association</a><br>
			<a href="retrieveArtistSong.obj">Artist Song Association</a><br>
			<a href="retrieveComposerListForSongs.obj">Show songs composed by
				Composer</a><br> <a href="retrieveArtistListForSongs.obj">Show
				songs sung by Artist</a><br>
		</div>
		<div class="side-nav1">
	<h1>Enter Composer Details</h1>
	<form:form action="addComposer.obj" modelAttribute="composer" method="post">

		<form:label path="composerName">Composer Name:</form:label>
		<form:input path="composerName" required="true" />
		<br/>`

		<form:label path="composerBornDate">Composer Birth Date:</form:label>
		<form:input type="date" path="composerBornDate" required="true" />
		<br/>
		
		<form:label path="composerDiedDate">Composer Death Date:</form:label>
		<form:input type="date" path="composerDiedDate"  />
		<br/>
		
		<form:label path="composerCaeipiNumber">Composer CAEIPI Number:</form:label>
		<form:input path="composerCaeipiNumber" required="true" />
		<br/>
		
		<form:label path="composerMusicSocId">Composer Music Society ID:</form:label>
		<form:input path="composerMusicSocId" required="true" />
		<br/>
		
		<%-- <form:label path="traineeLocation">Trainee Location:</form:label>
		<form:radiobutton path="traineeLocation" value="Pune" />Pune
	    <form:radiobutton path="traineeLocation" value="Bangalore" />Bangalore
		<form:radiobutton path="traineeLocation" value="Chennai" />Chennai
		<form:radiobutton path="traineeLocation" value="Mumbai" />Mumbai
		<br />

		<form:label path="traineeDomain">Trainee Domain</form:label>
		<form:select path="traineeDomain">
			<form:option value="JEE">JEE</form:option>
			<form:option value=".NET">.NET</form:option>
			<form:option value="PHP">PHP</form:option>
			<form:option value="Spring">Spring</form:option>
		</form:select>
		<br /> --%>
		<input type="submit" value="Add Composer"/>
	</form:form>
	</div>
	</div>
</body>
</html>