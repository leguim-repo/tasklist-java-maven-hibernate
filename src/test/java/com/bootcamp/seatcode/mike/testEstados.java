package com.bootcamp.seatcode.mike;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

import com.bootcamp.seatcode.mike.entities.EstadoEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class testEstados {

    private static EntityManager em;
    private static EntityManagerFactory emf;
    private static SessionFactory factory;


    public static void createNewEstado(String nombre,String descripcion) {
        // creo un estado nuevo de prueba
        EstadoEntity newState = new EstadoEntity(nombre,descripcion);
        em.getTransaction().begin();
        em.persist(newState);
        em.getTransaction().commit();
    }

    public static void deleteEstado(String nombre) {

        //Estados estadoParaBorrar =  manager.find(Estados.class, id); //buscas el objeto por el primary key

        // esta busqueda puede encontrar mas de un registro y fallar
        EstadoEntity estadoParaBorrar = (EstadoEntity) em.createNativeQuery(
                "SELECT * FROM estados WHERE ( nombre = '"+nombre+"')",
                EstadoEntity.class).getSingleResult();

        em.getTransaction().begin();
        em.remove(estadoParaBorrar);
        em.getTransaction().commit();

    }

    public static void getEstados() {
        // datos tabla estados
        List<EstadoEntity> estados = (List<EstadoEntity>) em.createQuery("FROM EstadoEntity").getResultList();
        System.out.println("En esta tabla hay " + estados.size() + " registros");
        for (EstadoEntity e: estados) {
            System.out.println("ID: "+e.getId());
            System.out.println("Nombre Estado:"+e.getNombre());
            System.out.println("Descripcion:"+e.getDescripcion());
            System.out.println("");
        }
    }

    public static void MyPrint(String TituloLista, List<EstadoEntity> lista) {
        System.out.println("** ** "+TituloLista+" ** **");
        System.out.println("En esta tabla hay " + lista.size() + " registros");
        for (EstadoEntity e: lista) {
            System.out.println("ID: "+e.getId());
            System.out.println("Nombre Estado:"+e.getNombre());
            System.out.println("Descripcion:"+e.getDescripcion());
            System.out.println("");
        }
    }

    public static void getEstadoHibernate() {
        CriteriaQuery<EstadoEntity> criteriaQuery = em.getCriteriaBuilder().createQuery(EstadoEntity.class);
        criteriaQuery.select(criteriaQuery.from(EstadoEntity.class));
        List<EstadoEntity> estados = em.createQuery(criteriaQuery).getResultList();
        MyPrint("Lista de Estados", estados);
    }

    public static void getFindEstadoByNombreHibernate(String criterio) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<EstadoEntity> q = cb.createQuery(EstadoEntity.class);
        Root<EstadoEntity> c = q.from(EstadoEntity.class);
        Predicate predicate = cb.like(c.<String>get("nombre"),criterio);
        q.where(predicate);

        List<EstadoEntity> listEstadosEncontrados = em.createQuery(q).getResultList();
        MyPrint("Estados encontrados con el criterio "+criterio,listEstadosEncontrados);
    }

    public static void main(String[] args) {
        /*
        // Creamos el gestor de persistencia (EM)
        emf = Persistence.createEntityManagerFactory("TaskListPersistence");
        em = emf.createEntityManager();
        //createNewEstado("hola","aqui va la descripcion del estado");
        //getEstados();
        //deleteEstado("hola");
        //getEstados();
        getFindEstadoByNombreHibernate("do");
        getEstadoHibernate();
        */


        // Test para usar Full Hibernate
        // Configuracion de Full Hibernate tiene que ir al contructor de crudHibernate
        try {
            Configuration config = new Configuration();
            config.addAnnotatedClass(EstadoEntity.class);
            config.configure();
            factory = config.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        // abrimos la session
        Session session = factory.openSession();

        try {
            CriteriaQuery<EstadoEntity> cq = session.getCriteriaBuilder().createQuery(EstadoEntity.class);
            cq.select(cq.from(EstadoEntity.class));
            List<EstadoEntity> estados = session.createQuery(cq).getResultList();
            MyPrint("Listado de estados via HIBERNATE A FULL",estados);

        }catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }

        session.close();


        //cierro los gestores. Es obligado
        /*
        em.close();
        emf.close();
        */
    }
}
