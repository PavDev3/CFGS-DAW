package supermercado;

import java.util.LinkedList;
import java.util.Queue;

public class Supermercado {

    private static final int NUM_CAJAS = 20;

    private Queue<Integer>[] cajas;
    private boolean[] abierta;
    private int contadorClientes;

    @SuppressWarnings("unchecked")
    public Supermercado() {
        cajas   = new LinkedList[NUM_CAJAS];
        abierta = new boolean[NUM_CAJAS];
        for (int i = 0; i < NUM_CAJAS; i++) {
            cajas[i]   = new LinkedList<>();
            abierta[i] = false;
        }
        contadorClientes = 0;
    }

    public void abrirCaja(int numCaja) throws Exception {
        validarNumCaja(numCaja);
        if (abierta[numCaja - 1]) {
            throw new Exception("La caja " + numCaja + " ya esta abierta.");
        }
        abierta[numCaja - 1] = true;
        System.out.println("Caja " + numCaja + " abierta.");
    }

    public void cerrarCaja(int numCaja) throws Exception {
        validarNumCaja(numCaja);
        if (!abierta[numCaja - 1]) {
            throw new Exception("La caja " + numCaja + " no esta abierta.");
        }
        if (!cajas[numCaja - 1].isEmpty()) {
            throw new Exception("La caja " + numCaja + " tiene clientes esperando.");
        }
        abierta[numCaja - 1] = false;
        System.out.println("Caja " + numCaja + " cerrada.");
    }

    // nuevo cliente — va a la caja abierta con menos clientes (empate: menor numero)
    public void nuevoCliente() throws Exception {
        int cajaMin = -1;
        int minClientes = Integer.MAX_VALUE;
        for (int i = 0; i < NUM_CAJAS; i++) {
            if (abierta[i] && cajas[i].size() < minClientes) {
                minClientes = cajas[i].size();
                cajaMin = i + 1;
            }
        }
        if (cajaMin == -1) {
            throw new Exception("No hay cajas abiertas.");
        }
        contadorClientes++;
        cajas[cajaMin - 1].add(contadorClientes);
        System.out.println("Es usted el cliente numero " + contadorClientes
                + " y debe ir a la caja numero " + cajaMin);
    }

    // atiende al primer cliente de una caja
    public void atenderCliente(int numCaja) throws Exception {
        validarNumCaja(numCaja);
        if (!abierta[numCaja - 1]) {
            throw new Exception("La caja " + numCaja + " esta cerrada.");
        }
        if (cajas[numCaja - 1].isEmpty()) {
            throw new Exception("No hay clientes en la caja " + numCaja);
        }
        int cliente = cajas[numCaja - 1].poll();
        System.out.println("Se ha atendido al cliente con numero " + cliente);
    }

    private void validarNumCaja(int numCaja) throws Exception {
        if (numCaja < 1 || numCaja > NUM_CAJAS) {
            throw new Exception("Numero de caja invalido. Debe ser entre 1 y " + NUM_CAJAS);
        }
    }
}
