package ExamenTema6;

public class Principal {

    public static void main(String[] args) {

        // --- 1. Crear array polimorfico con 4 Pokemon ---

        Pokemon[] pokemons = new Pokemon[4];

        // Movimientos y matrices de aprendizaje [nivel, potencia]

        Movimiento[] movs1 = {
            new Movimiento("Ascuas", Tipo.fuego, 40),
            new Movimiento("Giro Fuego", Tipo.fuego, 60),
            new Movimiento("Lanzallamas", Tipo.fuego, 90),
            new Movimiento("Llamarada", Tipo.fuego, 110)
        };
        int[][] aprendizaje1 = {{1, 40}, {10, 60}, {20, 90}, {36, 110}};

        Movimiento[] movs2 = {
            new Movimiento("Ascuas", Tipo.fuego, 40),
            new Movimiento("Golpe Igneo", Tipo.fuego, 75),
            new Movimiento("Lanzallamas", Tipo.fuego, 90),
            new Movimiento("Llama Final", Tipo.fuego, 100)
        };
        int[][] aprendizaje2 = {{1, 40}, {15, 75}, {26, 90}, {38, 100}};

        Movimiento[] movs3 = {
            new Movimiento("Latigo Cepa", Tipo.planta, 45),
            new Movimiento("Hoja Afilada", Tipo.planta, 55),
            new Movimiento("Petalada", Tipo.planta, 80),
            new Movimiento("Rayo Solar", Tipo.planta, 120)
        };
        int[][] aprendizaje3 = {{1, 45}, {10, 55}, {18, 80}, {32, 120}};

        Movimiento[] movs4 = {
            new Movimiento("Pistola Agua", Tipo.agua, 40),
            new Movimiento("Burbuja", Tipo.agua, 65),
            new Movimiento("Surf", Tipo.agua, 90),
            new Movimiento("Hidrobomba", Tipo.agua, 110)
        };
        int[][] aprendizaje4 = {{1, 40}, {12, 65}, {28, 90}, {42, 110}};

        // 2 PokemonOfensivo, 2 PokemonDefensivo
        pokemons[0] = new PokemonOfensivo(6, "Charizard", Tipo.fuego, 20, movs1, aprendizaje1, 2);
        pokemons[1] = new PokemonOfensivo(392, "Infernape", Tipo.fuego, 10, movs2, aprendizaje2, 3);
        pokemons[2] = new PokemonDefensivo(3, "Venusaur", Tipo.planta, 25, movs3, aprendizaje3, 80);
        pokemons[3] = new PokemonDefensivo(9, "Blastoise", Tipo.agua, 15, movs4, aprendizaje4, 90);

        // --- 2. Mostrar informacion de todos los Pokemon ---

        System.out.println("========================================");
        System.out.println("   INFORMACION DE TODOS LOS POKEMON");
        System.out.println("========================================");

        for (int i = 0; i < pokemons.length; i++) {
            System.out.println("\n--- " + pokemons[i].getNombre() + " ---");
            System.out.println(pokemons[i].toString());
            pokemons[i].mostrarMovimientosDisponibles();
            System.out.printf("Potencia media disponible: %.2f%n", pokemons[i].calcularPotenciaMediaDisponible());
            System.out.printf("Indice de combate: %.2f%n", pokemons[i].calcularIndiceCombate());
            System.out.println("Necesita mejorar: " + pokemons[i].necesitaMejorar());
        }

        // --- 3. Pokemon con mayor indice de combate ---

        System.out.println("\n========================================");
        System.out.println("   POKEMON CON MAYOR INDICE DE COMBATE");
        System.out.println("========================================");

        Pokemon mejorPokemon = pokemons[0];
        for (int i = 1; i < pokemons.length; i++) {
            if (pokemons[i].calcularIndiceCombate() > mejorPokemon.calcularIndiceCombate()) {
                mejorPokemon = pokemons[i];
            }
        }

        String tipoPokemon = "";
        if (mejorPokemon instanceof PokemonOfensivo) {
            tipoPokemon = "PokemonOfensivo";
        } else {
            tipoPokemon = "PokemonDefensivo";
        }

        System.out.println("Nombre: " + mejorPokemon.getNombre());
        System.out.println("Tipo real: " + tipoPokemon);
        System.out.printf("Indice: %.2f%n", mejorPokemon.calcularIndiceCombate());

        // --- 4. Contar tipos con instanceof ---

        System.out.println("\n========================================");
        System.out.println("   CONTEO POR TIPO");
        System.out.println("========================================");

        int contOfensivos = 0;
        int contDefensivos = 0;

        for (int i = 0; i < pokemons.length; i++) {
            if (pokemons[i] instanceof PokemonOfensivo) {
                contOfensivos++;
            } else if (pokemons[i] instanceof PokemonDefensivo) {
                contDefensivos++;
            }
        }

        System.out.println("Pokemon ofensivos: " + contOfensivos);
        System.out.println("Pokemon defensivos: " + contDefensivos);

        // --- 5. Movimiento mas potente disponible entre todos ---

        System.out.println("\n========================================");
        System.out.println("   MOVIMIENTO MAS POTENTE DISPONIBLE");
        System.out.println("========================================");

        String nombreMovMasPotente = "";
        String nombrePokemonDuenio = "";
        int potenciaMaxima = -1;

        for (int i = 0; i < pokemons.length; i++) {
            Movimiento[] movs = pokemons[i].getMovimientos();
            int[][] aprendizaje = pokemons[i].getAprendizaje();
            for (int j = 0; j < movs.length; j++) {
                if (aprendizaje[j][0] <= pokemons[i].getNivelActual()) {
                    if (movs[j].getPotencia() > potenciaMaxima) {
                        potenciaMaxima = movs[j].getPotencia();
                        nombreMovMasPotente = movs[j].getNombre();
                        nombrePokemonDuenio = pokemons[i].getNombre();
                    }
                }
            }
        }

        System.out.println("Movimiento: " + nombreMovMasPotente);
        System.out.println("Pokemon: " + nombrePokemonDuenio);
        System.out.println("Potencia: " + potenciaMaxima);

        // --- 6. Pokemon con mas movimientos disponibles ---

        System.out.println("\n========================================");
        System.out.println("   POKEMON CON MAS MOVIMIENTOS DISPONIBLES");
        System.out.println("========================================");

        Pokemon masMovimientos = pokemons[0];
        for (int i = 1; i < pokemons.length; i++) {
            if (pokemons[i].contarMovimientosDisponibles() > masMovimientos.contarMovimientosDisponibles()) {
                masMovimientos = pokemons[i];
            }
        }

        System.out.println("Nombre: " + masMovimientos.getNombre());
        System.out.println("Movimientos disponibles: " + masMovimientos.contarMovimientosDisponibles());
    }
}
