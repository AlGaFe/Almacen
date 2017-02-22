package Negocio;

import Modelo.Lavadora;
import Modelo.Mueble;
import Modelo.Producto;
import Modelo.Televisor;
import Modelo.Venta;
import java.util.ArrayList;
import java.util.List;

class ProductosService {

    private List<Producto> productos;
    private VentasService ventasC;

    ProductosService() {
        productos = new ArrayList();
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void setVentasC(VentasService ventasC) {
        this.ventasC = ventasC;
    }

    public void introducirProducto(Producto p) {

        try {
            productos.add(p);
        } catch (Exception e) {
            throw new RuntimeException("Error al introducir el producto\n" + e.getMessage());
        }
    }

    public Producto buscarProducto(int np) throws Exception {
        Producto producto = null;
        boolean respuesta = false;
        for (int i = 0; i < productos.size() && !respuesta; i++) {
            if (productos.get(i).getId() == np) {
                producto = productos.get(i);
                respuesta = true;
            }
        }

        if (producto == null) {
            throw new Exception("El producto no existe.");
        }
        return producto;
    }

    public void elimninarProducto(int nproducto) {
        try {
            Producto productoEliminar = null;
            //Eliminamos de ventas el producto seleccionado
            List<Venta> ventasEliminar = new ArrayList();
            for (Venta v : this.ventasC.getVentas()) {
                if (v.getProducto().getId() == nproducto) {
                    ventasEliminar.add(v);

                }
            }
            ventasC.eliminarVenta(nproducto);
            //Eliminamos el producto
            for (Producto p : productos) {
                if (p.getId() == nproducto) {
                    productoEliminar = p;
                }
            }
            productos.remove(productoEliminar);
        } catch (Exception e) {
            throw new RuntimeException("imposible eliminar producto");
        }
    }

    public String imprimirTodosProductos() {
        String res = "";
        String listaTelevision = String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s",
                "ID", "NOMBRE", "PRECIO", "MARCA", "FABRICANTE", "TAMAÑO", "TIPO", "PULGADAS");
        String listaLavadora = String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s",
                "ID", "NOMBRE", "PRECIO", "MARCA", "FABRICANTE", "TAMAÑO", "REVOLUCIONES", "CARGA");
        String listaMueble = String.format("%-15s %-15s %-15s %-15s %-15s %-15s",
                "ID", "NOMBRE", "PRECIO", "AÑO_FAB", "MADERA", "ESTILO");

        if (productos.isEmpty()) {
            res = "No hay productos introducidos.";

        } else {
            for (Producto p : productos) {
                if (p instanceof Televisor) {
                    Televisor t = (Televisor) p;
                    String listaPr1 = String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s", t.getId(), t.getNombre(), t.getPrecio(), t.getMarca(), t.getFabricante(), t.getTamanyo(), t.getTipo(), t.getPulgadas());
                    res += listaTelevision + "\n" + listaPr1;
                }

                if (p instanceof Lavadora) {
                    Lavadora l = (Lavadora) p;
                    String listaPr2 = String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s ", l.getId(), l.getNombre(), l.getPrecio(), l.getMarca(), l.getFabricante(), l.getTamanyo(), l.getRevoluciones(), l.getCarga());
                    res += listaLavadora + "\n" + listaPr2;
                }

                if (p instanceof Mueble) {
                    Mueble m = (Mueble) p;
                    String listaPr3 = String.format("%-15s %-15s %-15s %-15s %-15s %-15s", m.getId(), m.getNombre(), m.getPrecio(), m.getAnyoFab(), m.getTipoMadera(), m.getEstilo());
                    res += listaMueble + "\n" + listaPr3;
                }
            }
        }
        return res;
    }
}
