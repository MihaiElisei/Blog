package com.itschool.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Annotation to tell Hibernate that this class is an entity and should be persisted in the database
@Data // Generate all getters and setters
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments") // Annotation to tell Hibernate that this entity should be mapped to the 'comments' table in the database
public class Comment {

    @Id // Annotation to tell Hibernate that this field is the primary key in the table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Annotation to tell Hibernate to generate new ids for us. GenerationType.IDENTITY will use the database's auto-increment feature (will increment the id by 1)
    private Long id;

    private String name;
    private String email;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY) // fetch is used to specify how the posts should be fetched (LAZY will fetch the posts only when they are accessed)
    @JoinColumn(name = "post_id", nullable = false) // name will be the column name in the 'comments' table
    private Post post;
}
