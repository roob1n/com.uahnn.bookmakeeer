package com.uahnn.bookmakeeer.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Robin on 07.01.16
 */
@Entity
@Table(name = "bets")
@NamedQuery(name = Bet.FIND_BY_GAME_ID, query = "select b from Bet b where b.gameId = :gameid")
public class Bet {

    public static final String FIND_BY_GAME_ID = "Bet.FIND_BY_GAME_ID";

    private int id;
    private int gameId;
    private BetType type;
    private BigDecimal odds;
    private Boolean occurred;
    private List<UserBet> userBets;

    protected Bet (){}

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "game_id")
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public BetType getType() {
        return type;
    }

    public void setType(BetType type) {
        this.type = type;
    }

    @Basic
    @Column(name = "odds")
    public BigDecimal getOdds() {
        return odds;
    }

    public void setOdds(BigDecimal odds) {
        this.odds = odds;
    }

    @Basic
    @Column(name = "occurred")
    public Boolean getOccurred() {
        return occurred;
    }

    public void setOccurred(Boolean occurred) {
        this.occurred = occurred;
    }

    @OneToMany(mappedBy = "bet", fetch = FetchType.LAZY)
    public List<UserBet> getUserBets() {
        return userBets;
    }

    public void setUserBets(List<UserBet> userBets) {
        this.userBets = userBets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bet bet = (Bet) o;

        if (gameId != bet.gameId) return false;
        if (id != bet.id) return false;
        if (type != null ? !type.equals(bet.type) : bet.type != null) return false;
        if (odds != null ? !odds.equals(bet.odds) : bet.odds != null) return false;
        if (occurred != null ? !occurred.equals(bet.occurred) : bet.occurred != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gameId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (odds != null ? odds.hashCode() : 0);
        result = 31 * result + (occurred != null ? occurred.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
