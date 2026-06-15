package com.content.management.service;

import com.content.management.model.BlogModel;
import com.content.management.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public List<BlogModel> getAllPosts() {
        return blogRepository.findAll();
    }

    public Optional<BlogModel> getPostById(Long id) {
        return blogRepository.findById(id);
    }

    public BlogModel createPost(BlogModel blogPost) {
        return blogRepository.save(blogPost);
    }

    public BlogModel updatePost(Long id, BlogModel updatedPost) {
        return blogRepository.findById(id).map(post -> {
            post.setTitle(updatedPost.getTitle());
            post.setPost(updatedPost.getPost());
            post.setAuthor(updatedPost.getAuthor());
            return blogRepository.save(post);
        }).orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }

    public void deletePost(Long id) {
        blogRepository.deleteById(id);
    }

}
