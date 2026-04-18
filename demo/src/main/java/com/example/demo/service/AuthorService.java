package com.example.demo.service;

import com.example.demo.dto.AuthorRequest;
import com.example.demo.dto.AuthorResponse;
import com.example.demo.dto.AuthorUpdateRequest;
import com.example.demo.entity.Author;

import com.example.demo.entity.AuthorRole;
import com.example.demo.entity.BlogPost;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BlogPostRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;
    private PasswordEncoder  passwordEncoder;
    private BlogPostRepository blogPostRepository;

    public AuthorService(AuthorRepository authorRepository, PasswordEncoder passwordEncoder, BlogPostRepository blogPostRepository) {
        this.authorRepository = authorRepository;
        this.passwordEncoder = passwordEncoder;
        this.blogPostRepository = blogPostRepository;
    }

    public boolean existsByUsername(String email) {
        return authorRepository.existsByEmail(email);
    }


    public AuthorResponse update(Long id, AuthorUpdateRequest request) {

        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("author not found"));

        if (request.getName() != null) {
            existingAuthor.setName(request.getName());
        }
        if(request.getEmail() != null) {
            existingAuthor.setEmail(request.getEmail());
        }
        if(request.getPassword() != null) {
            existingAuthor.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        if(request.getRole() != null) {
            existingAuthor.setRole(request.getRole());
        }

        authorRepository.save(existingAuthor);
        return new AuthorResponse(existingAuthor.getId(), existingAuthor.getName(), existingAuthor.getEmail(),existingAuthor.getRole(),existingAuthor.getBlogPosts());
    }

    public AuthorResponse create(AuthorRequest request) {

        if(authorRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Author author = new Author();
        author.setName(request.getName());
        author.setEmail(request.getEmail());
        author.setPassword(passwordEncoder.encode(request.getPassword()));

        if(request.getBlogPosts() != null) {
            List<BlogPost> posts = request.getBlogPosts().stream()
                    .map(dto -> {
                        BlogPost post = new BlogPost();
                        post.setTitle(dto.getTitle());
                        post.setPost(dto.getPost());
                        post.setAuthor(author);
                        return post;
                    })
                    .toList();
            author.setBlogPosts(posts);
        }

        authorRepository.save(author);

        return new AuthorResponse(author.getId(), author.getName(), author.getEmail(),author.getRole(),author.getBlogPosts());
    }

    public AuthorResponse findById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("author not found"));

        return new AuthorResponse(author.getId(), author.getName(), author.getEmail(),author.getRole(),author.getBlogPosts());
    }

    public void delete(Long id) {
        if(!authorRepository.existsById(id)){
            throw new RuntimeException("author not found");
        }
        authorRepository.deleteById(id);
    }

    public boolean isOwner(Long id, String email) {
        return authorRepository.findById(id)
                .map(author -> author.getEmail().equals(email))
                .orElse(false);
    }

}
