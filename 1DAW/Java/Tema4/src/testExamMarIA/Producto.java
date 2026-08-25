package testExamMarIA;

public class Producto {
	// atributos
	private String nombre;
	private double precio;
	private int stock;

	// constructor
	public Producto(String nombre, double precio, int stock) {
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	}

	// getter
	public String getNombre() {
		return nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public int getStock() {
		return stock;
	}

	// setter
	public void setNombre(String nombre) {
		try {
			if (nombre == null || nombre.trim().isEmpty()) {
				throw new ProductoInvalidoException("El nombre no puede estar en blanco");
			}
			this.nombre = nombre;
		} catch (ProductoInvalidoException e) {
			System.out.println("Error al establecer el nombre: " + e.getMessage());
		}
	}

	public void setPrecio(double precio) {
		try {
			if (precio < 0) {
				throw new ProductoInvalidoException("El precio no puede ser negativo");
			}
			this.precio = precio;
		} catch (ProductoInvalidoException e) {
			System.out.println(e.getMessage());
		}
	}

	public void setStock(int stock) {
		try {
			if (stock < 0) {
				throw new ProductoInvalidoException("El stock no puede ser negativo.");
			}
			this.stock = stock;
		} catch (ProductoInvalidoException e) {
			System.out.println("Error al establecer el stock: " + e.getMessage());
		}
	}
}
