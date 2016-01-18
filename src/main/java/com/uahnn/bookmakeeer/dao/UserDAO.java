package com.uahnn.bookmakeeer.dao;

import com.uahnn.bookmakeeer.model.User;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Robin on 16.01.16
 */
public class UserDAO {
    private static UserDAO instance;
    private EntityManager em;
    public static final String PERSISTENCE_UNIT = "bookmakeeer-jpa";

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
            instance.em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
        }
        return instance;
    }

    public List<User> getAllUsers() {
        Query query = em.createNamedQuery(User.FIND_ALL_ORD_BY_EMAIL,User.class);
        List<User> users = query.getResultList();
        return users;
    }

    public User getUserByEmail(String email) {
        Query query = em.createNamedQuery(User.FIND_BY_EMAIL,User.class);
        query.setParameter("email", email);
        List<User> users = query.getResultList();
        User user = null;
        if(users.size() > 0) {
            users.get(0);
        }
        return user;
    }

    public void createUser(User newUser){
        em.getTransaction().begin();
        em.persist(newUser);
        em.getTransaction().commit();
    }

    public void updateUser(User updateUser){
        User user = em.find(User.class, updateUser.getId());
        em.getTransaction().begin();
        user.setFirstname(updateUser.getFirstname());
        user.setLastname(updateUser.getLastname());
        user.setEmail(updateUser.getEmail());
        user.setAddress(updateUser.getAddress());
        user.setZip(updateUser.getZip());
        user.setCity(updateUser.getCity());
        user.setPassword(updateUser.getPassword());
        em.getTransaction().commit();
    }

    public void updateBalance(User updateUser, BigDecimal newBalance){
        User user = em.find(User.class, updateUser.getId());
        em.getTransaction().begin();
        user.setBalance(newBalance);
        em.getTransaction().commit();
    }

    @PreDestroy
    public void destruct()
    {
        em.close();
    }
}
