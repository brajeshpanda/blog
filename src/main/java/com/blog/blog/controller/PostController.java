package com.blog.blog.controller;


import com.blog.blog.payload.PostDto;
import com.blog.blog.payload.PostResponse;
import com.blog.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto)
    {
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);

    }

    //http://localhost:8080/api/posts?pageNo=0&pageSize=3&sortBy=title&sortDir=desc
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value="pageNo" , defaultValue="0" , required=false) int pageNo ,
            @RequestParam(value="pageSize",defaultValue="5",required=false) int pageSize,
            @RequestParam(value="sortBy",defaultValue="id",required=false) String sortBy,
            @RequestParam(value="sortDir",defaultValue="asc",required=false) String sortDir
)
    {
        PostResponse postResponse= postService.getAllPosts(pageNo , pageSize , sortBy , sortDir);

        return postResponse;
    }


    //http://localhost:8080/api/posts/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id)
    {
       PostDto dto= postService.getPostById(id);

       return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto , @PathVariable("id") long id)
    {
        PostDto dto = postService.updatePost(postDto ,id);
        return new ResponseEntity<>(dto , HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id)
    {
        postService.deletePost(id);

        return new ResponseEntity<>("post is deleted" , HttpStatus.OK);
    }

}
