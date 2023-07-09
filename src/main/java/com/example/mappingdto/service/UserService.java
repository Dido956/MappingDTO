package com.example.mappingdto.service;


import com.example.mappingdto.model.dto.UserLoginDto;
import com.example.mappingdto.model.dto.UserRegisterDto;

public interface UserService{
    void registerUser(UserRegisterDto userRegisterDto);

    void loginUser(UserLoginDto userLoginDto);

    void logoutUser();
}
