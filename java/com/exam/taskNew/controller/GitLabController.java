package com.exam.taskNew.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import com.exam.taskNew.model.Commit;
import com.exam.taskNew.model.Developer;
import com.exam.taskNew.service.GitLabApiService;

@Controller
@RequestMapping("/api/v4")
public class GitLabController {
    private final GitLabApiService gitLabApiService;

    
    public GitLabController(GitLabApiService gitLabApiService)
    {
        this.gitLabApiService = gitLabApiService;
    }
    

    @GetMapping("/projects/{PROJECT_ID}/repository/commits/main")
    public String getDeveloper(
            @PathVariable String PROJECT_ID,
            Model model) {
//        List<Commit> commits;
        List<Developer> developers;
        System.out.println("asdadadadaddasd");
        if (PROJECT_ID != null) {
//            commits = gitLabApiService.getCommits(PROJECT_ID);
            developers = gitLabApiService.getDevelopers(PROJECT_ID);
        } else {
            return "error";
        }

  //      model.addAttribute("commits", commits);
        model.addAttribute("developers", developers);
        return "developer";
    }
    @GetMapping("/projects/{PROJECT_ID}/repository/commits")
    public String getCommits(
            @PathVariable String PROJECT_ID,
            Model model) {
        List<Commit> commits;
//        List<Developer> developers;
        System.out.println("ffffffffffffffffffffffffff");
        if (PROJECT_ID != null) {
           commits = gitLabApiService.getCommits(PROJECT_ID);
           //         developers = gitLabApiService.getDevelopers(PROJECT_ID);
        } else {
            return "error";
        }

        	model.addAttribute("commits", commits);
       //      model.addAttribute("developers", developers);
        return "commit-list";
    }
    @GetMapping("/commit/{id}")
    public String getCommitDetail(@PathVariable Long id, Model model) {
        
        Commit commit = gitLabApiService.getCommitById(id);
     
        if (commit == null) {
            return "commit-not-found";
        }
        
        model.addAttribute("commit", commit);

        return "commit-detal";
    }
}
