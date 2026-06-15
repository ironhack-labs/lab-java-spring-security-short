package com.content.management.repository;

import com.content.management.model.BlogModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository <BlogModel, Long> {
}
