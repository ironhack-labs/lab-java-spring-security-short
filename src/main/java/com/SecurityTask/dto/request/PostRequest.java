package com.SecurityTask.dto.request;

public class PostRequest {
    private Long authorId;
    private String title;
    private String post;

    public PostRequest() {}
    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getPost() { return post; }
    public void setPost(String post) { this.post = post; }
}
