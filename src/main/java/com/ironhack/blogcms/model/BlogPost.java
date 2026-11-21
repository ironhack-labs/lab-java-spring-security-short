package com.ironhack.blogcms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * A blog post written by one author. Maps to the {@code blog_post} table from the lab brief,
 * with {@code author_id} as the foreign key to {@code author}.
 */
@Entity
@Table(name = "blog_post")
public class BlogPost {

    public static final int MAX_POST_LENGTH = 10_000;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = MAX_POST_LENGTH)
    private String post;

    protected BlogPost() {
        // required by JPA
    }

    public BlogPost(Author author, String title, String post) {
        this.author = author;
        this.title = title;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
