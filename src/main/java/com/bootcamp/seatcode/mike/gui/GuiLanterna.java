package com.bootcamp.seatcode.mike.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.bundle.LanternaThemes;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.*;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class GuiLanterna {
    public Terminal terminal;
    public Screen screen;
    public BasicWindow window;
    public WindowBasedTextGUI textGUI;
    public Panel mainPanel;

    public GuiLanterna() throws IOException {
        this.terminal = new DefaultTerminalFactory().createTerminal();
        this.screen = new TerminalScreen(terminal);
        this.window = new BasicWindow();
        this.textGUI = new MultiWindowTextGUI(this.screen);
        this.mainPanel = new Panel();
        this.mainPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        this.window.setHints(Arrays.asList(Window.Hint.CENTERED));
        this.window.setComponent(mainPanel.withBorder(Borders.singleLine("Main Panel")));

        //esto tiene que ir al constructor
        //Setup theme
        this.textGUI.setTheme(LanternaThemes.getRegisteredTheme("default"));

        this.screen.startScreen();
    }


    public void menuPrincipal()  {
        this.mainPanel.removeAllComponents();
        final BasicWindow window = new BasicWindow("Task List Project");

        //final Panel mainPanel = new Panel();
        ActionListBox dialogsListBox = new ActionListBox();
        dialogsListBox.addItem("List Tareas", new Runnable() {
            @Override
            public void run() {
                String result = TextInputDialog.showDialog(textGUI, "TextInputDialog sample", "This is the description", "initialContent");
                System.out.println("Result was: " + result);
                verTablaDatos();
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

    public void verTablaDatos() {
        this.mainPanel.removeAllComponents();
        final Panel panelTabla = new Panel();
        panelTabla.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        final Table<String> tabla = new Table<String>("Nombre", "Apellido", "Num Cuenta");

        tabla.getTableModel().addRow("Jason", "Bourne", "111111");
        tabla.getTableModel().addRow("Denis", "Pastor", "222222");
        tabla.getTableModel().addRow("James", "Stocks", "333333");

        tabla.setSelectAction(new Runnable() {
            @Override
            public void run() {
                List<String> data = tabla.getTableModel().getRow(tabla.getSelectedRow());
                for(int i = 0; i < data.size(); i++) {
                    System.out.println(data.get(i));
                }
            }
        });

        Button btnCerrar = new Button("Cerrar", new Runnable() {
            @Override
            public void run() {
                // Actions go here
                System.out.println("btnCerrar");
                mainPanel.removeComponent(panelTabla);
                menuPrincipal();
            }
        });

        panelTabla.addComponent(new EmptySpace(new TerminalSize(0, 1)));
        panelTabla.addComponent(tabla);
        panelTabla.addComponent(new EmptySpace(new TerminalSize(0, 1)));
        panelTabla.addComponent(btnCerrar);

        this.mainPanel.addComponent(panelTabla);

    }
}
