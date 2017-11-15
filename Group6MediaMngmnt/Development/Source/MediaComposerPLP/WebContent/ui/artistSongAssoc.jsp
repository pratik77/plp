<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Artist Song Association</title>
<link rel="stylesheet" type="text/css" href="css/adminstyle.css">
<script type="text/javascript" src="javascript/artistSongAssoc.js"></script>

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
			<form name="form" method="post" action="artistSongAssoc.obj"
				onsubmit="return validateSongs(${songList.size()})">
				<h1>Artist List</h1>
				<table border="1" id="table">
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Gender</th>
						<th>Born Date</th>
						<th>Died Date</th>
					</tr>
					<c:forEach items="${artistList}" var="artist">
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
				<br> <br> <br>
				<h1>Songs List</h1>
				<table border="1" id="table">
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Duration</th>
					</tr>
					<c:forEach items="${songList}" var="song">

						<tr>

							<td><input type="checkbox" name="songSelect"
								value="${song.songId }" />${song.songId}</td>
							<td>${song.songName }</td>
							<td>${song.songDuration }</td>
						</tr>
					</c:forEach>
				</table>
				<br> <input type="submit" value="Associate"><br>

			</form>
		</div>
	</div>
</body>
</html>