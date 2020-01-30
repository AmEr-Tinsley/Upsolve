package com.upsolve.upsolve.service;

import java.util.List;

import com.upsolve.upsolve.entity.Link;
import com.upsolve.upsolve.user.LinkChecker;

public interface LinkService {
	public Link findBylink(String link);

	public void save(LinkChecker linkchecker);
	
	public List<Link> getall();
	
	public void deleteBylink(String link);
}
