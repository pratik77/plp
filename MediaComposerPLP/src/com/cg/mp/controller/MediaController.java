package com.cg.mp.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import com.cg.mp.service.IMediaService;

/**
 * 
 * @author pratiksa
 *
 */
@Controller
public class MediaController {

	@Autowired
	private IMediaService mediaService;

	List<ComposerMasterDTO> composers = new ArrayList();
	List<SongMasterDTO> songs = new ArrayList();
	List<ArtistMasterDTO> artists = new ArrayList();
	String userFlag, message = "";
	ModelAndView checkPassword;

	int userId;

	/**
	 * 
	 * @param username
	 * @param password
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/login.obj")
	public String checkLogin(@RequestParam("username") int username,
			@RequestParam("password") String password, Model model) {
		try {
			userFlag = mediaService.checkLogin(username, password);
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		userId = username;
		if (("../login1").equals(userFlag))
			model.addAttribute("message",
					"Invalid user ID and password combination.");
		return userFlag;

	}

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
			// TODO Auto-generated catch block
			return new ModelAndView("mediaError", "message", e.getMessage());
		}
		return checkPassword;

	}

	@RequestMapping(value = "/retrieveAllComposer.obj")
	public String compSelect(Model model) {
		List<ComposerMasterDTO> composerList;
		try {
			composerList = mediaService.loadAllComposer();
			composerList = mediaService.loadAllComposer();
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("composerList", composerList);
		model.addAttribute("composerMasterDTO", new ComposerMasterDTO());
		return "ShowComposer";
	}

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
				model.addAttribute("sqlDate",sqlDate);
			} catch (MediaException e) {
				// TODO Auto-generated catch block
				model.addAttribute("message", e.getMessage());
				return "mediaError";
			}
			model.addAttribute("composerMasterDTO", composerMasterDTO);
			return "ModifyComposer";
		case "delete":
			try {
				composerMasterDTO = mediaService.getComposerById(composerMasterDTO.getComposerId());
				composerMasterDTO = mediaService.deleteComposer(composerMasterDTO.getComposerId());
				composers = mediaService.loadAllComposer();
			} catch (MediaException e) {
				// TODO Auto-generated catch block
				model.addAttribute("message", e.getMessage());
				return "mediaError";
			}
			model.addAttribute("composerList",composers);
			message = new String("Composer with Id "+composerMasterDTO.getComposerId()+ "deleted!");
			model.addAttribute("message",message);
			return "ShowComposer";
		}

		model.addAttribute("composerMasterDTO", composerMasterDTO);
		return "Composer";

	}

	@RequestMapping(value = "/insertComposer.obj")
	public String add(Model model) {
		LocalDate ldate = LocalDate.now();
		Date sqlDate = Date.valueOf(ldate);
		model.addAttribute("sqlDate",sqlDate);
		model.addAttribute("composer", new ComposerMasterDTO());
		return "AddComposer";
	}

	@RequestMapping(value = "/addComposer.obj")
	public String save(@ModelAttribute("composer") ComposerMasterDTO composer,
			Model model) {
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
			// TODO Auto-generated catch block
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("message",
				"Composer with Id " + composer.getComposerId()
						+ " added successfully!");
		return "successComposer";
	}

	@RequestMapping(value = "/insertModifiedComposer.obj", method = RequestMethod.POST)
	public String modifyComposer(
			@ModelAttribute("composerMasterDTO") ComposerMasterDTO composerMasterDTO,
			Model model) {
		composerMasterDTO.setCreatedBy(userId);
		System.out.println(composerMasterDTO.getComposerId());
		composerMasterDTO.setUpdatedBy(userId);
		LocalDate ldate = LocalDate.now();
		Date sqlDate = Date.valueOf(ldate);
		composerMasterDTO.setCreatedOn(sqlDate);
		composerMasterDTO.setUpdatedOn(sqlDate);
		try {
			ComposerMasterDTO composerCheck = mediaService
					.updateComposer(composerMasterDTO);
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("message",
				"Composer with Id " + composerMasterDTO.getComposerId()
						+ " modified successfully!");
		return "successComposer";
	}

	@RequestMapping(value = "/retrieveAllArtist.obj")
	public String artistSelect(Model model) {
		List<ArtistMasterDTO> artistList;
		try {
			artistList = mediaService.loadAllArtists();
			artistList = mediaService.loadAllArtists();
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("artistList", artistList);
		model.addAttribute("artistMasterDTO", new ArtistMasterDTO());
		return "ShowArtist";
	}

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
				// TODO Auto-generated catch block
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
				model.addAttribute("sqlDate",sqlDate);
				artistMasterDTO = mediaService.getArtistById(artistId);
			} catch (MediaException e) {
				// TODO Auto-generated catch block
				model.addAttribute("message", e.getMessage());
				return "mediaError";
			}
			model.addAttribute("artistMasterDTO", artistMasterDTO);
			return "ModifyArtist";
		}
		return "ShowArtist";
	}

	@RequestMapping(value = "/insertModifiedArtist.obj", method = RequestMethod.POST)
	public String modifyArtist(
			@ModelAttribute("artistMasterDTO") ArtistMasterDTO artistMasterDTO,
			Model model) {
		artistMasterDTO.setCreatedBy(userId);
		System.out.println(artistMasterDTO.getArtistId());
		artistMasterDTO.setUpdatedBy(userId);
		LocalDate ldate = LocalDate.now();
		Date sqlDate = Date.valueOf(ldate);
		artistMasterDTO.setCreatedOn(sqlDate);
		artistMasterDTO.setUpdatedOn(sqlDate);
		try {
			ArtistMasterDTO artistCheck = mediaService
					.updateArtist(artistMasterDTO);
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("message",
				"Artist with Id " + artistMasterDTO.getArtistId()
						+ " modified successfully!");
		return "successArtist";
	}

	@RequestMapping("/insertArtist.obj")
	public String addArtist(Model model) {
		LocalDate ldate = LocalDate.now();
		Date sqlDate = Date.valueOf(ldate);
		model.addAttribute("sqlDate",sqlDate);
		model.addAttribute("artistMasterDTO", new ArtistMasterDTO());
		return "AddArtist";
	}

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
			// TODO Auto-generated catch block
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("message",
				"Artist with Id " + artistMasterDTO.getArtistId()
						+ " added successfully!");
		return "successArtist";
	}

	@RequestMapping(value = "/retrieveArtistSong.obj")
	public String retrieveArtistSong(Model model)

	{

		try {
			artists = mediaService.loadAllArtists();
			songs = mediaService.loadAllSongs();
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("artistList", artists);
		model.addAttribute("songList", songs);
		return "artistSongAssoc";
	}

	@RequestMapping(value = "/composerSongAssoc.obj")
	public String compSongAssoc(@RequestParam("composerSelect") int composerId,
			@RequestParam("songSelect") int[] songIdList, Model model) {

		try {
			mediaService.compSongAssoc(composerId, songIdList, userId);
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("composerList", composers);
		model.addAttribute("songList", songs);
		model.addAttribute("message",
				"Composer and songs associated successfully");
		return "composerSongAssocSuccess";
	}

	@RequestMapping(value = "/artistSongAssoc.obj")
	public String artistSongAssoc(@RequestParam("artistSelect") int artistId,
			@RequestParam("songSelect") int[] songIdList, Model model)

	{

		try {
			mediaService.artistSongAssoc(artistId, songIdList, userId);
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("artistList", artists);
		model.addAttribute("songList", songs);
		model.addAttribute("message",
				"Artist and songs associated successfully");
		return "artistSongAssocSuccess";
	}

	@RequestMapping(value = "/retrieveComposerSong.obj")
	public String retrieveCompSong(Model model) {

		try {
			composers = mediaService.loadAllComposer();
			songs = mediaService.loadAllSongs();
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("composerList", composers);
		model.addAttribute("songList", songs);
		return "composerSongAssoc";
	}

	@RequestMapping(value = "/retrieveComposerListForSongs.obj")
	public String retrieveComposerListForSongs(Model model) {

		try {
			composers = mediaService.loadAllComposer();
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("composerList", composers);
		return "retrieveComposerListForSongs";
	}

	@RequestMapping(value = "/listSongsForComposer.obj")
	public String listSongsForComposer(
			@RequestParam("composerSelect") int composerId, Model model) {

		try {
			songs = mediaService.listAllSongsForComposer(composerId);
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("songList", songs);
		return "listAllSongsForComposer";
	}

	@RequestMapping(value = "/retrieveArtistListForSongs.obj")
	public String retrieveArtistListForSongs(Model model) {

		try {
			artists = mediaService.loadAllArtists();
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("artists", artists);
		return "retrieveArtistListForSongs";
	}

	@RequestMapping(value = "/listSongsForArtist.obj")
	public String listSongsForArtist(
			@RequestParam("artistSelect") int artistId, Model model) {

		try {
			songs = mediaService.listAllSongsForArtist(artistId);
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		model.addAttribute("songList", songs);
		return "listAllSongsForArtist";
	}

	@RequestMapping(value = "/retrieveSongs.obj")
	public String retrieveSongs(Model model) {

		try {
			songs = mediaService.listAllSongs();
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		if (songs.size() != 0) {
			model.addAttribute("songMasterDTO", new SongMasterDTO());
			model.addAttribute("songList", songs);
			return "retrieveSongs";
		}
		else
		{
			model.addAttribute("message", "No songs available.");
			return "noMedia";
		}
		
	}
	
	@RequestMapping(value = "/insertSongs.obj")
	public String insertSongs(Model model) {

		model.addAttribute("songMasterDTO",new SongMasterDTO());
		return "addSongs";
	}
	
	@RequestMapping(value = "/addSongs.obj")
	public String addSongs(@ModelAttribute("songMasterDTO") SongMasterDTO songMasterDTO,
			Model model) {

	    try {
			SongMasterDTO songMasterDTONew=mediaService.insertSong(songMasterDTO,userId);
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
	    model.addAttribute("songMasterDTO",songMasterDTO);
		return "addSongsSuccess";
	}
	
	@RequestMapping(value = "/deleteSong.obj")
	public String deleteSong(@ModelAttribute("songMasterDTO") SongMasterDTO songMasterDTO,Model model) {
		try {
			songMasterDTO = mediaService.deleteSong(songMasterDTO.getSongId());
			songs = mediaService.loadAllSongs();
			model.addAttribute("songList", songs);
			model.addAttribute("message", "Song with Id " +songMasterDTO.getSongId()+ "deleted Successfully");
		} catch (MediaException e) {
			model.addAttribute("message", e.getMessage());
			return "mediaError";
		}
		return "retrieveSongs";
	}
	

}
