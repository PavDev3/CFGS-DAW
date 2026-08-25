package Relacion_4;
import java.util.*;

public class Nunez_Fernandez_Pablo_LCDP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        long tiempo = System.nanoTime();
        
        // Generar 4 letras mayúsculas aleatorias (A-Z)
        int numeroSecreto1 = random.nextInt(26) + 65;
        int numeroSecreto2 = random.nextInt(26) + 65;
        int numeroSecreto3 = random.nextInt(26) + 65;
        int numeroSecreto4 = random.nextInt(26) + 65;
        
        // Combinar los 4 numeros en un solo long usando desplazamiento de bits
        long combinacionSecreta = ((long)numeroSecreto1 << 24) | ((long)numeroSecreto2 << 16) | ((long)numeroSecreto3 << 8) | numeroSecreto4;
       // Mostrar la combinación generada
        System.out.println( "La Combinacion es: "+ (char) numeroSecreto1 + "" + (char) numeroSecreto2 + (char) numeroSecreto3 + (char) numeroSecreto4);
        // Llamar al metodo de fuerza bruta
        fuerzaAleatoria(numeroSecreto1, numeroSecreto2, numeroSecreto3, numeroSecreto4);
        fuerzaBruta(combinacionSecreta);
        fuerzaJusta(numeroSecreto1, numeroSecreto2, numeroSecreto3, numeroSecreto4);
       
    }

    public static void fuerzaAleatoria(int numeroSecreto1, int numeroSecreto2, int numeroSecreto3, int numeroSecreto4) {
    	// generamos letras aletatorias de la a la z y comprobamos con los numeros secretos    	
    	//Arrancamos el tiempo
    	long start = System.nanoTime();
    	Random random = new Random();
		char letra1, letra2, letra3, letra4;
		do {
			letra1 = (char) (random.nextInt(26) + 65);
			letra2 = (char) (random.nextInt(26) + 65);
			letra3 = (char) (random.nextInt(26) + 65);
			letra4 = (char) (random.nextInt(26) + 65);
		} while (letra1 != (char) numeroSecreto1 || letra2 != (char) numeroSecreto2 || letra3 != (char) numeroSecreto3 || letra4 != (char) numeroSecreto4);
		
		// Paramos el tiempo
		long end = System.nanoTime();
		//comprobamos el tiempo que ha pasado entre los dos
		double sec = (end - start) / 1e9; 
		System.out.println("Encontrado de manera aleatoria !! --> " + letra1 + letra2 + letra3 + letra4);
		System.out.println("Por fuerza aleatoria tardo " + sec + "segundos , esta chupado");
    }   
    
    public static void fuerzaBruta(long combinacionSecreta) {
    	long start = System.nanoTime();
    	// implementar fuerza bruta en bloques de 4 en 4 de la A a la Z
    	for (char i = 'A'; i <= 'Z'; i++) {
			for (char j = 'A'; j <= 'Z'; j++) {
				for (char k = 'A'; k <= 'Z'; k++) {
					for (char l = 'A'; l <= 'Z'; l++) {
						// Combinar los caracteres en un long de la misma manera ( La parte del claudio )
						long combinacionBruta = ((long)i << 24) | ((long)j << 16) | ((long)k << 8) | l;
						if (combinacionBruta == combinacionSecreta) {
							long end = System.nanoTime();
							double sec = (end - start) / 1e9; 
							System.out.println("Encontrado por fuerza bruta !! --> " + i + j + k + l);
							System.out.println("Por fuerza bruta tardo " + sec + " segundos , esta chupado");
							return;
						}
					}
				}
			}
		}   	
    }
    
    public static void fuerzaJusta (int numeroSecreto1, int numeroSecreto2, int numeroSecreto3, int numeroSecreto4) {
		// implementar fuerza justa
		//Arrancamos el tiempo
		long start = System.nanoTime();
		// Comparamos la primera 
		for (char i = 'A'; i <= 'Z'; i++) {
			if (i == (char) numeroSecreto1) {
				// Comparamos la segunda
				for (char j = 'A'; j <= 'Z'; j++) {
					if (j == (char) numeroSecreto2) {
						// Comparamos la tercera
						for (char k = 'A'; k <= 'Z'; k++) {
							if (k == (char) numeroSecreto3) {
								// Comparamos la cuarta
								for (char l = 'A'; l <= 'Z'; l++) {
									if (l == (char) numeroSecreto4) {
										long end = System.nanoTime();
										double sec = (end - start) / 1e9; 
										System.out.println("Encontrado por fuerza justa !! --> " + i + j + k + l);
										System.out.println("Por fuerza justa tardo " + sec + " segundos , esta chupado");
										return;
									}
								}
							}
						}
					}
				}
			}
    	
		}
    }
}