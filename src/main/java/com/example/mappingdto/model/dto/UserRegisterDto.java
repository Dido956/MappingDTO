package com.example.mappingdto.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UserRegisterDto {
    @Email(message = "Invalid email!")
    private String email;
    @Pattern(regexp = "[A-Za-z\\d]{6,}", message = "Enter valid password")
    private String password;
    private String confirmPassword;
    @Size(min = 2)
    private String fullName;

    public UserRegisterDto() {
    }

    public UserRegisterDto(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
    }
}
