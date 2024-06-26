package com.sprintmanagement.services;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprintmanagement.dto.MeetingPlatformsDTO;
import com.sprintmanagement.entities.MeetingPlatforms;
import com.sprintmanagement.repository.MeetingPlatformsRepository;

@Service
public class MeetingsPlatformServiceImpl implements MeetingsPlatfromService{
	
	@Autowired
	private MeetingPlatformsRepository meetingPlatformsRepository;
	
//	--This Method performs, To get all meeting platforms present in the database--
	@Override
	public List<MeetingPlatformsDTO> getMeetingPlatforms() {
		Iterable<MeetingPlatforms> meetingLists = meetingPlatformsRepository.findAll();
		List<MeetingPlatformsDTO> meetingPlatformsList=new ArrayList<MeetingPlatformsDTO>();
 		for(MeetingPlatforms meetingsp : meetingLists)
 		{
 			MeetingPlatformsDTO meetingPlatformsDTO = new MeetingPlatformsDTO();
 			meetingPlatformsDTO.setId(meetingsp.getId());
 			meetingPlatformsDTO.setName(meetingsp.getName());
 			meetingPlatformsList.add(meetingPlatformsDTO);
 		}
 		return meetingPlatformsList;
		
	}


	
}
