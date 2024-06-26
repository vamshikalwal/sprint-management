package com.sprintmanagement.dto;

import java.time.LocalDate;

import java.time.LocalTime;

import com.sprintmanagement.utilities.MeetingStatusEnum;
import com.sprintmanagement.utilities.MeetingTypeEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class MeetingsDTO {
	



	private int id;
	private String meetingLink;
	private LocalDate meetingDate;
	private LocalTime meetingTime;
	
	
	@Enumerated(EnumType.STRING) 
	private MeetingTypeEnum meetingType;
	

	private int sprintId;
	private String meetingPassword;
	private LocalDate createdOn;
	
	
	@Enumerated(EnumType.STRING)
	private MeetingStatusEnum status;

	private LocalDate updatedOn;
	private int meetingPlatformId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMeetingLink() {
		return meetingLink;
	}
	public void setMeetingLink(String meetingLink) {
		this.meetingLink = meetingLink;
	}
	public LocalDate getMeetingDate() {
		return meetingDate;
	}
	public void setMeetingDate(LocalDate meetingDate) {
		this.meetingDate = meetingDate;
	}
	public LocalTime getMeetingTime() {
		return meetingTime;
	}
	public void setMeetingTime(LocalTime meetingTime) {
		this.meetingTime = meetingTime;
	}
	public MeetingTypeEnum getMeetingType() {
		return meetingType;
	}
	public void setMeetingType(MeetingTypeEnum meetingType) {
		this.meetingType = meetingType;
	}
	public int getSprintId() {
		return sprintId;
	}
	public void setSprintId(int sprintId) {
		this.sprintId = sprintId;
	}
	public String getMeetingPassword() {
		return meetingPassword;
	}
	public void setMeetingPassword(String meetingPassword) {
		this.meetingPassword = meetingPassword;
	}
	public LocalDate getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}
	public MeetingStatusEnum getStatus() {
		return status;
	}
	public void setStatus(MeetingStatusEnum status) {
		this.status = status;
	}
	public LocalDate getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(LocalDate updatedOn) {
		this.updatedOn = updatedOn;
	}
	public int getMeetingPlatformId() {
		return meetingPlatformId;
	}
	public void setMeetingPlatformId(int meetingPlatformId) {
		this.meetingPlatformId = meetingPlatformId;
	}



	
	
	

}
