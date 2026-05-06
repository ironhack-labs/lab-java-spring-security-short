package com.SecurityTask.service;

import com.SecurityTask.dto.request.PostRequest;
import com.SecurityTask.model.Author;
import com.SecurityTask.model.Post;
import com.SecurityTask.repository.AuthorRepository;
import com.SecurityTask.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepo;
    private final AuthorRepository authorRepo;

    public PostService(PostRepository postRepo, AuthorRepository authorRepo) {
        this.postRepo = postRepo; this.authorRepo = authorRepo;
    }

    public List<Post> list() { return postRepo.findAll(); }
    public Post get(Long id) { return postRepo.findById(id).orElse(null); }
    public Post create(PostRequest req) {
        Author a = authorRepo.findById(req.getAuthorId()).orElse(null);
        if (a == null) return null;
        Post p = new Post(); p.setAuthor(a); p.setTitle(req.getTitle()); p.setPost(req.getPost());
        return postRepo.save(p);
    }
    public Post update(Long id, PostRequest req) {
        return postRepo.findById(id).map(p -> {
            p.setTitle(req.getTitle()); p.setPost(req.getPost());
            if (req.getAuthorId() != null) {
                Author a = authorRepo.findById(req.getAuthorId()).orElse(null);
                if (a != null) p.setAuthor(a);
            }
            return postRepo.save(p);
        }).orElse(null);
    }
    public boolean delete(Long id) { if (!postRepo.existsById(id)) return false; postRepo.deleteById(id); return true; }
}
