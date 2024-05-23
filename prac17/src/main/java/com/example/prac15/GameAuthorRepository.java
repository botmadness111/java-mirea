package com.example.prac15;

import com.example.prac15.models.Game;
import com.example.prac15.models.GameAuthor;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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

    public List<GameAuthor> getGameAuthorByAttr(String attrName, Object attrValue) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<GameAuthor> gameCriteriaQuery = builder.createQuery(GameAuthor.class);
        Root<GameAuthor> root = gameCriteriaQuery.from(GameAuthor.class);
        gameCriteriaQuery.select(root).where(builder.equal(root.get(attrName), attrValue));
        Query<GameAuthor> query = session.createQuery(gameCriteriaQuery);
        return query.getResultList();
    }

    public GameAuthor getGameAuthorById(Long id) {
        return session.createQuery("from GameAuthor where id="+id.toString(), GameAuthor.class).getSingleResult();
    }

    public List<GameAuthor> getGameAuthors() {
        return session.createQuery("from GameAuthor", GameAuthor.class).getResultList();
    }
}
