import java.util.Scanner;

public class triangulo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int base;
        int i;
        int j;
        int k;
        int espacios;
        
        System.out.print("Ingrese la base del triangulo: ");
        base = sc.nextInt();
        
        // bucle para validar que la base sea un número positivo e impar
        if (base <= 0) {
			System.out.println("La base debe ser un número positivo.");
			System.out.println("Introcuzca otro numero distinto a 0: ");
			base = sc.nextInt();
			
		}
        
        if (base % 2 == 0) {
        	System.out.println("La base debe ser un número impar.");
        	
        	System.out.println("Ingrese de nuevo la base del triangulo: ");
        				base = sc.nextInt();
        				
        }
             
        //Dibuja un triaungulo con un solo contador
        if (base % 2 != 0 && base > 0) { 
        for ( i = 1; i <= base; i++) {
        				for ( j = 1; j <= i; j++) {
				System.out.print(" * ");
			}
			System.out.println();
        	}
        }
        
        //dibuja un triangulo con espacios
        if (base % 2 != 0 && base > 0) {
        	 espacios = base / 2;
        	for ( i = 1; i <= base; i += 2) {
				for ( j = 1; j <= espacios; j++) {
					System.out.print("   ");
				}
				for ( k = 1; k <= i; k++) {
					System.out.print(" * ");
				}
				System.out.println();
				espacios--;
			}
        }       
    }
}
    

