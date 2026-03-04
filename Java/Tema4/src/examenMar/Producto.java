package examenMar;

public class Producto implements Comparable<Producto> {
    
    // Atributos privados (encapsulación)
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) throws ProductoInvalidoException {
        this.nombre = nombre;
        setPrecio(precio);  // Usamos setter para validar
        setStock(stock);    // Usamos setter para validar
    }
    
    //getter
    
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
        this.nombre = nombre;
    }
    
    // Establecer precio
    public void setPrecio(double precio) throws ProductoInvalidoException {
        if (precio < 0) {
            throw new ProductoInvalidoException("El precio no puede ser negativo: " + precio);
        }
        this.precio = precio;
    }
    
    // Establecer stock 
    public void setStock(int stock) throws ProductoInvalidoException {
        if (stock < 0) {
            throw new ProductoInvalidoException("El stock no puede ser negativo: " + stock);
        }
        this.stock = stock;
    }
    
    @Override
    public String toString() {
        return "Producto: " + nombre + " | Precio: " + precio + "€ | Stock: " + stock + " uds";
    }
    
    
    @Override
    public int compareTo(Producto otro) {
        if (this.precio < otro.precio) {
            return -1;  // Este producto es más barato
        } else if (this.precio > otro.precio) {
            return 1;   // Este producto es más caro
        } else {
            return 0;   // Tienen el mismo precio
        }
    }
}
