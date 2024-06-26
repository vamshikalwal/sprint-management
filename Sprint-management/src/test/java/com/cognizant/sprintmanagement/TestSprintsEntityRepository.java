package com.cognizant.sprintmanagement;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import com.sprintmanagement.entities.Sprints;
import com.sprintmanagement.main.SprintManagementApplication;
import com.sprintmanagement.repository.SprintRepository;


@DataJpaTest
@ContextConfiguration(classes = SprintManagementApplication.class)
public class TestSprintsEntityRepository {
	@Autowired
	private SprintRepository sprintRepository;
	@Autowired
	private TestEntityManager entityManager;
	@Test
	public void testFindAllPositive() {
		Sprints s=new Sprints();
		s.setSprintId(1);
		s.setSprintName("Teju");
		s.setStartDate(LocalDate.parse("2024-04-02"));
		s.setEndDate(LocalDate.parse("2024-04-12"));
		s.setProjectCode(1);
		s.setCreatedOn(LocalDate.parse("2024-04-01"));
		sprintRepository.save(s);
		Iterable<Sprints> it = sprintRepository.findAll();
		assertTrue(it.iterator().hasNext());
	}
//	@Test
//	public void testFindAllNegative() {
//		Iterable<Sprints> it = sprintRepository.findAll();
//		assertTrue(!it.iterator().hasNext());
//	}
//	
	@Test
	public void testFindByIdPositive() {
		Sprints s=new Sprints();
		s.setSprintId(1);
		s.setSprintName("Teju");
		s.setStartDate(LocalDate.parse("2024-04-02"));
		s.setEndDate(LocalDate.parse("2024-04-12"));
		s.setProjectCode(1);
		s.setCreatedOn(LocalDate.parse("2024-04-01"));
		sprintRepository.save(s);
		Optional<Sprints> sprint = sprintRepository.findById(1);
		assertTrue(sprint.isPresent());
	}
	
	@Test
	public void testFindByIdNegative() {
		sprintRepository.deleteAll();
		Optional<Sprints> sprint = sprintRepository.findById(1);
		assertTrue(!sprint.isPresent());
	}
	
//	@Test
//	public void testSavePositive() {
//		Sprints s=new Sprints();
//		s.setSprintId(1);
//		s.setSprintName("Teju");
//		s.setStartDate(LocalDate.parse("2024-04-02"));
//		s.setEndDate(LocalDate.parse("2024-04-12"));
//		s.setProjectCode(1);
//		s.setCreatedOn(LocalDate.parse("2024-04-01"));
//		sprintRepository.save(s);
//		Optional<Sprints> sprint = sprintRepository.findById(1);
//		assertTrue(!sprint.isPresent());
//	}
	@Test
	public void testDeletePositive() {
		Sprints s=new Sprints();
		s.setSprintId(1);
		s.setSprintName("Teju");
		s.setStartDate(LocalDate.parse("2024-04-02"));
		s.setEndDate(LocalDate.parse("2024-04-12"));
		s.setProjectCode(1);
		s.setCreatedOn(LocalDate.parse("2024-04-01"));
//		entityManager.persist(s);
		sprintRepository.save(s);
		sprintRepository.delete(s);
		Optional<Sprints> sprint = sprintRepository.findById(1);
		assertTrue(!sprint.isPresent());
	}

	
	
	


}
