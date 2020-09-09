package com.bootcamp.seatcode.mike.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "login")
public class LoginEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String user;
    private String password;
    @Column(columnDefinition = "TINYINT(1))")
    private boolean active;

    /*
    Test A
    One To Many
    usuarios =---< login ( fk_usuario_id )

    @OneToOne
    @JoinColumn(name="fk_usuario_id")
    private UsuarioEntity usuario;
    */

    /*
    Test B
    Many To One
    usuario ( fk_login_id ) >---= login
    @OneToOne(mappedBy="login")
    private UsuarioEntity usuario;
     */




    public LoginEntity() {}

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
