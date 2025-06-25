package com.example.lab10.controller;

import com.example.lab10.model.Author;
import com.example.lab10.model.BlogPost;
import com.example.lab10.repository.AuthorRepository;
import com.example.lab10.repository.BlogPostRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogController {
    private final AuthorRepository authorRepo;
    private final BlogPostRepository postRepo;

    public BlogController(AuthorRepository authorRepo, BlogPostRepository postRepo) {
        this.authorRepo = authorRepo;
        this.postRepo = postRepo;
    }

    // Public GET route
    @GetMapping("/posts")
    public List<BlogPost> getAllPosts() {
        return postRepo.findAll();
    }

    // Admin-only routes
    @PostMapping("/posts")
    public BlogPost addPost(@RequestBody BlogPost post) {
        return postRepo.save(post);
    }

    @PutMapping("/posts/{id}")
    public BlogPost updatePost(@PathVariable Long id, @RequestBody BlogPost updatedPost) {
        return postRepo.findById(id)
                .map(p -> {
                    p.setTitle(updatedPost.getTitle());
                    p.setPost(updatedPost.getPost());
                    p.setAuthor(updatedPost.getAuthor());
                    return postRepo.save(p);
                }).orElseThrow();
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable Long id) {
        postRepo.deleteById(id);
    }

    @PostMapping("/authors")
    public Author addAuthor(@RequestBody Author author) {
        return authorRepo.save(author);
    }

    @PutMapping("/authors/{id}")
    public Author updateAuthor(@PathVariable Long id, @RequestBody Author updatedAuthor) {
        return authorRepo.findById(id)
                .map(a -> {
                    a.setName(updatedAuthor.getName());
                    return authorRepo.save(a);
                }).orElseThrow();
    }

    @DeleteMapping("/authors/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorRepo.deleteById(id);
    }
}
