package com.ironhack.blogcms.dto;

import com.ironhack.blogcms.model.BlogPost;

/** Blog post as returned by the API, with its author embedded. */
public record PostResponse(Long id, String title, String post, AuthorResponse author) {

    public static PostResponse from(BlogPost blogPost) {
        return new PostResponse(
                blogPost.getId(),
                blogPost.getTitle(),
                blogPost.getPost(),
                AuthorResponse.from(blogPost.getAuthor()));
    }
}
