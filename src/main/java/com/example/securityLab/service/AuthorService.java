package com.example.securityLab.service;

import com.example.securityLab.dto.AuthorRequest;
import com.example.securityLab.entity.Author;
import com.example.securityLab.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public Author getById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

    public Author create(AuthorRequest request) {
        Author author = new Author();
        author.setName(request.getName());
        return authorRepository.save(author);
    }

    public Author update(Long id, AuthorRequest request) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        author.setName(request.getName());

        return authorRepository.save(author);
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
