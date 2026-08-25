package simulacro;

import java.util.Arrays;
import java.util.Scanner;

public class mainAlumno {

	public static void main(String[] args) throws NotaInvalidaException {
		Scanner scanner = new Scanner(System.in);
		alumno[] alumnos = new alumno[5];

		// 2. Pedir nombre y nota para cada alumno
		for (int i = 0; i < alumnos.length; i++) {
			System.out.println("--- Alumno " + (i + 1) + " ---");
			System.out.print("Introduce el nombre: ");
			String nombre = scanner.nextLine();

			Double nota = null;
			boolean notaValida = false;
			while (!notaValida) {
				System.out.print("Introduce la nota (0-10): ");
				while (!scanner.hasNextDouble()) {
					System.out.println("Error: introduce un número válido.");
					scanner.nextLine();
				}
				nota = scanner.nextDouble();
				scanner.nextLine();
				// 3. Si la nota es inválida: mostrar error y volver a pedir
				try {
					if (nota < 0 || nota > 10) {
						throw new NotaInvalidaException("La nota debe estar entre 0 y 10");
					}
					notaValida = true;
				} catch (NotaInvalidaException e) {
					System.out.println("Error: " + e.getMessage());
				}
			}
			alumnos[i] = new alumno(nombre, nota);
		}
		scanner.close();

		// 4. Mostrar lista
		System.out.println("Lista original:");
		mostrarLista(alumnos);

		// 5. Ordenar por nota y mostrar
		Arrays.sort(alumnos);
		System.out.println("Lista ordenada por nota:");
		mostrarLista(alumnos);

		// 6. Mostrar el mejor alumno (el ultimo tras ordenar de menor a mayor)
		System.out.println("Mejor alumno");
		alumno mejor = alumnos[alumnos.length - 1];
		System.out.println(mejor.getNombre() + " - " + mejor.getNota());

		// 7. Subir 1 punto a todos sin superar 10
		for (alumno a : alumnos) {
			double notaActual = a.getNota() + 1;
			if (notaActual > 10) notaActual = 10;
			a.setNota(notaActual);
		}
	

		// 8. Mostrar lista final
		System.out.println("Lista final (ordenada, +1 punto):");
		mostrarLista(alumnos);
	}

	public static void mostrarLista(alumno[] alumnos) {
		for (alumno a : alumnos) {
			System.out.println(a.getNombre() + " - " + a.getNota());
		}
	}
}
