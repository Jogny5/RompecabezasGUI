package rompecabezas_gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelPrincipal extends JPanel implements MouseListener{
    
    private ModoEditor Meditor;
    private ModoJuego Mjuego;
    private int ModoDeJuego=0;
    
    public PanelPrincipal(){
        this.setLayout(null);
        this.setBackground(Color.white);
        this.addMouseListener(this);
          
        Botones();
        Texto();
    }
    
    
    public void paint(Graphics g){
        super.paint(g);
        
    }
    
    
    
    public void Texto(){
        JLabel texto=new JLabel("Rompecabezas");
        texto.setBounds(420,10,150,40);
        this.add(texto);
    }

    
     
    public void Botones(){
        JButton BotonEditor=new JButton("Modo editor");
        BotonEditor.setBounds(355,600,140,40);
        BotonEditor.addActionListener(Editoraccion);
        this.add(BotonEditor);
        
        JButton BotonJuego=new JButton("Modo Juego");
        BotonJuego.setBounds(555,600,140,40);
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
    
    
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        }

    @Override
    public void mouseReleased(MouseEvent e) {
        }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
