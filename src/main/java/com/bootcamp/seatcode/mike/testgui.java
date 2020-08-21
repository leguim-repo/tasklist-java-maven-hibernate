package com.bootcamp.seatcode.mike;

import com.bootcamp.seatcode.mike.crud.CrudTarea;
import com.bootcamp.seatcode.mike.tablas.Tarea;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.*;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.*;

import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

public class testgui {
    private static Terminal terminal;
    private static Screen screen;
    private static WindowBasedTextGUI textGUI;
    private static BasicWindow window;
    private static Panel mainPanel;

    private static CrudTarea crudTarea;

    public enum Acciones {
        LISTA_TAREAS,
        CREAR_TAREA,
        EDITAR_TAREA,
        CAMBIAR_ESTADO,
        BORRAR_TAREA,
        BUSCAR_USUARIO,
        BUSCAR_NOMBRE,
        BUSCAR_DESCRIPCION;
    }



    public static Panel panelEditarTarea(Tarea tarea){
        final Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));

        panel.addComponent(new Label("id"));
        final TextBox id = new TextBox(String.valueOf(tarea.getId()));
        id.setReadOnly(true);
        panel.addComponent(id);


        panel.addComponent(new Label("Estado"));
        final TextBox estado = new TextBox(tarea.getEstado());
        panel.addComponent(estado);

        panel.addComponent(new Label("Titulo"));
        final TextBox titulo = new TextBox(tarea.getTitulo());
        panel.addComponent(titulo);

        panel.addComponent(new Label("Descripcion"));
        final TextBox descripcion = new TextBox(tarea.getDescripcion());
        panel.addComponent(descripcion);

        panel.addComponent(new Label("Responsable"));
        final TextBox responsable = new TextBox(tarea.getResponsable());
        panel.addComponent(responsable);

        panel.addComponent(new Label("Fecha"));
        final TextBox fecha = new TextBox(String.valueOf(tarea.getFecha()));
        panel.addComponent(fecha);

        //panel.addComponent(new EmptySpace(new TerminalSize(0,1))); // Empty space underneath labels
        panel.addComponent(new EmptySpace(new TerminalSize(2, 1)));

        Button btPrueba = new Button("Crear", new Runnable() {
            @Override
            public void run() {
                // Actions go here
                System.out.println(estado.getText());

            }
        });
        panel.addComponent(btPrueba);

        Button btnCancelar = new Button("Cancelar", new Runnable() {
            @Override
            public void run() {
                mainPanel.removeComponent(panel);
                window.setComponent(mainPanel);
            }
        });
        panel.addComponent(btnCancelar);

        return panel;
    }

    public static Tarea row2Tarea(List<String> row) {

        Tarea tarea = new Tarea();
        tarea.setId(Integer.parseInt(row.get(0)));
        tarea.setEstado(row.get(1));
        tarea.setTitulo(row.get(2));
        tarea.setDescripcion(row.get(3));
        tarea.setResponsable(row.get(4));
        tarea.setFecha(Date.valueOf(row.get(5)));

        return tarea;
    }

    public static void panelListaTareas(final Acciones accion)  {
        final Panel panelTabla = new Panel();
        panelTabla.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        final Table<String> tabla = new Table<String>("ID", "Estado", "Titulo", "Descripcion", "Responsable", "Fecha");
        for (Tarea e: crudTarea.readTareas()) {
            tabla.getTableModel().addRow(String.valueOf(e.getId()), e.getEstado(), e.getTitulo(), e.getDescripcion(),  e.getResponsable(), String.valueOf(e.getFecha()));
        }

        tabla.setSelectAction(new Runnable() {
            @Override
            public void run() {
                Tarea tarea;
                tarea= row2Tarea(tabla.getTableModel().getRow(tabla.getSelectedRow()));
                System.out.println("Tarea objetivo:"+tarea.toString());
                switch (accion) {
                    case LISTA_TAREAS:
                        System.out.println("Accion: LISTA_TAREAS");
                        break;

                    case EDITAR_TAREA:
                        System.out.println("Accion: EDITAR_TAREA");
                        window.setComponent(panelEditarTarea(tarea));
                        break;

                    case CAMBIAR_ESTADO:
                        System.out.println("Accion: CAMBIAR_ESTADO");
                        window.setComponent(panelEditarTarea(tarea));
                        break;

                    case BORRAR_TAREA:
                        System.out.println("Accion: BORRAR_TAREA");
                        break;

                    default:
                        System.out.println("Valor no controlado");
                        break;
                }
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
    }


    public static void menuPrincipal() throws IOException {
        try {
            mainPanel = new Panel();
            ActionListBox dialogsListBox = new ActionListBox();

            dialogsListBox.addItem("Ver lista de Tareas", new Runnable() {
                @Override
                public void run() {
                    panelListaTareas(Acciones.LISTA_TAREAS);
                }
            });

            dialogsListBox.addItem("Crear nueva Tarea", new Runnable() {
                @Override
                public void run() {
                    //panelListaTareas(Acciones.CREAR_TAREA);
                    System.out.println("petada aqui");
                    Tarea tareavacia = new Tarea();
                    tareavacia.clear();
                    Panel panel = panelEditarTarea(tareavacia);
                    window.setComponent(panel);

                }
            });

            dialogsListBox.addItem("Cambiar estado Tarea", new Runnable() {
                @Override
                public void run() {
                    panelListaTareas(Acciones.CAMBIAR_ESTADO);

                }
            });

            dialogsListBox.addItem("Editar Tarea", new Runnable() {
                @Override
                public void run() {
                    panelListaTareas(Acciones.EDITAR_TAREA);
                }
            });

            dialogsListBox.addItem("Borrar Tarea", new Runnable() {
                @Override
                public void run() {
                    panelListaTareas(Acciones.BORRAR_TAREA);
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
                    crudTarea.close();
                    window.close();
                }
            }));
            window.setComponent(mainPanel);
            window.setHints(Collections.singletonList(Window.Hint.CENTERED));

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

        crudTarea = new CrudTarea();

        System.out.println(terminal.getTerminalSize().toString());
        //window.setHints(Arrays.asList(Window.Hint.FULL_SCREEN));

        menuPrincipal();
    }
}