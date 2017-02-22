package Negocio;

import Modelo.Cliente;
import Modelo.Producto;
import Modelo.Venta;
import java.util.ArrayList;
import java.util.List;

public class VentasService {

    private List<Venta> ventas;
    private ClientesService clientesC;
    private ProductosService productosP;

    public void setClientesC(ClientesService clientesC) {
        this.clientesC = clientesC;
    }

    public void eliminarProductoVent(int id) {
        // Eliminamos de ventas el producto seleccionado
        List<Venta> ventasEliminar = new ArrayList();

        for (Venta v : this.ventas) {
            if (v.getProducto().getId() == id) {
                ventasEliminar.add(v);

            }

            ventas.removeAll(ventasEliminar);

        }

    }

    public List<Venta> getVentas() {
        return ventas;
    }

    VentasService() {
        ventas = new ArrayList();
    }

    public void introducirVenta(int ncliente, int nproducto, String vend) {
        try {

            Cliente clienteVenta = null;
            for (int i = 0; i < clientesC.getClientes().size() && clienteVenta == null; i++) {
                if (clientesC.getClientes().get(i).getIdCliente() == ncliente) {
                    clienteVenta = clientesC.getClientes().get(i);
                }
            }
            if (clienteVenta == null) {
                throw new RuntimeException("El cliente no existe.");
            }

            Producto productoVenta = null;
            for (int i = 0; i < productosP.getProductos().size() && productoVenta == null; i++) {
                if (productosP.getProductos().get(i).getId() == nproducto) {
                    productoVenta = productosP.getProductos().get(i);
                }
            }
            if (productoVenta == null) {
                throw new RuntimeException("El producto no existe.");
            }

            Venta v = new Venta();
            v.setCliente(clienteVenta);
            v.setVendedor(vend);
            v.setProducto(productoVenta);
            v.setPrecioVenta(); //calcula el precio de la venta segun el cliente-mayorista

            ventas.add(v);

            clienteVenta.getCompras().add(v);
            productoVenta.getVentas().add(v);

        } catch (Exception e) {
            throw new RuntimeException("No ha sido posible introducir la venta" + e.getMessage());
        }

    }

    public Venta buscarVenta(int nv) {
        boolean respuesta = false;
        Venta venta = null;
        try {

            for (int i = 0; i < ventas.size() && !respuesta; i++) {
                if (ventas.get(i).getIdVenta() == nv) {
                    venta = ventas.get(i);
                    respuesta = true;
                }
            }
            if (venta == null) {
                throw new Exception("La venta con id: " + nv + " no existe");
            }
            venta.imprimirVenta();
        } catch (Exception e) {
            throw new RuntimeException("No ha sido posible imprimir la venta" + e.getMessage());
        }
        return venta;
    }

    public void eliminarVenta(int nv) {

        try {

            Venta ventaBorrar = null;
            for (int i = 0; i < ventas.size() && ventas.get(i).getIdVenta() != nv; i++) {

                if (ventas.get(i).getIdVenta() == nv) {
                    ventaBorrar = ventas.get(i);

                }
            }
            if (ventaBorrar == null) {
                // hacemos saltar una excepcion que nos lleva directamente al catch
                throw new Exception("No existe ninguna venta con ese Id");
            }
            // este código solo se ejecuta si todo va bien
            ventas.remove(ventaBorrar);

        } catch (Exception e) {
            throw new RuntimeException("imposible eliminar la venta");
        }

    }

    public void eliminarClienteVent(int id) {
        List<Venta> ventasEliminar = new ArrayList();
        for (Venta v : this.ventas) {
            if (v.getCliente().getIdCliente() == id) {
                ventasEliminar.add(v);

            }
        }
        ventas.removeAll(ventasEliminar);

    }

    public String imprimirtodasVentas() {
        String res = "";
        String listaVentas = String.format("%-15s %-15s %-15s %-15s %-15s %-15s", "ID", "VENTA", "VENDEDOR", "CLIENTE", "PRODUCTO", "PRECIO-VENTA");
        if (ventas.isEmpty()) {
            res = "No hay ventas introducidas.";
        } else {
            for (Venta v : ventas) {
                String ventasCliente = String.format("%-15s %-15s %-15s %-15s %-15s", v.getIdVenta(), v.getVendedor(), v.getCliente().getIdCliente(), v.getProducto().getId(), v.getPrecioVenta());
                res += listaVentas + "\n" + ventasCliente;
            }
        }
        return res;
    }
}
