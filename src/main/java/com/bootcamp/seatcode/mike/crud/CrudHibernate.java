package com.bootcamp.seatcode.mike.crud;

import com.bootcamp.seatcode.mike.entities.EstadoEntity;
import com.bootcamp.seatcode.mike.entities.TareaEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


public class CrudHibernate {
    private static EntityManager em;
    private static EntityManagerFactory emf;

    public CrudHibernate() {
        this.emf = Persistence.createEntityManagerFactory("TaskListPersistence");
        this.em = this.emf.createEntityManager();
    }

    public void createTarea(String titulo, String descripcion, String estado, String responsable, java.sql.Date fecha) {
        TareaEntity nuevaTarea = new TareaEntity(titulo, descripcion, estado, responsable, fecha);
        this.em.getTransaction().begin();
        this.em.persist(nuevaTarea);
        this.em.getTransaction().commit();
    }

    public void createTarea(TareaEntity nuevaTarea) {
        this.em.getTransaction().begin();
        this.em.persist(nuevaTarea);
        this.em.getTransaction().commit();
    }

    public List<TareaEntity> getTareas() {
        CriteriaQuery<TareaEntity> criteriaQuery = this.em.getCriteriaBuilder().createQuery(TareaEntity.class);
        criteriaQuery.select(criteriaQuery.from(TareaEntity.class));
        List<TareaEntity> tareas = this.em.createQuery(criteriaQuery).getResultList();

        return tareas;
    }

    public void updateTarea(TareaEntity tarea) {
        this.em.getTransaction().begin();
        this.em.merge(tarea);
        this.em.getTransaction().commit();
    }

    // como manu no especifica como borrarlo usare el id
    public void deleteTarea(Long id) {
        TareaEntity tareaABorrar = this.em.find(TareaEntity.class, id); //buscas el objeto por el primary key
        this.em.getTransaction().begin();
        this.em.remove(tareaABorrar);
        this.em.getTransaction().commit();
    }

    public List<EstadoEntity> getEstados() {
        CriteriaQuery<EstadoEntity> criteriaQuery = this.em.getCriteriaBuilder().createQuery(EstadoEntity.class);
        criteriaQuery.select(criteriaQuery.from(EstadoEntity.class));
        List<EstadoEntity> estados =this.em.createQuery(criteriaQuery).getResultList();
        return estados;
    }

    public List<TareaEntity> findForResponsable(String criterio) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<TareaEntity> q = cb.createQuery(TareaEntity.class);
        Root<TareaEntity> c = q.from(TareaEntity.class);
        Predicate predicate = cb.like(c.<String>get("responsable"),"%"+criterio+"%");
        q.where(predicate);
        List<TareaEntity> tareas = this.em.createQuery(q).getResultList();
        return tareas;
    }

    public List<TareaEntity> findForDescripcion(String criterio) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<TareaEntity> q = cb.createQuery(TareaEntity.class);
        Root<TareaEntity> c = q.from(TareaEntity.class);
        Predicate predicate = cb.like(c.<String>get("descripcion"),"%"+criterio+"%");
        q.where(predicate);
        List<TareaEntity> tareas = this.em.createQuery(q).getResultList();
        return tareas;
    }

    public void close() {
        this.emf.close();
        this.em.close();
    }


}

