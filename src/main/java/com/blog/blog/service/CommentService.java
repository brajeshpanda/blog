package com.blog.blog.service;

import com.blog.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto saveComment(Long postId , CommentDto commentDto);

    List<CommentDto> getCommentByPostId(Long postId);

    CommentDto getCommentById(Long postId , Long commentId);


    CommentDto updateComment(Long postId, Long id, CommentDto commentDto);

    void deleteComment(Long postId, Long id);
}
