package com.uahnn.bookmakeeer.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Robin on 07.01.16
 */
@Entity
@Table(name = "teams")
@NamedQueries({
        @NamedQuery(name = Team.FIND_ALL_ORD_BY_GROUP, query = "select t from Team t order by t.groupe"),
        @NamedQuery(name = Team.FIND_ALL_ORD_BY_UEFACOEF, query = "select t from Team t order by t.uefacoef")})
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL_ORD_BY_GROUP = "Team.FIND_ALL_ORD_BY_GROUP";
    public static final String FIND_ALL_ORD_BY_UEFACOEF = "Team.FIND_ALL_ORD_BY_UEFACOEF";

    private String id;
    private int uefacoef;
    private String groupe;

    protected Team(){}

    public Team(String id){
        this();
        this.id = id;
    }

    @Id
    @Column(name = "id", nullable = false, length = 3, unique = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "uefacoef")
    public int getUefacoef() {
        return uefacoef;
    }

    public void setUefacoef(int uefacoef) {
        this.uefacoef = uefacoef;
    }

    @Basic
    @Column(name = "groupe")
    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (uefacoef != team.uefacoef) return false;
        if (id != null ? !id.equals(team.id) : team.id != null) return false;
        if (groupe != null ? !groupe.equals(team.groupe) : team.groupe != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + uefacoef;
        result = 31 * result + (groupe != null ? groupe.hashCode() : 0);
        return result;
    }
}
