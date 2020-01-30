package com.upsolve.upsolve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upsolve.upsolve.dao.ProblemDao;
import com.upsolve.upsolve.entity.Problem;
import com.upsolve.upsolve.user.AddProblemChecker;

@Service
public class ProblemServiceImp implements ProblemService {
	@Autowired
	private ProblemDao problemDao;
	
	
	@Override
	@Transactional
	public Problem findBylink(String link) {
		if(problemDao.findById(link).isPresent())
			return problemDao.findById(link).get();
		else	return null;
	}
	
	@Override
	@Transactional
	public void save(AddProblemChecker addproblemchecker) {
		Problem problem = new Problem();
		problem.setLink(addproblemchecker.getLink());
		problem.setName(addproblemchecker.getName());
		problem.setType(addproblemchecker.getType());
		problem.setAddedby(addproblemchecker.getAddedby());

		 // save problem in the database
		problemDao.save(problem);
	}
	
	@Override
	public List<Problem> getall(){
		
			return problemDao.findAll();
	}

	@Override
	public void deleteBylink(String link) {
		// TODO Auto-generated method stub
		problemDao.deleteById(link);
	}
}
