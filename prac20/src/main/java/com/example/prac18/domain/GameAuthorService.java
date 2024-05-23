package com.example.prac18.domain;

import com.example.prac18.entities.GameAuthor;

import java.util.List;

public interface GameAuthorService {
    void addGameAuthor(GameAuthor gameAuthor);

    void removeGameAuthorByName(String name);

    List<GameAuthor> getGameAuthorByNickname(String nickname);

    List<GameAuthor> getGameAuthorByBirthday(String birthday);

    GameAuthor getGameAuthorById(Long id);

    List<GameAuthor> getGameAuthors();

}
