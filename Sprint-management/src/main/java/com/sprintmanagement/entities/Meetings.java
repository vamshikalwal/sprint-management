package com.sprintmanagement.entities;

import java.time.LocalDate;
//import java.util.List;
import java.time.LocalTime;

import com.sprintmanagement.utilities.*;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Meetings")
public class Meetings {
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getMeetingLink() {
		return MeetingLink;
	}

	public void setMeetingLink(String meetingLink) {
		MeetingLink = meetingLink;
	}

	public LocalDate getMeetingDate() {
		return MeetingDate;
	}

	public void setMeetingDate(LocalDate meetingDate) {
		MeetingDate = meetingDate;
	}

	public LocalTime getMeetingTime() {
		return MeetingTime;
	}

	public void setMeetingTime(LocalTime meetingTime) {
		MeetingTime = meetingTime;
	}

	public MeetingTypeEnum getMeetingType() {
		return MeetingType;
	}

	public void setMeetingType(MeetingTypeEnum meetingType) {
		MeetingType = meetingType;
	}

	public int getSprintId() {
		return SprintId;
	}

	public void setSprintId(int sprintId) {
		SprintId = sprintId;
	}

	public String getMeetingPassword() {
		return MeetingPassword;
	}

	public void setMeetingPassword(String meetingPassword) {
		MeetingPassword = meetingPassword;
	}

	public LocalDate getCreatedOn() {
		return CreatedOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		CreatedOn = createdOn;
	}

	public MeetingStatusEnum getStatus() {
		return Status;
	}

	public void setStatus(MeetingStatusEnum status) {
		Status = status;
	}

	public LocalDate getUpdatedOn() {
		return UpdatedOn;
	}

	public void setUpdatedOn(LocalDate updatedOn) {
		UpdatedOn = updatedOn;
	}

	public int getMeetingPlatformId() {
		return MeetingPlatformId;
	}

	public void setMeetingPlatformId(int meetingPlatformId) {
		MeetingPlatformId = meetingPlatformId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="Id")
	private int Id;
	@Column (name="Meeting_Link")
	private String MeetingLink;
	@Column (name="Meeting_Date")
	private LocalDate MeetingDate;
	@Column (name="Meeting_Time")
	private LocalTime MeetingTime;
	
	@Enumerated(EnumType.STRING) @Column(name="Meeting_Type")
	private MeetingTypeEnum MeetingType;
	
	private int SprintId;
	
	@Column (name="Meeting_Password")
	private String MeetingPassword;
	@Column(name="Created_On")
	private LocalDate CreatedOn;
	
	
	@Enumerated(EnumType.STRING) @Column (name="Status")
	private MeetingStatusEnum Status;
	
	
	@Column(name="Updated_On")
	private LocalDate UpdatedOn;
	
	private  int MeetingPlatformId;
	

	
	

}
