package empresa;

public class mainEmpleados {

	public static void main(String[] args) {
		Operario op = new Operario("Luis", 1100, 3);
		Informatico inf = new Informatico("Ana", 2400, "DESARROLLO");
		Directivo dir = new Directivo("Carlos", 3200, "Ventas");

		System.out.println(op.toString());
		System.out.println(inf.toString());
		System.out.println(dir.toString());

		// probar que el sueldo maximo se respeta
		op.setSueldo(1500);
		System.out.println("Sueldo operario tras intentar subir a 1500: " + op.getSueldo());

		// probar especialidad invalida
		inf.setEspecialidad("REDES");
		System.out.println("Especialidad tras intentar poner REDES: " + inf.getEspecialidad());

		// probar nave invalida
		op.setNave(7);
		System.out.println("Nave tras intentar poner 7: " + op.getNave());
	}
}
