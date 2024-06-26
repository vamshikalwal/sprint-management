package com.cognizant.sprintmanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprintmanagement.controller.AuthenticationController;
import com.sprintmanagement.controller.SprintManagementController;
import com.sprintmanagement.dto.MeetingPlatformsDTO;
import com.sprintmanagement.dto.MeetingsDTO;
import com.sprintmanagement.dto.SprintsDTO;
import com.sprintmanagement.dto.UserDTO;
import com.sprintmanagement.main.SprintManagementApplication;
import com.sprintmanagement.services.MeetingsPlatfromService;
import com.sprintmanagement.services.MeetingsService;
import com.sprintmanagement.services.SprintService;
import com.sprintmanagement.services.UserService;
import com.sprintmanagement.utilities.MeetingStatusEnum;
import com.sprintmanagement.utilities.MeetingStatusUpdateFailedException;
import com.sprintmanagement.utilities.MeetingTypeEnum;

@SpringBootTest(classes = SprintManagementApplication.class)
public class TestSprintManagementController {
	private MockMvc mockMvc;
	
	@Mock 
	private SprintService sprintService;
	
	@Mock
	private MeetingsService meetingsService;
	
	@Mock
	private MeetingsPlatfromService meetingsPlatfromService;
	
	@Mock
	private UserService userService; 
	
	@InjectMocks
	private SprintManagementController sprintsController;
	
	@InjectMocks
	private AuthenticationController authenticationController;
	
	
	@Mock
	private RestTemplate restTemplate;
	
