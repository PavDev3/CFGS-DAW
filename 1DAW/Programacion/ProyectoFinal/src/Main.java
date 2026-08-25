import exceptions.JugadorSancionadoException;
import exceptions.RolNoDisponibleException;
import model.*;
import structures.Liga;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Liga liga = new Liga("Liga Nacional de E-Sports");

        // Cargar datos de ejemplo para poder probar todas las funcionalidades
        inicializarDatos(liga);

        System.out.println("Bienvenido a la " + liga.getNombre());

        boolean salir = false;
        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = leerInt(scanner);
            switch (opcion) {
                case 1:  menuPersonas(scanner, liga);       break;
                case 2:  menuEquipos(scanner, liga);        break;
                case 3:  menuFichajes(scanner, liga);       break;
                case 4:  menuCalendario(scanner, liga);     break;
                case 5:  menuColaPartidos(scanner, liga);   break;
                case 6:  menuRegistrarPartido(scanner, liga); break;
                case 7:  menuIncidencias(scanner, liga);    break;
                case 8:  liga.mostrarClasificacion();       break;
                case 9:  menuEstadisticas(scanner, liga);   break;
                case 10: liga.mostrarHistorialAcciones();   break;
                case 11: System.out.println(liga.deshacerUltimaSancion()); break;
                case 12: salir = true; System.out.println("Hasta pronto."); break;
                default: System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }

    // ── MENÚ PRINCIPAL ─────────────────────────────────────────────────────

    private static void mostrarMenuPrincipal() {
        System.out.println("\n========================================");
        System.out.println("       LIGA DE E-SPORTS - MENÚ");
        System.out.println("========================================");
        System.out.println(" 1. Gestión de personas de la liga");
        System.out.println(" 2. Gestión de equipos");
        System.out.println(" 3. Gestión de fichajes y convocatorias");
        System.out.println(" 4. Gestión del calendario");
        System.out.println(" 5. Cola de partidos pendientes");
        System.out.println(" 6. Registrar partido jugado");
        System.out.println(" 7. Gestión de incidencias y sanciones");
        System.out.println(" 8. Mostrar clasificación");
        System.out.println(" 9. Mostrar estadísticas");
        System.out.println("10. Historial de acciones");
        System.out.println("11. Deshacer última sanción");
        System.out.println("12. Salir");
        System.out.print("Elige una opción: ");
    }

    // ── MENÚ PERSONAS ──────────────────────────────────────────────────────

    private static void menuPersonas(Scanner scanner, Liga liga) {
        System.out.println("\n--- Gestión de personas ---");
        System.out.println("1. Añadir jugador");
        System.out.println("2. Añadir entrenador");
        System.out.println("3. Listar todas las personas");
        System.out.println("4. Buscar persona por ID");
        System.out.println("5. Modificar persona");
        System.out.println("6. Eliminar persona");
        System.out.println("0. Volver");
        System.out.print("Opción: ");

        switch (leerInt(scanner)) {
            case 1: añadirJugador(scanner, liga);      break;
            case 2: añadirEntrenador(scanner, liga);   break;
            case 3: listarPersonas(liga);              break;
            case 4: buscarPersona(scanner, liga);      break;
            case 5: modificarPersona(scanner, liga);   break;
            case 6: eliminarPersona(scanner, liga);    break;
            case 0: break;
            default: System.out.println("Opción no válida.");
        }
    }

    private static void añadirJugador(Scanner scanner, Liga liga) {
        System.out.println("-- Nuevo jugador --");
        System.out.print("ID: "); String id = scanner.nextLine();
        System.out.print("Nombre: "); String nombre = scanner.nextLine();
        System.out.print("Nickname: "); String nick = scanner.nextLine();
        System.out.print("Edad: "); int edad = leerInt(scanner);
        System.out.print("Salario base: "); double salario = leerDouble(scanner);
        System.out.println("Rol (0=TOP, 1=JUNGLE, 2=MID, 3=ADC, 4=SUPPORT):");
        int rolIdx = leerInt(scanner);
        if (rolIdx < 0 || rolIdx > 4) { System.out.println("Rol no válido."); return; }
        Rol rol = Rol.values()[rolIdx];
        System.out.print("Nivel mecánico (1-10): "); int mec = leerInt(scanner);
        System.out.print("Nivel estratégico (1-10): "); int est = leerInt(scanner);

        try {
            Jugador j = new Jugador(id, nombre, nick, edad, salario, rol, mec, est);
            liga.registrarPersona(j);
            System.out.println("Jugador " + nick + " registrado correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void añadirEntrenador(Scanner scanner, Liga liga) {
        System.out.println("-- Nuevo entrenador --");
        System.out.print("ID: "); String id = scanner.nextLine();
        System.out.print("Nombre: "); String nombre = scanner.nextLine();
        System.out.print("Nickname: "); String nick = scanner.nextLine();
        System.out.print("Edad: "); int edad = leerInt(scanner);
        System.out.print("Salario base: "); double salario = leerDouble(scanner);
        System.out.print("Años de experiencia: "); int exp = leerInt(scanner);
        System.out.print("Especialidad: "); String esp = scanner.nextLine();

        try {
            Entrenador e = new Entrenador(id, nombre, nick, edad, salario, exp, esp);
            liga.registrarPersona(e);
            System.out.println("Entrenador " + nick + " registrado correctamente.");
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void listarPersonas(Liga liga) {
        System.out.println("-- Lista de personas registradas --");
        if (liga.getPersonas().isEmpty()) {
            System.out.println("No hay personas registradas.");
            return;
        }
        for (PersonaLiga p : liga.getPersonas()) {
            p.mostrarResumen();
            System.out.println();
        }
    }

    private static void buscarPersona(Scanner scanner, Liga liga) {
        System.out.print("ID a buscar: ");
        String id = scanner.nextLine();
        PersonaLiga p = liga.buscarPersonaPorId(id);
        if (p == null) {
            System.out.println("No se encontró ninguna persona con ID: " + id);
        } else {
            p.mostrarResumen();
        }
    }

    private static void modificarPersona(Scanner scanner, Liga liga) {
        System.out.print("ID de la persona a modificar: ");
        String id = scanner.nextLine();
        PersonaLiga p = liga.buscarPersonaPorId(id);
        if (p == null) { System.out.println("No encontrado."); return; }

        System.out.println("¿Qué quieres modificar?");
        System.out.println("1. Nombre  2. Nickname  3. Edad  4. Salario base");
        System.out.print("Opción: ");
        switch (leerInt(scanner)) {
            case 1:
                System.out.print("Nuevo nombre: ");
                p.setNombre(scanner.nextLine());
                System.out.println("Nombre actualizado.");
                break;
            case 2:
                System.out.print("Nuevo nickname: ");
                p.setNickname(scanner.nextLine());
                System.out.println("Nickname actualizado.");
                break;
            case 3:
                System.out.print("Nueva edad: ");
                p.setEdad(leerInt(scanner));
                System.out.println("Edad actualizada.");
                break;
            case 4:
                System.out.print("Nuevo salario base: ");
                p.setSalarioBase(leerDouble(scanner));
                System.out.println("Salario actualizado.");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void eliminarPersona(Scanner scanner, Liga liga) {
        System.out.print("ID a eliminar: ");
        String id = scanner.nextLine();
        if (liga.eliminarPersona(id)) {
            System.out.println("Persona eliminada.");
        } else {
            System.out.println("No se encontró ninguna persona con ese ID.");
        }
    }

    // ── MENÚ EQUIPOS ───────────────────────────────────────────────────────

    private static void menuEquipos(Scanner scanner, Liga liga) {
        System.out.println("\n--- Gestión de equipos ---");
        System.out.println("1. Crear equipo");
        System.out.println("2. Listar equipos");
        System.out.println("3. Ver plantilla de un equipo");
        System.out.println("4. Asignar entrenador a un equipo");
        System.out.println("0. Volver");
        System.out.print("Opción: ");

        switch (leerInt(scanner)) {
            case 1: crearEquipo(scanner, liga);            break;
            case 2: listarEquipos(liga);                   break;
            case 3: verPlantilla(scanner, liga);           break;
            case 4: asignarEntrenador(scanner, liga);      break;
            case 0: break;
            default: System.out.println("Opción no válida.");
        }
    }

    private static void crearEquipo(Scanner scanner, Liga liga) {
        System.out.print("Nombre del equipo: "); String nombre = scanner.nextLine();
        System.out.print("Ciudad: "); String ciudad = scanner.nextLine();
        System.out.print("Presupuesto: "); double presupuesto = leerDouble(scanner);

        try {
            Equipo e = new Equipo(nombre, ciudad, presupuesto);
            liga.registrarEquipo(e);
            System.out.println("Equipo " + nombre + " creado.");
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void listarEquipos(Liga liga) {
        if (liga.getEquipos().isEmpty()) {
            System.out.println("No hay equipos registrados.");
            return;
        }
        for (Equipo e : liga.getEquipos()) {
            System.out.println(e);
        }
    }

    private static void verPlantilla(Scanner scanner, Liga liga) {
        System.out.print("Nombre del equipo: ");
        String nombre = scanner.nextLine();
        Equipo e = liga.buscarEquipoPorNombre(nombre);
        if (e == null) { System.out.println("Equipo no encontrado."); return; }
        e.mostrarPlantillaCompleta();
    }

    private static void asignarEntrenador(Scanner scanner, Liga liga) {
        System.out.print("Nombre del equipo: ");
        String nombreEquipo = scanner.nextLine();
        Equipo equipo = liga.buscarEquipoPorNombre(nombreEquipo);
        if (equipo == null) { System.out.println("Equipo no encontrado."); return; }

        System.out.print("ID del entrenador: ");
        String idEntrenador = scanner.nextLine();
        PersonaLiga p = liga.buscarPersonaPorId(idEntrenador);
        if (!(p instanceof Entrenador)) {
            System.out.println("No se encontró ningún entrenador con ese ID.");
            return;
        }
        equipo.setEntrenador((Entrenador) p);
        System.out.println("Entrenador " + p.getNickname() + " asignado a " + nombreEquipo);
    }

    // ── MENÚ FICHAJES ──────────────────────────────────────────────────────

    private static void menuFichajes(Scanner scanner, Liga liga) {
        System.out.println("\n--- Gestión de fichajes y convocatorias ---");
        System.out.println("1. Fichar jugador como titular");
        System.out.println("2. Fichar jugador como suplente");
        System.out.println("3. Intercambiar titular y suplente");
        System.out.println("4. Eliminar jugador de un equipo");
        System.out.println("5. Ver coste del equipo");
        System.out.println("0. Volver");
        System.out.print("Opción: ");

        switch (leerInt(scanner)) {
            case 1: ficharTitular(scanner, liga);          break;
            case 2: ficharSuplente(scanner, liga);         break;
            case 3: intercambiarJugadores(scanner, liga);  break;
            case 4: eliminarDeEquipo(scanner, liga);       break;
            case 5: verCosteEquipo(scanner, liga);         break;
            case 0: break;
            default: System.out.println("Opción no válida.");
        }
    }

    private static void ficharTitular(Scanner scanner, Liga liga) {
        System.out.print("Nombre del equipo: ");
        String nombreEquipo = scanner.nextLine();
        Equipo equipo = liga.buscarEquipoPorNombre(nombreEquipo);
        if (equipo == null) { System.out.println("Equipo no encontrado."); return; }

        System.out.print("ID del jugador: ");
        PersonaLiga p = liga.buscarPersonaPorId(scanner.nextLine());
        if (!(p instanceof Jugador)) { System.out.println("Jugador no encontrado."); return; }

        try {
            equipo.ficharTitular((Jugador) p);
            System.out.println("Jugador fichado como titular.");
        } catch (RolNoDisponibleException | JugadorSancionadoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void ficharSuplente(Scanner scanner, Liga liga) {
        System.out.print("Nombre del equipo: ");
        String nombreEquipo = scanner.nextLine();
        Equipo equipo = liga.buscarEquipoPorNombre(nombreEquipo);
        if (equipo == null) { System.out.println("Equipo no encontrado."); return; }

        System.out.print("ID del jugador: ");
        PersonaLiga p = liga.buscarPersonaPorId(scanner.nextLine());
        if (!(p instanceof Jugador)) { System.out.println("Jugador no encontrado."); return; }

        try {
            equipo.ficharSuplente((Jugador) p);
            System.out.println("Jugador fichado como suplente.");
        } catch (JugadorSancionadoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void intercambiarJugadores(Scanner scanner, Liga liga) {
        System.out.print("Nombre del equipo: ");
        Equipo equipo = liga.buscarEquipoPorNombre(scanner.nextLine());
        if (equipo == null) { System.out.println("Equipo no encontrado."); return; }

        System.out.print("Nickname del titular a reemplazar: ");
        String nickTitular = scanner.nextLine();
        System.out.print("Nickname del suplente que entra: ");
        String nickSuplente = scanner.nextLine();

        try {
            equipo.intercambiarTitularSuplente(nickTitular, nickSuplente);
            System.out.println("Intercambio realizado.");
        } catch (RolNoDisponibleException | JugadorSancionadoException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void eliminarDeEquipo(Scanner scanner, Liga liga) {
        System.out.print("Nombre del equipo: ");
        Equipo equipo = liga.buscarEquipoPorNombre(scanner.nextLine());
        if (equipo == null) { System.out.println("Equipo no encontrado."); return; }

        System.out.print("Nickname del jugador a eliminar: ");
        String nick = scanner.nextLine();
        if (equipo.eliminarJugador(nick)) {
            System.out.println("Jugador eliminado del equipo.");
        } else {
            System.out.println("No se encontró ese jugador en el equipo.");
        }
    }

    private static void verCosteEquipo(Scanner scanner, Liga liga) {
        System.out.print("Nombre del equipo: ");
        Equipo equipo = liga.buscarEquipoPorNombre(scanner.nextLine());
        if (equipo == null) { System.out.println("Equipo no encontrado."); return; }
        System.out.printf("Coste mensual total de %s: %.2f€%n",
                equipo.getNombre(), equipo.calcularCosteEquipo());
    }

    // ── MENÚ CALENDARIO ────────────────────────────────────────────────────

    private static void menuCalendario(Scanner scanner, Liga liga) {
        System.out.println("\n--- Gestión del calendario ---");
        System.out.println("1. Registrar partido");
        System.out.println("2. Ver todos los partidos");
        System.out.println("3. Ver partidos de una jornada");
        System.out.println("4. Ver matriz de puntos por jornada");
        System.out.println("0. Volver");
        System.out.print("Opción: ");

        switch (leerInt(scanner)) {
            case 1: registrarPartido(scanner, liga);       break;
            case 2: verTodosPartidos(liga);                break;
            case 3: verPartidosJornada(scanner, liga);     break;
            case 4: liga.mostrarMatrizPuntos();            break;
            case 0: break;
            default: System.out.println("Opción no válida.");
        }
    }

    private static void registrarPartido(Scanner scanner, Liga liga) {
        System.out.print("ID del partido (ej: P007): ");
        String id = scanner.nextLine();
        System.out.print("Jornada (1-5): ");
        int jornada = leerInt(scanner);
        System.out.print("Nombre del equipo local: ");
        Equipo local = liga.buscarEquipoPorNombre(scanner.nextLine());
        if (local == null) { System.out.println("Equipo local no encontrado."); return; }
        System.out.print("Nombre del equipo visitante: ");
        Equipo visitante = liga.buscarEquipoPorNombre(scanner.nextLine());
        if (visitante == null) { System.out.println("Equipo visitante no encontrado."); return; }

        try {
            Partido p = new Partido(id, jornada, local, visitante);
            liga.registrarPartido(p);
            System.out.println("Partido registrado y añadido a la cola de pendientes.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void verTodosPartidos(Liga liga) {
        if (liga.getPartidos().isEmpty()) {
            System.out.println("No hay partidos registrados.");
            return;
        }
        for (Partido p : liga.getPartidos()) {
            p.mostrarResultado();
        }
    }

    private static void verPartidosJornada(Scanner scanner, Liga liga) {
        System.out.print("Número de jornada (1-5): ");
        int jornada = leerInt(scanner);
        boolean encontrado = false;
        for (Partido p : liga.getPartidos()) {
            if (p.getJornada() == jornada) {
                p.mostrarResultado();
                encontrado = true;
            }
        }
        if (!encontrado) System.out.println("No hay partidos para esa jornada.");
    }

    // ── MENÚ COLA DE PARTIDOS ──────────────────────────────────────────────

    private static void menuColaPartidos(Scanner scanner, Liga liga) {
        System.out.println("\n--- Cola de partidos pendientes ---");
        System.out.println("1. Ver próximo partido");
        System.out.println("2. Ver toda la cola");
        System.out.println("3. Limpiar la cola");
        System.out.println("0. Volver");
        System.out.print("Opción: ");

        switch (leerInt(scanner)) {
            case 1:
                Partido proximo = liga.verProximoPartido();
                if (proximo == null) {
                    System.out.println("No hay partidos pendientes en la cola.");
                } else {
                    proximo.mostrarResultado();
                }
                break;
            case 2:
                if (liga.getColaPartidos().isEmpty()) {
                    System.out.println("La cola está vacía.");
                } else {
                    System.out.println("Partidos pendientes (orden FIFO):");
                    for (Partido p : liga.getColaPartidos()) {
                        p.mostrarResultado();
                    }
                }
                break;
            case 3:
                liga.getColaPartidos().clear();
                System.out.println("Cola vaciada.");
                break;
            case 0: break;
            default: System.out.println("Opción no válida.");
        }
    }

    // ── MENÚ REGISTRAR PARTIDO JUGADO ──────────────────────────────────────

    private static void menuRegistrarPartido(Scanner scanner, Liga liga) {
        Partido partido = liga.jugarProximoPartido();
        if (partido == null) {
            System.out.println("No hay partidos pendientes en la cola.");
            return;
        }

        System.out.println("Registrando: " + partido.getEquipoLocal().getNombre()
                + " vs " + partido.getEquipoVisitante().getNombre()
                + " (Jornada " + partido.getJornada() + ")");

        System.out.print("Puntos equipo local: ");
        int golesLocal = leerInt(scanner);
        System.out.print("Puntos equipo visitante: ");
        int golesVisitante = leerInt(scanner);
        System.out.print("ID del jugador MVP: ");
        PersonaLiga mvpPersona = liga.buscarPersonaPorId(scanner.nextLine());
        if (!(mvpPersona instanceof Jugador)) {
            System.out.println("MVP no válido. Operación cancelada.");
            liga.getColaPartidos().add(partido); // devuelve el partido a la cola
            return;
        }

        try {
            partido.registrarResultado(golesLocal, golesVisitante, (Jugador) mvpPersona);
            liga.actualizarMatrizPuntos(partido);
            System.out.println("Resultado registrado correctamente.");
            partido.mostrarResultado();
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ── MENÚ INCIDENCIAS ───────────────────────────────────────────────────

    private static void menuIncidencias(Scanner scanner, Liga liga) {
        System.out.println("\n--- Gestión de incidencias y sanciones ---");
        System.out.println("1. Registrar incidencia");
        System.out.println("2. Aplicar sanción a jugador");
        System.out.println("3. Listar incidencias");
        System.out.println("4. Buscar incidencias por equipo");
        System.out.println("5. Buscar incidencias por jugador");
        System.out.println("0. Volver");
        System.out.print("Opción: ");

        switch (leerInt(scanner)) {
            case 1: registrarIncidencia(scanner, liga);      break;
            case 2: aplicarSancion(scanner, liga);           break;
            case 3: listarIncidencias(liga);                 break;
            case 4: buscarIncidenciasPorEquipo(scanner, liga); break;
            case 5: buscarIncidenciasPorJugador(scanner, liga); break;
            case 0: break;
            default: System.out.println("Opción no válida.");
        }
    }

    private static void registrarIncidencia(Scanner scanner, Liga liga) {
        System.out.print("ID de la incidencia (ej: I001): ");
        String id = scanner.nextLine();
        System.out.println("Tipo (0=SANCION, 1=EXPULSION, 2=ERROR_TECNICO, 3=PARTIDO_APLAZADO, 4=OTRO):");
        int tipoIdx = leerInt(scanner);
        if (tipoIdx < 0 || tipoIdx > 4) { System.out.println("Tipo no válido."); return; }
        TipoIncidencia tipo = TipoIncidencia.values()[tipoIdx];
        System.out.print("Descripción: ");
        String desc = scanner.nextLine();
        System.out.print("Fecha (DD/MM/YYYY): ");
        String fecha = scanner.nextLine();
        System.out.print("ID del jugador afectado (Enter para ninguno): ");
        String idJugador = scanner.nextLine();
        System.out.print("Nombre del equipo afectado (Enter para ninguno): ");
        String nombreEquipo = scanner.nextLine();

        Jugador jugador = null;
        if (!idJugador.isEmpty()) {
            PersonaLiga p = liga.buscarPersonaPorId(idJugador);
            if (p instanceof Jugador) jugador = (Jugador) p;
        }
        Equipo equipo = nombreEquipo.isEmpty() ? null : liga.buscarEquipoPorNombre(nombreEquipo);

        Incidencia inc = new Incidencia(id, tipo, desc, jugador, equipo, fecha);
        liga.registrarIncidencia(inc);
        System.out.println("Incidencia registrada.");
    }

    private static void aplicarSancion(Scanner scanner, Liga liga) {
        System.out.print("ID del jugador a sancionar: ");
        PersonaLiga p = liga.buscarPersonaPorId(scanner.nextLine());
        if (!(p instanceof Jugador)) { System.out.println("Jugador no encontrado."); return; }
        liga.aplicarSancion((Jugador) p);
        System.out.println("Sanción aplicada a " + p.getNickname()
                + ". (Usa la opción 11 del menú principal para deshacer.)");
    }

    private static void listarIncidencias(Liga liga) {
        if (liga.getIncidencias().isEmpty()) {
            System.out.println("No hay incidencias registradas.");
            return;
        }
        for (Incidencia i : liga.getIncidencias()) {
            i.mostrar();
            System.out.println();
        }
    }

    private static void buscarIncidenciasPorEquipo(Scanner scanner, Liga liga) {
        System.out.print("Nombre del equipo: ");
        String nombre = scanner.nextLine();
        boolean encontrado = false;
        for (Incidencia i : liga.getIncidencias()) {
            if (i.getEquipoAfectado() != null
                    && i.getEquipoAfectado().getNombre().equalsIgnoreCase(nombre)) {
                i.mostrar();
                encontrado = true;
            }
        }
        if (!encontrado) System.out.println("No hay incidencias para ese equipo.");
    }

    private static void buscarIncidenciasPorJugador(Scanner scanner, Liga liga) {
        System.out.print("Nickname del jugador: ");
        String nick = scanner.nextLine();
        boolean encontrado = false;
        for (Incidencia i : liga.getIncidencias()) {
            if (i.getJugadorAfectado() != null
                    && i.getJugadorAfectado().getNickname().equalsIgnoreCase(nick)) {
                i.mostrar();
                encontrado = true;
            }
        }
        if (!encontrado) System.out.println("No hay incidencias para ese jugador.");
    }

    // ── MENÚ ESTADÍSTICAS ──────────────────────────────────────────────────

    private static void menuEstadisticas(Scanner scanner, Liga liga) {
        System.out.println("\n--- Estadísticas ---");
        System.out.println("1. Estadísticas de jugadores");
        System.out.println("2. Coste total de todos los equipos");
        System.out.println("3. Hacer entrenar a un jugador");
        System.out.println("0. Volver");
        System.out.print("Opción: ");

        switch (leerInt(scanner)) {
            case 1: liga.mostrarEstadisticasJugadores(); break;
            case 2: mostrarCostesEquipos(liga);          break;
            case 3: hacerEntrenar(scanner, liga);        break;
            case 0: break;
            default: System.out.println("Opción no válida.");
        }
    }

    private static void mostrarCostesEquipos(Liga liga) {
        System.out.println("--- Coste mensual por equipo ---");
        for (Equipo e : liga.getEquipos()) {
            System.out.printf("%-15s → %.2f€%n", e.getNombre(), e.calcularCosteEquipo());
        }
    }

    private static void hacerEntrenar(Scanner scanner, Liga liga) {
        System.out.print("ID del jugador: ");
        PersonaLiga p = liga.buscarPersonaPorId(scanner.nextLine());
        if (!(p instanceof Jugador)) { System.out.println("Jugador no encontrado."); return; }
        ((Jugador) p).entrenar();
    }

    // ── UTILIDADES ─────────────────────────────────────────────────────────

    // Lee un entero del scanner, pidiendo de nuevo si el usuario mete algo que no es número
    private static int leerInt(Scanner scanner) {
        while (true) {
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("Introduce un número entero: ");
            }
        }
    }

    // Lee un double del scanner con manejo de error
    private static double leerDouble(Scanner scanner) {
        while (true) {
            try {
                double valor = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("Introduce un número válido: ");
            }
        }
    }

    // ── DATOS DE EJEMPLO ───────────────────────────────────────────────────

    // Carga 6 equipos con jugadores y entrenadores para poder probar la aplicación desde el inicio
    private static void inicializarDatos(Liga liga) {

        // Entrenadores
        Entrenador e1 = new Entrenador("E001", "Carlos Ruiz", "CarlosCoach", 38, 3000, 10, "Macro");
        Entrenador e2 = new Entrenador("E002", "Laura Gomez", "LauraG", 34, 2800, 7, "Teamfight");
        Entrenador e3 = new Entrenador("E003", "Marcos Gil", "MarcosCoach", 42, 3200, 15, "Draft");
        Entrenador e4 = new Entrenador("E004", "Sofia Vega", "SofiaV", 30, 2600, 5, "Early Game");
        Entrenador e5 = new Entrenador("E005", "Diego Leon", "DiegoCoach", 36, 2900, 8, "Jungla");
        Entrenador e6 = new Entrenador("E006", "Ana Mora", "AnaCoach", 32, 2700, 6, "Support");

        liga.registrarPersona(e1);
        liga.registrarPersona(e2);
        liga.registrarPersona(e3);
        liga.registrarPersona(e4);
        liga.registrarPersona(e5);
        liga.registrarPersona(e6);

        // Equipo 1: Storm Dragons
        Equipo eq1 = new Equipo("Storm Dragons", "Madrid", 500000);
        Jugador j101 = new Jugador("J101", "Ivan Torres", "IvanTop", 22, 2000, Rol.TOP, 8, 7);
        Jugador j102 = new Jugador("J102", "Pedro Diaz", "PedroJG", 21, 2100, Rol.JUNGLE, 9, 8);
        Jugador j103 = new Jugador("J103", "Luis Herrera", "LuisMid", 23, 2200, Rol.MID, 10, 9);
        Jugador j104 = new Jugador("J104", "Raul Perez", "RaulADC", 20, 1900, Rol.ADC, 8, 7);
        Jugador j105 = new Jugador("J105", "Mario Castillo", "MarioSup", 24, 1800, Rol.SUPPORT, 7, 9);
        Jugador j106 = new Jugador("J106", "Sergio Blanco", "SergiJG", 22, 1700, Rol.JUNGLE, 7, 6);
        fichaEquipo(eq1, e1, j101, j102, j103, j104, j105, j106, liga);

        // Equipo 2: Night Wolves
        Equipo eq2 = new Equipo("Night Wolves", "Barcelona", 480000);
        Jugador j201 = new Jugador("J201", "Alex Ramos", "AlexTop", 25, 2100, Rol.TOP, 7, 8);
        Jugador j202 = new Jugador("J202", "Javi Ortiz", "JaviJG", 22, 2000, Rol.JUNGLE, 8, 7);
        Jugador j203 = new Jugador("J203", "Pablo Reyes", "PabloMid", 21, 2300, Rol.MID, 9, 8);
        Jugador j204 = new Jugador("J204", "Tomas Vargas", "TomasADC", 23, 2000, Rol.ADC, 8, 8);
        Jugador j205 = new Jugador("J205", "David Fuentes", "DavidSup", 24, 1900, Rol.SUPPORT, 6, 9);
        Jugador j206 = new Jugador("J206", "Adrian Soto", "AdrianTop", 20, 1700, Rol.TOP, 6, 7);
        fichaEquipo(eq2, e2, j201, j202, j203, j204, j205, j206, liga);

        // Equipo 3: Iron Phoenix
        Equipo eq3 = new Equipo("Iron Phoenix", "Valencia", 520000);
        Jugador j301 = new Jugador("J301", "Fernando Cruz", "FerTop", 26, 2200, Rol.TOP, 9, 7);
        Jugador j302 = new Jugador("J302", "Hector Lara", "HectorJG", 23, 2100, Rol.JUNGLE, 8, 8);
        Jugador j303 = new Jugador("J303", "Miguel Santos", "MiguelMid", 22, 2400, Rol.MID, 10, 8);
        Jugador j304 = new Jugador("J304", "Oscar Mendez", "OscarADC", 21, 2100, Rol.ADC, 9, 7);
        Jugador j305 = new Jugador("J305", "Ruben Iglesias", "RubenSup", 25, 1900, Rol.SUPPORT, 7, 8);
        Jugador j306 = new Jugador("J306", "Victor Campos", "VicJG", 22, 1800, Rol.JUNGLE, 7, 7);
        fichaEquipo(eq3, e3, j301, j302, j303, j304, j305, j306, liga);

        // Equipo 4: Azure Hawks
        Equipo eq4 = new Equipo("Azure Hawks", "Sevilla", 460000);
        Jugador j401 = new Jugador("J401", "Daniel Pardo", "DaniTop", 21, 1900, Rol.TOP, 7, 7);
        Jugador j402 = new Jugador("J402", "Andres Molina", "AndresJG", 22, 2000, Rol.JUNGLE, 8, 6);
        Jugador j403 = new Jugador("J403", "Jorge Navarro", "JorgeMid", 23, 2100, Rol.MID, 8, 9);
        Jugador j404 = new Jugador("J404", "Carlos Rubio", "CarlosADC", 20, 1900, Rol.ADC, 7, 8);
        Jugador j405 = new Jugador("J405", "Alvaro Guerrero", "AlvaroSup", 24, 1800, Rol.SUPPORT, 6, 9);
        Jugador j406 = new Jugador("J406", "Eduardo Romero", "EduMid", 21, 1700, Rol.MID, 7, 7);
        fichaEquipo(eq4, e4, j401, j402, j403, j404, j405, j406, liga);

        // Equipo 5: Silver Ravens
        Equipo eq5 = new Equipo("Silver Ravens", "Bilbao", 490000);
        Jugador j501 = new Jugador("J501", "Gonzalo Castro", "GonzaTop", 22, 2000, Rol.TOP, 8, 6);
        Jugador j502 = new Jugador("J502", "Nicolas Serrano", "NicoJG", 21, 2000, Rol.JUNGLE, 7, 8);
        Jugador j503 = new Jugador("J503", "Roberto Medina", "RoberMid", 24, 2200, Rol.MID, 9, 7);
        Jugador j504 = new Jugador("J504", "Simon Aguilar", "SimonADC", 22, 2000, Rol.ADC, 8, 7);
        Jugador j505 = new Jugador("J505", "Mateo Delgado", "MateoSup", 23, 1900, Rol.SUPPORT, 7, 8);
        Jugador j506 = new Jugador("J506", "Alejandro Mora", "AleTop", 20, 1700, Rol.TOP, 6, 7);
        fichaEquipo(eq5, e5, j501, j502, j503, j504, j505, j506, liga);

        // Equipo 6: Gold Falcons
        Equipo eq6 = new Equipo("Gold Falcons", "Zaragoza", 470000);
        Jugador j601 = new Jugador("J601", "Rafael Jimenez", "RafaTop", 23, 1900, Rol.TOP, 7, 8);
        Jugador j602 = new Jugador("J602", "Manuel Flores", "ManuJG", 22, 2000, Rol.JUNGLE, 8, 7);
        Jugador j603 = new Jugador("J603", "Francisco Rojas", "FranMid", 21, 2100, Rol.MID, 8, 8);
        Jugador j604 = new Jugador("J604", "Antonio Vidal", "AntoniADC", 24, 2000, Rol.ADC, 7, 7);
        Jugador j605 = new Jugador("J605", "Juan Cano", "JuanSup", 22, 1800, Rol.SUPPORT, 6, 8);
        Jugador j606 = new Jugador("J606", "Ricardo Pena", "RicarJG", 21, 1700, Rol.JUNGLE, 7, 6);
        fichaEquipo(eq6, e6, j601, j602, j603, j604, j605, j606, liga);

        // Partidos de ejemplo - jornada 1 (todos contra todos, primera vuelta)
        try {
            Partido p1 = new Partido("P001", 1, eq1, eq2);
            Partido p2 = new Partido("P002", 1, eq3, eq4);
            Partido p3 = new Partido("P003", 1, eq5, eq6);
            Partido p4 = new Partido("P004", 2, eq1, eq3);
            Partido p5 = new Partido("P005", 2, eq2, eq5);
            Partido p6 = new Partido("P006", 2, eq4, eq6);
            liga.registrarPartido(p1);
            liga.registrarPartido(p2);
            liga.registrarPartido(p3);
            liga.registrarPartido(p4);
            liga.registrarPartido(p5);
            liga.registrarPartido(p6);
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear partidos de ejemplo: " + e.getMessage());
        }

        System.out.println("Datos de ejemplo cargados: 6 equipos, 36 jugadores, 6 entrenadores, 6 partidos.");
    }

    // Método auxiliar para registrar todas las personas y fichar jugadores de un equipo de una vez
    private static void fichaEquipo(Equipo equipo, Entrenador entrenador,
                                    Jugador t1, Jugador t2, Jugador t3,
                                    Jugador t4, Jugador t5, Jugador suplente,
                                    Liga liga) {
        liga.registrarEquipo(equipo);
        equipo.setEntrenador(entrenador);
        liga.registrarPersona(t1);
        liga.registrarPersona(t2);
        liga.registrarPersona(t3);
        liga.registrarPersona(t4);
        liga.registrarPersona(t5);
        liga.registrarPersona(suplente);
        try {
            equipo.ficharTitular(t1);
            equipo.ficharTitular(t2);
            equipo.ficharTitular(t3);
            equipo.ficharTitular(t4);
            equipo.ficharTitular(t5);
            equipo.ficharSuplente(suplente);
        } catch (RolNoDisponibleException | JugadorSancionadoException e) {
            System.out.println("Error al inicializar " + equipo.getNombre() + ": " + e.getMessage());
        }
    }
}
