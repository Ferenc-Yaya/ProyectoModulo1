package pe.codegym.modulo1;

public class Cifrar {
    private String alfabeto;

    public Cifrar(String alfabeto) {
        this.alfabeto = alfabeto;
    }

    public String encriptar(String mensaje, int clave){
        String textoEncriptado = "";
        char textoChar;
        int posicion=0;
        for (int i = 0; i <mensaje.length() ; i++) {
            textoChar=mensaje.charAt(i);
            posicion =this.alfabeto.indexOf(textoChar);

            if(posicion==-1){
                textoEncriptado += textoChar;
            }else{
                textoEncriptado += this.alfabeto.charAt((posicion+clave)%alfabeto.length());
            }
        }
        return textoEncriptado;
    }
    public String desencriptar(String mensajeEncriptado,int clave){
        String textoDesencriptado = "";
        char textoChar;
        int posicion=0;
        for (int i = 0; i <mensajeEncriptado.length() ; i++) {
            textoChar=mensajeEncriptado.charAt(i);
            posicion =alfabeto.indexOf(textoChar);

            if(posicion==-1){
                textoDesencriptado += textoChar;
            }else{
                if(posicion-clave<0){
                    textoDesencriptado += this.alfabeto.charAt(alfabeto.length()+(posicion-clave));
                }else{
                    textoDesencriptado += this.alfabeto.charAt((posicion-clave)%alfabeto.length());
                }
            }
        }
        return textoDesencriptado;
    }
}
