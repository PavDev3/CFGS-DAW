package tienda;

public class Articulo {
	// Atributos
	private String nombre;
	private float precio;
	private int stock;

	// Constructor

	public Articulo(String nombre, float precio, int stock) {
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	}

	// Setters

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPrecio(float precio) {
		if (precio <= 0) {
			System.out.println("El precio no puede ser menor o igual a 0.");
		}
		this.precio = precio;
	}

	public void setStock(int stock) {
		if (stock < 0) {
			System.out.println("El stock no puede ser menor a 0.");
		}
		this.stock = stock;
	}

	// Getters

	public String getNombre() {
		return nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public int getStock() {
		return stock;
	}

	// Metodos

	public int vender(int cantidad) {
		// verifica si hay suficiente stock hazlo con un try catch 
		if (cantidad <= stock) {
			stock -= cantidad;
			return cantidad;
		} else {
			System.out.println("No hay suficiente stock para vender " + cantidad + " unidades de " + nombre);
			return 0;
		}
	}
	

	public String mostrarInfo() {
		return "Nombre: " + nombre + ", Precio: " + precio + " £, Stock: " + stock;
	}

}
