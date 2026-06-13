package com.springsecurity.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springsecurity.demo.model.BlogPost;
import com.springsecurity.demo.service.BlogPostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    // PUBLIC - GET all posts with author
    @GetMapping
    public List<BlogPost> getAllPosts() {
        return blogPostService.getAllPosts();
    }

    // PUBLIC - GET single post by id
    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getPostById(@PathVariable Long id) {
        return blogPostService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ADMIN ONLY - POST new blog post
    @PostMapping
    public BlogPost createPost(@RequestBody BlogPost blogPost) {
        return blogPostService.createPost(blogPost);
    }

    // ADMIN ONLY - PUT update blog post
    @PutMapping("/{id}")
    public BlogPost updatePost(@PathVariable Long id, @RequestBody BlogPost blogPost) {
        return blogPostService.updatePost(id, blogPost);
    }

    // ADMIN ONLY - DELETE blog post
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        blogPostService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
