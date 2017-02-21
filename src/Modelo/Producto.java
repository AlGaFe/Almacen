
package Modelo;

import java.util.ArrayList;
import java.util.List;



public abstract  class Producto {
    public int id;
    public String nombre;
    public double precio;
    protected List<Venta> ventas;
    protected static int contador=1; 
    
    public Producto(){
        ventas= new ArrayList();
        this.id=contador;
        contador++;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public int getId() {
        return id;
    }

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    
    
    public abstract void setPrecio(double precioBase);
    
    
    
    public  String imprimirProducto(){
        String patron = "El id es: %d"+" el precio: %f del objeto: %s";
        String res= String.format(patron, this);
        
        return res;
    }
    
    
    public static String imprimirNumProductos(){
        String res = "El número de productos es: "+contador;
        return res;
    }

        
    }
    
    

