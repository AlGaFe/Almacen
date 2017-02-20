
package Modelo;

import Modelo.Televisor.TipoTelevisor;


public class Televisor extends Electrodomestico {
   public enum TipoTelevisor{
    PLASMA,LCD,LED,OLED
}
    
    protected double pulgadas;
    protected TipoTelevisor tipo;
    

    public Televisor() {
        super();
    }

    @Override
    public void setPrecio(double precioBase) {
     if (this.getPulgadas()> 40){
         precioBase=precioBase+((precioBase*10)/100);
            precio = precioBase;
     }
     if(this.getTipo()== TipoTelevisor.PLASMA){
         precioBase=precioBase-((precioBase*10)/100);
            precio = precioBase;
     }   
    }
    
    @Override
    public String imprimirProducto() {
        String res = super.imprimirProducto() + "tipo de TV: " + this.tipo + "con " + this.pulgadas + " pulgadas";
        return res;

    }

    public double getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(double pulgadas) {
        this.pulgadas = pulgadas;
    }

    public TipoTelevisor getTipo() {
        return tipo;
    }

    public void setTipo(TipoTelevisor tipo) {
        this.tipo = tipo;
    }
    

}
