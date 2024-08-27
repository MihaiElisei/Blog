package com.itschool.blog.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter //Generate Getters
@Setter //Generate Setters
@AllArgsConstructor
@NoArgsConstructor
@Entity // Annotation to tell Hibernate that this class is an entity and should be persisted in the database
@Table(name="posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}) // Annotation to tell Hibernate that this entity should be mapped to the 'posts' table in the database
public class Post {

    @Id // Annotation to tell Hibernate that this field is the primary key in the table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Annotation to tell Hibernate to generate new ids for us. GenerationType.IDENTITY will use the database's auto-increment feature (will increment the id by 1)
    private Long id;


    private String title;
    private String description;
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true) // mappedBy is used to specify the field in the Comment class that owns the relationship and orphanRemoval will remove all parent it will also remove the child wh
    private Set<Comment> comments = new HashSet<>();

}
