package com.upsolve.upsolve.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upsolve.upsolve.entity.Problem;

public interface ProblemDao extends JpaRepository<Problem, String>  {
	
	//public Problem findBylink(String link);
    
    //public void save(Problem problem);
    
    //public List<Problem> getall(); 
    
}
