package com.ironhack.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.ironhack.lab.model.Author;
import com.ironhack.lab.dto.AuthorDTO;
import com.ironhack.lab.repository.AuthorRepository;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public List<Author> list() {
        return authorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> get(@PathVariable Long id) {
        return authorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Author> create(@RequestBody AuthorDTO dto) {
        Author a = new Author(dto.getName());
        Author saved = authorRepository.save(a);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable Long id, @RequestBody AuthorDTO dto) {
        return authorRepository.findById(id).map(a -> {
            a.setName(dto.getName());
            authorRepository.save(a);
            return ResponseEntity.ok(a);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return authorRepository.findById(id).map(a -> {
            authorRepository.delete(a);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
