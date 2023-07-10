package com.example.mappingdto.repository;

import com.example.mappingdto.model.entity.Game;
import com.example.mappingdto.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

        Optional<User> findByEmailAndPassword(String email, String password);
}
