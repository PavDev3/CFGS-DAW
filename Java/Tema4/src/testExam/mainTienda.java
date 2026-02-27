package testExam;

import java.util.*;
public class mainTienda {

	
	public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        ControlTienda control = new ControlTienda();
	        Producto producto;
	        boolean crearProductos = false;

	        while (true) {
	            System.out.println("Menú de Tienda:");
	            System.out.println("1. Crear los 5 productos");
	            System.out.println("2. Lista los productos");
	            System.out.println("3. Ordenar por precio");
	            System.out.println("4. Mostrar el más caro");
	            System.out.println("5. Vender producto");
	            System.out.println("6. Salir");
	            System.out.print("Seleccione una opción: ");
	            int opcion = scanner.nextInt();
	            scanner.nextLine(); // Consume the newline

	            switch (opcion) {
	                case 1:
	                    if (!crearProductos) {
	                        for (int i = 0; i < 5; i++) {
	                            System.out.println("Producto " + (i + 1));
	                            System.out.print("Nombre: ");
	                            String nombre = scanner.nextLine();
	                            System.out.print("Precio: ");
	                            double precio = Double.parseDouble(scanner.nextLine());
	                            System.out.print("Stock: ");
	                            int stock = Integer.parseInt(scanner.nextLine());
	                            producto = new Producto(nombre, precio, stock);
	                            control.agregarProducto(producto);
	                        }
	                        crearProductos = true;
	                    } else {
	                        System.out.println("Ya se han creado los productos.");
	                    }
	                    break;
	                case 2:
	                    if (crearProductos) {
	                        System.out.println("Lista de productos:");
	                        control.listaProductos();
	                    } else {
	                        System.out.println("Debe crear los productos primero.");
	                    }
	                    break;
	                case 3:
	                    if (crearProductos) {
	                        control.ordenarProducto();
	                        System.out.println("Productos ordenados por precio.");
	                    } else {
	                        System.out.println("Debe crear los productos primero.");
	                    }
	                    break;
	                case 4:
	                    if (crearProductos) {
	                        Producto productoMasCaro = control.productoMasCaro();
	                        if (productoMasCaro != null) {
	                            System.out.println("El producto más caro es: " + productoMasCaro.getNombre() + " con un precio de " + productoMasCaro.getPrecio());
	                        }
	                    } else {
	                        System.out.println("Debe crear los productos primero.");
	                    }
	                    break;
	                case 5:
	                    if (crearProductos) {
	                        System.out.print("Ingrese el nombre del producto a vender: ");
	                        String nombreVender = scanner.nextLine();
	                        System.out.print("Ingrese la cantidad a vender: ");
	                        int cantidadVender = scanner.nextInt(); // Use scanner.nextInt() here
	                        control.venderProducto(nombreVender, cantidadVender);
	                    } else {
	                        System.out.println("Debe crear los productos primero.");
	                    }
	                    break;
	                case 6:
	                    System.out.println("Saliendo...");
	                    return;
	                default:
	                    System.out.println("Opción inválida. Intente de nuevo.");
	            }
	        }
	    }
	}
