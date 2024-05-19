package org.viajes.Frontend.Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.viajes.BBDD.Persistencia.Models.RegisterUser;
import org.viajes.Backend.Clases.registerUserController;
import org.viajes.Backend.Ficheros.categoryController;
import org.viajes.Frontend.Ficheros.EscribirFichero;

public class Registro extends JFrame {
    //Ponemos los atributos
    private JFrame frame;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JPasswordField txtContraseña;
    private JTextField txtEmail;
    private JTextField txtTelefono;
    private JButton btnRegistrar;
    private JButton btnIniciarSesion;
    private static String infoEnviar;


    public Registro() {
        setTitle("Registro nuevo Usuario");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();

    }

    private void initComponents() {

        //Valores del usuario
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblApellido = new JLabel("Apellido:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblTelefono = new JLabel("Teléfono:");
        JLabel lblContraseña = new JLabel("Contraseña:");

        txtNombre = new JTextField(15);
        txtApellido = new JTextField(15);
        txtEmail = new JTextField(15);
        txtTelefono = new JTextField(15);
        txtContraseña = new JPasswordField(15);
        btnRegistrar = new JButton("Registrar");
        btnIniciarSesion = new JButton("¿Ya te habías registrado? Inicia sesión");

        //Con el addActionListener hacemos que al pulsar register vaya al registro
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });

        // Añadimos ActionListener al botón de Iniciar Sesión
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });

        // Establecer el diseño de la ventana
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblNombre, gbc);

        gbc.gridx = 1;
        add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblApellido, gbc);

        gbc.gridx = 1;
        add(txtApellido, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblContraseña, gbc);

        gbc.gridx = 1;
        add(txtContraseña, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblEmail, gbc);

        gbc.gridx = 1;
        add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(lblTelefono, gbc);

        gbc.gridx = 1;
        add(txtTelefono, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(btnRegistrar, gbc);

        gbc.gridy = 7;
        add(btnIniciarSesion, gbc);
    }

    public void registrarUsuario() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String email = txtEmail.getText();
        String telefono = txtTelefono.getText();
        String contraseña = txtContraseña.getText();
        try {
            if (nombre.length()>0 && apellido.length()>0 && email.length()>0 && telefono.length()>0 && contraseña.length()>0) {
                RegisterUser registerUser = new RegisterUser();
                registerUser.setId(null);
                registerUser.setName(nombre);
                registerUser.setSurname(apellido);
                registerUser.setEmail(email);
                registerUser.setTelephone(telefono);
                registerUser.setPassword(contraseña);
                JOptionPane.showMessageDialog(this, "Usuario " + nombre + " con contraseña " + contraseña + " registrado correctamente");
                new EscribirFichero().writeFileRegisterUser(registerUser);
                new registerUserController().registerUser("src/main/java/org/viajes/Frontend/Informacion/transportes.txt");
                dispose();
                new categoryController().chargeCategoryTable("src/main/java/org/viajes/Frontend/Informacion/categories.txt");
                new categoryController().chargeCategoryTable("src/main/java/org/viajes/Frontend/Informacion/transports.txt");
            } else if (nombre.length()==0 || apellido.length()==0 || email.length()==0 || telefono.length()==0 || contraseña.length()==0) {
                JOptionPane.showMessageDialog(this, "Debes introducir todos los datos");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void iniciarSesion() {
        JOptionPane.showMessageDialog(this, "Redirigiendo a la ventana de inicio de sesión...");
        new InicioSesion().setVisible(true); // Asegúrate de tener una clase VentanaLogin
        dispose(); // Cierra la ventana de registro
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Registro().setVisible(true);
            }
        });
    }

    private void registrar() {
        // Redirigir a la ventana de registro
        new Registro().setVisible(true);
        dispose();
    }
}
