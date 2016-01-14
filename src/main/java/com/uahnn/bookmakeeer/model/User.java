package com.uahnn.bookmakeeer.model;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.logging.StreamHandler;

/**
 * Created by Robin on 07.01.16
 */
@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = User.FIND_BY_EMAIL, query = "select u from User u where lower(u.email) = lower(:email)"),
        @NamedQuery(name = User.FIND_ALL_ORD_BY_EMAIL, query = "select u from User u order by u.email"),
        @NamedQuery(name = User.FIND_ADMINS_ORD_BY_EMAIL, query = "select u from User u where u.admin = true order by u.email")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_BY_EMAIL = "User.FIND_BY_EMAIL";
    public static final String FIND_ALL_ORD_BY_EMAIL = "User.FIND_ALL_ORD_BY_EMAIL";
    public static final String FIND_ADMINS_ORD_BY_EMAIL = "User.FIND_ADMINS_ORD_BY_EMAIL";

    private int id;
    private Timestamp lastUpdate;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private int zip;
    private String city;
    private boolean isAdmin = false;
    private String locale;
    private BigDecimal balance;
    private List<UserBet> bets;

    protected User (){}

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "last_update")
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "zip")
    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "is_admin")
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Basic
    @Column(name = "locale")
    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Basic
    @Column(name = "balance")
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public List<UserBet> getBets() {
        return bets;
    }

    public void setBets(List<UserBet> bets) {
        this.bets = bets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (id != that.id) return false;
        if (zip != that.zip) return false;
        if (isAdmin != that.isAdmin) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password!= null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (locale != null ? !locale.equals(that.locale) : that.locale != null) return false;
        if (balance != null ? !balance.equals(that.balance) : that.balance != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + zip;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (isAdmin ? 1 : 0);
        result = 31 * result + (locale != null ? locale.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        return result;
    }
}
