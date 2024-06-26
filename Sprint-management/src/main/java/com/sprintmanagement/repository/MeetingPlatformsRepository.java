package com.sprintmanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sprintmanagement.entities.MeetingPlatforms;

//CrudRepository Interfaces
@Repository
public interface MeetingPlatformsRepository extends CrudRepository<MeetingPlatforms,Integer>{
	
}
