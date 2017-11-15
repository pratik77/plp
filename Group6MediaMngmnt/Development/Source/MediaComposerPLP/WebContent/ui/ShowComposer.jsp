<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Composer List</title>
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
			<table>

				<form:form method="post" modelAttribute="composerMasterDTO"
					action="modifyOrDelete.obj">
					<h1>Composer Details</h1>
					<table border="1" id="table">

						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Born Date</th>
							<th>Died Date</th>
							<th>Cae/ipi</th>
							<th>Soceity Id</th>
							<th>Created By</th>
							<th>Updated By</th>
							<th>Updated On</th>

						</tr>
						<c:forEach items="${composerList}" var="composer">
							<tr>

								<td><form:radiobutton path="composerId"
										value="${composer.composerId }" required="true" />${composer.composerId}</td>
								<td>${composer.composerName }</td>
								<td>${composer.composerBornDate }</td>
								<td>${composer.composerDiedDate }</td>
								<td>${composer.composerCaeipiNumber }</td>
								<td>${composer.composerMusicSocId }</td>
								<td>${composer.createdBy }</td>
								<td>${composer.updatedBy }</td>
								<td>${composer.updatedOn }</td>
							</tr>

						</c:forEach>
					</table>
					<input type="submit" name="submit" value="modify" />
					<input type="submit" name="submit" value="delete" />
				</form:form>

			</table>
			<br> <a href="insertComposer.obj">Add New Composer</a> <br>${message}
		</div>
	</div>
</body>
</html>