package com.cognizant.sprintmanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.sprintmanagement.dto.MeetingPlatformsDTO;
import com.sprintmanagement.dto.MeetingsDTO;
import com.sprintmanagement.dto.SprintsDTO;
import com.sprintmanagement.dto.UserDTO;
import com.sprintmanagement.entities.MeetingPlatforms;
import com.sprintmanagement.entities.Meetings;
import com.sprintmanagement.entities.Sprints;
import com.sprintmanagement.entities.Users;
import com.sprintmanagement.main.SprintManagementApplication;
import com.sprintmanagement.repository.MeetingPlatformsRepository;
import com.sprintmanagement.repository.MeetingsRepository;
import com.sprintmanagement.repository.SprintRepository;
import com.sprintmanagement.repository.UserRepository;
import com.sprintmanagement.services.MeetingsPlatformServiceImpl;
import com.sprintmanagement.services.MeetingsServiceImpl;
import com.sprintmanagement.services.SprintServiceImpl;
import com.sprintmanagement.services.UserServiceImpl;
import com.sprintmanagement.utilities.MeetingStatusEnum;
import com.sprintmanagement.utilities.MeetingStatusUpdateFailedException;
import com.sprintmanagement.utilities.MeetingTypeEnum;

@SpringBootTest(classes = SprintManagementApplication.class)
public class TestServiceImpl {
	
	@Mock
	private SprintRepository sprintRepository;
	
	@Mock
	private MeetingsRepository meetingsRepository;
	
	@Mock
	private MeetingPlatformsRepository meetingPlatformsRepository;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	MeetingsServiceImpl meetingserviceimpl;
	
	@InjectMocks
	MeetingsPlatformServiceImpl meetingsPlatformServiceImpl;

	@InjectMocks
	SprintServiceImpl sprintServiceImpl;
	
	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetMeetingByIdPositive()
	{
		Meetings meetingData1= new Meetings();
		meetingData1.setId(2);
		meetingData1.setMeetingLink("xjnwnljfhre");
		LocalDate meetingDate1 = LocalDate.parse("2024-04-02");
		meetingData1.setMeetingDate(meetingDate1);
		LocalTime meetingTime = LocalTime.parse("10:30:00");
		meetingData1.setMeetingTime(meetingTime);
		meetingData1.setMeetingType(MeetingTypeEnum.SprintPlanning);
		
		Sprints sprints = new Sprints();
		sprints.setSprintId(2);
		
		meetingData1.setSprintId(2);		
		meetingData1.setMeetingPassword("meeting@1");
		LocalDate createdOn = LocalDate.parse("2024-04-01");
		meetingData1.setCreatedOn(createdOn);
		meetingData1.setStatus(MeetingStatusEnum.Rescheduled);
		LocalDate updatedOn = LocalDate.parse("2024-04-06");
		meetingData1.setUpdatedOn(updatedOn);
		
		MeetingPlatforms meetingPlatforms = new MeetingPlatforms();
		meetingPlatforms.setId(2);
		
		meetingData1.setMeetingPlatformId(2);
		
		Optional <Meetings> optionalofMeetingsOptional = Optional.of(meetingData1);
		when(meetingsRepository.findById(meetingData1.getId())).thenReturn(optionalofMeetingsOptional);
		System.out.println(optionalofMeetingsOptional);
		MeetingsDTO meetingsDtoresult = meetingserviceimpl.getMeetingById(2);
		assertNotNull(meetingsDtoresult);	
		
	}
	
	@Test
	public void testGetMeetingByIdNegative()
	{
		MeetingsRepository meetingsRepository = mock(MeetingsRepository.class);
		
		when(meetingsRepository.findById(10001)).thenReturn(null);
		MeetingsDTO resObject = meetingserviceimpl.getMeetingById(10001);
		assertNull(resObject);
	}
	
