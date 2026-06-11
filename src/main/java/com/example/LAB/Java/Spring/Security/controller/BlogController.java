package com.example.LAB.Java.Spring.Security.controller;

import com.example.LAB.Java.Spring.Security.model.Author;
import com.example.LAB.Java.Spring.Security.model.BlogPost;
import com.example.LAB.Java.Spring.Security.repository.AuthorRepository;
import com.example.LAB.Java.Spring.Security.repository.BlogPostRepository;
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

    @GetMapping("/posts")
    public List<BlogPost> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @PostMapping("/posts")
    public BlogPost addPost(@RequestBody BlogPost post) {
        return postRepository.save(post);
    }

    @PostMapping("/authors")
    public Author addAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<BlogPost> updatePost(@PathVariable Long id, @RequestBody BlogPost postDetails) {
        return postRepository.findById(id).map(post -> {
            post.setTitle(postDetails.getTitle());
            post.setPost(postDetails.getPost());
            post.setAuthor(postDetails.getAuthor());
            return ResponseEntity.ok(postRepository.save(post));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {
        return authorRepository.findById(id).map(author -> {
            author.setName(authorDetails.getName());
            return ResponseEntity.ok(authorRepository.save(author));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
