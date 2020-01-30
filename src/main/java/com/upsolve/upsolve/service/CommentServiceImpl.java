package com.upsolve.upsolve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upsolve.upsolve.dao.CommentDao;
import com.upsolve.upsolve.entity.Comment;
@Service

public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDao commentDao;
	
	@Override
	@Transactional
	public Comment findByid(Integer id) {
		
		if(commentDao.findById(id).isPresent())
			return commentDao.findById(id).get();
		
		return null;
	}

	@Override
	@Transactional
	public void save(Comment comment) {
		commentDao.save(comment);

	}

	@Override
	public void deleteById(Integer id) {
		
		commentDao.deleteById(id);
	}

}
