package com.example.demo.dto;


public class BlogPostResponse {
    private Long id;
    private String title;
    private String post;
    private String authorName;

    public BlogPostResponse(){}
    public BlogPostResponse(Long id, String title, String post,String authorName) {
        this.id = id;
        this.post = post;
        this.authorName = authorName;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPost() {
        return post;
    }

    public String getAuthorName() {
        return authorName;
    }
}
