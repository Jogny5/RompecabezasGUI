package rompecabezas_gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class PanelPrincipal extends JPanel implements MouseListener{
    public PanelPrincipal(){
        this.setLayout(null);
        this.setBackground(Color.white);
        this.addMouseListener(this);
    }
    
    public void paint(Graphics g){
        super.paint(g);
    }

    
    
    
    
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
