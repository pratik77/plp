<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Song List</title>
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

				<form:form method="post" modelAttribute="songMasterDTO"
					action="deleteSong.obj">
					<h1>Songs Details</h1>
					<table border="1" id="table">

						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Duration</th>
							<th>Created by</th>
							<th>Created On</th>
							<th>Updated By</th>
							<th>Updated On</th>

						</tr>
						<c:forEach items="${songList}" var="song">
							<tr>

								<td><form:radiobutton path="songId" required="true"
										value="${song.songId }" />${song.songId}</td>
								<td>${song.songName }</td>
								<td>${song.songDuration }</td>
								<td>${song.createdBy }</td>
								<td>${song.createdOn }</td>
								<td>${song.updatedBy }</td>
								<td>${song.updatedOn }</td>
							</tr>

						</c:forEach>
						<!-- <tr>
							<td colspan="2"><input type="submit" name="submit"
								value="modify" /></td>
							<td colspan="2"><input type="submit" name="submit"
								value="delete" /></td>
						</tr> -->
					</table>
					<input type="submit" name="submit" value="delete" />
				</form:form>

			</table>
			<br> <a href="insertSongs.obj">Add New Songs</a><br>${message}
		</div>
	</div>
</body>
</html>