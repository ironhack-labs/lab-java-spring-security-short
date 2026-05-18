package com.ironhack.securityjava.config;

import com.ironhack.securityjava.entity.Author;
import com.ironhack.securityjava.entity.BlogPost;
import com.ironhack.securityjava.repository.AuthorRepository;
import com.ironhack.securityjava.repository.BlogPostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(AuthorRepository authorRepository, BlogPostRepository postRepository) {
        return args -> {
            Author aiko = new Author(); aiko.setName("Aiko Tanaka");
            Author jonas = new Author(); jonas.setName("Jonas Schmidt");
            Author cas = new Author(); cas.setName("Cas Van Dijk");

            authorRepository.save(aiko);
            authorRepository.save(jonas);
            authorRepository.save(cas);

            BlogPost post1 = new BlogPost();
            post1.setTitle("Boost Your Productivity with 10 Easy Tips");
            post1.setPost("Productivity - we all want it but it seems ...");
            post1.setAuthor(aiko);

            BlogPost post2 = new BlogPost();
            post2.setTitle("How to Focus");
            post2.setPost("Do you ever sit down to work and find yourself ...");
            post2.setAuthor(aiko);

            BlogPost post3 = new BlogPost();
            post3.setTitle("Learn to Speed Read in 30 Days");
            post3.setPost("Knowledge, not ability, is the great determiner of ...");
            post3.setAuthor(jonas);

            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(post3);
        };
    }
}