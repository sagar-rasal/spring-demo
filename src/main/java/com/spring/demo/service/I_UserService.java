package com.spring.demo.service;

import com.spring.demo.dto.User;
import com.spring.demo.dto.UserRequest;

public interface I_UserService {

	User createUser(UserRequest user);

	User searchUser(int id);

	User updateUser(User newUser);

	void deleteUser(int id);

}
