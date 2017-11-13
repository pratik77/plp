package com.cg.mp.dto;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="ARTIST_SONG_ASSOC")
public class ArtistSongAssoc {
	
	@Column(name="ARTIST_ID")
	private int artistId;
	@Id
	@Column(name="SONG_ID")
	private int songId;
	
	@Column(name="CREATED_BY")
	private int createdBy;
	
	@Column(name="CREATED_ON")
	private Date createdOn;
	
	@Column(name="UPDATED_BY")
	private int updatedBy;
	
	@Column(name="UPDATED_ON")
	private Date updatedOn;
	
	
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
