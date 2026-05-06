package com.SecurityTask.mapper;

import com.SecurityTask.dto.request.AuthorRequest;
import com.SecurityTask.dto.response.AuthorResponse;
import com.SecurityTask.model.Author;

public class AuthorMapper {
    public static Author toEntity(AuthorRequest req) {
        Author a = new Author();
        a.setName(req.getName());
        return a;
    }

    public static AuthorResponse toResponse(Author a) {
        return new AuthorResponse(a.getId(), a.getName());
    }
}
