package com.bootcamp.seatcode.mike;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import com.bootcamp.seatcode.mike.models.Estados;

public class testEstados {

    private static EntityManager manager;
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        /*Creamos el gestor de persistencia (EM)*/
        emf = Persistence.createEntityManagerFactory("TaskListPersistence");
        manager = emf.createEntityManager();

        List<Estados> estados = (List<Estados>) manager.createQuery("FROM Estados").getResultList();
        System.out.println("En esta base de datos hay " + estados.size() + " estados");
        for (Estados e: estados) {
            System.out.println("Nombre Estado:"+e.getNombre());
            System.out.println("Descripcion:"+e.getDescripcion());
            System.out.println("");

        }
        manager.close();
        emf.close();
    }
}
