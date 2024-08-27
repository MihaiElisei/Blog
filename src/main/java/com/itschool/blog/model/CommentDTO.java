package com.itschool.blog.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

// Generates getters and setters with lombok annotation
@Data
public class CommentDTO {

    private Long id;

    // name should not be null or empty
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    // email should not be null or empty
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;

    // content should not be null or empty and it should have at least 10 characters
    @NotEmpty(message = "Content should not be null or empty")
    @Size(min = 10, message = "Content should have at least 10 characters")
    private String content;
}
