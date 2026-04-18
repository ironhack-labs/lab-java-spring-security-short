package com.example.demo.dto;


import com.example.demo.entity.AuthorRole;
import com.example.demo.entity.BlogPost;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;

import java.util.List;

public class AuthorRequest {
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 3, max = 50)
    private String password;


    private List<BlogPost> blogPosts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<BlogPost> getBlogPosts() {
        return blogPosts;
    }

    public void setBlogPosts(List<BlogPost> blogPosts) {
        this.blogPosts = blogPosts;
    }


}
