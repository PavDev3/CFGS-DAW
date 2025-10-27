import java.util.Scanner;

public class rectangulo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int alto, ancho;
        System.out.print("Ingrese el alto del rectángulo: ");
        alto = sc.nextInt();
        System.out.print("Ingrese el ancho del rectángulo: ");
        ancho = sc.nextInt();
        System.out.println("Dibujalo con :");
        char letra = sc.next().charAt(0);
        
        
        if (alto <= 0 || ancho <= 0) {
        				System.out.println("El alto y el ancho deben ser números positivos.");
        				System.out.println("Inténtelo de nuevo.");
        				alto = sc.nextInt();
        				ancho = sc.nextInt();
        				
        }

        // Dibuja rectángulo con 2 contadores, uno para el alto y otro para el ancho
        for (int i = 0; i < alto; i++) {
            for ( int j = 0 ; j < ancho; j++) {
                System.out.print(" " + letra);
            }
            System.out.println();
        }
        sc.close();
    }
    
}
