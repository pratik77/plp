<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Artist List</title>
<link rel="stylesheet" type="text/css" href="css/adminstyle.css">
</head>
<body>
	<div class="header">
		<h1>Hello Admin</h1>
		<a href="ui/login.jsp">Logout</a>
	</div>
	<div class="side-nav">
		<div class="side-nav2">
			<a href="retrieveAllComposer.obj">Composers</a><br> <a
				href="retrieveAllArtist.obj">Artists</a><br> <a
				href="retrieveSongs.obj">Songs</a><br> <a
				href="retrieveComposerSong.obj">Composer Song Association</a><br>
			<a href="retrieveArtistSong.obj">Artist Song Association</a><br>
			<a href="retrieveComposerListForSongs.obj">Show songs composed by
				Composer</a><br> <a href="retrieveArtistListForSongs.obj">Show
				songs sung by Artist</a><br>
		</div>
		<div class="side-nav2">
			<form name="form" method="post" action="listSongsForArtist.obj">
				<h1>Artist List</h1>
				<table border="1" id="table">
					<tr>
						<th>Artist Id</th>
						<th>Artist Name</th>
						<th>Artist Type</th>
						<th>Artist Born Date</th>
						<th>Artist Died Date</th>
					</tr>


					<c:forEach items="${artists}" var="artist">
						<tr>

							<td><input type="radio" name="artistSelect"
								value="${artist.artistId }" required />${artist.artistId}</td>
							<td>${artist.artistName }</td>
							<td>${artist.artistType }</td>
							<td>${artist.artistBornDate }</td>
							<td>${artist.artistDiedDate }</td>
						</tr>
					</c:forEach>
				</table>
				<br> <input type="submit" value="Show Songs"><br>
				Click on the radio button to select artist and submit to see song
				details composed by the artist

			</form>
		</div>
	</div>
</html>