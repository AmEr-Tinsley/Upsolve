package com.upsolve.upsolve.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.upsolve.upsolve.entity.Link;
import com.upsolve.upsolve.entity.Problem;
import com.upsolve.upsolve.service.LinkService;
import com.upsolve.upsolve.service.ProblemService;
import com.upsolve.upsolve.user.AddProblemChecker;
import com.upsolve.upsolve.user.LinkChecker;

@Controller
public class ProblemController {
    
    @Autowired
    private ProblemService problemService;
    
    @Autowired
    private LinkService linkService;
    
    private Logger logger = Logger.getLogger(getClass().getName());
    
    /*@InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }  */ 
    
    @GetMapping("/showAddProblemForm")
    public String showAddProblemForm(Model theModel,@RequestParam(name="user") String user) {
        
    	
        theModel.addAttribute("AddProblemChecker", new AddProblemChecker());
        
        theModel.addAttribute("user",user);
        
        System.out.println("I AM " + user);
        
        return "addproblem";
    }

    @PostMapping("/processAddProblemForm")
    public String processAddproblemForm(
                @Valid @ModelAttribute("AddProblemChecker") AddProblemChecker addChecker, 
                BindingResult theBindingResult, 
                Model theModel,
                @RequestParam("user") String user) {
    	
    	
        
        String link = addChecker.getLink();
        
        
         if (theBindingResult.hasErrors()){
        	Map<Problem,String> Problems = new HashMap<>();
 			List<Problem> theProblems = problemService.getall();
 			List<Link> theLinks = linkService.getall();
 			
             for(int i = 0;i<theProblems.size();i++) {
             	Problem curr = theProblems.get(i);
             	Problems.put(curr, "notyet");
             }
             theModel.addAttribute("problems", Problems);
 			 theModel.addAttribute("links",theLinks);
 			 theModel.addAttribute("LinkChecker", new LinkChecker());

 			 theModel.addAttribute("errr", 1);
        	 return "home";          
        }

        Problem existing = problemService.findBylink(link);
        if (existing != null){
        	Map<Problem,String> Problems = new HashMap<>();
			List<Problem> theProblems = problemService.getall();
			List<Link> theLinks = linkService.getall();
			
            for(int i = 0;i<theProblems.size();i++) {
            	Problem curr = theProblems.get(i);
            	Problems.put(curr, "notyet");
            }
            theModel.addAttribute("problems", Problems);
			theModel.addAttribute("links",theLinks);
			theModel.addAttribute("AddProblemChecker", new AddProblemChecker());
			theModel.addAttribute("LinkChecker", new LinkChecker());

            theModel.addAttribute("AddProblemError", "Problem already exists.");
            theModel.addAttribute("errr", 1);
            logger.warning("Problem already exists.");
            return "home"; 
        }
        
        addChecker.setAddedby(user);
      
        problemService.save(addChecker);
        
        logger.info("Successfully added Problem : " + link);
        
        return "redirect:";     
    }
    
    @GetMapping("/delete")
	public String delete(@RequestParam("problemlink") String link) {
		
		problemService.deleteBylink(link);
		
		return "redirect:";
		
	}
    
    @GetMapping("/showUpdateproblem")
    public String showFormForUpdate(@RequestParam("link") String link,
			Model theModel) {
        
    	Problem problem = problemService.findBylink(link);
    	
    	Map<Problem,String> Problems = new HashMap<>();
		List<Problem> theProblems = problemService.getall();
		List<Link> theLinks = linkService.getall();
		
        for(int i = 0;i<theProblems.size();i++) {
        	Problem curr = theProblems.get(i);
        	Problems.put(curr, "notyet");
        }
        theModel.addAttribute("problems", Problems);
		theModel.addAttribute("links",theLinks);
		theModel.addAttribute("AddProblemChecker", new AddProblemChecker());
		theModel.addAttribute("LinkChecker", new LinkChecker());

        theModel.addAttribute("updateproblem", 1);
        theModel.addAttribute("errrr", 0);
        theModel.addAttribute("errr", 0);
        theModel.addAttribute("updatelink", 0);
        theModel.addAttribute("link",new Link());
		theModel.addAttribute("problem", problem);
		
        
        return "home";
    }
    
    @PostMapping("/processUpdateProblem")
    public String processUpdate(
                @Valid @ModelAttribute("AddProblemChecker") AddProblemChecker addChecker, 
                BindingResult theBindingResult, 
                Model theModel
                ,@RequestParam("user") String user) {
        
        String link = addChecker.getLink();
        logger.info("Processing updating for: " + link);
        
         if (theBindingResult.hasErrors()){
             return showFormForUpdate(link,theModel);
          }

       
        addChecker.setAddedby(user);
        problemService.save(addChecker);
        
        logger.info("Successfully added Problem : " + link);
        
        return "redirect:";     
    }
    
    
    
    
  
}
