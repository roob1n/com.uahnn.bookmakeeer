package com.uahnn.bookmakeeer.controller;

import com.uahnn.bookmakeeer.model.User;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import java.io.Serializable;

/**
 * Created by Robin on 10.01.16
 */

@Named
@SessionScoped
public class LoginController implements Serializable {
    private static final long serialVersionUID = 1L;

    @PersistenceUnit
    EntityManagerFactory emf;

    private String email;
    private String password;

    private User user;

    public void login() {
        TypedQuery<User> query = emf.createEntityManager().createNamedQuery(User.FIND_BY_EMAIL, User.class);
        query.setParameter("email", getEmail());
        User loginUser = query.getSingleResult();
        if (getPassword() == loginUser.getPassword()) {
        }
        this.user = loginUser;
    }

    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}


