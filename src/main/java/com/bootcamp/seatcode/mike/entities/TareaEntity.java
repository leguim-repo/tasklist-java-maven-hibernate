package com.bootcamp.seatcode.mike.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "tareas")
public class TareaEntity implements Serializable {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
    private String estado;
    private String titulo;
    private String descripcion;
    private String responsable;
    private java.sql.Date fecha;

    public TareaEntity() {

    }

    public TareaEntity(String titulo, String descripcion, String estado, String responsable, java.sql.Date fecha)  {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.responsable = responsable;
        // https://www.baeldung.com/java-string-to-timestamp
        //this.fecha = Timestamp.valueOf("20/08/31");
        this.fecha = fecha;
    }

    public void clear() {
        this.id = 0;
        this.titulo = "titulo";
        this.descripcion = "descripcion";
        this.estado = "estado";
        this.responsable = "responsable";
        this.fecha = Date.valueOf("2050-08-31");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        return "Tarea{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", responsable='" + responsable + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
