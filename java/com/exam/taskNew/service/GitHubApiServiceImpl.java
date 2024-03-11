package com.exam.taskNew.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exam.taskNew.model.*;


import com.exam.taskNew.repository.CommitRepository;
import com.exam.taskNew.repository.DeveloperRepository;
import com.exam.taskNew.model.DTO.Github;

@Service
public class GitHubApiServiceImpl implements GitHubApiService{
	private final String GITHUB_API_BASE_URL = "https://api.github.com";
    private final RestTemplate restTemplate;
    private final CommitRepository commitRepository;
    private final DeveloperRepository developerRepository;
    
    
    public GitHubApiServiceImpl(RestTemplate restTemplate, DeveloperRepository developerRepository,
    		CommitRepository commitRepository) {
        this.restTemplate = restTemplate;
		this.commitRepository = commitRepository;
        this.developerRepository = developerRepository;
    }

    @SuppressWarnings("unlikely-arg-type")
	public List<Commit> getCommits(String username, String repositoryName) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer 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");

        String apiUrl = String.format("%s/repos/%s/%s/commits?since=2023-02-01T00:00:00Z", GITHUB_API_BASE_URL, username, repositoryName);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Github[]> response = restTemplate.exchange(
                apiUrl, HttpMethod.GET, requestEntity, Github[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Github[] githubCommits = response.getBody();

            if (githubCommits != null && githubCommits.length > 0) {
                System.out.println("API'den gelen veri: " + Arrays.toString(githubCommits));

                List<Commit> commits = new ArrayList<>();
                
                for (Github github : githubCommits) {
                    Commit commit = new Commit();
                    commit.setMessage(github.getCommit().getMessage());
                    commit.setHash(github.getSha());
                    commit.setAuthor(github.getCommit().getAuthor().getName());
                    commit.setTimestamp(github.getCommit().getAuthor().getDate());
                    commit.setPatch(github.getCommit().getPatch());
                   if(!commits.contains(commit.hashCode())) {
                	      commits.add(commit);
                      }
                }

                commitRepository.saveAll(commits);

                return commits;
            } else {
             
                return Collections.emptyList();
            }
        } else {
            
            return Collections.emptyList();
        }
    }

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public List<Developer> getDevelopers(String username, String repositoryName) {
		HttpHeaders headers = new HttpHeaders();
	   	 //   headers.setContentType(MediaType.APPLICATION_JSON);
	   	    headers.set("Authorization", "Bearer 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");
	   	   // HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	           String apiUrl = String.format("%s/repos/%s/%s/commits?since=2023-02-01T00:00:00Z", GITHUB_API_BASE_URL, username, repositoryName);
	           
	          // ResponseEntity<Commit[]> response = restTemplate.getForEntity(apiUrl, Commit[].class);

	   			HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
	   			
	   		
	   			System.out.println(restTemplate.exchange(
	    				   apiUrl, HttpMethod.GET, requestEntity,String.class ));
	   			ResponseEntity<Github[]> response = restTemplate.exchange(
	   				   apiUrl, HttpMethod.GET, requestEntity, Github[].class);
	   			System.out.println("	ResponseEntity<GithubCommit[]> response");
	   			if (response.getStatusCode() == HttpStatus.OK) {
	   		     System.out.println(response.getBody() );
	   		     
	   			  Github[] githubCommits = response.getBody();
	   			
	   			    List<Developer> developers = new ArrayList<>();
	   			 
	   			    for (Github github : githubCommits) {
	   			    	Developer developer = new Developer();
	   			    	developer.setUsername(github.getCommit().getAuthor().getName());
	   			    	developer.setEmail(github.getCommit().getAuthor().getEmail());
	   			    	developer.setMessage(github.getCommit().getMessage());
	   			      if(!developers.contains(developer.hashCode())) {
		   	            developers.add(developer);
	   			      }
	   			    }
	   			   developerRepository.saveAll(developers);
	   		
	               return developers;
	           } else {
	               // Handle error
	               return Collections.emptyList();
	           }
	}

	@Override
	public List<Commit> getAllCommits(String username, String repositoryName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Commit GetCommitDetail(Long Id) {
		 Optional<Commit> commitOptional  = commitRepository.findById(Id);
		 System.out.println("!!!!!!!!!!!!");
		 System.out.println("!!!!!!!!!!!!");
		 System.out.println(commitOptional);
		 return commitOptional .orElse(null);
	}


}

