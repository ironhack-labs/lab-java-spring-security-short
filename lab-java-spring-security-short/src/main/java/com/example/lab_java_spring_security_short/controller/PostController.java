package com.example.lab_java_spring_security_short.controller;

import com.example.lab_java_spring_security_short.model.Post;
import com.example.lab_java_spring_security_short.repository.PostRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository postRepo;

    public PostController(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    @PostMapping("/admin")
    public Post addPost(@RequestBody Post post) {
        return postRepo.save(post);
    }

    @PutMapping("/admin/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post post) {
        post.setId(id);
        return postRepo.save(post);
    }

    @DeleteMapping("/admin/{id}")
    public void deletePost(@PathVariable Long id) {
        postRepo.deleteById(id);
    }
}
