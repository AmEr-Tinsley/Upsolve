package com.upsolve.upsolve.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upsolve.upsolve.entity.Comment;

public interface CommentDao extends JpaRepository<Comment, Integer> {

}
