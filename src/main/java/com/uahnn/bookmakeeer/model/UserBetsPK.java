package com.uahnn.bookmakeeer.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Robin on 07.01.16
 */
public class UserBetsPK implements Serializable {
    private int userId;
    private int betId;

    @Column(name = "user_id")
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "bet_id")
    @Id
    public int getBetId() {
        return betId;
    }

    public void setBetId(int betId) {
        this.betId = betId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBetsPK that = (UserBetsPK) o;

        if (userId != that.userId) return false;
        if (betId != that.betId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + betId;
        return result;
    }
}
