package recetario;

public class mainRecetario {

    public static void main(String[] args) {
        try {
            Recetario rec = new Recetario();

            // crear recetas
            Receta tortilla = new Receta("Tortilla", 20);
            tortilla.annadirIngrediente(new Ingrediente("huevo", 3));
            tortilla.annadirIngrediente(new Ingrediente("patata", 2));
            tortilla.annadirIngrediente(new Ingrediente("aceite", 0.1));
            tortilla.annadirPaso("Pelar y cortar las patatas");
            tortilla.annadirPaso("Freir las patatas con aceite");
            tortilla.annadirPaso("Batir los huevos");
            tortilla.annadirPaso("Mezclar patatas y huevos");
            tortilla.annadirPaso("Cuajar en la sarten");

            Receta ensalada = new Receta("Ensalada", 10);
            ensalada.annadirIngrediente(new Ingrediente("lechuga", 1));
            ensalada.annadirIngrediente(new Ingrediente("tomate", 2));
            ensalada.annadirIngrediente(new Ingrediente("aceite", 0.05));
            ensalada.annadirPaso("Lavar y cortar la lechuga");
            ensalada.annadirPaso("Cortar el tomate");
            ensalada.annadirPaso("Aliñar con aceite");

            Receta huevosFritos = new Receta("Huevos fritos", 5);
            huevosFritos.annadirIngrediente(new Ingrediente("huevo", 2));
            huevosFritos.annadirIngrediente(new Ingrediente("aceite", 0.15));
            huevosFritos.annadirPaso("Calentar aceite en la sarten");
            huevosFritos.annadirPaso("Cascar y echar los huevos");
            huevosFritos.annadirPaso("Freir hasta que cuajen");

            rec.annadirReceta(tortilla);
            rec.annadirReceta(ensalada);
            rec.annadirReceta(huevosFritos);

            System.out.println("=== Recetas anadidas ===");
            System.out.println(tortilla);
            System.out.println(ensalada);
            System.out.println(huevosFritos);

            System.out.println("\n=== Listado alfabetico ===");
            System.out.println(rec.listadoRecetasOrdenadasAlfabeticamente());

            System.out.println("=== Recetas con 'huevo' por tiempo ===");
            System.out.println(rec.listadoRecetasConIngredienteOrdenadasPorTiempoPreparacion("huevo"));

            System.out.println("=== Recetas con 'aceite' por tiempo ===");
            System.out.println(rec.listadoRecetasConIngredienteOrdenadasPorTiempoPreparacion("aceite"));

            // test anadir paso detras de otro
            System.out.println("=== Anadir paso detras de 'Batir los huevos' ===");
            tortilla.annadirPasoDetrasDe("Salar los huevos", "Batir los huevos");
            System.out.println(tortilla);

            // test borrar ingrediente (borra pasos que lo mencionen)
            System.out.println("\n=== Borrar ingrediente 'aceite' de tortilla ===");
            tortilla.borrarIngrediente(new Ingrediente("aceite", 0));
            System.out.println(tortilla);

            // test excepcion: receta duplicada
            System.out.println("\n=== Test excepcion: receta duplicada ===");
            rec.annadirReceta(new Receta("Tortilla", 15));

        } catch (RecetaException e) {
            System.out.println("RecetaException: " + e.getMessage());
        }
    }
}
