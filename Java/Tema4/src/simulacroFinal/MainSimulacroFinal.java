package simulacroFinal;



public class MainSimulacroFinal {

	 public static void main(String[] args) {

	        // Array de Empleados
	        Empleado[] empleados = new Empleado[4];

	        double[][] horas1 = {
	            {4, 3},
	            {4, 4},
	            {5, 3},
	            {4, 4},
	            {3, 2}
	        };

	        double[][] horas2 = {
	            {5, 4},
	            {5, 5},
	            {4, 4},
	            {5, 3},
	            {4, 4}
	        };

	        double[][] horas3 = {
	            {3, 3},
	            {4, 3},
	            {4, 4},
	            {3, 3},
	            {4, 3}
	        };

	        double[][] horas4 = {
	            {5, 5},
	            {4, 4},
	            {5, 5},
	            {4, 3},
	            {5, 4}
	        };

	        empleados[0] = new Programador("P001", "HugIA",    Departamento.INFORMATICA,   horas1, 8);
	        empleados[1] = new Programador("P002", "Polaco", Departamento.INFORMATICA,   horas2, 3);
	        empleados[2] = new Administrativo("A001", "Josela",  Departamento.ADMINISTRACION, horas3, 12);
	        empleados[3] = new Administrativo("A002", "Filipo",  Departamento.RRHH,           horas4, 20);

	        // Mostrar datos de cada empleado 
	        System.out.println("GESTION DE PRODUCTIVIDAD DE EMPLEADOS:");
	        for (Empleado e : empleados) {
	        	System.out.println();
	            System.out.println("###############################################");
	            
;	            System.out.println(e);
	            System.out.println();
	            e.mostrarHorario();
	            System.out.printf("Horas totales semana : %.1f h%n", e.calcularHorasTotalesSemana());
	            System.out.printf("Productividad        : %.1f%n", e.calcularProductividad());
	            System.out.println("Merece reconocimiento: " + (e.mereceReconocimiento() ? "SÍ" : "NO"));
	        }

	        // Empleado con mayor productividad + conteo por tipo
	        System.out.println("ESTADISTICAS GLOBALES:");
	        Empleado mejorEmpleado = empleados[0];
	        int numProgramadores  = 0;
	        int numAdministrativos = 0;

	        for (Empleado e : empleados) {
	            if (e.calcularProductividad() > mejorEmpleado.calcularProductividad()) {
	                mejorEmpleado = e;
	            }
	            if (e instanceof Programador)    numProgramadores++;
	            if (e instanceof Administrativo) numAdministrativos++;
	        }

	        System.out.println("Empleado con mayor productividad: " + mejorEmpleado.getNombre() +
	                           " (" + String.format("%.1f", mejorEmpleado.calcularProductividad()) + ")");
	        System.out.println("Numero de Programadores  : " + numProgramadores);
	        System.out.println("Numero de Administrativos: " + numAdministrativos);
	        System.out.println();

	        // Media de horas por turno
	        double totalManana = 0;
	        double totalTarde  = 0;

	        for (Empleado e : empleados) {
	            for (int dia = 0; dia < 5; dia++) {
	                totalManana += e.getHorasTrabajadas()[dia][0];
	                totalTarde  += e.getHorasTrabajadas()[dia][1];
	            }
	        }

	        int totalDias = empleados.length * 5;
	        System.out.printf("Media horas turno mañana: %.2f h%n", totalManana / totalDias);
	        System.out.printf("Media horas turno tarde : %.2f h%n", totalTarde  / totalDias);
	        System.out.println();

	        // Empleado que ha trabajado más horas en un solo día 
	        Empleado empleadoMaxDia = empleados[0];
	        int      diaMaximo      = 0;
	        double   maxHorasDia    = empleados[0].calcularHorasDia(0);

	        for (Empleado e : empleados) {
	            for (int dia = 0; dia < 5; dia++) {
	                double horas = e.calcularHorasDia(dia);
	                if (horas > maxHorasDia) {
	                    maxHorasDia    = horas;
	                    empleadoMaxDia = e;
	                    diaMaximo      = dia;
	                }
	            }
	        }

	        String[] nombresDias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
	        System.out.println("Empleado con mas horas en un dia: " + empleadoMaxDia.getNombre() +
	                           " el " + nombresDias[diaMaximo] +
	                           " con " + maxHorasDia + " h");
	    }

}
