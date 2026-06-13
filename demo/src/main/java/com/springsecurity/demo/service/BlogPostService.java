package com.springsecurity.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springsecurity.demo.model.BlogPost;
import com.springsecurity.demo.repository.BlogPostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }

    public Optional<BlogPost> getPostById(Long id) {
        return blogPostRepository.findById(id);
    }

    public BlogPost createPost(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    public BlogPost updatePost(Long id, BlogPost updatedPost) {
        return blogPostRepository.findById(id).map(post -> {
            post.setTitle(updatedPost.getTitle());
            post.setPost(updatedPost.getPost());
            post.setAuthor(updatedPost.getAuthor());
            return blogPostRepository.save(post);
        }).orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }

    public void deletePost(Long id) {
        blogPostRepository.deleteById(id);
    }
}