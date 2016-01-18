package com.uahnn.bookmakeeer.controller;

import com.uahnn.bookmakeeer.MasterOfMocks;
import com.uahnn.bookmakeeer.dao.GameDAO;
import com.uahnn.bookmakeeer.dao.TeamDAO;
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

    @Inject
    private Game game;

    private String homeTeamId;

    private String awayTeamId;

    private List<Team> teams;

    private List<Game> upcomingGames;

    private List<Game> startedGames;

    private List<Game> endedGames;

    @PostConstruct
    public void init() {
        teams = TeamDAO.getInstance().getAllTeams();

        upcomingGames = GameDAO.getInstance().getAllUpcoming();

        startedGames = GameDAO.getInstance().getAllStarted();

        endedGames = GameDAO.getInstance().getAllEnded();
    }

    public String createGame() {
        TeamDAO teamDaoIns = TeamDAO.getInstance();
        game.setHomeTeam(teamDaoIns.getTeamById(homeTeamId));
        game.setAwayTeam(teamDaoIns.getTeamById(awayTeamId));
        GameDAO.getInstance().createGame(this.game);
        return "games.xhtml";
    }

    public String updateGame() {
        GameDAO.getInstance().updateGame(this.game);
        return "games.xhtml";
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Game> getUpcomingGames() {
        return upcomingGames;
    }

    public void setUpcomingGames(List<Game> upcomingGames) {
        this.upcomingGames = upcomingGames;
    }

    public List<Game> getStartedGames() {
        return startedGames;
    }

    public void setStartedGames(List<Game> startedGames) {
        this.startedGames = startedGames;
    }

    public List<Game> getEndedGames() {
        return endedGames;
    }

    public void setEndedGames(List<Game> endedGames) {
        this.endedGames = endedGames;
    }

    public String getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(String homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public String getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(String awayTeamId) {
        this.awayTeamId = awayTeamId;
    }
}
