package com.example.LAB.Java.Spring.Security.model;

import jakarta.persistence.*;

@Entity
@Table(name = "blog_posts")
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public BlogPost() {}

    public BlogPost(String title, String post, Author author) {
        this.title = title;
        this.post = post;
        this.author = author;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getPost() { return post; }
    public void setPost(String post) { this.post = post; }
    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }
}
