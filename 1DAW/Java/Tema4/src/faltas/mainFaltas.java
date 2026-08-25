package faltas;

import java.util.Scanner;

public class mainFaltas {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int numAlumnos = 5;
		int numAsignaturas = 3;

		String[] asignaturas = new String[numAsignaturas];
		asignaturas[0] = "Matematicas";
		asignaturas[1] = "Lengua";
		asignaturas[2] = "Historia";

		// Crear la tabla de faltas
		FaltasAlumnosAsignaturas tabla = new FaltasAlumnosAsignaturas(numAlumnos, numAsignaturas);

		// Rellenar la tabla con datos aleatorios (0-10)
		for (int i = 0; i < numAlumnos; i++) {
			for (int j = 0; j < numAsignaturas; j++) { 
				int justificadas = (int) (Math.random() * 11);
				int injustificadas = (int) (Math.random() * 11);
				int retrasos = (int) (Math.random() * 11);
				tabla.setFalta(i, j, new Falta(justificadas, injustificadas, retrasos));
			}
		}

		// Mostrar todos los datos
		System.out.println("DATOS GENERADOS:");
		// Recorrer la tabla y mostrar los datos
		for (int i = 0; i < numAlumnos; i++) {
			// Mostrar el alumno
			System.out.println("Alumno " + (i + 1) + ":");
			// Mostrar las faltas por asignatura
			for (int j = 0; j < numAsignaturas; j++) {
				// Obtener la falta de la tabla
				Falta f = tabla.getFalta(i, j);
				// Mostrar los datos de la falta
				System.out.println("  " + asignaturas[j]
						+ " -> Justificadas: " + f.getJustificadas()
						+ ", Injustificadas: " + f.getInjustificadas()
						+ ", Retrasos: " + f.getRetrasos());
			}
		}

		System.out.println();

		// Listado 1: alumno con mas faltas injustificadas por asignatura
		System.out.println("Listado 1: alumno con mas faltas injustificadas por asignatura");
		for (int j = 0; j < numAsignaturas; j++) {
			int maxInjustificadas = tabla.getFalta(0, j).getInjustificadas();
			int alumnoMax = 0;
			for (int i = 1; i < numAlumnos; i++) {
				if (tabla.getFalta(i, j).getInjustificadas() > maxInjustificadas) {
					maxInjustificadas = tabla.getFalta(i, j).getInjustificadas();
					alumnoMax = i;
				}
			}
			System.out.println("  " + asignaturas[j] + " -> Alumno " + (alumnoMax + 1) + " con " + maxInjustificadas + " faltas injustificadas");
		}

		System.out.println();

		// Listado 2: alumnos con retrasos superiores a la media
		System.out.println("Listado 2: alumnos con retrasos superiores a la media");

		// Sumar retrasos totales por alumno
		int[] retrasosAlumno = new int[numAlumnos];
		for (int i = 0; i < numAlumnos; i++) {
			for (int j = 0; j < numAsignaturas; j++) {
				retrasosAlumno[i] += tabla.getFalta(i, j).getRetrasos();
			}
		}

		// Calcular la media
		int sumaTotal = 0;
		for (int i = 0; i < numAlumnos; i++) {
			sumaTotal += retrasosAlumno[i];
		}
		double mediaRetrasos = (double) sumaTotal / numAlumnos;

		System.out.println("  Media de retrasos: " + mediaRetrasos);
		for (int i = 0; i < numAlumnos; i++) {
			if (retrasosAlumno[i] > mediaRetrasos) {
				System.out.println("  Alumno " + (i + 1) + " -> " + retrasosAlumno[i] + " retrasos");
			}
		}

		System.out.println();

		// Listado 3: asignatura con menor numero de retrasos
		System.out.println("Listado 3: asignatura con menor numero de retrasos");

		// Sumar retrasos por asignatura
		int[] retrasosAsignatura = new int[numAsignaturas];
		for (int j = 0; j < numAsignaturas; j++) {
			for (int i = 0; i < numAlumnos; i++) {
				retrasosAsignatura[j] += tabla.getFalta(i, j).getRetrasos();
			}
		}

		// Buscar el minimo
		// Inicializar con el primer valor
		int minRetrasos = retrasosAsignatura[0];
		// Guardar el indice de la asignatura con menos retrasos
		int asignaturaMin = 0;
		for (int j = 1; j < numAsignaturas; j++) {
			// Comparar con el minimo actual
			if (retrasosAsignatura[j] < minRetrasos) {
				// Actualizar el minimo
				minRetrasos = retrasosAsignatura[j];
				// Actualizar el indice de la asignatura con menos retrasos
				asignaturaMin = j;
			}
		}

		System.out.println("  " + asignaturas[asignaturaMin] + " con " + minRetrasos + " retrasos");

		sc.close();
	}
}
