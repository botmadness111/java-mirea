package com.example.prac18;



import com.example.prac18.domain.EmailService;
import com.example.prac18.domain.GameService;
import com.example.prac18.entities.Game;
import com.example.prac18.requests.DeleteRequestBody;
import com.example.prac18.requests.GameRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller()
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    private final EmailService emailService;


    @PostMapping("/create")
    public @ResponseBody Map<String, String> create(@RequestBody GameRequestBody game) {
        try {
            gameService.addGame(game);
        } catch (Exception e) {
            return Map.of("error", e.getMessage());
        }
        return Map.of("result", "success");
    }

    @DeleteMapping("/delete")
    public @ResponseBody String remove(@RequestBody DeleteRequestBody deleteRequestBody) {
        gameService.removeGameByName(deleteRequestBody.deleteValue());
        return "Success";
    }

    @GetMapping("/getAll")
    public @ResponseBody List<Game> getAllGames() {
        return gameService.getGames();
    }

    @GetMapping("/name/{name}")
    public @ResponseBody List<Game> getGameByName(@PathVariable("name") String name) {
        return gameService.getGameByName(name);
    }

    @GetMapping("/creationDate/{creationDate}")
    public @ResponseBody List<Game> getGameByCreationDate(@PathVariable("creationDate") String creationDate) {
        return gameService.getGameByCreationDate(creationDate);
    }

    @GetMapping("/id/{id}")
    public @ResponseBody Game getGameByCreationDate(@PathVariable("id") Long id) {
        return gameService.getGameById(id);
    }

    @GetMapping("send/{user-email}")
    public ResponseEntity <String> sendMessage(@PathVariable("user-email") String userEmail) {
        emailService.sendMessage(userEmail, "hello bro");
        return ResponseEntity.ok("Success");
    }
}
