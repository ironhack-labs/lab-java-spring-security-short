package com.example.lab_java_spring_security_short.repository;

import com.example.lab_java_spring_security_short.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {}

