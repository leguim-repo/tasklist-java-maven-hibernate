package com.bootcamp.seatcode.mike.tablas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login")
public class Login {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name= "user")
    private String user;

    @Column(name= "password")
    private String password;

    
    @Column(name = "active", columnDefinition = "TINYINT(1))")
    private boolean active;

    public Login() {
    }

    public Login(int id, String user, String password, boolean active) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.active = active;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                '}';
    }
}
