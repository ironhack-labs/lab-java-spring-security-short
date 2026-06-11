package com.example.LAB.Java.Spring.Security.config;

import com.example.LAB.Java.Spring.Security.model.Author;
import com.example.LAB.Java.Spring.Security.model.BlogPost;
import com.example.LAB.Java.Spring.Security.repository.AuthorRepository;
import com.example.LAB.Java.Spring.Security.repository.BlogPostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(AuthorRepository authorRepository, BlogPostRepository postRepository) {
        return args -> {
            Author aiko = authorRepository.save(new Author("Aiko Tanaka"));
            Author jonas = authorRepository.save(new Author("Jonas Schmidt"));
            Author cas = authorRepository.save(new Author("Cas Van Dijk"));

            postRepository.save(new BlogPost(
                    "Boost Your Productivity with 10 Easy Tips",
                    "Productivity - we all want it but it seems hard to achieve. Start by blocking time...",
                    aiko
            ));

            postRepository.save(new BlogPost(
                    "How to Focus",
                    "Do you ever sit down to work and find yourself endlessly scrolling? Here is a concrete guide...",
                    aiko
            ));

            postRepository.save(new BlogPost(
                    "Learn to Speed Read in 30 Days",
                    "Knowledge, not ability, is the great determiner of success. Speed reading unlocks information access...",
                    jonas
            ));

            postRepository.save(new BlogPost(
                    "Mastering Spring Boot 3 Security",
                    "Securing REST APIs requires robust architecture. Spring Security components simplify role configurations...",
                    cas
            ));
        };
    }
}

