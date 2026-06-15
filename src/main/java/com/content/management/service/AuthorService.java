package com.content.management.service;

import com.content.management.model.AuthorModel;
import com.content.management.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<AuthorModel> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<AuthorModel> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public AuthorModel createAuthor(AuthorModel author) {
        return authorRepository.save(author);
    }

    public AuthorModel updateAuthor(Long id, AuthorModel updatedAuthor) {
        return authorRepository.findById(id).map(author -> {
            author.setName(updatedAuthor.getName());
            return authorRepository.save(author);
        }).orElseThrow(() -> new RuntimeException("We couldn't find any Authors with id: " + id));
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}