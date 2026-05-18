package com.ironhack.securityjava.entity;

import com.ironhack.securityjava.entity.BlogPost;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
@Entity
@Table(name = "authors")
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<BlogPost> posts;
}