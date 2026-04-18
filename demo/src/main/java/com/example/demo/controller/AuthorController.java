package com.example.demo.controller;

import com.example.demo.dto.AuthorRequest;
import com.example.demo.dto.AuthorResponse;
import com.example.demo.dto.AuthorUpdateRequest;
import com.example.demo.entity.Author;
import com.example.demo.entity.BlogPost;
import com.example.demo.repository.BlogPostRepository;
import com.example.demo.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/authors")
public class AuthorController {
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AuthorResponse> createAuthor(@Valid  @RequestBody AuthorRequest author) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.create(author));
    }

    @GetMapping("/{id}")
    public AuthorResponse getAuthor(@PathVariable Long id) {
        return authorService.findById(id);
    }

    @PutMapping("/{id}")
    public AuthorResponse  updateAuthor(@PathVariable Long id, @RequestBody AuthorUpdateRequest author) {
        return  authorService.update(id, author);
    }

    @PreAuthorize("@authorService.isOwner(#id,authentication.name) or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
