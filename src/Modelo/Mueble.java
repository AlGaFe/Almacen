package Modelo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Mueble extends Producto {

    public enum Madera {
        PINO, ROBLE, HAYA
    };

    private LocalDate anyoFab;
    private Madera tipoMadera;
    private String estilo;

    public Mueble() {
        super();
    }


    @Override
    public String imprimirProducto() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
        String res = super.imprimirProducto() + "el año de fabricación: " + anyoFab.format(sdf) + " el tipo de madera: " + this.tipoMadera + "el estilo: " + getEstilo();
        return res;

    }

    @Override
    public void setPrecio(double precioBase) {
        if(this.tipoMadera== Madera.ROBLE){
            precioBase=precioBase+((precioBase*10)/100);
           precio = precioBase;
        }
        if(this.tipoMadera== Madera.PINO){
            precioBase=precioBase-((precioBase*10)/100);
            precio = precioBase;
        }
    }

    public LocalDate getAnyoFab() {
        return anyoFab;
    }

    public void setAnyoFab(LocalDate anyoFab) {
        this.anyoFab = anyoFab;
    }

    public Madera getTipoMadera() {
        return tipoMadera;
    }

    public void setTipoMadera(Madera madera) {
        this.tipoMadera = madera;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

}
