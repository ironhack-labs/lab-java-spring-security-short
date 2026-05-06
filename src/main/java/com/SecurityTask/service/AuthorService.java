package com.SecurityTask.service;

import com.SecurityTask.dto.request.AuthorRequest;
import com.SecurityTask.model.Author;
import com.SecurityTask.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository repo;

    public AuthorService(AuthorRepository repo) { this.repo = repo; }

    public List<Author> list() { return repo.findAll(); }
    public Author get(Long id) { return repo.findById(id).orElse(null); }
    public Author create(AuthorRequest req) {
        Author a = new Author();
        a.setName(req.getName());
        return repo.save(a);
    }
    public Author update(Long id, AuthorRequest req) {
        return repo.findById(id).map(a -> { a.setName(req.getName()); return repo.save(a); }).orElse(null);
    }
    public boolean delete(Long id) { if (!repo.existsById(id)) return false; repo.deleteById(id); return true; }
}
