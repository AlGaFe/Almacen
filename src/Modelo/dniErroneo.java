/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author daw
 */
public class dniErroneo extends RuntimeException {

    private String dniok;

    public dniErroneo(String dni , String dnie) {
        super(dnie + " no es un dni correcto, el dni adecuado es "+ dni);
        this.dniok = dni;
    }

    public String getDniok() {
        return dniok;
    }
    
    
}
