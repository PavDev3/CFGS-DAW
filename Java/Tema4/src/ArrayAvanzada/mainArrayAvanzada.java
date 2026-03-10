package ArrayAvanzada;
import java.util.Scanner;

public class mainArrayAvanzada {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		// Crear una matriz de alumnos de 30 con 5 asignaturas
		Alumno[][] alumnos = new Alumno[30][5];
		// Crear un array de asignaturas
		Asignatura[] asignaturas = new Asignatura[5];
		// Rellenar el array de asignaturas con datos de ejemplo
		for (int i = 0; i < asignaturas.length; i++) {
			// pedir nombre de la asignatura por consola
			System.out.print("Introduce el nombre de la asignatura " + (i + 1) + ": ");
			String nombreAsignatura = sc.nextLine();
			asignaturas[i] = new Asignatura(nombreAsignatura);
			
	
		}
		// Rellenar la matriz con datos de ejemplo
		for (int i = 0; i < alumnos.length; i++) {
			for (int j = 0; j < alumnos[i].length; j++) {
				// Crear un alumno con un nombre, una nota aleatoria entre 0 y 10, y el número de asignatura Maximo 1 entero y decimal
				alumnos[i][j] = new Alumno("Alumno " + (i + 1), Math.round(Math.random() * 1000) / 100.0, j + 1);
			}
		}
		
		// Imprimir los datos de los alumnos y la media de sus notas
		for (int i = 0; i < alumnos.length; i++) {
			System.out.println("Alumno: " + alumnos[i][0].getNombre());
			for (int j = 0; j < alumnos[i].length; j++) {
				System.out.println(asignaturas[j].getNombre() + ": Nota = " + alumnos[i][j].getNota());
			}
			// Calcular la media de las notas del alumno
			double media = calcularMediaAlumno(alumnos[i]);
			System.out.println("Media de notas: " + media);
		}
		
		// Calcular las estadísticas de alumnos suspensos
		calcularEstadisticasSuspensos(alumnos);
		sc.close();
		

	}
	// Método para calcular la media de un alumno
	private static double calcularMediaAlumno(Alumno[] asignaturasAlumno) {
		// Sumar las notas de las asignaturas del alumno
		double sumaNotas = 0;
		// Recorrer las asignaturas del alumno y sumar sus notas
		for (int i = 0; i < asignaturasAlumno.length; i++) {
			// Sumar la nota de la asignatura i del alumno
			sumaNotas += asignaturasAlumno[i].getNota();
		}
		// Calcular la media dividiendo la suma de las notas entre el número de asignaturas
		return sumaNotas / asignaturasAlumno.length;
	}
	
	// Metodo para calcular la estadisticas de alumnos suspensos con 5 asignaturas, 4 , 3 , 2 , 1 y 0 asignaturas suspendidas
	private static void calcularEstadisticasSuspensos(Alumno[][] alumnos) {
		// Crear un array para contar el número de alumnos con 0, 1, 2, 3, 4 y 5 asignaturas suspendidas
		int[] suspensos = new int[6]; // 0, 1, 2, 3, 4, 5 asignaturas suspendidas
		// Contar el número de alumnos con cada cantidad de asignaturas suspendidas
		for (int i = 0; i < alumnos.length; i++) {
			// Contar el número de asignaturas suspendidas para el alumno i
			int numSuspensos = 0;
			for (int j = 0; j < alumnos[i].length; j++) {
				// Si la nota es menor que 5, el alumno ha suspendido esa asignatura
				if (alumnos[i][j].getNota() < 5) {
					numSuspensos++;
				}
			}
			// Incrementar el contador correspondiente en el array de suspensos
			suspensos[numSuspensos]++;
		}
		System.out.println("Estadísticas de alumnos suspensos:");
		for (int i = 0; i < suspensos.length; i++) {
			System.out.println(i + " asignaturas suspendidas: " + suspensos[i] + " alumnos");
		}
	}
	

}
