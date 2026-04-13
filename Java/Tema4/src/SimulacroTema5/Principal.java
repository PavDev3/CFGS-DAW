package SimulacroTema5;

public class Principal {

    public static void main(String[] args) {

        // --- a) Crear array polimorfico ---

        CentroOperativo[] centros = new CentroOperativo[6];

        // Responsables
        Responsable r1 = new Responsable("11111111A", "Ana Garcia", 12);
        Responsable r2 = new Responsable("22222222B", "Luis Lopez", 5);
        Responsable r3 = new Responsable("33333333C", "Maria Ruiz", 15);
        Responsable r4 = new Responsable("44444444D", "Pedro Sanchez", 3);
        Responsable r5 = new Responsable("55555555E", "Laura Perez", 8);
        Responsable r6 = new Responsable("66666666F", "Carlos Gomez", 11);

        // Matrices de operaciones e incidencias para cada centro
        // [dia][turno] -> turno 0 = manana, turno 1 = tarde

        int[][] ops1 = {
            {10, 9},
            {8, 10},
            {11, 9},
            {10, 8},
            {9, 10}
        };
        int[][] inc1 = {
            {1, 2},
            {0, 1},
            {2, 1},
            {1, 0},
            {1, 1}
        };

        int[][] ops2 = {
            {5, 6},
            {7, 5},
            {6, 4},
            {8, 7},
            {5, 6}
        };
        int[][] inc2 = {
            {2, 3},
            {1, 2},
            {3, 2},
            {1, 1},
            {2, 3}
        };

        int[][] ops3 = {
            {12, 11},
            {10, 13},
            {11, 10},
            {12, 11},
            {13, 12}
        };
        int[][] inc3 = {
            {0, 1},
            {1, 0},
            {0, 0},
            {1, 1},
            {0, 1}
        };

        int[][] ops4 = {
            {9, 8},
            {10, 9},
            {8, 9},
            {11, 10},
            {9, 8}
        };
        int[][] inc4 = {
            {1, 0},
            {2, 1},
            {0, 1},
            {1, 2},
            {0, 1}
        };

        int[][] ops5 = {
            {3, 4},
            {5, 3},
            {4, 4},
            {3, 5},
            {4, 3}
        };
        int[][] inc5 = {
            {1, 2},
            {0, 1},
            {2, 1},
            {1, 1},
            {3, 2}
        };

        int[][] ops6 = {
            {8, 9},
            {9, 8},
            {10, 9},
            {8, 10},
            {9, 8}
        };
        int[][] inc6 = {
            {0, 0},
            {1, 0},
            {0, 1},
            {0, 0},
            {1, 0}
        };

        centros[0] = new CentroDistribucion("CD-001", Zona.NORTE, r1, ops1, inc1, 20);
        centros[1] = new CentroDistribucion("CD-002", Zona.SUR, r2, ops2, inc2, 10);
        centros[2] = new CentroDistribucion("CD-003", Zona.CENTRAL, r3, ops3, inc3, 35);
        centros[3] = new CentroSeguridad("CS-001", Zona.ESTE, r4, ops4, inc4, 5);
        centros[4] = new CentroSeguridad("CS-002", Zona.OESTE, r5, ops5, inc5, 2);
        centros[5] = new CentroSeguridad("CS-003", Zona.NORTE, r6, ops6, inc6, 8);

        // --- b) Mostrar toda la informacion ---

        System.out.println("============================================================");
        System.out.println("          INFORMACION DE TODOS LOS CENTROS");
        System.out.println("============================================================");

        for (int i = 0; i < centros.length; i++) {
            System.out.println("\n" + centros[i].toString());
            System.out.println("Responsable: " + centros[i].getResponsable().toString());
            centros[i].mostrarResumenSemanal();
            System.out.println("Total operaciones: " + centros[i].calcularTotalOperaciones());
            System.out.println("Total incidencias: " + centros[i].calcularTotalIncidencias());
            System.out.printf("Tasa de incidencias: %.2f%%%n", centros[i].calcularTasaIncidencias());
            System.out.printf("Indice de eficiencia: %.2f%n", centros[i].calcularIndiceEficiencia());
            System.out.println("Necesita auditoria: " + centros[i].necesitaAuditoria());
        }

        // --- c) Centro con mayor indice de eficiencia ---

        System.out.println("\n============================================================");
        System.out.println("     CENTRO CON MAYOR INDICE DE EFICIENCIA");
        System.out.println("============================================================");

        CentroOperativo mejorCentro = centros[0];
        for (int i = 1; i < centros.length; i++) {
            if (centros[i].calcularIndiceEficiencia() > mejorCentro.calcularIndiceEficiencia()) {
                mejorCentro = centros[i];
            }
        }

        System.out.println("Codigo: " + mejorCentro.getCodigo());
        System.out.println("Tipo: " + mejorCentro.getClass().getSimpleName());
        System.out.println("Responsable: " + mejorCentro.getResponsable().getNombre());
        System.out.printf("Indice de eficiencia: %.2f%n", mejorCentro.calcularIndiceEficiencia());

        // --- d) Centro con peor tasa de incidencias ---

        System.out.println("\n============================================================");
        System.out.println("     CENTRO CON PEOR TASA DE INCIDENCIAS");
        System.out.println("============================================================");

        CentroOperativo peorCentro = centros[0];
        for (int i = 1; i < centros.length; i++) {
            if (centros[i].calcularTasaIncidencias() > peorCentro.calcularTasaIncidencias()) {
                peorCentro = centros[i];
            }
        }

        System.out.println("Codigo: " + peorCentro.getCodigo());
        System.out.printf("Tasa de incidencias: %.2f%%%n", peorCentro.calcularTasaIncidencias());
        System.out.println("Tipo: " + peorCentro.getClass().getSimpleName());

        // --- e) Contar tipos con instanceof ---

        System.out.println("\n============================================================");
        System.out.println("     CONTEO DE TIPOS");
        System.out.println("============================================================");

        int numDistribucion = 0;
        int numSeguridad = 0;

        for (int i = 0; i < centros.length; i++) {
            if (centros[i] instanceof CentroDistribucion) {
                numDistribucion++;
            } else if (centros[i] instanceof CentroSeguridad) {
                numSeguridad++;
            }
        }

        System.out.println("Centros de distribucion: " + numDistribucion);
        System.out.println("Centros de seguridad: " + numSeguridad);

        // --- f) Calcular medias globales por turno ---

        System.out.println("\n============================================================");
        System.out.println("     MEDIAS GLOBALES POR TURNO");
        System.out.println("============================================================");

        int totalOpManana = 0;
        int totalOpTarde = 0;
        int totalIncManana = 0;
        int totalIncTarde = 0;
        int totalCeldas = centros.length * 5; // 6 centros x 5 dias

        for (int i = 0; i < centros.length; i++) {
            for (int dia = 0; dia < 5; dia++) {
                totalOpManana  += centros[i].getOperaciones()[dia][0];
                totalOpTarde   += centros[i].getOperaciones()[dia][1];
                totalIncManana += centros[i].getIncidencias()[dia][0];
                totalIncTarde  += centros[i].getIncidencias()[dia][1];
            }
        }

        System.out.printf("Media operaciones turno manana: %.2f%n", (double) totalOpManana / totalCeldas);
        System.out.printf("Media operaciones turno tarde:  %.2f%n", (double) totalOpTarde / totalCeldas);
        System.out.printf("Media incidencias turno manana: %.2f%n", (double) totalIncManana / totalCeldas);
        System.out.printf("Media incidencias turno tarde:  %.2f%n", (double) totalIncTarde / totalCeldas);

        // --- g) Buscar el dia mas problematico ---

        System.out.println("\n============================================================");
        System.out.println("     DIA MAS PROBLEMATICO");
        System.out.println("============================================================");

        String[] diasSemana = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
        int[] incidenciasPorDia = new int[5];

        for (int i = 0; i < centros.length; i++) {
            for (int dia = 0; dia < 5; dia++) {
                incidenciasPorDia[dia] += centros[i].calcularIncidenciasDia(dia);
            }
        }

        int diaMasProblematico = 0;
        for (int dia = 1; dia < 5; dia++) {
            if (incidenciasPorDia[dia] > incidenciasPorDia[diaMasProblematico]) {
                diaMasProblematico = dia;
            }
        }

        System.out.println("Dia mas problematico: " + diasSemana[diaMasProblematico]);
        System.out.println("Total incidencias ese dia: " + incidenciasPorDia[diaMasProblematico]);

        // --- h) Centro mas equilibrado ---

        System.out.println("\n============================================================");
        System.out.println("     CENTRO MAS EQUILIBRADO");
        System.out.println("============================================================");

        CentroOperativo centroEquilibrado = centros[0];
        int menorDiferencia = Math.abs(centros[0].calcularTotalOperaciones() - centros[0].calcularTotalIncidencias());

        for (int i = 1; i < centros.length; i++) {
            int diferencia = Math.abs(centros[i].calcularTotalOperaciones() - centros[i].calcularTotalIncidencias());
            if (diferencia < menorDiferencia) {
                menorDiferencia = diferencia;
                centroEquilibrado = centros[i];
            }
        }

        System.out.println("Codigo: " + centroEquilibrado.getCodigo());
        System.out.println("Diferencia absoluta: " + menorDiferencia);
        System.out.println("Tipo: " + centroEquilibrado.getClass().getSimpleName());

        // --- i) Centros con responsable con antiguedad > 10 anios ---

        System.out.println("\n============================================================");
        System.out.println("     CENTROS CON RESPONSABLE DE MAS DE 10 ANIOS");
        System.out.println("============================================================");

        boolean hayAlguno = false;
        for (int i = 0; i < centros.length; i++) {
            if (centros[i].getResponsable().getAntiguedad() > 10) {
                System.out.println("Codigo: " + centros[i].getCodigo());
                System.out.println("Responsable: " + centros[i].getResponsable().getNombre());
                System.out.println("Antiguedad: " + centros[i].getResponsable().getAntiguedad() + " anios");
                System.out.println("Tipo: " + centros[i].getClass().getSimpleName());
                System.out.println("---");
                hayAlguno = true;
            }
        }
        if (!hayAlguno) {
            System.out.println("No hay centros con responsable de mas de 10 anios.");
        }

        // --- j) Centros con >40 operaciones, <10 incidencias y sin auditoria ---

        System.out.println("\n============================================================");
        System.out.println("     CENTROS CON MAS DE 40 OP, MENOS DE 10 INC Y SIN AUDITORIA");
        System.out.println("============================================================");

        boolean encontrado = false;
        for (int i = 0; i < centros.length; i++) {
            if (centros[i].calcularTotalOperaciones() > 40
                    && centros[i].calcularTotalIncidencias() < 10
                    && !centros[i].necesitaAuditoria()) {
                System.out.println(centros[i].toString());
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Ningun centro cumple esas condiciones.");
        }
    }
}
