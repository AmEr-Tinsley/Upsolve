package com.upsolve.upsolve.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upsolve.upsolve.entity.Link;


public interface LinkDao  extends JpaRepository<Link, String> {
	
	//public Link findBylink(String link);
    
    //public void save(Link link);
    
    //public List<Link> getall(); 
}
