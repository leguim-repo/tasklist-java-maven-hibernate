package com.bootcamp.seatcode.mike.tablas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "tareas")
public class Tarea {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private String estado;

    @Column(name = "responsable")
    private String responsable;

    @Column(name = "fecha")
    private java.sql.Date fecha;

    public Tarea() {
    }

    public Tarea(String titulo, String descripcion, String estado, String responsable, java.sql.Date fecha) throws ParseException {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.responsable = responsable;
        // https://www.baeldung.com/java-string-to-timestamp
        //this.fecha = Timestamp.valueOf("20/08/31");
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public java.sql.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Tareas{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", responsable='" + responsable + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
