import java.util.*;

// EL PROGRAMA NO ME COMPILA NO ME HA DADO TIEMPO A SABER POR QUÉ: "Exception in thread "main" java.lang.IllegalArgumentException: bound must be positive"
// HE INTENTADO CREAR LOS BLOQUES DE MÉTODO PERO NO HE PODIDO VERIFICAR QUE FUNCIONEN
public class javi {
	public static void main (String[] args) {
		Random random = new Random();
		int min = 65; // LETRA A
		int max = 90; // LETRA Z
	
		// Genero 4 letras aleatorieas entre la A y la Z en números ASCII
		int a1 = random.nextInt(min - max + 1);
		int a2 = random.nextInt(min - max + 1);
		int a3 = random.nextInt(min - max + 1);
		int a4 = random.nextInt(min - max + 1);
		
		// Casteo para que sea legible luego al mostrar por pantalla
		
		char b1 = (char)a1;
		char b2 = (char)a2;
		char b3 = (char)a3;
		char b4 = (char)a4;
		
		// Muestro la combinación por pantalla después de castearlas a texto
		System.out.println("La combinación correcta es: " + b1 + " " + b2 + " " + b3 + " " + b4);
		
		// Llamo a los métodos
		fuerzaAleatoria(a1, a2, a3, a4, min, max);
		fuerzaBruta(a1, a2, a3, a4, min, max);
		fuerzaJusta(a1, a2, a3, a4, min, max);
	}
	// Creo un método para compararlos con la combinación original
	public static int comparador(int a, int b, int c, int d, int f, int g, int h, int i) { // LLamo f, g, h, i, a las letras de la combinación por orden de lista
		if (a == f && b == g && c == h && d == i) {
			return 1; // Es la misma combinación
		} else
		return 0; // La combinación es distinta 
	}
	// Creo el método de fuerza Aleatoria para que acierte al azar la combinación
	public static void fuerzaAleatoria (int a1, int a2, int a3, int a4, int min, int max) {
		Random x = new Random();
		int a = 65, b = 65, c = 65, d = 65; // Comience en A
		int solucion = 0; // 0 es si no se encuentra y el opuesto "1" si lo encuentra 
		int inicioTiempo = (int) System.nanoTime(); // Declaro el tiempo de comienzo
	// Hago un bucle de intentos infinitos hasta que encuentre la clave
		while (solucion == 0) {
			a = min + x.nextInt(max - min + 1);
			b = min + x.nextInt(max - min + 1);
			c = min + x.nextInt(max - min + 1);
			d = min + x.nextInt(max - min + 1);
			// Comparo con la combinación original
			solucion = comparador(a, b, c, d, a1, a2, a3, a4);
					
		}
		// Casteo a texto para el mensaje por pantalla
		char aa = (char) a;
		char bb = (char) b;
		char cc = (char) c;
		char dd = (char) d;
		// Tiempo final y cuento el recorrido y luego lo paso a segundos
		int finTiempo = (int) System.nanoTime();
		double segundos = (finTiempo - inicioTiempo) / 1000000000.0;
		
		System.out.println("Verificando de forma aleatoria, la clave es: " + aa + bb + cc + dd);
		System.out.println("Por aleaetoridad ha tardado: " + segundos + "segundos");
		
	}
	// Creo el método de fuerza Bruta donde recorre las 4 letras a la vez, en este caso por número ASCII
	public static void fuerzaBruta (int a1, int a2, int a3, int a4, int min, int max) {
		int a = 65, b = 65, c = 65, d = 65; // ""
		int solucion = 0; // "" 
		int inicioTiempo = (int) System.nanoTime(); // ""
		// Creo 4 bucles desde la a hasta la d para las 4 letras de la combinación y que prueben hasta la Z 
		for (a = min; a <= max && solucion == 0; a++) {
			for (b = min; b <= max && solucion == 0; b++) {
				for (c = min; c <= max && solucion == 0; c++) {
					for (d = min; d <= max && solucion == 0; d++) {
						solucion = comparador(a, b, c, d, a1, a2, a3, a4);
					}
				}
			}
		}
		char aa = (char) a;
		char bb = (char) b;
		char cc = (char) c;
		char dd = (char) d;
		
		int finTiempo = (int) System.nanoTime();
		double segundos = (finTiempo - inicioTiempo) / 1000000000.0;
		
		// Muestro por pantalla la clave econtrada y el tiempo que tardó
		System.out.println("Verificando a través de fuerza bruta, la clave es: " + aa + bb + cc + dd);
		System.out.println("Por fuerza bruta ha tardado: " + segundos + "segundos");
		
	
	}		
	
	public static void fuerzaJusta (int a1, int a2, int a3, int a4, int min, int max) {
		int a = 65, b = 65, c = 65, d = 65;
		int inicioTiempo = (int) System.nanoTime();
		
		// Buscamos una por una la clave
		for (a = min; a<= max; a++) {
			if (a == a1) break;
		}
		for (b = min; b<= max; b++) {
			if (b == a2) break;
		}
		for (c = min; c<= max; c++) {
			if (c == a3) break;
		}
		for (d = min; d<= max; d++) {
			if (d == a4) break;
		}
		
		char aa = (char) a;
		char bb = (char) b;
		char cc = (char) c;
		char dd = (char) d;
		
		
		int finTiempo = (int) System.nanoTime();
		double segundos = (finTiempo - inicioTiempo) / 1000000000.0;
		
		// Muestro por pantalla la clave econtrada y el tiempo que tardó
		System.out.println("Verificando uno a uno, la clave es: " + aa + bb + cc + dd);
		System.out.println("Por fuerza justa ha tardado: " + segundos + "segundos");
	}
}
