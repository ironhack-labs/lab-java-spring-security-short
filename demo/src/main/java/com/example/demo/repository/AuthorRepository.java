package com.example.demo.repository;

import com.example.demo.entity.Author;

import org.springframework.data.auditing.AuditingHandler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AuthorRepository extends JpaRepository<Author,Long> {
    boolean existsByEmail(String email);
    Optional<Author> findByEmail(String email);

}
