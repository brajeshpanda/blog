package com.blog.blog.service;

import com.blog.blog.payload.PostDto;
import com.blog.blog.payload.PostResponse;

import java.util.List;

public  interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo , int pageSize , String sortBy , String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePost(long id);
}
