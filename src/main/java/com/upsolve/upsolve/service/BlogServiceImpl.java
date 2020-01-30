package com.upsolve.upsolve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upsolve.upsolve.dao.BlogDao;
import com.upsolve.upsolve.entity.Blog;
import com.upsolve.upsolve.entity.Problem;
import com.upsolve.upsolve.user.AddPostChecker;

@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
	@Override
	@Transactional
	public Blog findByid(Integer id) {
		if(blogDao.findById(id).isPresent())
			return blogDao.findById(id).get();
		
		return null;
	}

	@Override
	@Transactional
	public void save(AddPostChecker addpostchecker) {
		Blog blog = new Blog();
		blog.setTitle(addpostchecker.getTitle());
		blog.setDescription(addpostchecker.getDescription());
		blog.setContent(addpostchecker.getContent());
		
		blog.setAddedby(addpostchecker.getAddedby());
		if(addpostchecker.getId()!=-1)
			blog.setId(addpostchecker.getId());
		blogDao.save(blog);

	}
	
	@Override
	@Transactional
	public void savee(Blog a) {
		
		blogDao.save(a);

	}

	@Override
	public List<Blog> getall() {
		return blogDao.findAll();
	}

	@Override
	public void deleteById(Integer id) {
		blogDao.deleteById(id);
	}

}
