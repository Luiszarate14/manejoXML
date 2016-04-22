/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leexml;


import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luisza
 */
public class Lector {
    
    BufferedReader br;
    
    private String read_file_with_throws(String filepath) throws FileNotFoundException, IOException{
        br = new BufferedReader(new FileReader(filepath));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        return sb.toString();
    
    }
    
    public String read_file(String filepath){
        String dev = null;
        try {
            dev = read_file_with_throws(filepath);
        } catch (IOException ex) {
            System.out.println(ex.toString());
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dev;
    }
        
    public ArrayList<Estudiante> read_xml(String filepath){
       // https://docs.oracle.com/javase/7/docs/api/java/beans/XMLDecoder.html
       XMLDecoder d;
       ArrayList<Estudiante>  estudiantes=null;
        try {
            d = new XMLDecoder(
                    new BufferedInputStream(
                            new FileInputStream(filepath)));
            
            estudiantes = (ArrayList<Estudiante>) d.readObject();
            d.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        }

        return estudiantes;
    }
}
