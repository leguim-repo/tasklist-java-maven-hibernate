package com.bootcamp.seatcode.mike;

import com.bootcamp.seatcode.mike.crud.CrudTarea;

public class testCrudTarea {
    public static void main(String[] args) {
        CrudTarea crud = new CrudTarea();
        System.out.println(crud.readTareas().toString());
        crud.close();
    }
}
