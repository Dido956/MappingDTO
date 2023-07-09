package com.example.mappingdto.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto {
    @Email(message = "Enter valid email!")
    private String email;
    @Pattern(regexp = "[A-Za-z\\d]{6,}", message = "Password is wrong for this user!")
    private String password;

    public UserLoginDto() {
    }

    public UserLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
