import java.util.Scanner;


public class ordenMenorMayor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int temp;
        
        System.out.println("ORDENADOR DE NÚMEROS DE MENOR A MAYOR");
        // System.out.println("Introduce primer número:");
        
        // int [] numeros = new int[3];
        // // Crear array para almacenar los números
        // numeros[0] = scanner.nextInt();
        // System.out.println("Introduce segundo número:");
        // numeros[1] = scanner.nextInt();
        // System.out.println("Introduce tercer número:");
        // numeros[2] = scanner.nextInt();
        
        
        // System.out.println("Números ordenados:");
        // // ordenar los números de menor a mayor
        // Arrays.sort(numeros);
        // // imprimir los números ordenados
        // System.out.println(numeros[0]);
        // System.out.println(numeros[1]);
        // System.out.println(numeros[2]);
    
        // scanner.close();

        // Hacer un programa que pida 3 números y los ordene de menor a mayor sin array 
        System.out.println("Introduce primer número:");
        int numero1 = scanner.nextInt();
        System.out.println("Introduce segundo número:");
        int numero2 = scanner.nextInt();
        System.out.println("Introduce tercer número:");
        int numero3 = scanner.nextInt();
        if (numero1 > numero2) {
            temp = numero1;
            numero1 = numero2;
            numero2 = temp;
        }
        if (numero1 > numero3) {
            temp = numero1;
            numero1 = numero3;
            numero3 = temp;
        }
        if (numero2 > numero3) {
            temp = numero2;
            numero2 = numero3;
            numero3 = temp;
        }
        System.out.println("Números ordenados:");
        System.out.println(numero1);
        System.out.println(numero2);
        System.out.println(numero3);
        scanner.close();

    }
}
