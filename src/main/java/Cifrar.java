public class Cifrar {

    public String encriptar(String mensaje,int clave){
        String alfabeto= new AdminArchivos().lee("alfabeto.property");
        String textoEncriptado = "";
        char textoChar;
        int posicion=0;
        for (int i = 0; i <mensaje.length() ; i++) {
            textoChar=mensaje.charAt(i);
            posicion =alfabeto.indexOf(textoChar);

            if(posicion!=-1){
                int a=(posicion+clave)%alfabeto.length();
                textoEncriptado += alfabeto.charAt(a);
            }else{
                textoEncriptado += textoChar;
            }
        }
        return textoEncriptado;
    }
    public String desencriptar(String mensajeEncriptado,int clave){
        return null;
    }
}
