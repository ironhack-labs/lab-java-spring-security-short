package com.example.blogsecurity.config;

import com.example.blogsecurity.model.Author;
import com.example.blogsecurity.model.BlogPost;
import com.example.blogsecurity.repository.AuthorRepository;
import com.example.blogsecurity.repository.BlogPostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BlogPostRepository blogPostRepository;

    public DataLoader(AuthorRepository authorRepository, BlogPostRepository blogPostRepository) {
        this.authorRepository = authorRepository;
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public void run(String... args) {
        Author author1 = authorRepository.save(new Author("Aiko Tanaka"));
        Author author2 = authorRepository.save(new Author("Jonas Schmidt"));
        authorRepository.save(new Author("Cas Van Dijk"));

        blogPostRepository.save(new BlogPost(
                "Boost Your Productivity with 10 Easy Tips",
                "Productivity - we all want it but it seems ...",
                author1
        ));

        blogPostRepository.save(new BlogPost(
                "How to Focus",
                "Do you ever sit down to work and find yourself ...",
                author1
        ));

        blogPostRepository.save(new BlogPost(
                "Learn to Speed Read in 30 Days",
                "Knowledge, not ability, is the great determiner of ...",
                author2
        ));
    }
}
