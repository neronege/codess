package com.exam.taskNew.model.DTO;



public class GithubCommit {
    public GithubAuthor getAuthor() {
        return author;
    }

    public void setAuthor(GithubAuthor author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


	public String getPatch() {
		return patch;
	}

	public void setPatch(String patch) {
		this.patch = patch;
	}


	private GithubAuthor author;
    private String message;
    private String patch;
}
