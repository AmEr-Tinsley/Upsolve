package com.upsolve.upsolve.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.upsolve.upsolve.entity.Blog;
import com.upsolve.upsolve.entity.Comment;
import com.upsolve.upsolve.service.BlogService;
import com.upsolve.upsolve.service.CommentService;
import com.upsolve.upsolve.user.AddPostChecker;

@Controller

public class BlogController {
	
	@Autowired
	private BlogService blogservice;
	
	@Autowired
	private CommentService commentservice;
	
    private Logger logger = Logger.getLogger(getClass().getName());

	@GetMapping("/blog")
	public String ShowBlog( Model theModel,
            @RequestParam("id") Integer id) {
		Blog a = blogservice.findByid(id);
		theModel.addAttribute("blog",a);
		Set<Comment> commentss = a.getComments();
		List<Comment> comments = new ArrayList<>();
		for(Comment c : commentss)
			comments.add(c);
		Collections.sort(comments);
		
		
		Integer n = comments.size();
		theModel.addAttribute("Comment",new Comment());
		theModel.addAttribute("comments",comments);
		theModel.addAttribute("n",n);
		return "blog";
	}
	
	@GetMapping("/writeblog")
	public String Showlog(Model theModel,@RequestParam(name="user") String user) {
		 theModel.addAttribute("AddPostChecker", new AddPostChecker());
	     theModel.addAttribute("user",user);
		return "writeblog";
	}
	
	@PostMapping("/processAddBlog")
    public String processAddblogForm(
                @Valid @ModelAttribute("AddPostChecker") AddPostChecker addChecker, 
                BindingResult theBindingResult, 
                Model theModel,
                @RequestParam("user") String user) {
		
		
		 addChecker.setAddedby(user);
		 blogservice.save(addChecker);
	     logger.info("Successfully added Problem : ");
	     List<Blog> theBlogs = blogservice.getall();
		theModel.addAttribute("blogs",theBlogs);

		 return "blogs";
	}
	
	@PostMapping("/processAddComment")
	public String processAddComment(
			@Valid @ModelAttribute("Comment") Comment comment, 
            BindingResult theBindingResult, 
            Model theModel,
            @RequestParam("id") Integer id) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username;
			if (principal instanceof UserDetails) {
			  username = ((UserDetails)principal).getUsername();
			} else {
			  username = principal.toString();
			}
			comment.setAddedby(username);
			Blog a = blogservice.findByid(id);
			comment.setBlogid(id);
			comment.setBlog(a);
			
			a.getComments().add(comment);
			
			blogservice.savee(a);
		
			return ShowBlog(theModel,id);
	}
	
	
	@GetMapping("/blogs")
	public String showblogs(Model theModel) {
		List<Blog> theBlogs = blogservice.getall();
		theModel.addAttribute("blogs",theBlogs);
		return "blogs";
	}
	
	 @GetMapping("/deleteblog")
	 public String delete(@RequestParam("id") Integer id) {
			
			blogservice.deleteById(id);
			
			return "redirect:/blogs";
			
	}
	 @GetMapping("/updateblog")
	 public String updateblog(Model theModel,@RequestParam(name="id") Integer id) {
			
			//blogservice.deleteById(id);
		 Blog a = blogservice.findByid(id);
		 theModel.addAttribute("AddPostChecker", new AddPostChecker());
	     theModel.addAttribute("blog",a);
			
			return "updateblog";
			
	}
	 
	 @PostMapping("/processUpdateBlog")
	    public String processUpdateblog(
	                @Valid @ModelAttribute("AddPostChecker") AddPostChecker addChecker, 
	                BindingResult theBindingResult, 
	                Model theModel
	                ,@RequestParam("user") String user) {
		 
		    Integer id = addChecker.getId();
	        logger.info("Processing updating for: " + id);
	        
	         if (theBindingResult.hasErrors()){
	             return updateblog(theModel,id);
	          }

	        blogservice.save(addChecker);
	        
	        logger.info("Successfully added Problem : " + id);
	        
	        return "redirect:/blogs";     
	 }
	 
	 @GetMapping("/deletecomment")
	 public String deletecomment(@RequestParam("id") Integer id , Model theModel) {
			
		 	Comment wa = commentservice.findByid(id);
		 	Integer a = wa.getBlogid();
			commentservice.deleteById(id);
			return ShowBlog(theModel,a); 
			
	}

	
}
