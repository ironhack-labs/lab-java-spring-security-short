package com.ironhack.securityLab.controller;

import com.ironhack.securityLab.dto.request.BlogPostRequest;
import com.ironhack.securityLab.entity.BlogPost;
import com.ironhack.securityLab.service.BlogPostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class BlogPostController {

    private  final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping
    public List<BlogPost> getAllPosts(){
        return blogPostService.getAllPosts();
    }

    @GetMapping("/{id}")
    public BlogPost getPostById(@PathVariable Long id){
        return blogPostService.getPostById(id);
    }

    @PostMapping
    public BlogPost addPost(@RequestBody BlogPostRequest request) {
        return blogPostService.addPost(
                request.getAuthorId(),
                request.getTitle(),
                request.getPost()
        );
    }

    @PutMapping("/{id}")
    public BlogPost updatePost(@PathVariable Long id, @RequestBody BlogPostRequest request) {
        return blogPostService.updatePost(
                id,
                request.getAuthorId(),
                request.getTitle(),
                request.getPost()
        );
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        blogPostService.deletePost(id);
    }
}
