package com.cg.mp.dto;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SONG_MASTER")
public class SongMasterDTO 
{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SONG_ID")
	private int songId;
	
	@Column(name="SONG_NAME")
	private String songName;
	
	@Column(name="SONG_DURATION")
	private String songDuration;
	
	@Column(name="CREATED_BY")
	private int createdBy;
	
	@Column(name="CREATED_ON")
	private Date createdOn;
	
	@Column(name="UPDATED_BY")
	private int updatedBy;
	
	@Column(name="UPDATED_ON")
	private Date updatedOn;
	
	@Column(name="SONG_DELETEDFLAG")
	private int songDelFlag;


	public SongMasterDTO() {
		super();
	}
	public SongMasterDTO(int songId, String songName, String songDuration,
			int createdBy, Date createdOn, int updatedBy, Date updatedOn,
			int songDelFlag) {
		super();
		this.songId = songId;
		this.songName = songName;
		this.songDuration = songDuration;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.songDelFlag = songDelFlag;
	}
	public int getSongId() {
		return songId;
	}
	public void setSongId(int songId) {
		this.songId = songId;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public String getSongDuration() {
		return songDuration;
	}
	public void setSongDuration(String songDuration) {
		this.songDuration = songDuration;
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
	public int getSongDelFlag() {
		return songDelFlag;
	}
	public void setSongDelFlag(int songDelFlag) {
		this.songDelFlag = songDelFlag;
	}
	public String displaySongsDetails() {
		// TODO Auto-generated method stub
		return String.format("%-30s%-30s%-30s\n", songId, songName, songDuration);
	}

	public void disp()
	{
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","Song ID","Song Name","Song Duration","Created By","Created On","Updated By","Updated On");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}
	@Override
	public String toString() {

		return String.format("%-30s%-30s%-30s%-30s%-30s%-30s%-30s", songId, songName, songDuration,createdBy,createdOn,updatedBy,updatedOn);

	}

}
