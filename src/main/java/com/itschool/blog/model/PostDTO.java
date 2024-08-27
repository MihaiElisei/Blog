package com.itschool.blog.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

// Generates getters and setters
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long id;

    //title should not be empty and it should have at least 2 characters
    @NotEmpty
    @Size(min = 2, max = 50, message = "Post title should have at least 2 characters")
    private String title;

    //description should not be null or empty, and it should have at least 10 characters
    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;

    //content should not be null or empty
    @NotEmpty
    private String content;
    private Set<CommentDTO> comments;
}
