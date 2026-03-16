package Conecta4;

import java.util.Scanner;

public class Tablero {
	// Atributos
	private String[][] tablero;
	private Scanner scanner = new Scanner(System.in);

	// Constructor
	public Tablero(int filas, int columnas) {
		tablero = new String[filas][columnas];
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				tablero[i][j] = " ";
			}
		}
	}

	// Mostrar el tablero por pantalla
	public void mostrarTablero() {
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print("| " + tablero[i][j] + " ");
			}
			System.out.println("|");
		}
		// Numeracion de columnas
		for (int j = 0; j < tablero[0].length; j++) {
			System.out.print("  " + j + " ");
		}
		System.out.println();
	}

	// Coloca la ficha en la columna, cae a la fila mas baja disponible
	public boolean colocarFicha(int columna, Jugador.Color color) {
		for (int i = tablero.length - 1; i >= 0; i--) {
			if (tablero[i][columna].equals(" ")) {
				tablero[i][columna] = color.getSimbolo();
				return true;
			}
		}
		return false; // Columna llena
	}

	// Comprueba si el color dado ha conectado 4: horizontal, vertical y diagonales
	public boolean comprobarGanador(String color) {
		int filas = tablero.length;
		int cols = tablero[0].length;

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < cols; j++) {
				if (!tablero[i][j].equals(color)) continue;

				// Horizontal
				if (j + 3 < cols
						&& tablero[i][j+1].equals(color)
						&& tablero[i][j+2].equals(color)
						&& tablero[i][j+3].equals(color)) return true;

				// Vertical
				if (i + 3 < filas
						&& tablero[i+1][j].equals(color)
						&& tablero[i+2][j].equals(color)
						&& tablero[i+3][j].equals(color)) return true;
			}
		}
		return false;
	}

	// Comprueba si el tablero esta completamente lleno (empate)
	public boolean tableroLleno() {
		for (int j = 0; j < tablero[0].length; j++) {
			if (tablero[0][j].equals(" ")) return false;
		}
		return true;
	}

	// Pide una columna valida al jugador
	public int preguntarColumna() {
		int columna = -1;
		while (columna < 0 || columna >= tablero[0].length) {
			System.out.print("Introduce el numero de columna (0-" + (tablero[0].length - 1) + "): ");
			try {
				// Lee la entrada del usuario y la convierte a entero
				columna = Integer.parseInt(scanner.nextLine());
				if (columna < 0 || columna >= tablero[0].length) {
					System.out.println("Columna fuera de rango. Intenta de nuevo.");
					columna = -1;
				}
			} catch (NumberFormatException e) {
				System.out.println("Entrada no valida. Introduce un numero.");
			}
		}
		return columna;
	}
}
