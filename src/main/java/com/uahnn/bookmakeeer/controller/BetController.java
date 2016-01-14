package com.uahnn.bookmakeeer.controller;

import com.uahnn.bookmakeeer.model.Game;
import com.uahnn.bookmakeeer.model.Team;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Robin on 09.01.16
 */
@Named
@RequestScoped
public class BetController implements Serializable {
    private static final long serialVersionUID = 1L;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Resource
    private UserTransaction ut;

    /**
     * Creates the view to make a new bet on a game
     * @param gameId
     * @return the view to be forwarded to
     */
    public String makeBet(String gameId){
        // TODO is user logged in?

        // TODO create bet view

        return null;
    }

    /**
     * Persists a game in the DB
     * @return view to be forwarded to
     */
    public String persist() {
        EntityManager em = emf.createEntityManager();
        //TODO

        return null;
    }
}
