package com.example.LAB.Java.Spring.Security.repository;

import com.example.LAB.Java.Spring.Security.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
}


