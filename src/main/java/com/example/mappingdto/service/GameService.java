package com.example.mappingdto.service;

import com.example.mappingdto.model.dto.GameAddDto;

import java.math.BigDecimal;

public interface GameService {
    void addGame(GameAddDto gameAddDto);

    void editGame(Long gameId, BigDecimal price, Double size);

    void deleteGame(Long Id);

    void retrieveAllGames();

    void getGameDetails(String game);

}
