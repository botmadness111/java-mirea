package com.example.prac15;

import com.example.prac15.models.GameAuthor;
import jakarta.annotation.PostConstruct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


@Repository
public class GameAuthorRepository {
    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    @PostConstruct
    void init() {
        session = sessionFactory.openSession();
    }

    public void addGameAuthor(GameAuthor gameAuthor) {
        session.beginTransaction();
        session.persist(gameAuthor);
        session.flush();
        session.getTransaction().commit();
    }

    public void removeGameAuthorByName(String name) {
        String hql = "delete GameAuthor where nickname=:name";
        session.createQuery(hql, GameAuthor.class).setParameter("name", name);
    }

    public GameAuthor getGameAuthorById(Long id) {
        return session.createQuery("from GameAuthor where id="+id.toString(), GameAuthor.class).getSingleResult();
    }

    public List<GameAuthor> getGameAuthors() {
        return session.createQuery("from GameAuthor", GameAuthor.class).getResultList();
    }
}
