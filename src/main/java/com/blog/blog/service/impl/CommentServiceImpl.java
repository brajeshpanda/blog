package com.blog.blog.service.impl;

import com.blog.blog.entities.Comment;
import com.blog.blog.entities.Post;
import com.blog.blog.exception.BlogAPIException;
import com.blog.blog.exception.ResourceNotFoundException;
import com.blog.blog.payload.CommentDto;
import com.blog.blog.repository.CommentRepository;
import com.blog.blog.repository.PostRepository;
import com.blog.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepository;
    private CommentRepository commentRepository;

    private ModelMapper modelMapper;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto saveComment(Long postId, CommentDto commentDto) {

        Post post = postRepository.findById(postId).orElseThrow(

                () -> new ResourceNotFoundException("post not found with id :" + postId)
        );

        Comment comment = mapToEntity(commentDto);

        comment.setPost(post);

        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentByPostId(Long postId) {

        List<Comment> comments = commentRepository.findByPostId(postId);

        return  comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());

    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {


        Post post = postRepository.findById(postId).orElseThrow(

                () -> new ResourceNotFoundException("post not found with id :" + postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(

                () -> new ResourceNotFoundException("comment not found with id :" + commentId));

        if (!comment.getPost().getId().equals((post.getId()))) {
            throw new BlogAPIException("comment does not belong to post");
        }


        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long id, CommentDto commentDto) {

        Post post= postRepository.findById(postId).orElseThrow(

                () -> new ResourceNotFoundException("post is not found with id: "+postId)

        );

        Comment comment= commentRepository.findById(id).orElseThrow(

                () -> new ResourceNotFoundException("comment is not found with id: "+id)

        );

        if(!comment.getPost().getId().equals(post.getId()))
        {
            throw new BlogAPIException("comment does not belongs to  post");
        }

        comment.setBody(commentDto.getBody());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());

        Comment updateComment = commentRepository.save(comment);

        return mapToDto(updateComment);
    }

    @Override
    public void deleteComment(Long postId, Long id) {
        Post post= postRepository.findById(postId).orElseThrow(

                () -> new ResourceNotFoundException("post is not found with id: "+postId)

        );

        Comment comment= commentRepository.findById(id).orElseThrow(

                () -> new ResourceNotFoundException("comment is not found with id: "+id)

        );

        if(!comment.getPost().getId().equals(post.getId()))
        {
            throw new BlogAPIException("comment does not belongs to  post");
        }

        commentRepository.deleteById(id);

    }

    Comment mapToEntity(CommentDto commentDto)
    {
        Comment comment = modelMapper.map(commentDto, Comment.class);
//        Comment comment= new Comment();
//        comment.setBody(commentDto.getBody());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());

        return comment;
    }

    CommentDto mapToDto(Comment comment)
    {
        CommentDto Dto = modelMapper.map(comment, CommentDto.class);
//        CommentDto Dto= new CommentDto();
//        Dto.setId(comment.getId());
//        Dto.setName(comment.getName());
//        Dto.setEmail(comment.getEmail());
//        Dto.setBody(comment.getBody());

        return Dto;
    }

}
