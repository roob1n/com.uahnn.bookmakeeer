package com.uahnn.bookmakeeer.controller;

import com.uahnn.bookmakeeer.model.User;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import java.io.Serializable;

/**
 * Created by Robin on 07.01.16
 */

@Named
@RequestScoped
public class RegisterController implements Serializable {
    private static final long serialVersionUID = 1L;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Resource
    private UserTransaction ut;

    @Inject
    private User user;

    /**
     * Returns the user
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user
     * @param user new user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Persists a new user in the database
     * @return
     */
    public String persist() {
        try {
            ut.begin();
            emf.createEntityManager().persist(user);
            ut.commit();
            return "/success.xhtml";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "/register.xhtml";
    }
}
