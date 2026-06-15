package com.content.management.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "blogs")
public class BlogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String post;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorModel author;


}
