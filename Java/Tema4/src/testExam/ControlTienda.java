package testExam;

import java.util.Arrays;
import java.util.Comparator;

public class ControlTienda {
    // Atributos
    private Producto productos[] = new Producto[5];

    // Constructor
    public ControlTienda() {
        // Inicia el array con valores nulos
        for (int i = 0; i < productos.length; i++) {
            productos[i] = null;
        }
    }

    //  para agregar un producto
    public boolean agregarProducto(Producto producto) {
        for (int i = 0; i < productos.length; i++) {
            if (productos[i] == null) {
                productos[i] = producto;
                return true;
            }
        }
        return false; // Sin espacio 
    }

    //  para listar todos los productos
    public void listaProductos() {
        for (Producto producto : productos) {
            if (producto != null) {
                System.out.println(producto.getNombre());
            } else {
                break; // Para cuanto el contador es nulo
            }
        }
    }

    //  para ordenar los productos por precio
    public void ordenarProducto() {
        Arrays.sort(productos, new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {
                if (p1 == null || p2 == null) {
                    return 0;
                }
                return Double.compare(p1.getPrecio(), p2.getPrecio());
            }
        });
    }

    // para encontrar el producto mas caro
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

    // para vender un producto
    public void venderProducto(String nombre, int cantidad) {
        for (int i = 0; i < productos.length; i++) {
            if (productos[i] != null && productos[i].getNombre().equals(nombre)) {
                if (productos[i].getStock() >= cantidad) {
                    productos[i].setStock(productos[i].getStock() - cantidad);
                    System.out.println("Se vendieron " + cantidad + " unidades de " + nombre);
                    return;
                } else {
                    System.out.println("No hay suficiente stock para vender " + cantidad + " unidades de " 
+ nombre);
                    return;
                }
            }
        }
        System.out.println("Producto no encontrado: " + nombre);
    }

    // Getter and Setter para productos
    public Producto[] getProductos() {
        return productos;
    }

    public void setProductos(Producto[] productos) {
        this.productos = productos;
    }
}