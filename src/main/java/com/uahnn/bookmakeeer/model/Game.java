package com.uahnn.bookmakeeer.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Robin on 07.01.16
 */
@Entity
@Table(name = "games")
@NamedQueries({
        @NamedQuery(name = Game.FIND_STARTED, query = "select g from Game g where g.startTime <= :starttime and g.terminated = false order by g.startTime"),
        @NamedQuery(name = Game.FIND_ENDED, query = "select g from Game g where g.terminated = true order by g.startTime"),
        @NamedQuery(name = Game.FIND_UPCOMING, query = "select g from Game g where g.startTime > :starttime order by g.startTime"),
        @NamedQuery(name = Game.FIND_BY_ID, query = "select g from Game g where g.id = :id")})
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FIND_STARTED = "Game.FIND_STARTED";
    public static final String FIND_ENDED = "Game.FIND_ENDED";
    public static final String FIND_UPCOMING = "Game.FIND_UPCOMING";
    public static final String FIND_BY_ID = "Game.FIND_BY_ID";


    private int id;
    private Date startTime;
    private Team homeTeam;
    private Team awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private boolean isTerminated;

    public Game(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "starttime")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "home_team", nullable = false)
    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "away_team", nullable = false)
    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Basic
    @Column(name = "hometeamscore")
    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    @Basic
    @Column(name = "awayteamscore")
    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    @Basic
    @Column(name = "is_terminated")
    public boolean isTerminated() {
        return isTerminated;
    }

    public void setTerminated(boolean terminated) {
        isTerminated = terminated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (id != game.id) return false;
        if (startTime != null ? !startTime.equals(game.startTime) : game.startTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        return result;
    }
}
