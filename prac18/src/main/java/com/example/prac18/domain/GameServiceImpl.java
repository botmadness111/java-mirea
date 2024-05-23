package com.example.prac18.domain;

import com.example.prac18.data.GameRepository;
import com.example.prac18.entities.Game;
import com.example.prac18.entities.GameAuthor;
import com.example.prac18.requests.GameRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired GameAuthorService gameAuthorService;

    @Override
    public void addGame(GameRequestBody game) {
        GameAuthor gameAuthor = gameAuthorService.getGameAuthorById(game.authorId());
        if (gameAuthor == null) {
            return;
        }
        Game newGame = new Game();
        newGame.setGameAuthor(gameAuthor);
        newGame.setCreationDate(game.creationDate());
        newGame.setName(game.name());
        gameRepository.save(newGame);
    }

    @Override
    public List<Game> getGameByName(String name) {
        return gameRepository.findAllByName(name);
    }

    @Override
    public List<Game> getGameByCreationDate(String creationDate) {
        return gameRepository.findAllByCreationDate(creationDate);
    }

    @Override
    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }


    @Override
    public void removeGameByName(String name) {
        gameRepository.deleteByName(name);
    }

    @Override
    public List<Game> getGames() {
        return gameRepository.findAll();
    }
}
