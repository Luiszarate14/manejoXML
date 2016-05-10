/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import Modelo.Anotacion;
import Modelo.Estudiante;
import org.w3c.dom.Element;

/**
 *
 * @author luisza
 */
public class EstudianteBuilder implements InterfaceBuilder{

    @Override
    public Estudiante contruyeEstudiante(Object obj) {
       Element eElement = (Element) obj;
       Estudiante est = new Estudiante(
        eElement.getElementsByTagName("carnet").item(0).getTextContent(),
        eElement.getElementsByTagName("nombre").item(0).getTextContent(),
        eElement.getElementsByTagName("correo").item(0).getTextContent()
       );
        
       return est;
    }
    
}
