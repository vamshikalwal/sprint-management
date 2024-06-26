package com.cognizant.sprintmanagement;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import com.sprintmanagement.entities.MeetingPlatforms;
import com.sprintmanagement.main.SprintManagementApplication;
import com.sprintmanagement.repository.MeetingPlatformsRepository;

@DataJpaTest
@ContextConfiguration(classes = SprintManagementApplication.class)
public class TestMeetingPlatformsEntityRepository {
	@Autowired
	private MeetingPlatformsRepository meetingPlatformsRepository;
	@Autowired
	private TestEntityManager entityManager;
	@Test
	public void testFindAllPositive()
	{
		MeetingPlatforms s= new MeetingPlatforms();
		s.setId(3);
		s.setName("Teju11");
		meetingPlatformsRepository.save(s);
		Iterable<MeetingPlatforms> it = meetingPlatformsRepository.findAll();
		assertTrue(it.iterator().hasNext());
	}
	@Test
	public void testFindAllNegative()
	{
				
		Iterable<MeetingPlatforms> it = meetingPlatformsRepository.findAll();
		meetingPlatformsRepository.deleteAll();
		assertTrue(it.iterator().hasNext());
		
	}
	
	@Test
	public void testFindByIdPositive()
	{
		MeetingPlatforms s= new MeetingPlatforms();
		s.setId(3);
		s.setName("Teju11");
		entityManager.persist(s);
		Optional<MeetingPlatforms> sprint = meetingPlatformsRepository.findById(2);
		assertTrue(sprint.isPresent());	
	}
	@Test
	public void testFindByIdNegative()
	{
		meetingPlatformsRepository.deleteAll();
		Optional<MeetingPlatforms> sprint = meetingPlatformsRepository.findById(2);
		assertTrue(!sprint.isPresent());	
	}
	@Test
	public void testSavePositive()
	{
		MeetingPlatforms s= new MeetingPlatforms();
		s.setId(3);
		s.setName("Teju11");
		meetingPlatformsRepository.save(s);
		Optional<MeetingPlatforms> sprint = meetingPlatformsRepository.findById(2);
		assertTrue(sprint.isPresent());	
		
	}
	
	@Test
	public void testDeletePositive() 
	{
		MeetingPlatforms s= new MeetingPlatforms();
		s.setId(3);
		s.setName("Teju11");
		entityManager.persist(s);
		meetingPlatformsRepository.delete(s);
		Optional<MeetingPlatforms> sprint = meetingPlatformsRepository.findById(2);
		assertTrue(sprint.isPresent());
		
	}
	
	
	
	
	
	
	
	
	

}
