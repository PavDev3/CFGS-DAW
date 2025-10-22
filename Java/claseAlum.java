import java.util.Random;
import java.util.Scanner;

public class claseAlum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int numeroAlumnos = random.nextInt(20) + 1;
        int i = 0;
        double porcentajeAprobados;
        double porcentajeSuspensos;
        int aprobados = 0;
        int suspensos = 0;
        System.out.println("El numero de alumnos es: " + numeroAlumnos);
        while (i < numeroAlumnos) {
            int nota = random.nextInt(10);
            System.out.println("El alumno " + i + " tiene una nota de " + nota);
            i++;
            if (nota >= 5) {
                aprobados++;
            } else {
                suspensos++;
            }
        }
        porcentajeAprobados = (aprobados * 100) / numeroAlumnos;
        porcentajeSuspensos = (suspensos * 100) / numeroAlumnos;
        System.out.println("El porcentaje de aprobados es: " + porcentajeAprobados + "%");
        System.out.println("El porcentaje de suspensos es: " + porcentajeSuspensos + "%");
        scanner.close();
    }
}
