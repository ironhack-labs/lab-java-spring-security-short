package com.SecurityTask.controller;

import com.SecurityTask.dto.request.PostRequest;
import com.SecurityTask.dto.response.PostResponse;
import com.SecurityTask.mapper.PostMapper;
import com.SecurityTask.model.Post;
import com.SecurityTask.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service) { this.service = service; }

    @GetMapping
    public List<PostResponse> list() { return service.list().stream().map(PostMapper::toResponse).collect(Collectors.toList()); }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> get(@PathVariable Long id) {
        Post p = service.get(id); if (p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(PostMapper.toResponse(p));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PostRequest req) {
        Post saved = service.create(req);
        if (saved == null) return ResponseEntity.badRequest().body("author not found");
        return ResponseEntity.created(URI.create("/api/posts/" + saved.getId())).body(PostMapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PostRequest req) {
        Post updated = service.update(id, req);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(PostMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!service.delete(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
