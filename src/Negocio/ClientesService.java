package Negocio;

import Modelo.Cliente;
import Modelo.Mayorista;
import Modelo.Particular;
import Modelo.Venta;
import java.util.ArrayList;
import java.util.List;

public class ClientesService {

    private List<Cliente> clientes;
    private VentasService ventasV;

    ClientesService() {
        clientes = new ArrayList();
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setVentasV(VentasService ventasV) {
        this.ventasV = ventasV;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public boolean introducirCliente(Cliente c) {
        boolean respuesta = false;
        try {
            clientes.add(c);
            respuesta = true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return respuesta;

    }

    public Cliente buscarCliente(int numeroCliente) {
        Cliente cliente = null;
        boolean respuesta = false;
        for (Cliente c : clientes) {
            if (c.getIdCliente() == numeroCliente && !respuesta) {
                cliente = c;
                respuesta = true;
            }
        }

        return cliente;
    }

    public boolean eliminarCliente(int numCliente) {
        boolean respuesta = false;
        try {
            // Al eliminar un cliente también eliminamos las ventas asociadas a el

            //Eliminamos las ventas del cliente seleccionado
            this.ventasV.eliminarClienteVent(numCliente);
            List<Venta> ventasEliminar = new ArrayList();
            this.ventasV.eliminarClienteVent(numCliente);
            //Eliminamos el cliente
            Cliente clienteBorrar = null;
            for (int i = 0; i < clientes.size() && clienteBorrar == null; i++) {
                if (clientes.get(i).getIdCliente() == numCliente) {
                    clienteBorrar = clientes.get(i);
                }
            }
            clientes.remove(clienteBorrar);
            respuesta = true;
        } catch (Exception e) {
            throw new RuntimeException("imposible eliminar cliente");
        }
        return respuesta;

    }

    public String imprimirTodosClientes() {
        String res = "";
        String listaMayorista = String.format("%-15s %-15s %-15s %-15s %-15s %-15s",
                "ID", "NOMBRE", "RAZON_SOC", "CIF", "TIPO", "DESCUENTO");
        String listaParticular = String.format("%-15s %-15s %-15s %-15s",
                "ID", "NOMBRE", "RAZON_SOC", "DNI");
        if (clientes.isEmpty()) {
            res = "No hay clientes introducidos.";

        } else {
            for (Cliente c : clientes) {

                if (c instanceof Mayorista) {
                    Mayorista m = (Mayorista) c;
                    String listaPrM = String.format("%-15s %-15s %-15s %-15s %-15s %-15s", m.getIdCliente(), m.getNombre(), m.getRazonSocial(), m.getCif(), m.getTipoMayorista(), m.getDescuento());
                    res += "\n" + listaMayorista + "\n" + listaPrM;

                }
                if (c instanceof Particular) {
                    Particular p = (Particular) c;
                    String listaPrP = String.format("%-15s %-15s %-15s %-15s", p.getIdCliente(), p.getNombre(), p.getRazonSocial(), p.getDni());
                    res += "\n" + listaParticular + "\n" + listaPrP;

                }
            }
        }
        return res;
    }

}
