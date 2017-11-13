package com.cg.mp.dto;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="composer_master")

public class ComposerMasterDTO 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="composer_id")
	private int composerId;
	@Column(name="composer_name")
	private String composerName;
	@Column(name="composer_borndate")
	private Date composerBornDate;
	@Column(name="composer_dieddate")
	private Date composerDiedDate;
	@Column(name="composer_caeipinumber")
	private String composerCaeipiNumber;
	@Column(name="composer_musicsocietyid")
	private String composerMusicSocId;
	@Column(name="created_by")
	private int createdBy;
	@Column(name="created_on")
	private Date createdOn;
	@Column(name="updated_by")
	private int updatedBy;
	@Column(name="updated_on")
	private Date updatedOn;
	@Column(name="composer_deletedflag")
	private int composerDelFlag;
	
	@Override
	public String toString() {
		return "ComposerMasterDTO [composerId=" + composerId
				+ ", composerName=" + composerName + ", composerBornDate="
				+ composerBornDate + ", composerDiedDate=" + composerDiedDate
				+ ", composerCaeipiNumber=" + composerCaeipiNumber
				+ ", composerMusicSocId=" + composerMusicSocId + ", createdBy="
				+ createdBy + ", createdOn=" + createdOn + ", updatedBy="
				+ updatedBy + ", updatedOn=" + updatedOn + ", composerDelFlag="
				+ composerDelFlag + "]";
	}
	public int getComposerId() {
		return composerId;
	}
	public void setComposerId(int composerId) {
		this.composerId = composerId;
	}
	public String getComposerName() {
		return composerName;
	}
	public void setComposerName(String composerName) {
		this.composerName = composerName;
	}
	public Date getComposerBornDate() {
		return composerBornDate;
	}
	public void setComposerBornDate(Date composerBornDate) {
		this.composerBornDate = composerBornDate;
	}
	public Date getComposerDiedDate() {
		return composerDiedDate;
	}
	public void setComposerDiedDate(Date composerDiedDate) {
		this.composerDiedDate = composerDiedDate;
	}
	public String getComposerCaeipiNumber() {
		return composerCaeipiNumber;
	}
	public void setComposerCaeipiNumber(String composerCaeipiNumber) {
		this.composerCaeipiNumber = composerCaeipiNumber;
	}
	public String getComposerMusicSocId() {
		return composerMusicSocId;
	}
	public void setComposerMusicSocId(String composerMusicSocId) {
		this.composerMusicSocId = composerMusicSocId;
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
	public int getComposerDelFlag() {
		return composerDelFlag;
	}
	public void setComposerDelFlag(int composerDelFlag) {
		this.composerDelFlag = composerDelFlag;
	}
	
}
