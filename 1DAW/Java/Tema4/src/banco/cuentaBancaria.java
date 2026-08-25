package banco;

public class cuentaBancaria {
	// Atributos privados
	private String titular;
	private double saldo;
	
	// Constructor
	public cuentaBancaria(String titular, double saldoInicial) throws IllegalArgumentException, CuentaSinTitularException { 	
			if ( titular == null || titular.trim().isEmpty()) {
				throw new CuentaSinTitularException("El nombre del titular no puede estar vacio.");
			}

			if (saldoInicial < 0) {
				throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
			}
		
		this.titular = titular;
		this.saldo = saldoInicial;
	
	}
	// Metodo ingresar
	public void ingresar(double cantidad) {
		if (cantidad <= 0) {
			throw new IllegalArgumentException("La cantidad a ingresar debe ser mayor que 0.");
		}
		saldo += cantidad;
	}
	
	// Metodo retirar
	public void retirar(double cantidad) throws SaldoInsuficienteException {
		if (cantidad <= 0) {
			throw new IllegalArgumentException("La cantidad a retirar debe ser mayor que 0.");
		}
		if (cantidad > saldo) {
			throw new SaldoInsuficienteException("Saldo insuficiente. Saldo actual: " + saldo + ", cantidad solicitada: " + cantidad);
		}
		saldo -= cantidad;
	}
	
	// Getter saldo
	public double getSaldo() {
		return saldo;
	}
	
	// toString
	public String toString() {
		return "Cuenta de " + titular + " | Saldo: " + saldo + " euros";
	}
}
