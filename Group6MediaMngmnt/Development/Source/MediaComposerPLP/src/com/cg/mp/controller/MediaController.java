package com.cg.mp.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.mp.dto.ArtistMasterDTO;
import com.cg.mp.dto.ComposerMasterDTO;
import com.cg.mp.dto.SongMasterDTO;
import com.cg.mp.exception.MediaException;
import com.cg.mp.service.MediaService;

/**
 * File name: MediaController Package name: com.cg.mp.controller Description:
 * Controller which handles the user request and navigate to respective jsp
 * pages. Version: 1.0 Restrictions:N/A
 * 
 * @author pratiksa,sayush,rauagarw,sapsaha Date: 13/11/2017
 */
@Controller
public class MediaController {
	@Autowired
	private MediaService mediaService;

	List<ComposerMasterDTO> composers = new ArrayList<>();
	List<SongMasterDTO> songs = new ArrayList<>();
	List<ArtistMasterDTO> artists = new ArrayList<>();
	String userFlag, message = "";
	ModelAndView checkPassword;
	int userId;

	/**
	 * Method name: checkLogin Description: This method will navigate the user
	 * to the Login page.
	 * 
	 * @param username
	 * @param password
	 * @param model
	 * @return Type: String
	 */
	@RequestMapping(value = "/login.obj")
	public String checkLogin(@RequestParam("username") int username,
			@RequestParam("password") String password, Model model) {
		try {
			userFlag = mediaService.checkLogin(username, password);
		} catch (MediaException e) {
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		userId = username;
		if (("../login1").equals(userFlag))
			model.addAttribute("message",
					"Invalid user ID and password combination.");
		return userFlag;
	}

	/**
	 * Method name: createAnAccount Description: This method will allow the user
	 * to sign Up and navigates to createAnAccount page
	 * 
	 * @param model
	 * @return ytpe: String
	 */
	@RequestMapping(value = "/createAnAccount.obj")
	public String createAnAccount(Model model) {
		return "createAnAccount";
	}

	@RequestMapping(value = "/accountCreation.obj")
	public ModelAndView accountCreation(
			@RequestParam("password") String password,
			@RequestParam("cpassword") String cpassword, Model model) {
		message = "";
		try {
			checkPassword = mediaService.checkPassword(password, cpassword);
		} catch (MediaException e) {
			return new ModelAndView("mediaError", "message", e.getMessage());
		}
		return checkPassword;
	}

	/**
	 * Method name: composerSelect Description: This method gets the entire
	 * composer list present and navigates to ShowComposer page.
	 * 
	 * @param model
	 * @return type: String
	 */
	@RequestMapping(value = "/retrieveAllComposer.obj")
	public String composerSelect(Model model) {
		List<ComposerMasterDTO> composerList;
		try {
			composerList = mediaService.loadAllComposer();
			composerList = mediaService.loadAllComposer();
		} catch (MediaException e) {
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("composerList", composerList);
		model.addAttribute("composerMasterDTO", new ComposerMasterDTO());
		return "ShowComposer";
	}

	/**
	 * Method name: modifyAndDeleteComposer Description: This method allows the
	 * admin to either delete or modify the composer details 1.When the admin
	 * clicks Modify, method navigates to Modify Composer 2.When the admin
	 * clicks Delete, method navigates to Delete Composer
	 * 
	 * @param submit
	 * @param composerId
	 * @param composerMasterDTO
	 * @param model
	 * @return type: String
	 */
	@RequestMapping(value = "/modifyOrDelete.obj")
	public String modifyAndDeleteComposer(
			@RequestParam("submit") String submit,

			@RequestParam("composerId") String composerId,
			@ModelAttribute("composerMasterDTO") ComposerMasterDTO composerMasterDTO,
			Model model) {
		switch (submit) {
		case "modify":
			try {
				composerMasterDTO = mediaService
						.getComposerById(composerMasterDTO.getComposerId());
				LocalDate ldate = LocalDate.now();
				Date sqlDate = Date.valueOf(ldate);
				model.addAttribute("sqlDate", sqlDate);
			} catch (MediaException e) {
				model.addAttribute("message", e.getMessage());
				return "mediaError";
			}
			model.addAttribute("composerMasterDTO", composerMasterDTO);
			return "ModifyComposer";
		case "delete":
			try {
				composerMasterDTO = mediaService
						.getComposerById(composerMasterDTO.getComposerId());
				composerMasterDTO = mediaService
						.deleteComposer(composerMasterDTO.getComposerId());
				composers = mediaService.loadAllComposer();
			} catch (MediaException e) {
				model.addAttribute("message", e.getMessage());
				return "mediaError";
			}
			model.addAttribute("composerList", composers);
			message = new String("Composer with Id "
					+ composerMasterDTO.getComposerId() + "deleted!");
			model.addAttribute("message", message);
			return "ShowComposer";
		}
		return "composer";
	}

	/**
	 * Method name: addComposer Description: This method navigates the admin to
	 * the AddComposer page.
	 * 
	 * @param model
	 * @return type: String
	 */
	@RequestMapping(value = "/insertComposer.obj")
	public String add(Model model) {
		LocalDate ldate = LocalDate.now();
		Date sqlDate = Date.valueOf(ldate);
		model.addAttribute("sqlDate", sqlDate);
		model.addAttribute("composer", new ComposerMasterDTO());
		return "AddComposer";
	}

	/**
	 * Method name: saveComposer Description: This method allows the admin to
	 * add a composer to the list and finally navigates to the success page
	 * after successfully adding the composer.
	 * 
	 * @param model
	 * @return type: String
	 */
	@RequestMapping(value = "/addComposer.obj")
	public String saveComposer(
			@ModelAttribute("composer") ComposerMasterDTO composer, Model model) {
		composer.setCreatedBy(userId);
		composer.setUpdatedBy(userId);
		LocalDate ldate = LocalDate.now();
		Date sqlDate = Date.valueOf(ldate);
		composer.setCreatedOn(sqlDate);
		composer.setUpdatedOn(sqlDate);
		try {
			ComposerMasterDTO composerCheck = mediaService
					.insertComposer(composer);
		} catch (MediaException e) {

			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("message",
				"Composer with Id " + composer.getComposerId()
						+ " added successfully!");
		return "successComposer";
	}

	/**
	 * Method name: modifyComposer Description: This method helps to update the
	 * modified details of the composer and finally navigates to the success
	 * page after successfully updating the composer.
	 * 
	 * @param composerMasterDTO
	 * @param model
	 * @return type: String
	 */
	@RequestMapping(value = "/insertModifiedComposer.obj", method = RequestMethod.POST)
	public String modifyComposer(
			@ModelAttribute("composerMasterDTO") ComposerMasterDTO composerMasterDTO,
			Model model) {
		composerMasterDTO.setCreatedBy(userId);
		composerMasterDTO.setUpdatedBy(userId);
		LocalDate ldate = LocalDate.now();
		Date sqlDate = Date.valueOf(ldate);
		composerMasterDTO.setCreatedOn(sqlDate);
		composerMasterDTO.setUpdatedOn(sqlDate);
		try {
			ComposerMasterDTO composerCheck = mediaService
					.updateComposer(composerMasterDTO);
		} catch (MediaException e) {
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("message",
				"Composer with Id " + composerMasterDTO.getComposerId()
						+ " modified successfully!");
		return "successComposer";
	}

	/**
	 * Method name: artistSelect Description: This method gets the entire artist
	 * list present and navigates to ShowArtist page.
	 * 
	 * @param model
	 * @return type: String
	 */
	@RequestMapping(value = "/retrieveAllArtist.obj")
	public String artistSelect(Model model) {
		List<ArtistMasterDTO> artistList;
		try {
			artistList = mediaService.loadAllArtists();
			artistList = mediaService.loadAllArtists();
		} catch (MediaException e) {
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("artistList", artistList);
		model.addAttribute("artistMasterDTO", new ArtistMasterDTO());
		return "ShowArtist";
	}

	/**
	 * Method name: modifyOrDeleteArtist Description: This method allows the
	 * admin to either delete or modify the artist details 1.When the admin
	 * clicks Modify, method navigates to Modify Artist 2.When the admin clicks
	 * Delete, method navigates to Delete Artist
	 * 
	 * @param submit
	 * @param artistId
	 * @param artistMasterDTO
	 * @param model
	 * @return type: String
	 */
	@RequestMapping(value = "/modifyOrDeleteArtist.obj")
	public String artistSelect(@RequestParam("submit") String submit,
			@RequestParam("artistId") int artistId,
			@ModelAttribute("artistMasterDTO") ArtistMasterDTO artistMasterDTO,
			Model model) {
		if (("delete").equals(submit)) {
			try {
				artistMasterDTO = mediaService.getArtistById(artistId);
				model.addAttribute("artistMasterDTO", artistMasterDTO);
				artistMasterDTO = mediaService.deleteArtist(artistId);
				artists = mediaService.loadAllArtists();
			} catch (MediaException e) {

				model.addAttribute("message", e.getMessage());
				return "mediaError";
			}
			model.addAttribute("artistList", artists);
			model.addAttribute("artistMasterDTO", new ArtistMasterDTO());
			message = new String("Artist deleted!");
			model.addAttribute("message", message);
			return "ShowArtist";
		}
		if (("modify").equals(submit)) {
			try {
				LocalDate ldate = LocalDate.now();
				Date sqlDate = Date.valueOf(ldate);
				model.addAttribute("sqlDate", sqlDate);
				artistMasterDTO = mediaService.getArtistById(artistId);
			} catch (MediaException e) {

				model.addAttribute("message", e.getMessage());
				return "mediaError";
			}
			model.addAttribute("artistMasterDTO", artistMasterDTO);
			return "ModifyArtist";
		}
		return "ShowArtist";
	}

	/**
	 * Method name: modifyArtist Description: This method helps to update the
	 * modified details of the artist and finally navigates to the success page
	 * after successfully updating the artist.
	 * 
	 * @param artistMasterDTO
	 * @param model
	 * @return type: String
	 */
	@RequestMapping(value = "/insertModifiedArtist.obj", method = RequestMethod.POST)
	public String modifyArtist(
			@ModelAttribute("artistMasterDTO") ArtistMasterDTO artistMasterDTO,
			Model model) {
		artistMasterDTO.setCreatedBy(userId);
		artistMasterDTO.setUpdatedBy(userId);
		LocalDate ldate = LocalDate.now();
		Date sqlDate = Date.valueOf(ldate);
		artistMasterDTO.setCreatedOn(sqlDate);
		artistMasterDTO.setUpdatedOn(sqlDate);
		try {
			ArtistMasterDTO artistCheck = mediaService
					.updateArtist(artistMasterDTO);
		} catch (MediaException e) {
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("message",
				"Artist with Id " + artistMasterDTO.getArtistId()
						+ " modified successfully!");
		return "successArtist";
	}

	/**
	 * Method name: addArtist Description: This method navigates the artist to
	 * the AddArtist page.
	 * 
	 * @param model
	 * @return type: String
	 */
	@RequestMapping("/insertArtist.obj")
	public String addArtist(Model model) {
		LocalDate ldate = LocalDate.now();
		Date sqlDate = Date.valueOf(ldate);
		model.addAttribute("sqlDate", sqlDate);
		model.addAttribute("artistMasterDTO", new ArtistMasterDTO());
		return "AddArtist";
	}

	/**
	 * Method name: saveArtist Description: This method allows the admin to add
	 * a artist to the list and finally navigates to the success page after
	 * successfully adding the artist.
	 * 
	 * @param artistMasterDTO
	 * @param model
	 * @return type: String
	 */
	@RequestMapping(value = "/addArtist.obj", method = RequestMethod.POST)
	public String saveArtist(
			@ModelAttribute("artistMasterDTO") ArtistMasterDTO artistMasterDTO,
			Model model) {
		artistMasterDTO.setCreatedBy(userId);
		artistMasterDTO.setUpdatedBy(userId);
		LocalDate ldate = LocalDate.now();
		Date sqlDate = Date.valueOf(ldate);
		artistMasterDTO.setCreatedOn(sqlDate);
		artistMasterDTO.setUpdatedOn(sqlDate);
		try {
			ArtistMasterDTO artistCheck = mediaService
					.insertArtist(artistMasterDTO);
		} catch (MediaException e) {
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("message",
				"Artist with Id " + artistMasterDTO.getArtistId()
						+ " added successfully!");
		return "successArtist";
	}

	/**
	 * Method name: retrieveArtistSong Description: This method gets the artist
	 * and the songs list and navigates to artistSongAssoc.
	 * 
	 * @param model
	 * @return type: String
	 */
	@RequestMapping(value = "/retrieveArtistSong.obj")
	public String retrieveArtistSong(Model model) {
		try {
			artists = mediaService.loadAllArtists();
			songs = mediaService.loadAllSongs();
		} catch (MediaException e) {
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("artistList", artists);
		model.addAttribute("songList", songs);
		return "artistSongAssoc";
	}

	/**
	 * Method name: compSongAssoc Description: This method allows the admin to
	 * associate songs to the composer and finally navigates to
	 * composerSongAssocSuccess.
	 * 
	 * @param composerId
	 * @param songIdList
	 * @param model
	 * @return type: String
	 */
	@RequestMapping(value = "/composerSongAssoc.obj")
	public String compSongAssoc(@RequestParam("composerSelect") int composerId,
			@RequestParam("songSelect") int[] songIdList, Model model) {

		try {
			mediaService.compSongAssoc(composerId, songIdList, userId);
		} catch (MediaException e) {
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("composerList", composers);
		model.addAttribute("songList", songs);
		model.addAttribute("message",
				"Composer and songs associated successfully");
		return "composerSongAssocSuccess";
	}

	/**
	 * Method name: artistSongAssoc Description: This method allows the admin to
	 * associate songs to the artist and finally navigates to
	 * artistSongAssocSuccess page.
	 * 
	 * @param artistId
	 * @param songIdList
	 * @param model
	 * @return type: String
	 */
	@RequestMapping(value = "/artistSongAssoc.obj")
	public String artistSongAssoc(@RequestParam("artistSelect") int artistId,
			@RequestParam("songSelect") int[] songIdList, Model model) {

		try {
			mediaService.artistSongAssoc(artistId, songIdList, userId);
		} catch (MediaException e) {
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("artistList", artists);
		model.addAttribute("songList", songs);
		model.addAttribute("message",
				"Artist and songs associated successfully");
		return "artistSongAssocSuccess";
	}

	/**
	 * Method name: retrieveCompSong Description: This method gets the composer
	 * and the songs list and navigates to composerSongAssoc page.
	 * 
	 * @param model
	 * @return type: String
	 */
	@RequestMapping(value = "/retrieveComposerSong.obj")
	public String retrieveCompSong(Model model) {
		try {
			composers = mediaService.loadAllComposer();
			songs = mediaService.loadAllSongs();
		} catch (MediaException e) {
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("composerList", composers);
		model.addAttribute("songList", songs);
		return "composerSongAssoc";
	}

	/**
	 * Method name: retrieveComposerListForSongs Description: This method
	 * retrieves the composer list from the database and navigates it to the
	 * retrieveComposerListForSongs page.
	 * 
	 * @param model
	 * @return type: String
	 */
	@RequestMapping(value = "/retrieveComposerListForSongs.obj")
	public String retrieveComposerListForSongs(Model model) {

		try {
			composers = mediaService.loadAllComposer();
		} catch (MediaException e) {

			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("composerList", composers);
		return "retrieveComposerListForSongs";
	}

	/**
	 * Method name: listSongsForComposer Description: Retrieves songs for a
	 * composer by navigating to the listAllSongsForComposer page.
	 * 
	 * @param composerId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listSongsForComposer.obj")
	public String listSongsForComposer(
			@RequestParam("composerSelect") int composerId, Model model) {
		TreeSet<SongMasterDTO> songs = new TreeSet<SongMasterDTO>();
		try {
			songs = mediaService.listAllSongsForComposer(composerId);
		} catch (MediaException e) {

			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		if (songs.isEmpty()) {
			model.addAttribute("message",
					"No songs exist for the selected Composer");
			return "mediaEmpty";
		}
		model.addAttribute("composerId", composerId);
		model.addAttribute("songList", songs);
		return "listAllSongsForComposer";
	}

	/**
	 * Method name: retrieveArtistListForSongs Description: This method
	 * retrieves the artist list from the database and navigates it to the
	 * retrieveArtistListForSongs page.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/retrieveArtistListForSongs.obj")
	public String retrieveArtistListForSongs(Model model) {
		try {
			artists = mediaService.loadAllArtists();
		} catch (MediaException e) {
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("artists", artists);
		return "retrieveArtistListForSongs";
	}

	/**
	 * Method name: listSongsForArtist Description: Retrieves songs for a artist
	 * by navigating to the listAllSongsForArtist page.
	 * 
	 * @param artistId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listSongsForArtist.obj")
	public String listSongsForArtist(
			@RequestParam("artistSelect") int artistId, Model model) {
		TreeSet<SongMasterDTO> songs = new TreeSet<SongMasterDTO>();
		try {
			songs = mediaService.listAllSongsForArtist(artistId);
		} catch (MediaException e) {
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		if (songs.isEmpty()) {
			model.addAttribute("message",
					"No songs exist for the selected Artist");
			return "mediaEmpty";
		}
		model.addAttribute("artistId", artistId);
		model.addAttribute("songList", songs);
		return "listAllSongsForArtist";
	}

	/**
	 * Method name: retrieveSongs Description: Retrieves all songs from the
	 * database and displays it on retrieveSongs.jsp.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/retrieveSongs.obj")
	public String retrieveSongs(Model model) {

		try {
			songs = mediaService.listAllSongs();
		} catch (MediaException e) {
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		if (songs.size() != 0) {
			model.addAttribute("songMasterDTO", new SongMasterDTO());
			model.addAttribute("songList", songs);
			return "retrieveSongs";
		} else {
			model.addAttribute("message", "No songs available.");
			return "noMedia";
		}
	}

	/**
	 * Method name: insertSongs Description: This method sends an empty object
	 * of SongMasterDTO class to addSongs.jsp
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/insertSongs.obj")
	public String insertSongs(Model model) {
		model.addAttribute("songMasterDTO", new SongMasterDTO());
		return "addSongs";
	}

	/**
	 * Method name: addSongs Description: This method redirects to
	 * addSongsSuccess and display the success message of insertion of song
	 * 
	 * @param songMasterDTO
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addSongs.obj")
	public String addSongs(
			@ModelAttribute("songMasterDTO") SongMasterDTO songMasterDTO,
			Model model) {
		try {
			SongMasterDTO songMasterDTONew = mediaService.insertSong(
					songMasterDTO, userId);
		} catch (MediaException e) {
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("songMasterDTO", songMasterDTO);
		return "addSongsSuccess";
	}

	/**
	 * Method name: deleteSong Description: This method deletes a song from the
	 * database and displays success page on retrieveSongs.jsp page
	 * 
	 * @param songMasterDTO
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteSong.obj")
	public String deleteSong(
			@ModelAttribute("songMasterDTO") SongMasterDTO songMasterDTO,
			Model model) {
		try {
			songMasterDTO = mediaService.deleteSong(songMasterDTO.getSongId());
			songs = mediaService.loadAllSongs();
			model.addAttribute("songList", songs);
			model.addAttribute("message",
					"Song with Id " + songMasterDTO.getSongId()
							+ "deleted Successfully");
		} catch (MediaException e) {
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		return "retrieveSongs";
	}
}
