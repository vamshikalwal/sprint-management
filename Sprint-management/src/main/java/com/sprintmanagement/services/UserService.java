package com.sprintmanagement.services;

import java.util.List;

import com.sprintmanagement.dto.UserDTO;
import com.sprintmanagement.entities.Users;

public interface UserService {
	
	public UserDTO authenticateUser(String username,String password);

}