	private MockRestServiceServer mockServer;
	private RestTemplate template;
	private ObjectMapper mapper=new ObjectMapper();
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(sprintsController).build();
		template = new RestTemplate();
		mockServer = MockRestServiceServer.createServer(template);
		
	}
	
	@Test
	public void testReturnAllSprintsPositive()
	{
		List<SprintsDTO> sprintsDTOList = new ArrayList<>();
		SprintsDTO sprintsDataDto = new SprintsDTO();
		sprintsDataDto.setSprintId(1);
		sprintsDataDto.setSprintName("eshfrbcjh");
		sprintsDataDto.setStartDate(LocalDate.parse("2023-04-01"));
		sprintsDataDto.setEndDate(LocalDate.parse("2023-04-11"));
		sprintsDataDto.setProjectCode(23);
		sprintsDataDto.setCreatedOn(LocalDate.parse("2023-04-01"));
		sprintsDTOList.add(sprintsDataDto);
		
		Mockito.when(sprintService.getAllSprints()).thenReturn(sprintsDTOList);
		List<SprintsDTO> responseSprintsList  =  sprintsController.getAllSprints().getBody();
		
		assertTrue(!responseSprintsList.isEmpty());
		assertEquals(sprintsDTOList.size(), responseSprintsList.size());
	
	
	} 
	
	@Test
	public void testReturnAllSprintsNegative()
	{
		List<SprintsDTO> sprintsDTOList = new ArrayList<>();
		Mockito.when(sprintService.getAllSprints()).thenReturn(sprintsDTOList);
		
		ResponseEntity<?> responseEntity = sprintsController.getAllSprints();
		List<SprintsDTO> responseSprintsList  =  (List<SprintsDTO>) responseEntity.getBody();

		assertNull(responseSprintsList);

	}
	
	@Test
	public void testReturnAllMeetingsPlatformsPositive()
	{
		
		List<MeetingPlatformsDTO> meetingPlatformsDtoList = new ArrayList<>();
		
		MeetingPlatformsDTO meetingPlatformsDataDto = new MeetingPlatformsDTO();
		meetingPlatformsDataDto.setId(11);
		meetingPlatformsDataDto.setName("Jaanu");
		
		meetingPlatformsDtoList.add(meetingPlatformsDataDto);
		
		Mockito.when(meetingsPlatfromService.getMeetingPlatforms()).thenReturn(meetingPlatformsDtoList);
		
		ResponseEntity<?> responseEntity = sprintsController.getAllMeetingPlatforms();
		List<MeetingPlatformsDTO> responseMeetingsPlatformsDTOList = (List<MeetingPlatformsDTO>) responseEntity.getBody();
		
		assertTrue(!responseMeetingsPlatformsDTOList.isEmpty());
		assertEquals(meetingPlatformsDtoList.size(), responseMeetingsPlatformsDTOList.size());
		
		
		
		
		
		
		
	}
	

	@Test 
	public void testReturnAllMeetingsPlatformsNegative()
	{
		List<MeetingPlatformsDTO> meetingPlatformsDtoList = new ArrayList<>();
		Mockito.when(meetingsPlatfromService.getMeetingPlatforms()).thenReturn(meetingPlatformsDtoList);
		
		ResponseEntity<?> responseEntity = sprintsController.getAllMeetingPlatforms();
		List<MeetingPlatformsDTO> responseSprintsList  = (List<MeetingPlatformsDTO>) responseEntity.getBody();

		assertNull(responseSprintsList);
		
	}
	
	@Test
	public void testUriReturnAllSprintsPositive()
	{
		List<SprintsDTO> sprintsDTOList = new ArrayList<>();
		SprintsDTO sprintsDataDto = new SprintsDTO();
		sprintsDataDto.setSprintId(1);
		sprintsDataDto.setSprintName("eshfrbcjh");
		sprintsDataDto.setStartDate(LocalDate.parse("2023-04-01"));
		sprintsDataDto.setEndDate(LocalDate.parse("2023-04-11"));
		sprintsDataDto.setProjectCode(23);
		sprintsDataDto.setCreatedOn(LocalDate.parse("2023-04-01"));
		sprintsDTOList.add(sprintsDataDto);
		
		Mockito.when(sprintService.getAllSprints()).thenReturn(sprintsDTOList);
		try {
			MvcResult mockMvcResult = mockMvc.perform(get("http://localhost:8088/api/sprints")).andExpect(status().isOk()).andReturn();
		} catch (Exception e) {
			// TODO: handle exception
			assertTrue(false);
		}
		
	}
	
	@Test
	public void testUriReturnAllSprintsNegative()
	{
		
		
		Mockito.when(sprintService.getAllSprints()).thenReturn(null);
		try {
			MvcResult mockMvcResult = mockMvc.perform(get("http://localhost:8088/api/sprints")).andExpect(status().isBadRequest()).andReturn();
		} catch (Exception e) {
			// TODO: handle exception
			assertTrue(true);
		}
		
	}
	
	@Test
	public void testUriReturnAllMeetingsPlatformsPositive()
	{
		List<MeetingPlatformsDTO> meetingPlatformsDtoList = new ArrayList<>();
		
		MeetingPlatformsDTO meetingPlatformsDataDto = new MeetingPlatformsDTO();
		meetingPlatformsDataDto.setId(11);
		meetingPlatformsDataDto.setName("Jaanu");
		
		meetingPlatformsDtoList.add(meetingPlatformsDataDto);
		
		Mockito.when(meetingsPlatfromService.getMeetingPlatforms()).thenReturn(meetingPlatformsDtoList);
		
		try {
			MvcResult mockMvcResult = mockMvc.perform(get("http://localhost:8088/api/meetings/platform")).andExpect(status().isOk()).andReturn();
		} catch (Exception e) {
			// TODO: handle exception
			assertTrue(false);
		}
	}
	
	@Test
	public void testUriReturnAllMeetingsPlatformsNegative()
	{
		List<MeetingPlatformsDTO> meetingPlatformsDtoList = new ArrayList<>();
		
		
		Mockito.when(meetingsPlatfromService.getMeetingPlatforms()).thenReturn(meetingPlatformsDtoList);
		
		try {
			MvcResult mockMvcResult = mockMvc.perform(get("http://localhost:8088/api/meetings/platform")).andExpect(status().isBadRequest()).andReturn();
		} catch (Exception e) {
			// TODO: handle exception
			assertTrue(true);
		}
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetMeetingByIdPositive()
	{
		MeetingsDTO meetingsDataDto = new MeetingsDTO();
		meetingsDataDto.setId(1);
		meetingsDataDto.setMeetingLink("sjhcbacijsc");
		meetingsDataDto.setMeetingDate(LocalDate.parse("2022-04-01"));
		meetingsDataDto.setMeetingTime(LocalTime.parse("10:30:00"));
		meetingsDataDto.setMeetingType(MeetingTypeEnum.SprintPlanning);
		meetingsDataDto.setSprintId(2);
		meetingsDataDto.setMeetingPassword("vamshivam@1");
		meetingsDataDto.setCreatedOn(LocalDate.parse("2022-04-01"));
		meetingsDataDto.setStatus(MeetingStatusEnum.Scheduled);
		meetingsDataDto.setMeetingPlatformId(2);
		
		when(meetingsService.scheduleMeeting(meetingsDataDto)).thenReturn("success");
		when(meetingsService.getMeetingById(1)).thenReturn(meetingsDataDto);
		
		ResponseEntity<?> responseEntity = sprintsController.findMeetingById(1);
		assertEquals(200,responseEntity.getStatusCodeValue());
		

	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetMeetingByIdNegative()
	{
		MeetingsDTO meetingsDataDto = new MeetingsDTO();
		meetingsDataDto.setId(1);
		meetingsDataDto.setMeetingLink("sjhcbacijsc");
		meetingsDataDto.setMeetingDate(LocalDate.parse("2022-04-01"));
		meetingsDataDto.setMeetingTime(LocalTime.parse("10:30:00"));
		meetingsDataDto.setMeetingType(MeetingTypeEnum.SprintPlanning);
		meetingsDataDto.setSprintId(2);
		meetingsDataDto.setMeetingPassword("vamshivam@1");
		meetingsDataDto.setCreatedOn(LocalDate.parse("2022-04-01"));
		meetingsDataDto.setStatus(MeetingStatusEnum.Scheduled);
		meetingsDataDto.setMeetingPlatformId(2);
		
		when(meetingsService.scheduleMeeting(meetingsDataDto)).thenReturn("success");
		when(meetingsService.getMeetingById(1)).thenReturn(null);
		
		ResponseEntity<?> responseEntity = sprintsController.findMeetingById(1);
		assertEquals(400,responseEntity.getStatusCodeValue());
		

	}
	
	@Test
	public void testUriGetMeetingByIdPositive()
	{
		MeetingsDTO meetingsDataDto = new MeetingsDTO();
		meetingsDataDto.setId(1);
		meetingsDataDto.setMeetingLink("sjhcbacijsc");
		meetingsDataDto.setMeetingDate(LocalDate.parse("2022-04-01"));
		meetingsDataDto.setMeetingTime(LocalTime.parse("10:30:00"));
		meetingsDataDto.setMeetingType(MeetingTypeEnum.SprintPlanning);
		meetingsDataDto.setSprintId(2);
		meetingsDataDto.setMeetingPassword("vamshivam@1");
		meetingsDataDto.setCreatedOn(LocalDate.parse("2022-04-01"));
		meetingsDataDto.setStatus(MeetingStatusEnum.Scheduled);
		meetingsDataDto.setMeetingPlatformId(2);

		when(meetingsService.getMeetingById(anyInt())).thenReturn(meetingsDataDto);
		
		try {
			mockMvc.perform(get("http://localhost:8088/api/meetings/1"))
					.andExpect(status().isOk())
					.andReturn();

		} catch (Exception e) {
			// TODO: handle exception
			assertTrue(false);
		}
		
	}
	
	@Test
	public void testUriGetMeetingByIdNegative()
	{
		MeetingsDTO meetingsDataDto = new MeetingsDTO();
		meetingsDataDto.setId(1);
		meetingsDataDto.setMeetingLink("sjhcbacijsc");
		meetingsDataDto.setMeetingDate(LocalDate.parse("2022-04-01"));
		meetingsDataDto.setMeetingTime(LocalTime.parse("10:30:00"));
		meetingsDataDto.setMeetingType(MeetingTypeEnum.SprintPlanning);
		meetingsDataDto.setSprintId(2);
		meetingsDataDto.setMeetingPassword("vamshivam@1");
		meetingsDataDto.setCreatedOn(LocalDate.parse("2022-04-01"));
		meetingsDataDto.setStatus(MeetingStatusEnum.Scheduled);
		meetingsDataDto.setMeetingPlatformId(2);

		when(meetingsService.getMeetingById(1)).thenReturn(meetingsDataDto);
		
		try {
			MvcResult mockMvcResult = mockMvc.perform(get("http://localhost:8088/meetings/{meetingId}",2)).andExpect(status().isNotFound()).andReturn();

		} catch (Exception e) {
			// TODO: handle exception
			assertTrue(true);
		}
		
	}
	
//	-------------------------------
	
	@Test
	public void testAddNewSprintPositive()
	{
		SprintsDTO sprintsDataDto = new SprintsDTO();
		sprintsDataDto.setSprintId(1);
		sprintsDataDto.setSprintName("eshfrbcjh");
		sprintsDataDto.setStartDate(LocalDate.parse("2023-04-01"));
		sprintsDataDto.setEndDate(LocalDate.parse("2023-04-11"));
		sprintsDataDto.setProjectCode(23);
		sprintsDataDto.setCreatedOn(LocalDate.parse("2023-04-01"));
		
		when(sprintService.persistSprint(sprintsDataDto)).thenReturn("success");
		ResponseEntity<?> responseEntity = sprintsController.addNewSprint(sprintsDataDto);
		assertEquals(201, responseEntity.getStatusCodeValue());
	}
	
	@Test
	public void testAddNewSprintNegative()
	{
		SprintsDTO sprintsDataDto = null;
		
		when(sprintService.persistSprint(sprintsDataDto)).thenReturn("fail");
		ResponseEntity<?> responseEntity = sprintsController.addNewSprint(sprintsDataDto);
		assertEquals(400, responseEntity.getStatusCodeValue());
		
	}
	
	
	@Test
	public void testUriAddNewSprintNegative()
	{
		SprintsDTO sprintsDataDto = new SprintsDTO();
		sprintsDataDto.setSprintId(1);
		sprintsDataDto.setSprintName("eshfrbcjh");
		sprintsDataDto.setStartDate(LocalDate.parse("2023-04-01"));
		sprintsDataDto.setEndDate(LocalDate.parse("2023-04-11"));
		sprintsDataDto.setProjectCode(23);
		sprintsDataDto.setCreatedOn(LocalDate.parse("2023-04-01"));
		
		Mockito.when(sprintService.persistSprint(sprintsDataDto)).thenReturn("success");
		
		try {
			mockMvc.perform(post("http://localhost:8088/api/sprints/new").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(sprintsDataDto))).andExpect(status().isBadRequest()).andReturn();
		} catch (Exception e) {

			assertTrue(true);
		}
		
	}
//	----------------------------------AddNewMeetings
	@Test
	public void testAddNewMeetingsPositive()
	{
		MeetingsDTO meetingsDataDto = new MeetingsDTO();
		meetingsDataDto.setId(1);
		meetingsDataDto.setMeetingLink("sjhcbacijsc");
		meetingsDataDto.setMeetingDate(LocalDate.parse("2022-04-01"));
		meetingsDataDto.setMeetingTime(LocalTime.parse("10:30:00"));
		meetingsDataDto.setMeetingType(MeetingTypeEnum.SprintPlanning);
		meetingsDataDto.setSprintId(2);
		meetingsDataDto.setMeetingPassword("vamshivam@1");
		meetingsDataDto.setCreatedOn(LocalDate.parse("2022-04-01"));
		meetingsDataDto.setStatus(MeetingStatusEnum.Scheduled);
		meetingsDataDto.setMeetingPlatformId(2);
		
		when(meetingsService.scheduleMeeting(meetingsDataDto)).thenReturn("success");
		ResponseEntity<?> responseEntity = sprintsController.addNewMeeting(meetingsDataDto);
		assertEquals(201, responseEntity.getStatusCodeValue());
	}
	
	@Test
	public void testAddNewMeetingsNegative()
	{
		MeetingsDTO meetingsDataDto = new MeetingsDTO();
		
		when(meetingsService.scheduleMeeting(meetingsDataDto)).thenReturn("fail");
		ResponseEntity<?> responseEntity = sprintsController.addNewMeeting(meetingsDataDto);
		assertEquals(400, responseEntity.getStatusCodeValue());
		
	}
	

	
	@Test
	public void testUriAddNewMeetingsNegative()
	{
		MeetingsDTO meetingsDataDto = new MeetingsDTO();
		meetingsDataDto.setId(1);
		meetingsDataDto.setMeetingLink("sjhcbacijsc");
		meetingsDataDto.setMeetingDate(LocalDate.parse("2022-04-01"));
		meetingsDataDto.setMeetingTime(LocalTime.parse("10:30:00"));
		meetingsDataDto.setMeetingType(MeetingTypeEnum.SprintPlanning);
		meetingsDataDto.setSprintId(2);
		meetingsDataDto.setMeetingPassword("vamshivam@1");
		meetingsDataDto.setCreatedOn(LocalDate.parse("2022-04-01"));
		meetingsDataDto.setStatus(MeetingStatusEnum.Scheduled);
		meetingsDataDto.setMeetingPlatformId(2);
		
		Mockito.when(meetingsService.scheduleMeeting(meetingsDataDto)).thenReturn("fail");
		
		try {
			MvcResult mockMvcResult = mockMvc.perform(post("http://localhost:8088/api/sprints").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(meetingsDataDto))).andExpect(status().isBadRequest()).andReturn();
		} catch (Exception e) {
			assertTrue(true);
		}
		
	}
	
	@Test
	public void testRescheduleMeetingPositive()
	{
		MeetingsDTO meetingsDataDto = new MeetingsDTO();
		meetingsDataDto.setId(1);
		meetingsDataDto.setMeetingLink("sjhcbacijsc");
		meetingsDataDto.setMeetingDate(LocalDate.parse("2022-04-01"));
		meetingsDataDto.setMeetingTime(LocalTime.parse("10:30:00"));
		meetingsDataDto.setMeetingType(MeetingTypeEnum.SprintPlanning);
		meetingsDataDto.setSprintId(2);
		meetingsDataDto.setMeetingPassword("vamshivam@1");
		meetingsDataDto.setCreatedOn(LocalDate.parse("2022-04-01"));
		meetingsDataDto.setStatus(MeetingStatusEnum.Scheduled);
		meetingsDataDto.setMeetingPlatformId(2);
		
		try {
			when(meetingsService.rescheduleMeeting(1,meetingsDataDto)).thenReturn("success");
			ResponseEntity<?> responseEntity = sprintsController.rescheduleMeeting(1, meetingsDataDto);
			assertEquals(201, responseEntity.getStatusCodeValue());
			
			
		} catch (MeetingStatusUpdateFailedException e) {
			// TODO: handle exception
			assertTrue(false);
		}
	}
	
	@Test
	public void testRescheduleMeetingNegative()
	{
		MeetingsDTO meetingsDataDto = new MeetingsDTO();
		meetingsDataDto.setId(1);
		
		try {
			when(meetingsService.rescheduleMeeting(2,meetingsDataDto)).thenReturn("fail");
			ResponseEntity<?> responseEntity = sprintsController.rescheduleMeeting(2, meetingsDataDto);
			assertEquals(400, responseEntity.getStatusCodeValue());
			
			
		} catch (MeetingStatusUpdateFailedException e) {
			// TODO: handle exception
			assertTrue(true);
		}
	}
	

	

	@Test
	public void testUriRescheduleMeetingNegative()
	{
		
		MeetingsDTO meetingsDataDto = new MeetingsDTO();
		meetingsDataDto.setId(1);
		try {
			mockMvc.perform(put("http://localhost:8088/api/mengs/reedule",2).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(meetingsDataDto))).andExpect(status().isNotFound()).andReturn();
		} catch (Exception e) {
			// TODO: handle exception
			assertTrue(true);
		}
	}
	
//	@Test
//	public void TestAuthenticationSuccess()
//	{
////		UserService userService = mock(UserService.class);
//		
//		UserDTO userDto =new UserDTO();
//		userDto.setUserName("user1");
//		userDto.setPassword("password");
//		userDto.setRole("Admin");
////		
////		UserDTO userRequestDto1 =new UserDTO();
////		userRequestDto1.setUserName("user2");
////		userRequestDto1.setPassword("@password");
////		userRequestDto1.setRole("Admin");
////		
////		when(userService.authenticateUser("user1", "@password")).thenReturn(userRequestDto);
////		AuthenticationController controller =new AuthenticationController();
////		ResponseEntity<?> responseEntity = controller.authenticate(userRequestDto1);
////		
////		assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
////		assertEquals(userRequestDto, responseEntity.getBody());
//		
////		when(userService.authenticateUser("user1", "password")).thenReturn(userDto);
////		mockMvc.perform(MockMvcRequestBuilders.post("/users").
////				contentType(MediaType.APPLICATION_JSON)
////				.content("{\"userName\}":"\{user1\}""))
//		
//	}
	
	
	
	
	
	
	
	
	
	
	
	

}
