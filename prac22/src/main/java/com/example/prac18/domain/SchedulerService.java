package com.example.prac18.domain;

import com.example.prac18.entities.Game;
import com.example.prac18.entities.GameAuthor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchedulerService {
    private final GameAuthorService gameAuthorService;
    private final GameService gameService;
    @Scheduled(fixedDelay = 30, timeUnit = TimeUnit.SECONDS)
    public void doScheduledTask(){
        try {
            FileUtils.cleanDirectory(new File("test"));
        } catch (IOException e) {
            log.info("Error while clean directory");
        }
        List<String> authors = gameAuthorService.getGameAuthors().stream().map((gameAuthor) -> gameAuthor.getId() +
                " " +
                gameAuthor.getBirthday() +
                " " +
                gameAuthor.getNickname()).toList();
        List<String> games = gameService.getGames().stream().map((game) -> game.getId() +
                " " +
                game.getName() +
                " " +
                game.getCreationDate()).toList();
        Path authorsFile = Paths.get("test/authors.txt");
        Path gamesFile = Paths.get("test/games.txt");
        try {
            Files.write(authorsFile, authors, StandardCharsets.UTF_8);
            Files.write(gamesFile, games, StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.info("Error while write files");
        }
    }
}
