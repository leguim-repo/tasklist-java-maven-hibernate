package com.bootcamp.seatcode.mike;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


import com.bootcamp.seatcode.mike.entities.TareaEntity;

public class testTareas {

    private static EntityManager em;
    private static EntityManagerFactory emf;

    public static void MyPrint(String TituloLista, List<TareaEntity> lista) {
        System.out.println("** ** "+TituloLista+" ** **");
        System.out.println("En esta tabla hay " + lista.size() + " registros");
        for (TareaEntity e: lista) {
            System.out.println("ID: "+e.getId());
            System.out.println("Nombre Estado:"+e.getTitulo());
            System.out.println("Descripcion:"+e.getDescripcion());
            System.out.println("");
        }
    }

    public static List<TareaEntity> buscarDescripcion(String criterio) {
        //List<Tarea> tareas = (List<Tarea>) em.createQuery("FROM Tarea WHERE ( descripcion LIKE '%"+target+"%')").getResultList();


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TareaEntity> q = cb.createQuery(TareaEntity.class);
        Root<TareaEntity> c = q.from(TareaEntity.class);
        Predicate predicate = cb.like(c.<String>get("responsable"),criterio);
        q.where(predicate);

        List<TareaEntity> tareas = em.createQuery(q).getResultList();
        return tareas;
    }

    public static void main(String[] args) {
        /*Creamos el gestor de persistencia (EM)*/
        emf = Persistence.createEntityManagerFactory("TaskListPersistence");
        em = emf.createEntityManager();

        // datos tabla tareas
        List<TareaEntity> tareas = (List<TareaEntity>) em.createQuery("FROM TareaEntity").getResultList();


        MyPrint("Lista Tareas", tareas);
        ;
        MyPrint("Busqueda Tareas", buscarDescripcion("%in%"));

        em.close();
        emf.close();
    }
}
