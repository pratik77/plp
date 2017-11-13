package com.cg.mp.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COMPOSER_SONG_ASSOC")
public class ComposerSongAssoc implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	

	
	@Column(name="COMPOSER_ID")
	private int composerId;
	
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
	
	public int getComposerId() {
		return composerId;
	}
	public void setComposerId(int composerId) {
		this.composerId = composerId;
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
	@Override
	public String toString() {
		return "ComposerSongAssoc [composerId=" + composerId + ", songId="
				+ songId + ", createdBy=" + createdBy + ", createdOn="
				+ createdOn + ", updatedBy=" + updatedBy + ", updatedOn="
				+ updatedOn + "]";
	}
	

}
