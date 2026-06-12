package com.ironhack.labSpringSecurity.controller;

import com.ironhack.labSpringSecurity.model.Author;
import com.ironhack.labSpringSecurity.repository.AuthorRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;

    @GetMapping
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author create(@RequestBody @Valid Author author) {
        return authorRepository.save(author);
    }

    @PutMapping("/{id}")
    public Author update(@PathVariable Integer id, @RequestBody @Valid Author author) {
        author.setId(id);
        return authorRepository.save(author);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        authorRepository.deleteById(id);
    }
}
