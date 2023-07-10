package com.example.mappingdto.service.impl;

import com.example.mappingdto.model.dto.UserLoggedInGameTitlesDto;
import com.example.mappingdto.model.dto.UserLoginDto;
import com.example.mappingdto.model.dto.UserRegisterDto;
import com.example.mappingdto.model.entity.Game;
import com.example.mappingdto.model.entity.Order;
import com.example.mappingdto.model.entity.User;
import com.example.mappingdto.repository.GameRepository;
import com.example.mappingdto.repository.OrderRepository;
import com.example.mappingdto.repository.UserRepository;
import com.example.mappingdto.service.UserService;
import com.example.mappingdto.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private User loggedInUser;
    private Order currentOrder;
    private Set<Game> currentOrderGames;


    public UserServiceImpl(UserRepository userRepository, GameRepository gameRepository, OrderRepository orderRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        currentOrderGames = new HashSet<>();
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            System.out.println("Passwords don't match!");
            return;
        }

        Set<ConstraintViolation<UserRegisterDto>> violations = validationUtil.getViolations(userRegisterDto);

        if (!violations.isEmpty()) {
            violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        User user = modelMapper.map(userRegisterDto, User.class);

        userRepository.save(user);
    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        Set<ConstraintViolation<UserLoginDto>> violations = validationUtil.getViolations(userLoginDto);
        if (!violations.isEmpty()) {
            violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        User user = userRepository
                .findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword())
                .orElse(null);

        if (user == null) {
            System.out.println("Incorrect username/password");
            return;
        }

        System.out.println("Successfully logged in!");
        loggedInUser = user;
    }

    @Override
    public void logoutUser() {
        if (loggedInUser == null) {
            System.out.println("Cannot log out. No user was logged in.");
            return;
        } else {
            loggedInUser = null;
        }
    }

    @Override
    public void getAllGamesByLoggedInUser() {
        if (loggedInUser == null) {
            System.out.println("No logged in user");
            return;
        }

        UserLoggedInGameTitlesDto currentUser = new UserLoggedInGameTitlesDto();
        modelMapper.map(loggedInUser, currentUser);


        if (currentUser.getGames().isEmpty()) {
            System.out.println("User owns no games");
            return;
        }

        currentUser
                .getGames()
                .stream()
                .map(Game::getTitle)
                .forEach(System.out::println);
    }

    @Override
    public void addItemToCart(String gameName) {

        if (loggedInUser == null) {
            System.out.println("No logged in user");
            return;
        }

        checkIfUserOwnsGame(gameName, loggedInUser);

        Game gameToAdd = gameRepository.findGameByTitle(gameName);
        currentOrder = new Order();
        currentOrder.setBuyer(loggedInUser);

        currentOrderGames.add(gameToAdd);

        currentOrder.setGames(currentOrderGames);
        orderRepository.save(currentOrder);
    }

    @Override
    public void removeItemFromCart(String gameName) {
        if (loggedInUser == null) {
            System.out.println("No logged in user");
            return;
        }

        Game gameToRemove = gameRepository.findGameByTitle(gameName);

        if (gameToRemove == null) {
            System.out.println("No such game");
            return;
        }

        orderRepository.findByGameName(gameName);

        Long gameToRemoveId = gameToRemove.getId();



    }

    private static void checkIfUserOwnsGame(String gameName, User loggedInUser) {
        boolean gameAlreadyOwned = loggedInUser
                .getGames()
                .stream()
                .map(Game::getTitle)
                .filter(title -> title.equals(gameName))
                .anyMatch(title -> true);

        if (gameAlreadyOwned) {
            System.out.println("User has this game already.");
            return;
        }
    }

}
