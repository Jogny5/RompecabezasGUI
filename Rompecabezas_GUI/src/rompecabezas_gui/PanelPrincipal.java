package rompecabezas_gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.Point;
import javax.swing.JComponent;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelPrincipal extends JPanel implements MouseListener,MouseMotionListener{
    
    private ModoEditor Meditor;
    private ModoJuego Mjuego;
    private int ModoDeJuego=0;
    private Image img = Toolkit.getDefaultToolkit().getImage(PanelPrincipal.class.getResource("/kirby.png"));
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
    
    public int xImagen=10;      //pieza 1 random
    public int yImagen=10;
    public int xImagen2=10;
    public int yImagen2=10;
    public int xImagen3=10;
    public int yImagen3=10;
    public int xImagen4=10;
    public int yImagen4=10;
    public int xImagenPanel=200;            //115
    public int yImagenPanel=400;            //300
    public int anchoPanel=550;
    public int altoPanel=300;        
    
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
    
    ImageIcon icon = new ImageIcon(img);
    Point initialClick;
    
    int icx1;
    int icy1;
    int count=0;
    
    public PanelPrincipal(){
        
        this.setLayout(null);
        this.setBackground(Color.white);
        //this.addMouseListener(this);
        
        
        label=new JLabel();
        label.setBounds(xImagenPanel, yImagenPanel, anchoPanel, altoPanel);
        label.setIcon(icon);
        label.addMouseListener(this);
        this.add(label);
        
        labelPieza1=new JLabel();
        labelPieza1.setBounds(10,10,550,300);
        labelPieza1.addMouseListener(this);
        labelPieza1.addMouseMotionListener(this);
        this.add(labelPieza1);
        
        
        
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
            
            g.setColor(Color.black);
            g.fillPolygon(p1);
            g.drawPolygon(p1);
        }
        
        g.drawRect(xImagenPanel, yImagenPanel, anchoPanel, altoPanel);
        g.drawImage(img, xImagenPanel, yImagenPanel, anchoPanel, altoPanel,this);        
        
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
         
          
    
        //si L1 empieza abajo y L2 empieza arriba
        if(L1y1>intersecciony && L2y1<intersecciony && pieza2estado!=0){
            //ver si l1y1 esta en laparte de abajo
            if(L1y1>=intersecciony+yImagen-yImagenPanel){
                //si uno toca la izquierda
                if(L1x1==xImagenPanel || L2x1==xImagenPanel){
                    //si ambos tocan la izquierda
                    if(L1x1==xImagenPanel && L2x1==xImagenPanel){
                        p1.addPoint(xImagen, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(xImagen, yImagen);
                        p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+L1y1-yImagenPanel);
                        System.out.println("dddddd 1111");
                    }else if(L1x1==xImagenPanel){
                        p1.addPoint(xImagen, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+L1y1-yImagenPanel);
                        System.out.println("lllpplpplplp 111");
                    }else if(L2x1==xImagenPanel){
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(xImagen, yImagen);
                        p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                        System.out.println("rrrrr 111"); 
                    }else{
                        System.out.println("caso otro 1111");
                    }
                    
                }else{
                    //
                    p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+altoPanel);
                    p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                    p1.addPoint(xImagen+anchoPanel, yImagen);
                    p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen);
                    p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                    System.out.println("caso no extremos izq y derecha");
                }
                
            }//else if  
            
            

        }else if(L1y1<intersecciony && L2y1>intersecciony && pieza2estado!=0){
            if(L2y1>=intersecciony+yImagen-yImagenPanel){
                if(L1x1==xImagenPanel || L2x1==xImagenPanel){
                    //si ambos tocan la izquierda
                    if(L1x1==xImagenPanel && L2x1==xImagenPanel){
                        p1.addPoint(xImagen, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(xImagen, yImagen);
                        p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+L1y1-yImagenPanel);
                        System.out.println("dddddd 22222");
                    }else if(L1x1==xImagenPanel){
                        p1.addPoint(xImagen, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        if(L2x2==xImagen+anchoPanel){
                            p1.addPoint(xImagen+anchoPanel, yImagen);
                            p1.addPoint(L2x2+xImagen-xImagenPanel, yImagen);
                            System.out.println("gggggggg 22222");
                        }else{
                            p1.addPoint(xImagen+anchoPanel, yImagen);
                            p1.addPoint(L2x2+xImagen-xImagenPanel, yImagen+L2y2-yImagenPanel);
                            System.out.println("gnng 22222");
                        }
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+L1y1-yImagenPanel);
                        System.out.println("lllpplpplplp 222");
                    }else if(L2x1==xImagenPanel){
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen, yImagen+altoPanel);
                        p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                        System.out.println("rrrr 2222");
                    }else{
                        System.out.println("caso otro2222");
                    }
                    
                }else if(L1x1>L2x2){
                    //
                    p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen);
                    p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                    p1.addPoint(xImagen+anchoPanel, yImagen);
                    p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen+altoPanel);
                    p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                    System.out.println("ddddmmmmdd2222");
                }else{
                    p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen+altoPanel);
                    p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                    p1.addPoint(xImagen+anchoPanel, yImagen);
                    p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen);
                    p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                    System.out.println("caso no extremos en izq y derecha");
                }
            }
            
            System.out.println("rarraarra22222");
        }else if(L2y1>intersecciony && L1y1>intersecciony && pieza2estado!=0){
            if(L2y1>=intersecciony+yImagen-yImagenPanel){
                
                if(L1x1==xImagenPanel || L2x1==xImagenPanel){
                    //si ambos tocan la izquierda
                    if(L1x1==xImagenPanel && L2x1==xImagenPanel){
                        p1.addPoint(xImagen, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(xImagen, yImagen);
                        p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+L1y1-yImagenPanel);
                        System.out.println("dddddd3333");
                    }else if(L1x1==xImagenPanel){
                        p1.addPoint(xImagen, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(L2x2+xImagen-xImagenPanel, yImagen);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+L1y1-yImagenPanel);
                        System.out.println("lllpplpplplp 3333");
                    }else if(L2x1==xImagenPanel){
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(xImagen, yImagen);
                        p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                        System.out.println("rrrrr3333");
                    }else{
                        System.out.println("caso otro3333");
                    }
                    
                }else if(L1x2==xImagenPanel+anchoPanel || L2x2==xImagenPanel+anchoPanel ){
                    System.out.println("333uiuih");
                    if(L1x2==xImagenPanel+anchoPanel && L2x2==xImagenPanel+anchoPanel){
                        if(L1x1<L2x1){
                            p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen+400);
                            p1.addPoint(xImagen+anchoPanel, yImagen);
                            p1.addPoint(xImagen, yImagen);
                            p1.addPoint(xImagen, yImagen+altoPanel);
                            p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+altoPanel);
                            p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                            System.out.println("nnnnnnnnnnnnnnn 333.111");
                        }else if(L1x1>L2x1){
                            p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen);
                            p1.addPoint(xImagen, yImagen);
                            p1.addPoint(xImagen, yImagen+altoPanel);
                            p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen+altoPanel);
                            p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                            System.out.println("nnnnnnnnnnnnnnn 333.222");
                        }else{
                            System.out.println("falta caso333.111: ");
                        }
                    }else if(L1x2==xImagenPanel+anchoPanel){
                        p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(xImagen, yImagen);
                        p1.addPoint(xImagen, yImagen+altoPanel);
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+altoPanel);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                        System.out.println("bbbbbbbbbb 3333");
                    }else if(L2x2==xImagenPanel+anchoPanel){
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(xImagen, yImagen);
                        p1.addPoint(xImagen, yImagen+altoPanel);
                        p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen+altoPanel);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                        System.out.println("bbbbbbbbb 3333.2222");
                    }else{
                        System.out.println("falta caso 33.22");
                    }
                       
                }
            }
            
            System.out.println("rarraarra 3333");
        }else if(L2y1<intersecciony && L1y1<intersecciony && pieza2estado!=0){
            if(L2y1>=intersecciony+yImagen-yImagenPanel){
                if(L1x1==xImagenPanel || L2x1==xImagenPanel){
                    //si ambos tocan la izquierda
                    if(L1x1==xImagenPanel && L2x1==xImagenPanel){
                        p1.addPoint(xImagen, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(xImagen, yImagen);
                        p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+L1y1-yImagenPanel);
                        System.out.println("dddddd 4444");
                    }else if(L1x1==xImagenPanel){
                        p1.addPoint(xImagen, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+L1y1-yImagenPanel);
                        System.out.println("lllpplpplplp 444");
                    }else if(L2x1==xImagenPanel){
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen, yImagen+altoPanel);

                        p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                        System.out.println("rrrrr 444");
                    }else{
                        System.out.println("caso otro 4444");
                    }
                    
                }else{
                    //
                    p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen);
                    p1.addPoint(xImagen, yImagen);
                    p1.addPoint(xImagen, yImagen+altoPanel);
                    p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                    p1.addPoint(xImagen+anchoPanel, yImagen);
                    p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen);
                    p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                    System.out.println("dddddd 4444");
                }
            }
            
            System.out.println("rarraarra 44444");
        }
        
        this.remove(label);
        
        
        labelPieza1.setIcon(icon);
        
        
        repaint();
        
    }
  
    
    
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
          
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
            p1.getBounds();
            System.out.println(p1.getBounds());
            initialClick = e.getPoint();
            icx1=e.getX()+labelPieza1.getLocation().x;
            icy1=e.getY()+labelPieza1.getLocation().y;
            
            System.out.println(icx1+ "xd"+ icy1);
            System.out.println(e.toString());

            count=0;
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        
                
        if(p1.contains(icx1,icy1)==false && count==0){
        
            int thisX = labelPieza1.getLocation().x;
            int thisY = labelPieza1.getLocation().y;


            // Determine how much the mouse moved since the initial click
            int xMoved = (labelPieza1.getLocation().x + e.getX()) - (thisX + initialClick.x);
            int yMoved = (labelPieza1.getLocation().y + e.getY()) - (thisY + initialClick.y);

            // Move picture to this position
            int X = thisX + xMoved;
            int Y = thisY + yMoved;

            p1.translate(xMoved, yMoved);

            labelPieza1.setLocation(X,Y);
            
            count=1;

            repaint();
        
        }
        
        else if(count==1){
        
            int thisX = labelPieza1.getLocation().x;
            int thisY = labelPieza1.getLocation().y;


            // Determine how much the mouse moved since the initial click
            int xMoved = (labelPieza1.getLocation().x + e.getX()) - (thisX + initialClick.x);
            int yMoved = (labelPieza1.getLocation().y + e.getY()) - (thisY + initialClick.y);

            // Move picture to this position
            int X = thisX + xMoved;
            int Y = thisY + yMoved;

            p1.translate(xMoved, yMoved);

            labelPieza1.setLocation(X,Y);
            
            count=1;

            repaint();
        }
    }
    
    public void mouseMoved(MouseEvent e){
    }

    @Override
    public void mouseReleased(MouseEvent e) {
         
        initialClick = null;
        
    }

    
    @Override
    public void mouseEntered(MouseEvent e) {
        
        if (e.getSource() == label) {
        
        if(pieza1estado==0 && pieza2estado==0){
            int x=e.getX();
            int y=e.getY();
            if(x<4){
                x=0;
            }else if(x>anchoPanel-2){
                x=anchoPanel;
            }
            if(y<5){
                y=0;
            }else if(y>altoPanel-4){
                y=altoPanel;
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
            }else if(x>anchoPanel-2){
                x=anchoPanel;
            }
            if(y<5){
                y=0;
            }else if(y>altoPanel-4){
                y=altoPanel;
            }
        
            L2x1=x+xImagenPanel;
            L2y1=y+yImagenPanel;
            repaint();
            System.out.println("entrada "+x+" , "+y);
        }
        }
    }

    
    @Override
    public void mouseExited(MouseEvent e) {
        
        
        if (e.getSource() == label) {
            
            if(pieza1estado==1 && pieza2estado==0){
                int x=e.getX();
                int y=e.getY();
                if(x<5){
                    x=0;
                }else if(x>anchoPanel-2){
                    x=anchoPanel;
                }

                if(y<5){
                    y=0;
                }else if(y>altoPanel-4){
                    y=altoPanel;
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
                }else if(x>anchoPanel-2){
                    x=anchoPanel;
                }

                if(y<5){
                    y=0;
                }else if(y>altoPanel-4){
                    y=altoPanel;
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
    
   
}
