package com.bootcamp.seatcode.mike.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "login")
public class LoginEntity implements Serializable {
    @Id
    private long id;
    private String user;
    private String password;
    @Column(columnDefinition = "TINYINT(1))")
    private boolean active;

    public LoginEntity() {
    }

    public LoginEntity(long id, String user, String password, boolean active) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.active = active;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
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
