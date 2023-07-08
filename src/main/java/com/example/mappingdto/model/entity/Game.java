package com.example.mappingdto.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "games")
@Getter@Setter
public class Game extends BaseEntity {
    @Column
    private String title;
    @Column
    private String trailer;
    @Column
    private String image;
    @Column
    private Integer size;
    @Column
    private BigDecimal price;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column
    private LocalDate releaseDate;

    public Game() {
    }
}
