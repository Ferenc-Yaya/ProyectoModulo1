package pe.codegym.modulo1;

import pe.codegym.modulo1.strategy.ContextoValidar;
import pe.codegym.modulo1.strategy.EstrategiaValidar;
import pe.codegym.modulo1.factorymethod.FabricaConcretaValidar;
import pe.codegym.modulo1.factorymethod.FabricaValidar;

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

                if(!valida(txtAreaMensaje.getText(), Errores.CAMPO_VACIO.toString())) return;
                String nombreArchivo = JOptionPane.showInputDialog("Ingrese el nombre del archivo:");
                if(!valida(nombreArchivo, Errores.CAMPO_VACIO.toString())) return;
                if(!valida(nombreArchivo, Errores.TXT_NO_VALIDO.toString())) return;
                new AdminArchivos(RUTA).escribe(txtAreaMensaje.getText(), nombreArchivo + ".txt");
                lblArchivo.setText(nombreArchivo + ".txt");
            }
        });
        btnEncriptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreArchivo = JOptionPane.showInputDialog("Ingrese el nombre del archivo:",lblArchivo.getText());
                //valida vacio
                if(!valida(nombreArchivo,Errores.CAMPO_VACIO.toString()))return;
                //valida si existe el archivo
                if(!valida(RUTA.resolve(nombreArchivo).toString(),Errores.ARCHIVO_NO_ENCONTRADO.toString()))return;

                String clave = JOptionPane.showInputDialog("Ingrese la clave");
                //valida vacio
                if(!valida(clave,Errores.CAMPO_VACIO.toString()))return;
                //valido si es digito negativo o positivo
                if(!valida(clave,Errores.CLAVE_NO_NUMERO.toString()))return;
                //busco el archivo
                String mensaje = new AdminArchivos(RUTA).lee(nombreArchivo);
                //encripto el archivo
                String mensajeEncriptado = new Cifrar(ALFABETO).encriptar(mensaje, Integer.parseInt(clave));
                //creo otro archivo con los datos encriptados
                String archivoEncriptado = nombreArchivo.replaceFirst("\\.", "_encriptado.");
                //guardo el archivo
                new AdminArchivos(RUTA).escribe(mensajeEncriptado, archivoEncriptado);
                lblarchivoEncriptado.setText(archivoEncriptado);
                //guardo el archivo property
                new AdminArchivos(RUTA).escribeProperty(clave);
                lblClave.setText(clave);
                txtAreaMensaje.setText(mensajeEncriptado);
            }
        });

        btnDesencriptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreArchivo = JOptionPane.showInputDialog("Ingrese nombre del archivo para desencriptar", lblarchivoEncriptado.getText());
                //valida vacio
                if (!valida(nombreArchivo, Errores.CAMPO_VACIO.toString())) return;
                if (!valida(RUTA.resolve(nombreArchivo).toString(), Errores.ARCHIVO_NO_ENCONTRADO.toString())) return;
                if (!valida(RUTA.resolve("clave.property").toString(), Errores.ARCHIVO_NO_ENCONTRADO.toString())) return;
                //leo Property
                String clave = new AdminArchivos(RUTA).leeProperty();
                //paso la clave al formulario
                String claveDesencriptar = JOptionPane.showInputDialog("Ingrese la clave para desencriptar", clave);
                //valido vacio
                if (!valida(claveDesencriptar, Errores.CAMPO_VACIO.toString())) return;
                //valido si es digito negativo o positivo
                if (!valida(claveDesencriptar, Errores.CLAVE_NO_NUMERO.toString())) return;
                //desencriptar el mensaje
                String mensajeDesencriptado = new Cifrar(ALFABETO).desencriptar(
                        new AdminArchivos(RUTA).lee(nombreArchivo), Integer.parseInt(claveDesencriptar));
                //guarda ne _encriptado.txt
                new AdminArchivos(RUTA).escribe(mensajeDesencriptado, nombreArchivo);

                txtAreaMensaje.setText("");
                txtAreaMensaje.setText(mensajeDesencriptado);
            }
        });

        btnPirateo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreArchivo = JOptionPane.showInputDialog("Ingrese nombre del archivo");
                //valido vacio
                if (!valida(nombreArchivo, Errores.CAMPO_VACIO.toString())) return;
                if (!valida(RUTA.resolve(nombreArchivo).toString(), Errores.ARCHIVO_NO_ENCONTRADO.toString())) return;
                //busco la clave con el metodo pirateo
                txtAreaMensaje.setText(
                        new Cifrar(ALFABETO).piratearClave(
                                new AdminArchivos(RUTA).lee(nombreArchivo)));

            }
        });
    }

    private boolean valida(String text, String tipoValidar) {
        boolean respuesta=true;
        FabricaValidar fabricaValidar= new FabricaConcretaValidar();
        EstrategiaValidar estrategiaValidar=fabricaValidar.crearEstrategia(tipoValidar);
        ContextoValidar contextoValidar= new ContextoValidar(estrategiaValidar);

        if(!contextoValidar.validarCadena(text)) {
            Errores errores= Errores.valueOf(tipoValidar);
            JOptionPane.showMessageDialog(null, errores.getMensaje(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
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


