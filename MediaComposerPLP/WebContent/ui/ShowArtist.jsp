<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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

				<form:form method="post" modelAttribute="artistMasterDTO"
					action="modifyOrDeleteArtist.obj">
					<h1>Artist Details</h1>
					<table border="1" id="table">

						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Gender</th>
							<th>Born Date</th>
							<th>Died Date</th>
							<th>Created By</th>
							<th>Created On</th>
							<th>Updated By</th>
							<th>Updated On</th>
						</tr>

						<c:forEach items="${artistList}" var="artist">
							<tr>

								<td><form:radiobutton path="artistId"
										value="${artist.artistId }" required="true"/>${artist.artistId }</td>

								<td>${artist.artistName }</td>
								<td>${artist.artistType }</td>
								<td>${artist.artistBornDate }</td>
								<td>${artist.artistDiedDate }</td>
								<td>${artist.createdBy }</td>
								<td>${artist.createdOn }</td>
								<td>${artist.updatedBy }</td>
								<td>${artist.updatedOn }</td>

							</tr>

						</c:forEach>


					</table>
					<tr>

						<td><input type="submit" name="submit" value="modify" /> <input
							type="submit" name="submit" value="delete" /></td>
					</tr>
					<br>
${message}
</form:form>

			</table>
			<a href="insertArtist.obj">Add New Artist</a>

		</div>
	</div>
</body>
</html>