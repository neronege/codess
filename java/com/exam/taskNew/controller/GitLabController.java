package com.exam.taskNew.controller;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exam.taskNew.model.Commit;
import com.exam.taskNew.service.GitLabApiService;

@RestController
@RequestMapping("/gitlab")
public class GitLabController {
    private final GitLabApiService gitLabApiService;

    
    public GitLabController(GitLabApiService gitLabApiService)
    {
        this.gitLabApiService = gitLabApiService;
    }

    @GetMapping("/commits")
    public ResponseEntity<List<Commit>> getCommits(@RequestParam String username, @RequestParam String repositoryName, @RequestParam(required = false) String sinceDate)
    {
        List<Commit> commits;

        if (sinceDate != null) {
            commits = gitLabApiService.getCommits(username, repositoryName, sinceDate);
        } else {
            commits = gitLabApiService.getAllCommits(username, repositoryName);
        }

        return new ResponseEntity<>(commits, HttpStatus.OK);
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
