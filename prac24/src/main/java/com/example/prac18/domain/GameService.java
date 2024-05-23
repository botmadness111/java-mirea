package com.example.prac18.domain;

import com.example.prac18.entities.Game;
import com.example.prac18.requests.GameRequestBody;

import java.util.List;

public interface GameService {
    void addGame(GameRequestBody game);

    List<Game> getGameByName(String name);

    List<Game> getGameByCreationDate(String creationDate);

    Game getGameById(Long id);

    void removeGameByName(String name);

    List<Game> getGames();
}
