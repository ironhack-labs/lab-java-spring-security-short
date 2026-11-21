package com.ironhack.blogcms.config;

import com.ironhack.blogcms.model.Author;
import com.ironhack.blogcms.model.BlogPost;
import com.ironhack.blogcms.repository.AuthorRepository;
import com.ironhack.blogcms.repository.BlogPostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

/** Loads the author and blog post rows from the lab brief into an empty database. */
@Configuration
public class DataSeeder {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    @Bean
    public CommandLineRunner seedData(AuthorRepository authors, BlogPostRepository posts, TransactionTemplate tx) {
        return args -> tx.executeWithoutResult(status -> {
            if (authors.count() > 0) {
                return;
            }
            Author aiko = authors.save(new Author("Aiko Tanaka"));
            Author jonas = authors.save(new Author("Jonas Schmidt"));
            authors.save(new Author("Cas Van Dijk"));

            posts.save(new BlogPost(aiko, "Boost Your Productivity with 10 Easy Tips",
                    "Productivity - we all want it but it seems ..."));
            posts.save(new BlogPost(aiko, "How to Focus",
                    "Do you ever sit down to work and find yourself ..."));
            posts.save(new BlogPost(jonas, "Learn to Speed Read in 30 Days",
                    "Knowledge, not ability, is the great determiner of ..."));

            log.info("Seeded {} authors and {} blog posts", authors.count(), posts.count());
        });
    }
}
