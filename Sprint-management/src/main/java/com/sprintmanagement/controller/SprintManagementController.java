package com.sprintmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprintmanagement.dto.MeetingPlatformsDTO;
import com.sprintmanagement.dto.MeetingsDTO;
import com.sprintmanagement.dto.SprintsDTO;
import com.sprintmanagement.dto.Status;
import com.sprintmanagement.services.MeetingsPlatfromService;
import com.sprintmanagement.services.MeetingsService;
import com.sprintmanagement.services.SprintService;
import com.sprintmanagement.utilities.MeetingStatusUpdateFailedException;

@RestController 
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class SprintManagementController {	
	@Autowired
	public MeetingsService meetingservice;
	
	@Autowired
	public SprintService sprintService;
	
	@Autowired
	public MeetingsPlatfromService meetingsPlatformService;
	
	//Sprint management - Endpoint - 1
	@GetMapping("/meetings/platform")
	public ResponseEntity<List<MeetingPlatformsDTO>> getAllMeetingPlatforms()
	{
		List<MeetingPlatformsDTO> responseList = meetingsPlatformService.getMeetingPlatforms();
		ResponseEntity<List<MeetingPlatformsDTO>> responseEntity = null;
		if(!responseList.isEmpty()) {
			responseEntity = new ResponseEntity<List<MeetingPlatformsDTO>>(responseList, HttpStatus.OK);
		}
		else {
			responseEntity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
			
	}
	
//	Sprint management - Endpoint - 2
	@PostMapping("/sprints/new")
	public ResponseEntity<?> addNewSprint(@RequestBody SprintsDTO sdto) {
		String responseString = sprintService.persistSprint(sdto);
		if (responseString.equals("success"))
		{
			Status status = new Status();
			status.setStatus(responseString);
			
			return new ResponseEntity<>(status,HttpStatus.CREATED);
			
		}
		else {
			return new ResponseEntity<>(responseString,HttpStatus.BAD_REQUEST);
		}
	}
	
//	Sprint management - Endpoint - 3
	@GetMapping("/sprints")
	public ResponseEntity<List<SprintsDTO>> getAllSprints()
	{
		List<SprintsDTO> responseList = sprintService.getAllSprints();
		ResponseEntity<List<SprintsDTO>> responseEntity = null;
		if(!responseList.isEmpty()) {
			responseEntity = new ResponseEntity<List<SprintsDTO>>(responseList, HttpStatus.OK);
		}
		else {
			responseEntity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
		
	}
	
//	Sprint management - Endpoint - 4
	@PostMapping("/meetings/new")
	public ResponseEntity<?> addNewMeeting(@RequestBody MeetingsDTO mdto) {
		String responseString = meetingservice.scheduleMeeting(mdto);
		if (responseString.equals("success"))
		{
			Status status = new Status();
			status.setStatus(responseString);
			return new ResponseEntity<>(status,HttpStatus.CREATED);
			
		}
		else {
			return new ResponseEntity<>(responseString,HttpStatus.BAD_REQUEST);
		}
	}
	
//	Sprint management - Endpoint - 5
	@GetMapping("/meetings/{meetingId}")
	public ResponseEntity<?> findMeetingById(@PathVariable int meetingId)
	{
		MeetingsDTO response =meetingservice.getMeetingById(meetingId);
		ResponseEntity<?> responseEntity = null;

		if(response!=null) {
			responseEntity= new ResponseEntity<MeetingsDTO>(response,HttpStatus.OK);
		}else {
			responseEntity =new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}

//	Sprint management - Endpoint - 6
	@PutMapping("/meetings/{meetingid}/reschedule")
	public ResponseEntity<String> rescheduleMeeting(@PathVariable("meetingid") int meetingId,@RequestBody MeetingsDTO meetingsDTO) throws MeetingStatusUpdateFailedException
	{
		
		String responseString = meetingservice.rescheduleMeeting(meetingId,meetingsDTO );
		if (responseString.equals("success"))
		{

			return new ResponseEntity<>(HttpStatus.CREATED);
			
		}
		else {
			return new ResponseEntity<>(responseString,HttpStatus.BAD_REQUEST);
		}
	}
		
}

	
	

