package com.sprintmanagement.utilities;

public enum MeetingStatusEnum {
//	Scheduled,Completed,cancelled,Rescheduled
	Completed("Completed"),Scheduled("Scheduled"),Rescheduled("Rescheduled"),cancelled("cancelled");
	
	String s;
	private MeetingStatusEnum(String s) {
		// TODO Auto-generated constructor stub
		this.s = s;
	}

}
