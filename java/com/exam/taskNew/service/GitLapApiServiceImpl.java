package com.exam.taskNew.service;



import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exam.taskNew.model.Commit;

import com.exam.taskNew.repository.CommitRepository;


@Service
public class GitLapApiServiceImpl implements GitLabApiService {
    private final String GITHUB_API_BASE_URL = "https://api.github.com";
    private final RestTemplate restTemplate;
    private final CommitRepository commitRepository;

   
    
    
    public GitLapApiServiceImpl(RestTemplate restTemplate,CommitRepository commitRepository) {
        this.restTemplate = restTemplate;
		this.commitRepository = commitRepository;
		
    }

    @Override
    public List<Commit> getCommits(String username, String repositoryName, String sinceDate)
    {
    	HttpHeaders headers = new HttpHeaders();
	 //   headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.set("Authorization", "Bearer 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");
	   // HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        String apiUrl = String.format("%s/repos/%s/%s/commits?since=2023-02-01T00:00:00Z", GITHUB_API_BASE_URL, username, repositoryName, sinceDate);
        
       // ResponseEntity<Commit[]> response = restTemplate.getForEntity(apiUrl, Commit[].class);

			HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
			
			ResponseEntity<String> response = restTemplate.exchange(
			apiUrl, HttpMethod.GET, requestEntity, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println(response.getBody());
            return Collections.emptyList();
        } else {
            // Handle error
            return Collections.emptyList();
        }
     
    }
    @Override
    public List<Commit> getAllCommits(String username, String repositoryName) 
    {
        String apiUrl = String.format("%s/repos/%s/%s/commits", GITHUB_API_BASE_URL, username, repositoryName);

        ResponseEntity<Commit[]> response = restTemplate.getForEntity(apiUrl, Commit[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            List<Commit> commits = Arrays.asList(response.getBody());

            // GitHub'dan alınan commit verilerini veritabanına kaydet
            commitRepository.saveAll(commits);

            return commits;
        } else {
            // Handle error
            return Collections.emptyList();
        }
    }

	@Override
	public Commit getCommitById(Long id) {
		java.util.Optional<Commit> optionalCommit = this.commitRepository.findById(id);
        return optionalCommit.orElse(null);
	}

	
}