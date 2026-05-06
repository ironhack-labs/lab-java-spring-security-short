package com.SecurityTask.controller;

import com.SecurityTask.dto.request.AuthorRequest;
import com.SecurityTask.dto.response.AuthorResponse;
import com.SecurityTask.mapper.AuthorMapper;
import com.SecurityTask.model.Author;
import com.SecurityTask.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService service;

    public AuthorController(AuthorService service) { this.service = service; }

    @GetMapping
    public List<AuthorResponse> list() { return service.list().stream().map(AuthorMapper::toResponse).collect(Collectors.toList()); }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> get(@PathVariable Long id) {
        Author a = service.get(id); if (a == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(AuthorMapper.toResponse(a));
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> create(@RequestBody AuthorRequest req) {
        Author saved = service.create(req);
        return ResponseEntity.created(URI.create("/api/authors/" + saved.getId())).body(AuthorMapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> update(@PathVariable Long id, @RequestBody AuthorRequest req) {
        Author updated = service.update(id, req); if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(AuthorMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!service.delete(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
