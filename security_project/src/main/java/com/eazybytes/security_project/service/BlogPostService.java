package com.eazybytes.security_project.service;

import com.eazybytes.security_project.entity.Author;
import com.eazybytes.security_project.entity.BlogPost;
import com.eazybytes.security_project.repository.BlogPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;
    private final AuthorService authorService;

    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }

    public BlogPost getPostById(Long id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }

    @Transactional
    public BlogPost createPost(BlogPost post, Long authorId) {
        Author author = authorService.getAuthorById(authorId);
        post.setAuthor(author);
        return blogPostRepository.save(post);
    }

    @Transactional
    public BlogPost updatePost(Long id, BlogPost updatedPost) {
        BlogPost existing = getPostById(id);
        existing.setTitle(updatedPost.getTitle());
        existing.setPost(updatedPost.getPost());
        // author dəyişməyəcək (istəsəniz əlavə parametr göndərin)
        return blogPostRepository.save(existing);
    }

    @Transactional
    public void deletePost(Long id) {
        BlogPost post = getPostById(id);
        blogPostRepository.delete(post);
    }
}
