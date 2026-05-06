package com.example.securityLab.controller;

import com.example.securityLab.dto.AuthorRequest;
import com.example.securityLab.entity.Author;
import com.example.securityLab.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAll() {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    public Author getById(@PathVariable Long id) {
        return authorService.getById(id);
    }

    @PostMapping
    public Author create(@RequestBody AuthorRequest author) {
        return authorService.create(author);
    }

    @PutMapping("/{id}")
    public Author update(@PathVariable Long id, @RequestBody AuthorRequest author) {
        return authorService.update(id, author);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        authorService.delete(id);
    }
}
