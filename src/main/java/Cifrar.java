public class Cifrar {

    public String encriptar(String mensaje,int clave){
        String alfabeto= new AdminArchivos().lee("alfabeto.property");
        String textoEncriptado = "";
        char textoChar;
        int posicion=0;
        for (int i = 0; i <mensaje.length() ; i++) {
            textoChar=mensaje.charAt(i);
            posicion =alfabeto.indexOf(textoChar);

            if(posicion==-1){
                textoEncriptado += textoChar;
            }else{
                textoEncriptado += alfabeto.charAt((posicion+clave)%alfabeto.length());
            }
        }
        return textoEncriptado;
    }
    public String desencriptar(String mensajeEncriptado,int clave){
        String alfabeto= new AdminArchivos().lee("alfabeto.property");
        String textoDesencriptado = "";
        char textoChar;
        int posicion=0;
        for (int i = 0; i <mensajeEncriptado.length() ; i++) {
            textoChar=mensajeEncriptado.charAt(i);
            posicion =alfabeto.indexOf(textoChar);

            if(posicion==-1){
                textoDesencriptado += textoChar;
            }else{
                if(posicion-3<0){
                    textoDesencriptado += alfabeto.charAt(alfabeto.length()+(posicion+clave));
                }else{
                    textoDesencriptado += alfabeto.charAt((posicion-clave)%alfabeto.length());
                }
            }
        }
        return textoDesencriptado;
    }
}
