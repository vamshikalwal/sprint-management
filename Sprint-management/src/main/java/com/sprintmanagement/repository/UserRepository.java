package com.sprintmanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sprintmanagement.entities.Users;

@Repository
public interface UserRepository extends CrudRepository<Users,String>{

}
