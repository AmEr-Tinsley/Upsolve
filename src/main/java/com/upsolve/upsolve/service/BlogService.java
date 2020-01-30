package com.upsolve.upsolve.service;

import java.util.List;

import com.upsolve.upsolve.entity.Blog;
import com.upsolve.upsolve.entity.Problem;
import com.upsolve.upsolve.user.AddPostChecker;

public interface BlogService {
	
	public Blog findByid(Integer id);

	public void save(AddPostChecker addpostchecker);
	
	public void savee(Blog a);

	
	public List<Blog> getall();
	
	public void deleteById(Integer id);
}
