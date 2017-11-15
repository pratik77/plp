<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modify Composer</title>
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
			<h1>Modify Composer Details</h1>
			<form:form name="form" action="insertModifiedComposer.obj"
				modelAttribute="composerMasterDTO" method="post"
				onsubmit="return validateDiedDate()">

				<form:input type="hidden" path="composerId"
					value="${composerMasterDTO.composerId}" />
				<br />`
		
		<form:label path="composerName">Composer Name:            </form:label>
				<form:input path="composerName"
					value="${composerMasterDTO.composerName}"
					pattern="[A-Za-z]{1}[A-Za-z ]{0,}"
					title="Name should contain alphabets only" required="true" />
				<br />`

		<form:label path="composerBornDate">Composer Birth Date:      </form:label>
				<form:input type="date" path="composerBornDate"
					name="composerBornDate"
					value="${composerMasterDTO.composerBornDate}" max="${sqlDate}"
					required="true" />
				<br />

				<form:label path="composerDiedDate">Composer Death Date:      </form:label>
				<form:input type="date" path="composerDiedDate"
					name="composerDiedDate"
					value="${composerMasterDTO.composerDiedDate}" max="${sqlDate}" />
				<br />

				<form:label path="composerCaeipiNumber">Composer CAEIPI Number:   </form:label>
				<form:input path="composerCaeipiNumber"
					value="${composerMasterDTO.composerCaeipiNumber}"
					pattern="[1-9]{1}[0-9]{6,9}"
					title="CAEIPI Number should be 7 to 10 digits" required="true" />
				<br />

				<form:label path="composerMusicSocId">Composer Music Society ID:</form:label>
				<form:input path="composerMusicSocId"
					value="${composerMasterDTO.composerMusicSocId}"
					pattern="[1-9]{1}[0-9]{2}"
					title="Music Society Id should be a 3 Digit Number" required="true" />
				<br />

				<input type="submit" value="Modify Details" />
			</form:form>
		</div>
	</div>
</body>
</html>