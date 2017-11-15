package com.cg.mp.dto;

/**
 * File name: ArtistSongAssoc
 * Package name: com.cg.mp.dto
 * Description:This is a POJO class which provides getters and setters for Artist Song Association
 * Version: 	1.0
 * Restrictions:N/A
 * @author pratiksa,sayush,rauagarw,sapsaha
 * Date: 13/11/2017
 */

import java.sql.Date;

/**
 * File name: ArtistSongAssoc
 * Package name: com.cg.mp.dto
 * Description:This is a POJO class which provides getters and setters for Artist Song Association
 * Version: 	1.0
 * Restrictions:N/A
 * @author pratiksa,sayush,rauagarw,
 * Date: 13/11/2017
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ARTIST_SONG_ASSOC")
public class ArtistSongAssoc {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ASSOCIATION_ID")
	private int associationId;

	@Column(name = "ARTIST_ID")
	private int artistId;

	@Column(name = "SONG_ID")
	private int songId;

	@Column(name = "CREATED_BY")
	private int createdBy;

	@Column(name = "CREATED_ON")
	private Date createdOn;

	@Column(name = "UPDATED_BY")
	private int updatedBy;

	@Column(name = "UPDATED_ON")
	private Date updatedOn;

	/**
	 * Getter Setter Methods for ArtistSongAssoc
	 */

	public int getArtistId() {
		return artistId;
	}

	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}

	public int getSongId() {
		return songId;
	}

	public void setSongId(int songId) {
		this.songId = songId;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

}
