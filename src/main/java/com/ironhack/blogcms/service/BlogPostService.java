package com.ironhack.blogcms.service;

import com.ironhack.blogcms.dto.PostRequest;
import com.ironhack.blogcms.dto.PostResponse;
import com.ironhack.blogcms.exception.ResourceNotFoundException;
import com.ironhack.blogcms.model.Author;
import com.ironhack.blogcms.model.BlogPost;
import com.ironhack.blogcms.repository.BlogPostRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;
    private final AuthorService authorService;

    public BlogPostService(BlogPostRepository blogPostRepository, AuthorService authorService) {
        this.blogPostRepository = blogPostRepository;
        this.authorService = authorService;
    }

    @Transactional(readOnly = true)
    public List<PostResponse> findAll() {
        return blogPostRepository.findAll().stream().map(PostResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public PostResponse findById(Long id) {
        return PostResponse.from(getPost(id));
    }

    @Transactional
    public PostResponse create(PostRequest request) {
        Author author = authorService.getAuthor(request.authorId());
        BlogPost saved = blogPostRepository.save(new BlogPost(author, request.title(), request.post()));
        return PostResponse.from(saved);
    }

    @Transactional
    public PostResponse update(Long id, PostRequest request) {
        BlogPost blogPost = getPost(id);
        blogPost.setAuthor(authorService.getAuthor(request.authorId()));
        blogPost.setTitle(request.title());
        blogPost.setPost(request.post());
        return PostResponse.from(blogPost);
    }

    @Transactional
    public void delete(Long id) {
        blogPostRepository.delete(getPost(id));
    }

    private BlogPost getPost(Long id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", id));
    }
}
