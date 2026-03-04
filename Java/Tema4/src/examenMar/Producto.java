package examenMar;

/**
 * Clase Producto para gestionar productos de una tienda.
 * Implementa Comparable para poder ordenar por precio.
 */
public class Producto implements Comparable<Producto> {
    
    // Atributos privados (encapsulación)
    private String nombre;
    private double precio;
    private int stock;
    
    /**
     * Constructor de Producto
     * @param nombre Nombre del producto
     * @param precio Precio del producto (debe ser >= 0)
     * @param stock Stock del producto (debe ser >= 0)
     * @throws ProductoInvalidoException si precio o stock son inválidos
     */
    public Producto(String nombre, double precio, int stock) throws ProductoInvalidoException {
        this.nombre = nombre;
        setPrecio(precio);  // Usamos setter para validar
        setStock(stock);    // Usamos setter para validar
    }
    
    // ==================== GETTERS ====================
    
    public String getNombre() {
        return nombre;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public int getStock() {
        return stock;
    }
    
    // ==================== SETTERS ====================
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Establece el precio del producto.
     * @param precio Precio a establecer (debe ser >= 0)
     * @throws ProductoInvalidoException si el precio es negativo
     */
    public void setPrecio(double precio) throws ProductoInvalidoException {
        if (precio < 0) {
            throw new ProductoInvalidoException("El precio no puede ser negativo: " + precio);
        }
        this.precio = precio;
    }
    
    /**
     * Establece el stock del producto.
     * @param stock Stock a establecer (debe ser >= 0)
     * @throws ProductoInvalidoException si el stock es negativo
     */
    public void setStock(int stock) throws ProductoInvalidoException {
        if (stock < 0) {
            throw new ProductoInvalidoException("El stock no puede ser negativo: " + stock);
        }
        this.stock = stock;
    }
    
    // ==================== OTROS MÉTODOS ====================
    
    /**
     * Devuelve una representación en texto del producto.
     */
    @Override
    public String toString() {
        return "Producto: " + nombre + " | Precio: " + precio + "€ | Stock: " + stock + " uds";
    }
    
    /**
     * Compara este producto con otro por precio.
     * SIN usar Double.compare (como pide el enunciado).
     * @return -1 si este precio es menor, 1 si es mayor, 0 si son iguales
     */
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
