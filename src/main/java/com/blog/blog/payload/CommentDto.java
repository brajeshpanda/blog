package com.blog.blog.payload;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {

    private long id;

    @NotEmpty
    @Size(min = 15, message = "Post title should have at least 15 characters")
    private String name;

    @NotEmpty
    @Size(min = 10, message = "Post title should have at least 10 characters")
    private String email;

    @NotEmpty
    @Size(min = 100, message = "Post title should have at least 100 characters")
    private String body;
}
