package rompecabezas_gui;

import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Clase donde estan los botones que cambian los modos de juego
 * y los botones que rotan las piezas
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
     * Metodo paint
     * @param g paint
     */
    public void paint(Graphics g){
        super.paint(g);
        
    }   
    /**
     * Crea todos los botones 
     */ 
    public void Botones(){
        JButton PiezaEleccion = new JButton("Pieza 1");
        JButton PiezaEleccion2 = new JButton("Pieza 2");
        JButton PiezaEleccion3 = new JButton("Pieza 3");
        JButton PiezaEleccion4 = new JButton("Pieza 4");
        
        PiezaEleccion.addActionListener(PE);
        PiezaEleccion2.addActionListener(PE2);
        PiezaEleccion3.addActionListener(PE3);
        PiezaEleccion4.addActionListener(PE4);
        
        this.add(PiezaEleccion);
        this.add(PiezaEleccion2);
        this.add(PiezaEleccion3);
        this.add(PiezaEleccion4);
        
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
    /**
     * Boton para seleccionar la pieza 1
     */
    ActionListener PE=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            PanelPrincipal.getInstancia().setPiezaelegir(1);
        }
    };
    /**
     * Boton para seleccionar la pieza 2
     */
    ActionListener PE2=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            PanelPrincipal.getInstancia().setPiezaelegir(2);
        }
    };
    /**
     * Boton para seleccionar la pieza 3
     */
    ActionListener PE3=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            PanelPrincipal.getInstancia().setPiezaelegir(3);
        }
    };
    /**
     * Boton para seleccionar la pieza 4
     */
    ActionListener PE4=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            PanelPrincipal.getInstancia().setPiezaelegir(4);
        }
    };
    /**
     * Boton que hace la rotacion hacia la izquierda
     */
    ActionListener RIzq=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
           PanelPrincipal.getInstancia().RotarIzq();
        }
    };
    /**
     * Boton que hace la rotacion hacia la derecha
     */
    ActionListener RDer=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
          PanelPrincipal.getInstancia().RotarDer();
        }
    };
    /**
     * Boton que cambia al modo editor
     */
    ActionListener Editoraccion=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
         PanelPrincipal.getInstancia().EstadoEditor();
         
          
          
        }
    };
    /**
     * Boton que cambia al modo juego
     */
    ActionListener Juegoraccion=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
         PanelPrincipal.getInstancia().EstadoJuego();
         
        }
    };
    
}