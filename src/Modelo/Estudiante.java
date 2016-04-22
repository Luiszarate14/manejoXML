/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Luisza
 */
public class Estudiante {

    @Anotacion
    private String carnet;
    @Anotacion
    private String nombre;
    @Anotacion
    private String correo;
    @Anotacion(guardar_xml = false)
    private static String[] etiquetas = {"carnet", "nombre", "correo"};

    public static String[] getEtiquetas() {
        return etiquetas;
    }

    public static int getTamanno() {
        return etiquetas.length;
    }

    public String getDatos(int j) {

        switch (j) {
            case 0:
                return carnet;
                
            case 1:
                return nombre;
                
            case 2: 
                return correo;
                

        }
        return null;

    }

    public Estudiante() {
    }

    public Estudiante(String carnet, String nombre, String correo) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getInformacion(){
        return "Carnet "+getCarnet()+" Nombre "+getNombre()+" Correo "+getCorreo();
    }

}
