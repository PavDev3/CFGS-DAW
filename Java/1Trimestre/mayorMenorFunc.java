import java.util.Scanner;
public class mayorMenorFunc {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
            
        int numero1 = pedirNumero();
        int numero2 = pedirNumero();
        int numero3 = pedirNumero();
        int mayor = mayor(numero1, numero2, numero3);
        int menor = menor(numero1, numero2, numero3);
        System.out.println("El número mayor es: " + mayor);
        //System.out.println("El número mayor es: " + mediano);
        System.out.println("El número menor es: " + menor);
    }   

    public static int pedirNumero() {
        System.out.print("Introduce un número: ");
        return scanner.nextInt();
    }
    public static int mayor(int numero1, int numero2, int numero3) {
        if (numero1 > numero2 && numero1 > numero3) {
            return numero1;
        } else if (numero2 > numero1 && numero2 > numero3) {
            return numero2;
        } else {
            return numero3;
        }
    }
    public static int menor(int numero1, int numero2, int numero3) {
        if (numero1 < numero2 && numero1 < numero3) {
            return numero1;
        } else if (numero2 < numero1 && numero2 < numero3) {
            return numero2;
        } else {
            return numero3;
        }
    }
}
