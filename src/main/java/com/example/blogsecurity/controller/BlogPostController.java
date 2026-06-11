package com.example.blogsecurity.controller;

import com.example.blogsecurity.model.BlogPost;
import com.example.blogsecurity.service.BlogPostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {

    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping
    public List<BlogPost> getAllPosts() {
        return blogPostService.getAllPosts();
    }

    @GetMapping("/{id}")
    public BlogPost getPostById(@PathVariable Long id) {
        return blogPostService.getPostById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost addPost(@RequestBody BlogPost blogPost) {
        return blogPostService.addPost(blogPost);
    }

    @PutMapping("/{id}")
    public BlogPost updatePost(@PathVariable Long id, @RequestBody BlogPost blogPost) {
        return blogPostService.updatePost(id, blogPost);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long id) {
        blogPostService.deletePost(id);
    }
}
