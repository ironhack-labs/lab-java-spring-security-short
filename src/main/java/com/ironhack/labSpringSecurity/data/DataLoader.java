package com.ironhack.labSpringSecurity.data;

import com.ironhack.labSpringSecurity.model.Author;
import com.ironhack.labSpringSecurity.model.BlogPost;
import com.ironhack.labSpringSecurity.repository.AuthorRepository;
import com.ironhack.labSpringSecurity.repository.BlogPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BlogPostRepository blogPostRepository;

    @Override
    public void run(String... args) {
        if (authorRepository.count() == 0) {
            Author author1 = new Author(null, "Aiko Tanaka");
            Author author2 = new Author(null, "Jonas Schmidt");
            Author author3 = new Author(null, "Cas Van Dijk");
            authorRepository.save(author1);
            authorRepository.save(author2);
            authorRepository.save(author3);
        }

        if (blogPostRepository.count() == 0) {
            BlogPost post1 = new BlogPost(null, 1, "Boost Your Productivity with 10 Easy Tips", "Productivity - we all want it but it seems ...");
            BlogPost post2 = new BlogPost(null, 1, "How to Focus", "Do you ever sit down to work and find yourself ...");
            BlogPost post3 = new BlogPost(null, 2, "Learn to Speed Read in 30 Days", "Knowledge, not ability, is the great determiner of ...");
            blogPostRepository.save(post1);
            blogPostRepository.save(post2);
            blogPostRepository.save(post3);
        }
    }
}
