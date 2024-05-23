package com.example.prac18.domain;

import com.example.prac18.data.GameAuthorRepository;
import com.example.prac18.entities.GameAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameAuthorServiceImpl implements GameAuthorService {
    @Autowired
    private GameAuthorRepository gameAuthorRepository;


    @Override
    public void addGameAuthor(GameAuthor gameAuthor) {
        gameAuthorRepository.saveAndFlush(gameAuthor);
    }

    @Override
    public void removeGameAuthorByName(String name) {
        gameAuthorRepository.deleteGameAuthorByNickname(name);
    }

    @Override
    public List<GameAuthor> getGameAuthorByNickname(String nickname) {
        return gameAuthorRepository.getGameAuthorsByNickname(nickname);
    }

    @Override
    public List<GameAuthor> getGameAuthorByBirthday(String birthday) {
        return gameAuthorRepository.getGameAuthorsByBirthday(birthday);
    }

    @Override
    public GameAuthor getGameAuthorById(Long id) {
        Optional<GameAuthor> gameAuthor = gameAuthorRepository.findById(id);
        return gameAuthor.orElse(null);
    }

    @Override
    public List<GameAuthor> getGameAuthors() {
        return gameAuthorRepository.findAll();
    }
}
