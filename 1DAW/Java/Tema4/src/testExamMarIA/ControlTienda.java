package testExamMarIA;

public class ControlTienda {
	// Atributos
	private Producto[] productos = new Producto[5];

	// Constructor
	public ControlTienda() {
		// Inicia el array con valores nulos
		for (int i = 0; i < productos.length; i++) {
			productos[i] = null;
		}
	}

	// Agregar un producto al array
	public boolean agregarProducto(Producto producto) {
		for (int i = 0; i < productos.length; i++) {
			if (productos[i] == null) {
				productos[i] = producto;
				return true;
			}
		}
		return false; // Sin espacio disponible
	}

	// Listar todos los productos
	public void listaProductos() {
		for (Producto producto : productos) {
			if (producto != null) {
				System.out.println(producto.getNombre() + " - Precio: " + producto.getPrecio() + " - Stock: " + producto.getStock());
			} else {
				break; // Para cuando encuentra un null
			}
		}
	}

	// Ordenar productos por precio (de menor a mayor)
	public void ordenarProducto() {
		// Primero movemos los nulls al final del array
		int posicion = 0;
		for (int i = 0; i < productos.length; i++) {
			if (productos[i] != null) {
				productos[posicion] = productos[i];
				if (posicion != i) {
					productos[i] = null;
				}
				posicion++;
			}
		}

		// Ordenamos con burbuja solo los productos existentes
		for (int i = 0; i < posicion - 1; i++) {
			for (int j = i + 1; j < posicion; j++) {
				if (productos[i].getPrecio() > productos[j].getPrecio()) {
					Producto temp = productos[i];
					productos[i] = productos[j];
					productos[j] = temp;
				}
			}
		}
	}

	// Encontrar el producto más caro
	public Producto productoMasCaro() {
		double maxPrecio = -1;
		Producto productoMasCaro = null;

		for (Producto producto : productos) {
			if (producto != null && producto.getPrecio() > maxPrecio) {
				maxPrecio = producto.getPrecio();
				productoMasCaro = producto;
			}
		}

		return productoMasCaro;
	}

	// Vender un producto (reduce el stock)
	public void venderProducto(String nombre, int cantidad) {
		for (int i = 0; i < productos.length; i++) {
			if (productos[i] != null && productos[i].getNombre().equals(nombre)) {
				if (productos[i].getStock() >= cantidad) {
					productos[i].setStock(productos[i].getStock() - cantidad);
					System.out.println("Se vendieron " + cantidad + " unidades de " + nombre);
					return;
				} else {
					System.out.println("No hay suficiente stock para vender " + cantidad + " unidades de " + nombre);
					return;
				}
			}
		}
		System.out.println("Producto no encontrado: " + nombre);
	}

	// Getter y Setter para productos
	public Producto[] getProductos() {
		return productos;
	}

	public void setProductos(Producto[] productos) {
		this.productos = productos;
	}
}
