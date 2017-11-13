<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modify Composer</title>
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
			<h1>Modify Composer Details</h1>
			<form:form action="insertModifiedComposer.obj"
				modelAttribute="composerMasterDTO" method="post">

				<form:input type="hidden" path="composerId"
					value="${composerMasterDTO.composerId}" />
				<br />`
		
		<form:label path="composerName">Composer Name:            </form:label>
				<form:input path="composerName"
					value="${composerMasterDTO.composerName}" required="true" />
				<br />`

		<form:label path="composerBornDate">Composer Birth Date:      </form:label>
				<form:input type="date" path="composerBornDate"
					value="${composerMasterDTO.composerBornDate}" required="true" />
				<br />

				<form:label path="composerDiedDate">Composer Death Date:      </form:label>
				<form:input type="date" path="composerDiedDate"
					value="${composerMasterDTO.composerDiedDate}" required="true" />
				<br />

				<form:label path="composerCaeipiNumber">Composer CAEIPI Number:   </form:label>
				<form:input path="composerCaeipiNumber"
					value="${composerMasterDTO.composerCaeipiNumber}" required="true" />
				<br />

				<form:label path="composerMusicSocId">Composer Music Society ID:</form:label>
				<form:input path="composerMusicSocId"
					value="${composerMasterDTO.composerMusicSocId}" required="true" />
				<br />

				<form:label path="createdOn">Created On:               </form:label>
				<form:input path="createdOn" value="${composerMasterDTO.createdOn}"
					readonly="true" required="true" />
				<br />

				<form:label path="createdBy">Created By:               </form:label>
				<form:input path="createdBy" value="${composerMasterDTO.createdBy}"
					readonly="true" required="true" />
				<br />

				<form:label path="updatedOn">Updated On:               </form:label>
				<form:input path="updatedOn" value="${composerMasterDTO.updatedOn}"
					readonly="true" required="true" />
				<br />

				<form:label path="updatedBy">Updated By:               </form:label>
				<form:input path="updatedBy" value="${composerMasterDTO.updatedBy}"
					readonly="true" required="true" />
				<br />

				<input type="submit" value="Modify Details" />
			</form:form>
		</div>
	</div>
</body>
</html>