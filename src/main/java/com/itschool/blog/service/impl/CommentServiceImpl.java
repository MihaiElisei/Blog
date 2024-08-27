package com.itschool.blog.service.impl;

import com.itschool.blog.entity.Comment;
import com.itschool.blog.entity.Post;
import com.itschool.blog.exception.BlogAPIException;
import com.itschool.blog.exception.ResourceNotFoundException;
import com.itschool.blog.model.CommentDTO;
import com.itschool.blog.repository.CommentRepository;
import com.itschool.blog.repository.PostRepository;
import com.itschool.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    // Inject post, comment repositories and modelMapper
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    // Add new comment or threw an error if post does not exist
    @Override
    public CommentDTO addComment(Long postId, CommentDTO commentDTO) {

        Comment comment = mapToComment(commentDTO);

        //Retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        //Set post to comment entity
        comment.setPost(post);

        //Save comment to db
        Comment newComment = commentRepository.save(comment);

        return mapToDTO(newComment);
    }

    // Get all comments from a post
    @Override
    public List<CommentDTO> getCommentsByPostId(Long postId) {

        //Retrieve comments by postId
        List<Comment> comments = commentRepository.findByPostId(postId);

        // convert list opf comment entities to list of comment DTO's and return it
        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }

    // Get a specific comment by id or threw an error if comment does not exist
    @Override
    public CommentDTO getCommentById(Long postId, Long commentId) {

        //Retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        //Retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        //check if the comment belongs to a particular post or not
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return mapToDTO(comment);
    }

    //Update a comment or threw an error if comment does not exist
    @Override
    public CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDTO) {

        //Retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        //Retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        //check if the comment belongs to a particular post or not
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        //Update comment object and save it in db
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setContent(commentDTO.getContent());
        Comment updatedComment = commentRepository.save(comment);

        return mapToDTO(updatedComment);
    }

    //Delete a comment or threw an error if comment does not exist
    @Override
    public void deleteComment(Long postId, Long commentId) {
        //Retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        //Retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        //check if the comment belongs to a particular post or not
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        commentRepository.delete(comment);
    }


    // convert entity into DTO
    private CommentDTO mapToDTO(Comment comment) {
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class); // Map CommentDTO object in the Comment Object
        return commentDTO;
    }

    // convert DTO to entity
    private Comment mapToComment(CommentDTO commentDTO) {
        Comment comment = modelMapper.map(commentDTO, Comment.class); // Map Comment object in the CommentDTO Object
        return comment;
    }
}
