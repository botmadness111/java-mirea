package com.example.prac18.domain;

import com.example.prac18.data.GameAuthorRepository;
import com.example.prac18.entities.GameAuthor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class GameAuthorServiceTests {
    @Mock
    private GameAuthorRepository gameAuthorRepository;
    @Test
    void getGameAuthorByNickname() {
        GameAuthor gameAuthor = new GameAuthor(
                0L,
                "qqandrey",
                "zxcd"
        );
        Mockito.when(gameAuthorRepository.getGameAuthorsByNickname("qqandrey")).thenReturn(List.of(gameAuthor, gameAuthor));
        GameAuthorService gameAuthorService = new GameAuthorServiceImpl(gameAuthorRepository);
        Assertions.assertEquals(2, gameAuthorService.getGameAuthorByNickname("qqandrey").size());
    }

    @Test
    void getGameAuthorByBirthday() {
        GameAuthor gameAuthor = new GameAuthor(
                0L,
                "qqandrey",
                "zxcv"
        );
        Mockito.when(gameAuthorRepository.getGameAuthorsByBirthday("zxcv")).thenReturn(List.of(gameAuthor, gameAuthor, gameAuthor));
        GameAuthorService gameAuthorService = new GameAuthorServiceImpl(gameAuthorRepository);
        Assertions.assertEquals(3, gameAuthorService.getGameAuthorByBirthday("zxcv").size());
    }

    @Test
    void getGameAuthorById() {
        GameAuthor gameAuthor = new GameAuthor(
                0L,
                "qqandrey",
                "zxcv"
        );
        Mockito.when(gameAuthorRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Mockito.when(gameAuthorRepository.findById(0L)).thenReturn(Optional.of(gameAuthor));
        GameAuthorService gameAuthorService = new GameAuthorServiceImpl(gameAuthorRepository);
        Assertions.assertNotNull(gameAuthorService.getGameAuthorById(0L));
        Assertions.assertNull(gameAuthorService.getGameAuthorById(2L));
    }

    @Test
    void getGameAuthors() {
        GameAuthor gameAuthor = new GameAuthor(
                0L,
                "qqandrey",
                "zxcv"
        );
        Mockito.when(gameAuthorRepository.findAll()).thenReturn(List.of(gameAuthor));
        GameAuthorService gameAuthorService = new GameAuthorServiceImpl(gameAuthorRepository);
        Assertions.assertEquals(1, gameAuthorService.getGameAuthors().size());
    }
}
