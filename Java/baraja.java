import java.util.*;

public class baraja {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	
    	// variables para los palos de la baraja
    	char palo;
    	char b = 'b';
    	char c = 'c';
    	char e = 'e';
    	char o = 'o';
    	char t = 't';
    	
 
    	System.out.print("Elija el palo que quiere imprimir: Bastos, Copas, Espadas , Oros o Todas: ");
    	palo = scanner.next().charAt(0);
    	// comprobar que solo se introduce los switch posibles
   	
    	while (palo != b && palo != c && palo != e && palo != o && palo != t) { // bucle para forzar a introducir los Cases.
    		System.out.print("Introduce B, C , O , E o T: "); //preguntamos
    		palo = scanner.next().charAt(0); // guardamos
    	}
    	
    	// imprimir la baraja segun el palo elegido
    	switch (palo) { // Se buscara el caso del switch Palo
    	case 'b':
			for (int i = 1; i <= 12; i++) { // contador arranca en 1 hasta que llegue a 12 y para de sumar
				if (i <8 ) { // caso de contador sea menos de 8 imprimira contador mas el palo.
					System.out.print(i + "-B, ");
				}
				if (i == 10 && i <=12 ) { // llegado a 10 y no sobrepasando el 12 imprimira la sota,caballo y rey
					System.out.println("S-B, C-B, R-B ");
				}
			}
			break; // Se rompe el codigo
    	case 'c': {
    		for(int i = 1; i <= 12; i++) {
    			if (i <8 ) {
					System.out.print(i + "-C, ");
				}
				if (i == 10 && i <=12 ) {
					System.out.println("S-C, C-C, R-C ");
				}
				
    		}
    	}
    		break;
    	case 'e':{
    		for(int i = 1; i <= 12; i++) {
    			if (i <8 ) {
					System.out.print(i + "-E, ");
				}
				if (i == 10 && i <=12 ) {
					System.out.println("S-E, C-E, R-E ");
				}
    		}
    	}
    		break;
    	case 'o':{
    		for(int i = 1; i <= 12; i++) {
    			if (i <8 ) {
					System.out.print(i + "-O, ");
				}
				if (i == 10 && i <=12 ) {
					System.out.println("S-O, C-O, R-O ");
				}
    		}
    	}
    		break;
    	case 't':{
    			for(int i = 1; i <= 12; i++) {
        			if (i <8 ) {
    					System.out.print(i + "-B, ");
    				}
    				if (i == 10 && i <=12 ) {
    					System.out.println("S-B, C-B, R-B ");
    				}		
    		}
        		for(int i = 1; i <= 12; i++) {
        			if (i <8 ) {
    					System.out.print(i + "-C, ");
    				}
    				if (i == 10 && i <=12 ) {
    					System.out.println("S-C, C-C, R-C ");
    				}
    	}
        		for(int i = 1; i <= 12; i++) {
        			if (i <8 ) {
    					System.out.print(i + "-E, ");
    				}
    				if (i == 10 && i <=12 ) {
    					System.out.println("S-E, C-E, R-E ");
    				}
        		}
    			for (int i = 1; i <= 12; i++) {
        			if (i <8 ) {
    					System.out.print(i + "-O, ");
    				}
    				if (i == 10 && i <=12 ) {
    					System.out.println("S-O, C-O, R-O ");
    				}
    			}
    		}
    		break;
    	default:
			System.out.println("El palo introducido no es valido");
			break;
    	}
    }
}