package rompecabezas_gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ModoJuego extends JPanel{
    
    public ModoJuego(){
        this.setLayout(null);
        this.setBackground(Color.white);
        this.setBounds(0,0,1000,700);
        
        Texto();
    }
    
    public void paint(Graphics g){ 
       g.setColor(Color.gray);      
       g.fillRect(250,80,500,500);   
    }
    
    public void Texto(){
        JLabel texto=new JLabel("Modo juego");
        texto.setBounds(350,100,150,40);
        this.add(texto);

    }
    
}
