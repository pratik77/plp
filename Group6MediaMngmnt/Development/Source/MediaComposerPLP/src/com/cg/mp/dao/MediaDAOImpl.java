package com.cg.mp.dao;

import java.util.ArrayList;
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
import com.cg.mp.util.QueryMapper;

/**
 * File name: MediaDAOImpl Package name: com.cg.mp.dao Description:
 * Implementation class of data access layer which interacts with the database
 * and performs the appropriate operations. Version: 1.0 Restrictions: N/A
 * 
 * @author rauagarw,pratiksa,sayush,sapsaha Date: 13/11/2017
 */

@Repository
@Transactional(rollbackOn = MediaException.class)
public class MediaDAOImpl implements MediaDAO {
	@PersistenceContext
	private EntityManager entityManager;
	UserMasterDTO userMasterDTO = new UserMasterDTO();
	ComposerMasterDTO composerMasterDTO = new ComposerMasterDTO();
	List<SongMasterDTO> temp = new ArrayList();

	/**
	 * Method Name: checkLogin Description: Checks for the login credentials for
	 * a given username and password from the database and returns the data to
	 * the service layer. Return Type: int
	 * 
	 * @param: int username
	 * @param: String password
	 */
	@Override
	public int checkLogin(int username, String password) throws MediaException {
		TypedQuery<UserMasterDTO> query = entityManager.createQuery(
				QueryMapper.query1, UserMasterDTO.class);
		query.setParameter("puserId", username);
		query.setParameter("puserPassword", password);
		List<UserMasterDTO> users;
		try {
			users = query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()
					+ " and problems in checking login");
		}
		if (users.size() == 0)
			return 3;
		return users.get(0).getUserFlag();
	}

	/**
	 * Method Name: loadAllComposer() Description: Retrieves details of all the
	 * composers from the database and returns the data to the service layer.
	 * Return Type: List
	 */
	@Override
	public List<ComposerMasterDTO> loadAllComposer() throws MediaException {
		TypedQuery<ComposerMasterDTO> query = entityManager.createQuery(
				QueryMapper.query2, ComposerMasterDTO.class);
		try {
			return query.getResultList();
		} catch (Exception e) {
			throw new MediaException(e.getMessage()
					+ " and problems in loading composers.");
		}
	}

	/**
	 * Method Name: loadAllSongs() Description: Retrieves details of all the
	 * songs from the database and returns the data to the service layer. Return
	 * Type: List
	 */
	@Override
	public List<SongMasterDTO> loadAllSongs() throws MediaException {
		TypedQuery<SongMasterDTO> query = entityManager.createQuery(
				QueryMapper.query3, SongMasterDTO.class);
		try {
			return query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()
					+ " and problems in loading songs.");
		}
	}

	/**
	 * Method Name: insertComposer Description: Adds the Composer and its
	 * details into the database and returns the result to the service layer.
	 * Return Type: ComposerMasterDTO
	 * 
	 * @param: ComposerMasterDTO composer
	 */
	@Override
	public ComposerMasterDTO insertComposer(ComposerMasterDTO composer)
			throws MediaException {
		try {
			entityManager.persist(composer);
			entityManager.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()
					+ " and problems in inserting composers.");
		}
		return composer;
	}

	/**
	 * Method Name: getComposerById Description: Retrieves a Composer and its
	 * details for the given composer id into the database and returns the
	 * result to the service layer. Return Type: ComposerMasterDTO
	 * 
	 * @param: int composerId
	 */
	@Override
	public ComposerMasterDTO getComposerById(int composerId)
			throws MediaException {
		ComposerMasterDTO composer;
		try {
			composer = entityManager.find(ComposerMasterDTO.class, composerId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()
					+ " and problems in getting composer by Id.");
		}
		return composer;
	}

	/**
	 * Method Name: updateComposer Description: Performs update operation by
	 * updating the details of a composer in the database and returns the result
	 * to the service layer. Return Type: ComposerMasterDTO
	 * 
	 * @param: ComposerMasterDTO composerMasterDTO
	 */
	@Override
	public ComposerMasterDTO updateComposer(ComposerMasterDTO composerMasterDTO)
			throws MediaException {
		try {
			composerMasterDTO = entityManager.merge(composerMasterDTO);
			entityManager.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()
					+ " and problems in updating composers.");
		}
		return composerMasterDTO;
	}

	public void compSongAssoc(ComposerSongAssoc composerSongAssoc)
			throws MediaException {

		try {
			entityManager.persist(composerSongAssoc);
			entityManager.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()
					+ " and problems in associating composers to songs.");
		}
	}

	/**
	 * Method Name: loadAllArtists() Description: Retrieves details of all the
	 * artists from the database and returns the data to the service layer.
	 * Return Type: List
	 */
	@Override
	public List<ArtistMasterDTO> loadAllArtists() throws MediaException {
		TypedQuery<ArtistMasterDTO> query = entityManager.createQuery(
				QueryMapper.query4, ArtistMasterDTO.class);
		try {
			return query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()
					+ " and problems in loading all artists.");
		}
	}

	/**
	 * Method Name: getArtistById Description: Retrieves an artist and its
	 * details for the given artist id from the database and returns the result
	 * to the service layer. Return Type: ArtistMasterDTO
	 * 
	 * @param: int artistId
	 */
	@Override
	public ArtistMasterDTO getArtistById(int artistId) throws MediaException {
		ArtistMasterDTO artistMasterDTO = new ArtistMasterDTO();
		TypedQuery<ArtistMasterDTO> query = entityManager.createQuery(
				QueryMapper.query5, ArtistMasterDTO.class);
		query.setParameter("partistId", artistId);
		try {
			artistMasterDTO = query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()
					+ " and problems in getting artist by id .");
		}
		return artistMasterDTO;
	}

	/**
	 * Method Name: deleteArtist Description: Finds an artist and its details
	 * for the given artist id and deletes it from the database and returns the
	 * result to the service layer. Return Type: ArtistMasterDTO
	 * 
	 * @param: int artistId
	 */
	@Override
	public ArtistMasterDTO deleteArtist(int artistId) throws MediaException {
		ArtistMasterDTO artistMasterDTO = entityManager.find(
				ArtistMasterDTO.class, artistId);
		try {
			entityManager.remove(artistMasterDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()
					+ " and problems in deleting artist.");
		}
		return artistMasterDTO;
	}

	/**
	 * Method name: artistSongAssoc Description: Inserts the associated artists
	 * and songs into the database and returns the result to the service layer.
	 * Return type: void
	 * 
	 * @param: ArtistSongAssoc artistSongAssoc
	 */
	@Override
	public void artistSongAssoc(ArtistSongAssoc artistSongAssoc)
			throws MediaException {

		try {
			entityManager.persist(artistSongAssoc);
			entityManager.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()
					+ " and problems in associating songs to artist..");
		}
	}

	/**
	 * Method name: getComposerSongs Description: Retrieves the associated
	 * composer and songs for the given composer id from the database and
	 * returns the result to the service layer. Return type: List
	 * 
	 * @param: int composerId
	 */
	@Override
	public List<ComposerSongAssoc> getComposerSongs(int composerId)
			throws MediaException {
		// TODO Auto-generated method stub
		TypedQuery<ComposerSongAssoc> query = entityManager.createQuery(
				QueryMapper.query6, ComposerSongAssoc.class);
		query.setParameter("pcomposerId", composerId);
		try {
			return query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()
					+ " and problems in loading composers and song list.");
		}

	}

	/**
	 * Method Name: insertArtist Description: Adds the Artist and its details
	 * into the database and returns the result to the service layer. Return
	 * Type: ArtistMasterDTO
	 * 
	 * @param: ArtistMasterDTO artistMasterDTO
	 */
	@Override
	public ArtistMasterDTO insertArtist(ArtistMasterDTO artistMasterDTO) {
		entityManager.persist(artistMasterDTO);
		entityManager.flush();
		return artistMasterDTO;
	}

	/**
	 * Method Name: updateArtist Description: Performs update operation by
	 * updating the details of a artist in the database and returns the result
	 * to the service layer. Return Type: ArtistMasterDTO
	 * 
	 * @param: ArtistMasterDTO artistMasterDTO
	 */
	@Override
	public ArtistMasterDTO updateArtist(ArtistMasterDTO artistMasterDTO) {
		artistMasterDTO = entityManager.merge(artistMasterDTO);
		entityManager.flush();
		return artistMasterDTO;

	}

	/**
	 * Method name: listAllSongsForComposer Description: Retrieves a song from
	 * the database for a given song id and returns the result to the service
	 * layer. Return type: SongMasterDTO
	 * 
	 * @param: int songId
	 */
	@Override
	public SongMasterDTO listAllSongsForComposer(int songId)
			throws MediaException {
		TypedQuery<SongMasterDTO> query = entityManager.createQuery(
				QueryMapper.query7, SongMasterDTO.class);
		query.setParameter("psongId", songId);
		try {
			temp = query.getResultList();
			if (temp.size() == 0) {
				SongMasterDTO songMasterDTO = new SongMasterDTO();
				return songMasterDTO;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()
					+ " and problems in loading songs for composer..");
		}
		return temp.get(0);
	}

	/**
	 * Method name: getArtistSongs Description: Retrieves the associated artist
	 * and songs for the given artist id from the database and returns the
	 * result to the service layer. Return type: List
	 * 
	 * @param: int artistId
	 */
	@Override
	public List<ArtistSongAssoc> getArtistSongs(int artistId)
			throws MediaException {
		// TODO Auto-generated method stub
		TypedQuery<ArtistSongAssoc> query = entityManager.createQuery(
				QueryMapper.query8, ArtistSongAssoc.class);
		query.setParameter("partistId", artistId);
		try {
			return query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()
					+ " and problems in getting songs for artist..");
		}
	}

	/**
	 * Method name: checkPassword Description: Validates the password when it is
	 * entered the second time and returns the result to the service layer.
	 * Return type: ModelAndView
	 * 
	 * @param: UserMasterDTO userMasterDTO
	 */
	@Override
	public ModelAndView checkPassword(UserMasterDTO userMasterDTO)
			throws MediaException {

		try {
			entityManager.persist(userMasterDTO);
			entityManager.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()
					+ " and problems in matching password.");
		}
		return new ModelAndView("createSuccess", "userMasterDTO", userMasterDTO);
	}

	/**
	 * Method name: listAllSongs Description: It fetches all the songs details
	 * from the database and returns the list to the service layer Return type:
	 * ArrayList
	 * 
	 * @param: no parameters
	 */
	@Override
	public List<SongMasterDTO> listAllSongs() throws MediaException {
		// TODO Auto-generated method stub
		TypedQuery<SongMasterDTO> query = entityManager.createQuery(
				QueryMapper.query9, SongMasterDTO.class);
		try {
			return query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()
					+ " and problems in loading songs ..");
		}
	}

	/**
	 * Method name: insertSong Description: It persists a song details into the
	 * database and returns the SongMasterDTO object to the service layer Return
	 * type: SongMasterDTO songMasterDTO
	 * 
	 * @param: SongMasterDTO songMasterDTO
	 */
	@Override
	public SongMasterDTO insertSong(SongMasterDTO songMasterDTO)
			throws MediaException {
		// TODO Auto-generated method stub
		try {
			entityManager.persist(songMasterDTO);
			entityManager.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()
					+ " and problems in inserting songs.");
		}
		return songMasterDTO;
	}

	/**
	 * Method name: deleteComposer Description: It deletes a composer details
	 * from the database and returns the ComposerMasterDTO object to the service
	 * layer Return type: ComposerMasterDTO composerMasterDTO
	 * 
	 * @param: int composerId
	 */
	@Override
	public ComposerMasterDTO deleteComposer(int composerId)
			throws MediaException {
		ComposerMasterDTO composerMasterDTO;
		try {
			composerMasterDTO = entityManager.find(ComposerMasterDTO.class,
					composerId);
			entityManager.remove(composerMasterDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new MediaException(e.getMessage()
					+ " problem in composer deletion");
		}
		return composerMasterDTO;
	}

	/**
	 * Method name: deleteSong Description: It deletes a songs details from the
	 * database and returns SongMasterDTO to the service layer Return type:
	 * SongMasterDTO songMasterDTO
	 * 
	 * @param: songId
	 */
	@Override
	public SongMasterDTO deleteSong(int songId) throws MediaException {
		SongMasterDTO songMasterDTO = entityManager.find(SongMasterDTO.class,
				songId);
		try {
			entityManager.remove(songMasterDTO);
		} catch (Exception e) {
			throw new MediaException(e.getMessage()
					+ " and problems in deleting song.");
		}
		return songMasterDTO;
	}

}