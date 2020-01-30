package com.upsolve.upsolve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upsolve.upsolve.dao.LinkDao;
import com.upsolve.upsolve.entity.Link;
import com.upsolve.upsolve.user.LinkChecker;

@Service
public class LinkServiceImp implements LinkService {
	@Autowired
	private LinkDao linkdao;
	
	@Override
	@Transactional
	public Link findBylink(String link) {
		if(linkdao.findById(link).isPresent())
			return linkdao.findById(link).get();
		else	return null;
	}

	@Override
	@Transactional
	public void save(LinkChecker linkchecker) {
		Link link = new Link();
		link.setLink(linkchecker.getLink());
		link.setName(linkchecker.getName());
		link.setResource(linkchecker.getResource());
		link.setAbout(linkchecker.getAbout());
		link.setAddedby(linkchecker.getAddedBy());

		linkdao.save(link);
		
	}

	@Override
	public List<Link> getall() {
		return linkdao.findAll();
	}

	@Override
	public void deleteBylink(String link) {
		// TODO Auto-generated method stub
		
		linkdao.deleteById(link);
		
	}

}
