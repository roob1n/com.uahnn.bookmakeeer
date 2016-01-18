package com.uahnn.bookmakeeer.dao;

import com.uahnn.bookmakeeer.model.Bet;
import com.uahnn.bookmakeeer.model.Game;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by Robin on 18.01.16
 */
public class BetDAO {
    private static BetDAO instance;

    @PersistenceUnit
    private EntityManager em;

    public static final String PERSISTENCE_UNIT = "bookmakeeer-jpa";

    public static BetDAO getInstance() {
        if (instance == null) {
            instance = new BetDAO();
            instance.em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
        }
        return instance;
    }

    public List<Bet> getBetsByMatchId(int id) {
        Query query = em.createNamedQuery(Bet.FIND_BY_GAME_ID, Game.class);
        query.setParameter("gameid", id);
        List<Bet> bets = query.getResultList();
        return bets;
    }

    public Bet getBetById(int id){
        return em.find(Bet.class, id);
    }

    public void createBet(Bet newBet){
        em.getTransaction().begin();
        em.persist(newBet);
        em.getTransaction().commit();
    }

    public void updateBet(Bet updateBet){
        Bet bet = em.find(Bet.class, updateBet.getId());
        em.getTransaction().begin();
        bet.setOdds(updateBet.getOdds());
        bet.setOccurred(updateBet.getOccurred());
        em.getTransaction().commit();
    }

    public void deleteBet(Bet deleteBet) {
        Bet bet = em.find(Bet.class, deleteBet.getId());
        em.getTransaction().begin();
        em.remove(bet);
        em.getTransaction().commit();
    }

    @PreDestroy
    public void destruct()
    {
        em.close();
    }
}
