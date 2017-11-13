package com.cg.mp.service;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.cg.mp.dto.ArtistMasterDTO;
import com.cg.mp.dto.ComposerMasterDTO;
import com.cg.mp.dto.SongMasterDTO;
import com.cg.mp.exception.MediaException;

/**
 * File name: IMediaService
 * Package name: com.cg.mp.service
 * Description:	Consists of all the Method Declarations of those methods present in MediaServiceImpl.java 		
 * Version: 	1.0
 * Restrictions:	N/A
 * @author rauagarw,pratiksa,sayush,sapsaha
 * Date: 13/11/2017
 */

public interface IMediaService {
	List<ComposerMasterDTO> loadAllComposer() throws MediaException;

	String checkLogin(int username, String password) throws MediaException;

	ComposerMasterDTO insertComposer(ComposerMasterDTO composer) throws MediaException;

	List<SongMasterDTO> loadAllSongs()throws MediaException;

	void compSongAssoc(int composerId, int[] songIdList, int userId)throws MediaException;

	List<ArtistMasterDTO> loadAllArtists() throws MediaException;

	ArtistMasterDTO getArtistById(int artistId) throws MediaException;

	ArtistMasterDTO deleteArtist(int artistId)throws MediaException;

	ArtistMasterDTO insertArtist(ArtistMasterDTO artistMasterDTO)throws MediaException;

	ArtistMasterDTO updateArtist(ArtistMasterDTO artistMasterDTO)throws MediaException;

	ComposerMasterDTO getComposerById(int composerId) throws MediaException;

	ComposerMasterDTO updateComposer(ComposerMasterDTO composerMasterDTO) throws MediaException;

	void artistSongAssoc(int artistId, int[] songIdList, int userId) throws MediaException;

	List<SongMasterDTO> listAllSongsForComposer(int composerId) throws MediaException;

	List<SongMasterDTO> listAllSongsForArtist(int artistId) throws MediaException;

	ModelAndView checkPassword(String password, String cpassword) throws MediaException;

	List<SongMasterDTO> listAllSongs()throws MediaException;

	SongMasterDTO insertSong(SongMasterDTO songMasterDTO, int userId)throws MediaException;

	ComposerMasterDTO deleteComposer(int composerId)throws MediaException;
	
	SongMasterDTO deleteSong(int songId) throws MediaException;

}
