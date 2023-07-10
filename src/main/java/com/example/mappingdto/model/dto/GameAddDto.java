package com.example.mappingdto.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class GameAddDto {
    @Pattern(regexp = "[A-Z][a-z]{6,100}", message = "Enter valid title!")
    private String title;
    @DecimalMin(value = "0", message = "Enter valid price!")
    private BigDecimal price;
    @Positive
    private Double size;
    @Size(min = 11, max = 11, message = "Enter valid trailer!")
    private String trailer;
    @Pattern(regexp = "(https?).+", message = "Enter valid URL!")
    private String thumbnailURL;
    @Size(min = 20, message = "Enter valid description!")
    private String description;
    private LocalDate releaseDate;

    public GameAddDto() {
    }
}
