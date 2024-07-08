package pe.codegym.modulo1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SwingPrincipal {
    private JButton btnGuardarMensaje;
    private JPanel mainPanel;
    private JTextArea txtAreaMensaje;
    private JButton btnEncriptar;
    private JLabel lblArchivo;
    private JLabel lblarchivoEncriptado;
    private JLabel lblClave;
    private JButton btnBurcarArchivoEncriptado;
    private JButton btnDesencriptar;

    public static final Path RUTA= Paths.get("ficheros");
    public static final String ALFABETO= new AdminArchivos(RUTA).lee("alfabeto.property");



    public SwingPrincipal() {
        btnGuardarMensaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!txtAreaMensaje.getText().isEmpty()) {
                    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del archivo:");
                    if (nombre != null) {
                        new AdminArchivos(RUTA).escribe(txtAreaMensaje.getText(), nombre + ".txt");
                        lblArchivo.setText(nombre + ".txt");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Ingrese mensaje","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        btnEncriptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String clave = null;
                boolean isFile=false;
                if(!lblArchivo.getText().isEmpty()) {
                    isFile = new Validador().isFileExists(RUTA.resolve(lblArchivo.getText()));
                }
                if(!isFile) {
                    JOptionPane.showMessageDialog(null,"El archivo no existe","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                }else {
                    clave = JOptionPane.showInputDialog("Ingrese la clave");
                    if (clave != null) {

                        String mensaje = new AdminArchivos(RUTA).lee(lblArchivo.getText());
                        String mensajeEncriptado = new Cifrar(ALFABETO).encriptar(mensaje, Integer.parseInt(clave));

                        String archivoEncriptado = lblArchivo.getText().replaceFirst("\\.", "_encriptado.");

                        new AdminArchivos(RUTA).escribe(mensajeEncriptado, archivoEncriptado);
                        lblarchivoEncriptado.setText(archivoEncriptado);

                        new AdminArchivos(RUTA).escribe(clave, "clave.property");
                        lblClave.setText(clave);
                    }
                }
            }
        });
        btnBurcarArchivoEncriptado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreArchivo=JOptionPane.showInputDialog("Ingrese nombre del archivo para desencriptar",lblarchivoEncriptado.getText());
                if(nombreArchivo!=null) {
                    String archivoEncriptado = new AdminArchivos(RUTA).lee(nombreArchivo);
                    txtAreaMensaje.setText(archivoEncriptado);
                    String clave = new AdminArchivos(RUTA).lee(nombreArchivo);
                }
            }
        });
        btnDesencriptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String clave=new AdminArchivos(RUTA).lee("clave.property");
                String claveDesencriptar=JOptionPane.showInputDialog("Ingrese la clave para desencriptar",clave);
                String mensajeDesencriptado=new Cifrar(ALFABETO).desencriptar(txtAreaMensaje.getText(),Integer.parseInt(claveDesencriptar));
                new AdminArchivos(RUTA).escribe(mensajeDesencriptado,lblarchivoEncriptado.getText());
                txtAreaMensaje.setText(mensajeDesencriptado);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Proyecto cifrado César");
        frame.setContentPane(new SwingPrincipal().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.setVisible(true);
    }
}
