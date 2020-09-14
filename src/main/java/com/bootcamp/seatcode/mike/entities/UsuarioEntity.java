package com.bootcamp.seatcode.mike.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private String nombre;
    private String apellidos;
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<TareaEntity> tasks;

    @OneToOne()
    @JoinColumn(name = "fk_datos_login", nullable = false)
    private LoginEntity datosLogin;


    //usuarios es el punto de referencia ( padre o propietario de la relacion )
    /*
    Test A
    One To Many
    usuarios =---< login ( fk_usuario_id )

    @OneToOne(mappedBy="usuario")
    private LoginEntity login;
    */
    
    /*
    Test B
    Many To One
    usuario ( fk_login_id ) >---= login

    @OneToOne
    @JoinColumn(name="fk_login_id")
    private LoginEntity login;
     */


    //todo mapear la relacion OneToOne con la tabla Login

    public UsuarioEntity() {
    }


    public UsuarioEntity(String nombre, String apellidos, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
    }

    public UsuarioEntity(String nombre, String apellidos, String email, Set<TareaEntity> tasks) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.tasks = tasks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Set<TareaEntity> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TareaEntity> tasks) {
        this.tasks = tasks;
    }

    public LoginEntity getDatosLogin() {
        return datosLogin;
    }

    public void setDatosLogin(LoginEntity datosLogin) {
        this.datosLogin = datosLogin;
    }
}
