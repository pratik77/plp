package com.cg.mp.util;

public interface QueryMapper {
	public static final String query1 = "SELECT userMasterDTO FROM UserMasterDTO "
			+ "userMasterDTO WHERE userMasterDTO.userId=:puserId AND userMasterDTO.userPassword=:puserPassword";
	public static final String query2 = "select c from ComposerMasterDTO c";
	public static final String query3 = "select songs from SongMasterDTO songs";
	public static final String query4 = "select a from ArtistMasterDTO a";
	public static final String query5 = "select a from ArtistMasterDTO a where a.artistId=:partistId";
	public static final String query6 = "select composerSong from ComposerSongAssoc composerSong  where composerSong.composerId=:pcomposerId";
	public static final String query7 = "select songMasterDTO from SongMasterDTO songMasterDTO where songMasterDTO.songId=:psongId";
	public static final String query8 = "select artistSong from ArtistSongAssoc artistSong  where artistSong.artistId=:partistId";
	public static final String query9 = "select songMasterDTO from SongMasterDTO songMasterDTO ";
}
