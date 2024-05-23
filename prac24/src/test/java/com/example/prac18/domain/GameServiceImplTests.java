package com.example.prac18.domain;

import com.example.prac18.data.GameRepository;
import com.example.prac18.entities.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class GameServiceImplTests {
    @Mock
    private GameRepository gameRepository;
    @Mock
    private GameAuthorService gameAuthorService;

    @Test
    void getGames() {
        Game game = new Game(
                0L,
                "vera",
                "asdfg"
        );
        Mockito.when(gameRepository.findAll()).thenReturn(List.of(game));
        GameService gameService = new GameServiceImpl(gameRepository, gameAuthorService);
        Assertions.assertEquals(1, gameService.getGames().size());
        Assertions.assertEquals("asdfg", gameService.getGames().get(0).getCreationDate());
    }

    @Test
    void getGameById() {
        Game game1 = new Game(
                0L,
                "vera",
                "asdfg"
        );
        Game game2 = new Game(
                1L,
                "stepa",
                "now"
        );
        Mockito.when(gameRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Mockito.when(gameRepository.findById(0L)).thenReturn(Optional.of(game1));
        Mockito.when(gameRepository.findById(1L)).thenReturn(Optional.of(game2));
        GameService gameService = new GameServiceImpl(gameRepository, gameAuthorService);
        Assertions.assertEquals("vera",gameService.getGameById(0L).getName());
        Assertions.assertEquals("qqandrey",gameService.getGameById(1L).getName());
        Assertions.assertNull(gameService.getGameById(2L));
    }

    @Test
    void getGameByCreationDate() {
        Game game1 = new Game(
                0L,
                "vera",
                "asdfg"
        );
        Game game2 = new Game(
                1L,
                "qqandrey",
                "now"
        );
        Mockito.when(gameRepository.findAllByCreationDate(Mockito.anyString())).thenReturn(List.of());
        Mockito.when(gameRepository.findAllByCreationDate("vera")).thenReturn(List.of(game1));
        Mockito.when(gameRepository.findAllByCreationDate("now")).thenReturn(List.of(game2));
        GameService gameService = new GameServiceImpl(gameRepository, gameAuthorService);
        Assertions.assertEquals(1,gameService.getGameByCreationDate("vera").size());
        Assertions.assertEquals(1,gameService.getGameByCreationDate("now").size());
        Assertions.assertEquals(0,gameService.getGameByCreationDate("2").size());
    }

    @Test
    void getGameByName() {
        Game game1 = new Game(
                0L,
                "vera",
                "asdfg"
        );
        Mockito.when(gameRepository.findAllByName(Mockito.anyString())).thenReturn(List.of());
        Mockito.when(gameRepository.findAllByName("vera")).thenReturn(List.of(game1));
        GameService gameService = new GameServiceImpl(gameRepository, gameAuthorService);
        Assertions.assertEquals(1, gameService.getGameByName("vera").size());
        Assertions.assertEquals(0, gameService.getGameByName("vera2").size());
    }
}
