package com.example.blog.controller;

import com.example.blog.entity.Author;
import com.example.blog.entity.BlogPost;
import com.example.blog.repository.AuthorRepository;
import com.example.blog.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BlogController {

    private final AuthorRepository authorRepository;
    private final BlogPostRepository blogPostRepository;

    @Autowired
    public BlogController(AuthorRepository authorRepository, BlogPostRepository blogPostRepository) {
        this.authorRepository = authorRepository;
        this.blogPostRepository = blogPostRepository;
    }

    // --- AUTHORS ---

    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @PostMapping("/authors")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        Author savedAuthor = authorRepository.save(author);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            Author authorToUpdate = authorOptional.get();
            authorToUpdate.setName(authorDetails.getName());
            return new ResponseEntity<>(authorRepository.save(authorToUpdate), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // --- POSTS ---

    @GetMapping("/posts")
    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }

    @PostMapping("/posts")
    public ResponseEntity<BlogPost> addPost(@RequestBody BlogPost post) {
        // Assume author_id is provided in the post object
        BlogPost savedPost = blogPostRepository.save(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<BlogPost> updatePost(@PathVariable Long id, @RequestBody BlogPost postDetails) {
        Optional<BlogPost> postOptional = blogPostRepository.findById(id);
        if (postOptional.isPresent()) {
            BlogPost postToUpdate = postOptional.get();
            postToUpdate.setTitle(postDetails.getTitle());
            postToUpdate.setPost(postDetails.getPost());
            if (postDetails.getAuthor() != null) {
                postToUpdate.setAuthor(postDetails.getAuthor());
            }
            return new ResponseEntity<>(blogPostRepository.save(postToUpdate), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        if (blogPostRepository.existsById(id)) {
            blogPostRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
