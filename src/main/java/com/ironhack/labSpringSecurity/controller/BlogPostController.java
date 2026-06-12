package com.ironhack.labSpringSecurity.controller;

import com.ironhack.labSpringSecurity.model.BlogPost;
import com.ironhack.labSpringSecurity.repository.BlogPostRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class BlogPostController {

    private final BlogPostRepository blogPostRepository;

    @GetMapping
    public List<BlogPost> getAll() {
        return blogPostRepository.findAll();
    }

    @GetMapping("/{id}")
    public BlogPost getById(@PathVariable Integer id) {
        return blogPostRepository.findById(id).orElseThrow();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost create(@RequestBody @Valid BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }
    @PutMapping("/{id}")
    public BlogPost update(@PathVariable Integer id, @RequestBody @Valid BlogPost blogPost) {
        blogPost.setId(id);
        return blogPostRepository.save(blogPost);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        blogPostRepository.deleteById(id);
    }
}
