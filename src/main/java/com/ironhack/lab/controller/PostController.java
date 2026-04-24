package com.ironhack.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.ironhack.lab.model.Post;
import com.ironhack.lab.model.Author;
import com.ironhack.lab.dto.PostDTO;
import com.ironhack.lab.repository.PostRepository;
import com.ironhack.lab.repository.AuthorRepository;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public List<Post> list() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> get(@PathVariable Long id) {
        return postRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody PostDTO dto) {
        Author author = authorRepository.findById(dto.getAuthorId()).orElse(null);
        if (author == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("author not found");
        Post p = new Post(author, dto.getTitle(), dto.getPost());
        Post saved = postRepository.save(p);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody PostDTO dto) {
        return postRepository.findById(id).map(p -> {
            Author author = authorRepository.findById(dto.getAuthorId()).orElse(null);
            if (author == null) return ResponseEntity.notFound().build();
            p.setAuthor(author);
            p.setTitle(dto.getTitle());
            p.setPost(dto.getPost());
            postRepository.save(p);
            return ResponseEntity.ok(p);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return postRepository.findById(id).map(p -> {
            postRepository.delete(p);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
