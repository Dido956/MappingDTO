package com.example.mappingdto.repository;

import com.example.mappingdto.model.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Game findGameByTitle(String title);
}
