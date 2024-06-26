package com.sprintmanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sprintmanagement.entities.Sprints;

//CrudRepository Interface
@Repository
public interface SprintRepository extends CrudRepository<Sprints,Integer> {

}
