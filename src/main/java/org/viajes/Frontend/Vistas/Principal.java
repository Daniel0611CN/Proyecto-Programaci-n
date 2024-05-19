package org.viajes.Frontend.Vistas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Principal extends JFrame {

    private double precio = 0;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private String info = "";
    private JComboBox<String> comboBox1 = new JComboBox<String>();

    public String obtenerSeleccion() {
        return (String) comboBox1.getSelectedItem();

    }

    public static void main(String[] args) {
        Principal p1 = new Principal();

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal frame = new Principal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        String seleccion = p1.obtenerSeleccion();
    }

    public Principal() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(700, 400, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.LIGHT_GRAY);

        setContentPane(contentPane);
        GridBagLayout layout = new GridBagLayout();
        layout.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
        layout.columnWeights = new double[] { 1.0, 1.0 };

        contentPane.setLayout(layout);

        JLabel label1 = new JLabel("Seleccione el tipo de transporte: ");

        GridBagConstraints gridLabel = new GridBagConstraints();
        gridLabel.insets = new Insets(0, 0, 5, 5);
        gridLabel.gridx = 0;
        gridLabel.gridy = 0;
        gridLabel.gridwidth = 1;
        gridLabel.gridheight = 1;

        contentPane.add(label1, gridLabel);

        JLabel label2 = new JLabel("Elige tu origen: ");

        GridBagConstraints gridLabel2 = new GridBagConstraints();
        gridLabel2.insets = new Insets(0, 0, 5, 5);
        gridLabel2.gridx = 0;
        gridLabel2.gridy = 2;
        gridLabel2.gridwidth = 1;
        gridLabel2.gridheight = 1;

        contentPane.add(label2, gridLabel2);


        JLabel label3 = new JLabel("Elige tu destino: ");

        GridBagConstraints gridLabel3 = new GridBagConstraints();
        gridLabel3.insets = new Insets(0, 0, 5, 5);
        gridLabel3.gridx = 0;
        gridLabel3.gridy = 4;
        gridLabel3.gridwidth = 1;
        gridLabel3.gridheight = 1;

        contentPane.add(label3, gridLabel3);

        JLabel label4 = new JLabel("Tipo de categoría: ");

        GridBagConstraints gridLabel4 = new GridBagConstraints();
        gridLabel4.insets = new Insets(0, 0, 5, 5);
        gridLabel4.gridx = 1;
        gridLabel4.gridy = 0;
        gridLabel4.gridwidth = 1;
        gridLabel4.gridheight = 1;

        contentPane.add(label4, gridLabel4);


        JLabel label5 = new JLabel("Tipo de viaje: ");

        GridBagConstraints gridLabel5 = new GridBagConstraints();
        gridLabel5.insets = new Insets(0, 0, 5, 5);
        gridLabel5.gridx = 1;
        gridLabel5.gridy = 2;
        gridLabel5.gridwidth = 1;
        gridLabel5.gridheight = 1;

        contentPane.add(label5, gridLabel5);


        JLabel label6 = new JLabel("Número de pasajeros: ");

        GridBagConstraints gridLabel6 = new GridBagConstraints();
        gridLabel6.insets = new Insets(0, 0, 5, 5);
        gridLabel6.gridx = 1;
        gridLabel6.gridy = 4;
        gridLabel6.gridwidth = 1;
        gridLabel6.gridheight = 1;

        contentPane.add(label6, gridLabel6);


        GridBagConstraints gridBox = new GridBagConstraints();
        gridBox.insets = new Insets(0, 0, 5, 0);
        gridBox.fill = GridBagConstraints.HORIZONTAL;
        gridBox.gridx = 0;
        gridBox.gridy = 1;

        comboBox1.addItem("Seleccione el tipo de transporte");
        comboBox1.addItem("Avión");
        comboBox1.addItem("Tren");
        comboBox1.addItem("Barco");

        comboBox1.setBackground(Color.WHITE);
        comboBox1.setForeground(Color.BLACK);
        comboBox1.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));

        contentPane.add(comboBox1, gridBox);

        comboBox1.addActionListener(e -> {
            String seleccion = (String) comboBox1.getSelectedItem();
            Extras.mostrarContenido(seleccion);
        });

        JComboBox<String> comboBox2 = new JComboBox<String>();

        GridBagConstraints gridBox2 = new GridBagConstraints();
        gridBox2.insets = new Insets(0, 0, 5, 0);
        gridBox2.fill = GridBagConstraints.HORIZONTAL;
        gridBox2.gridx = 0;
        gridBox2.gridy = 3;
        comboBox2.setBackground(Color.WHITE);
        comboBox2.setForeground(Color.BLACK);
        comboBox2.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));

        comboBox1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                String seleccion = (String) comboBox1.getSelectedItem();

                comboBox1.updateUI();
                comboBox1.update(getGraphics());

                if (seleccion!=null && seleccion.equals("Avión")) {

                    comboBox2.removeAllItems();

                    comboBox2.addItem("Francia");
                    comboBox2.addItem("Londres");
                    comboBox2.addItem("Roma");
                    comboBox2.addItem("Valencia");
                    comboBox2.addItem("New York");
                    comboBox2.addItem("Viena");
                    comboBox2.addItem("Copenhague");
                    comboBox2.addItem("Berlín");
                    comboBox2.addItem("Camberra");
                    comboBox2.addItem("Pekín");

                    contentPane.add(comboBox2, gridBox2);

                } else if (seleccion!=null && seleccion.equals("Tren")) {

                    comboBox2.removeAllItems();

                    comboBox2.addItem("Francia");
                    comboBox2.addItem("Londres");
                    comboBox2.addItem("Roma");
                    comboBox2.addItem("Valencia");
                    comboBox2.addItem("New York");
                    comboBox2.addItem("Viena");
                    comboBox2.addItem("Copenhague");
                    comboBox2.addItem("Berlín");
                    comboBox2.addItem("Camberra");
                    comboBox2.addItem("Pekín");

                    contentPane.add(comboBox2, gridBox2);

                } else if (seleccion!=null && seleccion.equals("Barco")) {

                    comboBox2.removeAllItems();

                    comboBox2.addItem("Francia");
                    comboBox2.addItem("Londres");
                    comboBox2.addItem("Roma");
                    comboBox2.addItem("Valencia");
                    comboBox2.addItem("New York");
                    comboBox2.addItem("Viena");
                    comboBox2.addItem("Copenhague");
                    comboBox2.addItem("Berlín");
                    comboBox2.addItem("Camberra");
                    comboBox2.addItem("Pekín");

                    contentPane.add(comboBox2, gridBox2);

                }
            }
        });

        JComboBox<String> comboBox3 = new JComboBox<String>();

        GridBagConstraints gridBox3 = new GridBagConstraints();
        gridBox3.insets = new Insets(0, 0, 5, 0);
        gridBox3.fill = GridBagConstraints.HORIZONTAL;
        gridBox3.gridx = 0;
        gridBox3.gridy = 5;
        comboBox3.setBackground(Color.WHITE);
        comboBox3.setForeground(Color.BLACK);
        comboBox3.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));

        JComboBox<String> comboBox4 = new JComboBox<String>();

        GridBagConstraints gridBox4 = new GridBagConstraints();
        gridBox4.insets = new Insets(0, 0, 5, 0);
        gridBox4.fill = GridBagConstraints.HORIZONTAL;
        gridBox4.gridx = 1;
        gridBox4.gridy = 1;
        comboBox4.setBackground(Color.WHITE);
        comboBox4.setForeground(Color.BLACK);
        comboBox4.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));

        comboBox2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                String seleccion = (String) comboBox2.getSelectedItem();

                if (seleccion!=null && seleccion.equals("Francia")) {

                    comboBox3.removeAllItems();

                    comboBox3.addItem("Londres");
                    comboBox3.addItem("Roma");
                    comboBox3.addItem("Valencia");
                    comboBox3.addItem("New York");
                    comboBox3.addItem("Viena");
                    comboBox3.addItem("Copenhague");
                    comboBox3.addItem("Berlín");
                    comboBox3.addItem("Camberra");
                    comboBox3.addItem("Pekín");

                    contentPane.add(comboBox3, gridBox3);

                } else if (seleccion!=null && seleccion.equals("Londres")) {

                    comboBox3.removeAllItems();

                    comboBox3.addItem("Francia");
                    comboBox3.addItem("Roma");
                    comboBox3.addItem("Valencia");
                    comboBox3.addItem("New York");
                    comboBox3.addItem("Viena");
                    comboBox3.addItem("Copenhague");
                    comboBox3.addItem("Berlín");
                    comboBox3.addItem("Camberra");
                    comboBox3.addItem("Pekín");

                    contentPane.add(comboBox3, gridBox3);

                } else if (seleccion!=null && seleccion.equals("Roma")) {

                    comboBox3.removeAllItems();

                    comboBox3.addItem("Francia");
                    comboBox3.addItem("Londres");
                    comboBox3.addItem("Valencia");
                    comboBox3.addItem("New York");
                    comboBox3.addItem("Viena");
                    comboBox3.addItem("Copenhague");
                    comboBox3.addItem("Berlín");
                    comboBox3.addItem("Camberra");
                    comboBox3.addItem("Pekín");

                    contentPane.add(comboBox3, gridBox3);

                } else if (seleccion!=null && seleccion.equals("Valencia")) {

                    comboBox3.removeAllItems();

                    comboBox3.addItem("Francia");
                    comboBox3.addItem("Londres");
                    comboBox3.addItem("Roma");
                    comboBox3.addItem("New York");
                    comboBox3.addItem("Viena");
                    comboBox3.addItem("Copenhague");
                    comboBox3.addItem("Berlín");
                    comboBox3.addItem("Camberra");
                    comboBox3.addItem("Pekín");

                    contentPane.add(comboBox3, gridBox3);

                } else if (seleccion!=null && seleccion.equals("New York")) {

                    comboBox3.removeAllItems();

                    comboBox3.addItem("Francia");
                    comboBox3.addItem("Londres");
                    comboBox3.addItem("Roma");
                    comboBox3.addItem("Valencia");
                    comboBox3.addItem("Viena");
                    comboBox3.addItem("Copenhague");
                    comboBox3.addItem("Berlín");
                    comboBox3.addItem("Camberra");
                    comboBox3.addItem("Pekín");

                    contentPane.add(comboBox3, gridBox3);

                } else if (seleccion!=null && seleccion.equals("Viena")) {

                    comboBox3.removeAllItems();

                    comboBox3.addItem("Francia");
                    comboBox3.addItem("Londres");
                    comboBox3.addItem("Roma");
                    comboBox3.addItem("Valencia");
                    comboBox3.addItem("New York");
                    comboBox3.addItem("Copenhague");
                    comboBox3.addItem("Berlín");
                    comboBox3.addItem("Camberra");
                    comboBox3.addItem("Pekín");

                    contentPane.add(comboBox3, gridBox3);

                } else if (seleccion!=null && seleccion.equals("Copenhague")) {

                    comboBox3.removeAllItems();

                    comboBox3.addItem("Francia");
                    comboBox3.addItem("Londres");
                    comboBox3.addItem("Roma");
                    comboBox3.addItem("Valencia");
                    comboBox3.addItem("New York");
                    comboBox3.addItem("Viena");
                    comboBox3.addItem("Berlín");
                    comboBox3.addItem("Camberra");
                    comboBox3.addItem("Pekín");

                    contentPane.add(comboBox3, gridBox3);

                } else if (seleccion!=null && seleccion.equals("Berlín")) {

                    comboBox3.removeAllItems();

                    comboBox3.addItem("Francia");
                    comboBox3.addItem("Londres");
                    comboBox3.addItem("Roma");
                    comboBox3.addItem("Valencia");
                    comboBox3.addItem("New York");
                    comboBox3.addItem("Viena");
                    comboBox3.addItem("Copenhague");
                    comboBox3.addItem("Camberra");
                    comboBox3.addItem("Pekín");

                    contentPane.add(comboBox3, gridBox3);

                } else if (seleccion!=null && seleccion.equals("Camberra")) {

                    comboBox3.removeAllItems();

                    comboBox3.addItem("Francia");
                    comboBox3.addItem("Londres");
                    comboBox3.addItem("Roma");
                    comboBox3.addItem("Valencia");
                    comboBox3.addItem("New York");
                    comboBox3.addItem("Viena");
                    comboBox3.addItem("Copenhague");
                    comboBox3.addItem("Berlín");
                    comboBox3.addItem("Pekín");

                    contentPane.add(comboBox3, gridBox3);

                } else if (seleccion!=null && seleccion.equals("Pekín")) {

                    comboBox3.removeAllItems();

                    comboBox3.addItem("Francia");
                    comboBox3.addItem("Londres");
                    comboBox3.addItem("Roma");
                    comboBox3.addItem("Valencia");
                    comboBox3.addItem("New York");
                    comboBox3.addItem("Viena");
                    comboBox3.addItem("Copenhague");
                    comboBox3.addItem("Berlín");
                    comboBox3.addItem("Camberra");

                    contentPane.add(comboBox3, gridBox3);

                }
                contentPane.revalidate();
                seleccion = (String) comboBox2.getSelectedItem();
                contentPane.repaint();
            }
        });


        comboBox1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String seleccion = (String) comboBox1.getSelectedItem();

                comboBox1.update(getGraphics());
                comboBox1.updateUI();

                if (seleccion.equals("Avión")) {

                    comboBox4.removeAllItems();

                    comboBox4.addItem("First Class");
                    comboBox4.addItem("Premium Class");
                    comboBox4.addItem("Executive Class");
                    comboBox4.addItem("Tourism Class");

                    contentPane.add(comboBox4, gridBox4);

                } else if (seleccion.equals("Tren")) {

                    comboBox4.removeAllItems();

                    comboBox4.addItem("Cabin Class");
                    comboBox4.addItem("Seat Class");

                    contentPane.add(comboBox4, gridBox4);

                } else if (seleccion.equals("Barco")) {

                    comboBox4.removeAllItems();

                    comboBox4.addItem("Preferent Class");
                    comboBox4.addItem("Tourist Class");

                    contentPane.add(comboBox4, gridBox4);
                }

            }
        });



        JComboBox<String> comboBox5 = new JComboBox<String>();

        GridBagConstraints gridBox5 = new GridBagConstraints();
        gridBox5.insets = new Insets(0, 0, 5, 0);
        gridBox5.fill = GridBagConstraints.HORIZONTAL;
        gridBox5.gridx = 1;
        gridBox5.gridy = 3;
        comboBox5.setBackground(Color.WHITE);
        comboBox5.setForeground(Color.BLACK);
        comboBox5.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));

        comboBox5.addItem("Ida");
        comboBox5.addItem("Ida Y Vuelta");

        if (comboBox5.getSelectedItem().equals("Ida Y Vuelta")) {
            precio = precio*2;
        }

        contentPane.add(comboBox5, gridBox5);


        JComboBox<String> comboBox6 = new JComboBox<String>();
        GridBagConstraints gridBox6 = new GridBagConstraints();
        gridBox6.insets = new Insets(0, 0, 5, 0);
        gridBox6.fill = GridBagConstraints.HORIZONTAL;
        gridBox6.gridx = 1;
        gridBox6.gridy = 5;
        comboBox6.setBackground(Color.WHITE);
        comboBox6.setForeground(Color.BLACK);
        comboBox6.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));

        comboBox6.addItem("1");
        comboBox6.addItem("2");
        comboBox6.addItem("3");
        comboBox6.addItem("4");
        comboBox6.addItem("5");
        comboBox6.addItem("6");
        comboBox6.addItem("7");
        comboBox6.addItem("8");

        if (comboBox6.getSelectedItem().equals("2")) {
            precio = precio*2;
        }
        if (comboBox6.getSelectedItem().equals("3")) {
            precio = precio*3;
        }
        if (comboBox6.getSelectedItem().equals("4")) {
            precio = precio*4;
        }
        if (comboBox6.getSelectedItem().equals("5")) {
            precio = precio*5;
        }
        if (comboBox6.getSelectedItem().equals("6")) {
            precio = precio*6;
        }
        if (comboBox6.getSelectedItem().equals("7")) {
            precio = precio*7;
        }
        if (comboBox6.getSelectedItem().equals("8")) {
            precio = precio*8;
        }
        contentPane.add(comboBox6, gridBox6);


        JButton button1 = new JButton();
        GridBagConstraints gridButton = new GridBagConstraints();
        button1.setText("Enviar");
        gridButton.insets = new Insets(0, 0, 5, 5);
        gridButton.gridx = 1;
        gridButton.gridy = 6;
        contentPane.add(button1, gridButton);
        button1.setBackground(Color.BLUE);
        button1.setForeground(Color.WHITE);
        button1.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
        button1.setFocusPainted(true);

        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (comboBox1.getSelectedItem()==null ||
                        comboBox2.getSelectedItem()==null ||
                        comboBox3.getSelectedItem()==null ||
                        comboBox4.getSelectedItem()==null ||
                        comboBox5.getSelectedItem()==null ||
                        comboBox6.getSelectedItem()==null)
                {
                    JOptionPane.showMessageDialog(contentPane, "Rellene todas las opciones");
                } else {
                    JOptionPane.showConfirmDialog(contentPane, "Valores registrados con éxito", info, JOptionPane.DEFAULT_OPTION, ICONIFIED);
                    int option = JOptionPane.showConfirmDialog(contentPane, "¿Deseas añadir algún extra?",
                            info, JOptionPane.YES_NO_OPTION);
                    dispose();
                    if (option == 0) {
                        Extras panelExtra = new Extras(contentPane, true, info);
                        panelExtra.setVisible(true);

                    } else {
                        JOptionPane.showConfirmDialog(contentPane, "El precio final es de " + precio + "€\n", info, JOptionPane.DEFAULT_OPTION);
                        dispose();
                    }
                }
//					else {
//						String resultado = controlador.escribirInfo(info);
//						JOptionPane.showMessageDialog(contentPane, resultado.concat(".El precio total es:").concat(String.valueOf(precio).concat("€")));
//					}
//			}
            }
            public String valueString() {
                return (String) comboBox1.getSelectedItem();
            }
        });
    }
}