package com.itschool.blog.model;

import lombok.Data;

@Data // Generates getters and setters
public class PostDTO {

    private Long id;
    private String title;
    private String description;
    private String content;
}
