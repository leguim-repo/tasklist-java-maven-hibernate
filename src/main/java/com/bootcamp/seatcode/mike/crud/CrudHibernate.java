package com.bootcamp.seatcode.mike.crud;

import com.bootcamp.seatcode.mike.entities.EstadoEntity;
import com.bootcamp.seatcode.mike.entities.Tarea;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    public void deleteTarea(Long id) {
        Tarea tareaABorrar =  em.find(Tarea.class, id); //buscas el objeto por el primary key
        this.em.getTransaction().begin();
        this.em.remove(tareaABorrar);
        this.em.getTransaction().commit();
    }

    public List<EstadoEntity> findEstados() {
        //List<EstadoEntity> estados = (List<EstadoEntity>) em.createQuery("FROM EstadoEntity").getResultList();
        CriteriaQuery<EstadoEntity> criteriaQuery = this.em.getCriteriaBuilder().createQuery(EstadoEntity.class);
        criteriaQuery.select(criteriaQuery.from(EstadoEntity.class));
        List<EstadoEntity> estados =this.em.createQuery(criteriaQuery).getResultList();
        return estados;
    }

    public List<Tarea> buscarPorResponsable(String target) {
        List<Tarea> tareas = (List<Tarea>) em.createQuery("FROM Tarea WHERE ( responsable LIKE '%"+target+"%')").getResultList();

        return tareas;
    }

    public List<Tarea> buscarDescripcion(String criterio) {
        //List<Tarea> tareas = (List<Tarea>) em.createQuery("FROM Tarea WHERE ( descripcion LIKE '%"+target+"%')").getResultList();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tarea> q = cb.createQuery(Tarea.class);
        Root<Tarea> c = q.from(Tarea.class);
        Predicate predicate = cb.like(c.<String>get("descripcion"),criterio);
        q.where(predicate);

        List<Tarea> tareas = em.createQuery(q).getResultList();
        return tareas;
    }

    public void close() {
        this.emf.close();
        this.em.close();
    }


}

