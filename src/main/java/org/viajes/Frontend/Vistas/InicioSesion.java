package org.viajes.Frontend.Vistas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InicioSesion extends JFrame{
    private JFrame frame;
    private JLabel userLabel;
    private JLabel emailLabel;
    private String info;
    // Método principal en el que manejamos las posibles excepciones
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    InicioSesion user = new InicioSesion();
                    user.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public InicioSesion() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel userPanel = new JPanel();
        frame.add(userPanel);
        placeComponents(userPanel);

        frame.setVisible(true);
    }

    // En este método, creamos los diferentes componentes para registrarse: nombre, email
    private static void placeComponents(JPanel userPanel) {
        userPanel.setLayout(null);

        JLabel userLabel = new JLabel("Name");
        userLabel.setBounds(10, 10, 80, 25);
        userPanel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        userPanel.add(userText);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(10, 40, 80, 25);
        userPanel.add(emailLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 160, 25);
        userPanel.add(passwordText);

        JButton registerButton = new JButton("¿Aún no te has registrado?");
        registerButton.setBounds(10, 80, 250, 25);
        userPanel.add(registerButton);

        JButton sendButton = new JButton("ENVIAR");
        sendButton.setBounds(10, 110, 250, 25);
        userPanel.add(sendButton);

        // Esto agrega una acción al pulsar el botón de registro
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mostrarVentanaRegistro();
            }
        });

        // Esto agrega una acción al pulsar el botón de enviar
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // Lógica de inicio de sesión
                String usuario = userText.getText();
                char[] contrasena = passwordText.getPassword();

                // Aquí puedes añadir la lógica para verificar el usuario y la contraseña
                JOptionPane.showMessageDialog(userPanel, "Iniciando sesión para " + usuario);
            }
        });
    }


    private boolean comprobarCampos() {
        boolean esValido=true;
        //Comprobamos que los datos se encuentren. Para ello, se lo mandamos al controlador
        if (!userLabel.getText().isEmpty()) {
            info += "Name,";

        } else if (!emailLabel.getText().isEmpty()) {
            info += "Email,";

        } else {
            esValido=false;
            JOptionPane.showMessageDialog(frame, "Debes introducir los datos correctos");
        }
        return esValido && true;

    }

    private static void mostrarVentanaRegistro() {
        // Mostramos la ventana de registro
        Registro ventanaRegistro = new Registro();
        ventanaRegistro.setVisible(true);
    }

}