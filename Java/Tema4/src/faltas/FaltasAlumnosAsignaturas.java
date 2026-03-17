package faltas;

public class FaltasAlumnosAsignaturas {
	// Atributos
	private Falta[][] faltas; // filas = alumnos, columnas = asignaturas

	// Constructor
	public FaltasAlumnosAsignaturas(int numAlumnos, int numAsignaturas) {
		faltas = new Falta[numAlumnos][numAsignaturas];
	}

	// Getters y Setters
	public Falta[][] getFaltas() {
		return faltas;
	}

	public void setFalta(int alumno, int asignatura, Falta falta) {
		faltas[alumno][asignatura] = falta;
	}

	public Falta getFalta(int alumno, int asignatura) {
		return faltas[alumno][asignatura];
	}

	public int getNumAlumnos() {
		return faltas.length;
	}

	public int getNumAsignaturas() {
		return faltas[0].length;
	}
}
