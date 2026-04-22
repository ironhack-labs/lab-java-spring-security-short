package com.ironhack.securityLab.service;

import com.ironhack.securityLab.entity.Author;
import com.ironhack.securityLab.entity.BlogPost;
import com.ironhack.securityLab.repository.AuthorRepository;
import com.ironhack.securityLab.repository.BlogPostRepository;
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

    public BlogPost addPost(Long authorId, String title, String postText) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        BlogPost blogPost = new BlogPost();
        blogPost.setAuthor(author);
        blogPost.setTitle(title);
        blogPost.setPost(postText);

        return blogPostRepository.save(blogPost);
    }

    public BlogPost updatePost(Long id, Long authorId, String title, String postText) {
        BlogPost blogPost = getPostById(id);
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        blogPost.setAuthor(author);
        blogPost.setTitle(title);
        blogPost.setPost(postText);

        return blogPostRepository.save(blogPost);
    }

    public void deletePost(Long id) {
        blogPostRepository.deleteById(id);
    }
}
