package com.exam.taskNew.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.exam.taskNew.model.*;
import com.exam.taskNew.repository.*;

@Service
public class DeveloperServiceImpl implements DeveloperService {
    private final GitHubApiService gitHubapiService;
    private final GitLabApiService gitLabapiService;
    private final DeveloperRepository developerRepository;

    
    public DeveloperServiceImpl(GitHubApiService gitHubapiService,GitLabApiService gitLabapiService, DeveloperRepository developerRepository) {
        
    
		this.gitHubapiService = gitHubapiService;
		this.gitLabapiService = gitLabapiService;
		this.developerRepository = developerRepository;
    }

    @Override
    public Developer getDeveloperDetails(String username,String repositoryName) {
        // Veritabanından geliştirici detaylarını al
        Developer developer = developerRepository.findByUsername(username);

        // GitHub ve GitLab'dan alınan son 1 aydaki commitleri ekleyin
        if(this.gitHubapiService != null) {
        	 List<Commit> hubcommits =this.gitHubapiService.getAllCommits(username,repositoryName);
        	 developer.setCommits(hubcommits);
        }
        if(this.gitLabapiService != null) {
       List<Commit> labcommits =this.gitLabapiService.getAllCommits(username,repositoryName);
       	 developer.setCommits(labcommits);
       }
     
        return developer;
    }

	@Override
	public Developer getDeveloper(Long id) {
	    Optional<Developer> optionalDeveloper = developerRepository.findById(id);
	    return optionalDeveloper.orElse(null);
	}
}
