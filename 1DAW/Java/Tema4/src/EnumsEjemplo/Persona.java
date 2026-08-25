package EnumsEjemplo;

public class Persona {
	private String dni;
	private String nombre;
	private TipoEstadoCivil estadoCivil;
	
	
	public Persona(String dni, String nombre, TipoEstadoCivil estadoCivil) {
	
		this.dni = dni;
		this.nombre = nombre;
		this.estadoCivil = estadoCivil;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public TipoEstadoCivil getEstadoCivil() {
		return estadoCivil;
	}


	public void setEstadoCivil(TipoEstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}


	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", nombre=" + nombre + ", estadoCivil=" + estadoCivil + "]";
	}
	
	
	
	
	
	
}