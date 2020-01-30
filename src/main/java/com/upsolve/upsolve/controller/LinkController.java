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
public class LinkController {
    
    @Autowired
    private LinkService linkService;
    
    @Autowired
    private ProblemService problemService;
    
    private Logger logger = Logger.getLogger(getClass().getName());
    
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    } 
    
    @GetMapping("/deletelink")
	public String delete(@RequestParam("link") String link) {
		
		linkService.deleteBylink(link);
		
		return "redirect:";
		
	}
    
    @GetMapping("/showAddLinkForm")
    public String showAddlinkForm(Model theModel,@RequestParam(name="user") String user) {
        
        theModel.addAttribute("LinkChecker", new LinkChecker());
        theModel.addAttribute("user",user);
        return "addlink";
    }

    @PostMapping("/processAddLinkForm")
    public String processAddLink(
                @Valid @ModelAttribute("LinkChecker") LinkChecker addChecker, 
                BindingResult theBindingResult, 
                Model theModel,
                @RequestParam("user") String user) {
        
        String link = addChecker.getLink();
        logger.info("Processing Adding for: " + link);
        
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
  			 
  			theModel.addAttribute("AddProblemChecker", new AddProblemChecker());
  			 theModel.addAttribute("errrr", 1);
         	 return "home";       
            }

        Link existing = linkService.findBylink(link);
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
			theModel.addAttribute("LinkChecker", new LinkChecker());
			theModel.addAttribute("AddProblemChecker", new AddProblemChecker());
	        theModel.addAttribute("AddLinkError", "Link already exists.");
	        theModel.addAttribute("errrr", 1);
            logger.warning("Link already exists.");
            return "home";
        }
        
        addChecker.setAddedBy(user);
        linkService.save(addChecker);
        
        
        logger.info("Successfully added Link : " + link);
        
        return "redirect:";     
    }
    
    @GetMapping("/showUpdatelink")
    public String showFormForUpdate(@RequestParam("link") String link,
			Model theModel) {
        
    	Link linkk = linkService.findBylink(link);
		
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

        theModel.addAttribute("updateproblem", 0);
        theModel.addAttribute("errrr", 0);
        theModel.addAttribute("errr", 0);
        theModel.addAttribute("updatelink", 1);
		
		theModel.addAttribute("problem", new Problem());
		
		//theModel.addAttribute("errr", 1);
		theModel.addAttribute("link",linkk);
        
        return "home";		
}
    
    @PostMapping("/processUpdateLink")
    public String processUpdate(
                @Valid @ModelAttribute("AddLinkChecker") LinkChecker addChecker, 
                BindingResult theBindingResult, 
                Model theModel,
                @RequestParam("user") String user) {
        
        String link = addChecker.getLink();
        logger.info("Processing registration form for: " + link);
        
         if (theBindingResult.hasErrors()){
             return showFormForUpdate(link,theModel);
          }

       addChecker.setAddedBy(user);
        
        linkService.save(addChecker);
        
        logger.info("Successfully added Problem : " + link);
        
        return "redirect:";     
    }
}
