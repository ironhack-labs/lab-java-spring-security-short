package com.example.demo.repository;

import com.example.demo.dto.BlogPostResponse;
import com.example.demo.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BlogPostRepository extends JpaRepository<BlogPost,Long> {
    @Query("SELECT p FROM BlogPost p JOIN FETCH p.author WHERE p.id = :id")
    Optional<BlogPost> findByIdWithAuthor(@Param("id") Long id);

    List<BlogPost> findAll();

}
