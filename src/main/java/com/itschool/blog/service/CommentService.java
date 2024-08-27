package com.itschool.blog.service;


import com.itschool.blog.model.CommentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    CommentDTO addComment(Long postId, CommentDTO commentDTO);

    List<CommentDTO> getCommentsByPostId(Long postId);

    CommentDTO getCommentById(Long postId, Long commentId);

    CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDTO);

    void deleteComment(Long postId, Long commentId);
}
