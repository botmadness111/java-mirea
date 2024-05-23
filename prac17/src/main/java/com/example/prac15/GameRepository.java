package com.example.prac15;

import com.example.prac15.models.Game;
import com.example.prac15.models.GameAuthor;
import com.example.prac15.requests.GameRequestBody;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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

    public List<Game> getGameByAttr(String attrName, Object attrValue) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Game> gameCriteriaQuery = builder.createQuery(Game.class);
        Root<Game> root = gameCriteriaQuery.from(Game.class);
        gameCriteriaQuery.select(root).where(builder.equal(root.get(attrName), attrValue));
        Query<Game> query = session.createQuery(gameCriteriaQuery);
        return query.getResultList();
    }

    public void removeGameByName(String name) {
        String hql = "delete Game where name=:name";
        session.createQuery(hql, Game.class).setParameter("name", name);
    }

    public List<Game> getGames() {
        return session.createQuery("from Game", Game.class).getResultList();
    }
}
