package com.hriday.blogapis.payloads;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    @NotEmpty
    @Size(min = 4,message = "Username must be min of 4 character")
    private String name;

    @Email(message = "Email address not valid")
    private String email;

    @NotEmpty
    @Size(min = 4,max = 10,message = "Password must be between 3 & 10 character")
    private String password;

    @NotEmpty
    private String about;

    @JsonIgnore
    public String getPassword(){
        return this.password;
    }
    @JsonProperty
    public  void setPassword(String password){
        this.password = password;
    }
}
