package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @JsonIgnore // JSON serializasiyasında sonsuz rekurziyanın qarşısını alır
    private List<BlogPost> posts;

    public Author() {}

    public Author(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<BlogPost> getPosts() { return posts; }
    public void setPosts(List<BlogPost> posts) { this.posts = posts; }
}