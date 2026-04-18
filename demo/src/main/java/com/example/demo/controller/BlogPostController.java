package com.example.demo.controller;

import com.example.demo.dto.BlogPostRequest;
import com.example.demo.dto.BlogPostResponse;
import com.example.demo.dto.BlogPostUpdateRequest;
import com.example.demo.entity.BlogPost;
import com.example.demo.service.BlogPostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {

    private BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping
    public List<BlogPost> getBlogPosts() {
        return blogPostService.findAll();
    }


    @GetMapping("/{id}")
    public BlogPostResponse getBlogPost(@PathVariable Long id) {
        return blogPostService.findById(id);
    }


    //@PreAuthorize("@blogPostService.isOwner(#id,authentication.name) or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<BlogPostResponse> createBlogPost(@Valid @RequestBody BlogPostRequest blogPost) {
        return ResponseEntity.status(HttpStatus.CREATED).body(blogPostService.create(blogPost));
    }

    @PreAuthorize("@blogPostService.isOwner(#id,authentication.name) or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public BlogPostResponse updateBlogPost(@PathVariable Long id, @RequestBody BlogPostUpdateRequest blogPost) {
        return blogPostService.update(id,blogPost);
    }

    @PreAuthorize("@blogPostService.isOwner(#id,authentication.name) or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlogPost(@PathVariable Long id) {
        blogPostService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
