package com.bootcamp.seatcode.mike.crud;

import com.bootcamp.seatcode.mike.tablas.Tarea;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Crud {
    private static EntityManager em;
    private static EntityManagerFactory emf;

    public Crud() {
        this.emf = Persistence.createEntityManagerFactory("TaskListPersistence");
        this.em = this.emf.createEntityManager();
    }

    public void createTarea(String titulo, String descripcion, String estado, String responsable, String fecha) {
        Tarea nuevaTarea = new Tarea();
        em.getTransaction().begin();
        em.persist(nuevaTarea);
        em.getTransaction().commit();
    }
    public void readTareas() {
        List<Tarea> tareas = (List<Tarea>) em.createQuery("FROM Tarea").getResultList();
    }

    public void update() {

    }

    // como manu no especifica como borrarlo usare el id
    public void deleteTarea(int id) {
        Tarea tareaABorrar =  em.find(Tarea.class, id); //buscas el objeto por el primary key

    }

    public void close() {
        this.emf.close();
        this.em.close();
    }
}

