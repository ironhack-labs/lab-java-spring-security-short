package com.eazybytes.security_project.controller;



import com.eazybytes.security_project.entity.BlogPost;
import com.eazybytes.security_project.service.BlogPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class BlogPostController {

    private final BlogPostService blogPostService;

    @GetMapping
    public List<BlogPost> getAllPosts() {
        return blogPostService.getAllPosts();
    }

    @GetMapping("/{id}")
    public BlogPost getPost(@PathVariable Long id) {
        return blogPostService.getPostById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public BlogPost createPost(@RequestBody BlogPost post, @RequestParam Long authorId) {
        return blogPostService.createPost(post, authorId);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public BlogPost updatePost(@PathVariable Long id, @RequestBody BlogPost post) {
        return blogPostService.updatePost(id, post);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deletePost(@PathVariable Long id) {
        blogPostService.deletePost(id);
    }
}