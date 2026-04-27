package calificaciones;

import java.util.Random;

public class Calificaciones {

    private static final int NUM_ALUMNOS = 30;
    private static final int NUM_ASIGNATURAS = 5;

    private int[][] notas;
    private String[] asignaturas;

    // constructor: carga notas aleatoriamente
    public Calificaciones() {
        asignaturas = new String[]{"PROGRAMACION", "BD", "LMSGI", "SISTEMAS", "IPE"};
        notas = new int[NUM_ALUMNOS][NUM_ASIGNATURAS];
        Random rand = new Random();
        for (int i = 0; i < NUM_ALUMNOS; i++) {
            for (int j = 0; j < NUM_ASIGNATURAS; j++) {
                notas[i][j] = rand.nextInt(11); // 0..10
            }
        }
    }

    // cuenta cuantos alumnos tienen exactamente 'numSuspensos' asignaturas suspensas
    public int contarAlumnosConSuspensos(int numSuspensos) {
        int count = 0;
        for (int i = 0; i < NUM_ALUMNOS; i++) {
            int suspensos = 0;
            for (int j = 0; j < NUM_ASIGNATURAS; j++) {
                if (notas[i][j] < 5) {
                    suspensos++;
                }
            }
            if (suspensos == numSuspensos) {
                count++;
            }
        }
        return count;
    }

    // nota media de una asignatura por indice
    public double notaMediaAsignatura(int indice) {
        int suma = 0;
        for (int i = 0; i < NUM_ALUMNOS; i++) {
            suma += notas[i][indice];
        }
        return (double) suma / NUM_ALUMNOS;
    }

    // mostrar estadisticas completas
    public void mostrarEstadisticas() {
        System.out.println("=== Numero de alumnos por suspensos ===");
        for (int s = 0; s <= NUM_ASIGNATURAS; s++) {
            System.out.println("Con " + s + " asignatura(s) suspensa(s): " + contarAlumnosConSuspensos(s));
        }

        System.out.println("\n=== Nota media por asignatura ===");
        for (int j = 0; j < NUM_ASIGNATURAS; j++) {
            System.out.printf("La nota media de %s es %.2f%n", asignaturas[j], notaMediaAsignatura(j));
        }
    }
}
