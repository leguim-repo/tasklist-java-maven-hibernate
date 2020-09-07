package com.bootcamp.seatcode.mike;

import com.bootcamp.seatcode.mike.crud.CrudHibernate;
import com.bootcamp.seatcode.mike.entities.EstadoEntity;
import com.bootcamp.seatcode.mike.entities.TareaEntity;
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

public class App {
    private static Terminal terminal;
    private static Screen screen;
    private static WindowBasedTextGUI textGUI;
    private static BasicWindow window;
    private static Panel mainPanel;

    private static CrudHibernate crud;

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


    public static ComboBox<String> comboEstados() {
        ComboBox<String> comboBox = new ComboBox<String>();
        for (EstadoEntity e: crud.getEstados()) {
           comboBox.addItem(e.getNombre());
        }
        return comboBox;
    }

    public static Panel panelCambiarEstado(final Acciones accion, final TareaEntity tarea) {
        final Panel panelMain = new Panel();
        panelMain.setLayoutManager(new LinearLayout(Direction.VERTICAL));
        panelMain.addComponent(new EmptySpace(new TerminalSize(0, 1)));

        final Panel panelCampos = new Panel();
        panelCampos.setLayoutManager(new GridLayout(2));

        panelCampos.addComponent(new Label("Estado Actual"));
        panelCampos.addComponent(new TextBox(new TerminalSize(45, 1), tarea.getEstado()).setReadOnly(true));

        panelCampos.addComponent(new Label("Nuevo Estado"));

        final ComboBox comboEstado = comboEstados();
        panelCampos.addComponent(comboEstado);
        panelCampos.addComponent(new EmptySpace(TerminalSize.ONE));

        final Panel panelBotones = new Panel();
        panelBotones.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
        panelBotones.addComponent(new EmptySpace(new TerminalSize(0, 0)));

        Button btAceptar = new Button("Aceptar", new Runnable() {
            @Override
            public void run() {
                System.out.println("Accion: "+accion.toString());
                System.out.println("Nuevo estoado: "+comboEstado.getText());
                tarea.setEstado(comboEstado.getText());
                crud.updateTarea(tarea);
                mainPanel.removeComponent(panelMain);
                window.setComponent(mainPanel);
            }
        });

        Button btnCancelar = new Button("Cancelar", new Runnable() {
            @Override
            public void run() {
                mainPanel.removeComponent(panelMain);
                window.setComponent(mainPanel);
            }
        });

        panelBotones.addComponent(btAceptar);
        panelBotones.addComponent(btnCancelar);

        panelMain.addComponent(panelCampos);
        panelMain.addComponent(new EmptySpace(TerminalSize.ONE));
        panelMain.addComponent(panelBotones);

        return panelMain;
    }

