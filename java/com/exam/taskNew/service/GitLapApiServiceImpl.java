package com.exam.taskNew.service;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;


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
import com.exam.taskNew.model.DTO.GitLabCommit;


@Service
public class GitLapApiServiceImpl implements GitLabApiService{
	private final String gitLabApiBaseUrl = "https://gitlab.example.com/api/v4";
    private final RestTemplate restTemplate;
    private final CommitRepository commitRepository;
    private final DeveloperRepository developerRepository;
    private final String accessToken = "Bearer glpat-c-H3imEf6QUsGhjs9Bv1";
    
    public GitLapApiServiceImpl(RestTemplate restTemplate, DeveloperRepository developerRepository,
    		CommitRepository commitRepository) {
        this.restTemplate = restTemplate;
		this.commitRepository = commitRepository;
        this.developerRepository = developerRepository;
    }

  
    @Override
    public List<Commit>getCommits(String projectId) {
      
        // URL oluşturma
        String apiUrl = String.format("%s/projects/%s/repository/commits", gitLabApiBaseUrl, projectId);

        // HTTP başlıklarını ayarlama
        HttpHeaders headers = new HttpHeaders();
        //headers.set("Authorization", "Bearer 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");
        headers.set("Authorization", accessToken);
        // HTTP isteği oluşturma
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // REST çağrısı
        ResponseEntity<GitLabCommit[]> response = restTemplate.exchange(
                apiUrl, HttpMethod.GET, requestEntity, GitLabCommit[].class);

        // API yanıtını işleme
        if (response.getStatusCode() == HttpStatus.OK) {
            GitLabCommit[] gitLabCommits = response.getBody();

            if (gitLabCommits != null && gitLabCommits.length > 0) {
                List<Commit> commits = new ArrayList<>();

                for (GitLabCommit gitLabCommit : gitLabCommits) {
                    Commit commit = new Commit();
                    commit.setMessage(gitLabCommit.getMessage());
                    ;
                    commit.setAuthor(gitLabCommit.getAuthorName());
                    commit.setTimestamp(gitLabCommit.getCommittedDate());

                    commits.add(commit);
                }

                // Burada commits listesini kaydetme işlemini istediğiniz şekilde gerçekleştirebilirsiniz

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
	public List<Developer>getDevelopers(String projectId) {
		 // URL oluşturma
        String apiUrl = String.format("%s/projects/%s/repository/commits", gitLabApiBaseUrl, projectId);

        // HTTP başlıklarını ayarlama
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);

        // HTTP isteği oluşturma
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // REST çağrısı
        ResponseEntity<GitLabCommit[]> response = restTemplate.exchange(
                apiUrl, HttpMethod.GET, requestEntity, GitLabCommit[].class);

        // API yanıtını işleme
        if (response.getStatusCode() == HttpStatus.OK) {
            GitLabCommit[] gitLabCommits = response.getBody();

            if (gitLabCommits != null && gitLabCommits.length > 0) {
	   			
	   			    List<Developer> developers = new ArrayList<>();
	   			 
	   			    for (GitLabCommit gitLabCommit : gitLabCommits) {
	   			    	Developer developer = new Developer();
	   			    	developer.setUsername(gitLabCommit.getAuthorName());
	   			    	developer.setEmail(gitLabCommit.getAuthorEmail());
	   			    	developer.setMessage(gitLabCommit.getMessage());
	   			      if(!developers.contains(developer.hashCode())) {
		   	            developers.add(developer);
	   			      }
	   			    }
	   			   developerRepository.saveAll(developers);
	   		
	               return developers;
            } else {
                return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }



	@Override
	public List<Commit> GetCommitDetail(String projectId) {
		// URL oluşturma
        String apiUrl = String.format("%s/projects/%s/repository/commits/main", gitLabApiBaseUrl, projectId);

        // HTTP başlıklarını ayarlama
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);

        // HTTP isteği oluşturma
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // REST çağrısı
        ResponseEntity<GitLabCommit[]> response = restTemplate.exchange(
                apiUrl, HttpMethod.GET, requestEntity, GitLabCommit[].class);

        // API yanıtını işleme
        if (response.getStatusCode() == HttpStatus.OK) {
            GitLabCommit[] gitLabCommits = response.getBody();

            if (gitLabCommits != null && gitLabCommits.length > 0) {
                List<Commit> commits = new ArrayList<>();

                for (GitLabCommit gitLabCommit : gitLabCommits) {
                    Commit commit = new Commit();
                    commit.setMessage(gitLabCommit.getMessage());
                    commit.setHash(gitLabCommit.getAuthor().getSha());
                    commit.setAuthor(gitLabCommit.getAuthorName());
                    commit.setTimestamp(gitLabCommit.getCommittedDate());
                  
                    commits.add(commit);
                }

                // Burada commits listesini kaydetme işlemini istediğiniz şekilde gerçekleştirebilirsiniz

                return commits;
            } else {
                return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }

	@Override
	public Commit getCommitById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
 }




