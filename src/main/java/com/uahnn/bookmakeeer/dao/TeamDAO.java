package com.uahnn.bookmakeeer.dao;

import com.uahnn.bookmakeeer.model.Game;
import com.uahnn.bookmakeeer.model.Team;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by Robin on 16.01.16
 */
public class TeamDAO {
    private static TeamDAO instance;
    private EntityManager em;
    public static final String PERSISTENCE_UNIT = "bookmakeeer-jpa";

    public static TeamDAO getInstance() {
        if (instance == null) {
            instance = new TeamDAO();
            instance.em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
        }
        return instance;
    }

    public List<Team> getAllTeams() {
        Query query = em.createNamedQuery(Team.FIND_ALL_ORD_BY_GROUP, Team.class);
        List<Team> teams = query.getResultList();
        return teams;
    }

    public Team getTeamById(String id){
        return em.find(Team.class, id);
    }

    public void createTeam(Team newTeam){
        em.getTransaction().begin();
        em.persist(newTeam);
        em.getTransaction().commit();
    }

    public void updateTeam(Team updateTeam){
        Team team = em.find(Team.class, updateTeam.getId());
        em.getTransaction().begin();
        team.setGroupe(updateTeam.getGroupe());
        team.setUefacoef(updateTeam.getUefacoef());
        em.getTransaction().commit();
    }

    public void deleteTeam(Team deleteTeam) {
        Team team = em.find(Team.class, deleteTeam.getId());
        em.getTransaction().begin();
        em.remove(team);
        em.getTransaction().commit();
    }

    @PreDestroy
    public void destruct()
    {
        em.close();
    }
}
