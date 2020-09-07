package com.bootcamp.seatcode.mike;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import com.bootcamp.seatcode.mike.entities.LoginEntity;

public class testLogin {

    private static EntityManager manager;
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        /*Creamos el gestor de persistencia (EM)*/
        emf = Persistence.createEntityManagerFactory("TaskListPersistence");
        manager = emf.createEntityManager();

        // datos tabla login
        List<LoginEntity> login = (List<LoginEntity>) manager.createQuery("FROM LoginEntity").getResultList();
        System.out.println("En esta tabla hay " + login.size() + " registros");
        for (LoginEntity e: login) {
            System.out.println("ID: "+e.getId());
            System.out.println("Usuario: "+e.getUser());
            System.out.println("Password: "+e.getPassword());
            System.out.println("Activo: "+e.getActive());
            System.out.println("");
        }

        manager.close();
        emf.close();
    }
}
