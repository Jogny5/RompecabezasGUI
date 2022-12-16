package rompecabezas_gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Clase que tiene al rompecabezas
 * @author Jorge
 * @author Diego
 */
public class Rompecabezas extends JPanel{
    /**
     * Inicializa Rompecabezas
     */    
    public Rompecabezas(){
        this.setLayout(null);
        this.setBackground(Color.white);
    }
    /**
     * metodo paint
     * @param g paint
     */
    public void paint(Graphics g){ 
       g.setColor(Color.gray);      
       g.fillRect(250,40,500,500);   
    
    }
    
}
