package rompecabezas_gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Rompecabezas extends JPanel{
        
    public Rompecabezas(){
        this.setLayout(null);
        this.setBackground(Color.white);
    }
    
    public void paint(Graphics g){ 
       g.setColor(Color.gray);      
       g.fillRect(250,40,500,500);   
    
    }
    
}
