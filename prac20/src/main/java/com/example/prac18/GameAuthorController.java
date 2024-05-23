package com.example.prac18;


import com.example.prac18.domain.GameAuthorService;
import com.example.prac18.entities.GameAuthor;
import com.example.prac18.requests.DeleteRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller()
@RequestMapping("/gameAuthor")
public class GameAuthorController {
    @Autowired
    private GameAuthorService gameAuthorService;

    @PostMapping("/create")
    public @ResponseBody String create(@RequestBody GameAuthor game) {
        gameAuthorService.addGameAuthor(game);
        return "Success";
    }

    @DeleteMapping("/delete")
    public @ResponseBody String remove(@RequestBody DeleteRequestBody deleteRequestBody) {
        gameAuthorService.removeGameAuthorByName(deleteRequestBody.deleteValue());
        return "Success";
    }

    @GetMapping("/getAll")
    public @ResponseBody List<GameAuthor> getAllGames() {
        return gameAuthorService.getGameAuthors();
    }

    @GetMapping("/nickname/{nickname}")
    public @ResponseBody List<GameAuthor> getByNickname(@PathVariable("nickname") String nickname) {
        return gameAuthorService.getGameAuthorByNickname(nickname);
    }

    @GetMapping("/birthday/{birthday}")
    public @ResponseBody List<GameAuthor> getByBirthday(@PathVariable("birthday") String birthday) {
        return gameAuthorService.getGameAuthorByBirthday(birthday);
    }

    @GetMapping("/id/{id}")
    public @ResponseBody GameAuthor getByBirthday(@PathVariable("id") Long id) {
        return gameAuthorService.getGameAuthorById(id);
    }
}
