package com.example.prac15;

import com.example.prac15.models.Game;
import com.example.prac15.models.GameAuthor;
import com.example.prac15.requests.GameRequestBody;
import jakarta.annotation.PostConstruct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class GameRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private GameAuthorRepository gameAuthorRepository;

    private Session session;

    @PostConstruct
    void init() {
        session = sessionFactory.openSession();
    }

    public void addGame(GameRequestBody game) {
        Game newGame = new Game();
        newGame.setName(game.name());
        newGame.setCreationDate(game.creationDate());
        GameAuthor gameAuthor = gameAuthorRepository.getGameAuthorById(game.authorId());
        if (gameAuthor == null) {
            throw new RuntimeException("Game author not exist");
        }
        newGame.setGameAuthor(gameAuthor);
        session.beginTransaction();
        session.persist(newGame);
        session.flush();
        session.getTransaction().commit();
    }

    public void removeGameByName(String name) {
        String hql = "delete Game where name=:name";
        session.createQuery(hql, Game.class).setParameter("name", name);
    }

    public List<Game> getGames() {
        return session.createQuery("from Game", Game.class).getResultList();
    }
}
