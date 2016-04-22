/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luisza
 */
public class LectorXML {

    Document doc = null;
    private void Obtengo_el_documento(String inputString) throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException {
        //FIXME: Optimizar para usar con archivo directamente
        // Creo un  DocumentBuilder
        DocumentBuilderFactory factory
                = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //
        StringBuilder xmlStringBuilder = new StringBuilder();
        xmlStringBuilder.append(inputString);
        ByteArrayInputStream input = new ByteArrayInputStream(
                xmlStringBuilder.toString().getBytes("UTF-8"));
        
        doc = builder.parse(input);
        
    }
    
    private NodeList busco_en_documento(String expresion) throws XPathExpressionException{
        XPath xPath =  XPathFactory.newInstance().newXPath();
        NodeList nodeList = (NodeList) xPath.compile(
                expresion).evaluate(
            doc, XPathConstants.NODESET);
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
    
    public void setText(String text){
        try {
            this.Obtengo_el_documento(text);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(LectorXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(LectorXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LectorXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
