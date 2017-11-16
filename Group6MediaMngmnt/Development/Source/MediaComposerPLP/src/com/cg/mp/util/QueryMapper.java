package com.cg.mp.util;

public interface QueryMapper {
	public static final String Query1 = "SELECT userMasterDTO FROM UserMasterDTO "
			+ "userMasterDTO WHERE userMasterDTO.userId=:puserId AND userMasterDTO.userPassword=:puserPassword";
	public static final String Query2 = "select c from ComposerMasterDTO c";
	public static final String Query3 = "select songs from SongMasterDTO songs";
	public static final String Query4 = "select a from ArtistMasterDTO a";
	public static final String Query5 = "select a from ArtistMasterDTO a where a.artistId=:partistId";
	public static final String Query6 = "select composerSong from ComposerSongAssoc composerSong  where composerSong.composerId=:pcomposerId";
	public static final String Query7 = "select songMasterDTO from SongMasterDTO songMasterDTO where songMasterDTO.songId=:psongId";
	public static final String Query8 = "select artistSong from ArtistSongAssoc artistSong  where artistSong.artistId=:partistId";
	public static final String Query9 = "select songMasterDTO from SongMasterDTO songMasterDTO ";
}
