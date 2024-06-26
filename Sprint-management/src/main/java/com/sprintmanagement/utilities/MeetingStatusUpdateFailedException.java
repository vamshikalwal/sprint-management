package com.sprintmanagement.utilities;

public class MeetingStatusUpdateFailedException extends Exception{
	
	private String message;

	public MeetingStatusUpdateFailedException(String message) {
		super(message);
		this.message = message;
	}
	

}
