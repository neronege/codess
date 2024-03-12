package com.exam.taskNew.service;

import java.util.List;

import com.exam.taskNew.model.Commit;
import com.exam.taskNew.model.Developer;


public interface GitLabApiService{
	List<Commit> getCommits(String projectId);
	List<Developer> getDevelopers(String projectId);
	List<Commit>GetCommitDetail(String projectId);
	Commit getCommitById(Long Id);

}
