package com.example.blogsecurity.repository;

import com.example.blogsecurity.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
