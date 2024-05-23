package com.example.prac18.data;

import com.example.prac18.entities.GameAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameAuthorRepository extends JpaRepository<GameAuthor, Long> {
    void deleteGameAuthorByNickname(String nickname);

    List<GameAuthor> getGameAuthorsByNickname(String nickname);

    List<GameAuthor> getGameAuthorsByBirthday(String nickname);
}
