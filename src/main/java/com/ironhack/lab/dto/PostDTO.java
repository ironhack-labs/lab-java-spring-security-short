package com.ironhack.lab.dto;

public class PostDTO {
    private Long authorId;
    private String title;
    private String post;

    public PostDTO() {}
    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getPost() { return post; }
    public void setPost(String post) { this.post = post; }
}
