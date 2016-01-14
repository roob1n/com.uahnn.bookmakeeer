package com.uahnn.bookmakeeer.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Robin on 07.01.16
 */
@Entity
@Table(name = "users_bets")
@IdClass(UserBetsPK.class)
@NamedQuery(name = UserBet.FIND_BY_USER_BET, query = "select c from UserBet c where c.betId = :betId and c.userId = :userId")
public class UserBet implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FIND_BY_USER_BET = "UserBet.FIND_BY_USER_BET";

    private int userId;
    private User user;
    private int betId;
    private Bet bet;
    private BigDecimal stake;

    protected UserBet() {}

    public UserBet(User user, Bet bet, BigDecimal stake) {
        this();
        this.userId = user.getId();
        this.user = user;
        this.betId = bet.getId();
        this.bet = bet;
        this.stake = stake;
    }

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinTable(name = "users")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Id
    @Column(name = "bet_id")
    public int getBetId() {
        return betId;
    }

    public void setBetId(int betId) {
        this.betId = betId;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinTable(name = "bets")
    private Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.betId = bet.getId();
        this.bet = bet;
    }

    @Basic
    @Column(name = "stake")
    public BigDecimal getStake() {
        return stake;
    }

    public void setStake(BigDecimal stake) {
        this.stake = stake;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBet userBet = (UserBet) o;

        if (userId != userBet.userId) return false;
        if (betId != userBet.betId) return false;
        if (stake != null ? !stake.equals(userBet.stake) : userBet.stake != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + betId;
        result = 31 * result + (stake != null ? stake.hashCode() : 0);
        return result;
    }
}
