package com.example.demo.dto;


import com.example.demo.entity.AuthorRole;
import com.example.demo.entity.BlogPost;

import java.util.List;

public class AuthorResponse {

    private final Long id;
    private final String name;
    private final String email;
    private final List<BlogPost> blogPosts;
    private final AuthorRole role;

    public AuthorResponse(Long id, String name, String email,AuthorRole role, List<BlogPost> blogPosts) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.blogPosts = blogPosts;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<BlogPost> getBlogPosts() {
        return blogPosts;
    }

    public AuthorRole getRole() {
        return role;
    }
}
