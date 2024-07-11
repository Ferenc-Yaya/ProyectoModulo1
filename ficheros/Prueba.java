import javax.swing.JOptionPane;

import javafx.event.ActionEvent;

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
                //valida vacio
                if(!txtAreaMensaje.getText().isEmpty()) {
                    String nombreArchivo = JOptionPane.showInputDialog("Ingrese el nombre del archivo:");
                    //valida vacio
                    if (!nombreArchivo.isEmpty()) {
                        //guarda el txt
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
                //valida vacio
                if(nombreArchivo.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Ingrese el nombre del archivo","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                //valida si existe el archivo
                boolean isFile = new Validador().isFileExists(RUTA.resolve(nombreArchivo));

                if(!isFile) {
                    JOptionPane.showMessageDialog(null,"El archivo no existe","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                }else {
                    String clave = JOptionPane.showInputDialog("Ingrese la clave");
                    //valido vacio
                    if(clave.isEmpty()){
                        JOptionPane.showMessageDialog(null,"Ingrese la clave","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    //valido si es digito negativo o positivo
                    boolean digito=new Validador().isValidarClave(clave);
                    if (digito) {
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
                //valido vacio
                if(nombreArchivo.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Ingrese el nombre del archivo","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                boolean isFileEncriptado = new Validador().isFileExists(RUTA.resolve(nombreArchivo));
                Boolean isFileClave=new Validador().isFileExists(RUTA.resolve("clave.property"));
                //valido si existe los archivo
                if(isFileEncriptado&&isFileClave) {
                    //leo Property
                    String clave = new AdminArchivos(RUTA).leeProperty();
                    //paso la clave al formulario
                    String claveDesencriptar = JOptionPane.showInputDialog("Ingrese la clave para desencriptar", clave);
                    //valido vacio
                    if(claveDesencriptar.isEmpty()){
                        JOptionPane.showMessageDialog(null,"Ingrese la clave para desencriptar","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    //valido si es digito negativo o positivo
                    boolean digito=new Validador().isValidarClave(claveDesencriptar);
                    if (digito) {
                        //desencriptar el mensaje
                        String mensajeDesencriptado = new Cifrar(ALFABETO).desencriptar(
                                new AdminArchivos(RUTA).lee(nombreArchivo), Integer.parseInt(claveDesencriptar));
                        //guarda ne _encriptado.txt
                        new AdminArchivos(RUTA).escribe(mensajeDesencriptado, nombreArchivo);

                        txtAreaMensaje.setText("");
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
                //valido vacio
                if(nombreArchivo.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Ingrese nombre del archivo","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                boolean isFile=new Validador().isFileExists(RUTA.resolve(nombreArchivo));
                //valido si existe el archivo
                if(isFile) {
                    //busco la clave con el metodo pirateo
                    txtAreaMensaje.setText(
                            new Cifrar(ALFABETO).piratearClave(
                                    new AdminArchivos(RUTA).lee(nombreArchivo)));
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
        //el formulario aparece en el centro
        frame.setLocationRelativeTo(null);
        //el formulario no se puede modificar de tamaño
        frame.setResizable(false);

        //muestra el formulario
        frame.setVisible(true);
    }