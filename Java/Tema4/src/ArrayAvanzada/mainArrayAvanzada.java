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
		

	}
	// Método para calcular la media de un alumno
	private static double calcularMediaAlumno(Alumno[] asignaturasAlumno) {
		double sumaNotas = 0;
		for (int i = 0; i < asignaturasAlumno.length; i++) {
			sumaNotas += asignaturasAlumno[i].getNota();
		}
		return sumaNotas / asignaturasAlumno.length;
	}

}
