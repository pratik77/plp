package com.cg.mp.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.cg.mp.dao.IMediaDAO;
import com.cg.mp.dto.ArtistMasterDTO;
import com.cg.mp.dto.ArtistSongAssoc;
import com.cg.mp.dto.ComposerMasterDTO;
import com.cg.mp.dto.ComposerSongAssoc;
import com.cg.mp.dto.SongMasterDTO;
import com.cg.mp.dto.UserMasterDTO;
import com.cg.mp.exception.MediaException;

/**
 * File name: MediaService
 * Package name: com.cg.mp.service
 * Description:	Implementation class of service layer which interacts with the data access layer and performs the appropriate operations.
 * Version: 	1.0
 * Restrictions:	N/A 
 * @author rauagarw,pratiksa,sayush,sapsaha
 * Date: 13/11/2017
 */

@Component("service")
public class MediaService implements IMediaService {

	@Autowired
	IMediaDAO mediaDAO;

	int userFlag;
	ComposerSongAssoc composerSongAssoc = new ComposerSongAssoc();
	ArtistSongAssoc artistSongAssoc = new ArtistSongAssoc();
	SongMasterDTO songMasterDTO = new SongMasterDTO();
	UserMasterDTO userMasterDTO=new UserMasterDTO();
	List<ComposerSongAssoc> composerSongs = new ArrayList();
	List<ArtistSongAssoc> artistSongs = new ArrayList();
	List<SongMasterDTO> songs = new ArrayList();

	/**
	 * Method Name:	checkLogin
	 * Description:	Calls the checkLogin method of the data access layer and returns the result to the MediaController.
	 * Return Type:	String
	 * @param:	int username, String password
	 */
	
	@Override
	public String checkLogin(int username, String password) throws MediaException {
		// TODO Auto-generated method stub
		userFlag = mediaDAO.checkLogin(username, password);
		if (userFlag == 1)
			return "admin";
		else if (userFlag == 2)
			return "user";
		else
			return "../login1";

	}
	
	/**
	 * Method Name:	loadAllComposer
	 * Description:	Calls the loadAllComposer method of the data access layer and returns the result to the MediaController.
	 * Return Type:	List
	 */

	@Override
	public List<ComposerMasterDTO> loadAllComposer() throws MediaException {
		return mediaDAO.loadAllComposer();
	}

	/**
	 * Method Name:	insertComposer
	 * Description:	Calls the insertComposer method of the data access layer and returns the result to the MediaController.
	 * Return Type:	ComposerMasterDTO
	 * @param:	ComposerMasterDTO composer
	 */
	
	@Override
	public ComposerMasterDTO insertComposer(ComposerMasterDTO composer) throws MediaException {

		return mediaDAO.insertComposer(composer);
	}
	
	/**
	 * Method Name:	loadAllSongs
	 * Description:	Calls the loadAllSongs method of the data access layer and returns the result to the MediaController.
	 * Return Type:	List
	 */

	@Override
	public List<SongMasterDTO> loadAllSongs() throws MediaException {

		return mediaDAO.loadAllSongs();

	}
	
	/**
	 * Method Name:	getComposerById
	 * Description:	Calls the getComposerById method of the data access layer and returns the result to the MediaController.
	 * Return Type:	ComposerMasterDTO
	 * @param:	int composerId
	 */

	@Override
	public ComposerMasterDTO getComposerById(int composerId) throws MediaException {
		return mediaDAO.getComposerById(composerId);
	}

	/**
	 * Method Name:	updateComposer
	 * Description:	Calls the updateComposer method of the data access layer and returns the result to the MediaController.
	 * Return Type:	ComposerMasterDTO
	 * @param:	ComposerMasterDTO composerMasterDTO
	 */
	
	@Override
	public ComposerMasterDTO updateComposer(ComposerMasterDTO composerMasterDTO) throws MediaException {
		return mediaDAO.updateComposer(composerMasterDTO);
	}

	/**
	 * Method Name:	compSongAssoc
	 * Description:	Calls the compSongAssoc method of the data access layer and returns the result to the MediaController.
	 * Return Type:	void
	 * @param:	int composerId, int[] songIdList, int userId
	 */
	
	@Override
	public void compSongAssoc(int composerId, int[] songIdList, int userId) throws MediaException {
		// TODO Auto-generated method stub
		for (int songId : songIdList) {
			composerSongAssoc.setComposerId(composerId);
			composerSongAssoc.setSongId(songId);
			composerSongAssoc.setCreatedBy(userId);
			composerSongAssoc.setCreatedOn(Date.valueOf(LocalDate.now()));
			composerSongAssoc.setUpdatedBy(userId);
			composerSongAssoc.setUpdatedOn(Date.valueOf(LocalDate.now()));
			mediaDAO.compSongAssoc(composerSongAssoc);
		}
	}
	
	/**
	 * Method Name:	loadAllArtists
	 * Description:	Calls the loadAllArtists method of the data access layer and returns the result to the MediaController.
	 * Return Type:	List
	 */
	
	@Override
	public List<ArtistMasterDTO> loadAllArtists() throws MediaException {

		return mediaDAO.loadAllArtists();

	}
	
	/**
	 * Method Name:	getArtistById
	 * Description:	Calls the getArtistById method of the data access layer and returns the result to the MediaController.
	 * Return Type:	ArtistMasterDTO
	 * @param:	int artistId
	 */

	@Override
	public ArtistMasterDTO getArtistById(int artistId) throws MediaException {

		return mediaDAO.getArtistById(artistId);
	}

	/**
	 * Method Name:	deleteArtist
	 * Description:	Calls the deleteArtist method of the data access layer and returns the result to the MediaController.
	 * Return Type:	ArtistMasterDTO
	 * @param:	int artistId
	 */
	
