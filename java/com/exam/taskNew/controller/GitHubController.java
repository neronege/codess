package com.exam.taskNew.controller;

import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.exam.taskNew.model.Commit;
import com.exam.taskNew.model.Developer;
import com.exam.taskNew.service.DeveloperService;
import com.exam.taskNew.service.GitHubApiService;


@Controller
@RequestMapping("/github.com")
public class GitHubController {
    
	@Autowired
    private final GitHubApiService gitHubApiService;
	private final DeveloperService developerService;

    public GitHubController(GitHubApiService gitHubApiService, DeveloperService developerService) {
        this.gitHubApiService = gitHubApiService;
        this.developerService = developerService;
    }

    @GetMapping("/")
    public String home() {
        System.out.println("adsdasdsadsdada");
        return "index";
    }

    @GetMapping("/repos/{owner}/{repo}/commits")
    public String getCommits(
            @PathVariable String owner,
            @PathVariable String repo,
            Model model) {
        List<Commit> commits;
        List<Developer> developers;
        if (owner != null) {
        	commits= gitHubApiService.getCommits(owner, repo);
            developers = gitHubApiService.getDevelopers(owner,repo); 
        } 
        else {
         return "error";
        }

        model.addAttribute("commits", commits);
        model.addAttribute("developers",developers);
        return "commit-list";
    }
    @GetMapping("/developer/{id}")
    public String getDeveloperDetail(@PathVariable Long id, Model model) {
    	
    	
        Developer developer = developerService.getDeveloper(id);
        
        model.addAttribute("developer", developer);
        
        return "developer";
    }
    @GetMapping("/commit/{id}")
    public String getCommitDetail(@PathVariable Long id, Model model) {
  
        Commit commit = gitHubApiService.GetCommitDetail(id);
        System.out.println("Commit: " + commit);
        model.addAttribute("commit", commit);
        return "commit-detail";
    }
    @RequestMapping("/error")
    public String handleError() {
        return "error";
    }

    public String getErrorPath() {
        return "/error";
    }
}
