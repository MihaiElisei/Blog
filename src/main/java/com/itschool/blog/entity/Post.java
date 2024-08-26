package com.itschool.blog.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generates getters and setters
@AllArgsConstructor // Generates all arguments constructors
@NoArgsConstructor
@Entity
@Table(name="posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "descritption")
    private String description;

    @Column(nullable = false, name = "content")
    private String content;
}
