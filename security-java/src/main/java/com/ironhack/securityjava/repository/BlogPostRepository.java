package com.ironhack.securityjava.repository;

import com.ironhack.securityjava.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {}