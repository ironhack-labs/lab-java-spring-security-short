package com.ironhack.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ironhack.lab.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
