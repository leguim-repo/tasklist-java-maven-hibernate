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
        emf = Persistence.createEntityManagerFactory("TaskList-Persistence");
        manager = emf.createEntityManager();

        List<Estados> estados = (List<Estados>) manager.createQuery("FROM Estados").getResultList();
        System.out.println("En esta base de datos hay " + estados.size() + " estados");

    }
}
