package com.hriday.blogapis.payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String userName;
    private String password;
}
