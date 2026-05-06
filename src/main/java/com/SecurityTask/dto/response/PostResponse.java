package com.SecurityTask.dto.response;

public class PostResponse {
    private Long id;
    private Long authorId;
    private String title;
    private String post;

    public PostResponse() {}
    public PostResponse(Long id, Long authorId, String title, String post) {
        this.id = id; this.authorId = authorId; this.title = title; this.post = post;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getPost() { return post; }
    public void setPost(String post) { this.post = post; }
}
