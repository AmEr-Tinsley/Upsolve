package com.upsolve.upsolve.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upsolve.upsolve.entity.Blog;

public interface BlogDao  extends JpaRepository<Blog, Integer> {

}
