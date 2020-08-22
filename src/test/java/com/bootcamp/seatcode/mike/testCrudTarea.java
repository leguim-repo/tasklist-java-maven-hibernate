package com.bootcamp.seatcode.mike;

import com.bootcamp.seatcode.mike.crud.CrudHibernate;

public class testCrudTarea {
    public static void main(String[] args) {
        CrudHibernate crud = new CrudHibernate();
        System.out.println(crud.readTareas().toString());
        crud.close();
    }
}
