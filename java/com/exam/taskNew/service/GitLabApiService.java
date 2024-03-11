package com.exam.taskNew.service;

import java.util.List;

import com.exam.taskNew.model.Commit;


public interface GitLabApiService{
	List<Commit> getCommits(String username, String repositoryName, String sinceDate);
	List<Commit> getAllCommits(String username, String repositoryName); 
	Commit getCommitById(Long id);
}
