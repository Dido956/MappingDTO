package com.example.mappingdto;

import com.example.mappingdto.model.dto.UserLoginDto;
import com.example.mappingdto.model.dto.UserRegisterDto;
import com.example.mappingdto.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final BufferedReader bufferedReader;
    private final UserService userService;

    public CommandLineRunnerImpl(UserService userService) {
        this.userService = userService;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {
        while (true){
            System.out.println("Enter your commands:");
            String [] commands = bufferedReader.readLine().split("\\|");

            switch (commands[0]){
                case "RegisterUser" -> userService
                        .registerUser(
                                new UserRegisterDto(commands[1],commands[2],commands[3],commands[4]));
                case "LoginUser" -> userService
                        .loginUser(new UserLoginDto(commands[1],commands[2]));
                case "Logout" -> userService
                        .logoutUser();
            }
        }
    }
}
