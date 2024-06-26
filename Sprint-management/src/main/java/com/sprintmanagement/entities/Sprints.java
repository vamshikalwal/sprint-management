package com.sprintmanagement.entities;

import java.time.LocalDate;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="Sprints")
public class Sprints {
	@Id
	@Column(name = "Sprint_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sprintId;
	
	@OneToMany(mappedBy = "SprintId", targetEntity = Meetings.class)
	private List<Meetings> meetings;

	@Column(name="Sprint_Name")
	private String SprintName;
	@Column(name="Start_Date")
	private LocalDate StartDate;
	@Column(name="End_Date")
	private LocalDate EndDate;
	public int getSprintId() {
		return sprintId;
	}


	public void setSprintId(int sprintId) {
		this.sprintId = sprintId;
	}


	public List<Meetings> getMeetings() {
		return meetings;
	}


	public void setMeetings(List<Meetings> meetings) {
		this.meetings = meetings;
	}


	public String getSprintName() {
		return SprintName;
	}


	public void setSprintName(String sprintName) {
		SprintName = sprintName;
	}


	public LocalDate getStartDate() {
		return StartDate;
	}


	public void setStartDate(LocalDate startDate) {
		StartDate = startDate;
	}


	public LocalDate getEndDate() {
		return EndDate;
	}


	public void setEndDate(LocalDate endDate) {
		EndDate = endDate;
	}


	public int getProjectCode() {
		return ProjectCode;
	}


	public void setProjectCode(int projectCode) {
		ProjectCode = projectCode;
	}


	public LocalDate getCreatedOn() {
		return CreatedOn;
	}


	public void setCreatedOn(LocalDate createdOn) {
		CreatedOn = createdOn;
	}


	@Column(name="Project_Code")
	private int ProjectCode;
	@Column(name="Created_On")
	private LocalDate CreatedOn;
	
	
	@PostPersist
	public void updateSprintNameAfterPersist()
	{
		String formatedSprintIdString = String.format("%02d", this.sprintId);
		this.SprintName = "Sprint-"+formatedSprintIdString;
	}
	
	

	
	
	
	

}
