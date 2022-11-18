package rompecabezas_gui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Ventana extends JFrame{
    
    public Ventana(){
        super();
        setTitle("Rompecabezas"); 
        this.setLayout(new BorderLayout());
        //this.add(new PanelPrincipal( ),BorderLayout.CENTER);
        
        this.setSize(1000,800);              
        setLocationRelativeTo(null);                    
        this.getContentPane().setBackground(Color.white); 
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);       
        this.setVisible(true); 
    }
}
