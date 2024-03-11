package com.exam.taskNew.model.DTO;

public class Github {
    private String sha;

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public GithubCommit getCommit() {
        return commit;
    }

    public void setCommit(GithubCommit commit) {
        this.commit = commit;
    }

    private GithubCommit commit;
}
