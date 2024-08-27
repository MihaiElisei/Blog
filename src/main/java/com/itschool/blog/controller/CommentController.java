package com.itschool.blog.controller;

import com.itschool.blog.model.CommentDTO;
import com.itschool.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) { // Constructor-based dependency injection. It injects the CommentService bean into the CommentController
        this.commentService = commentService;
    }

    //Create comment to a post based by post ID
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment( @PathVariable(value = "postId") Long postId,@Valid @RequestBody CommentDTO commentDTO) { // @RequestBody annotation needed because the request body is a JSON object that holds another JSON object
        return new ResponseEntity<>(commentService.addComment(postId, commentDTO), HttpStatus.CREATED);
    }

    //Get comments by postId
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDTO> getCommentsByPostId(@PathVariable(value = "postId") Long postId){
        return commentService.getCommentsByPostId(postId);
    }

    //Get comment by comment id
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable(value = "postId") Long postId,@PathVariable(value = "commentId") Long commentId){
        CommentDTO commentDTO = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    //Update a comment
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable(value = "postId")Long postId,
                                                    @PathVariable(value = "commentId") Long commentId,
                                                    @Valid @RequestBody CommentDTO commentDTO){
        CommentDTO updateComment = commentService.updateComment(postId, commentId, commentDTO);

        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }

    //Delete Comment
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") Long postId,
                                                @PathVariable(value = "commentId") Long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
