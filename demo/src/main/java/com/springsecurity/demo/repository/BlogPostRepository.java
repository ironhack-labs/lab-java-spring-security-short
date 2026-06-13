package com.springsecurity.demo.repository;

import com.springsecurity.demo.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
}