package org.viajes.Frontend.Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Registro extends JFrame {
    //Ponemos los atributos
    private JFrame frame;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtFechaNacimiento;
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
        JLabel lblId = new JLabel("ID:");
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblApellido = new JLabel("Apellido:");
        JLabel lblFechaNacimiento = new JLabel("Fecha de Nacimiento (dd-MM-yyyy):");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblTelefono = new JLabel("Teléfono:");

        txtId = new JTextField(15);
        txtNombre = new JTextField(15);
        txtApellido = new JTextField(15);
        txtFechaNacimiento = new JTextField(15);
        txtEmail = new JTextField(15);
        txtTelefono = new JTextField(15);
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
        gbc.gridy = 0;
        add(lblId, gbc);

        gbc.gridx = 1;
        add(txtId, gbc);

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
        add(lblFechaNacimiento, gbc);

        gbc.gridx = 1;
        add(txtFechaNacimiento, gbc);

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

    private void registrarUsuario() {
        int id = Integer.parseInt(txtId.getText());
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String fechaNacimientoStr = txtFechaNacimiento.getText();
        String email = txtEmail.getText();
        String telefono = txtTelefono.getText();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaNacimiento = null;
        try {
            fechaNacimiento = sdf.parse(fechaNacimientoStr);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha no válido. Debe ser dd-MM-yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Enseñamos un mensaje de que el usuario se ha registrado correctamente
        JOptionPane.showMessageDialog(this, "Usuario " + nombre + " " + apellido + " registrado correctamente");
    }

    private void iniciarSesion() {
        JOptionPane.showMessageDialog(this, "Redirigiendo a la ventana de inicio de sesión...");
        new InicioSesion().setVisible(true); // Asegúrate de tener una clase VentanaLogin
        dispose(); // Cierra la ventana de registro
    }

    private boolean comprobarCampos() {
        boolean esValido=true;
        //Comprobamos que los datos se encuentren. Para ello, se lo mandamos al controlador
        if (!txtId.toString().isEmpty()) {
            String infoEnviar = "Id,";
        } else if (!txtNombre.getText().isEmpty()) {
            String infoEnviar = "Name,";
        } else if (!txtApellido.getText().isEmpty()) {
            String infoEnviar = "Surname,";
        } else if (!txtFechaNacimiento.getText().isEmpty()) {
            String infoEnviar = "Bithday";
        } else if (!txtEmail.getText().isEmpty()) {
            String infoEnviar = "Email,";
        } else if (!txtTelefono.getText().isEmpty()) {
            String infoEnviar = "Phone,";

        } else {
            esValido=false;
            JOptionPane.showMessageDialog(frame, "Debes introducir los datos correctos");
        }
        return esValido && true;

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
