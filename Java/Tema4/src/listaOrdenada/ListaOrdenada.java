package listaOrdenada;

import java.util.Iterator;
import java.util.LinkedList;

public class ListaOrdenada {

    LinkedList<Integer> lista = new LinkedList<Integer>();

    public void insertarEnOrden(Integer nuevo) {
        int pos = buscarSuSito(nuevo);
        lista.add(pos, nuevo);
    }

    private int buscarSuSito(Integer nuevo) {
        boolean encontradoPosicion = false;
        int pos = 0;
        Iterator<Integer> it = lista.iterator();
        Integer elemento;
        while (it.hasNext() && encontradoPosicion == false) {
            elemento = it.next();
            if (nuevo.intValue() < elemento.intValue())
                encontradoPosicion = true;
            else
                pos++;
        }
        return pos;
    }

    public void mostrar() {
        Iterator<Integer> it = lista.iterator();
        while (it.hasNext())
            System.out.print(it.next() + " ");
        System.out.println();
    }
}
