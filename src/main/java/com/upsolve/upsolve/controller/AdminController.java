package com.upsolve.upsolve.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.upsolve.upsolve.dao.UserDao;
import com.upsolve.upsolve.entity.User;
import com.upsolve.upsolve.service.UserService;
@Controller
public class AdminController {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserService userservice;
	@GetMapping("/showAddTrainerForm")
    public String showApprovePage(Model theModel) {
		List<User> theUsers = userservice.getall();
		List<User> ret = new ArrayList<User>();
		for(int i=0;i<theUsers.size();i++) {
			for(com.upsolve.upsolve.entity.Role r : theUsers.get(i).getRoles()) {
				if (r.getName().equals("ROLE_TRAINER") && !theUsers.get(i).isApproved())
					ret.add(theUsers.get(i));
			}
		}
		theModel.addAttribute("trainers", ret);
        
        return "requests";
    }
	
	@GetMapping("/admin")
    public String showAdminPage(Model theModel) {
		List<User> theUsers = userservice.getall();
		List<User> Trainer = new ArrayList<User>();
		List<User> Trainee = new ArrayList<User>();
		for(int i=0;i<theUsers.size();i++) {
			for(com.upsolve.upsolve.entity.Role r : theUsers.get(i).getRoles()) {
				if (r.getName().equals("ROLE_TRAINER") && theUsers.get(i).isApproved())
					Trainer.add(theUsers.get(i));
				else if (r.getName().equals("ROLE_TRAINEE"))
					Trainee.add(theUsers.get(i));
			}
		}
		theModel.addAttribute("trainers", Trainer);
		theModel.addAttribute("trainees",Trainee);
        return "admin";
    }
	
	@GetMapping("/deleteUser")
	public String delete(@RequestParam("username") String link) {
		
		userservice.deleteByUserName(link);
		
		return "redirect:/admin";
		
	}
	@GetMapping("/approveUser")
	public String approve(@RequestParam("username") String name) {
		
		User x = userservice.findByUserName(name);
		
		System.out.println(x);
		x.setApproved(true);
		//System.out.println(x);
		userDao.update(x);
		return "redirect:/showAddTrainerForm";
		
	}
	@GetMapping("/declineUser")
	public String decline(@RequestParam("username") String name) {
		
		userservice.deleteByUserName(name);
		
		return "redirect:/showAddTrainerForm";
		
	}
}
