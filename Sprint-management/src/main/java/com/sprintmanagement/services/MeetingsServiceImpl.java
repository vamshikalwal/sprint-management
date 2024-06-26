package com.sprintmanagement.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprintmanagement.dto.MeetingsDTO;
import com.sprintmanagement.entities.MeetingPlatforms;
import com.sprintmanagement.entities.Meetings;
import com.sprintmanagement.entities.Sprints;
import com.sprintmanagement.repository.MeetingPlatformsRepository;
import com.sprintmanagement.repository.MeetingsRepository;
import com.sprintmanagement.repository.SprintRepository;
import com.sprintmanagement.utilities.MeetingStatusEnum;
import com.sprintmanagement.utilities.MeetingStatusUpdateFailedException;
import com.sprintmanagement.utilities.MeetingTypeEnum;

@Service
public class MeetingsServiceImpl implements MeetingsService{
	
	@Autowired
	private MeetingsRepository meetingsRepository;
	
	@Autowired
	private SprintRepository sprintRepository;
	
	@Autowired
	private MeetingPlatformsRepository meetingPlatformsRepository;
	
//	--This method is used to get a particular meeting details by id--
	@Override
	public MeetingsDTO getMeetingById(int id) {
		Optional<Meetings> optionalOfmeetings=meetingsRepository.findById(id);
		MeetingsDTO meetingsResponse=new MeetingsDTO();
		
	    if(optionalOfmeetings.isPresent()) {
	    	Meetings meetings = optionalOfmeetings.get();
			meetingsResponse.setId(meetings.getId());
			meetingsResponse.setMeetingLink(meetings.getMeetingLink());
			meetingsResponse.setMeetingDate(meetings.getMeetingDate());
			meetingsResponse.setMeetingTime(meetings.getMeetingTime());
			meetingsResponse.setMeetingType(meetings.getMeetingType());
				
			meetingsResponse.setSprintId(meetings.getSprintId());
			
			meetingsResponse.setMeetingPassword(meetings.getMeetingPassword());
			meetingsResponse.setCreatedOn(meetings.getCreatedOn());
			meetingsResponse.setStatus(meetings.getStatus());
			meetingsResponse.setUpdatedOn(meetings.getUpdatedOn());
			meetingsResponse.setMeetingPlatformId(meetings.getMeetingPlatformId());
			return meetingsResponse;
	    }
	    return null;
		
	}
	
//	--This method is used to reschedule a meeting--
	@Override
	public String rescheduleMeeting(int meetingId, MeetingsDTO meetingDTO) throws MeetingStatusUpdateFailedException {
		Optional<Meetings> optionalMeetings = meetingsRepository.findById(meetingId);
			if (optionalMeetings.isPresent())
			{
				
				if ("Completed".equals(optionalMeetings.get().getStatus().toString()))
				{
					throw new MeetingStatusUpdateFailedException("We cannot Reschedule a meeting which is already completed.");
				}
				
				Meetings existingMeeting = optionalMeetings.get();
				existingMeeting.setMeetingDate(meetingDTO.getMeetingDate());
				existingMeeting.setStatus(MeetingStatusEnum.Rescheduled);
				existingMeeting.setMeetingTime(meetingDTO.getMeetingTime());
				existingMeeting.setUpdatedOn(LocalDate.now());
				
				Meetings meetings = meetingsRepository.save(existingMeeting);
				if(meetings != null)
					return "success";
		
			}
				
			
			 return "fail";
			 
	}
	
	
//	--This method is used to schedule or create a meeting--
	@SuppressWarnings("unused")
	@Override
	public String scheduleMeeting(MeetingsDTO meetingDTO)
	{
		Optional<MeetingPlatforms> meetingPlatformsOptional = 
				meetingPlatformsRepository.findById(meetingDTO.getMeetingPlatformId());
		if (!meetingPlatformsOptional.isPresent())
		{
			return "fail";
		}
		Optional<Sprints> sprints = sprintRepository.findById(meetingDTO.getSprintId());		
		if (!sprints.isPresent())
		{
			return "fail";
		}

		Meetings meetingsObject = new Meetings();
		
		if ("DailyScrum".equals(meetingDTO.getMeetingType().toString()))
		{
			LocalDate startDate = sprints.get().getStartDate();
			LocalDate endDate = sprints.get().getEndDate();
			long days = ChronoUnit.DAYS.between(startDate, endDate);
			for (int i=0;i<=days;i++)
			{
				LocalDate currentDate = startDate.plusDays(i);
				
				if (currentDate.getDayOfWeek() == DayOfWeek.SATURDAY || currentDate.getDayOfWeek() == DayOfWeek.SUNDAY)
				{
					continue;
				}
				
				Meetings dailyMeetingsfor = new Meetings();
				dailyMeetingsfor.setMeetingLink(meetingDTO.getMeetingLink());
				dailyMeetingsfor.setMeetingDate(startDate.plusDays(i));
				dailyMeetingsfor.setMeetingTime(LocalTime.parse("00:15:00.000000"));
				dailyMeetingsfor.setMeetingType(MeetingTypeEnum.DailyScrum);
				dailyMeetingsfor.setSprintId(meetingDTO.getSprintId());
				dailyMeetingsfor.setMeetingPassword(meetingDTO.getMeetingPassword());
				dailyMeetingsfor.setCreatedOn(LocalDate.now());
				dailyMeetingsfor.setStatus(MeetingStatusEnum.Scheduled);
				dailyMeetingsfor.setUpdatedOn(null);
				dailyMeetingsfor.setMeetingPlatformId(meetingDTO.getMeetingPlatformId());
				
				meetingsObject = meetingsRepository.save(dailyMeetingsfor);
				
			}
			if (meetingsObject!=null)
			{
				return "success";
			}
			
		}

		else if ("SprintPlanning".equals(meetingDTO.getMeetingType().toString()))
		{	
			meetingsObject.setMeetingDate(sprints.get().getStartDate());
			meetingsObject.setSprintId(meetingDTO.getSprintId());
			
			meetingsObject.setMeetingType(MeetingTypeEnum.SprintPlanning);
		}
		
		else if ("SprintReview".equals(meetingDTO.getMeetingType().toString()) || 
				"SprintRetrospective".equals(meetingDTO.getMeetingType().toString()))
		{
			meetingsObject.setMeetingDate(sprints.get().getEndDate());
			meetingsObject.setSprintId(meetingDTO.getSprintId());
			if("SprintReview".equals(meetingDTO.getMeetingType().toString())){
				meetingsObject.setMeetingType(MeetingTypeEnum.SprintReview);
			}
			else {
				{
					meetingsObject.setMeetingType(MeetingTypeEnum.SprintRetrospective);
				}
			}
		}
		
		
		meetingsObject.setMeetingPassword(meetingDTO.getMeetingPassword());
		meetingsObject.setCreatedOn(LocalDate.now());
		meetingsObject.setStatus(MeetingStatusEnum.Scheduled);
		meetingsObject.setUpdatedOn(null);
		meetingsObject.setMeetingLink(meetingDTO.getMeetingLink());
		meetingsObject.setMeetingTime(meetingDTO.getMeetingTime());
		meetingsObject.setMeetingPlatformId(meetingDTO.getMeetingPlatformId());
		
		if(meetingsObject !=null) {
			meetingsRepository.save(meetingsObject);
			return "success";
		}
		else {
			return "fail";
		}
		
		
		
	}
	
	

}



