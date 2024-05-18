package org.viajes.Frontend.Vistas;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerListener;

import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.text.IconView;



public class Extras extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private double precio = 0;
    static String transporte;
    String info;

    public static void mostrarContenido(String seleccion) {
        switch (seleccion) {
            case "Avión":
                transporte=seleccion;
                break;
            case "Tren":
                transporte=seleccion;
                break;
            case "Barco":
                transporte=seleccion;
                break;
            default:
                System.out.println("Selección no válida");
        }
    }

    public Extras(JPanel contentPane, boolean b, String info) {

        setBounds(700, 400, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWeights = new double[]{ 0.0, 0.0, Double.MIN_VALUE};
        gbl_contentPanel.rowWeights = new double[]{ 1.0, 1.0, 1.0, Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPanel);

        JLabel label1 = new JLabel("Elige tu extra: ");
        GridBagConstraints gridLabel = new GridBagConstraints();
        gridLabel.insets = new Insets(0, 0, 5, 5);
        gridLabel.gridx = 0;
        gridLabel.gridy = 0;
        contentPanel.add(label1, gridLabel);

        JComboBox<String> comboBox1 = new JComboBox<String>();

        if (transporte.equals("Avión")) {
            JRadioButton radioButton = new JRadioButton("Asiento Extra");
            GridBagConstraints gridButton = new GridBagConstraints();
            gridButton.insets = new Insets(0, 0, 5, 5);
            gridButton.gridx = 0;
            gridButton.gridy = 1;
            contentPanel.add(radioButton, gridButton);

            JRadioButton radioButton2 = new JRadioButton("Mascota en cabina");
            GridBagConstraints gridButton2 = new GridBagConstraints();
            gridButton2.insets = new Insets(0, 0, 5, 5);
            gridButton2.gridx = 1;
            gridButton2.gridy = 1;
            contentPanel.add(radioButton2, gridButton2);

            JRadioButton radioButton3 = new JRadioButton("Maleta Facturada");
            GridBagConstraints gridButton3 = new GridBagConstraints();
            gridButton3.insets = new Insets(0, 0, 5, 5);
            gridButton3.gridx = 0;
            gridButton3.gridy = 2;
            contentPanel.add(radioButton3, gridButton3);
            if (radioButton.isSelected()) {

            } else if (radioButton2.isSelected()) {

            } else if (radioButton3.isSelected()) {

            }
        } else if (transporte.equals("Tren")) {
            JRadioButton radioButton = new JRadioButton("Asiento XL Confort");
            GridBagConstraints gridButton = new GridBagConstraints();
            gridButton.insets = new Insets(0, 0, 5, 5);
            gridButton.gridx = 0;
            gridButton.gridy = 1;
            contentPanel.add(radioButton, gridButton);

            JRadioButton radioButton2 = new JRadioButton("Mascotas");
            GridBagConstraints gridButton2 = new GridBagConstraints();
            gridButton2.insets = new Insets(0, 0, 5, 5);
            gridButton2.gridx = 1;
            gridButton2.gridy = 1;
            contentPanel.add(radioButton2, gridButton2);

            JRadioButton radioButton3 = new JRadioButton("Menú Premium");
            GridBagConstraints gridButton3 = new GridBagConstraints();
            gridButton3.insets = new Insets(0, 0, 5, 5);
            gridButton3.gridx = 0;
            gridButton3.gridy = 2;
            contentPanel.add(radioButton3, gridButton3);
            if (radioButton.isSelected()) {

            } else if (radioButton2.isSelected()) {

            } else if (radioButton3.isSelected()) {

            }
        } else if (transporte.equals("Barco")) {
            JRadioButton radioButton = new JRadioButton("Salón Y Terrazas Privados");
            GridBagConstraints gridButton = new GridBagConstraints();
            gridButton.insets = new Insets(0, 0, 5, 5);
            gridButton.gridx = 0;
            gridButton.gridy = 1;
            contentPanel.add(radioButton, gridButton);

            JRadioButton radioButton2 = new JRadioButton("Butacas Extra Cómodas");
            GridBagConstraints gridButton2 = new GridBagConstraints();
            gridButton2.insets = new Insets(0, 0, 5, 5);
            gridButton2.gridx = 1;
            gridButton2.gridy = 1;
            contentPanel.add(radioButton2, gridButton2);

            JRadioButton radioButton3 = new JRadioButton("Zona premium mascotas");
            GridBagConstraints gridButton3 = new GridBagConstraints();
            gridButton3.insets = new Insets(0, 0, 5, 5);
            gridButton3.gridx = 0;
            gridButton3.gridy = 2;
            contentPanel.add(radioButton3, gridButton3);
            if (radioButton.isSelected()) {

            } else if (radioButton2.isSelected()) {

            } else if (radioButton3.isSelected()) {

            }
        }
        JButton button = new JButton("Enviar Datos");
        GridBagConstraints grid1 = new GridBagConstraints();
        grid1.insets = new Insets(0, 0, 5, 5);
        grid1.gridx = 1;
        grid1.gridy = 2;
        contentPanel.add(button, grid1);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JOptionPane.showConfirmDialog(contentPane, "El precio final es de " + precio + "€\n", info, JOptionPane.DEFAULT_OPTION);
                dispose();
            }
        });
    }
}