package faltas;

import java.util.Scanner;

public class mainFaltas {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// Definimos el número de alumnos y asignaturas
		int numAlumnos = 5;
		int numAsignaturas = 3;

		// Crear array de nombres de asignaturas
		String[] asignaturas = new String[numAsignaturas];
		asignaturas[0] = "Matematicas";
		asignaturas[1] = "Lengua";
		asignaturas[2] = "Historia";

		// Crear la tabla de faltas
		FaltasAlumnosAsignaturas tabla = new FaltasAlumnosAsignaturas(numAlumnos, numAsignaturas);

		// Rellenar la tabla con datos aleatorios
		// Cada alumno tiene entre 0 y 10 faltas justificadas, injustificadas y retrasos
		for (int i = 0; i < numAlumnos; i++) {
			for (int j = 0; j < numAsignaturas; j++) {
				int justificadas = (int) (Math.random() * 11);
				int injustificadas = (int) (Math.random() * 11);
				int retrasos = (int) (Math.random() * 11);
				tabla.setFalta(i, j, new Falta(justificadas, injustificadas, retrasos));
			}
		}

		// Mostrar los datos generados
		System.out.println("=== DATOS GENERADOS ===");
		for (int i = 0; i < numAlumnos; i++) {
			System.out.println("Alumno " + (i + 1) + ":");
			for (int j = 0; j < numAsignaturas; j++) {
				Falta f = tabla.getFalta(i, j);
				System.out.println("  " + asignaturas[j]
						+ " -> Justificadas: " + f.getJustificadas()
						+ ", Injustificadas: " + f.getInjustificadas()
						+ ", Retrasos: " + f.getRetrasos());
			}
		}

		System.out.println();

		// -------------------------------------------------------
		// LISTADO 1: Para cada asignatura, el alumno con más faltas injustificadas
		// -------------------------------------------------------
		System.out.println("=== LISTADO 1: Alumno con más faltas injustificadas por asignatura ===");
		for (int j = 0; j < numAsignaturas; j++) {
			int maxInjustificadas = tabla.getFalta(0, j).getInjustificadas();
			int alumnoMax = 0;

			// Recorrer todos los alumnos en esa asignatura
			for (int i = 1; i < numAlumnos; i++) {
				if (tabla.getFalta(i, j).getInjustificadas() > maxInjustificadas) {
					maxInjustificadas = tabla.getFalta(i, j).getInjustificadas();
					alumnoMax = i;
				}
			}

			System.out.println("Asignatura: " + asignaturas[j]
					+ " -> Alumno " + (alumnoMax + 1)
					+ " con " + maxInjustificadas + " faltas injustificadas");
		}

		System.out.println();

		// -------------------------------------------------------
		// LISTADO 2: Alumnos con retrasos superiores a la media
		// -------------------------------------------------------
		System.out.println("=== LISTADO 2: Alumnos con retrasos superiores a la media ===");

		// Calcular la suma total de retrasos de cada alumno (suma de todas sus asignaturas)
		int[] retrasosAlumno = new int[numAlumnos];
		for (int i = 0; i < numAlumnos; i++) {
			for (int j = 0; j < numAsignaturas; j++) {
				retrasosAlumno[i] += tabla.getFalta(i, j).getRetrasos();
			}
		}

		// Calcular la media de retrasos de todos los alumnos
		int sumaTotal = 0;
		for (int i = 0; i < numAlumnos; i++) {
			sumaTotal += retrasosAlumno[i];
		}
		double mediaRetrasos = (double) sumaTotal / numAlumnos;

		System.out.println("Media de retrasos: " + mediaRetrasos);

		// Mostrar alumnos con retrasos superiores a la media
		for (int i = 0; i < numAlumnos; i++) {
			if (retrasosAlumno[i] > mediaRetrasos) {
				System.out.println("Alumno " + (i + 1) + " -> " + retrasosAlumno[i] + " retrasos (supera la media)");
			}
		}

		System.out.println();

		// -------------------------------------------------------
		// LISTADO 3: Asignatura con menor número de retrasos
		// -------------------------------------------------------
		System.out.println("=== LISTADO 3: Asignatura con menor número de retrasos ===");

		// Calcular la suma de retrasos de cada asignatura (suma de todos los alumnos)
		int[] retrasosAsignatura = new int[numAsignaturas];
		for (int j = 0; j < numAsignaturas; j++) {
			for (int i = 0; i < numAlumnos; i++) {
				retrasosAsignatura[j] += tabla.getFalta(i, j).getRetrasos();
			}
		}

		// Buscar la asignatura con menos retrasos
		int minRetrasos = retrasosAsignatura[0];
		int asignaturaMin = 0;

		for (int j = 1; j < numAsignaturas; j++) {
			if (retrasosAsignatura[j] < minRetrasos) {
				minRetrasos = retrasosAsignatura[j];
				asignaturaMin = j;
			}
		}

		System.out.println("Asignatura con menor número de retrasos: " + asignaturas[asignaturaMin]
				+ " con " + minRetrasos + " retrasos");

		sc.close();
	}
}
