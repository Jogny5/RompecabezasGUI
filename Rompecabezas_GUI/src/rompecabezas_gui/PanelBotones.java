package rompecabezas_gui;

import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 * @author Jorge
 * @author Diego
 */

public class PanelBotones extends JPanel{
    /**
     * Inicializa el panel de los botones
     */    
    public PanelBotones(){
        
        this.setLayout(new FlowLayout());
        this.setBackground(Color.gray);
       
        Botones();     
    }
    
    /**
     * 
     * @param g 
     */
    public void paint(Graphics g){
        super.paint(g);
        
    }   
    /**
     * Crea todos los botones 
     */ 
    public void Botones(){
        JButton RotarIzq=new JButton("Rotar 90 izquierda");
        RotarIzq.addActionListener(RIzq);
        this.add(RotarIzq);
        
        JButton RotarDer=new JButton("Rotar 90 derecha");
        RotarDer.addActionListener(RDer);
        this.add(RotarDer);
        
        JButton BotonEditor=new JButton("Modo editor");
        BotonEditor.addActionListener(Editoraccion);
        this.add(BotonEditor);
        
        JButton BotonJuego=new JButton("Modo Juego");
        BotonJuego.addActionListener(Juegoraccion);
        this.add(BotonJuego);
 
    }
    
    ActionListener RIzq=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            PanelPrincipal.getInstancia().RotarIzq();
        }
    };
    
    ActionListener RDer=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            PanelPrincipal.getInstancia().RotarDer();
        }
    };
    
    ActionListener Editoraccion=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            Ventana.getInstancia().ModoEditor();
        }
    };
    
    ActionListener Juegoraccion=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            Ventana.getInstancia().ModoJuego();
        }
    };
}