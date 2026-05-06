package com.example.securityLab.service;

import com.example.securityLab.dto.BlogPostRequest;
import com.example.securityLab.entity.Author;
import com.example.securityLab.entity.BlogPost;
import com.example.securityLab.repository.AuthorRepository;
import com.example.securityLab.repository.BlogPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostService {
    private BlogPostRepository blogPostRepository;
    private AuthorRepository authorRepository;

    public BlogPostService(BlogPostRepository blogPostRepository, AuthorRepository authorRepository) {
        this.blogPostRepository = blogPostRepository;
        this.authorRepository = authorRepository;
    }

    public List<BlogPost> getAll() {
        return blogPostRepository.findAll();
    }

    public BlogPost getById(Long id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public BlogPost create(BlogPostRequest request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        BlogPost blogPost = new BlogPost();
        blogPost.setAuthor(author);
        blogPost.setTitle(request.getTitle());
        blogPost.setPost(request.getPost());
        return blogPostRepository.save(blogPost);
    }

    public BlogPost update(Long id, BlogPostRequest request) {
        BlogPost blogPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        blogPost.setAuthor(author);
        blogPost.setTitle(request.getTitle());
        blogPost.setPost(request.getPost());
        return blogPostRepository.save(blogPost);
    }

    public void delete(Long id) {
        blogPostRepository.deleteById(id);
    }
}
