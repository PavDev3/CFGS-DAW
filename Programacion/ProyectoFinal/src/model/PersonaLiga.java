package model;

// Clase abstracta base para cualquier persona de la liga (jugador o entrenador)
// calcularCosteMensual() es abstracto porque la formula cambia en cada subclase
public abstract class PersonaLiga {

    private String identificador; // ID unico en la liga (ej: "J001", "E001")
    private String nombre;
    private String nickname;
    private int edad;
    private double salarioBase;

    public PersonaLiga(String identificador, String nombre, String nickname,
                       int edad, double salarioBase) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.nickname = nickname;
        this.edad = edad;
        this.salarioBase = salarioBase;
    }

    // Getters
    public String getIdentificador() { return identificador; }
    public String getNombre() { return nombre; }
    public String getNickname() { return nickname; }
    public int getEdad() { return edad; }
    public double getSalarioBase() { return salarioBase; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public void setEdad(int edad) { this.edad = edad; }
    public void setSalarioBase(double salarioBase) { this.salarioBase = salarioBase; }

    // Muestra los datos basicos por consola
    // Las subclases llaman a super.mostrarResumen() y anaden su info especifica
    public void mostrarResumen() {
        System.out.println("ID: " + identificador
                + " | Nombre: " + nombre
                + " | Nickname: " + nickname
                + " | Edad: " + edad
                + " | Salario base: " + salarioBase + "€");
    }

    // Cada subclase calcula el coste total de forma diferente:
    // Jugador: salarioBase + (nivelMecanico + nivelEstrategico) * 100
    // Entrenador: salarioBase + (experiencia * 200)
    public abstract double calcularCosteMensual();

    @Override
    public String toString() {
        return "PersonaLiga{id='" + identificador + "', nickname='" + nickname + "'}";
    }
}
