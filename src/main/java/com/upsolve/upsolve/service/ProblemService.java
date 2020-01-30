package com.upsolve.upsolve.service;

import java.util.List;

import com.upsolve.upsolve.entity.Problem;
import com.upsolve.upsolve.user.AddProblemChecker;

public interface ProblemService {
	
	public Problem findBylink(String problem);

	public void save(AddProblemChecker addproblemchecker);
	
	public List<Problem> getall();
	
	public void deleteBylink(String link);
}

