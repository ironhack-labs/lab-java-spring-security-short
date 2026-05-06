package com.example.securityLab.controller;

import com.example.securityLab.dto.BlogPostRequest;
import com.example.securityLab.entity.BlogPost;
import com.example.securityLab.service.BlogPostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class BlogPostController {
    private final BlogPostService blogPostService;

    public  BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping
    public List<BlogPost> getBlogPosts() {
        return blogPostService.getAll();
    }

    @GetMapping("/{id}")
    public BlogPost getBlogPost(@PathVariable Long id) {
        return blogPostService.getById(id);
    }

    @PostMapping
    public BlogPost createBlogPost(@RequestBody BlogPostRequest blogPost) {
        return  blogPostService.create(blogPost);
    }

    @PutMapping("/{id}")
    public BlogPost updateBlogPost(@PathVariable Long id, @RequestBody BlogPostRequest blogPost) {
        return blogPostService.update(id, blogPost);
    }

    @DeleteMapping("/{id}")
    public void deleteBlogPost(@PathVariable Long id) {
        blogPostService.delete(id);
    }
}
