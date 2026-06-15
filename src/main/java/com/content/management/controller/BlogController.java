package com.content.management.controller;

import com.content.management.model.BlogModel;
import com.content.management.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public List<BlogModel> getAllPosts() {
        return blogService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogModel> getPostById(@PathVariable Long id) {
        return blogService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public BlogModel createPost(@RequestBody BlogModel blogPost) {
        return blogService.createPost(blogPost);
    }

    @PutMapping("/{id}")
    public BlogModel updatePost(@PathVariable Long id, @RequestBody BlogModel blogPost) {
        return blogService.updatePost(id, blogPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        blogService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}