package com.ironhack.blogcms.repository;

import com.ironhack.blogcms.model.BlogPost;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    /** Loads every post together with its author in one query (no N+1). */
    @Override
    @EntityGraph(attributePaths = "author")
    List<BlogPost> findAll();

    /** Loads one post together with its author in one query. */
    @Override
    @EntityGraph(attributePaths = "author")
    Optional<BlogPost> findById(Long id);

    boolean existsByAuthorId(Long authorId);
}
