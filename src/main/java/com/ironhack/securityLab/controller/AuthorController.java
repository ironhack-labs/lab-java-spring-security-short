package com.ironhack.securityLab.controller;

import com.ironhack.securityLab.dto.request.AuthorRequest;
import com.ironhack.securityLab.entity.Author;
import com.ironhack.securityLab.service.AuthorService;
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
    public List<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id){
        return authorService.getAuthorById(id);
    }

    @PostMapping
    public Author addAuthor(@RequestBody AuthorRequest request){
        Author author = new Author();
        author.setName(request.getName());
        return authorService.addAuthor(author);
    }

    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable Long id, @RequestBody AuthorRequest request){
        Author author = new Author();
        author.setName(request.getName());
        return  authorService.updateAuthor(id, author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id){
        authorService.deleteAuthor(id);
    }
}
