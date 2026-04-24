package com.ironhack.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ironhack.lab.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
