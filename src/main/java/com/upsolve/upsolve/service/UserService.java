package com.upsolve.upsolve.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.upsolve.upsolve.entity.User;
import com.upsolve.upsolve.user.RegistrationChecker;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);
	
	public void deleteByUserName(String userName);

	public void save(RegistrationChecker registrationChecker);
	
	public List<User> getall();
}
