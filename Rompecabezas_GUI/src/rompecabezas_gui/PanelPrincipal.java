package rompecabezas_gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
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
    public int L1x1;
    public int L1y1;
    public int L1x2=-1;
    public int L1y2;
    
    public int L2x1;
    public int L2y1;
    public int L2x2=-1;
    public int L2y2;
    
    public int xImagen=10;
    public int yImagen=10;
    public int xImagenPanel=115;            //115
    public int yImagenPanel=300;            //300
    public int pieza1estado=0;
    public int pieza2estado=0;
    public int PiezaMasPequena;             //si es 0 escoge la izquierda, 1 la derecha
    
    private Polygon p1;
    private Polygon p2;
    public JLabel label;
    public JLabel labelPieza1;
    public JLabel labelPieza2;
    
    public PanelPrincipal(){
        
        this.setLayout(null);
        this.setBackground(Color.white);
        //this.addMouseListener(this);
        
        
        label=new JLabel();
        label.setBounds(xImagenPanel, yImagenPanel, 750, 400);
        label.addMouseListener(this);
        this.add(label);
        
        
        p1=new Polygon();
        p2=new Polygon();
        
    }
    
    
    public void paint(Graphics g){
        super.paint(g);
        
        if(L1x2!=-1){
            g.drawImage(img, 10, 10, 749, 399,this);
            g.setColor(Color.white);
            g.fillPolygon(p1);
            g.drawPolygon(p1);
        }
        
        
        g.drawRect(xImagenPanel, yImagenPanel, 750, 400);
        g.drawImage(img, xImagenPanel, yImagenPanel, 749, 399,this);
        
        
        g.setColor(Color.blue);
        if(L1x2>=0){
            g.drawLine(L1x1, L1y1, L1x2, L1y2);
            /*
            if(L1x2>=0){
                g.drawLine(L2x1, L2y1, L2x2, L2y2);
            
            }
            */
        }
    }
    
    
    
    public void dividirPiezas(){
        
        //idea partir desde x1,y1 recorriendo por el borde hasta llegar a x2,y2
        //recorrido derecha
        if(L1y2<L1y1){
            PiezaMasPequena=1;      //0 a la izquerda 1 a la derecha
        }
        
        if(L1x1==xImagenPanel && PiezaMasPequena==1){
            p1.addPoint(L1x2+xImagen-xImagenPanel,yImagen);
            p1.addPoint(xImagen+750,yImagen);
            p1.addPoint(xImagen+750,yImagen+400);
            p1.addPoint(xImagen,yImagen+400);
            p1.addPoint(xImagen,L1y1+yImagen-yImagenPanel);
            
        }
        repaint();
        
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
        
        if(pieza1estado==0 && pieza2estado==0){
            int x=e.getX();
            int y=e.getY();
            if(x<4){
                x=0;
            }else if(x>750){
                x=750;
            }
            if(y<5){
                y=0;
            }else if(y>396){
                y=400;
            }
        
            L1x1=x+xImagenPanel;
            L1y1=y+yImagenPanel;
            repaint();
            System.out.println("entrada "+x+" , "+y);
        }
        
        if(pieza1estado==1 && pieza2estado==0){
            int x=e.getX();
            int y=e.getY();
            if(x<4){
                x=0;
            }else if(x>750){
                x=750;
            }
            if(y<5){
                y=0;
            }else if(y>396){
                y=400;
            }
        
            L2x1=x+xImagenPanel;
            L2y1=y+yImagenPanel;
            repaint();
            System.out.println("entrada "+x+" , "+y);
        }
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
        if(pieza1estado==1 && pieza2estado==0){
            int x=e.getX();
            int y=e.getY();
            if(x<5){
                x=0;
            }else if(x>750){
                x=750;
            }
            
            if(y<5){
                y=0;
            }else if(y>396){
                y=400;
            }
            
            L2x2=x+xImagenPanel;
            L2y2=y+yImagenPanel;
            repaint();
            System.out.println("salida "+x+" , "+y);
            
            dividirPiezas();
            pieza2estado=1;
        }
        if(pieza1estado==0 && pieza2estado==0){
            int x=e.getX();
            int y=e.getY();
            if(x<5){
                x=0;
            }else if(x>750){
                x=750;
            }
            
            if(y<5){
                y=0;
            }else if(y>396){
                y=400;
            }
            
            L1x2=x+xImagenPanel;
            L1y2=y+yImagenPanel;
            repaint();
            System.out.println("salida "+x+" , "+y);
            
            dividirPiezas();
            pieza1estado=1;
        }
        
    }
}
