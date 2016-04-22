/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leexml;

import java.util.ArrayList;

/**
 *
 * @author luisza
 */
public class Estudiante {
    private String nombre;
    private String carnet;
    private String correo;

    public static ArrayList get_estudiantes(){
        ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
        
        for(int x=0; x<30; x++){
            estudiantes.add(new Estudiante(
                    "Estudiante "+ x,
                    "Carnet "+x,
                    "correo "+x
            ));
        
        }        
        return estudiantes;
    }
    
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Estudiante(String nombre, String carnet, String correo) {
        this.nombre = nombre;
        this.carnet = carnet;
        this.correo = correo;
    }

    public Estudiante() {
    }

    @Override
    public String toString() {
        return "Estudiante{" + "nombre=" + nombre + ", carnet=" + carnet + ", correo=" + correo + '}';
    }
    
    
}
