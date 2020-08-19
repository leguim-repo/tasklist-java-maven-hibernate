package com.bootcamp.seatcode.mike;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import com.bootcamp.seatcode.mike.tablas.Estados;
import com.bootcamp.seatcode.mike.tablas.Login;
import com.bootcamp.seatcode.mike.tablas.Tareas;
import com.bootcamp.seatcode.mike.tablas.Usuarios;

public class testEstados {

    private static EntityManager manager;
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        /*Creamos el gestor de persistencia (EM)*/
        emf = Persistence.createEntityManagerFactory("TaskListPersistence");
        manager = emf.createEntityManager();

        // datos tabla estados
        List<Estados> estados = (List<Estados>) manager.createQuery("FROM Estados").getResultList();
        System.out.println("En esta tabla hay " + estados.size() + " registros");
        for (Estados e: estados) {
            System.out.println("ID: "+e.getId());
            System.out.println("Nombre Estado:"+e.getNombre());
            System.out.println("Descripcion:"+e.getDescripcion());
            System.out.println("");
        }

        manager.close();
        emf.close();
    }
}