	@Test
	public void testRescheduleMeetingPositive() throws MeetingStatusUpdateFailedException
	{
			Meetings meetingData1= new Meetings();
			meetingData1.setId(3);
			meetingData1.setMeetingLink("xcvbnmz");
			LocalDate meetingDate = LocalDate.parse("2024-04-02");
			meetingData1.setMeetingDate(meetingDate);
			LocalTime meetingTime = LocalTime.parse("10:30:00");
			meetingData1.setMeetingTime(meetingTime);
			meetingData1.setMeetingType(MeetingTypeEnum.SprintReview);
			
			Sprints sprints = new Sprints();
			sprints.setSprintId(3);
			
			meetingData1.setSprintId(1);		
			meetingData1.setMeetingPassword("meetingTest@1");
			LocalDate createdOn = LocalDate.parse("2024-04-01");
			meetingData1.setCreatedOn(createdOn);
			meetingData1.setStatus(MeetingStatusEnum.Scheduled);
			LocalDate updateOn = LocalDate.parse("2024-04-05");
			meetingData1.setUpdatedOn(updateOn);
			
			MeetingPlatforms meetingPlatforms = new MeetingPlatforms();
			meetingPlatforms.setId(3);
			Optional<Meetings> optionalOfMeetingsOptional = Optional.of(meetingData1);
			when(meetingsRepository.findById(3)).thenReturn(optionalOfMeetingsOptional);
			
			//Reschedule Update
			Meetings meetingUpdate= new Meetings();
			meetingUpdate.setId(3);
			meetingUpdate.setMeetingLink("xcvbnmz");
			LocalDate meetingUpdatedata1 = LocalDate.parse("2024-04-02");
			meetingUpdate.setMeetingDate(meetingUpdatedata1);
			LocalTime meetingTime1 = LocalTime.parse("10:30:00");
			meetingUpdate.setMeetingTime(meetingTime1);
			meetingUpdate.setMeetingType(MeetingTypeEnum.SprintReview);
			
			Sprints sprints1 = new Sprints();
			sprints1.setSprintId(3);
			
			meetingUpdate.setSprintId(2);		
			meetingUpdate.setMeetingPassword("meetingTest@1");
			LocalDate createdOn1 = LocalDate.parse("2024-04-01");
			meetingUpdate.setCreatedOn(createdOn1);
			meetingUpdate.setStatus(MeetingStatusEnum.Scheduled);
			LocalDate meetingUpdated = LocalDate.parse("2024-04-02");
			meetingUpdate.setUpdatedOn(meetingUpdated);
			
			MeetingPlatforms meetingPlatforms1 = new MeetingPlatforms();
			meetingPlatforms1.setId(3);
			meetingUpdate.setMeetingPlatformId(3);
			
			MeetingsDTO meetingsDTO = new MeetingsDTO();
			meetingsDTO.setMeetingDate(meetingUpdate.getMeetingDate());
			when(meetingsRepository.save(meetingData1)).thenReturn(meetingUpdate);
			String actual = meetingserviceimpl.rescheduleMeeting(3,meetingsDTO);
			assertEquals("success", actual);
	}
	
	@Test
	public void testRescheduleMeetingNegative() throws MeetingStatusUpdateFailedException {
		Meetings meetingData1= new Meetings();
		meetingData1.setId(3);
		meetingData1.setMeetingLink("xcvbnmz");
		LocalDate meetingDate = LocalDate.parse("2024-04-02");
		meetingData1.setMeetingDate(meetingDate);
		LocalTime meetingTime = LocalTime.parse("10:30:00");
		meetingData1.setMeetingTime(meetingTime);
		meetingData1.setMeetingType(MeetingTypeEnum.SprintReview);
		
		Sprints sprints = new Sprints();
		sprints.setSprintId(3);
		
		meetingData1.setSprintId(3);		
		meetingData1.setMeetingPassword("meetingTest@1");
		LocalDate createdOn = LocalDate.parse("2024-04-01");
		meetingData1.setCreatedOn(createdOn);
		meetingData1.setStatus(MeetingStatusEnum.Scheduled);
		LocalDate meetingUpdate1 = LocalDate.parse("2024-04-02");
		meetingData1.setUpdatedOn(meetingUpdate1);
		
		MeetingPlatforms meetingPlatforms = new MeetingPlatforms();
		meetingPlatforms.setId(3);
		Optional<Meetings> optionalOfMeetingsOptional = Optional.of(meetingData1);
		when(meetingsRepository.findById(3)).thenReturn(optionalOfMeetingsOptional);
		
		//Reschedule Update
		Meetings meetingUpdate= new Meetings();
		meetingUpdate.setId(3);
		meetingUpdate.setMeetingLink("xcvbnmz");
		LocalDate meetingDate1 = LocalDate.parse("2024-04-02");
		meetingUpdate.setMeetingDate(meetingDate1);
		LocalTime meetingTime1 = LocalTime.parse("10:30:00");
		meetingUpdate.setMeetingTime(meetingTime1);
		meetingUpdate.setMeetingType(MeetingTypeEnum.SprintReview);
		
		Sprints sprints1 = new Sprints();
		sprints1.setSprintId(3);
		
		meetingUpdate.setSprintId(3);		
		meetingUpdate.setMeetingPassword("meetingTest@1");
		LocalDate createdOn1 = LocalDate.parse("2024-04-01");
		meetingUpdate.setCreatedOn(createdOn1);
		meetingUpdate.setStatus(MeetingStatusEnum.Scheduled);
		LocalDate updateon1 = LocalDate.parse("2024-04-01");
		meetingUpdate.setUpdatedOn(updateon1);
		
		MeetingPlatforms meetingPlatforms1 = new MeetingPlatforms();
		meetingPlatforms1.setId(3);
		meetingUpdate.setMeetingPlatformId(3);
		
		MeetingsDTO meetingsDTO = new MeetingsDTO();
		meetingsDTO.setMeetingDate(meetingUpdate.getMeetingDate());

		
		
		//negative testing
		when(meetingsRepository.save(meetingData1)).thenReturn(null);
		String actual = meetingserviceimpl.rescheduleMeeting(4,meetingsDTO);
		assertEquals("fail", actual); // fail // assertTrue(true);
	}
	
