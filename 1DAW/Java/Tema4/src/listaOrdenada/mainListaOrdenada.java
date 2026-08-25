package listaOrdenada;

public class mainListaOrdenada {

    public static void main(String[] args) {
        ListaOrdenada lo = new ListaOrdenada();

        lo.insertarEnOrden(5);
        lo.insertarEnOrden(2);
        lo.insertarEnOrden(8);
        lo.insertarEnOrden(1);
        lo.insertarEnOrden(4);

        System.out.print("Lista ordenada: ");
        lo.mostrar();
    }
}
