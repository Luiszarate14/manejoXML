/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leexml;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author luisza
 */
public class LeeXML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        Lector lector = new Lector();
        String  texto = lector.read_file("books.xml");
        
        LectorXML lectorxml = new LectorXML();
        lectorxml.setText(texto);
        ArrayList <String> campos = new ArrayList( Arrays.asList(
                "title", "author", "year"));
        //http://www.w3schools.com/xml/xml_xpath.asp
        String expresion = "/bookstore/book[year=2005]";
        lectorxml.buscar(expresion, campos);
                
    }
    
}
