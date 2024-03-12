package com.exam.taskNew.model.DTO;

public class GitLabCommit {
private String title;
private String authorName;
private String authorEmail;
private String authoredDate;
private String committerName;
private String committerEmail;
private String committedDate;
private String createdAt;
private String message;
private GitLabAuthor author;

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getAuthorName() {
	return authorName;
}

public void setAuthorName(String authorName) {
	this.authorName = authorName;
}

public String getAuthorEmail() {
	return authorEmail;
}

public void setAuthorEmail(String authorEmail) {
	this.authorEmail = authorEmail;
}

public String getAuthoredDate() {
	return authoredDate;
}

public void setAuthoredDate(String authoredDate) {
	this.authoredDate = authoredDate;
}

public String getCommitterName() {
	return committerName;
}

public void setCommitterName(String committerName) {
	this.committerName = committerName;
}

public String getCommitterEmail() {
	return committerEmail;
}

public void setCommitterEmail(String committerEmail) {
	this.committerEmail = committerEmail;
}

public String getCommittedDate() {
	return committedDate;
}

public void setCommittedDate(String committedDate) {
	this.committedDate = committedDate;
}

public String getCreatedAt() {
	return createdAt;
}

public void setCreatedAt(String createdAt) {
	this.createdAt = createdAt;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

public GitLabAuthor getAuthor() {
	return author;
}

public void setAuthor(GitLabAuthor author) {
	this.author = author;
}
}