	@Test
	public void testRescheduleMeetingException()
	{
		try {
			Meetings meetingData1= new Meetings();
			meetingData1.setId(3);
			meetingData1.setMeetingLink("xcvbnmz");
			LocalDate meetingDate = LocalDate.parse("2024-04-02");
			meetingData1.setMeetingDate(meetingDate);
			LocalTime meetingTime = LocalTime.parse("10:30:00");
			meetingData1.setMeetingTime(meetingTime);
			meetingData1.setMeetingType(MeetingTypeEnum.SprintReview);
			
			Sprints sprints = new Sprints();
			sprints.setSprintId(3);
			
			meetingData1.setSprintId(1);		
			meetingData1.setMeetingPassword("meetingTest@1");
			LocalDate createdOn = LocalDate.parse("2024-04-01");
			meetingData1.setCreatedOn(createdOn);
			meetingData1.setStatus(MeetingStatusEnum.Completed);
			LocalDate meetingUpdate1 = LocalDate.parse("2024-04-02");
			meetingData1.setUpdatedOn(meetingUpdate1);
			
			MeetingPlatforms meetingPlatforms = new MeetingPlatforms();
			meetingPlatforms.setId(3);
			Optional<Meetings> optionalOfMeetingsOptional = Optional.of(meetingData1);
			when(meetingsRepository.findById(3)).thenReturn(optionalOfMeetingsOptional);
			
			//Reschedule Update
			Meetings meetingUpdate= new Meetings();
			meetingUpdate.setId(3);
			meetingUpdate.setMeetingLink("xcvbnmz");
			LocalDate meetingDate1 = LocalDate.parse("2024-04-02");
			meetingUpdate.setMeetingDate(meetingDate1);
			LocalTime meetingTime1 = LocalTime.parse("10:30:00");
			meetingUpdate.setMeetingTime(meetingTime1);
			meetingUpdate.setMeetingType(MeetingTypeEnum.SprintReview);
			
			Sprints sprints1 = new Sprints();
			sprints1.setSprintId(3);
			
			meetingUpdate.setSprintId(3);		
			meetingUpdate.setMeetingPassword("meetingTest@1");
			LocalDate createdOn1 = LocalDate.parse("2024-04-01");
			meetingUpdate.setCreatedOn(createdOn1);
			meetingUpdate.setStatus(MeetingStatusEnum.Completed);
			LocalDate updateon1 = LocalDate.parse("2024-04-01");
			meetingUpdate.setUpdatedOn(updateon1);
			
			MeetingPlatforms meetingPlatforms1 = new MeetingPlatforms();
			meetingPlatforms1.setId(3);
			meetingUpdate.setMeetingPlatformId(3);
			MeetingsDTO meetingsDTO = new MeetingsDTO();
			//meetingsDTO.setId(3);
			meetingsDTO.setMeetingDate(meetingUpdate.getMeetingDate());
			
			//error testing
			when(meetingsRepository.save(meetingData1)).thenReturn(meetingUpdate);
			meetingserviceimpl.rescheduleMeeting(3,meetingsDTO);
			assertTrue(false);
		}
		catch (Exception e) {
			assertTrue(true);
		}
		
	}
	
	@Test
	public void testReturnMeetingPlatformsListPositive()
	{
		MeetingPlatforms meetingPlatformsData1= new MeetingPlatforms();
		meetingPlatformsData1.setId(1);
		meetingPlatformsData1.setName("Teju");
		
		MeetingPlatforms meetingPlatformsData2= new MeetingPlatforms();
		meetingPlatformsData2.setId(2);
		meetingPlatformsData2.setName("Jaanu");
		
		List<MeetingPlatforms> meetingPlatformsDataList= new ArrayList<MeetingPlatforms>();
		meetingPlatformsDataList.add(meetingPlatformsData1);
		meetingPlatformsDataList.add(meetingPlatformsData2);
		
		Mockito.when(meetingPlatformsRepository.findAll()).thenReturn(meetingPlatformsDataList);
		
		List<MeetingPlatformsDTO> meetingplatformsDtoList =meetingsPlatformServiceImpl.getMeetingPlatforms();
		
		assertTrue(!meetingplatformsDtoList.isEmpty());
		
	}
	
