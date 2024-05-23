package com.example.prac15;


import com.example.prac15.models.Game;
import com.example.prac15.requests.DeleteRequestBody;
import com.example.prac15.requests.GameRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller()
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameRepository gameRepository;

    @PostMapping("/create")
    public @ResponseBody Map<String, String> create(@RequestBody GameRequestBody game) {
        try {
            gameRepository.addGame(game);
        } catch (Exception e) {
            return Map.of("error", e.getMessage());
        }
        return Map.of("result", "success");
    }

    @DeleteMapping("/delete")
    public @ResponseBody String remove(@RequestBody DeleteRequestBody deleteRequestBody) {
        gameRepository.removeGameByName(deleteRequestBody.deleteValue());
        return "Success";
    }

    @GetMapping("/getAll")
    public @ResponseBody List<Game> getAllGames() {
        return gameRepository.getGames();
    }
}
