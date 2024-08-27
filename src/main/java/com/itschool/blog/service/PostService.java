package com.itschool.blog.service;


import com.itschool.blog.model.PostDTO;
import com.itschool.blog.model.PostResponse;
import org.springframework.stereotype.Service;


@Service
public interface PostService {

    PostDTO createPost(PostDTO postDTO);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDTO getPostById(Long id);

    PostDTO updatePost(PostDTO postDTO, Long id);

    void deletePostById(Long id);
}