	@Test
	public void testReturnMeetingPlatformsListNegative()
	{
		List<MeetingPlatforms> meetingplatformsList= new ArrayList<MeetingPlatforms>();
		
		Mockito.when(meetingPlatformsRepository.findAll()).thenReturn(meetingplatformsList);
		
		List<MeetingPlatformsDTO> meetingplatformsDtoList =meetingsPlatformServiceImpl.getMeetingPlatforms();
		
		assertTrue(meetingplatformsDtoList.isEmpty());
		
	}
	
	@Test
	public void testSprintsListPositive()
	{
		
		Sprints sprintsData1=new Sprints();
		Optional<Sprints> sprintOptional1=Optional.of(sprintsData1);
		
		sprintsData1.setSprintId(2);
		sprintsData1.setSprintName("Shubham");
		LocalDate startDate1 = LocalDate.parse("2024-04-02");
		sprintsData1.setStartDate(startDate1);
		LocalDate endDate1 = LocalDate.parse("2024-04-30");
		sprintsData1.setEndDate(endDate1);
		sprintsData1.setProjectCode(12);
		LocalDate createdOn1 = LocalDate.parse("2024-04-02");
		sprintsData1.setCreatedOn(createdOn1);
		
		Sprints sprintsData2=new Sprints();
		Optional<Sprints> sprintOptional2=Optional.of(sprintsData2);
		
		sprintsData2.setSprintId(3);
		sprintsData2.setSprintName("Malik");
		LocalDate startDate2 = LocalDate.parse("2024-05-01");
		sprintsData2.setStartDate(startDate2);
		LocalDate endDate2 = LocalDate.parse("2024-05-29");
		sprintsData2.setEndDate(endDate2);
		sprintsData2.setProjectCode(15);
		LocalDate createdOn2 = LocalDate.parse("2024-05-01");
		sprintsData2.setCreatedOn(createdOn2);
		
		when(sprintRepository.save(any())).thenReturn(sprintsData1,sprintsData2);
		
		List<Sprints> sprintDataList = new ArrayList<Sprints>();
		sprintDataList.add(sprintsData1);
		sprintDataList.add(sprintsData2);
		
		Mockito.when(sprintRepository.findAll()).thenReturn(sprintDataList);
		
		List<SprintsDTO> sprintDataDtoList = sprintServiceImpl.getAllSprints();
		assertTrue(!sprintDataDtoList.isEmpty());	
	}
	
	@Test
	public void testSprintsListNegative()
	{
		List<Sprints> sprintDataList = new ArrayList<Sprints>();
		Mockito.when(sprintRepository.findAll()).thenReturn(sprintDataList);
		List<SprintsDTO> sprintDataDtoList = sprintServiceImpl.getAllSprints();
		
		assertTrue(sprintDataDtoList.isEmpty());	
	}
	

	@Test
	public void testPersistSprintPositive()
	{
		Sprints sprints = Mockito.mock(Sprints.class);
		SprintsDTO sprintsDataDto = new SprintsDTO();
		sprintsDataDto.setSprintId(2);
		sprintsDataDto.setSprintName("Sai Lalith");
		sprintsDataDto.setStartDate(LocalDate.parse("2024-04-01"));	
		sprintsDataDto.setEndDate(LocalDate.parse("2024-04-20"));
		sprintsDataDto.setProjectCode(222);
		sprintsDataDto.setCreatedOn(LocalDate.parse("2024-04-01"));
		when(sprintRepository.save(Mockito.any())).thenReturn(sprints);
		
		
		String actual=sprintServiceImpl.persistSprint(sprintsDataDto);
//		when(sprintRepository.findById(2)).thenReturn(Optional.empty());
		assertEquals("success", actual);
		
	}
	
	
	@Test
	public void testPersistSprintNegative()
	{
		Sprints sprints = Mockito.mock(Sprints.class);
		SprintsDTO sprintsDataDto = new SprintsDTO();
		sprintsDataDto.setSprintId(2);
		sprintsDataDto.setSprintName("Sai Lalith");
		LocalDate startDate = LocalDate.parse("2024-04-02");
		sprintsDataDto.setStartDate(startDate);
		LocalDate endDate = LocalDate.parse("2024-04-10");
		sprintsDataDto.setEndDate(endDate);
		sprintsDataDto.setProjectCode(222);
		LocalDate createdOn = LocalDate.parse("2024-04-01");
		sprintsDataDto.setCreatedOn(createdOn);
		String actual=sprintServiceImpl.persistSprint(sprintsDataDto);
		assertEquals("fail", actual);
		
	}
	

