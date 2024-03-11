package com.exam.taskNew.model.DTO;

public class GithubAuthor {
	    private String login;
	    private String email;
	    private String name;
	    private String date;
	
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public void getLogin(String name) {
        this.name = name;
    }
 public String toString() {
	 return name;
 }

	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
}
