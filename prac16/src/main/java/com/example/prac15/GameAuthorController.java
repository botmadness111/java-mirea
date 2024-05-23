package com.example.prac15;


import com.example.prac15.models.GameAuthor;
import com.example.prac15.requests.DeleteRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller()
@RequestMapping("/gameAuthor")
public class GameAuthorController {
    @Autowired
    private GameAuthorRepository gameAuthorRepository;

    @PostMapping("/create")
    public @ResponseBody String create(@RequestBody GameAuthor game) {
        gameAuthorRepository.addGameAuthor(game);
        return "Success";
    }

    @DeleteMapping("/delete")
    public @ResponseBody String remove(@RequestBody DeleteRequestBody deleteRequestBody) {
        gameAuthorRepository.removeGameAuthorByName(deleteRequestBody.deleteValue());
        return "Success";
    }

    @GetMapping("/getAll")
    public @ResponseBody List<GameAuthor> getAllGames() {
        return gameAuthorRepository.getGameAuthors();
    }
}
