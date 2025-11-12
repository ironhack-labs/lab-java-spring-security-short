package com.example.lab_java_spring_security_short.controller;

import com.example.lab_java_spring_security_short.model.Author;
import com.example.lab_java_spring_security_short.repository.AuthorRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorRepository authorRepo;

    public AuthorController(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    @PostMapping("/admin")
    public Author addAuthor(@RequestBody Author author) {
        return authorRepo.save(author);
    }

    @PutMapping("/admin/{id}")
    public Author updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        author.setId(id);
        return authorRepo.save(author);
    }

    @DeleteMapping("/admin/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorRepo.deleteById(id);
    }
}
