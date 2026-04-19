package com.ironhack.demo.controller;

import com.ironhack.demo.Service.PostService;
import com.ironhack.demo.entity.Post;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping
    public List<Post>getPost(){
        return postService.getAllPosts();
    }
    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Post addPost(@RequestBody Post post){
       return postService.addPost(post);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Post updatePost(@PathVariable Long id,@RequestBody Post post){
        return postService.updatePost(id,post);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deletePost(@PathVariable Long id) {
     postService.deletePost(id);
    }





}
