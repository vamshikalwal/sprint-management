package com.sprintmanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprintmanagement.dto.UserDTO;
import com.sprintmanagement.entities.Users;
import com.sprintmanagement.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDTO authenticateUser(String username, String password) {
		Iterable<Users> users= userRepository.findAll();
		UserDTO userModel=new UserDTO();
		for(Users user:users) {
			if(user.getUserName().equals(username) && user.getPassword().equals(password)) {
				userModel.setUserName(user.getUserName());
				userModel.setPassword(user.getPassword());
				userModel.setRole(user.getRole());
				break;
			}
		}
		return userModel;
	}

}
