package com.uahnn.bookmakeeer.controller;

import com.uahnn.bookmakeeer.MasterOfMocks;
import com.uahnn.bookmakeeer.model.Game;
import com.uahnn.bookmakeeer.model.Team;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import javax.transaction.UserTransaction;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Robin on 09.01.16
 */
@Named
@RequestScoped
public class GameController implements Serializable {
    private static final long serialVersionUID = 1L;

    private String homeTeamId, awayTeamId;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Resource
    private UserTransaction ut;

    @Inject
    private Game game;

    private List<Team> teams;

    private List<Game> upcomingGames;

    private List<Game> pastGames;


    /**
     * Returns the game object
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Sets the game object
     * @param game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Returns a list with all teams
     * @return list with all teams
     */
    public List<Team> getTeams() {
        if(this.teams != null) {
            TypedQuery<Team> query = emf.createEntityManager().createNamedQuery(Team.FIND_ALL_ORD_BY_GROUP, Team.class);
            this.teams = query.getResultList();
        }
        return teams;
    }

    /**
     * Returns a list with all upcoming games
     * @return list with all upcoming games
     */
    public List<Game> getUpcomingGames() {
        /*
        if(this.upcomingGames != null) {
            TypedQuery<Game> query = emf.createEntityManager().createNamedQuery(Game.FIND_UPCOMING, Game.class);
            this.upcomingGames = query.getResultList();

        }
        return upcomingGames;
        */

        // LOL REMOVE
        MasterOfMocks mam = MasterOfMocks.getInstance();
        return mam.mockGames(10);
    }

    /**
     * Returns a list with all games that already started or are already over
     * @return list with all started/past games
     */
    public List<Game> getPastGames() {
        /*
        if(this.pastGames != null) {
            TypedQuery<Game> query = emf.createEntityManager().createNamedQuery(Game.FIND_STARTED, Game.class);
            this.pastGames = query.getResultList();

        }
        return pastGames;
        */

        // LOL REMOVE
        MasterOfMocks mam = MasterOfMocks.getInstance();
        return mam.mockGames(15);
    }

    /**
     * Returns the id of the home team
     * @return id of the home team
     */
    public String getHomeTeamId() {
        return homeTeamId;
    }

    /**
     * Sets the home team id
     * @param homeTeamId
     */
    public void setHomeTeamId(String homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    /**
     * Returns the id of the away team
     * @return id of the away team
     */
    public String getAwayTeamId() {
        return awayTeamId;
    }

    /**
     * Sets the away team id
     * @param awayTeamId
     */
    public void setAwayTeamId(String awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    /**
     * Persists a game in the DB
     * @return view to be forwarded to
     */
    public String persist() {
        EntityManager em = emf.createEntityManager();
        this.game.setHomeTeam(em.getReference(Team.class, homeTeamId));
        this.game.setAwayTeam(em.getReference(Team.class, awayTeamId));
        try {
            ut.begin();
           em.persist(game);
            ut.commit();
            return "/admin/game.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/fail.xhtml";
    }
}
