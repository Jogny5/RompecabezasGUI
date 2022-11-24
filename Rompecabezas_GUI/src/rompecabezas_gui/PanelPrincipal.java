package rompecabezas_gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
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
    private Image img = Toolkit.getDefaultToolkit().getImage(PanelPrincipal.class.getResource("/kirby.jpg"));
    
    public PanelPrincipal(){
        
        this.setLayout(null);
        this.setBackground(Color.white);
        this.addMouseListener(this);  
    }
    
    
    public void paint(Graphics g){
        super.paint(g);
        g.drawRect(115, 300, 750, 400);
        g.drawImage(img, 116, 301, 749, 399,this);
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
