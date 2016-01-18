package com.uahnn.bookmakeeer.dao;

import com.uahnn.bookmakeeer.model.Game;
import com.uahnn.bookmakeeer.model.User;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Robin on 16.01.16
 */
public class GameDAO {
    private static GameDAO instance;
    @PersistenceUnit
    private EntityManager em;

    public static final String PERSISTENCE_UNIT = "bookmakeeer-jpa";

    public static GameDAO getInstance() {
        if (instance == null) {
            instance = new GameDAO();
            instance.em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
        }
        return instance;
    }

    public List<Game> getAllUpcoming() {
        Query query = em.createNamedQuery(Game.FIND_UPCOMING, Game.class);
        query.setParameter("starttime", new Date());
        List<Game> games = query.getResultList();
        return games;
    }

    public List<Game> getAllStarted() {
        Query query = em.createNamedQuery(Game.FIND_STARTED, Game.class);
        query.setParameter("starttime", new Date());
        List<Game> games = query.getResultList();
        return games;
    }

    public List<Game> getAllEnded() {
        Query query = em.createNamedQuery(Game.FIND_ENDED, Game.class);
        List<Game> games = query.getResultList();
        return games;
    }

    public Game getGameById(int id){
        return em.find(Game.class, id);
    }

    public void createGame(Game newGame){
        em.getTransaction().begin();
        em.persist(newGame);
        em.getTransaction().commit();
    }

    public void updateGame(Game updateGame){
        Game game = em.find(Game.class, updateGame.getId());
        em.getTransaction().begin();
        game.setHomeTeam(updateGame.getHomeTeam());
        game.setAwayTeam(updateGame.getAwayTeam());
        game.setStartTime(updateGame.getStartTime());
        em.getTransaction().commit();
    }

    public void updateScores(Game updateScoreGame){
        Game game = em.find(Game.class, updateScoreGame.getId());
        em.getTransaction().begin();
        game.setHomeTeamScore(updateScoreGame.getHomeTeamScore());
        game.setAwayTeamScore(updateScoreGame.getAwayTeamScore());
        em.getTransaction().commit();
    }

    public void deleteGame(Game deleteGame) {
        Game game = em.find(Game.class, deleteGame.getId());
        em.getTransaction().begin();
        em.remove(game);
        em.getTransaction().commit();
    }

    @PreDestroy
    public void destruct()
    {
        em.close();
    }
}
