package com.hriday.blogapis.services;

import com.hriday.blogapis.entities.Post;
import com.hriday.blogapis.payloads.PostDto;
import com.hriday.blogapis.payloads.PostResponse;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.Iterator;
import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId,Integer categoryId);
    PostDto updatePost(PostDto postDto,Integer postId);
    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy);
    PostDto getPostById(Integer postId);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> searchPost(String keyword);
}
