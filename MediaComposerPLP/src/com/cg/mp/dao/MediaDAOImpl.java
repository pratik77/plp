package com.cg.mp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import com.cg.mp.dto.ArtistMasterDTO;
import com.cg.mp.dto.ArtistSongAssoc;
import com.cg.mp.dto.ComposerMasterDTO;
import com.cg.mp.dto.ComposerSongAssoc;
import com.cg.mp.dto.SongMasterDTO;
import com.cg.mp.dto.UserMasterDTO;
import com.cg.mp.exception.MediaException;

@Repository
@Transactional(rollbackOn = MediaException.class)
public class MediaDAOImpl implements IMediaDAO {

	@PersistenceContext
	private EntityManager entityManager;

	UserMasterDTO userMasterDTO = new UserMasterDTO();
	ComposerMasterDTO composerMasterDTO = new ComposerMasterDTO();

	@Override
	public int checkLogin(int username, String password) throws MediaException {

		TypedQuery<UserMasterDTO> query = entityManager
				.createQuery(
						"SELECT userMasterDTO FROM UserMasterDTO "
								+ "userMasterDTO WHERE userMasterDTO.userId=:puserId AND userMasterDTO.userPassword=:puserPassword",
						UserMasterDTO.class);
		query.setParameter("puserId", username);
		query.setParameter("puserPassword", password);
		List<UserMasterDTO> userMasterList;
		try {
			userMasterList = query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()+" and problems in checking login");
		}
		if (userMasterList.size() == 0)
			return 3;
		return userMasterList.get(0).getUserFlag();
	}

	@Override
	public List<ComposerMasterDTO> loadAllComposer() throws MediaException {
		TypedQuery<ComposerMasterDTO> query = entityManager.createQuery(
				"select c from ComposerMasterDTO c", ComposerMasterDTO.class);
		try {
			return query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()+" and problems in loading composers.");
		}
	}

	@Override
	public List<SongMasterDTO> loadAllSongs() throws MediaException {

		TypedQuery<SongMasterDTO> query = entityManager.createQuery(
				"select songs from SongMasterDTO songs", SongMasterDTO.class);
		try {
			return query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()+" and problems in loading songs.");
		}
	}

	@Override
	public ComposerMasterDTO insertComposer(ComposerMasterDTO composer) throws MediaException {
		try {
			entityManager.persist(composer);
			entityManager.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()+" and problems in inserting composers.");
		}
		return composer;
	}

	@Override
	public ComposerMasterDTO getComposerById(int composerId) throws MediaException {
		ComposerMasterDTO composer;
		try {
			composer = entityManager.find(
					ComposerMasterDTO.class, composerId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()+" and problems in getting composer by Id.");
		}
		return composer;
	}

	@Override
	public ComposerMasterDTO updateComposer(ComposerMasterDTO composerMasterDTO) throws MediaException {
		try {
			composerMasterDTO = entityManager.merge(composerMasterDTO);
			entityManager.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()+" and problems in updating composers.");
		}
		return composerMasterDTO;
	}

	public void compSongAssoc(ComposerSongAssoc composerSongAssoc) throws MediaException {

		try {
			entityManager.persist(composerSongAssoc);
			entityManager.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()+" and problems in associating composers to songs.");		}
	}

	



	

	@Override
	public List<ArtistMasterDTO> loadAllArtists() throws MediaException {

		TypedQuery<ArtistMasterDTO> query = entityManager.createQuery(
				"select a from ArtistMasterDTO a", ArtistMasterDTO.class);
		try {
			return query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()+" and problems in loading all artists.");
		}
	}

	@Override
	public ArtistMasterDTO getArtistById(int artistId) throws MediaException {
		ArtistMasterDTO artistMasterDTO = new ArtistMasterDTO();
		TypedQuery<ArtistMasterDTO> query = entityManager.createQuery(
				"select a from ArtistMasterDTO a where a.artistId=:partistId",
				ArtistMasterDTO.class);
		query.setParameter("partistId", artistId);
		try {
			artistMasterDTO = query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()+" and problems in getting artist by id .");
		}
		return artistMasterDTO;
	}

	@Override
	public ArtistMasterDTO deleteArtist(int artistId) throws MediaException {
		ArtistMasterDTO artistMasterDTO = entityManager.find(
				ArtistMasterDTO.class, artistId);
		try {
			entityManager.remove(artistMasterDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()+" and problems in deleting artist.");
		}
		return artistMasterDTO;
	}


	@Override
	public void artistSongAssoc(ArtistSongAssoc artistSongAssoc) throws MediaException {
		// TODO Auto-generated method stub
		System.out.println("h1");
		try {
			entityManager.persist(artistSongAssoc);
			System.out.println("h2");
			entityManager.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()+" and problems in associating songs to artist..");
		}
	}

	@Override
	public List<ComposerSongAssoc> getComposerSongs(int composerId) throws MediaException {
		// TODO Auto-generated method stub
		TypedQuery<ComposerSongAssoc> query = entityManager.createQuery(
				"select composerSong from ComposerSongAssoc composerSong  where composerSong.composerId=:pcomposerId",
				ComposerSongAssoc.class);
		query.setParameter("pcomposerId", composerId);
		try {
			return query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()+" and problems in loading composers and song list.");
		}
		
	}

	

	@Override
	public ArtistMasterDTO insertArtist(ArtistMasterDTO artistMasterDTO) {
		entityManager.persist(artistMasterDTO);
		entityManager.flush();
		return artistMasterDTO;
	}

	@Override
	public ArtistMasterDTO updateArtist(ArtistMasterDTO artistMasterDTO) {
		artistMasterDTO = entityManager.merge(artistMasterDTO);
		entityManager.flush();
		return artistMasterDTO;

	}

	@Override
	public SongMasterDTO listAllSongsForComposer(int songId) throws MediaException {
		// TODO Auto-generated method stub
		
		TypedQuery<SongMasterDTO> query = entityManager.createQuery(
				"select songMasterDTO from SongMasterDTO songMasterDTO where songMasterDTO.songId=:psongId",
				SongMasterDTO.class);
		query.setParameter("psongId", songId);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()+" and problems in loading songs for composer..");
		}

	}

	@Override
	public List<ArtistSongAssoc> getArtistSongs(int artistId) throws MediaException {
		// TODO Auto-generated method stub
		TypedQuery<ArtistSongAssoc> query = entityManager.createQuery(
				"select artistSong from ArtistSongAssoc artistSong  where artistSong.artistId=:partistId",
				ArtistSongAssoc.class);
		query.setParameter("partistId", artistId);
		try {
			return query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()+" and problems in getting songs for artist..");
		}
	}

	@Override
	public ModelAndView checkPassword(UserMasterDTO userMasterDTO) throws MediaException {
		// TODO Auto-generated method stub
		try {
			entityManager.persist(userMasterDTO);
			entityManager.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()+" and problems in matching password.");
		}
		return new ModelAndView("createSuccess","userMasterDTO",userMasterDTO);
	}


}
