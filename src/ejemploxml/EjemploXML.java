/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploxml;

import Controlador.CreadorArchivo;
import Modelo.Anotacion;
import Modelo.Estudiante;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPathExpressionException;

/**
 *
 * @author luisza
 */
public class EjemploXML {

    /**
     * @param args the command line arguments
     */

/*
            HashMap map = ...
            Iterator i = map.keySet().iterator();
            while(i.hasNext()){
                Map.Entry me = (Map.Entry)i.next();
                System.out.println((String)me.getKey()); 
                System.out.println(me.getValue().toString());
               
            }    
    
*/
    
    
    public static void main(String[] args) throws XPathExpressionException {
        // TODO code application logic here
        // Mostrarles inspector
        Estudiante estudiante = new Estudiante("B93223", "Juanito Perez", "jperez@ucr.ac.cr");
          Estudiante estudiante2 = new Estudiante("C19223", "Alberto Coto", "acoto@ucr.ac.cr");
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(estudiante);
        estudiantes.add(estudiante2);
        
        
        CreadorArchivo creador = new CreadorArchivo("ejemploEstudiante.xml");
        creador.agregarEstudiante(estudiantes);
        creador.guardar();
        /*
        String expresion = "/ejemploEstudiante.xml/Modelo.Estudiante[carnet='B93223']";
        
        ArrayList<String> campos = new ArrayList<>();
        campos.add("carnet");
        campos.add("nombre");
        creador.buscar(expresion, campos);*/
        
        ArrayList<Estudiante> ests = creador.get_estudiantes();
        for(Estudiante est: ests){
            System.out.println(est);
        }
 
    }

}
