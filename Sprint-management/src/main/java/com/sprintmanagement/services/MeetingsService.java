package com.sprintmanagement.services;

import com.sprintmanagement.dto.MeetingsDTO;
import com.sprintmanagement.utilities.MeetingStatusUpdateFailedException;


public interface MeetingsService {
	String scheduleMeeting(MeetingsDTO meetingDTO);
	MeetingsDTO getMeetingById(int id);
	String rescheduleMeeting(int meetingId,MeetingsDTO meetingDTO) throws MeetingStatusUpdateFailedException;

}
