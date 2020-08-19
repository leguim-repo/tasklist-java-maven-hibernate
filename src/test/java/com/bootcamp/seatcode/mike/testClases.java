package com.bootcamp.seatcode.mike;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import com.bootcamp.seatcode.mike.tablas.Estados;
import com.bootcamp.seatcode.mike.tablas.Login;
import com.bootcamp.seatcode.mike.tablas.Tareas;
import com.bootcamp.seatcode.mike.tablas.Usuarios;

public class testClases {

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
            System.out.println("Nombre Estado:"+e.getNombre());
            System.out.println("Descripcion:"+e.getDescripcion());
            System.out.println("");
        }

        // datos tabla login
        List<Login> login = (List<Login>) manager.createQuery("FROM Login").getResultList();
        System.out.println("En esta tabla hay " + login.size() + " registros");
        for (Login e: login) {
            System.out.println("Usuario: "+e.getUser());
            System.out.println("Password: "+e.getPassword());
            System.out.println("Activo: "+e.getActive());
            System.out.println("");
        }

        // datos tabla tareas
        List<Tareas> tareas = (List<Tareas>) manager.createQuery("FROM Tareas").getResultList();
        System.out.println("En esta tabla hay " + tareas.size() + " registros");
        for (Tareas e: tareas) {
            System.out.println("Titulo: "+e.getTitulo());
            System.out.println("Descripcion: "+e.getDescripcion());
            System.out.println("Estado: "+e.getEstado());
            System.out.println("Responsable: "+e.getResponsable());
            System.out.println("Fecha: "+e.getFecha());
            System.out.println("");
        }

        // datos tabla usuarios
        List<Usuarios> usuarios = (List<Usuarios>) manager.createQuery("FROM Usuarios").getResultList();
        System.out.println("En esta tabla hay " + usuarios.size() + " registros");
        for (Usuarios e: usuarios) {
            System.out.println("Nombre: "+e.getNombre());
            System.out.println("Apellidos: "+e.getApellidos());
            System.out.println("Email: "+e.getEmail());

            System.out.println("");
        }
        manager.close();
        emf.close();
    }
}
