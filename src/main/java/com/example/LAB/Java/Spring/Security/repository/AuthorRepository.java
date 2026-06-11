package com.example.LAB.Java.Spring.Security.repository;

import com.example.LAB.Java.Spring.Security.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}


