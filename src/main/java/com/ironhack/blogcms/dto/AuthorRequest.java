package com.ironhack.blogcms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/** Request body for creating or updating an author. */
public record AuthorRequest(
        @NotBlank @Size(max = 255) String name) {
}
