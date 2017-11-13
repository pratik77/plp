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

	@Override
	public List<ComposerMasterDTO> loadAllComposer() throws MediaException {
		return mediaDAO.loadAllComposer();
	}

	@Override
	public ComposerMasterDTO insertComposer(ComposerMasterDTO composer) throws MediaException {

		return mediaDAO.insertComposer(composer);
	}

	@Override
	public List<SongMasterDTO> loadAllSongs() throws MediaException {

		return mediaDAO.loadAllSongs();

	}

	@Override
	public ComposerMasterDTO getComposerById(int composerId) throws MediaException {
		return mediaDAO.getComposerById(composerId);
	}

	@Override
	public ComposerMasterDTO updateComposer(ComposerMasterDTO composerMasterDTO) throws MediaException {
		return mediaDAO.updateComposer(composerMasterDTO);
	}

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

	public List<ArtistMasterDTO> loadAllArtists() throws MediaException {

		return mediaDAO.loadAllArtists();

	}

	@Override
	public ArtistMasterDTO getArtistById(int artistId) throws MediaException {

		return mediaDAO.getArtistById(artistId);
	}

	@Override
	public ArtistMasterDTO deleteArtist(int artistId) throws MediaException {

		return mediaDAO.deleteArtist(artistId);
	}

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

	@Override

	public ArtistMasterDTO insertArtist(ArtistMasterDTO artistMasterDTO) throws MediaException {
		
		return mediaDAO.insertArtist(artistMasterDTO);
	}

	@Override
	public ArtistMasterDTO updateArtist(ArtistMasterDTO artistMasterDTO) throws MediaException {
		
		return mediaDAO.updateArtist(artistMasterDTO);
	}


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
