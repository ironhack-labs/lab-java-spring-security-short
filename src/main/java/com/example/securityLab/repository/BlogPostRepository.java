package com.example.securityLab.repository;

import com.example.securityLab.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost,Long> {
}
