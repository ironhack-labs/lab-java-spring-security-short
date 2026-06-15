package com.content.management.repository;

import com.content.management.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository < AuthorModel, Long> {
}
