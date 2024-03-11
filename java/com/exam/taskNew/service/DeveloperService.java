package com.exam.taskNew.service;
import com.exam.taskNew.model.Developer;

public interface DeveloperService {
	 Developer getDeveloperDetails(String username,String repositoryName);
	 Developer getDeveloper(Long id);
}