	@Test
	public void testPersistStartDateEndDate()
	{

		
			SprintsDTO sprintsDataDto = new SprintsDTO();
			sprintsDataDto.setSprintId(2);
			sprintsDataDto.setSprintName("Sai Lalith");
			LocalDate startDate = null;
			sprintsDataDto.setStartDate(startDate);
			LocalDate endDate = null;
			sprintsDataDto.setEndDate(endDate);
			sprintsDataDto.setProjectCode(222);
			LocalDate createdOn = LocalDate.parse("2024-04-01");
			sprintsDataDto.setCreatedOn(createdOn);
			
			Optional<Sprints> o = Optional.empty();
			when(sprintRepository.findById(any())).thenReturn(o);
			
			String string = sprintServiceImpl.persistSprint(sprintsDataDto);
			assertEquals("fail", string);
			
	
		
		
	}
	
	
	@Test
	public void testScheduleMeetingPositiveSprintPlanning()
	{
		
		Meetings meetingsData = new Meetings();
		meetingsData.setId(1);
		meetingsData.setMeetingLink("ssdvbnmz");
		LocalDate meetingDate1 = LocalDate.parse("2024-04-02");
		meetingsData.setMeetingDate(meetingDate1);
		LocalTime meetingTime1 = LocalTime.parse("10:30:00");
		meetingsData.setMeetingTime(meetingTime1);
		meetingsData.setMeetingType(MeetingTypeEnum.SprintPlanning);
		
		Sprints sprints = new Sprints();
		sprints.setSprintId(1);
		
		Optional<Sprints> optionalOfSprints=Optional.of(sprints);
		
		when(sprintRepository.findById(1)).thenReturn(optionalOfSprints);
		
		meetingsData.setMeetingPassword("meetingScheduleTest@1");
		LocalDate createdOn1 = LocalDate.parse("2024-04-01");
		meetingsData.setCreatedOn(createdOn1);
		meetingsData.setStatus(MeetingStatusEnum.Scheduled);
		LocalDate updateOn1 = LocalDate.parse("2024-04-07");
		meetingsData.setUpdatedOn(updateOn1);
		
		MeetingPlatforms meetingPlatforms = new MeetingPlatforms();
		meetingPlatforms.setId(1);
		Optional<MeetingPlatforms> optionalOfMp=Optional.of(meetingPlatforms);
		when(meetingPlatformsRepository.findById(1)).thenReturn(optionalOfMp);
		
		
		MeetingsDTO meetingsDataDto = new MeetingsDTO(); 
		meetingsDataDto.setId(1);
		meetingsDataDto.setMeetingLink("ssdvbnmz");
		LocalDate meetingDate2 = LocalDate.parse("2024-04-02");
		meetingsDataDto.setMeetingDate(meetingDate2);
		LocalTime meetingTime2 = LocalTime.parse("10:30:00");
		meetingsDataDto.setMeetingTime(meetingTime2);
		meetingsDataDto.setMeetingType(MeetingTypeEnum.SprintPlanning);
		meetingsDataDto.setSprintId(1);		
		meetingsDataDto.setMeetingPassword("meetingScheduleTest@1");
		LocalDate createdOn2 = LocalDate.parse("2024-04-01");
		meetingsDataDto.setCreatedOn(createdOn2);
		meetingsDataDto.setStatus(MeetingStatusEnum.Scheduled);
		LocalDate updateOn2 = LocalDate.parse("2024-04-06");
		meetingsDataDto.setUpdatedOn(updateOn2);
		meetingsDataDto.setMeetingPlatformId(1);
		
		when(meetingsRepository.save(Mockito.any())).thenReturn(meetingsData);
		String scheduleActual = meetingserviceimpl.scheduleMeeting(meetingsDataDto);
		assertEquals("success",scheduleActual);
	}
	
