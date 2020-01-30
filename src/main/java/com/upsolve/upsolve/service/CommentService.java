package com.upsolve.upsolve.service;

import com.upsolve.upsolve.entity.Comment;

public interface CommentService {
	
	public Comment findByid(Integer id);

	public void save(Comment comment);
	
	public void deleteById(Integer id);
}
