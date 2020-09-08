package com.bootcamp.seatcode.mike;

import com.bootcamp.seatcode.mike.crud.CrudHibernate;
import com.bootcamp.seatcode.mike.entities.TareaEntity;

import java.util.List;

public class testGetTareaH {
    public static void MyPrint(String TituloLista, List<TareaEntity> lista) {
        System.out.println("** ** "+TituloLista+" ** **");
        System.out.println("En esta tabla hay " + lista.size() + " registros");
        for (TareaEntity e: lista) {
            System.out.println("ID: "+e.getId());
            System.out.println("Titulo:"+e.getTitulo());
            System.out.println("Descripcion:"+e.getDescripcion());
            System.out.println("");
        }
    }
    public static void main(String[] args) {
        CrudHibernate crud = new CrudHibernate();
        MyPrint("DarthVader",crud.getTareas());
        crud.close();
    }
}
