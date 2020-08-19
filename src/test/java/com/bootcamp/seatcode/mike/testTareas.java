package com.bootcamp.seatcode.mike;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import com.bootcamp.seatcode.mike.tablas.Estados;
import com.bootcamp.seatcode.mike.tablas.Login;
import com.bootcamp.seatcode.mike.tablas.Tareas;
import com.bootcamp.seatcode.mike.tablas.Usuarios;

public class testTareas {

    private static EntityManager manager;
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        /*Creamos el gestor de persistencia (EM)*/
        emf = Persistence.createEntityManagerFactory("TaskListPersistence");
        manager = emf.createEntityManager();

        // datos tabla tareas
        List<Tareas> tareas = (List<Tareas>) manager.createQuery("FROM Tareas").getResultList();
        System.out.println("En esta tabla hay " + tareas.size() + " registros");
        for (Tareas e: tareas) {
            System.out.println("ID: "+e.getId());
            System.out.println("Titulo: "+e.getTitulo());
            System.out.println("Descripcion: "+e.getDescripcion());
            System.out.println("Estado: "+e.getEstado());
            System.out.println("Responsable: "+e.getResponsable());
            System.out.println("Fecha: "+e.getFecha());
            System.out.println("");
        }

        manager.close();
        emf.close();
    }
}
