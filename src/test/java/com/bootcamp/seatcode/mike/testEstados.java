package com.bootcamp.seatcode.mike;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import com.bootcamp.seatcode.mike.tablas.Estados;


public class testEstados {

    private static EntityManager manager;
    private static EntityManagerFactory emf;

    public static void createNewEstado(String nombre,String descripcion) {
        // creo un estado nuevo de prueba
        Estados newState = new Estados(nombre,descripcion);
        manager.getTransaction().begin();
        manager.persist(newState);
        manager.getTransaction().commit();
    }

    public static void deleteEstado(String nombre) {

        //Estados estadoParaBorrar =  manager.find(Estados.class, id); //buscas el objeto por el primary key

        // esta busqueda puede encontrar mas de un registro y fallar
        Estados estadoParaBorrar = (Estados) manager.createNativeQuery(
                "SELECT * FROM estados WHERE ( nombre = '"+nombre+"')",
                Estados.class).getSingleResult();

        manager.getTransaction().begin();
        manager.remove(estadoParaBorrar);
        manager.getTransaction().commit();

    }

    public static void getEstados() {
        // datos tabla estados
        List<Estados> estados = (List<Estados>) manager.createQuery("FROM Estados").getResultList();
        System.out.println("En esta tabla hay " + estados.size() + " registros");
        for (Estados e: estados) {
            System.out.println("ID: "+e.getId());
            System.out.println("Nombre Estado:"+e.getNombre());
            System.out.println("Descripcion:"+e.getDescripcion());
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        /*Creamos el gestor de persistencia (EM)*/
        emf = Persistence.createEntityManagerFactory("TaskListPersistence");
        manager = emf.createEntityManager();

        createNewEstado("hola","aqui va la descripcion del estado");
        getEstados();
        deleteEstado("hola");
        getEstados();


        //cierro los gestores. Es obligado
        manager.close();
        emf.close();
    }
}
