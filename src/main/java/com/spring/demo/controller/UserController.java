package com.spring.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.dto.ResponseDto;
import com.spring.demo.dto.ErrorResponse;
import com.spring.demo.dto.User;
import com.spring.demo.dto.UserDetailsResponse;
import com.spring.demo.dto.UserRequest;
import com.spring.demo.service.I_UserService;
import com.spring.demo.util.ValidationUtil;

@RestController
@RequestMapping("/company")
public class UserController {

	@Autowired
	I_UserService service;

	@PostMapping("/user")
	public ResponseEntity<ResponseDto> createUser(@RequestBody UserRequest user) {
		User userResult = service.createUser(user);
		return new ResponseEntity<>(mappToResponse(userResult), HttpStatus.OK);
	}

	@GetMapping("/users/{Id}")
	public ResponseEntity<ResponseDto> createUser(@PathVariable(name = "Id") int id) {

		User user = service.searchUser(id);
		if (user == null) {
			return new ResponseEntity<>(new ErrorResponse(ValidationUtil.MESSAGE_USER_NOT_FOUND), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(mappToResponse(user), HttpStatus.OK);
	}

	@PutMapping("/users/{Id}")
	public ResponseEntity<ResponseDto> updateUser(@PathVariable(name = "Id") int id, @RequestBody UserRequest user) {
		User newUser = new User();
		newUser.setId(id);
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setAge(user.getAge());
		User userResult = service.updateUser(newUser);
		if (userResult == null) {
			return new ResponseEntity<>(new ErrorResponse(ValidationUtil.MESSAGE_USER_NOT_FOUND + id),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(mappToResponse(userResult), HttpStatus.OK);
	}

	@DeleteMapping("/users/{Id}")
	public ResponseEntity<ResponseDto> deleteUser(@PathVariable(name = "Id") int id) {
		User userResult = service.searchUser(id);
		if (userResult == null) {
			return new ResponseEntity<>(new ErrorResponse(ValidationUtil.MESSAGE_USER_NOT_FOUND + id),
					HttpStatus.NOT_FOUND);
		}
		service.deleteUser(id);
		return new ResponseEntity<>(mappToResponse(userResult), HttpStatus.OK);
	}

	private UserDetailsResponse mappToResponse(User user) {
		UserDetailsResponse response = new UserDetailsResponse();
		response.setId(String.valueOf(user.getId()));
		response.setFirstName(user.getFirstName());
		response.setLastName(user.getLastName());
		response.setAge(String.valueOf(user.getAge()));
		response.setEmail(user.getEmail());
		return response;
	}

}
