package ArrayAvanzada;
import java.util.Scanner;

public class mainArrayAvanzada {

	public static void main(String[] args) {
		
		// Creamos un objeto Scanner para poder leer datos que escriba el usuario por teclado
		Scanner sc = new Scanner(System.in);
		
		// Crear una matriz (tabla) de alumnos de 30 filas (30 alumnos)
		// y 5 columnas (5 asignaturas por alumno). Cada casilla guardará
		// un objeto Alumno con su nombre, nota y número de asignatura.
		Alumno[][] alumnos = new Alumno[30][5];
		
		// Crear un array de asignaturas de tamaño 5.
		// En cada posición guardaremos un objeto Asignatura con su nombre.
		Asignatura[] asignaturas = new Asignatura[5];
		
		// Rellenar el array de asignaturas pidiendo el nombre de cada una por teclado.
		for (int i = 0; i < asignaturas.length; i++) {
			// Pedir nombre de la asignatura por consola
			System.out.print("Introduce el nombre de la asignatura " + (i + 1) + ": ");
			String nombreAsignatura = sc.nextLine();
			// Crear el objeto Asignatura y guardarlo en el array
			asignaturas[i] = new Asignatura(nombreAsignatura);
			
	
		}
		
		// Rellenar la matriz de alumnos con datos de ejemplo.
		// Para cada alumno (fila) y cada asignatura (columna) creamos un objeto Alumno.
		for (int i = 0; i < alumnos.length; i++) {
			for (int j = 0; j < alumnos[i].length; j++) {
				// Crear un alumno con:
				// - Nombre: "Alumno X" donde X es el número de alumno (i + 1)
				// - Nota: número aleatorio entre 0 y 10 redondeado a 2 decimales
				// - Número de asignatura: j + 1 (posición de la asignatura en la fila)
				alumnos[i][j] = new Alumno("Alumno " + (i + 1), Math.round(Math.random() * 1000) / 100.0, j + 1);
			}
		}
		
		// Imprimir por pantalla los datos de todos los alumnos
		// y la media de sus notas en las 5 asignaturas.
		for (int i = 0; i < alumnos.length; i++) {
			// Mostrar el nombre del alumno (todas las columnas de la fila tienen el mismo nombre)
			System.out.println("Alumno: " + alumnos[i][0].getNombre());
			for (int j = 0; j < alumnos[i].length; j++) {
				// Para cada asignatura mostramos su nombre y la nota que tiene el alumno en esa asignatura
				System.out.println(asignaturas[j].getNombre() + ": Nota = " + alumnos[i][j].getNota());
			}
			// Calcular la media de las notas del alumno llamando al método calcularMediaAlumno
			double media = calcularMediaAlumno(alumnos[i]);
			System.out.println("Media de notas: " + media);
		}
		
		// Llamar al método que calcula cuántos alumnos tienen 0, 1, 2, 3, 4 o 5 asignaturas suspendidas
		calcularEstadisticasSuspensos(alumnos);
		
		// Cerrar el Scanner para liberar el recurso de entrada estándar
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
		// Crear un array para contar el número de alumnos con 0, 1, 2, 3, 4 y 5 asignaturas suspendidas.
		// La posición del array indicará la cantidad de suspensos y el valor almacenado,
		// cuántos alumnos tienen exactamente esa cantidad de suspensos.
		int[] suspensos = new int[6]; // 0, 1, 2, 3, 4, 5 asignaturas suspendidas
		
		// Recorremos todos los alumnos de la matriz
		for (int i = 0; i < alumnos.length; i++) {
			// Contador de asignaturas suspendidas para el alumno i
			int numSuspensos = 0;
			
			// Recorremos todas las asignaturas del alumno i (toda la fila i)
			for (int j = 0; j < alumnos[i].length; j++) {
				// Si la nota de la asignatura j es menor que 5, el alumno ha suspendido esa asignatura
				if (alumnos[i][j].getNota() < 5) {
					numSuspensos++; // aumentamos el número de suspensos de ese alumno
				}
			}
			// Una vez contados los suspensos del alumno i,
			// incrementamos el contador de la posición correspondiente del array 'suspensos'.
			// Ejemplo: si numSuspensos = 3, sumamos 1 a suspensos[3].
			suspensos[numSuspensos]++;
		}
		
		// Mostrar por pantalla las estadísticas calculadas
		System.out.println("Estadísticas de alumnos suspensos:");
		
		// Recorremos el array 'suspensos' y mostramos cuántos alumnos hay con
		// 0, 1, 2, 3, 4 y 5 asignaturas suspendidas.
		for (int i = 0; i < suspensos.length; i++) {
			System.out.println(i + " asignaturas suspendidas: " + suspensos[i] + " alumnos");
		}
	}
	

}
