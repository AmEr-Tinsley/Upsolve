package com.upsolve.upsolve.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.upsolve.upsolve.entity.Link;
import com.upsolve.upsolve.entity.Problem;
import com.upsolve.upsolve.service.LinkService;
import com.upsolve.upsolve.service.ProblemService;
import com.upsolve.upsolve.user.AddProblemChecker;
import com.upsolve.upsolve.user.LinkChecker;

@Controller

public class DemoController {
	
	@Autowired
	private ProblemService problemservice;
	
	@Autowired
	private LinkService linkservice;


	
	public static JSONArray CodeForcesUserStatusParser(String UserHandle, int from, int count) throws IOException, ParseException {
        String URLString = "https://codeforces.com/api/user.status?handle=" + UserHandle + "&from=" + from + "&count=" + count;
        URL urlForGetRequest = new URL(URLString);
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        JSONArray jsonArray = null;
        int responseCode = conection.getResponseCode();
        JSONParser parse = new JSONParser();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }
            in.close();
            JSONObject jobj = (JSONObject) parse.parse(response.toString());
            jsonArray = (JSONArray) jobj.get("result");
            //GetAndPost.POSTRequest(response.toString());
        } else {
            System.out.println("GET NOT WORKED");
        }
        return jsonArray;
}

	
	@GetMapping("/")
	public String showHome(Model theModel) throws IOException,ParseException {
			//System.out.println(user);
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username;
			if (principal instanceof UserDetails) {
			  username = ((UserDetails)principal).getUsername();
			} else {
			  username = principal.toString();
			}
			System.out.println(username);
			Map<Problem,String> Problems = new HashMap<>();
			List<Problem> theProblems = problemservice.getall();
			List<Link> theLinks = linkservice.getall();
			
			try {
				JSONArray JA = (DemoController.CodeForcesUserStatusParser(username, 1,500));
				for(int i = 0;i<theProblems.size();i++) {
					Problem curr = theProblems.get(i);
					String status ="notyet";
					if(JA != null)
					for (Object jo :JA) {
							
			                JSONObject problem = (JSONObject) ((JSONObject) jo).get("problem");
			                String problemContestID = problem.get("contestId").toString();
			                String submissionStatus = (String) ((JSONObject) jo).get("verdict");
			                String index = problem.get("index").toString();
			                String link = "https://codeforces.com/problemset/problem/"+problemContestID+"/"+index;
			                String link2 = "https://codeforces.com/contest/"+problemContestID+"/problem/"+index;
			                String link3 = "codeforces.com/contest/"+problemContestID+"/problem/"+index;
			                String link4 = "codeforces.com/problemset/problem/"+problemContestID+"/"+index;
			                if(curr.getLink().equals(link) || curr.getLink().equals(link2) || curr.getLink().equals(link3) || curr.getLink().equals(link4)) {
			                	
			                	status = submissionStatus;
			                	if(status.equals("OK"))break;
			                }
		             }
			
					 Problems.put(curr, status);
					// System.out.println(curr+" "+status);
            }
	        } catch (ParseException e) {
	            System.out.println(e);
	            System.out.println("Could Not Retrieve Values");
	        }
			
			theModel.addAttribute("problems", Problems);
			theModel.addAttribute("links",theLinks);
			theModel.addAttribute("AddProblemChecker", new AddProblemChecker());
			theModel.addAttribute("LinkChecker", new LinkChecker());
			theModel.addAttribute("problem", new Problem());
			theModel.addAttribute("updateproblem", 0);
	        theModel.addAttribute("errrr", 0);
	        theModel.addAttribute("errr", 0);
	        theModel.addAttribute("link", new Link());
			return "home";
	}
	
	
	
}










