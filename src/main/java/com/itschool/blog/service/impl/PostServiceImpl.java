package com.itschool.blog.service.impl;

import com.itschool.blog.entity.Post;
import com.itschool.blog.exception.ResourceNotFoundException;
import com.itschool.blog.model.PostDTO;
import com.itschool.blog.model.PostResponse;
import com.itschool.blog.repository.PostRepository;
import com.itschool.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostServiceImpl implements PostService {

    // Inject post repository and modelMapper
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }


    //Create a new post
    @Override
    public PostDTO createPost(PostDTO postDTO) {

        // convert DTO to entity
        Post post = mapToEntity(postDTO);

        Post newPost = postRepository.save(post);

        // convert entity to DTO
        PostDTO postResponse = mapToDTO(newPost);

        return postResponse;
    }

    // Get all posts
    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        // Create a sort object by sorting order
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // Pagination
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort); //Create a Pageable instance

        Page<Post> posts = postRepository.findAll(pageable); //Find all posts

        //Get content from page object
        List<Post> listOfPosts = posts.getContent();

        List<PostDTO> content = listOfPosts.stream().map(post -> mapToDTO(post)).toList();
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        return postResponse;
    }

    //Get post by ID or threw an error if post does not exist
    @Override
    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDTO(post);
    }

    //Update a existing post or threw an error if post does not exist
    @Override
    public PostDTO updatePost(PostDTO postDTO, Long id) {
        // get post by id from db and if it doesn't exist throw an exception
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        // Save the updated post
        Post updatedPost = postRepository.save(post);

        return mapToDTO(updatedPost);
    }

    // Delete post by ID or threw an error if post does not exist
    @Override
    public void deletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    // convert entity into DTO using model mapper
    private PostDTO mapToDTO(Post post) {
       PostDTO postDTO = modelMapper.map(post, PostDTO.class); // Map PostDTO object in the Post Object
        return postDTO;
    }

    // convert DTO to entity using model mapper
    private Post mapToEntity(PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class); // Map Post object in the PostDTO Object
        return post;
    }
}
