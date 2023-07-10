package com.example.mappingdto.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewGameDetailsDto {
    private String title;
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;
}
