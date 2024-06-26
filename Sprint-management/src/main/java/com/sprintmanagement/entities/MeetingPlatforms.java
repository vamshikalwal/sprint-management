package com.sprintmanagement.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Meeting_Platforms")

public class MeetingPlatforms {
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public List<Meetings> getMeetings() {
		return meetings;
	}

	public void setMeetings(List<Meetings> meetings) {
		this.meetings = meetings;
	}

	@Id
	@Column (name="Id")
	private int Id;
	@Column (name="Name")
	private String Name;
	
	@OneToMany(mappedBy = "MeetingPlatformId", targetEntity = Meetings.class)
	private List<Meetings> meetings;
	
	
	
	

}
