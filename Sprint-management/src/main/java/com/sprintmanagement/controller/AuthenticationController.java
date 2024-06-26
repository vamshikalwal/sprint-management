package com.sprintmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprintmanagement.dto.UserDTO;
import com.sprintmanagement.services.UserService;

@RestController
@RequestMapping("/authenticate")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
	@Autowired
	private UserService userService;
	
	@PostMapping("users")
	public ResponseEntity<?> authenticate(@RequestBody UserDTO userRequest){
		UserDTO userDTO=userService.authenticateUser(userRequest.getUserName(), userRequest.getPassword());
		
		if(userDTO.getUserName()!=null) {
			return new ResponseEntity<UserDTO>(userDTO,HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
	}

}
