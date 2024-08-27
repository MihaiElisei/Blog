package com.itschool.blog.model;

import lombok.Data;

// Generates getters and setters with lombok annotation
@Data
public class CommentDTO {

    private Long id;
    private String name;
    private String email;
    private String content;
}
