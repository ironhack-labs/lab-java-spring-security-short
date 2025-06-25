package com.example.lab10.demo;

import com.example.lab10.model.Author;
import com.example.lab10.model.BlogPost;
import com.example.lab10.repository.AuthorRepository;
import com.example.lab10.repository.BlogPostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final AuthorRepository authorRepo;
    private final BlogPostRepository postRepo;

    public DataLoader(AuthorRepository authorRepo, BlogPostRepository postRepo) {
        this.authorRepo = authorRepo;
        this.postRepo = postRepo;
    }

    @Override
    public void run(String... args) {
        Author aiko = authorRepo.save(new Author(null, "Aiko Tanaka"));
        Author jonas = authorRepo.save(new Author(null, "Jonas Schmidt"));

        postRepo.save(new BlogPost(null, "Boost Your Productivity with 10 Easy Tips", "Productivity - we all want it...", aiko));
        postRepo.save(new BlogPost(null, "How to Focus", "Do you ever sit down to work...", aiko));
        postRepo.save(new BlogPost(null, "Learn to Speed Read in 30 Days", "Knowledge, not ability...", jonas));
    }
}
