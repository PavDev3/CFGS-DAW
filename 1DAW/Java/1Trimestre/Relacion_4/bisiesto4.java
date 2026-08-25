package Relacion_4;
import java.util.Scanner;

public class bisiesto4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int anio = solicitarAnio();
		esBisiesto(anio);
		if (esBisiesto(anio)) {
			System.out.println("El año " + anio + " es bisiesto.");
		} else {
			System.out.println("El año " + anio + " no es bisiesto.");
		}scanner.close();
    }
    
    public static int solicitarAnio() {
        Scanner scanner = new Scanner(System.in);
        int anio;
        boolean anioValido = false;
        do {
            System.out.print("Introduce un año: ");
            anio = scanner.nextInt();
            scanner.close();  
            }
        while (anioValido);
        return anio;       
    }
    
    public static boolean anioValio(int anio)	{
    	if (anio > 0) {
			return true;
		} else {
			System.out.println("El año debe ser DC ( Despues de Cristo ). Intentalo de nuevo.");
			return false;
		}
    }
    

    public static boolean esBisiesto(int anio) {
        return (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);
    }
}
