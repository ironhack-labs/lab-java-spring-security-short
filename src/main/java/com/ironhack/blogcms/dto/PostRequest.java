package com.ironhack.blogcms.dto;

import com.ironhack.blogcms.model.BlogPost;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/** Request body for creating or updating a blog post. */
public record PostRequest(
        @NotNull @Positive Long authorId,
        @NotBlank @Size(max = 255) String title,
        @NotBlank @Size(max = BlogPost.MAX_POST_LENGTH) String post) {
}
