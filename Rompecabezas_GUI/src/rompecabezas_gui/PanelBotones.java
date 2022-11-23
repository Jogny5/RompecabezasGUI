package rompecabezas_gui;

import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBotones extends JPanel{
    
    private ModoEditor Meditor;
    private ModoJuego Mjuego;
    private int ModoDeJuego=0;
    
    public PanelBotones(){
        
        this.setLayout(new FlowLayout());
        this.setBackground(Color.gray);
       
        Botones();     
    }
    
    
    public void paint(Graphics g){
        super.paint(g);
        
    }   
     
    public void Botones(){
        JButton BotonEditor=new JButton("Modo editor");
        BotonEditor.addActionListener(Editoraccion);
        this.add(BotonEditor);
        
        JButton BotonJuego=new JButton("Modo Juego");
        BotonJuego.addActionListener(Juegoraccion);
        this.add(BotonJuego);
    }
    
    
    ActionListener Editoraccion=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            //cambia al panel del modo editor
            ModoDeJuego=0;
        }
    };
    
    ActionListener Juegoraccion=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            //cambia al panel del modo juego
            ModoDeJuego=1;
        }
    };
}