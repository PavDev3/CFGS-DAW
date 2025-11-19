package Relacion_4;

public class replace {
    public static void main(String[] args) {
        StringBuilder str =  new StringBuilder("El cielo es azul");
        str.replace(str.length() - str.length() , str.length() , "hola");
        System.out.println(str);
    }
}
