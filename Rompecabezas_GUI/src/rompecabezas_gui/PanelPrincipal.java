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
    private Polygon Linea1;
    
    public int L2x1;
    public int L2y1;
    public int L2x2;
    public int L2y2;
    private Polygon Linea2;
    
    public int interseccionx=-1;
    public int intersecciony=-1;
    
    public int xImagen=10;
    public int yImagen=10;
    public int xImagen2=10;
    public int yImagen2=10;
    public int xImagen3=10;
    public int yImagen3=10;
    public int xImagen4=10;
    public int yImagen4=10;
    public int xImagenPanel=115;            //115
    public int yImagenPanel=300;            //300
    public int pieza1estado=0;
    public int pieza2estado=0;
    public int pieza3estado=0;
    public int pieza4estado=0;
    public int PiezaMasPequena;             //si es 0 escoge la izquierda, 1 la derecha
    
    private Polygon p1;
    private Polygon p2;
    private Polygon p3;
    private Polygon p4;
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
        p3=new Polygon();
        p4=new Polygon();
        Linea1=new Polygon();
        Linea2=new Polygon();
        
    }
    
    
    public void paint(Graphics g){
        super.paint(g);
        //pintar las piezas una vez las 4 esten hechas
        if(L1x2!=-1 && pieza2estado==1){
            g.drawImage(img, 10, 10, 749, 399,this);
            g.setColor(Color.white);
            g.fillPolygon(p1);
            g.drawPolygon(p1);
        }
        
        
        g.drawRect(xImagenPanel, yImagenPanel, 750, 400);
        g.drawImage(img, xImagenPanel, yImagenPanel, 749, 399,this);
        
        
        g.setColor(Color.blue);
        if(L1x2>=0){
            //g.drawLine(L1x1, L1y1, L1x2, L1y2);
            g.setColor(Color.red);
            g.drawPolygon(Linea1);
            
            if(L1x2>=0){
                g.drawPolygon(Linea2);
            
            }
            if(interseccionx>0){
                g.fillOval((int)interseccionx,(int)intersecciony,15,15);
                g.drawOval((int)interseccionx,(int)intersecciony,15,15);
            }
            
        }
    }
    
    public void interseccion(){
        System.out.println(pieza2estado);
        if(pieza2estado!=0){
            int x1,x2,y1,y2;
            
            for(int i=0;i<30;i++){
                x1=(L1x1+i*(L1x2-L1x1)/30);
                y1=(L1y1+i*(L1y2-L1y1)/30);
                
                
                for(int j=0;j<30;j++){
                    
                    x2=(L2x1+j*(L2x2-L2x1)/30);
                    y2=(L2y1+j*(L2y2-L2y1)/30);
                    
                    if(Math.abs(x1-x2)<10 && Math.abs(y1-y2)<10){
                        interseccionx=x1;
                        intersecciony=y1;
                        System.out.println("La interseccion es "+interseccionx+" , "+intersecciony);
                        System.out.println("La interseccion en el label es "+(interseccionx-xImagenPanel)+
                                " , "+(intersecciony-yImagenPanel));
                        i=30;
                        j=30;
                }
                
                    
                }
               
                
            }
            if(interseccionx<0){
                System.out.println("no intersectan, ingrese las lineas de nuevo");
                        Linea1=new Polygon();
                        Linea2=new Polygon();
                
                        pieza1estado=0; 
                        pieza2estado=0;
                        pieza3estado=0; 
                        pieza4estado=0;
            }
        }
            
        dividirPiezas();
        
    }
    
    public void dividirPiezas(){
       
        
        //sacar las 4 figuras 
        
        
        //idea partir desde x1,y1 recorriendo por el borde hasta llegar a x2,y2
        //recorrido derecha
        if(L1y2<L1y1){
            PiezaMasPequena=1;      //0 a la izquerda 1 a la derecha
        }
        /*
        if(L1x1==xImagenPanel && L1y2==yImagenPanel && PiezaMasPequena==1 && pieza1estado==1){
            p1=new Polygon();
            
            p1.addPoint(L1x2+xImagen-xImagenPanel,yImagen);
            p1.addPoint(xImagen+750,yImagen);
            p1.addPoint(xImagen+750,yImagen+400);
            p1.addPoint(xImagen,yImagen+400);
            p1.addPoint(xImagen,L1y1+yImagen-yImagenPanel);
            
            
            
        }
*/
        if(L1y1>intersecciony && L2y1<intersecciony && pieza2estado!=0){
            //ver si l1y1 esta en laparte de abajo
            if(L1y1>=intersecciony+yImagen-yImagenPanel){
                if(L1x1==xImagenPanel || L2x1==xImagenPanel){
                    if(L1x1==xImagenPanel && L2x1==xImagenPanel){
                        p1.addPoint(xImagen, yImagen+400);
                        p1.addPoint(xImagen+750, yImagen+400);
                        p1.addPoint(xImagen+750, yImagen);
                        p1.addPoint(xImagen, yImagen);
                        p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+L1y1-yImagenPanel);
                        System.out.println("dddddd");
                    }else if(L1x1==xImagenPanel){
                        p1.addPoint(xImagen, yImagen+400);
                        p1.addPoint(xImagen+750, yImagen+400);
                        p1.addPoint(xImagen+750, yImagen);
                        p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+L1y1-yImagenPanel);
                        
                    }else if(L2x1==xImagenPanel){
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+400);
                        p1.addPoint(xImagen+750, yImagen+400);
                        p1.addPoint(xImagen+750, yImagen);
                        p1.addPoint(xImagen, yImagen);
                        p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                        System.out.println("rrrrr");
                    }
                    
                }else{
                    p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+400);
                    p1.addPoint(xImagen+750, yImagen+400);
                    p1.addPoint(xImagen+750, yImagen);
                    p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen);
                    p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                    System.out.println("dddddd");
                }
                
            }
            
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
            
            
        }else if(pieza1estado==1 && pieza2estado==0){
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
            
            if(L2x2<L2x1){
                int aux=L2x1;
                L2x1=L2x2;
                L2x2=aux;
                aux=L2y1;
                L2y1=L2y2;
                L2y2=aux;
            }
            
            RellenaConPuntos.nuevaLinea(L2x1, L2y1, L2x2, L2y2, Linea2);
            
            repaint();
            System.out.println("salida "+x+" , "+y);
            pieza2estado=1;
            interseccion();
            
            
        }else if(pieza1estado==0 && pieza2estado==0){
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
            if(L1x2<L1x1){
                int aux=L1x1;
                L1x1=L1x2;
                L1x2=aux;
                aux=L1y1;
                L1y1=L1y2;
                L1y2=aux;
            }
            
            RellenaConPuntos.nuevaLinea(L1x1, L1y1, L1x2, L1y2, Linea1);

            repaint();
            System.out.println("salida "+x+" , "+y);
            
            pieza1estado=1;
        }
        
    }
}