	@Test
	public void testScheduleMeetingPositiveDailyScrum()
	{
		
		Meetings meetingsData = new Meetings();
		meetingsData.setId(1);
		meetingsData.setMeetingLink("ssdvbnmz");
		LocalDate meetingDate1 = LocalDate.parse("2024-04-02");
		meetingsData.setMeetingDate(meetingDate1);
		LocalTime meetingTime1 = LocalTime.parse("10:30:00");
		meetingsData.setMeetingTime(meetingTime1);
		meetingsData.setMeetingType(MeetingTypeEnum.DailyScrum);
		
		Sprints sprints = new Sprints();
		sprints.setSprintId(1);
		sprints.setStartDate(LocalDate.parse("2024-04-01"));
		sprints.setEndDate(LocalDate.parse("2024-04-04"));
		
		Optional<Sprints> optionalOfSprints=Optional.of(sprints);
		
		when(sprintRepository.findById(1)).thenReturn(optionalOfSprints);
		
		meetingsData.setMeetingPassword("meetingScheduleTest@1");
		LocalDate createdOn1 = LocalDate.parse("2024-04-01");
		meetingsData.setCreatedOn(createdOn1);
		meetingsData.setStatus(MeetingStatusEnum.Scheduled);
		LocalDate updateOn1 = LocalDate.parse("2024-04-07");
		meetingsData.setUpdatedOn(updateOn1);
		
		MeetingPlatforms meetingPlatforms = new MeetingPlatforms();
		meetingPlatforms.setId(1);
		Optional<MeetingPlatforms> optionalOfMp=Optional.of(meetingPlatforms);
		when(meetingPlatformsRepository.findById(1)).thenReturn(optionalOfMp);
		
		
		MeetingsDTO meetingsDataDto = new MeetingsDTO(); 
		meetingsDataDto.setId(1);
		meetingsDataDto.setMeetingLink("ssdvbnmz");
		LocalDate meetingDate2 = LocalDate.parse("2024-04-02");
		meetingsDataDto.setMeetingDate(meetingDate2);
		LocalTime meetingTime2 = LocalTime.parse("10:30:00");
		meetingsDataDto.setMeetingTime(meetingTime2);
		meetingsDataDto.setMeetingType(MeetingTypeEnum.DailyScrum);
		meetingsDataDto.setSprintId(1);		
		meetingsDataDto.setMeetingPassword("meetingScheduleTest@1");
		LocalDate createdOn2 = LocalDate.parse("2024-04-01");
		meetingsDataDto.setCreatedOn(createdOn2);
		meetingsDataDto.setStatus(MeetingStatusEnum.Scheduled);
		LocalDate updateOn2 = LocalDate.parse("2024-04-06");
		meetingsDataDto.setUpdatedOn(updateOn2);
		meetingsDataDto.setMeetingPlatformId(1);
		
		when(meetingsRepository.save(Mockito.any())).thenReturn(meetingsData);
		String scheduleActual = meetingserviceimpl.scheduleMeeting(meetingsDataDto);
		assertEquals("success",scheduleActual);
	}
	
