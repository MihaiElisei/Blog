package com.itschool.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY) // fetch is used to specify how the posts should be fetched (LAZY will fetch the posts only when they are accessed)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
