package com.bootcamp.seatcode.mike;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import com.bootcamp.seatcode.mike.crud.CrudHibernate;
import com.bootcamp.seatcode.mike.entities.TareaEntity;
import com.bootcamp.seatcode.mike.entities.UsuarioEntity;

public class testUsuarios {

    private static EntityManager manager;
    private static EntityManagerFactory emf;

    public static void MyPrint(String TituloLista, List<UsuarioEntity> lista) {
        System.out.println("** ** "+TituloLista+" ** **");
        System.out.println("En esta tabla hay " + lista.size() + " registros");
        for (UsuarioEntity e: lista) {
            System.out.println("ID: "+e.getId());
            System.out.println("Nombre:"+e.getNombre());
            System.out.println("Email:"+e.getEmail());
            System.out.println("login:"+e.getDatosLogin().getUser());
            System.out.println("pass:"+e.getDatosLogin().getPassword());

            System.out.println("");
        }
    }
    public static void main(String[] args) {
        /*Creamos el gestor de persistencia (EM)*/
        /*
        emf = Persistence.createEntityManagerFactory("TaskListPersistence");
        manager = emf.createEntityManager();

        // datos tabla usuarios
        List<UsuarioEntity> usuarios = (List<UsuarioEntity>) manager.createQuery("FROM UsuarioEntity").getResultList();
        System.out.println("En esta tabla hay " + usuarios.size() + " registros");
        for (UsuarioEntity e: usuarios) {
            System.out.println("ID: "+e.getId());
            System.out.println("Nombre: "+e.getNombre());
            System.out.println("Apellidos: "+e.getApellidos());
            System.out.println("Email: "+e.getEmail());

            System.out.println("");
        }

        manager.close();
        emf.close();
         */
        CrudHibernate crud = new CrudHibernate();
        MyPrint("Lista Usuarios",crud.getUsersResponsable());
        crud.close();
    }
}
