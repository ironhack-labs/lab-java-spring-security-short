package com.ironhack.blogcms.dto;

import com.ironhack.blogcms.model.Author;

/** Author as returned by the API. */
public record AuthorResponse(Long id, String name) {

    public static AuthorResponse from(Author author) {
        return new AuthorResponse(author.getId(), author.getName());
    }
}
