package com.itschool.blog.repository;

import com.itschool.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    //List of comments by post ID
    List<Comment> findByPostId(Long postId);
}
