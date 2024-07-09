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
    private JButton btnDesencriptar;
    private JButton btnPirateo;

    public static final Path RUTA= Paths.get("ficheros");
    public static final String ALFABETO= "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ123456789 !¡¿?$%&/()=><;:,.-_áéíóú";

    public SwingPrincipal() {
        btnGuardarMensaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!txtAreaMensaje.getText().isEmpty()) {
                    String nombreArchivo = JOptionPane.showInputDialog("Ingrese el nombre del archivo:");
                    if (!nombreArchivo.isEmpty()) {
                        new AdminArchivos(RUTA).escribe(txtAreaMensaje.getText(), nombreArchivo + ".txt");
                        lblArchivo.setText(nombreArchivo + ".txt");
                    }else{
                        JOptionPane.showMessageDialog(null,"Ingrese un nombre correcto","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Ingrese mensaje","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        btnEncriptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreArchivo = JOptionPane.showInputDialog("Ingrese el nombre del archivo:",lblArchivo.getText());
                if(nombreArchivo.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Ingrese el nombre del archivo","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                boolean isFile = new Validador().isFileExists(RUTA.resolve(nombreArchivo));

                if(!isFile) {
                    JOptionPane.showMessageDialog(null,"El archivo no existe","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                }else {
                    String clave = JOptionPane.showInputDialog("Ingrese la clave");
                    if(clave.isEmpty()){
                        JOptionPane.showMessageDialog(null,"Ingrese la clave","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    boolean digito=new Validador().isValidarClave(clave);
                    if (digito) {

                        String mensaje = new AdminArchivos(RUTA).lee(nombreArchivo);
                        String mensajeEncriptado = new Cifrar(ALFABETO).encriptar(mensaje, Integer.parseInt(clave));

                        String archivoEncriptado = nombreArchivo.replaceFirst("\\.", "_encriptado.");

                        new AdminArchivos(RUTA).escribe(mensajeEncriptado, archivoEncriptado);
                        lblarchivoEncriptado.setText(archivoEncriptado);

                        new AdminArchivos(RUTA).escribeProperty(clave);
                        lblClave.setText(clave);

                        txtAreaMensaje.setText(mensajeEncriptado);
                    }else{
                        JOptionPane.showMessageDialog(null,"Ingrese una clave correcta","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        btnDesencriptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreArchivo=JOptionPane.showInputDialog("Ingrese nombre del archivo para desencriptar",lblarchivoEncriptado.getText());
                if(nombreArchivo.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Ingrese el nombre del archivo","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                boolean isFileEncriptado = new Validador().isFileExists(RUTA.resolve(nombreArchivo));
                Boolean isFileClave=new Validador().isFileExists(RUTA.resolve("clave.property"));

                if(isFileEncriptado&&isFileClave) {
                    String clave = new AdminArchivos(RUTA).leeProperty();

                    String claveDesencriptar = JOptionPane.showInputDialog("Ingrese la clave para desencriptar", clave);
                    if(claveDesencriptar.isEmpty()){
                        JOptionPane.showMessageDialog(null,"Ingrese la clave para desencriptar","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    boolean digito=new Validador().isValidarClave(claveDesencriptar);
                    if (digito) {
                        String mensajeDesencriptado = new Cifrar(ALFABETO).desencriptar(txtAreaMensaje.getText(), Integer.parseInt(claveDesencriptar));
                        new AdminArchivos(RUTA).escribe(mensajeDesencriptado, nombreArchivo);
                        txtAreaMensaje.setText(mensajeDesencriptado);
                    }else{
                        JOptionPane.showMessageDialog(null,"Ingrese una clave correcta","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"El archivo encriptado o clave no existe","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnPirateo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreArchivo=JOptionPane.showInputDialog("Ingrese nombre del archivo");
                if(nombreArchivo.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Ingrese nombre del archivo","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                boolean isFile=new Validador().isFileExists(RUTA.resolve(nombreArchivo));
                if(isFile) {
                    txtAreaMensaje.setText(new Cifrar(ALFABETO).piratearClave(new AdminArchivos(RUTA).lee(nombreArchivo)));
                }else{
                    JOptionPane.showMessageDialog(null,"El archivo no existe","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                }
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
