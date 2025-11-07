import java.util.*;
public class fibonacciFunc {
	public static void main(String[] args) {
		int n = pedirCantidad();
		fibonacci (n);
	

	}
    
	
	private static int pedirCantidad() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Introduce hasta que numero quiere mostrar");
		int n = scanner.nextInt();
		return n;
	}
	
	
	private static void fibonacci(int n) {
		int a = 0;
		int b = 1;
		int c;
		int i;
		
		
		if(n<0) {
			System.out.println("No hay terminos para mostrar"); //Caso para el 0, negativos, 1 y 2.
		}else if(n==0) {
			System.out.println("No hay terminos para mostrar"); 
		}else if(n==1) {
			System.out.println("0");
		} else if(n==2) {
			System.out.println("0,1"); 
		}else {
			
			System.out.print("0,1,"); //Si no, empezamos con los primeros 2.
			
			for (i = 0 ; i < n ; i++ ) { //0<9
				c = a + b;
				a = b;
				b = c;
				System.out.print(c+",");
		}
		}
			
			
	}
}
