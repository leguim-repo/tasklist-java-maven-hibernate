package com.bootcamp.seatcode.mike.crud;

import com.bootcamp.seatcode.mike.entities.EstadoEntity;
import com.bootcamp.seatcode.mike.entities.LoginEntity;
import com.bootcamp.seatcode.mike.entities.TareaEntity;
import com.bootcamp.seatcode.mike.entities.UsuarioEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.List;


public class CrudHibernate {
    private static SessionFactory dbConnection;

    public CrudHibernate() {
        // Configuracion de Full Hibernate
        try {
            Configuration config = new Configuration();
            //Registro de entidades
            config.addAnnotatedClass(EstadoEntity.class);
            config.addAnnotatedClass(TareaEntity.class);
            config.addAnnotatedClass(LoginEntity.class);
            config.addAnnotatedClass(UsuarioEntity.class);
            config.configure();
            dbConnection = config.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void createTarea(TareaEntity nuevaTarea) {
        //TODO pasar el try para arriba -> throws Throwable
        Session session = this.dbConnection.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(nuevaTarea);
            transaction.commit();
        }catch (Throwable ex) {
            if (transaction!=null) transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }


    public List<TareaEntity> getTareas() {
        //TODO pasar el try para arriba -> throws Throwable
        List<TareaEntity> tareas = null;
        Session session = this.dbConnection.openSession();
        try {
            CriteriaQuery<TareaEntity> cq = session.getCriteriaBuilder().createQuery(TareaEntity.class);
            cq.select(cq.from(TareaEntity.class));
            tareas = session.createQuery(cq).getResultList();
        }catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return tareas;
    }

    public void updateTarea(TareaEntity tarea) {
        //TODO pasar el try para arriba -> throws Throwable
        Session session = this.dbConnection.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(tarea);
            transaction.commit();
        }catch (Throwable ex) {
            if (transaction!=null) transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    // como manu no especifica como borrarlo usare el id
    public void deleteTarea(Long id) {
        //TODO pasar el try para arriba -> throws Throwable
        Session session = this.dbConnection.openSession();
        Transaction transaction = null;
        try {
            TareaEntity tareaABorrar = session.load(TareaEntity.class, id);
            transaction = session.beginTransaction();
            session.delete(tareaABorrar);
            transaction.commit();
        }catch (Throwable ex) {
            if (transaction!=null) transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }

    }

    public List<EstadoEntity> getEstados() {
        //TODO pasar el try para arriba -> throws Throwable
        List<EstadoEntity> estados = null;
        Session session = this.dbConnection.openSession();
        try {
            CriteriaQuery<EstadoEntity> cq = session.getCriteriaBuilder().createQuery(EstadoEntity.class);
            cq.select(cq.from(EstadoEntity.class));
            estados = session.createQuery(cq).getResultList();
        }catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return estados;
    }

    public List<TareaEntity> findForResponsable(String criterio) {
        //TODO pasar el try para arriba -> throws Throwable
        List<TareaEntity> tareas = null;
        Session session = this.dbConnection.openSession();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<TareaEntity> q = cb.createQuery(TareaEntity.class);
            Root<TareaEntity> c = q.from(TareaEntity.class);
            Predicate predicate = cb.like(c.<String>get("responsable"),"%"+criterio+"%");
            q.where(predicate);
            Query<TareaEntity> query = session.createQuery(q);
            tareas = query.getResultList();
        }catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return tareas;
    }

    public List<TareaEntity> findForDescripcion(String criterio) {
        //TODO pasar el try para arriba -> throws Throwable
        List<TareaEntity> tareas = null;
        Session session = this.dbConnection.openSession();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<TareaEntity> q = cb.createQuery(TareaEntity.class);
            Root<TareaEntity> c = q.from(TareaEntity.class);
            Predicate predicate = cb.like(c.<String>get("descripcion"),"%"+criterio+"%");
            q.where(predicate);
            Query<TareaEntity> query = session.createQuery(q);
            tareas = query.getResultList();
        }catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return tareas;
    }

    public void close() {
        //Cierre conexion a la BD
        this.dbConnection.close();
    }


}

