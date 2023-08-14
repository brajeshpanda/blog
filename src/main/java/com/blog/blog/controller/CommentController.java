package com.blog.blog.controller;


import com.blog.blog.payload.CommentDto;
import com.blog.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    //http://localhost:8080/api/posts/1/comments
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId") Long postId ,
                                                   @RequestBody CommentDto commentDto)

    {
        CommentDto dto = commentService.saveComment(postId, commentDto);

        return new ResponseEntity<> (dto, HttpStatus.CREATED);

    }

    //http://localhost:8080/api/posts/3/comments/1
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getsCommentByPostId(@PathVariable("postId") Long postId)
    {
        return  commentService.getCommentByPostId(postId);

    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto>  getCommentById(@PathVariable("postId") Long postId ,
                                                      @PathVariable("commentId") Long commentId)

    {
        CommentDto dto = commentService.getCommentById(postId, commentId);

       return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("postId") Long postId,
                                                    @PathVariable("id") Long id ,
                                                    @RequestBody CommentDto commentDto)
    {
        CommentDto dto= commentService.updateComment(postId,id,commentDto);

        return new ResponseEntity<>(dto,HttpStatus.OK);
    }


    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("postId") Long postId ,
                                                @PathVariable("id") Long id)
    {
        commentService.deleteComment(postId,id);

        return new ResponseEntity<>("comment is deleted" , HttpStatus.OK);
    }



}
