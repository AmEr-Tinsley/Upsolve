package com.upsolve.upsolve.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.upsolve.upsolve.entity.User;
import com.upsolve.upsolve.service.UserService;
import com.upsolve.upsolve.user.RegistrationChecker;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    
    @Autowired
    private UserService userService;
    
    private Logger logger = Logger.getLogger(getClass().getName());
    
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }   
    
    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model theModel) {
        
        theModel.addAttribute("RegistrationChecker", new RegistrationChecker());
        
        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
                @Valid @ModelAttribute("RegistrationChecker") RegistrationChecker theRegistrationChecker, 
                BindingResult theBindingResult, 
                Model theModel) {
    	
    	System.out.println(theRegistrationChecker);
        String userName = theRegistrationChecker.getUserName();
        logger.info("Processing registration form for: " + userName);
        
         if (theBindingResult.hasErrors()){
             return "registration-form";
          }

        User existing = userService.findByUserName(userName);
        if (existing != null){
            theModel.addAttribute("RegistrationChecker", new RegistrationChecker());
            theModel.addAttribute("registrationError", "User name already exists.");

            logger.warning("User name already exists.");
            return "registration-form";
        }
        
        userService.save(theRegistrationChecker);
        
        logger.info("Successfully created user: " + userName);
        
        return "login";     
    }
}
