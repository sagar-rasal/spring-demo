package com.spring.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.demo.dto.User;
import com.spring.demo.dto.UserRequest;
import com.spring.demo.exception.IllegalUserException;
import com.spring.demo.repository.I_UserRepository;

public class UserServiceImpl implements I_UserService {

	@Autowired
	I_UserRepository dao;

	@Override
	public User createUser(UserRequest user) {
		if (user == null)
			throw new IllegalUserException("Invalid user!!");
		
		User newUser = new User();
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setAge(user.getAge());
		return dao.save(newUser);
	}

	@Override
	public User searchUser(int id) {
		return dao.getById((Integer) id);
	}

	@Override
	public User updateUser(User user) {
		if (user == null)
			throw new IllegalUserException("Invalid user!!");
		return dao.save(user);
	}

	@Override
	public void deleteUser(int id) {

		dao.deleteById(id);
	}

}
