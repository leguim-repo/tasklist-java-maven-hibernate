package com.bootcamp.seatcode.mike;

import com.bootcamp.seatcode.mike.gui.GuiLanterna;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            GuiLanterna gui = new GuiLanterna();
            gui.menuPrincipal();

        } catch (IOException e) {
            e.printStackTrace();
        }    }
}
