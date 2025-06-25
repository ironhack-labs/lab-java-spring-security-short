package com.example.lab10.repository;

import com.example.lab10.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository  extends JpaRepository<BlogPost, Long> {
}
