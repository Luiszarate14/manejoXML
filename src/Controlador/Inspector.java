package Controlador;


import Modelo.Anotacion;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luisza
 */
public class Inspector {
    
    public String obtener_nombre_clase(Object o){
        return o.getClass().getName();
    }
    
    public HashMap<String, Object> obtener_campos(Object o) throws IllegalArgumentException, IllegalAccessException {
        Class<?> clazz = o.getClass();
        HashMap<String, Object> dev = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            //you can also use .toGenericString() instead of .getName(). This will
            //give you the type information as well.
            if (field.isAnnotationPresent(Anotacion.class)) {
                for (Annotation anotacion : field.getDeclaredAnnotations()) {
                    Anotacion acampo = (Anotacion) anotacion;
                    if (acampo.guardar_xml()) {
                        field.setAccessible(true);
                        dev.put( field.getName(), field.get(o));
                    }
                }
            }
        }
        return dev;
    }
    
    
}