	@Override
	public ArtistMasterDTO deleteArtist(int artistId) throws MediaException {

		return mediaDAO.deleteArtist(artistId);
	}

	/**
	 * Method Name:	artistSongAssoc
	 * Description:	Calls the artistSongAssoc method of the data access layer and returns the result to the MediaController.
	 * Return Type:	void
	 * @param:	int artistId, int[] songIdList, int userId
	 */
	
	@Override
	public void artistSongAssoc(int artistId, int[] songIdList, int userId) throws MediaException {
		// TODO Auto-generated method stub

		for (int songId : songIdList) {
			artistSongAssoc.setArtistId(artistId);
			artistSongAssoc.setSongId(songId);
			artistSongAssoc.setCreatedBy(userId);
			artistSongAssoc.setCreatedOn(Date.valueOf(LocalDate.now()));
			artistSongAssoc.setUpdatedBy(userId);
			artistSongAssoc.setUpdatedOn(Date.valueOf(LocalDate.now()));
			mediaDAO.artistSongAssoc(artistSongAssoc);
		}

	}
	
	/**
	 * Method Name:	listAllSongsForComposer
	 * Description:	Calls the listAllSongsForComposer method of the data access layer and returns the result to the MediaController.
	 * Return Type:	List
	 * @param:	int composerId
	 */

	@Override
	public List<SongMasterDTO> listAllSongsForComposer(int composerId) throws MediaException {
		// TODO Auto-generated method stub
		composerSongs = mediaDAO.getComposerSongs(composerId);
		List<SongMasterDTO> songs = new ArrayList();
		for (ComposerSongAssoc composerSongAssoc : composerSongs) {
			SongMasterDTO songMaster = new SongMasterDTO();
			songMaster = mediaDAO.listAllSongsForComposer(composerSongAssoc
					.getSongId());
			songs.add(songMaster);

		}
		return songs;

	}
	
	/**
	 * Method Name:	insertArtist
	 * Description:	Calls the insertArtist method of the data access layer and returns the result to the MediaController.
	 * Return Type:	ArtistMasterDTO
	 * @param:	ArtistMasterDTO artistMasterDTO
	 */

	@Override

	public ArtistMasterDTO insertArtist(ArtistMasterDTO artistMasterDTO) throws MediaException {
		
		return mediaDAO.insertArtist(artistMasterDTO);
	}
	
	/**
	 * Method Name:	updateArtist
	 * Description:	Calls the updateArtist method of the data access layer and returns the result to the MediaController.
	 * Return Type:	ArtistMasterDTO
	 * @param:	ArtistMasterDTO artistMasterDTO
	 */

	@Override
	public ArtistMasterDTO updateArtist(ArtistMasterDTO artistMasterDTO) throws MediaException {
		
		return mediaDAO.updateArtist(artistMasterDTO);
	}

	/**
	 * Method Name:	listAllSongsForArtist
	 * Description:	Calls the listAllSongsForArtist method of the data access layer and returns the result to the MediaController.
	 * Return Type:	List
	 * @param:	int artistId
	 */

	@Override
	public List<SongMasterDTO> listAllSongsForArtist(int artistId) throws MediaException {
		// TODO Auto-generated method stub
		artistSongs = mediaDAO.getArtistSongs(artistId);
		List<SongMasterDTO> songs = new ArrayList();
		for (ArtistSongAssoc artistSongAssoc : artistSongs) {
			SongMasterDTO songMaster = new SongMasterDTO();
			songMaster = mediaDAO.listAllSongsForComposer(artistSongAssoc
					.getSongId());
			songs.add(songMaster);

		}
		return songs;
	}
	
	/**
	 * Method Name:	checkPassword
	 * Description:	Calls the checkPassword method of the data access layer and returns the result to the MediaController.
	 * Return Type:	ModelAndView
	 * @param:	String password, String cpassword
	 */

	@Override
	public ModelAndView checkPassword(String password, String cpassword) throws MediaException {
		// TODO Auto-generated method stub
		if(password.equals(cpassword))
		{
			userMasterDTO.setUserPassword(password);
			userMasterDTO.setCreatedBy(100001);
			userMasterDTO.setCreatedOn(Date.valueOf(LocalDate.now()));
			userMasterDTO.setUpdatedBy(100001);
			userMasterDTO.setUpdatedOn(Date.valueOf(LocalDate.now()));
			return mediaDAO.checkPassword(userMasterDTO);

		
		}
		else
			return new ModelAndView("createAnAccount","message","password does not match");
	}

	@Override
	public List<SongMasterDTO> listAllSongs() throws MediaException {
		// TODO Auto-generated method stub
		songs=mediaDAO.listAllSongs();
		return songs;
	}

	@Override
	public SongMasterDTO insertSong(SongMasterDTO songMasterDTO,int userId)
			throws MediaException {
		// TODO Auto-generated method stub
		songMasterDTO.setCreatedBy(userId);
		songMasterDTO.setCreatedOn(Date.valueOf(LocalDate.now()));
		songMasterDTO.setUpdatedBy(userId);
		songMasterDTO.setUpdatedOn(Date.valueOf(LocalDate.now()));
		return mediaDAO.insertSong(songMasterDTO);
	}
	@Override
	public ComposerMasterDTO deleteComposer(int composerId) throws MediaException {
		return mediaDAO.deleteComposer(composerId);
	}

	@Override
	public SongMasterDTO deleteSong(int songId) throws MediaException {
		try {
			return mediaDAO.deleteSong(songId);
		} catch (Exception e) {
			throw new MediaException(e.getMessage() + "Problem while deleting song");
		}
	}


}
