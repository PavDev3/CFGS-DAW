package Relacion_4;
import java.util.Random;

public class cajafuerteJavi {

	private static final int NUM_LETRAS = 26;
	private static final int LETRA_A = 65;
	private static final int LETRA_Z = 90;

	private static final long NANOSEGUNDOS = 1000000000;

	public static void main(String[] args) {

		Random rd = new Random();
		char c1 = (char) (rd.nextInt(NUM_LETRAS) + LETRA_A);
		char c2 = (char) (rd.nextInt(NUM_LETRAS) + LETRA_A);
		char c3 = (char) (rd.nextInt(NUM_LETRAS) + LETRA_A);
		char c4 = (char) (rd.nextInt(NUM_LETRAS) + LETRA_A);

		System.out.println("La combinación es: " + c1 + c2 + c3 + c4);

		// Llamada a los metodos de busqueda
		System.out.println("Por fuerza bruta tardo: " + fuerzaBruta(c1, c2, c3, c4) + " segundos, está chupado");
		System.out.println("Por fuerza aleatoria tardo: " + fuerzaAleatoria(c1, c2, c3, c4) + " segundos, está chupado");
		System.out.println("Por fuerza justa tardo: " + fuerzaJusta(c1, c2, c3, c4) + " segundos, está chupado");

	}

	// Que devuelva segundos
	private static float fuerzaAleatoria(char c1, char c2, char c3, char c4) {
		long inicio = System.nanoTime();
		Random rd = new Random();
		char a1;
		char a2;
		char a3;
		char a4;

		do {
			a1 = (char) (rd.nextInt(NUM_LETRAS) + LETRA_A);
			a2 = (char) (rd.nextInt(NUM_LETRAS) + LETRA_A);
			a3 = (char) (rd.nextInt(NUM_LETRAS) + LETRA_A);
			a4 = (char) (rd.nextInt(NUM_LETRAS) + LETRA_A);
		} while (c1 != a1 || c2 != a2 || c3 != a3 || c4 != a4);
		System.out.println("Encontrado de manera aleatorio!! ->" + (char) a1 + (char) a2 + (char) a3 + (char) a4);
		return (float) (System.nanoTime() - inicio) / NANOSEGUNDOS;
	}

	// Que devuelva segundos
	private static float fuerzaBruta(char c1, char c2, char c3, char c4) {
		long inicio = System.nanoTime();
		boolean encontrado = false;
		for (int i = LETRA_A; i <= LETRA_Z && !encontrado; i++) {
			for (int j = LETRA_A; j <= LETRA_Z && !encontrado; j++) {
				for (int k = LETRA_A; k <= LETRA_Z && !encontrado; k++) {
					for (int l = LETRA_A; l <= LETRA_Z && !encontrado; l++) {
						if (c1 == i && c2 == j && c3 == k && c4 == l) {
							System.out.println(
									"Encontrado de manera secuencia!! ->" + (char) i + (char) j + (char) k + (char) l);
							encontrado = true;
						}
					}
				}
			}
		}
		return (float) (System.nanoTime() - inicio) / NANOSEGUNDOS;
	}

	private static float fuerzaJusta(char c1, char c2, char c3, char c4) {
		long inicio = System.nanoTime();

		boolean encontrado = false;
		char a1 = ' ';
		for (int i = LETRA_A; i <= LETRA_Z && !encontrado; i++) {
			if (c1 == i) {
				a1 = (char) i;
				encontrado = true;
			}
		}

		encontrado = false;
		char a2 = ' ';
		for (int i = LETRA_A; i <= LETRA_Z && !encontrado; i++) {
			if (c2 == i) {
				a2 = (char) i;
				encontrado = true;
			}
		}

		encontrado = false;
		char a3 = ' ';
		for (int i = LETRA_A; i <= LETRA_Z && !encontrado; i++) {
			if (c3 == i) {
				a3 = (char) i;
				encontrado = true;
			}
		}

		encontrado = false;
		char a4 = ' ';
		for (int i = LETRA_A; i <= LETRA_Z && !encontrado; i++) {
			if (c4 == i) {
				a4 = (char) i;
				encontrado = true;
			}
		}
		System.out.println("Encontrado con fuerza justa!! ->" + a1 + a2 + a3 + a4);

		return (float) (System.nanoTime() - inicio) / NANOSEGUNDOS;
	}
}
