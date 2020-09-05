package com.bootcamp.seatcode.mike;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import com.bootcamp.seatcode.mike.tablas.Usuario;

public class testUsuarios {

    private static EntityManager manager;
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        /*Creamos el gestor de persistencia (EM)*/
        emf = Persistence.createEntityManagerFactory("TaskListPersistence");
        manager = emf.createEntityManager();

        // datos tabla usuarios
        List<Usuario> usuarios = (List<Usuario>) manager.createQuery("FROM Usuario").getResultList();
        System.out.println("En esta tabla hay " + usuarios.size() + " registros");
        for (Usuario e: usuarios) {
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