	@Test
	public void testScheduleMeetingSprintReviewSprintRetrospective()
	{
		Meetings meetingsData = new Meetings();
		meetingsData.setId(1);
		meetingsData.setMeetingLink("ssdvbnmz");
		LocalDate meetingDate1 = LocalDate.parse("2024-04-02");
		meetingsData.setMeetingDate(meetingDate1);
		LocalTime meetingTime1 = LocalTime.parse("10:30:00");
		meetingsData.setMeetingTime(meetingTime1);
		meetingsData.setMeetingType(MeetingTypeEnum.SprintReview);
		
		Sprints sprints = new Sprints();
		sprints.setSprintId(1);
		
		Optional<Sprints> optionalOfSprints=Optional.of(sprints);
		
		when(sprintRepository.findById(1)).thenReturn(optionalOfSprints);
		
		meetingsData.setMeetingPassword("meetingScheduleTest@1");
		LocalDate createdOn1 = LocalDate.parse("2024-04-01");
		meetingsData.setCreatedOn(createdOn1);
		meetingsData.setStatus(MeetingStatusEnum.Scheduled);
		LocalDate updateOn1 = LocalDate.parse("2024-04-07");
		meetingsData.setUpdatedOn(updateOn1);
		
		MeetingPlatforms meetingPlatforms = new MeetingPlatforms();
		meetingPlatforms.setId(1);
		Optional<MeetingPlatforms> optionalOfMp=Optional.of(meetingPlatforms);
		when(meetingPlatformsRepository.findById(1)).thenReturn(optionalOfMp);
		
		
		MeetingsDTO meetingsDataDto = new MeetingsDTO(); 
		meetingsDataDto.setId(1);
		meetingsDataDto.setMeetingLink("ssdvbnmz");
		LocalDate meetingDate2 = LocalDate.parse("2024-04-02");
		meetingsDataDto.setMeetingDate(meetingDate2);
		LocalTime meetingTime2 = LocalTime.parse("10:30:00");
		meetingsDataDto.setMeetingTime(meetingTime2);
		meetingsDataDto.setMeetingType(MeetingTypeEnum.SprintReview);
		meetingsDataDto.setSprintId(1);		
		meetingsDataDto.setMeetingPassword("meetingScheduleTest@1");
		LocalDate createdOn2 = LocalDate.parse("2024-04-01");
		meetingsDataDto.setCreatedOn(createdOn2);
		meetingsDataDto.setStatus(MeetingStatusEnum.Scheduled);
		LocalDate updateOn2 = LocalDate.parse("2024-04-06");
		meetingsDataDto.setUpdatedOn(updateOn2);
		meetingsDataDto.setMeetingPlatformId(1);
		
		when(meetingsRepository.save(Mockito.any())).thenReturn(meetingsData);
		String scheduleActual = meetingserviceimpl.scheduleMeeting(meetingsDataDto);
		assertEquals("success",scheduleActual);
	}
	
	
	@Test
	public void testScheduleMeetingNegative()
	{
//
		Meetings meetingsData = new Meetings();
		meetingsData.setId(1);
		meetingsData.setMeetingLink("ssdvbnmz");
		LocalDate meetingDate1 = LocalDate.parse("2024-04-02");
		meetingsData.setMeetingDate(meetingDate1);
		LocalTime meetingTime1 = LocalTime.parse("10:30:00");
		meetingsData.setMeetingTime(meetingTime1);
		meetingsData.setMeetingType(MeetingTypeEnum.SprintReview);
		
		Sprints sprints = new Sprints();
		sprints.setSprintId(1);
		
		Optional<Sprints> optionalOfSprints=Optional.of(sprints);
		
		when(sprintRepository.findById(1)).thenReturn(optionalOfSprints);
		
		meetingsData.setMeetingPassword("meetingScheduleTest@1");
		LocalDate createdOn1 = LocalDate.parse("2024-04-01");
		meetingsData.setCreatedOn(createdOn1);
		meetingsData.setStatus(MeetingStatusEnum.Scheduled);
		LocalDate updateOn1 = LocalDate.parse("2024-04-07");
		meetingsData.setUpdatedOn(updateOn1);
		
		MeetingPlatforms meetingPlatforms = new MeetingPlatforms();
		meetingPlatforms.setId(1);
		Optional<MeetingPlatforms> optionalOfMp=Optional.of(meetingPlatforms);
		when(meetingPlatformsRepository.findById(1)).thenReturn(optionalOfMp);
		
		
		MeetingsDTO meetingsDataDto = new MeetingsDTO(); 
		meetingsDataDto.setMeetingPlatformId(1);
		
		when(meetingsRepository.save(Mockito.any())).thenReturn(null);
		String scheduleActual = meetingserviceimpl.scheduleMeeting(meetingsDataDto);
		assertEquals("fail",scheduleActual);
	}
	
//	@Test
//	public void testScheduleMeetingIdNotPresentNegative()
//	{
//		MeetingsDTO meetingsDataDto = new MeetingsDTO(); 
//		meetingsDataDto.setId(4);
//		meetingsDataDto.setMeetingLink("ssdvbnmz");
//		LocalDate meetingDate = LocalDate.parse("2024-04-02");
//		meetingsDataDto.setMeetingDate(meetingDate);
//		LocalTime meetingTime = LocalTime.parse("10:30:00");
//		meetingsDataDto.setMeetingTime(meetingTime);
//		meetingsDataDto.setMeetingType(MeetingTypeEnum.SprintReview);
//		meetingsDataDto.setSprintId(23);		
//		meetingsDataDto.setMeetingPassword("meetingScheduleTest@1");
//		meetingsDataDto.setCreatedOn(LocalDate.now());
//		meetingsDataDto.setStatus(MeetingStatusEnum.Scheduled);
//		LocalDate updateOn = LocalDate.parse("2024-04-05");
//		meetingsDataDto.setUpdatedOn(updateOn);
//		meetingsDataDto.setMeetingPlatformId(2);
//		
//		when(meetingsRepository.save(Mockito.any())).thenReturn(meetingsDataDto);
//
//		String scheduleActual = meetingserviceimpl.scheduleMeeting(meetingsDataDto);
//		assertEquals("Meeting Platform id is not present in database",scheduleActual);
//	}
//	
	@Test
	public void testScheduleSprintIdNotPresentNegative()
	{
		
		
		Meetings meetingsData = new Meetings();
		meetingsData.setId(1);
		meetingsData.setMeetingLink("ssdvbnmz");
		LocalDate meetingDate1 = LocalDate.parse("2024-04-02");
		meetingsData.setMeetingDate(meetingDate1);
		LocalTime meetingTime1 = LocalTime.parse("10:30:00");
		meetingsData.setMeetingTime(meetingTime1);
		meetingsData.setMeetingType(MeetingTypeEnum.SprintPlanning);
		
		Sprints sprints = new Sprints();
		sprints.setSprintId(1);
		
		Optional<Sprints> optionalOfSprints=Optional.of(sprints);
		
		when(sprintRepository.findById(1)).thenReturn(optionalOfSprints);
		
		meetingsData.setMeetingPassword("meetingScheduleTest@1");
		LocalDate createdOn1 = LocalDate.parse("2024-04-01");
		meetingsData.setCreatedOn(createdOn1);
		meetingsData.setStatus(MeetingStatusEnum.Scheduled);
		LocalDate updateOn1 = LocalDate.parse("2024-04-07");
		meetingsData.setUpdatedOn(updateOn1);
		
		MeetingPlatforms meetingPlatforms = new MeetingPlatforms();
		meetingPlatforms.setId(1);
		Optional<MeetingPlatforms> optionalOfMp=Optional.of(meetingPlatforms);
		when(meetingPlatformsRepository.findById(1)).thenReturn(optionalOfMp);
		
		
		MeetingsDTO meetingsDataDto = new MeetingsDTO(); 
		meetingsDataDto.setId(1);
		meetingsDataDto.setMeetingLink("ssdvbnmz");
		LocalDate meetingDate2 = LocalDate.parse("2024-04-02");
		meetingsDataDto.setMeetingDate(meetingDate2);
		LocalTime meetingTime2 = LocalTime.parse("10:30:00");
		meetingsDataDto.setMeetingTime(meetingTime2);
		meetingsDataDto.setMeetingType(MeetingTypeEnum.SprintPlanning);
		meetingsDataDto.setSprintId(32);		
		meetingsDataDto.setMeetingPassword("meetingScheduleTest@1");
		LocalDate createdOn2 = LocalDate.parse("2024-04-01");
		meetingsDataDto.setCreatedOn(createdOn2);
		meetingsDataDto.setStatus(MeetingStatusEnum.Scheduled);
		LocalDate updateOn2 = LocalDate.parse("2024-04-06");
		meetingsDataDto.setUpdatedOn(updateOn2);
		meetingsDataDto.setMeetingPlatformId(1);
		
		when(meetingsRepository.save(Mockito.any())).thenReturn(meetingsData);
		String scheduleActual = meetingserviceimpl.scheduleMeeting(meetingsDataDto);
		assertEquals("fail",scheduleActual);}
	
	
//	@Test
//	public void TestListOfUsers()
//	{
//		UserRepository mockServiceRepository = mock(UserRepository.class);
//		
//		Users user1 = new Users();
//		user1.setUserName("Admin");
//		user1.setPassword("Password");
//		user1.setRole("Admin");
//		
//		Users user2 = new Users();
//		user2.setUserName("Admin");
//		user2.setPassword("Password");
//		user2.setRole("Admin");
//		
//		List<Users> testUsers=List.of(user1,user2);
//		when(mockServiceRepository.findAll()).thenReturn(testUsers);
//		List<Users> resultList = (List<Users>) mockServiceRepository.findAll();
//		assertEquals(testUsers, resultList);
//	}
	
//	@Test
//	public void TestEmptyListOfUsers()
//	{
//		UserRepository mockServiceRepository = mock(UserRepository.class);
//		
//		when(mockServiceRepository.findAll()).thenReturn(List.of());
//		List<Users> resultList = (List<Users>) mockServiceRepository.findAll();
//		assertTrue(resultList.isEmpty());
//	}
	
