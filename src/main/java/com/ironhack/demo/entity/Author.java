package com.ironhack.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Author")
public class Author {
@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    @Column
    private  String name;
    @OneToMany(mappedBy ="author")
    private List<Post> posts;

    public Author(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getBlogPosts() {
        return posts;
    }

    public void setBlogPosts(List<Post> posts) {
        this.posts = posts;
    }
}
