//package com.cognizant.sprintmanagement;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.ContextConfiguration;
//
//import com.cognizant.entities.MeetingPlatforms;
////import com.cognizant.entities.MeetingType;
//import com.cognizant.entities.Meetings;
//import com.cognizant.entities.Sprints;
//import com.cognizant.main.SprintManagementApplication;
//import com.cognizant.repository.MeetingPlatformsRepository;
//import com.cognizant.repository.MeetingsRepository;
//import com.cognizant.repository.SprintRepository;
//
//import com.cognizant.utilities.*;
//
//import jakarta.persistence.EntityManager;
//
//@DataJpaTest
//@ContextConfiguration(classes = SprintManagementApplication.class)
//public class TestMeetingsEntityRepository {
//	@Autowired
//	private MeetingsRepository meetingsRepository;
//	
//	@Autowired
//	private SprintRepository sprintRepository;
//	
//	@Autowired
//	private MeetingPlatformsRepository meetingPlatformsRepository;
//	
//	@Autowired
//	private TestEntityManager entityManager;
//	@Test
//	public void testFindAllPositive() 
//	{
//		Meetings s=new Meetings();
//		
//		Sprints n=new Sprints();
//		n.setSprintId(1);
//		n.setSprintName("Teju");
//		n.setStartDate(LocalDate.parse("2024-08-02"));
//		n.setEndDate(LocalDate.parse("2024-08-10"));
//		n.setProjectCode(11);
//		n.setCreatedOn(LocalDate.parse("2024-08-01"));
//		sprintRepository.save(n);
//		
//		MeetingPlatforms m=new MeetingPlatforms();
//		m.setId(3);
//		m.setName("Teju11");
//		meetingPlatformsRepository.save(m);
//		
//		s.setId(1);
//		s.setMeetingLink("xyzbb");
//		s.setMeetingDate(LocalDate.parse("2024-08-02"));
//		s.setMeetingTime(LocalTime.parse("10:30:00"));
//		s.setMeetingType(MeetingTypeEnum.SprintPlanning);
//		s.setSprintId(1);
//		s.setMeetingPassword("xyz@1");
//		LocalDate meetingCreateDate = LocalDate.parse("2024-08-01");
//		s.setCreatedOn(meetingCreateDate);
//		s.setStatus(MeetingStatusEnum.Scheduled);
//		LocalDate updatedOn = LocalDate.parse("2024-08-08");
//		s.setUpdatedOn(updatedOn);
//		s.setMeetingPlatformId(3);
//		
////		sprintRepository.save(n);
//		entityManager.persist(n);
//		meetingPlatformsRepository.save(m);
//		meetingsRepository.save(s);
//		
//		Iterable<Meetings> it = meetingsRepository.findAll();
//		assertTrue(it.iterator().hasNext());
//	
//	}
//	@Test
//	public void testFindAllNegative()
//	{
//		Iterable<Meetings> iterable = meetingsRepository.findAll();
//		assertTrue(!iterable.iterator().hasNext());
//	}
//	
//	@Test
//	public void testFindByIdPositive()
//	{
//		Meetings s=new Meetings();
//		Sprints n=new Sprints();
//		MeetingPlatforms m=new MeetingPlatforms();
//		s.setId(1);
//		s.setMeetingLink("xyzbb");
//		LocalDate meetingDate = LocalDate.parse("2024-08-02");
//		s.setMeetingDate(meetingDate);
//		LocalTime meetingTime = LocalTime.parse("10:30:00");
//		s.setMeetingTime(meetingTime);
//		s.setMeetingType(MeetingTypeEnum.SprintPlanning);
//		s.setSprintId(1);
//		s.setMeetingPassword("xyz@1");
//		LocalDate createdOn = LocalDate.parse("2024-08-01");
//		s.setCreatedOn(createdOn);
//		s.setStatus(MeetingStatusEnum.cancelled);
//		LocalDate updateOn = LocalDate.parse("2024-08-06");
//		s.setUpdatedOn(updateOn);
//		s.setMeetingPlatformId(2);
//		sprintRepository.save(n);
//		meetingPlatformsRepository.save(m);
//		meetingsRepository.save(s);
//		Optional<Meetings> meetings = meetingsRepository.findById(1);
//		assertTrue(meetings.isPresent());
//		
//	}
//	
//	@Test
//	public void testFindByIdNegative() 
//	{
//		
//		Optional<Meetings> meetings = meetingsRepository.findById(5);
//		assertTrue(!meetings.isPresent());
//		
//	}
//	
//	@Test
//	public void testSavePositive()
//	{
//		Meetings s=new Meetings();
//		Sprints n=new Sprints();
//		n.setSprintId(1);
//		n.setSprintName("Sprint-01");
//		LocalDate startDate = LocalDate.parse("2024-08-02");
//		n.setStartDate(startDate);
//		LocalDate endDate = LocalDate.parse("2024-08-15");
//		n.setEndDate(endDate);
//		n.setProjectCode(1);
////		LocalDate createdOn= LocalDate.parse("2024-08-02");
//		n.setCreatedOn(LocalDate.now());
//		sprintRepository.save(n);
//		
//		MeetingPlatforms m=new MeetingPlatforms();
//		m.setId(2);
//		m.setName("Teju11");
//		meetingPlatformsRepository.save(m);
//		s.setId(1);
//		s.setMeetingLink("xyzbb");
//		LocalDate meetingDate = LocalDate.parse("2024-08-02");
//		s.setMeetingDate(meetingDate);
//		LocalTime meetingTime = LocalTime.parse("10:30:00");
//		s.setMeetingTime(meetingTime);
//		s.setMeetingType(MeetingTypeEnum.SprintPlanning);
//		s.setSprintId(1);
//		s.setMeetingPassword("xyz@1");
//		s.setCreatedOn(LocalDate.now());
//		s.setStatus(MeetingStatusEnum.Scheduled);
//		LocalDate updatedOn = LocalDate.parse("2024-08-08");
//		s.setUpdatedOn(updatedOn);
//		s.setMeetingPlatformId(2);
//		sprintRepository.save(n);
//		meetingPlatformsRepository.save(m);
//		meetingsRepository.save(s);
////		entityManager.persist(s);
//		Optional<Meetings> meetings = meetingsRepository.findById(2);
//		assertTrue(!meetings.isPresent());
//		
//	}
//	
//	@Test
//	public void testDeletePositive()
//	{
//		Meetings s=new Meetings();
//		Sprints n=new Sprints();
//		MeetingPlatforms m=new MeetingPlatforms();
//		s.setId(1);
//		s.setMeetingLink("xyzbb");
//		LocalDate meetingDate = LocalDate.parse("2024-08-02");
//		s.setMeetingDate(meetingDate);
//		LocalTime meetingTime = LocalTime.parse("10:30:00");
//		s.setMeetingTime(meetingTime);
//		s.setMeetingType(MeetingTypeEnum.SprintPlanning);
//		s.setSprintId(1);
//		s.setMeetingPassword("xyz@1");
//		LocalDate meetingCreateDate = LocalDate.parse("2024-08-01");
//		s.setCreatedOn(meetingCreateDate);
//		s.setStatus(MeetingStatusEnum.Completed);
//		LocalDate updatedOn = LocalDate.parse("2024-08-08");
//		s.setUpdatedOn(LocalDate.parse("2024-08-08"));
//		s.setMeetingPlatformId(2);
//		sprintRepository.save(n);
//		meetingPlatformsRepository.save(m);
//		meetingsRepository.save(s);
//		meetingsRepository.delete(s);
//		Optional<Meetings> meetings = meetingsRepository.findById(1);
//		assertTrue(!meetings.isPresent());
//		
//		
//		
//	}
//
//}
