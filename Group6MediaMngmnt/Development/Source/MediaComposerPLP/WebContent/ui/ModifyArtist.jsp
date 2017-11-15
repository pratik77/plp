<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modify Artist</title>
<link rel="stylesheet" type="text/css" href="css/adminstyle.css">
<script type="text/javascript" src="javascript/DiedDate.js"></script>
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
			<h1>Modify Artist Details</h1>
			<form:form name="form" action="insertModifiedArtist.obj"
				modelAttribute="artistMasterDTO" method="post"
				onsubmit="return validateArtistDiedDate()">

				<form:input type="hidden" path="artistId"
					value="${artistMasterDTO.artistId}" />
				<br />

				<form:label path="artistName">Artist Name:</form:label>
				<form:input path="artistName" value="${artistMasterDTO.artistName}"
					pattern="[A-Za-z]{1}[A-Za-z ]{0,}"
					title="Name should contain alphabets only" required="true" />
				<br />

				<form:label path="artistBornDate">Artist Birth Date:</form:label>
				<form:input type="date" path="artistBornDate"
					value="${artistMasterDTO.artistBornDate}" max="${sqlDate}"
					required="true" />
				<br />

				<form:label path="artistDiedDate">Artist Death Date:</form:label>
				<form:input type="date" path="artistDiedDate"
					value="${artistMasterDTO.artistDiedDate}" max="${sqlDate}"
					required="true" />
				<br />

				<form:label path="artistType">Artist Type:</form:label>
				<form:radiobutton path="artistType" value="M" required="true" />Male
				<form:radiobutton path="artistType" value="F" />Female
					
				<br />
				<br>

				<%-- <form:label path="createdOn">Created On:</form:label>
				<form:input path="createdOn" value="${artistMasterDTO.createdOn}"
					readonly="true" required="true" />
				<br />

				<form:label path="createdBy">Created By:</form:label>
				<form:input path="createdBy" value="${artistMasterDTO.createdBy}"
					readonly="true" required="true" />
				<br />

				<form:label path="updatedOn">Created By:</form:label>
				<form:input path="updatedOn" value="${artistMasterDTO.updatedOn}"
					readonly="true" required="true" />
				<br />

				<form:label path="updatedBy">Created By:</form:label>
				<form:input path="updatedBy" value="${artistMasterDTO.updatedBy}"
					readonly="true" required="true" />
				<br /> --%>

				<input type="submit" value="Modify Details" />
			</form:form>
		</div>
	</div>
</body>
</html>