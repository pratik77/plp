package com.cg.mp.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.cg.mp.dao.MediaDAO;
import com.cg.mp.dto.ArtistMasterDTO;
import com.cg.mp.dto.ComposerMasterDTO;
import com.cg.mp.dto.UserMasterDTO;
import com.cg.mp.exception.MediaException;
import com.cg.mp.service.MediaService;
import com.cg.mp.service.MediaServiceImpl;

public class MediaTest {

	@Mock
	private MediaDAO mediaDAO;

	@InjectMocks
	private MediaService mediaService = new MediaServiceImpl();

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	ComposerMasterDTO composer = new ComposerMasterDTO();
	
	@Test
	public void testGetComposerById() {
		ComposerMasterDTO composer = new ComposerMasterDTO();
		composer.setComposerId(87);
		try {
			stub(mediaDAO.getComposerById(87)).toReturn(composer);
		} catch (MediaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			assertEquals(composer, mediaService.getComposerById(87));
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			verify(mediaDAO).getComposerById(87);
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertComposer() throws MediaException {
		UserMasterDTO user = new UserMasterDTO();
		stub(mediaDAO.insertComposer(composer))
				.toReturn(composer);
		assertEquals(composer, mediaService.insertComposer(composer));
		verify(mediaDAO).insertComposer(composer);
	}
	
	@Test
	public void testupdateArtist() throws MediaException {
		ArtistMasterDTO artist=new ArtistMasterDTO();
		stub(mediaDAO.updateArtist(artist))
				.toReturn(artist);
		assertEquals(artist, mediaDAO.updateArtist(artist));
		verify(mediaDAO).updateArtist(artist);
	}

}
