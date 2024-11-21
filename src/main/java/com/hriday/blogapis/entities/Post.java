package com.hriday.blogapis.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name = "post_title")
    private String title;

    private String imageName;

    @Column(length = 10000)
    private String content;

    private Date addDate;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

}
