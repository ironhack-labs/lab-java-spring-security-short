package com.example.blogsecurity.service;

import com.example.blogsecurity.model.Author;
import com.example.blogsecurity.model.BlogPost;
import com.example.blogsecurity.repository.AuthorRepository;
import com.example.blogsecurity.repository.BlogPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;
    private final AuthorRepository authorRepository;

    public BlogPostService(BlogPostRepository blogPostRepository, AuthorRepository authorRepository) {
        this.blogPostRepository = blogPostRepository;
        this.authorRepository = authorRepository;
    }

    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }

    public BlogPost getPostById(Long id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public BlogPost addPost(BlogPost blogPost) {
        if (blogPost.getAuthor() == null || blogPost.getAuthor().getId() == null) {
            throw new RuntimeException("Author id is required");
        }

        Author author = authorRepository.findById(blogPost.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        blogPost.setAuthor(author);
        return blogPostRepository.save(blogPost);
    }

    public BlogPost updatePost(Long id, BlogPost postDetails) {
        BlogPost blogPost = getPostById(id);

        blogPost.setTitle(postDetails.getTitle());
        blogPost.setPost(postDetails.getPost());

        if (postDetails.getAuthor() != null && postDetails.getAuthor().getId() != null) {
            Author author = authorRepository.findById(postDetails.getAuthor().getId())
                    .orElseThrow(() -> new RuntimeException("Author not found"));

            blogPost.setAuthor(author);
        }

        return blogPostRepository.save(blogPost);
    }

    public void deletePost(Long id) {
        BlogPost blogPost = getPostById(id);
        blogPostRepository.delete(blogPost);
    }
}
