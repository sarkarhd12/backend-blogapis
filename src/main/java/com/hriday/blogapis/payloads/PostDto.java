package com.hriday.blogapis.payloads;

import com.hriday.blogapis.entities.Category;
import com.hriday.blogapis.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
    private Integer postId;
    private String title;
    private String content;

    private String imageName;

    private Date addDate;

    private CategoryDto category;
    private UserDto user;

}
