package com.example.lab_java_spring_security_short.repository;

import com.example.lab_java_spring_security_short.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {}
