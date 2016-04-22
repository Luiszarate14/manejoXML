
package Controlador;

import Modelo.Estudiante;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

 
public class CreadorArchivo 
{

    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    DOMImplementation implementation;
    Document document;
    ArrayList titulos;
    ArrayList valores;
    Element raiz;
    Inspector inspector;
    String nombreArchivo;
 
    public CreadorArchivo(String nombreArchivo) 
    {
        this.inspector = new Inspector();
        this.nombreArchivo = nombreArchivo;
        _nuevoArchivo();
    }

    private void _nuevoArchivo() {
        factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
            implementation = builder.getDOMImplementation();
            document = implementation.createDocument(null, nombreArchivo, null);
            document.setXmlVersion("1.0");
            raiz = document.getDocumentElement();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CreadorArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardar() 
    {
        try{
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File(nombreArchivo)); 
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            System.out.println("Archivo XML creado con el nombre: "+nombreArchivo);
        } catch (TransformerException ex) {
            Logger.getLogger(CreadorArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void _agregarEstudiante(ArrayList<Estudiante> estudiantes) throws IllegalArgumentException, IllegalAccessException{
        HashMap<String, Object> campoEstudiante = null;
        raiz = document.getDocumentElement();
        Text valor;
        Element item = null;
        Element clave =null;
        
        for(Estudiante estudiante: estudiantes){
            // Obtengo los campos y los valores de los campos
            campoEstudiante = inspector.obtener_campos(estudiante);
            // Obtengo un iterador para recorrer el HashMap
            Iterator i = campoEstudiante.keySet().iterator();
            item = document.createElement(inspector.obtener_nombre_clase(estudiante)); 
            while(i.hasNext()){
                String key = (String)i.next();
                clave = document.createElement(key); 
                valor = document.createTextNode(campoEstudiante.get(key).toString());
                clave.appendChild(valor);
                item.appendChild(clave);
            }
            raiz.appendChild(item);
        }
    }
    
    public void agregarEstudiante(ArrayList<Estudiante> estudiantes){
        try {
            this._agregarEstudiante(estudiantes);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(CreadorArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CreadorArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private NodeList busco_en_documento(String expresion) throws XPathExpressionException{
        XPath xPath =  XPathFactory.newInstance().newXPath();
        NodeList nodeList = (NodeList) xPath.compile(
                expresion).evaluate(
            document, XPathConstants.NODESET);
        return nodeList;
    }
    
    
    private void _buscar(String expresion, ArrayList<String> campo) throws XPathExpressionException{
         NodeList nodeList = this.busco_en_documento(expresion);
         for (int i = 0; i < nodeList.getLength(); i++) {
             Node nNode = nodeList.item(i);
             System.out.println("\nElemento :" + nNode.getNodeName());
             if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               for (int x=0; x<campo.size(); x++){
                   
                   System.out.println(
                           
                           campo.get(x)+" = "+
                    eElement.getElementsByTagName(campo.get(x)).item(0).getTextContent()+
                    eElement.getAttribute("class")
                            );
               }
               
             }
         }
    }
    
    
    public void buscar(String expresion, ArrayList<String> campo){
        try {
            this._buscar(expresion, campo);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(LectorXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void _cargar(String nombreArchivo) throws SAXException, IOException{
        document = builder.parse(
                        new FileInputStream(nombreArchivo));
    }
    
    public void cargar(String nombreArchivo){
        this.nombreArchivo = nombreArchivo;
        try {    
            _cargar(nombreArchivo);
        } catch (SAXException ex) {
            Logger.getLogger(CreadorArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreadorArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}