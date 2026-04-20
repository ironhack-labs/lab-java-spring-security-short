package com.ironhack.securitylab.repository;

import com.ironhack.securitylab.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
