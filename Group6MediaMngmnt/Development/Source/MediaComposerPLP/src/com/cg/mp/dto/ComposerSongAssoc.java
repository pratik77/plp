package com.cg.mp.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * File name: ComposerSongAssoc Package name: com.cg.mp.dto Description:This is
 * a POJO class which provides getters and setters for Composer Song Association
 * Version: 1.0 Restrictions:N/A
 * 
 * @author pratiksa,sayush,rauagarw,sapsaha Date: 13/11/2017
 */

@Entity
@Table(name = "COMPOSER_SONG_ASSOC")
public class ComposerSongAssoc implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ASSOCIATION_ID")
	private int associationId;

	@Column(name = "COMPOSER_ID")
	private int composerId;

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

	/*
	 * Getter Setter methods for ComposerSongAssoc
	 */

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
