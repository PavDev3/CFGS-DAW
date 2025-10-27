import java.util.*;
public class fibonacci {
	
		public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = 0, b = 1;
		int c;
		System.out.println(" Fibonacci hasta el limite de: ");
		int limite = sc.nextInt();
		
		for(int i = 0; a < limite; i++) {
			System.out.print(a + " " );
			c = a + b;
			a = b;
			b = c;
			 
		}
	}  
}
