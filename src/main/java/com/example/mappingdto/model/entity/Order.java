package com.example.mappingdto.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity (name = "orders")
@Getter@Setter
public class Order extends BaseEntity{
    @ManyToOne
    private User buyer;
    @ManyToMany
    private Set<Game> games;

    public Order (){

    }
}
