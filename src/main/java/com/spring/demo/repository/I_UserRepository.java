package com.spring.demo.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.demo.dto.User;

public interface I_UserRepository extends JpaRepository<User, Integer> {


}
