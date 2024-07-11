public class Prueba {
    btnGuardarMensaje.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //valida vacio
                    EstrategiaValidar estrategiaValidar=new ValidarVacio();
                    ContextValidar contextValidar= new ContextValidar(estrategiaValidar);
                    boolean a=contextValidar.validarCadena(txtAreaMensaje.getText());//-----------
    
                    //Boolean a=new ContextoValidar(new ValidarVacio()).validarCadena(txtAreaMensaje.getText());
                    if(!a) {
                        String nombreArchivo = JOptionPane.showInputDialog("Ingrese el nombre del archivo:");
                        //valida vacio
                        boolean b=contextValidar.validarCadena(nombreArchivo);//-----------------
                        if (!b) {
                            //guarda el txt
                            EstrategiaValidar estrategiaValidar1=new ValidarTxt();
                            ContextValidar contextValidar1= new ContextValidar(estrategiaValidar);
                            boolean c=contextValidar.validarCadena(nombreArchivo);//-----------
                            if(!c) {
                                new AdminArchivos(RUTA).escribe(txtAreaMensaje.getText(), nombreArchivo + ".txt");
                                lblArchivo.setText(nombreArchivo + ".txt");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Ingrese un nombre correcto","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Ingrese mensaje","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });
}
