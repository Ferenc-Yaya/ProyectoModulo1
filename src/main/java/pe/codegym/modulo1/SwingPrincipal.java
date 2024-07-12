package pe.codegym.modulo1;

import pe.codegym.modulo1.strategy.ContextoValidar;
import pe.codegym.modulo1.strategy.EstrategiaValidar;
import pe.codegym.modulo1.factorymethod.FabricaConcretaValidar;
import pe.codegym.modulo1.factorymethod.FabricaValidar;
import pe.codegym.modulo1.strategy.ValidarNumero;
import pe.codegym.modulo1.strategy.ValidarVacio;

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

    public SwingPrincipal() {
        btnGuardarMensaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!valida(txtAreaMensaje.getText(), Errores.CAMPO_VACIO.toString())) return;//sin enum seria "campoVacio"

                String nombreArchivo = JOptionPane.showInputDialog("Ingrese el nombre del archivo:");
                if(!valida(nombreArchivo, Errores.CAMPO_VACIO.toString())) return;

                new AdminArchivos(RUTA).escribe(txtAreaMensaje.getText(), nombreArchivo + ".txt");
                lblArchivo.setText(nombreArchivo + ".txt");
            }
        });


        btnEncriptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreArchivo = JOptionPane.showInputDialog("Ingrese el nombre del archivo:",lblArchivo.getText());
                if(!valida(nombreArchivo,Errores.CAMPO_VACIO.toString()))return;
                if(!valida(RUTA.resolve(nombreArchivo).toString(),Errores.ARCHIVO_NO_ENCONTRADO.toString()))return;

                String clave = JOptionPane.showInputDialog("Ingrese la clave");
                if(!valida(clave,Errores.CAMPO_VACIO.toString()))return;
                if(!valida(clave,Errores.CLAVE_NO_NUMERO.toString()))return;

                String mensaje = new AdminArchivos(RUTA).lee(nombreArchivo);
                String mensajeEncriptado = new Cifrar().encriptar(mensaje, Integer.parseInt(clave));
                String archivoEncriptado = nombreArchivo.replaceFirst("\\.", "_encriptado.");

                //guardo el archivo
                new AdminArchivos(RUTA).escribe(mensajeEncriptado, archivoEncriptado);
                new AdminArchivos(RUTA).escribeProperty(clave);

                lblarchivoEncriptado.setText(archivoEncriptado);
                lblClave.setText(clave);
                txtAreaMensaje.setText(mensajeEncriptado);
            }
        });

        btnDesencriptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreArchivo = JOptionPane.showInputDialog("Ingrese nombre del archivo para desencriptar", lblarchivoEncriptado.getText());
                if (!valida(nombreArchivo, Errores.CAMPO_VACIO.toString())) return;
                if (!valida(RUTA.resolve(nombreArchivo).toString(), Errores.ARCHIVO_NO_ENCONTRADO.toString())) return;
                if (!valida(RUTA.resolve("clave.property").toString(), Errores.ARCHIVO_NO_ENCONTRADO.toString())) return;


                String clave = new AdminArchivos(RUTA).leeProperty();
                String claveDesencriptar = JOptionPane.showInputDialog("Ingrese la clave para desencriptar", clave);
                if (!valida(claveDesencriptar, Errores.CAMPO_VACIO.toString())) return;
                if (!valida(claveDesencriptar, Errores.CLAVE_NO_NUMERO.toString())) return;


                String mensajeDesencriptado = new Cifrar().desencriptar(
                        new AdminArchivos(RUTA).lee(nombreArchivo), Integer.parseInt(claveDesencriptar));


                new AdminArchivos(RUTA).escribe(mensajeDesencriptado, nombreArchivo);

                txtAreaMensaje.setText("");
                txtAreaMensaje.setText(mensajeDesencriptado);
            }
        });

        btnPirateo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreArchivo = JOptionPane.showInputDialog("Ingrese nombre del archivo");
                if (!valida(nombreArchivo, Errores.CAMPO_VACIO.toString())) return;
                if (!valida(RUTA.resolve(nombreArchivo).toString(), Errores.ARCHIVO_NO_ENCONTRADO.toString())) return;


                txtAreaMensaje.setText(
                        new Cifrar().piratearClave(
                                new AdminArchivos(RUTA).lee(nombreArchivo)));

            }
        });
    }

    private boolean valida(String text, String tipoValidar) {
        boolean respuesta = true;
//        FabricaValidar fabricaValidar = new FabricaConcretaValidar();
//        EstrategiaValidar estrategiaValidar = fabricaValidar.crearEstrategiaValidar(tipoValidar);

//        ContextoValidar contextValidar= new ContextoValidar(estrategiaValidar);
//        Boolean a =contextValidar.validarCadena(text);

        Boolean a = new ContextoValidar(new FabricaConcretaValidar().crearEstrategiaValidar(tipoValidar)).validarCadena(text);

        if(!a) {
            Errores errores= Errores.valueOf(tipoValidar);
            JOptionPane.showMessageDialog(null, errores.getMensaje(), "Mensaje", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return respuesta;
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Proyecto cifrado César");
        frame.setContentPane(new SwingPrincipal().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        //el formulario aparece en el centro
        frame.setLocationRelativeTo(null);
        //el formulario no se puede modificar de tamaño
        frame.setResizable(false);

        //muestra el formulario
        frame.setVisible(true);
    }
}


