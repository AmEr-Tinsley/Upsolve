package com.upsolve.upsolve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.upsolve.upsolve.service.UserService;


@Controller
public class LoginController {
	
	@Autowired
    private UserService userService;
	
	@GetMapping("showMyLoginPage")
	public String showMyLoginPage() {
		
		return "login";
		
	}
	
	@PostMapping("showMyLoginPage")
	public String showMyoginPage(Model theModel) {
		return "login";
		
	}
		
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		
		return "access-denied";
		
	}
	@GetMapping("/notapproved")
	public String notApproved(Model theModel) {
		System.out.println("WTFFFFF");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		
		username = ((UserDetails)principal).getUsername();
		
		boolean approved = userService.findByUserName(username).isApproved() ;
		System.out.println(approved);
		if(!approved)theModel.addAttribute("notapproved",1);
		
		return "login";
	}
	
}









