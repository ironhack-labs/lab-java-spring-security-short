package com.ironhack.blogcms.repository;

import com.ironhack.blogcms.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