	@Test
	public void testAuthenticateUsersPositive() {
		UserRepository mockServiceRepository = mock(UserRepository.class);
		
		Users user1 = new Users();
		user1.setUserName("Admin");
		user1.setPassword("Password");
		user1.setRole("Admin");
		
		Users user2 = new Users();
		user2.setUserName("Admin");
		user2.setPassword("Password");
		user2.setRole("Admin");
		
		List<Users> testUsers=List.of(user1,user2);
		when(mockServiceRepository.findAll()).thenReturn(testUsers);
		
		UserDTO userDTO = userServiceImpl.authenticateUser("Admin", "Password");
		System.out.println(user1.getUserName() + userDTO.getUserName());
		assertNotNull(userDTO);
		
	}
	
	@Test
	public void testAuthenticateUsersNegative() {
		UserRepository mockServiceRepository = mock(UserRepository.class);
		
		Users user1 = new Users();
		user1.setUserName("Admin");
		user1.setPassword("Password");
		user1.setRole("Admin");
		
		Users user2 = new Users();
		user2.setUserName("Admin");
		user2.setPassword("Password");
		user2.setRole("Admin");
		
		List<Users> testUsers=List.of(user1,user2);
		when(mockServiceRepository.findAll()).thenReturn(testUsers);
		
		UserDTO userDTO = userServiceImpl.authenticateUser("Adn", "Paword");
		
		assertNotNull(userDTO);
		assertNotEquals(user1.getUserName(), userDTO.getUserName());
		assertNotEquals(user1.getPassword(), userDTO.getPassword());
		assertNotEquals(user1.getPassword(), userDTO.getRole());
		
	}

	@Test
	public void testAuthenticateUsersWhenListIsEmpty() {
		UserRepository mockServiceRepository = mock(UserRepository.class);
		
		List<Users> testUsers= new ArrayList<>();
		when(mockServiceRepository.findAll()).thenReturn(testUsers);
		
		UserDTO userDTO = userServiceImpl.authenticateUser("Adn", "Paword");
		
		assertNotNull(userDTO);
		assertNull(userDTO.getUserName());
		assertNull(userDTO.getPassword());
		assertNull(userDTO.getRole());
		
	}

}