    public static Panel panelTarea(final Acciones accion, final TareaEntity tarea){
        final Panel panelMain = new Panel();
        panelMain.setLayoutManager(new LinearLayout(Direction.VERTICAL));
        panelMain.addComponent(new EmptySpace(new TerminalSize(0, 1)));


        final Panel panelCampos = new Panel();
        panelCampos.setLayoutManager(new GridLayout(2));

        panelCampos.addComponent(new Label("id"));
        final TextBox id = new TextBox(String.valueOf(tarea.getId()));
        id.setReadOnly(true);
        panelCampos.addComponent(id);

        panelCampos.addComponent(new Label("Estado"));
        //final TextBox estado = new TextBox(new TerminalSize(45, 1), tarea.getEstado());
        final ComboBox<String> estado = comboEstados();
        panelCampos.addComponent(estado);

        panelCampos.addComponent(new Label("Titulo"));
        final TextBox titulo = new TextBox(new TerminalSize(45, 1), tarea.getTitulo());
        panelCampos.addComponent(titulo);

        panelCampos.addComponent(new Label("Descripcion"));
        final TextBox descripcion = new TextBox(new TerminalSize(50, 4),tarea.getDescripcion());
        panelCampos.addComponent(descripcion);

        panelCampos.addComponent(new Label("Responsable"));
        final TextBox responsable = new TextBox(new TerminalSize(45, 1), tarea.getResponsable());
        panelCampos.addComponent(responsable);

        panelCampos.addComponent(new Label("Fecha"));
        final TextBox fecha = new TextBox(new TerminalSize(11, 1), String.valueOf(tarea.getFecha()));
        panelCampos.addComponent(fecha);


        Panel panelBotones = new Panel();
        panelBotones.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
        panelBotones.addComponent(new EmptySpace(new TerminalSize(12, 0)));

        Button btAceptar = new Button("Aceptar", new Runnable() {
            @Override
            public void run() {
                tarea.setEstado(estado.getText());
                tarea.setTitulo(titulo.getText());
                tarea.setDescripcion(descripcion.getText());
                tarea.setResponsable(responsable.getText());
                tarea.setFecha(Date.valueOf(fecha.getText()));

                //TODO controlar valores erroneos (fecha) o campos vacios
                if (accion == Acciones.CREAR_TAREA) {
                    System.out.println("Accion: "+accion.toString());
                    crud.createTarea(tarea);
                    System.out.println(tarea.toString());
                }

                if (accion == Acciones.EDITAR_TAREA) {
                    System.out.println("Accion: "+accion.toString());
                    crud.updateTarea(tarea);
                    System.out.println(tarea.toString());

                }
                mainPanel.removeComponent(panelMain);
                window.setComponent(mainPanel);
            }
        });
        Button btnCancelar = new Button("Cancelar", new Runnable() {
            @Override
            public void run() {
                mainPanel.removeComponent(panelMain);
                window.setComponent(mainPanel);
            }
        });

        panelBotones.addComponent(btAceptar);
        panelBotones.addComponent(btnCancelar);

        panelMain.addComponent(panelCampos);
        panelMain.addComponent(new EmptySpace(TerminalSize.ONE));
        panelMain.addComponent(panelBotones);

        return panelMain;
    }

    public static TareaEntity row2Tarea(List<String> row) {

        TareaEntity tarea = new TareaEntity();
        tarea.setId(Integer.parseInt(row.get(0)));
        tarea.setEstado(row.get(1));
        tarea.setTitulo(row.get(2));
        tarea.setDescripcion(row.get(3));
        tarea.setResponsable(row.get(4));
        tarea.setFecha(Date.valueOf(row.get(5)));

        return tarea;
    }


