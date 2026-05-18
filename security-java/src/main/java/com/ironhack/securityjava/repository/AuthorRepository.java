package com.ironhack.securityjava.repository;

import com.ironhack.securityjava.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {}