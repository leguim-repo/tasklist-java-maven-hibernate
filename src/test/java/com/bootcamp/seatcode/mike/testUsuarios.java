package com.bootcamp.seatcode.mike;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import com.bootcamp.seatcode.mike.tablas.Estados;
import com.bootcamp.seatcode.mike.tablas.Login;
import com.bootcamp.seatcode.mike.tablas.Tareas;
import com.bootcamp.seatcode.mike.tablas.Usuarios;

public class testUsuarios {

    private static EntityManager manager;
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        /*Creamos el gestor de persistencia (EM)*/
        emf = Persistence.createEntityManagerFactory("TaskListPersistence");
        manager = emf.createEntityManager();

        // datos tabla usuarios
        List<Usuarios> usuarios = (List<Usuarios>) manager.createQuery("FROM Usuarios").getResultList();
        System.out.println("En esta tabla hay " + usuarios.size() + " registros");
        for (Usuarios e: usuarios) {
            System.out.println("ID: "+e.getId());
            System.out.println("Nombre: "+e.getNombre());
            System.out.println("Apellidos: "+e.getApellidos());
            System.out.println("Email: "+e.getEmail());

            System.out.println("");
        }
        manager.close();
        emf.close();
    }
}
