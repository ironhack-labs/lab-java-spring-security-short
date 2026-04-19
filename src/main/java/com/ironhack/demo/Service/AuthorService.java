package com.ironhack.demo.Service;

import com.ironhack.demo.Repository.AuthorRepository;
import com.ironhack.demo.entity.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    public List<Author>getAuthors(){
    return authorRepository.findAll();
}
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }
    public Author updateAuthor(Long id, Author author) {
        Author kohneAuthor = authorRepository.findById(id).orElseThrow();
        kohneAuthor.setName(author.getName());
        return authorRepository.save(kohneAuthor);
    }
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }














}
