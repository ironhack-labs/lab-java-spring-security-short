package com.eazybytes.security_project.service;


import com.eazybytes.security_project.entity.Author;
import com.eazybytes.security_project.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
    }

    @Transactional
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Transactional
    public Author updateAuthor(Long id, Author updatedAuthor) {
        Author existing = getAuthorById(id);
        existing.setName(updatedAuthor.getName());
        return authorRepository.save(existing);
    }

    @Transactional
    public void deleteAuthor(Long id) {
        Author author = getAuthorById(id);
        authorRepository.delete(author);
    }
}