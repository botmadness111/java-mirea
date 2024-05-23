package com.example.prac18.data;

import com.example.prac18.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findAllByCreationDate(String creationDate);

    List<Game> findAllByName(String name);

    void deleteByName(String name);
}
