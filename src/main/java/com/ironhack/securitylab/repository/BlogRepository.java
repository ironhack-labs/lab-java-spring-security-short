package com.ironhack.securitylab.repository;

import com.ironhack.securitylab.entity.Author;
import com.ironhack.securitylab.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Post, Long> {
}
