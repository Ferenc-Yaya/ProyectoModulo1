import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingPrincipal {
    private JButton btnGuardarMensaje;
    private JPanel mainPanel;
    private JTextArea txtAreaMensaje;
    private JButton btnEncriptar;
    private JLabel lblArchivo;
    private JLabel lblarchivoEncriptado;
    private JLabel lblClave;
    private JButton btnDesencriptar;


    public SwingPrincipal() {
        btnGuardarMensaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del archivo:");
                if(nombre!=null) {
                    new AdminArchivos().escribe(txtAreaMensaje.getText(),nombre+".txt");
                    lblArchivo.setText(nombre+".txt");
                }
            }
        });
        btnEncriptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String clave=JOptionPane.showInputDialog("Ingrese la clave");
                if(clave!=null){

                    String mensaje=new AdminArchivos().lee(lblArchivo.getText());
                    String mensajeEncriptado=new Cifrar().encriptar(mensaje,Integer.parseInt(clave));
                    txtAreaMensaje.setText(mensajeEncriptado);

                    String archivoEncriptado=lblArchivo.getText().replaceFirst("\\.","_encriptado.");

                    new AdminArchivos().escribe(mensajeEncriptado,archivoEncriptado);
                    lblarchivoEncriptado.setText(archivoEncriptado);

                    new AdminArchivos().escribe(clave,"clave.property");
                    lblClave.setText(clave);
                }
            }
        });
        btnDesencriptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreArchivo=JOptionPane.showInputDialog("Ingrese nombre del archivo para desencriptar");
                if(nombreArchivo!=null) {
                    String archivoEncriptado = new AdminArchivos().lee(nombreArchivo);
                    String clave = new AdminArchivos().lee(nombreArchivo);
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
        //ruta= Paths.get("ficheros","alfabeto.property");
//        BufferedWriter escribo= null;
//        try {
//            escribo = new BufferedWriter(new FileWriter(ruta.toString()));
//            escribo.write("hola ya escribi");
//            escribo.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    try{
//        BufferedReader leo= new BufferedReader(new FileReader(ruta.toString()));
//        int numerocaracter;
//        while((numerocaracter=leo.read())!=-1){
//            char letra=(char)numerocaracter;
//            System.out.print(letra);
//        }
//    } catch (IOException e) {
//        throw new RuntimeException(e);
//    }

//       try(BufferedReader leoalfabeto= new BufferedReader(new FileReader(ruta.toString()))){
////           int numero;
////           while((numero=leoalfabeto.read())!=-1){
////               char caracter=(char)numero;
////               System.out.print(caracter);
////           }
//           String texto="8";
//           String textocodificado = "";
//           String alfabeto=leoalfabeto.readLine();
//           char textochar;
//           int posicion=0;
//           for (int i = 0; i <texto.length() ; i++) {
//               textochar=texto.charAt(i);
//               posicion =alfabeto.indexOf(textochar);
//
//            if(posicion!=-1){
//                int a=(posicion+3)%alfabeto.length();
//                textocodificado += alfabeto.charAt(a);
//            }else{
//                textocodificado += textochar;
//            }
//           }
//           System.out.print(textocodificado);
//       }catch (IOException e) {
//            throw new RuntimeException(e);
//       }

    }
}
