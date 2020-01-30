package com.upsolve.upsolve.dao;

import java.util.List;

import com.upsolve.upsolve.entity.User;

public interface UserDao {

    public User findByUserName(String userName);
    
    public void save(User user);
    
    public void deleteByUserName(String userName);
    
    public List<User> findAll(); 
    
    public void update(User user);
    
}
