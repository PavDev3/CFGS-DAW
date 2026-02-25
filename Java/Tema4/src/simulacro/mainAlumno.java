package simulacro;

import java.util.Scanner;
public class mainAlumno {

	public static void main(String[] args) throws NotaInvalidaException {
		Scanner scanner = new Scanner(System.in);
		alumno[] alumnos = new alumno[5];

		for (int i = 0; i < alumnos.length; i++) {
			System.out.println("Introduce el nombre del alumno: ");
			String nombre = scanner.nextLine();
			System.out.println("Introduce la nota del alumno: ");
			while (!scanner.hasNextDouble()) {
				System.out.println("La nota debe ser un número");
				scanner.next();
			}
			Double nota = scanner.nextDouble();
			if (nota < 0 || nota > 10) {
				//mostrar mensaje de error
				throw new NotaInvalidaException("La nota debe estar entre 0 y 10");
			}
			alumnos[i] = new alumno(nombre, nota);
		}
		scanner.close();
	}
}
