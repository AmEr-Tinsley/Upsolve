package com.upsolve.upsolve.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upsolve.upsolve.dao.RoleDao;
import com.upsolve.upsolve.dao.UserDao;
import com.upsolve.upsolve.entity.Role;
import com.upsolve.upsolve.entity.User;
import com.upsolve.upsolve.user.RegistrationChecker;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public User findByUserName(String userName) {
		return userDao.findByUserName(userName);
	}

	@Override
	@Transactional
	public void save(RegistrationChecker registrationChecker) {
		System.out.println("ROLE_"+registrationChecker.getRole());
		User user = new User();
		user.setUserName(registrationChecker.getUserName());
		user.setPassword(passwordEncoder.encode(registrationChecker.getPassword()));
		user.setEmail(registrationChecker.getEmail());
		if(registrationChecker.getRole().equals("TRAINER"))
			user.setApproved(false);
		else	user.setApproved(true);
		
		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_"+registrationChecker.getRole())));
		
		user.setRole(registrationChecker.getRole());
		 // save user in the database
		//System.out.println(user);
		userDao.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public List<User> getall() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	@Override
	public void deleteByUserName(String userName) {
		// TODO Auto-generated method stub
		userDao.deleteByUserName(userName);
	}
	
	
	
}
