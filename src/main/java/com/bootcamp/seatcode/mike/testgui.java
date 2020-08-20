package com.bootcamp.seatcode.mike;

import com.bootcamp.seatcode.mike.tablas.Tarea;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.*;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class testgui {
    private static Terminal terminal;
    private static Screen screen;
    private static WindowBasedTextGUI textGUI;
    private static BasicWindow window;
    private static Panel mainPanel;

    private static EntityManager manager;
    private static EntityManagerFactory emf;

    public static List<Tarea> getTareas() {
        emf = Persistence.createEntityManagerFactory("TaskListPersistence");
        manager = emf.createEntityManager();
        List<Tarea> tareas = (List<Tarea>) manager.createQuery("FROM Tarea").getResultList();
        manager.close();
        emf.close();
        return tareas;
    }

    public static void verTabla()  {
        final Panel panelTabla = new Panel();
        panelTabla.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        final Table<String> tabla = new Table<String>("ID", "Estado", "Titulo", "Descripcion", "Responsable", "Fecha");
        for (Tarea e: getTareas()) {
            //System.out.println(e);
            tabla.getTableModel().addRow(String.valueOf(e.getId()), e.getEstado(), e.getTitulo(), e.getDescripcion(), e.getEstado(), e.getResponsable(), "Fecha");
        }

        tabla.setSelectAction(new Runnable() {
            @Override
            public void run() {
                List<String> data = tabla.getTableModel().getRow(tabla.getSelectedRow()); // no esta habilitado multirow
                System.out.println(data.toString());
                /*
                for(int i = 0; i < data.size(); i++) {
                    System.out.println(data.get(i));
                }
                 */
            }
        });

        // TODO botones custom para cada opcion lista, cambiar estado, editar, borrar
        Button btnCerrar = new Button("Cerrar", new Runnable() {
            @Override
            public void run() {
                // Actions go here
                System.out.println("btnCerrar");
                mainPanel.removeComponent(panelTabla);
                window.setComponent(mainPanel);
            }
        });

        panelTabla.addComponent(new EmptySpace(new TerminalSize(0, 1)));
        panelTabla.addComponent(tabla);
        panelTabla.addComponent(new EmptySpace(new TerminalSize(0, 1)));
        panelTabla.addComponent(btnCerrar);

        window.setComponent(panelTabla);
        //textGUI.addWindowAndWait(window);
    }


    public static void menuPrincipal() throws IOException {
        try {
            mainPanel = new Panel();
            ActionListBox dialogsListBox = new ActionListBox();
            dialogsListBox.addItem("Lista de Tareas", new Runnable() {
                @Override
                public void run() {
                    String result = TextInputDialog.showDialog(textGUI, "TextInputDialog sample", "This is the description", "initialContent");
                    System.out.println("Result was: " + result);
                    verTabla();
                }
            });

            dialogsListBox.addItem("Crear nueva Tarea", new Runnable() {
                @Override
                public void run() {
                    String result = TextInputDialog.showPasswordDialog(textGUI, "Test password input", "This is a password input dialog", "");
                    System.out.println("Result was: " + result);
                }
            });

            dialogsListBox.addItem("Cambiar estado Tarea", new Runnable() {
                @Override
                public void run() {
                    String result = new TextInputDialogBuilder()
                            .setTitle("Multi-line editor")
                            .setTextBoxSize(new TerminalSize(35, 5))
                            .build()
                            .showDialog(textGUI);
                    System.out.println("Result was: " + result);
                }
            });

            dialogsListBox.addItem("Editar Tarea", new Runnable() {
                @Override
                public void run() {
                    String result = new TextInputDialogBuilder()
                            .setTitle("Numeric input")
                            .setDescription("Enter a number")
                            .setValidationPattern(Pattern.compile("[0-9]+"), "Please enter a valid number")
                            .build()
                            .showDialog(textGUI);
                    System.out.println("Result was: " + result);
                }
            });
            dialogsListBox.addItem("Borrar Tarea", new Runnable() {
                @Override
                public void run() {
                    File result = new FileDialogBuilder()
                            .setTitle("Open File")
                            .setDescription("Choose a file:")
                            .setActionLabel(LocalizedString.Open.toString())
                            .build()
                            .showDialog(textGUI);
                    System.out.println("Result was: " + result);
                }
            });

            dialogsListBox.addItem("Buscar Tarea por...", new Runnable() {
                @Override
                public void run() {
                    new ActionListDialogBuilder()
                            .setTitle("Buscar Tarea por...")
                            .setDescription("Escoja el parametro de busqueda")
                            .addAction("Usuario", new Runnable() {
                                @Override
                                public void run() {
                                    String usuario = TextInputDialog.showDialog(textGUI, "Usuario", "Introduzca el Usuario a buscar", "Usuario");
                                    System.out.println("usuario: " + usuario);
                                    MessageDialog.showMessageDialog(textGUI, "Busqueda por...", "Usuario: "+usuario, MessageDialogButton.OK);
                                }
                            })
                            .addAction("Nombre", new Runnable() {
                                @Override
                                public void run() {
                                    String nombre = TextInputDialog.showDialog(textGUI, "Nombre", "Introduzca el Nombre a buscar", "Nombre");
                                    System.out.println("nombre: " + nombre);
                                    MessageDialog.showMessageDialog(textGUI, "Busqueda por...", "Nombre: "+nombre, MessageDialogButton.OK);
                                }
                            })
                            .addAction("Descripcion", new Runnable() {
                                @Override
                                public void run() {
                                    String descripcion = TextInputDialog.showDialog(textGUI, "Descripcion", "Introduzca Descripcion a buscar", "Descripcion");
                                    System.out.println("descripcion: " + descripcion);
                                    MessageDialog.showMessageDialog(textGUI, "Busqueda por...", "Descripcion: "+descripcion, MessageDialogButton.OK);
                                }
                            })
                            .build()
                            .showDialog(textGUI);
                }
            });

            mainPanel.addComponent(dialogsListBox);
            mainPanel.addComponent(new EmptySpace(TerminalSize.ONE));
            mainPanel.addComponent(new Button("Exit", new Runnable() {
                @Override
                public void run() {
                    window.close();
                }
            }));
            window.setComponent(mainPanel);

            textGUI.addWindowAndWait(window);
        }
        finally {
            screen.stopScreen();
        }
    }
    public static void main(String[] args) throws IOException {
        //Todo como cambiar el tamaño de la ventana y maximizarla
        TerminalSize size = new TerminalSize(100,24);

        terminal = new DefaultTerminalFactory().createTerminal();
        screen = new TerminalScreen(terminal);
        screen.startScreen();
        textGUI = new MultiWindowTextGUI(screen);
        window = new BasicWindow("Task List Project");



        System.out.println(terminal.getTerminalSize().toString());
        //window.setHints(Arrays.asList(Window.Hint.FULL_SCREEN));

        menuPrincipal();
    }
}