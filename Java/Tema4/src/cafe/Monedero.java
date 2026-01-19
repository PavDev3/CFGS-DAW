package cafe;

public class Monedero {
	//Atributos
	private double saldo = 25;
	
	// Constructor
	public Monedero() {
		
	}
	
	// Metodos
	public void setSaldo(double saldo) {
		this.saldo = saldo;
		
	}
	
	public double getSaldo() {
		return (float) saldo;
	}
	
	public String getSaldoFormateado() {
		return String.format("%.2f", saldo) + " €";
	}
	

}
