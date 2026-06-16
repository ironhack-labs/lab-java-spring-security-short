package com.example.demo.controller;

import com.example.demo.entity.BlogPost;
import com.example.demo.repository.BlogPostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {

    private final BlogPostRepository blogPostRepository;

    public BlogPostController(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @GetMapping
    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost createPost(@RequestBody BlogPost post) {
        return blogPostRepository.save(post);
    }

    @PutMapping("/{id}")
    public BlogPost updatePost(@PathVariable Long id, @RequestBody BlogPost postDetails) {
        BlogPost post = blogPostRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post tapılmadı"));

        post.setTitle(postDetails.getTitle());
        post.setPost(postDetails.getPost());
        post.setAuthor(postDetails.getAuthor());
        return blogPostRepository.save(post);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long id) {
        if (!blogPostRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post tapılmadı");
        }
        blogPostRepository.deleteById(id);
    }
}