    public static void panelListaTareas(List<TareaEntity> tareas, final Acciones accion)  {
        final Panel panelTabla = new Panel();
        panelTabla.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        final Table<String> tabla = new Table<String>("ID", "Estado", "Titulo", "Descripcion", "Responsable", "Fecha");
        for (TareaEntity e: tareas) {
            tabla.getTableModel().addRow(String.valueOf(e.getId()), e.getEstado(), e.getTitulo(), e.getDescripcion(),  e.getResponsable(), String.valueOf(e.getFecha()));
        }

        tabla.setSelectAction(new Runnable() {
            @Override
            public void run() {
                TareaEntity tarea;
                tarea= row2Tarea(tabla.getTableModel().getRow(tabla.getSelectedRow()));
                System.out.println("Tarea objetivo:"+tarea.toString());
                switch (accion) {
                    case LISTA_TAREAS:
                        System.out.println("Accion: "+accion.toString());
                        break;

                    case EDITAR_TAREA:
                        System.out.println("Accion: "+accion.toString());
                        window.setComponent(panelTarea(accion, tarea));
                        break;

                    case CAMBIAR_ESTADO:
                        System.out.println("Accion: "+accion.toString());
                        window.setComponent(panelCambiarEstado(accion, tarea));
                        //window.setComponent(panelEditarTarea(tarea));
                        break;

                    case BORRAR_TAREA:
                        System.out.println("Accion: "+accion.toString());
                        MessageDialogButton selectedButton = new MessageDialogBuilder().setTitle("Borrar Tarea?")
                                .setText("Esta seguro que desea borrar la Tarea seleccionada?")
                                .addButton(MessageDialogButton.Yes)
                                .addButton(MessageDialogButton.No)
                                .build()
                                .showDialog(textGUI);
                        if (selectedButton == MessageDialogButton.Yes) {
                            System.out.println("Borrado confirmado");
                            crud.deleteTarea(tarea.getId());
                            tabla.getTableModel().removeRow(tabla.getSelectedRow());
                        }
                        else {
                            System.out.println("Borrado cancelado");
                        }
                        break;

                    default:
                        System.out.println("Valor no controlado");
                        break;
                }
            }
        });

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

            dialogsListBox.addItem("Ver Lista de Tareas", new Runnable() {
                @Override
                public void run() {
                    panelListaTareas(crud.getTareas(), Acciones.LISTA_TAREAS);
                }
            });

            dialogsListBox.addItem("Crear Nueva Tarea", new Runnable() {
                @Override
                public void run() {
                    TareaEntity tareavacia = new TareaEntity();
                    tareavacia.clear();
                    window.setComponent(panelTarea(Acciones.CREAR_TAREA, tareavacia));
                }
            });

            dialogsListBox.addItem("Cambiar Estado Tarea", new Runnable() {
                @Override
                public void run() {
                    panelListaTareas(crud.getTareas(), Acciones.CAMBIAR_ESTADO);

                }
            });

            dialogsListBox.addItem("Editar Tarea", new Runnable() {
                @Override
                public void run() {
                    panelListaTareas(crud.getTareas(), Acciones.EDITAR_TAREA);
                }
            });

            dialogsListBox.addItem("Borrar Tarea", new Runnable() {
                @Override
                public void run() {
                    panelListaTareas(crud.getTareas(), Acciones.BORRAR_TAREA);
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
                                    MessageDialog.showMessageDialog(textGUI, "Busqueda por...", "Usuario: "+usuario+"\nFUNCIONALIDAD PENDIENTE\nEn PDF no queda claro", MessageDialogButton.OK);
                                    window.setComponent(mainPanel);
                                }
                            })
                            .addAction("Responsable", new Runnable() {
                                @Override
                                public void run() {
                                    String nombre = TextInputDialog.showDialog(textGUI, "Nombre", "Introduzca el Responsable a buscar", "Nombre");
                                    System.out.println("nombre: " + nombre);
                                    //MessageDialog.showMessageDialog(textGUI, "Busqueda por...", "Nombre Responsable: "+nombre, MessageDialogButton.OK);
                                    panelListaTareas(crud.buscarPorResponsable(nombre), Acciones.LISTA_TAREAS);
                                }
                            })
                            .addAction("Descripcion", new Runnable() {
                                @Override
                                public void run() {
                                    String descripcion = TextInputDialog.showDialog(textGUI, "Descripcion", "Introduzca Descripcion a buscar", "Descripcion");
                                    System.out.println("descripcion: " + descripcion);
                                    //MessageDialog.showMessageDialog(textGUI, "Busqueda por...", "Descripcion: "+descripcion, MessageDialogButton.OK);
                                    panelListaTareas(crud.buscarDescripcion(descripcion), Acciones.LISTA_TAREAS);
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
                    crud.close();
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
        DefaultTerminalFactory MiConfiguracionDeTerminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(120,24));
        terminal = MiConfiguracionDeTerminal.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.startScreen();
        textGUI = new MultiWindowTextGUI(screen);
        window = new BasicWindow("Task List Project");

        crud = new CrudHibernate();

        System.out.println(terminal.getTerminalSize().toString());
        //window.setHints(Arrays.asList(Window.Hint.FULL_SCREEN));

        menuPrincipal();
    }
}