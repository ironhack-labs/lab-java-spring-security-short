package com.ironhack.lab.model;

import jakarta.persistence.*;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String post;

    public Post() {}

    public Post(Author author, String title, String post) {
        this.author = author;
        this.title = title;
        this.post = post;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getPost() { return post; }
    public void setPost(String post) { this.post = post; }
}
