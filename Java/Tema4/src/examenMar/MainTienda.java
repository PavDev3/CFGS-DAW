package examenMar;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Programa principal para gestionar productos de una tienda.
 * 
 * Funcionalidades:
 * 1. Crear array de 5 productos
 * 2. Pedir datos por teclado con validación
 * 3. Mostrar productos
 * 4. Ordenar por precio
 * 5. Mostrar el más caro
 * 6. Reducir stock en 2 unidades
 * 7. Mostrar lista final
 */
public class MainTienda {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Producto[] productos = new Producto[5];
        
        // 1 y 2. Crear array y pedir datos por teclado
        System.out.println("=== GESTIÓN DE PRODUCTOS ===\n");
        
        for (int i = 0; i < productos.length; i++) {
            System.out.println("--- Producto " + (i + 1) + " ---");
            
            // Pedir nombre
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            
            // Pedir precio con validación
            double precio = 0;
            boolean precioValido = false;
            while (!precioValido) {
                System.out.print("Precio: ");
                if (scanner.hasNextDouble()) {
                    precio = scanner.nextDouble();
                    scanner.nextLine();  // Limpiar buffer
                    if (precio < 0) {
                        System.out.println("Error: El precio no puede ser negativo. Inténtalo de nuevo.");
                    } else {
                        precioValido = true;
                    }
                } else {
                    System.out.println("Error: Introduce un número válido.");
                    scanner.nextLine();  // Limpiar entrada incorrecta
                }
            }
            
            // Pedir stock con validación
            int stock = 0;
            boolean stockValido = false;
            while (!stockValido) {
                System.out.print("Stock: ");
                if (scanner.hasNextInt()) {
                    stock = scanner.nextInt();
                    scanner.nextLine();  // Limpiar buffer
                    if (stock < 0) {
                        System.out.println("Error: El stock no puede ser negativo. Inténtalo de nuevo.");
                    } else {
                        stockValido = true;
                    }
                } else {
                    System.out.println("Error: Introduce un número entero válido.");
                    scanner.nextLine();  // Limpiar entrada incorrecta
                }
            }
            
            // Crear el producto (puede lanzar excepción, pero ya validamos antes)
            try {
                productos[i] = new Producto(nombre, precio, stock);
            } catch (ProductoInvalidoException e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
            
            System.out.println();  // Línea en blanco entre productos
        }
        
        // 4. Mostrar todos los productos
        System.out.println("=== LISTA DE PRODUCTOS ===");
        mostrarProductos(productos);
        
        // 5. Ordenar los productos por precio
        Arrays.sort(productos);
        System.out.println("\n=== PRODUCTOS ORDENADOS POR PRECIO ===");
        mostrarProductos(productos);
        
        // 6. Mostrar el producto más caro (último después de ordenar de menor a mayor)
        System.out.println("\n=== PRODUCTO MÁS CARO ===");
        Producto masCaro = productos[productos.length - 1];
        System.out.println(masCaro);
        
        // 7. Reducir el stock de todos los productos en 2 unidades (sin bajar de 0)
        System.out.println("\n=== REDUCIENDO STOCK EN 2 UNIDADES ===");
        for (Producto p : productos) {
            int stockActual = p.getStock();
            int nuevoStock = stockActual - 2;
            if (nuevoStock < 0) {
                nuevoStock = 0;  // No puede ser negativo
            }
            try {
                p.setStock(nuevoStock);
            } catch (ProductoInvalidoException e) {
                // No debería ocurrir porque ya validamos que nuevoStock >= 0
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        // 8. Mostrar la lista final
        System.out.println("\n=== LISTA FINAL ===");
        mostrarProductos(productos);
        
        scanner.close();
    }
    
    /**
     * Muestra todos los productos del array.
     * @param productos Array de productos a mostrar
     */
    public static void mostrarProductos(Producto[] productos) {
        for (Producto p : productos) {
            System.out.println(p);
        }
    }
}
