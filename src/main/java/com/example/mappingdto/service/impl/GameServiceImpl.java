package com.example.mappingdto.service.impl;

import com.example.mappingdto.model.dto.GameAddDto;
import com.example.mappingdto.model.dto.ViewGameDetailsDto;
import com.example.mappingdto.model.entity.Game;
import com.example.mappingdto.model.entity.Order;
import com.example.mappingdto.repository.GameRepository;
import com.example.mappingdto.service.GameService;
import com.example.mappingdto.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    @Override
    public void addGame(GameAddDto gameAddDto) {

        Set<ConstraintViolation<GameAddDto>> violations = validationUtil.getViolations(gameAddDto);

        if (!violations.isEmpty()) {
            violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        Game game = modelMapper.map(gameAddDto, Game.class);

        gameRepository.save(game);
        System.out.println("Added game " + gameAddDto.getTitle());

    }

    @Override
    public void editGame(Long gameId, BigDecimal price, Double size) {
        Game game = gameRepository
                .findById(gameId)
                .orElse(null);

        if (game == null) {
            System.out.println("Id is not correct!");
            return;
        }

        game.setPrice(price);
        game.setSize(size);

        gameRepository.save(game);
        System.out.println("Game updated.");
    }

    @Override
    public void deleteGame(Long Id) {
        Game game = gameRepository
                .findById(Id)
                .orElse(null);

        if (game == null) {
            System.out.println("Id is not correct!");
            return;
        }

        gameRepository.delete(game);
        System.out.println("Deleted game " + game.getTitle());
    }

    @Override
    public void retrieveAllGames() {
        List<Game> allGames = gameRepository.findAll();

        allGames
                .forEach(game -> System.out.println(game.getTitle() + " " + game.getPrice()));
    }

    @Override
    public void getGameDetails(String gameTitle) {
        Game game = gameRepository.findGameByTitle(gameTitle);

        if (game == null) {
            System.out.println("No such game in database.");
            return;
        }
            ViewGameDetailsDto viewGameDetailsDto = mapGameToGameDTO(game);

        System.out.printf(
                "Title: %s%n" +
                        "Price: %.2f%n" +
                        "Description: %s%n" +
                        "Release Date: %s%n",
                viewGameDetailsDto.getTitle(),
                viewGameDetailsDto.getPrice(),
                viewGameDetailsDto.getDescription(),
                viewGameDetailsDto.getReleaseDate());
    }


    private static ViewGameDetailsDto mapGameToGameDTO(Game game) {
        ViewGameDetailsDto viewGameDetailsDto = new ViewGameDetailsDto();
        viewGameDetailsDto.setTitle(game.getTitle());
        viewGameDetailsDto.setDescription(game.getDescription());
        viewGameDetailsDto.setPrice(game.getPrice());
        viewGameDetailsDto.setReleaseDate(game.getReleaseDate());
        return viewGameDetailsDto;

    }

}
