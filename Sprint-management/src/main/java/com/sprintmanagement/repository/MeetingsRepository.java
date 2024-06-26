package com.sprintmanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sprintmanagement.entities.Meetings;

//CrudRepository Interface
@Repository 
public interface MeetingsRepository extends CrudRepository<Meetings,Integer>{
	
	
	
}
