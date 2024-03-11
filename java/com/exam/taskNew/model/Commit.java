package com.exam.taskNew.model;
import java.util.Objects;

import jakarta.persistence.*;



@Entity
@Table(name = "commits")
public class Commit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="hash")
    private String hash;
    @Column(name ="message" )
    private String message;
    @Column(name ="timestamp")
    private String timestamp;
    @Column(name ="author")
    private String author;
    @Column(name="patch")
    private String patch;
    @ManyToOne
    @JoinColumn(name = "developer_id")
    private Developer developer;

    // Getter ve Setter metotları
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    // Diğer getter ve setter metotları

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Commit commit = (Commit) obj;

        return Objects.equals(hash, commit.hash)
                && Objects.equals(author, commit.author)
                && Objects.equals(timestamp, commit.timestamp)
                && Objects.equals(message, commit.message)
                && Objects.equals(patch, commit.patch)
                && Objects.equals(developer, commit.developer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash, author, timestamp, message, patch, developer);
    }

}