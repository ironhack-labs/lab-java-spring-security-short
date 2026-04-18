package com.example.demo.service;

import com.example.demo.dto.BlogPostRequest;
import com.example.demo.dto.BlogPostResponse;
import com.example.demo.dto.BlogPostUpdateRequest;
import com.example.demo.entity.Author;
import com.example.demo.entity.BlogPost;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BlogPostRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BlogPostService {
    private BlogPostRepository blogPostRepository;
    private AuthorRepository authorRepository;

    public BlogPostService(BlogPostRepository blogPostRepository,AuthorRepository authorRepository) {
        this.blogPostRepository = blogPostRepository;
        this.authorRepository = authorRepository;
    }

    public List<BlogPost> findAll() {
        return blogPostRepository.findAll();
    }
    public BlogPostResponse findById(long id){
        BlogPost blogPost = blogPostRepository.findById(id)
                .orElseThrow(()->new RuntimeException("BlogPost not found"));

        return new BlogPostResponse(blogPost.getId(),blogPost.getTitle(),blogPost.getPost(),blogPost.getAuthor().getName());
    }

    public BlogPostResponse create(BlogPostRequest request){
        Author existingAuthor = authorRepository.findById(request.getAuthorId())
                .orElseThrow(()->new RuntimeException("Auhtor not found"));

        BlogPost blogPost = new BlogPost();

        blogPost.setTitle(request.getTitle());
        blogPost.setPost(request.getPost());
        blogPost.setAuthor(existingAuthor);
        blogPostRepository.save(blogPost);

        return new BlogPostResponse(blogPost.getId(),blogPost.getTitle(),blogPost.getPost(),existingAuthor.getName());
    }

    public BlogPostResponse update(long id, BlogPostUpdateRequest request){

        BlogPost existingBlogPost = blogPostRepository.findById(id)
                .orElseThrow(()->new RuntimeException("BlogPost not found"));

        if(request.getPost()!=null){
            existingBlogPost.setPost(request.getPost());
        }
        if(request.getTitle()!=null){
            existingBlogPost.setTitle(request.getTitle());
        }
        if(request.getAuthorId()!=null){
            Author existingAuhtor=authorRepository.findById(request.getAuthorId()).orElseThrow(()->new RuntimeException("Author not found"));
            existingBlogPost.setAuthor(existingAuhtor);
        }
        blogPostRepository.save(existingBlogPost);

        return new  BlogPostResponse(existingBlogPost.getId(),existingBlogPost.getTitle(),existingBlogPost.getPost(),existingBlogPost.getAuthor().getName());

    }

    public boolean isOwner(Long id,String email){
        return blogPostRepository.findById(id)
                .map(post->post.getAuthor().getEmail().equals(email))
                .orElse(false);
    }

    public void delete(long id){
        if(!blogPostRepository.existsById(id)){
            throw new RuntimeException("BlogPost not found");
        }
        blogPostRepository.deleteById(id);
    }
}
