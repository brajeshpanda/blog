package com.blog.blog.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="posts")
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title" ,nullable=false , unique = true)
    private String title;

    @Column(name="description" ,nullable=false)
    private String description;

    @Column(name="content" ,nullable=false)
    private String content;

    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL,fetch=FetchType.LAZY , orphanRemoval = true)
    private Set<Comment> comments= new HashSet<>();
}
