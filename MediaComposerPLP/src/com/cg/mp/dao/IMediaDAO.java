package com.cg.mp.dao;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.cg.mp.dto.ArtistMasterDTO;
import com.cg.mp.dto.ArtistSongAssoc;
import com.cg.mp.dto.ComposerMasterDTO;
import com.cg.mp.dto.ComposerSongAssoc;
import com.cg.mp.dto.SongMasterDTO;
import com.cg.mp.dto.UserMasterDTO;
import com.cg.mp.exception.MediaException;

public interface IMediaDAO {

	List<ComposerMasterDTO> loadAllComposer() throws MediaException;

	List<ArtistMasterDTO> loadAllArtists() throws MediaException;

	ArtistMasterDTO getArtistById(int artistId) throws MediaException;

	ArtistMasterDTO deleteArtist(int artistId) throws MediaException;

	ArtistMasterDTO insertArtist(ArtistMasterDTO artistMasterDTO) throws MediaException;

	ArtistMasterDTO updateArtist(ArtistMasterDTO artistMasterDTO) throws MediaException;

	int checkLogin(int username, String password) throws MediaException;

	ComposerMasterDTO insertComposer(ComposerMasterDTO composer) throws MediaException;

	List<SongMasterDTO> loadAllSongs() throws MediaException;

	ComposerMasterDTO getComposerById(int composerId) throws MediaException;

	ComposerMasterDTO updateComposer(ComposerMasterDTO composerMasterDTO) throws MediaException;

	void compSongAssoc(ComposerSongAssoc composerSongAssoc) throws MediaException;

	void artistSongAssoc(ArtistSongAssoc artistSongAssoc) throws MediaException;

	List<ComposerSongAssoc> getComposerSongs(int composerId) throws MediaException;

	SongMasterDTO listAllSongsForComposer(int songId) throws MediaException;

	List<ArtistSongAssoc> getArtistSongs(int artistId) throws MediaException;

	ModelAndView checkPassword(UserMasterDTO userMasterDTO) throws MediaException;

	

}
