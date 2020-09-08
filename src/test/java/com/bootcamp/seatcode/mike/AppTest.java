package com.bootcamp.seatcode.mike;

import static org.junit.Assert.assertTrue;

import com.bootcamp.seatcode.mike.crud.CrudHibernate;
import com.bootcamp.seatcode.mike.entities.EstadoEntity;
import org.junit.Test;

import javax.swing.*;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void getEstados() {
        CrudHibernate crud = new CrudHibernate();
        crud.getEstados();
        crud.close();
    }
    @Test
    public void getTareas() {
        CrudHibernate crud = new CrudHibernate();
        crud.getTareas();
        crud.close();
    }
}
