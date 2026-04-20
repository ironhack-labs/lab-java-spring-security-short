package com.ironhack.securitylab.controller;

import com.ironhack.securitylab.entity.Author;
import com.ironhack.securitylab.entity.Post;
import com.ironhack.securitylab.repository.AuthorRepository;
import com.ironhack.securitylab.repository.BlogRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class BlogController {
    private final BlogRepository blogRepository;

    public BlogController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return blogRepository.findAll();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post) {
        return blogRepository.save(post);
    }
    @PutMapping("/{id}")
        public Post updatePost(@PathVariable Long id, @RequestBody Post details) {
        Post post = blogRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        post.setTitle(details.getTitle());
        post.setPost(details.getPost());
        return blogRepository.save(post);
        }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long id) {
        blogRepository.deleteById(id);
    }
}
