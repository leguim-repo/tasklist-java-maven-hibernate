package com.bootcamp.seatcode.mike.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estados")
public class EstadoEntity {
    @Id
    //@Column(name = "id") // no es necesario decir el nombre de la columna si ya coincide con nombre de la variable
    private long id;

    //@Column(name = "nombre")
    private String nombre;

    //@Column(name = "descripcion")
    private String descripcion;

    public EstadoEntity() {

    }

    public EstadoEntity(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "estados{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }


}