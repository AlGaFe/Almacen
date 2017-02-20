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
public class fechaErronea  extends RuntimeException{
     public fechaErronea (String mensaje) {
        super(mensaje);
    }
}
