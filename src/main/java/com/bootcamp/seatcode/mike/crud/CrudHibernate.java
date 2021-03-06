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


    public List<UsuarioEntity> getUsersResponsable() {
        //TODO pasar el try para arriba -> throws Throwable
        List<UsuarioEntity> usuarios = null;
        Session session = this.dbConnection.openSession();
        try {
            CriteriaQuery<UsuarioEntity> cq = session.getCriteriaBuilder().createQuery(UsuarioEntity.class);
            cq.select(cq.from(UsuarioEntity.class));
            usuarios = session.createQuery(cq).getResultList();
        }catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return usuarios;
    }

    public EstadoEntity findEstadoByNombre(String criterio) {
        //TODO pasar el try para arriba -> throws Throwable
        EstadoEntity estado = null;
        Session session = this.dbConnection.openSession();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<EstadoEntity> q = cb.createQuery(EstadoEntity.class);
            Root<EstadoEntity> c = q.from(EstadoEntity.class);
            Predicate predicate = cb.like(c.<String>get("nombre"),"%"+criterio+"%");
            q.where(predicate);
            Query<EstadoEntity> query = session.createQuery(q);
            estado = query.getResultList().get(0);
        }catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return estado;
    }

    public UsuarioEntity findUserByNombre(String criterio){
        //TODO pasar el try para arriba -> throws Throwable
        UsuarioEntity usuario = null;
        Session session = this.dbConnection.openSession();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<UsuarioEntity> q = cb.createQuery(UsuarioEntity.class);
            Root<UsuarioEntity> c = q.from(UsuarioEntity.class);
            Predicate predicate = cb.like(c.<String>get("nombre"),"%"+criterio+"%");
            q.where(predicate);
            Query<UsuarioEntity> query = session.createQuery(q);
            usuario = query.getResultList().get(0);
        }catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return usuario;
    }

    public void createUser(UsuarioEntity nuevoUser) {
        //TODO pasar el try para arriba -> throws Throwable
        Session session = this.dbConnection.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(nuevoUser);
            transaction.commit();
        }catch (Throwable ex) {
            if (transaction!=null) transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
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

    public TareaEntity getTareaById(long id) {
        TareaEntity tarea = null;
        Session session = this.dbConnection.openSession();
        try {
            tarea = session.get(TareaEntity.class, id);
        } catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return tarea;
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

