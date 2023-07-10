package com.example.mappingdto.model.dto;

import com.example.mappingdto.model.entity.Game;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoggedInGameTitlesDto {
    private Set<Game> games;
}
