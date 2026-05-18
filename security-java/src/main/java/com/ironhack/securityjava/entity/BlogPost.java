package com.ironhack.securityjava.entity;

import com.ironhack.securityjava.entity.Author;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "blog_posts")
@Data
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String post;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}