package com.ironhack.securityjava.controller;

import com.ironhack.securityjava.entity.Author;
import com.ironhack.securityjava.entity.BlogPost;
import com.ironhack.securityjava.repository.AuthorRepository;
import com.ironhack.securityjava.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogController {

    @Autowired
    private BlogPostRepository postRepository;

    @Autowired
    private AuthorRepository authorRepository;

    // --- GET ROUTES (Public) ---

    @GetMapping("/posts")
    public List<BlogPost> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // --- POST ROUTES (Admin Only) ---

    @PostMapping("/authors")
    public Author createAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    @PostMapping("/posts")
    public ResponseEntity<BlogPost> createPost(@RequestBody BlogPost post) {
        // Gönderilen post nesnesinde geçerli bir author_id olmalı
        if (post.getAuthor() == null || post.getAuthor().getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(postRepository.save(post));
    }

    // --- PUT ROUTES (Admin Only) ---

    @PutMapping("/authors/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {
        return authorRepository.findById(id).map(author -> {
            author.setName(authorDetails.getName());
            return ResponseEntity.ok(authorRepository.save(author));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<BlogPost> updatePost(@PathVariable Long id, @RequestBody BlogPost postDetails) {
        return postRepository.findById(id).map(post -> {
            post.setTitle(postDetails.getTitle());
            post.setPost(postDetails.getPost());
            if (postDetails.getAuthor() != null) {
                post.setAuthor(postDetails.getAuthor());
            }
            return ResponseEntity.ok(postRepository.save(post));
        }).orElse(ResponseEntity.notFound().build());
    }

    // --- DELETE ROUTES (Admin Only) ---

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        return authorRepository.findById(id).map(author -> {
            authorRepository.delete(author);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        return postRepository.findById(id).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}