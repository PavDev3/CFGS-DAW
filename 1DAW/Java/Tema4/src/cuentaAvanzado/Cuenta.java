package cuentaAvanzado;

public class Cuenta {
	protected double saldo;

	public Cuenta() {
		saldo = 0;
	}

	public Cuenta(double saldoInicial) {
		saldo = saldoInicial;
	}

	public double getSaldo() {
		return saldo;
	}

	public void ingresarDinero(double cantidad) {
		if (cantidad > 0) {
			saldo += cantidad;
		}
	}

	public void sacarDinero(double cantidad) throws SaldoInsuficienteException {
		if (cantidad > saldo) {
			throw new SaldoInsuficienteException("Saldo insuficiente");
		}
		saldo -= cantidad;
	}
}
