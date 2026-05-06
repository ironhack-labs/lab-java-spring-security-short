package com.SecurityTask.mapper;

import com.SecurityTask.dto.request.PostRequest;
import com.SecurityTask.dto.response.PostResponse;
import com.SecurityTask.model.Post;

public class PostMapper {
    public static Post toEntity(PostRequest req) {
        Post p = new Post();
        p.setTitle(req.getTitle());
        p.setPost(req.getPost());
        return p;
    }

    public static PostResponse toResponse(Post p) {
        Long authorId = p.getAuthor() != null ? p.getAuthor().getId() : null;
        return new PostResponse(p.getId(), authorId, p.getTitle(), p.getPost());
    }
}
