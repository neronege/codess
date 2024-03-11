package com.exam.taskNew.service;

import java.util.List;



import com.exam.taskNew.model.Commit;
import com.exam.taskNew.model.Developer;

import ch.qos.logback.core.model.Model;



public interface GitHubApiService{
	List<Commit> getCommits(String username, String repositoryName);
	List<Commit> getAllCommits(String username, String repositoryName); 
	List<Developer> getDevelopers(String username, String repositoryName);
	Commit GetCommitDetail(Long id);
	
}


