package com.hriday.blogapis.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class JwtAuthResponse {
    private String token;
    private UserDto user;
}
