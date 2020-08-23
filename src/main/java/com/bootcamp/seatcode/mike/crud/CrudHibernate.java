package com.bootcamp.seatcode.mike.crud;

import com.bootcamp.seatcode.mike.tablas.Estado;
import com.bootcamp.seatcode.mike.tablas.Tarea;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.util.List;

public class CrudHibernate {
    private static EntityManager em;
    private static EntityManagerFactory emf;

    public CrudHibernate() {
        this.emf = Persistence.createEntityManagerFactory("TaskListPersistence");
        this.em = this.emf.createEntityManager();
    }

    public void createTarea(String titulo, String descripcion, String estado, String responsable, java.sql.Date fecha) throws ParseException {
        Tarea nuevaTarea = new Tarea(titulo, descripcion, estado, responsable, fecha);
        this.em.getTransaction().begin();
        this.em.persist(nuevaTarea);
        this.em.getTransaction().commit();
    }

    public void createTarea(Tarea nuevaTarea) {
        this.em.getTransaction().begin();
        this.em.persist(nuevaTarea);
        this.em.getTransaction().commit();
    }

    public List<Tarea> readTareas() {
        List<Tarea> tareas = (List<Tarea>) em.createQuery("FROM Tarea").getResultList();
        return tareas;
    }

    public void updateTarea(Tarea tarea) {
        this.em.getTransaction().begin();
        this.em.merge(tarea);
        this.em.getTransaction().commit();
    }

    // como manu no especifica como borrarlo usare el id
    public void deleteTarea(int id) {
        Tarea tareaABorrar =  em.find(Tarea.class, id); //buscas el objeto por el primary key
        this.em.getTransaction().begin();
        this.em.remove(tareaABorrar);
        this.em.getTransaction().commit();
    }

    public List<Estado> readEstados() {
        List<Estado> estados = (List<Estado>) em.createQuery("FROM Estado").getResultList();
        return estados;
    }


    //lo suyo seria hacerlo con @Query...supongo
    public List<Tarea> buscarNombre(String target) {
        List<Tarea> tareas = (List<Tarea>) em.createQuery("FROM Tarea WHERE ( responsable LIKE '%"+target+"%')").getResultList();
        return tareas;
    }

    public List<Tarea> buscarDescripcion(String target) {
        List<Tarea> tareas = (List<Tarea>) em.createQuery("FROM Tarea WHERE ( descripcion LIKE '%"+target+"%')").getResultList();
        return tareas;
    }

    public void close() {
        this.emf.close();
        this.em.close();
    }


}

