package com.example.mappingdto.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;

@Entity(name = "user")
@Getter
@Setter
public class User extends BaseEntity {
    @Column
    private String email;
    @Column
    private String password;
    @Column(name = "full_name")
    private String fullName;
    @Column
    private boolean isAdmin;
    @ManyToMany
    @Fetch(FetchMode.JOIN)
    private Set<Game> games;

    //problem?
    public User() {
    }
}
