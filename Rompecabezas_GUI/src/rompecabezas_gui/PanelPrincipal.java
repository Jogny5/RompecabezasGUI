package rompecabezas_gui;

import java.awt.Color;
import java.awt.Font;
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

/**
 * @author jorge
 * @author diego
 */

public class PanelPrincipal extends JPanel implements MouseListener,MouseMotionListener{
    
    private static final PanelPrincipal instancia = new PanelPrincipal();
    
    private ModoEditor Meditor;
    private ModoJuego Mjuego;
    private int ModoDeJuego=0;
    private Image img = Toolkit.getDefaultToolkit().getImage(PanelPrincipal.class.getResource("/kirby.png"));
    private Image img1 = Toolkit.getDefaultToolkit().getImage(PanelPrincipal.class.getResource("/kirby1.png"));
    private Image img2 = Toolkit.getDefaultToolkit().getImage(PanelPrincipal.class.getResource("/kirby2.png"));
    private Image img3 = Toolkit.getDefaultToolkit().getImage(PanelPrincipal.class.getResource("/kirby3.png"));
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
    public int xImagen2=380;
    public int yImagen2=10;
    public int xImagen3=810;
    public int yImagen3=10;
    public int xImagen4=700;
    public int yImagen4=400;
    public int xImagenPanel=200;            
    public int yImagenPanel=400;            
    public int anchoPanel=450;
    public int altoPanel=250;        
    
    public int pieza1terminada=0;
    public int pieza2terminada=0;
    public int pieza3terminada=0;
    public int pieza4terminada=0;
    
    public int pieza1estado=0;
    public int pieza2estado=0;
    public int pieza3estado=0;
    public int pieza4estado=0;
    
    private Polygon p1;
    private Polygon p2;
    private Polygon p3;
    private Polygon p4;
    public JLabel label;
    public JLabel labelPieza1;
    public JLabel labelPieza2;
    public JLabel labelPieza3;
    public JLabel labelPieza4;
    
    public JLabel labelfinal;
    
    ImageIcon icon = new ImageIcon(img);
    ImageIcon icon1 = new ImageIcon(img1);
    ImageIcon icon2 = new ImageIcon(img2);
    ImageIcon icon3 = new ImageIcon(img3);
    
    int Rotacion=0;
    int Rotacion2=0;
    int Rotacion3=0;
    int Rotacion4=0;
    
    int LastRota=0;
    
    Point initialClick;
    
    int icx1;
    int icy1;
    int count=0;
    
    /**
     * Inicializa el panel y los label de las piezas
     */
    int piezaSeleccionada=1;
    
    public PanelPrincipal(){
        
        this.setLayout(null);
        this.setBackground(Color.white);
        //this.addMouseListener(this);
        
        labelfinal=new JLabel("Fin del juego");
        labelfinal.setBounds(-600,100, 400, 100);
        labelfinal.setFont(new Font("arial",Font.PLAIN,50));
        this.add(labelfinal);
                
        label=new JLabel();
        label.setBounds(xImagenPanel, yImagenPanel, anchoPanel, altoPanel);
        label.setIcon(icon);
        label.addMouseListener(this);
        this.add(label);
        
        labelPieza1=new JLabel();
        labelPieza1.setBounds(xImagen,yImagen,anchoPanel,altoPanel);
        labelPieza1.addMouseListener(this);
        labelPieza1.addMouseMotionListener(this);
        this.add(labelPieza1);
        
        labelPieza2=new JLabel();
        labelPieza2.setBounds(xImagen2, yImagen2, anchoPanel, altoPanel);
        labelPieza2.addMouseListener(this);
        labelPieza2.addMouseMotionListener(this);
        this.add(labelPieza2);
        
        labelPieza3=new JLabel();
        labelPieza3.setBounds(xImagen3, yImagen3, anchoPanel, altoPanel);
        labelPieza3.addMouseListener(this);
        labelPieza3.addMouseMotionListener(this);
        this.add(labelPieza3);
        
        labelPieza4=new JLabel();
        labelPieza4.setBounds(xImagen4,yImagen4,anchoPanel,altoPanel);
        labelPieza4.addMouseListener(this);
        labelPieza4.addMouseMotionListener(this);
        this.add(labelPieza4);
        
        
        
        p1=new Polygon();
        p2=new Polygon();
        p3=new Polygon();
        p4=new Polygon();
        Linea1=new Polygon();
        Linea2=new Polygon();
        
    }
    /**
     * 
     * @return Una instancia de panelprincipal
     */
    public static PanelPrincipal getInstancia(){
        
        return instancia;
    }
    

    public void setPiezaelegir(int x){
        
        piezaSeleccionada=x;
    }
    
  

    /**
     * 
     * @param g Son todos los graficos que usan
     */
    public void paint(Graphics g){
        super.paint(g);
        
        g.drawRect(xImagenPanel, yImagenPanel, anchoPanel, altoPanel);
        g.drawImage(img, xImagenPanel, yImagenPanel, anchoPanel, altoPanel,this); 
        //pintar las piezas una vez las 4 esten hechas
        if(L1x2!=-1 && pieza2estado==1){
            
            
            
            g.setColor(Color.white);
            g.fillPolygon(p1);
            g.drawPolygon(p1);
            g.fillPolygon(p2);
            g.drawPolygon(p2);
            g.fillPolygon(p3);
            g.drawPolygon(p3);
            g.fillPolygon(p4);
            g.drawPolygon(p4);
        }
        if(pieza1terminada==1 && pieza2terminada==1 && pieza3terminada==1 && pieza4terminada==1){
            labelfinal.setLocation(500,100);
            
        }
               
        
        g.setColor(Color.blue);
        if(L1x2>=0){
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
    /**
     * Calcula la interseccion entre 2 lineas
     */
    public void interseccion(){
        System.out.println(pieza2estado);
        if(pieza2estado!=0){
            int x1,x2,y1,y2;
            
            for(int i=0;i<50;i++){
                x1=(L1x1+i*(L1x2-L1x1)/50);
                y1=(L1y1+i*(L1y2-L1y1)/50);
                
                
                for(int j=0;j<50;j++){
                    
                    x2=(L2x1+j*(L2x2-L2x1)/50);
                    y2=(L2y1+j*(L2y2-L2y1)/50);
                    
                    if(Math.abs(x1-x2)<10 && Math.abs(y1-y2)<8){
                        interseccionx=x1;
                        intersecciony=y1;
                        System.out.println("La interseccion es "+interseccionx+" , "+intersecciony);
                        System.out.println("La interseccion en el label es "+(interseccionx-xImagenPanel)+
                                " , "+(intersecciony-yImagenPanel));
                        i=50;
                        j=50;
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
    /**
     * Crea 4 piezas separadas por las 2 lineas hechas tomando todos los posibles casos de lineas
     */
    public void dividirPiezas(){
       
        //sacar las 4 figuras 
        
        
        //Primer caso principal
        if(L1y1>intersecciony && L2y1<intersecciony && pieza2estado!=0){
            if(L1y1>=intersecciony+yImagen-yImagenPanel){
                if(L1x1==xImagenPanel || L2x1==xImagenPanel){
                    if(L1x1==xImagenPanel && L2x1==xImagenPanel){
                        p1.addPoint(xImagen, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(xImagen, yImagen);
                        p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+L1y1-yImagenPanel);
                        
                        if(L2x2!=xImagenPanel+anchoPanel){
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2);
                            p2.addPoint(xImagen2, yImagen2);
                            p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                            p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            p2.addPoint(L2x2+xImagen2-xImagenPanel, yImagen2+altoPanel);
                            
                            p3.addPoint(xImagen3, yImagen3+altoPanel);
                            p3.addPoint(L2x2+xImagen3-xImagenPanel, yImagen3+altoPanel);
                            p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3);
                            p3.addPoint(xImagen3, yImagen3);
                        }else{
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+L2y2-yImagenPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2);
                            p2.addPoint(xImagen2, yImagen2);
                            p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                            p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            
                            p3.addPoint(xImagen3, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                            p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3);
                            p3.addPoint(xImagen3, yImagen3);
                        }
                       
                     
                        
                        p4.addPoint(xImagen4, yImagen4+L2y1-yImagenPanel);
                        p4.addPoint(xImagen4, yImagen4+altoPanel);
                        p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                        p4.addPoint(xImagen4+anchoPanel, yImagen4+L1y2-yImagenPanel);
                        p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                        
                    }else if(L1x1==xImagenPanel){
                        p1.addPoint(xImagen, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+L1y1-yImagenPanel);
                        
                        
                        if(L2x2!=xImagenPanel+anchoPanel){
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2);
                            p2.addPoint(xImagen2, yImagen2);
                            p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                            p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            p2.addPoint(L2x2+xImagen2-xImagenPanel, yImagen2+altoPanel);
                            
                            p3.addPoint(xImagen3, yImagen3+altoPanel);
                            p3.addPoint(L2x2+xImagen3-xImagenPanel, yImagen3+L2y2-yImagenPanel);
                            p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3);
                            p3.addPoint(xImagen3, yImagen3);
                        }else{
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+L2y2-yImagenPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2);
                            p2.addPoint(xImagen2, yImagen2);
                            p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                            p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            
                            p3.addPoint(xImagen3, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                            p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3);
                            p3.addPoint(xImagen3, yImagen3);
                        }
                        
                        
                        p4.addPoint(xImagen4, yImagen4+L2y1-yImagenPanel);
                        p4.addPoint(xImagen4, yImagen4+altoPanel);
                        p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                        p4.addPoint(xImagen4+anchoPanel, yImagen4+L1y2-yImagenPanel);
                        p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                        
                        
                    }else if(L2x1==xImagenPanel){
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(xImagen, yImagen);
                        p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                        
                        if(L1x2!=xImagenPanel+anchoPanel){
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+L2y2-yImagenPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2);
                            p2.addPoint(xImagen2, yImagen2);
                            p2.addPoint(xImagen2, yImagen2+altoPanel);
                            p2.addPoint(L1x1+xImagen2-xImagenPanel, yImagen2+altoPanel);
                            p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            
                            p3.addPoint(xImagen3, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                            p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                            p3.addPoint(L1x2+xImagen3-xImagenPanel, yImagen3+L1y2-yImagenPanel);
                            p3.addPoint(xImagen3, yImagen3);
                            
                            p4.addPoint(xImagen4, yImagen4+L2y1-yImagenPanel);
                            p4.addPoint(xImagen4, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4);
                            p4.addPoint(L1x2+xImagen4-xImagenPanel, yImagen4);
                            p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                        
                        }else{
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+L2y2-yImagenPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2);
                            p2.addPoint(xImagen2, yImagen2);
                            p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                            p2.addPoint(L1x1+xImagen2-xImagenPanel, yImagen2+altoPanel);
                            p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);


                            p3.addPoint(xImagen3, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                            p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3);
                            p3.addPoint(xImagen3, yImagen3);
                            
                            
                            p4.addPoint(xImagen4, yImagen4+L2y1-yImagenPanel);
                            p4.addPoint(xImagen4, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+L1y2-yImagenPanel);
                            p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                        
                        }
                        
                        
                    }else{
                        System.out.println("falta otro caso 1111");
                    }
                    
                }else{
                    
                    p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+altoPanel);
                    p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                    p1.addPoint(xImagen+anchoPanel, yImagen);
                    p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen);
                    p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                    
                    if(L1x2==xImagenPanel+anchoPanel && L2x2==xImagenPanel+anchoPanel){

                        p2.addPoint(xImagen2+anchoPanel, yImagen2+L2y2-yImagenPanel);
                        p2.addPoint(xImagen2+anchoPanel, yImagen2);
                        p2.addPoint(xImagen2, yImagen2);
                        p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                        p2.addPoint(L1x1+xImagen2-xImagenPanel, yImagen2+altoPanel);
                        p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                        

                        p3.addPoint(xImagen3, yImagen3+altoPanel);
                        p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                        p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                        p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                        p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                        p3.addPoint(xImagen3+anchoPanel, yImagen3);
                        p3.addPoint(xImagen3, yImagen3);
                        

                        p4.addPoint(xImagen4, yImagen4);
                        p4.addPoint(xImagen4, yImagen4+altoPanel);
                        p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                        p4.addPoint(xImagen4+anchoPanel, yImagen4+L1y2-yImagenPanel);
                        p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                        p4.addPoint(L2x1+xImagen4-xImagenPanel, yImagen4);
                        
                    }else if(L1x2==xImagenPanel+anchoPanel){

                        p2.addPoint(xImagen2+anchoPanel, yImagen2+L2y2-yImagenPanel);
                        p2.addPoint(xImagen2+anchoPanel, yImagen2);
                        p2.addPoint(xImagen2, yImagen2);
                        p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                        p2.addPoint(L1x1+xImagen2-xImagenPanel, yImagen2+altoPanel);
                        p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                        p2.addPoint(L2x2+xImagen2-xImagenPanel, yImagen2+altoPanel);
                        
                        p3.addPoint(xImagen3, yImagen3+altoPanel);
                        p3.addPoint(L2x2+xImagen3-xImagenPanel, yImagen3+altoPanel);
                        p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                        p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                        p3.addPoint(xImagen3+anchoPanel, yImagen3);
                        p3.addPoint(xImagen3, yImagen3);
                        
                        p4.addPoint(xImagen4, yImagen4);
                        p4.addPoint(xImagen4, yImagen4+altoPanel);
                        p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                        p4.addPoint(xImagen4+anchoPanel, yImagen4+L1y2-yImagenPanel);
                        p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                        p4.addPoint(L2x1+xImagen4-xImagenPanel, yImagen4);
                        
                    }else if(L2x2==xImagenPanel+anchoPanel){

                        p2.addPoint(xImagen2+anchoPanel, yImagen2);
                        p2.addPoint(xImagen2, yImagen2);
                        p2.addPoint(xImagen2, yImagen2+altoPanel);
                        p2.addPoint(L1x1+xImagen2-xImagenPanel, yImagen2+altoPanel);
                        p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                        p2.addPoint(L2x2+xImagen2-xImagenPanel, yImagen2+L2y2-yImagenPanel);
                        

                        p3.addPoint(xImagen3, yImagen3+altoPanel);
                        p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                        p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                        p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                        p3.addPoint(L1x2+xImagen3-xImagenPanel, yImagen3+L1y2-yImagenPanel);
                        p3.addPoint(xImagen3, yImagen3);
                        

                        p4.addPoint(xImagen4, yImagen4);
                        p4.addPoint(xImagen4, yImagen4+altoPanel);
                        p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                        p4.addPoint(xImagen4+anchoPanel, yImagen4+L1y2-yImagenPanel);
                        p4.addPoint(L1x2+xImagen4-xImagenPanel, yImagen4);
                        p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                        p4.addPoint(L2x1+xImagen4-xImagenPanel, yImagen4);
                        
                        
                    }else{

                        p2.addPoint(xImagen2, yImagen2+altoPanel);
                        p2.addPoint(L1x1+xImagen2-xImagenPanel, yImagen2+altoPanel);
                        p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                        p2.addPoint(L2x2+xImagen2-xImagenPanel, yImagen2+altoPanel);
                        p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                        p2.addPoint(xImagen2+anchoPanel, yImagen2);
                        p2.addPoint(xImagen2, yImagen2);
                        

                        p3.addPoint(xImagen3, yImagen3);
                        p3.addPoint(xImagen3, yImagen3+altoPanel);
                        p3.addPoint(L2x2+xImagen3-xImagenPanel, yImagen3+altoPanel);
                        p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                        p3.addPoint(L1x2+xImagen3-xImagenPanel, yImagen3);


                        p4.addPoint(xImagen4, yImagen4);
                        p4.addPoint(xImagen4, yImagen4+altoPanel);
                        p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                        p4.addPoint(xImagen4+anchoPanel, yImagen4);
                        p4.addPoint(L1x2+xImagen4-xImagenPanel, yImagen4);
                        p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                        p4.addPoint(L2x1+xImagen4-xImagenPanel, yImagen4);
                        
                    }
                    
                }
                
            } 
            
            
        //Segundo caso principal   
        }else if(L1y1<intersecciony && L2y1>intersecciony && pieza2estado!=0){
            if(L2y1>=intersecciony+yImagen-yImagenPanel){
                if(L1x1==xImagenPanel || L2x1==xImagenPanel){
                    if(L1x1==xImagenPanel && L2x1==xImagenPanel){
                        
                        p1.addPoint(xImagen, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(xImagen, yImagen);
                        p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+L1y1-yImagenPanel);
                        
                        if(L2x2!=xImagenPanel+anchoPanel){

                            p2.addPoint(xImagen2+anchoPanel, yImagen2+L1y2-yImagenPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2);
                            p2.addPoint(xImagen2, yImagen2);
                            p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                            p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            

                            p3.addPoint(xImagen3, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                            p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                            p3.addPoint(xImagen3+L2x2-xImagenPanel, yImagen3);
                            p3.addPoint(xImagen3, yImagen3);
                            

                            p4.addPoint(xImagen4, yImagen4+L1y1-yImagenPanel);
                            p4.addPoint(xImagen4, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4);
                            p4.addPoint(xImagen4+L2x2-xImagenPanel, yImagen4);
                            p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                           
                        
                        }else{

                            p2.addPoint(xImagen2+anchoPanel, yImagen2+L1y2-yImagenPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2);
                            p2.addPoint(xImagen2, yImagen2);
                            p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                            p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            p2.addPoint(L2x2+xImagen2-xImagenPanel, yImagen2+altoPanel);
                            

                            p3.addPoint(xImagen3, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                            p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3);
                            p3.addPoint(xImagen3, yImagen3);
                            

                            p4.addPoint(xImagen4, yImagen4+L1y1-yImagenPanel);
                            p4.addPoint(xImagen4, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+L2y2-yImagenPanel);
                            p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                            
                        }
                        
                    }else if(L1x1==xImagenPanel){
                        
                        p1.addPoint(xImagen+L2x1-xImagenPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(xImagen, yImagen);
                        p1.addPoint(xImagen, yImagen+L1y1-yImagenPanel);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);

                        if(L2x2==xImagenPanel+anchoPanel){
                            //check
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+L1y2-yImagenPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2);
                            p2.addPoint(xImagen2, yImagen2);
                            p2.addPoint(xImagen2, yImagen2+altoPanel);
                            p2.addPoint(xImagen2+L2x1-xImagenPanel, yImagen2+altoPanel);
                            p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            
                            //check
                            p3.addPoint(xImagen3, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                            p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3);
                            p3.addPoint(xImagen3, yImagen3);
                            
                            //check
                            p4.addPoint(xImagen4, yImagen4+L1y1-yImagenPanel);
                            p4.addPoint(xImagen4, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+L2y2-yImagenPanel);
                            p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                            
                            System.out.println("xccxxccx 222");
                            
                        }else{

                            p2.addPoint(xImagen2+anchoPanel, yImagen2+L1y2-yImagenPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2);
                            p2.addPoint(xImagen2, yImagen2);
                            p2.addPoint(xImagen2, yImagen2+altoPanel);
                            p2.addPoint(xImagen2+L2x1-xImagenPanel, yImagen2+altoPanel);
                            p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            

                            p3.addPoint(xImagen3, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                            p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                            p3.addPoint(xImagen3+L2x2-xImagenPanel, yImagen3+L2y2-yImagenPanel);
                            p3.addPoint(xImagen3, yImagen3);
                            

                            p4.addPoint(xImagen4, yImagen4+L1y1-yImagenPanel);
                            p4.addPoint(xImagen4, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+L2y2-yImagenPanel);
                            p4.addPoint(xImagen4+L2x2-xImagenPanel, yImagen4);
                            p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                            
                            
                        }
                        

                    }else if(L2x1==xImagenPanel){
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen, yImagen+altoPanel);
                        p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                        
                        
                        if(L2x2==xImagenPanel+anchoPanel){
                            
                            if(L1x2==xImagenPanel+anchoPanel){

                                p2.addPoint(xImagen2+anchoPanel, yImagen2+L1y2-yImagenPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            

                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3);
                                p3.addPoint(xImagen3, yImagen3);

                            }else{

                                p2.addPoint(xImagen2+anchoPanel, yImagen2+anchoPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(xImagen2+L1x2-xImagenPanel, yImagen2+altoPanel);

                            

                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+L1x2-xImagenPanel, yImagen3+altoPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3);
                                p3.addPoint(xImagen3, yImagen3);

                            }
                            
                            

                            p4.addPoint(xImagen4, yImagen4+L1y1-yImagenPanel);
                            p4.addPoint(xImagen4, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+L2y2-yImagenPanel);
                            p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                            p4.addPoint(xImagen4+L1x1-xImagenPanel, yImagen4);
                            
                        }else{
                            
                            if(L1x2==xImagenPanel+anchoPanel){

                                p2.addPoint(xImagen2+anchoPanel, yImagen2+L1y2-yImagenPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            

                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+L2x2-xImagenPanel, yImagen3+L2y2-yImagenPanel);
                                p3.addPoint(xImagen3, yImagen3);

                            }else{

                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(xImagen2+L1x2-xImagenPanel, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);

                                
                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+L1x2-xImagenPanel, yImagen3+L1y2-yImagenPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+L2x2-xImagenPanel, yImagen3+L2y2-yImagenPanel);
                                p3.addPoint(xImagen3, yImagen3);

                            }
                            
                            
                            p4.addPoint(xImagen4, yImagen4+L1y1-yImagenPanel);
                            p4.addPoint(xImagen4, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+L2y2-yImagenPanel);
                            p4.addPoint(xImagen4+L2x2-xImagenPanel, yImagen4);
                            p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                            p4.addPoint(xImagen4+L1x1-xImagenPanel, yImagen4);
                            
                        }
                        

                    }else{
                        System.out.println("otro caso 2");
                    }
                    
                }else if(L1x2==xImagenPanel+anchoPanel &&  L2x2==xImagenPanel+anchoPanel){

                    p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen+anchoPanel);
                    p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                    p1.addPoint(xImagen+anchoPanel, yImagen);
                    p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen);
                    p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                    

                    p2.addPoint(xImagen2+anchoPanel, yImagen2);
                    p2.addPoint(xImagen2, yImagen2);
                    p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                    p2.addPoint(xImagen2+L2x1-xImagenPanel, yImagen2+altoPanel);
                    p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                    p2.addPoint(xImagen2+anchoPanel, yImagen2+L1y2-yImagenPanel);


                    p3.addPoint(xImagen3, yImagen3+altoPanel);
                    p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                    p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                    p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                    p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                    p3.addPoint(xImagen3+anchoPanel, yImagen3);
                    p3.addPoint(xImagen3, yImagen3);
                    

                    p4.addPoint(xImagen4, yImagen4);
                    p4.addPoint(xImagen4, yImagen4+altoPanel);
                    p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                    p4.addPoint(xImagen4+anchoPanel, yImagen4+L2y2-yImagenPanel);
                    p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                    p4.addPoint(xImagen4+L1x1-xImagenPanel, yImagen4);
                    
                   
                }else if(L2x2==xImagenPanel+anchoPanel){
                    p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen+altoPanel);
                    p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                    p1.addPoint(xImagen+anchoPanel, yImagen);
                    p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen);
                    p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                    

                    p2.addPoint(xImagen2+anchoPanel, yImagen2);
                    p2.addPoint(xImagen2, yImagen2);
                    p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                    p2.addPoint(xImagen2+L2x1-xImagenPanel, yImagen2+altoPanel);
                    p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                    p2.addPoint(xImagen2+L1x2-xImagenPanel, yImagen2+altoPanel);
                    p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);


                    p3.addPoint(xImagen3, yImagen3+altoPanel);
                    p3.addPoint(xImagen3+L1x2-xImagenPanel, yImagen3+altoPanel);
                    p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                    p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                    p3.addPoint(xImagen3+anchoPanel, yImagen3);
                    p3.addPoint(xImagen3, yImagen3);
                    

                    p4.addPoint(xImagen4, yImagen4);
                    p4.addPoint(xImagen4, yImagen4+altoPanel);
                    p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                    p4.addPoint(xImagen4+anchoPanel, yImagen4+L2y2-yImagenPanel);
                    p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                    p4.addPoint(xImagen4+L1x1-xImagenPanel, yImagen4);
                    

                }else if(L1x2==xImagenPanel+anchoPanel){
                    p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen+altoPanel);
                    p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                    p1.addPoint(xImagen+anchoPanel, yImagen);
                    p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen);
                    p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                    

                    p2.addPoint(xImagen2+anchoPanel, yImagen2);
                    p2.addPoint(xImagen2, yImagen2);
                    p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                    p2.addPoint(xImagen2+L2x1-xImagenPanel, yImagen2+altoPanel);
                    p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                    p2.addPoint(xImagen2+L1x2-xImagenPanel, yImagen2+L1y2-yImagenPanel);


                    p3.addPoint(xImagen3, yImagen3+altoPanel);
                    p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                    p3.addPoint(xImagen3+L1x2-xImagenPanel, yImagen3+L1y2-yImagenPanel);
                    p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                    p3.addPoint(xImagen3+L2x2-xImagenPanel, yImagen3);
                    p3.addPoint(xImagen3, yImagen3);

                    

                    p4.addPoint(xImagen4, yImagen4);
                    p4.addPoint(xImagen4, yImagen4+altoPanel);
                    p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                    p4.addPoint(xImagen4+anchoPanel, yImagen4);
                    p4.addPoint(xImagen4+L2x2-xImagenPanel, yImagen4);
                    p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                    p4.addPoint(xImagen4+L1x1-xImagenPanel, yImagen4);
                    
                    
                }else{
                    p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen+altoPanel);
                    p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                    p1.addPoint(xImagen+anchoPanel, yImagen);
                    p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen);
                    p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                    

                    p2.addPoint(xImagen2+anchoPanel, yImagen2);
                    p2.addPoint(xImagen2, yImagen2);
                    p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                    p2.addPoint(xImagen2+L2x1-xImagenPanel, yImagen2+altoPanel);
                    p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                    p2.addPoint(xImagen2+L1x2-xImagenPanel, yImagen2+L1y2-yImagenPanel);
                    p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);


                    p3.addPoint(xImagen3, yImagen3+altoPanel);
                    p3.addPoint(xImagen3+L1x2-xImagenPanel, yImagen3+altoPanel);
                    p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                    p3.addPoint(xImagen3+L2x2-xImagenPanel, yImagen3);
                    p3.addPoint(xImagen3, yImagen3);

                    

                    p4.addPoint(xImagen4, yImagen4);
                    p4.addPoint(xImagen4, yImagen4+altoPanel);
                    p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                    p4.addPoint(xImagen4+anchoPanel, yImagen4);
                    p4.addPoint(xImagen4+L2x2-xImagenPanel, yImagen4);
                    p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                    p4.addPoint(xImagen4+L1x1-xImagenPanel, yImagen4);
                    
                    
                }
            }
            
            
            
            
            
        //Caso principal 3
        }else if(L2y1>intersecciony && L1y1>intersecciony && pieza2estado!=0){
            if(L2y1>=intersecciony+yImagen-yImagenPanel){
                
                if(L1x1==xImagenPanel || L2x1==xImagenPanel){

                    if(L1x1==xImagenPanel && L2x1==xImagenPanel){
                        
                        if(L1y2==yImagenPanel && L2y2==yImagenPanel){
                            if(L1x2>L2x2){

                                p1.addPoint(xImagen, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen);
                                p1.addPoint(xImagen+L2x2-xImagenPanel, yImagen);
                                p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                                p1.addPoint(xImagen, yImagen+L1y1-yImagenPanel);
                                

                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                                p2.addPoint(xImagen2, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);


                                p3.addPoint(xImagen3, yImagen3);
                                p3.addPoint(xImagen3, yImagen3+L2y1-yImagenPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+L1x2-xImagenPanel, yImagen3);
                                
                                
                    

                                p4.addPoint(xImagen4, yImagen4);
                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4);
                                p4.addPoint(xImagen4+L1x2-xImagenPanel, yImagen4);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                p4.addPoint(xImagen4+L2x2-xImagenPanel, yImagen4);
                    
                                
                            }else{

                                p1.addPoint(xImagen, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen);
                                p1.addPoint(xImagen+L1x2-xImagenPanel, yImagen);
                                p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                                p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                                
                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                                p2.addPoint(xImagen2, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                                
                                p3.addPoint(xImagen3, yImagen3);
                                p3.addPoint(xImagen3, yImagen3+L1y1-yImagenPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+L2x2-xImagenPanel, yImagen3);
                                

                                p4.addPoint(xImagen4, yImagen4);
                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4);
                                p4.addPoint(xImagen4+L2x2-xImagenPanel, yImagen4);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                p4.addPoint(xImagen4+L1x2-xImagenPanel, yImagen4);
                                

                            }
                        }else if(L1y2==yImagenPanel){

                            p1.addPoint(xImagen, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen);
                            p1.addPoint(xImagen+L1x2-xImagenPanel, yImagen);
                            p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                            p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                                

                            p2.addPoint(xImagen2+anchoPanel, yImagen2);
                            p2.addPoint(xImagen2, yImagen2);
                            p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                            p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                            p2.addPoint(xImagen2, yImagen2+altoPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);


                            p3.addPoint(xImagen3, yImagen3);
                            p3.addPoint(xImagen3, yImagen3+L1y1-yImagenPanel);
                            p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3);
                              
                                

                            p4.addPoint(xImagen4, yImagen4);
                            p4.addPoint(xImagen4, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+L2y2-yImagenPanel);
                            p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                            p4.addPoint(xImagen4+L1x2-xImagenPanel, yImagen4);
                                               

                        }else if(L2y2==yImagenPanel){

                            p1.addPoint(xImagen, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen);
                            p1.addPoint(xImagen+L2x2-xImagenPanel, yImagen);
                            p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                            p1.addPoint(xImagen, yImagen+L1y1-yImagenPanel);
                                

                            p2.addPoint(xImagen2+anchoPanel, yImagen2);
                            p2.addPoint(xImagen2, yImagen2);
                            p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                            p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                            p2.addPoint(xImagen2, yImagen2+altoPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);


                            p3.addPoint(xImagen3, yImagen3);
                            p3.addPoint(xImagen3, yImagen3+L2y1-yImagenPanel);
                            p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3);
                              

                            p4.addPoint(xImagen4, yImagen4);
                            p4.addPoint(xImagen4, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+L1y2-yImagenPanel);
                            p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                            p4.addPoint(xImagen4+L2x2-xImagenPanel, yImagen4);
                             
                        }else{
                            
                            if(L1y2<L2y2){
                                p1.addPoint(xImagen, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen+L1y2-yImagenPanel);
                                p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                                p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                                

                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                                p2.addPoint(xImagen2, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);


                                p3.addPoint(xImagen3, yImagen3);
                                p3.addPoint(xImagen3, yImagen3+L1y1-yImagenPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3);
                              

                                p4.addPoint(xImagen4, yImagen4);
                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+L1y2-yImagenPanel);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+L2y2-yImagenPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4);

                            }else{
                                p1.addPoint(xImagen, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen+L2y2-yImagenPanel);
                                p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                                p1.addPoint(xImagen, yImagen+L1y1-yImagenPanel);
                                

                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                                p2.addPoint(xImagen2, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);

                                
                                p3.addPoint(xImagen3, yImagen3);
                                p3.addPoint(xImagen3, yImagen3+L2y1-yImagenPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3);
                              

                                p4.addPoint(xImagen4, yImagen4);
                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+L2y2-yImagenPanel);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+L1y2-yImagenPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4);
                                
                            }
                            
                        }
                        
                    }else if(L1x1==xImagenPanel){
                        if(L1y2==yImagenPanel){
                            p1.addPoint(xImagen, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen);
                            p1.addPoint(L2x2+xImagen-xImagenPanel, yImagen);
                            p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                            p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+L1y1-yImagenPanel);
                            

                            p2.addPoint(xImagen2+anchoPanel, yImagen2);
                            p2.addPoint(xImagen2, yImagen2);
                            p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                            p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            p2.addPoint(xImagen2+L2x1-xImagenPanel, yImagen2+altoPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);


                            p3.addPoint(xImagen3, yImagen3);
                            p3.addPoint(xImagen3, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+L2x1-xImagenPanel, yImagen3+altoPanel);
                            p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                            p3.addPoint(xImagen3+L1x2-xImagenPanel, yImagen3);
                       

                            p4.addPoint(xImagen4, yImagen4);
                            p4.addPoint(xImagen4, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4);
                            p4.addPoint(xImagen4+L1x2-xImagenPanel, yImagen4);
                            p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                            p4.addPoint(xImagen4+L2x2-xImagenPanel, yImagen4);
                                

                        }else{
                            if(L2y2==yImagenPanel){
                                p1.addPoint(xImagen, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen);
                                p1.addPoint(L2x2+xImagen-xImagenPanel, yImagen);
                                p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                                p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+L1y1-yImagenPanel);
                                
                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(xImagen2+L2x1-xImagenPanel, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                                

                                p3.addPoint(xImagen3, yImagen3);
                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+L2x1-xImagenPanel, yImagen3+altoPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3);
                       

                                p4.addPoint(xImagen4, yImagen4);
                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+L1y2-yImagenPanel);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                p4.addPoint(xImagen4+L2x2-xImagenPanel, yImagen4);

                            }else{
                                
                                p1.addPoint(xImagen, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen+L2y2-yImagenPanel);
                                p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                                p1.addPoint(xImagen, yImagen+L1y1-yImagenPanel);
                                

                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(xImagen2+L2x1-xImagenPanel, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                                

                                p3.addPoint(xImagen3, yImagen3);
                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+L2x1-xImagenPanel, yImagen3+altoPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3);
                       

                                p4.addPoint(xImagen4, yImagen4);
                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+L1y2-yImagenPanel);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+L2y2-yImagenPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4);

                                
                            }
                        }
                        

                    }else if(L2x1==xImagenPanel){
                        if(L2y2==yImagenPanel){
                            p1.addPoint(xImagen, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen);
                            p1.addPoint(L1x2+xImagen-xImagenPanel, yImagen);
                            p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                            p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen+L2y1-yImagenPanel);
                            

                            p2.addPoint(xImagen2+anchoPanel, yImagen2);
                            p2.addPoint(xImagen2, yImagen2);
                            p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                            p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            p2.addPoint(xImagen2+L1x1-xImagenPanel, yImagen2+altoPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);


                            p3.addPoint(xImagen3, yImagen3);
                            p3.addPoint(xImagen3, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+L1x1-xImagenPanel, yImagen3+altoPanel);
                            p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                            p3.addPoint(xImagen3+L2x2-xImagenPanel, yImagen3);
                       

                            p4.addPoint(xImagen4, yImagen4);
                            p4.addPoint(xImagen4, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4);
                            p4.addPoint(xImagen4+L1x2-xImagenPanel, yImagen4);
                            p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                            p4.addPoint(xImagen4+L2x2-xImagenPanel, yImagen4);
                                

                        }else{
                            if(L1y2==yImagenPanel){
                                p1.addPoint(xImagen, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen);
                                p1.addPoint(L1x2+xImagen-xImagenPanel, yImagen);
                                p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                                p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                                
                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(xImagen2+L1x1-xImagenPanel, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                                

                                p3.addPoint(xImagen3, yImagen3);
                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+L1x1-xImagenPanel, yImagen3+altoPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3);
                       

                                p4.addPoint(xImagen4, yImagen4);
                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+L2y2-yImagenPanel);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                p4.addPoint(xImagen4+L1x2-xImagenPanel, yImagen4);

                            }else{
                                
                                p1.addPoint(xImagen, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen+L1y2-yImagenPanel);
                                p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                                p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                                
                                
                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(xImagen2+L1x1-xImagenPanel, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                                

                                p3.addPoint(xImagen3, yImagen3);
                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+L1x1-xImagenPanel, yImagen3+altoPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3);
                                                      
                                
                                p4.addPoint(xImagen4, yImagen4);
                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+L2y2-yImagenPanel);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+L1y2-yImagenPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4);

                            }
                        }
                        
                    }
                    
                    
                }else if(L1x2==xImagenPanel+anchoPanel || L2x2==xImagenPanel+anchoPanel ){

                    System.out.println("333uiuih");
                    if(L1x2==xImagenPanel+anchoPanel && L2x2==xImagenPanel+anchoPanel){
                        if(L1x1<L2x1){

                            p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                            p1.addPoint(L2x2+xImagen-xImagenPanel, yImagen);
                            p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                            
                            
                            p2.addPoint(xImagen2+anchoPanel, yImagen2);
                            p2.addPoint(xImagen2, yImagen2);
                            p2.addPoint(xImagen2, yImagen2+altoPanel);
                            p2.addPoint(xImagen2+L1x1-xImagenPanel, yImagen2+altoPanel);
                            p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            p2.addPoint(xImagen2+L2x1-xImagenPanel, yImagen2+altoPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                                

                            p3.addPoint(xImagen3, yImagen3);
                            p3.addPoint(xImagen3, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+L2x1-xImagenPanel, yImagen3+altoPanel);
                            p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3);
                       

                            p4.addPoint(xImagen4, yImagen4);
                            p4.addPoint(xImagen4, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+L1y2-yImagenPanel);
                            p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+L2y2-yImagenPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4);
                            
                            

                        }else if(L1x1>L2x1){
                            p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                            p1.addPoint(L1x2+xImagen-xImagenPanel, yImagen);
                            p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                            

                            p2.addPoint(xImagen2+anchoPanel, yImagen2);
                            p2.addPoint(xImagen2, yImagen2);
                            p2.addPoint(xImagen2, yImagen2+altoPanel);
                            p2.addPoint(xImagen2+L2x1-xImagenPanel, yImagen2+altoPanel);
                            p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            p2.addPoint(xImagen2+L1x1-xImagenPanel, yImagen2+altoPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                                

                            p3.addPoint(xImagen3, yImagen3);
                            p3.addPoint(xImagen3, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+L1x1-xImagenPanel, yImagen3+altoPanel);
                            p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3);
                       

                            p4.addPoint(xImagen4, yImagen4);
                            p4.addPoint(xImagen4, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+L2y2-yImagenPanel);
                            p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+L1y2-yImagenPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4);

                        }else{

                        }
                    }else if(L1x2==xImagenPanel+anchoPanel){
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(L2x2+xImagen-xImagenPanel, yImagen);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                           

                        p2.addPoint(xImagen2+anchoPanel, yImagen2);
                        p2.addPoint(xImagen2, yImagen2);
                        p2.addPoint(xImagen2, yImagen2+altoPanel);
                        p2.addPoint(xImagen2+L1x1-xImagenPanel, yImagen2+altoPanel);
                        p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                        p2.addPoint(xImagen2+L2x1-xImagenPanel, yImagen2+altoPanel);
                        p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                        

                        p3.addPoint(xImagen3, yImagen3);
                        p3.addPoint(xImagen3, yImagen3+altoPanel);
                        p3.addPoint(xImagen3+L2x1-xImagenPanel, yImagen3+altoPanel);
                        p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                        p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                        p3.addPoint(xImagen3+anchoPanel, yImagen3);
                       

                        p4.addPoint(xImagen4, yImagen4);
                        p4.addPoint(xImagen4, yImagen4+altoPanel);
                        p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                        p4.addPoint(xImagen4+anchoPanel, yImagen4+L1y2-yImagenPanel);
                        p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                        p4.addPoint(xImagen4+L2x2-xImagenPanel, yImagen4);
                        

                    }else if(L2x2==xImagenPanel+anchoPanel){
                        p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(L1x2+xImagen-xImagenPanel, yImagen);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                           

                        p2.addPoint(xImagen2+anchoPanel, yImagen2);
                        p2.addPoint(xImagen2, yImagen2);
                        p2.addPoint(xImagen2, yImagen2+altoPanel);
                        p2.addPoint(xImagen2+L2x1-xImagenPanel, yImagen2+altoPanel);
                        p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                        p2.addPoint(xImagen2+L1x1-xImagenPanel, yImagen2+altoPanel);
                        p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                        

                        p3.addPoint(xImagen3, yImagen3);
                        p3.addPoint(xImagen3, yImagen3+altoPanel);
                        p3.addPoint(xImagen3+L1x1-xImagenPanel, yImagen3+altoPanel);
                        p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                        p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                        p3.addPoint(xImagen3+anchoPanel, yImagen3);
                       

                        p4.addPoint(xImagen4, yImagen4);
                        p4.addPoint(xImagen4, yImagen4+altoPanel);
                        p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                        p4.addPoint(xImagen4+anchoPanel, yImagen4+L2y2-yImagenPanel);
                        p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                        p4.addPoint(xImagen4+L1x2-xImagenPanel, yImagen4);
                        

                    }

                       
                }else{
                    
                    if(L1x1<L2x1){
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+altoPanel);
                       p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                           

                        p2.addPoint(xImagen2+anchoPanel, yImagen2);
                        p2.addPoint(xImagen2, yImagen2);
                        p2.addPoint(xImagen2, yImagen2+altoPanel);
                        p2.addPoint(xImagen2+L1x1-xImagenPanel, yImagen2+altoPanel);
                        p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                        p2.addPoint(xImagen2+L2x1-xImagenPanel, yImagen2+altoPanel);
                        p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                                

                        p3.addPoint(xImagen3, yImagen3);
                        p3.addPoint(xImagen3, yImagen3+altoPanel);
                        p3.addPoint(xImagen3+L2x1-xImagenPanel, yImagen3+altoPanel);
                        p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                        p3.addPoint(xImagen3+L1x1-xImagenPanel, yImagen3);
                  

                        p4.addPoint(xImagen4, yImagen4);
                        p4.addPoint(xImagen4, yImagen4+altoPanel);
                        p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                        p4.addPoint(xImagen4+anchoPanel, yImagen4);
                        p4.addPoint(xImagen4+L1x2-xImagenPanel, yImagen4);
                        p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                        p4.addPoint(xImagen4+L2x2-xImagenPanel, yImagen4);

                    }else{
                        p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                           

                        p2.addPoint(xImagen2+anchoPanel, yImagen2);
                        p2.addPoint(xImagen2, yImagen2);
                        p2.addPoint(xImagen2, yImagen2+altoPanel);
                        p2.addPoint(xImagen2+L2x1-xImagenPanel, yImagen2+altoPanel);
                        p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                        p2.addPoint(xImagen2+L1x1-xImagenPanel, yImagen2+altoPanel);
                        p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                        

                        p3.addPoint(xImagen3, yImagen3);
                        p3.addPoint(xImagen3, yImagen3+altoPanel);
                        p3.addPoint(xImagen3+L1x1-xImagenPanel, yImagen3+altoPanel);
                        p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                        p3.addPoint(xImagen3+L2x1-xImagenPanel, yImagen3);
                        

                        p4.addPoint(xImagen4, yImagen4);
                        p4.addPoint(xImagen4, yImagen4+altoPanel);
                        p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                        p4.addPoint(xImagen4+anchoPanel, yImagen4);
                        p4.addPoint(xImagen4+L2x2-xImagenPanel, yImagen4);
                        p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                        p4.addPoint(xImagen4+L1x2-xImagenPanel, yImagen4);

                    }
                    
                            
                }
            }
            

            
            
        //Casos principales 4    
        }else if(L2y1<intersecciony && L1y1<intersecciony && pieza2estado!=0){
            if(L2y1>=intersecciony+yImagen-yImagenPanel){
                if(L1x1==xImagenPanel || L2x1==xImagenPanel){

                    if(L1x1==xImagenPanel && L2x1==xImagenPanel){
                        
                        if(L1y2==yImagenPanel+altoPanel && L2y2==yImagenPanel+altoPanel){
                            if(L1x2<L2x2){
                                p1.addPoint(xImagen, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen);
                                p1.addPoint(xImagen, yImagen);
                                p1.addPoint(xImagen, yImagen+L1y1-yImagenPanel);
                                p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                                p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+L2y1-yImagenPanel);
                                

                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(xImagen2+L1x2-xImagenPanel, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                                

                                p3.addPoint(xImagen3, yImagen3);
                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+L1x2-xImagenPanel, yImagen3+altoPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+L2x2-xImagenPanel, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3);
                        

                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+L2x2-xImagenPanel, yImagen4+altoPanel);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                p4.addPoint(xImagen4, yImagen4+L1y1-yImagenPanel);
                            
                                
                                                            
                            }else{
                                p1.addPoint(xImagen, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen);
                                p1.addPoint(xImagen, yImagen);
                                p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                                p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                                p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+L1y1-yImagenPanel);
                                

                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(xImagen2+L2x2-xImagenPanel, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                                

                                p3.addPoint(xImagen3, yImagen3);
                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+L2x2-xImagenPanel, yImagen3+altoPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+L1x2-xImagenPanel, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3);
                        

                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+L1x2-xImagenPanel, yImagen4+altoPanel);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                p4.addPoint(xImagen4, yImagen4+L2y1-yImagenPanel);
                            

                            }
                            
                        }else if(L1y2==yImagenPanel+altoPanel){
                            p1.addPoint(xImagen, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen);
                            p1.addPoint(xImagen, yImagen);
                            p1.addPoint(xImagen, yImagen+L1y1-yImagenPanel);
                            p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                            p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                                

                            p2.addPoint(xImagen2+anchoPanel, yImagen2);
                            p2.addPoint(xImagen2, yImagen2);
                            p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                            p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            p2.addPoint(xImagen2+L1x2-xImagenPanel, yImagen2+altoPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                              

                            p3.addPoint(xImagen3, yImagen3);
                            p3.addPoint(xImagen3, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+L1x2-xImagenPanel, yImagen3+altoPanel);
                            p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3);
                        

                            p4.addPoint(xImagen4, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+L2y2-yImagenPanel);
                            p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                            p4.addPoint(xImagen4, yImagen4+L1y1-yImagenPanel);
                         
                        }else if(L2y2==yImagenPanel+altoPanel){
                            p1.addPoint(xImagen, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen);
                            p1.addPoint(xImagen, yImagen);
                            p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                            p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                            p1.addPoint(xImagen, yImagen+L1y1-yImagenPanel);
                                

                            p2.addPoint(xImagen2+anchoPanel, yImagen2);
                            p2.addPoint(xImagen2, yImagen2);
                            p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                            p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            p2.addPoint(xImagen2+L2x2-xImagenPanel, yImagen2+altoPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                              

                            p3.addPoint(xImagen3, yImagen3);
                            p3.addPoint(xImagen3, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+L2x2-xImagenPanel, yImagen3+altoPanel);
                            p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3);
                        

                            p4.addPoint(xImagen4, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+L1y2-yImagenPanel);
                            p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                            p4.addPoint(xImagen4, yImagen4+L2y1-yImagenPanel);
                         
                        }else{
                            if(L1y1>L2y1){
                                p1.addPoint(xImagen, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen);
                                p1.addPoint(xImagen, yImagen);
                                p1.addPoint(xImagen, yImagen+L1y1-yImagenPanel);
                                p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                                p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                                

                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+L2y2-yImagenPanel);

                                

                                p3.addPoint(xImagen3, yImagen3);
                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3);
                        

                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+L1y2-yImagenPanel);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                p4.addPoint(xImagen4, yImagen4+L2y1-yImagenPanel);

                            }else{
                                p1.addPoint(xImagen, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen);
                                p1.addPoint(xImagen, yImagen);
                                p1.addPoint(xImagen, yImagen+L1y1-yImagenPanel);
                                p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                                p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                                

                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+L2y2-yImagenPanel);


                                p3.addPoint(xImagen3, yImagen3);
                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3);
                        

                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+L2y2-yImagenPanel);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                p4.addPoint(xImagen4, yImagen4+L1y1-yImagenPanel);

                            }
                            
                        }
                        
                    }else if(L1x1==xImagenPanel){
                        if(L1x2==xImagenPanel+anchoPanel){
                            if(L2x2==xImagenPanel+anchoPanel){
                                p1.addPoint(xImagen, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen);
                                p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen);
                                p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                                p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+L1y1-yImagenPanel);
                                

                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+L2y2-yImagenPanel);


                                p3.addPoint(xImagen3, yImagen3);
                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3);
                        

                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+L1y2-yImagenPanel);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                p4.addPoint(xImagen4+L2x1-xImagenPanel, yImagen4);
                                p4.addPoint(xImagen4, yImagen4);
                                

                            }else{
                                p1.addPoint(xImagen, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen);
                                p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen);
                                p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                                p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+L1y1-yImagenPanel);
                                

                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(xImagen2+L2x2-xImagenPanel, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);


                                p3.addPoint(xImagen3, yImagen3);
                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+L2x2-xImagenPanel, yImagen3+altoPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3);
                        

                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+L1y2-yImagenPanel);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                p4.addPoint(xImagen4+L2x1-xImagenPanel, yImagen4);
                                p4.addPoint(xImagen4, yImagen4);

                            }
                        }else{
                            p1.addPoint(xImagen, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen);
                            p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen);
                            p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                            p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+L1y1-yImagenPanel);
                                

                            p2.addPoint(xImagen2+anchoPanel, yImagen2);
                            p2.addPoint(xImagen2, yImagen2);
                            p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                            p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            p2.addPoint(xImagen2+L2x2-xImagenPanel, yImagen2+altoPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);


                            p3.addPoint(xImagen3, yImagen3);
                            p3.addPoint(xImagen3, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+L2x2-xImagenPanel, yImagen3+altoPanel);
                            p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                            p3.addPoint(xImagen3+L1x2-xImagenPanel, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3);
                        

                            p4.addPoint(xImagen4, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+L1x2-xImagenPanel, yImagen4+altoPanel);
                            p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                            p4.addPoint(xImagen4+L2x1-xImagenPanel, yImagen4);
                            p4.addPoint(xImagen4, yImagen4);

                        }
                        
                    }else if(L2x1==xImagenPanel){
                        if(L2x2==xImagenPanel+anchoPanel){
                            if(L1x2==xImagenPanel+anchoPanel){
                                p1.addPoint(xImagen, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen);
                                p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen);
                                p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                                p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen+L1y1-yImagenPanel);
                                

                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+L1y2-yImagenPanel);


                                p3.addPoint(xImagen3, yImagen3);
                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3);
                        

                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+L2y2-yImagenPanel);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                p4.addPoint(xImagen4+L1x1-xImagenPanel, yImagen4);
                                p4.addPoint(xImagen4, yImagen4);
                                

                            }else{
                                p1.addPoint(xImagen, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen);
                                p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen);
                                p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                                p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen+L2y1-yImagenPanel);
                                

                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(xImagen2+L1x2-xImagenPanel, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);


                                p3.addPoint(xImagen3, yImagen3);
                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+L1x2-xImagenPanel, yImagen3+altoPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3);
                        

                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+L2y2-yImagenPanel);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                p4.addPoint(xImagen4+L1x1-xImagenPanel, yImagen4);
                                p4.addPoint(xImagen4, yImagen4);

                            }
                        }else{
                            p1.addPoint(xImagen, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen);
                            p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen);
                            p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                            p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen+L2y1-yImagenPanel);
                                

                            p2.addPoint(xImagen2+anchoPanel, yImagen2);
                            p2.addPoint(xImagen2, yImagen2);
                            p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                            p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            p2.addPoint(xImagen2+L1x2-xImagenPanel, yImagen2+altoPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);


                            p3.addPoint(xImagen3, yImagen3);
                            p3.addPoint(xImagen3, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+L1x2-xImagenPanel, yImagen3+altoPanel);
                            p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                            p3.addPoint(xImagen3+L2x2-xImagenPanel, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3);
                        

                            p4.addPoint(xImagen4, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+L2x2-xImagenPanel, yImagen4+altoPanel);
                            p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                            p4.addPoint(xImagen4+L1x1-xImagenPanel, yImagen4);
                            p4.addPoint(xImagen4, yImagen4);

                        }
                    }
                    
                }else{
                    //
                    if(L1y2==yImagenPanel+altoPanel || L2y2==yImagenPanel+altoPanel){
                        if(L1y2==yImagenPanel+altoPanel && L2y2==yImagenPanel+altoPanel){
                            if(L1x1<L2x1){
                                
                                p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen);
                                p1.addPoint(xImagen+anchoPanel, yImagen);
                                p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                                p1.addPoint(L2x2+xImagen-xImagenPanel, yImagen+altoPanel);
                                p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                                

                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+L2x2-xImagenPanel, yImagen2+altoPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(xImagen2+L1x2-xImagenPanel, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);


                                p3.addPoint(xImagen3, yImagen3);
                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+L1x2-xImagenPanel, yImagen3+altoPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+L2x1-xImagenPanel, yImagen3);
                        

                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4);
                                p4.addPoint(xImagen4+L2x1-xImagenPanel, yImagen4);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                p4.addPoint(xImagen4+L1x1-xImagenPanel, yImagen4);
                                p4.addPoint(xImagen4, yImagen4);

                            }else{

                                p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen);
                                p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                                p1.addPoint(L1x2+xImagen-xImagenPanel, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                                p1.addPoint(xImagen+anchoPanel, yImagen);
                                

                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+L1x2-xImagenPanel, yImagen2+altoPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(xImagen2+L2x2-xImagenPanel, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);


                                p3.addPoint(xImagen3, yImagen3);
                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+L2x2-xImagenPanel, yImagen3+altoPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+L1x1-xImagenPanel, yImagen3);
                        

                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4);
                                p4.addPoint(xImagen4+L1x1-xImagenPanel, yImagen4);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                p4.addPoint(xImagen4+L2x1-xImagenPanel, yImagen4);
                                p4.addPoint(xImagen4, yImagen4);
                                

                            }
                        }else if(L2y2==yImagenPanel+altoPanel){

                            p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen);
                            p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                            p1.addPoint(L2x2+xImagen-xImagenPanel, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen);
                                
                                

                            p2.addPoint(xImagen2+anchoPanel, yImagen2);
                            p2.addPoint(xImagen2, yImagen2);
                            p2.addPoint(xImagen2, yImagen2+altoPanel);
                            p2.addPoint(xImagen2+L2x2-xImagenPanel, yImagen2+altoPanel);
                            p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+L1y2-yImagenPanel);


                            p3.addPoint(xImagen3, yImagen3);
                            p3.addPoint(xImagen3, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                            p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                            p3.addPoint(xImagen3+L2x1-xImagenPanel, yImagen3);
                        

                            p4.addPoint(xImagen4, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4);
                            p4.addPoint(xImagen4+L2x1-xImagenPanel, yImagen4);
                            p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                            p4.addPoint(xImagen4+L1x1-xImagenPanel, yImagen4);
                            p4.addPoint(xImagen4, yImagen4);
                                
                            
                        }else if(L1y2==yImagenPanel+altoPanel){

                            
                            p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen);
                            p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                            p1.addPoint(L1x2+xImagen-xImagenPanel, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                            p1.addPoint(xImagen+anchoPanel, yImagen);
                                
                                
                            p2.addPoint(xImagen2+anchoPanel, yImagen2);
                            p2.addPoint(xImagen2, yImagen2);
                            p2.addPoint(xImagen2, yImagen2+altoPanel);
                            p2.addPoint(xImagen2+L1x2-xImagenPanel, yImagen2+altoPanel);
                            p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+L2y2-yImagenPanel);


                            p3.addPoint(xImagen3, yImagen3);
                            p3.addPoint(xImagen3, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                            p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                            p3.addPoint(xImagen3+L1x1-xImagenPanel, yImagen3);
                            
                            p4.addPoint(xImagen4, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4);
                            p4.addPoint(xImagen4+L1x1-xImagenPanel, yImagen4);
                            p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                            p4.addPoint(xImagen4+L2x1-xImagenPanel, yImagen4);
                            p4.addPoint(xImagen4, yImagen4);
                       
                            
                        }
                    }else{
                        if(L1x1<L2x1){
                            p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen);
                            p1.addPoint(xImagen+anchoPanel, yImagen);
                            p1.addPoint(xImagen+anchoPanel, yImagen+L2y2-yImagenPanel);
                            p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                            

                            p2.addPoint(xImagen2, yImagen2);
                            p2.addPoint(xImagen2, yImagen2+altoPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+L2y2-yImagenPanel);
                            p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+L1y2-yImagenPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2);


                            p3.addPoint(xImagen3, yImagen3);
                            p3.addPoint(xImagen3, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                            p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                            p3.addPoint(xImagen3+L2x1-xImagenPanel, yImagen3);
                            
                            
                            p4.addPoint(xImagen4, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4);
                            p4.addPoint(xImagen4+L2x1-xImagenPanel, yImagen4);
                            p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                            p4.addPoint(xImagen4+L1x1-xImagenPanel, yImagen4);
                            p4.addPoint(xImagen4, yImagen4);
                       
                            
                            

                        }else{
                            p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen);
                            p1.addPoint(xImagen+anchoPanel, yImagen);
                            p1.addPoint(xImagen+anchoPanel, yImagen+L1y2-yImagenPanel);
                            p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                            
                            
                            p2.addPoint(xImagen2, yImagen2);
                            p2.addPoint(xImagen2, yImagen2+altoPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+L1y2-yImagenPanel);
                            p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2+L2y2-yImagenPanel);
                            p2.addPoint(xImagen2+anchoPanel, yImagen2);


                            p3.addPoint(xImagen3, yImagen3);
                            p3.addPoint(xImagen3, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                            p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                            p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                            p3.addPoint(xImagen3+L1x1-xImagenPanel, yImagen3);
                            
                            
                            p4.addPoint(xImagen4, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                            p4.addPoint(xImagen4+anchoPanel, yImagen4);
                            p4.addPoint(xImagen4+L1x1-xImagenPanel, yImagen4);
                            p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                            p4.addPoint(xImagen4+L2x1-xImagenPanel, yImagen4);
                            p4.addPoint(xImagen4, yImagen4);
                       
                            
                        }
                    }
                    
                }
            }
            
        }
        
        if(pieza2estado!=0){
            labelPieza1.setIcon(icon);
            labelPieza2.setIcon(icon);
            labelPieza3.setIcon(icon);
            labelPieza4.setIcon(icon);
        }
        
        repaint();
        
    }
  
    /**
     * Rota la pieza hacia la derecha en 90 grados
     */
    public void RotarDer(){
        
        if(piezaSeleccionada==1){   
        int xd= labelPieza1.getLocation().x;
        int yd =labelPieza1.getLocation().y;
        this.remove(labelPieza1);
        
        switch (Rotacion) {
            case 0:
                labelPieza1=new JLabel();
                labelPieza1.setBounds(xd,yd,altoPanel,anchoPanel);
                labelPieza1.addMouseListener(this);
                labelPieza1.addMouseMotionListener(this);
                labelPieza1.setIcon(icon1);
                this.add(labelPieza1);
                Rotacion=1;
                break;
            case 1:
                if(LastRota==1){
                
                labelPieza1=new JLabel();
                labelPieza1.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza1.addMouseListener(this);
                labelPieza1.addMouseMotionListener(this);
                labelPieza1.setIcon(icon);
                this.add(labelPieza1);
                Rotacion=0;
                break;
                }
                
                else{
                    
                labelPieza1=new JLabel();
                labelPieza1.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza1.addMouseListener(this);
                labelPieza1.addMouseMotionListener(this);
                labelPieza1.setIcon(icon2);
                this.add(labelPieza1);
                Rotacion=2;
                break;
                }
            case 2:
                labelPieza1=new JLabel();
                labelPieza1.setBounds(xd,yd,altoPanel,anchoPanel);
                labelPieza1.addMouseListener(this);
                labelPieza1.addMouseMotionListener(this);
                labelPieza1.setIcon(icon3);
                this.add(labelPieza1);
                Rotacion=3;
                break;
            case 3:
                if(LastRota==0){
                
                labelPieza1=new JLabel();
                labelPieza1.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza1.addMouseListener(this);
                labelPieza1.addMouseMotionListener(this);
                labelPieza1.setIcon(icon);
                this.add(labelPieza1);
                Rotacion=0;
                break;
                }
                
                else{
                    
                labelPieza1=new JLabel();
                labelPieza1.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza1.addMouseListener(this);
                labelPieza1.addMouseMotionListener(this);
                labelPieza1.setIcon(icon2);
                this.add(labelPieza1);
                Rotacion=2;
                break;
                }
            default:
                break;
        }
        }
        
        if(piezaSeleccionada==2){   
        int xd= labelPieza2.getLocation().x;
        int yd =labelPieza2.getLocation().y;
        this.remove(labelPieza2);
        
        switch (Rotacion2) {
            case 0:
                labelPieza2=new JLabel();
                labelPieza2.setBounds(xd,yd,altoPanel,anchoPanel);
                labelPieza2.addMouseListener(this);
                labelPieza2.addMouseMotionListener(this);
                labelPieza2.setIcon(icon1);
                this.add(labelPieza2);
                Rotacion2=1;
                break;
            case 1:
                if(LastRota==1){
                
                labelPieza2=new JLabel();
                labelPieza2.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza2.addMouseListener(this);
                labelPieza2.addMouseMotionListener(this);
                labelPieza2.setIcon(icon);
                this.add(labelPieza2);
                Rotacion2=0;
                break;
                }
                
                else{
                    
                labelPieza2=new JLabel();
                labelPieza2.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza2.addMouseListener(this);
                labelPieza2.addMouseMotionListener(this);
                labelPieza2.setIcon(icon2);
                this.add(labelPieza2);
                Rotacion2=2;
                break;
                }
            case 2:
                labelPieza2=new JLabel();
                labelPieza2.setBounds(xd,yd,altoPanel,anchoPanel);
                labelPieza2.addMouseListener(this);
                labelPieza2.addMouseMotionListener(this);
                labelPieza2.setIcon(icon3);
                this.add(labelPieza2);
                Rotacion2=3;
                break;
            case 3:
                if(LastRota==0){
                
                labelPieza2=new JLabel();
                labelPieza2.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza2.addMouseListener(this);
                labelPieza2.addMouseMotionListener(this);
                labelPieza2.setIcon(icon);
                this.add(labelPieza2);
                Rotacion2=0;
                break;
                }
                
                else{
                    
                labelPieza2=new JLabel();
                labelPieza2.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza2.addMouseListener(this);
                labelPieza2.addMouseMotionListener(this);
                labelPieza2.setIcon(icon2);
                this.add(labelPieza2);
                Rotacion2=2;
                break;
                }
            default:
                break;
        }
        }
        
        if(piezaSeleccionada==3){   
        int xd= labelPieza3.getLocation().x;
        int yd =labelPieza3.getLocation().y;
        this.remove(labelPieza3);
        
        switch (Rotacion3) {
            case 0:
                labelPieza3=new JLabel();
                labelPieza3.setBounds(xd,yd,altoPanel,anchoPanel);
                labelPieza3.addMouseListener(this);
                labelPieza3.addMouseMotionListener(this);
                labelPieza3.setIcon(icon1);
                this.add(labelPieza3);
                Rotacion3=1;
                break;
            case 1:
                if(LastRota==1){
                
                labelPieza3=new JLabel();
                labelPieza3.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza3.addMouseListener(this);
                labelPieza3.addMouseMotionListener(this);
                labelPieza3.setIcon(icon);
                this.add(labelPieza3);
                Rotacion3=0;
                break;
                }
                
                else{
                    
                labelPieza3=new JLabel();
                labelPieza3.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza3.addMouseListener(this);
                labelPieza3.addMouseMotionListener(this);
                labelPieza3.setIcon(icon2);
                this.add(labelPieza3);
                Rotacion3=2;
                break;
                }
            case 2:
                labelPieza3=new JLabel();
                labelPieza3.setBounds(xd,yd,altoPanel,anchoPanel);
                labelPieza3.addMouseListener(this);
                labelPieza3.addMouseMotionListener(this);
                labelPieza3.setIcon(icon3);
                this.add(labelPieza3);
                Rotacion3=3;
                break;
            case 3:
                if(LastRota==0){
                
                labelPieza3=new JLabel();
                labelPieza3.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza3.addMouseListener(this);
                labelPieza3.addMouseMotionListener(this);
                labelPieza3.setIcon(icon);
                this.add(labelPieza3);
                Rotacion3=0;
                break;
                }
                
                else{
                    
                labelPieza3=new JLabel();
                labelPieza3.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza3.addMouseListener(this);
                labelPieza3.addMouseMotionListener(this);
                labelPieza3.setIcon(icon2);
                this.add(labelPieza3);
                Rotacion3=2;
                break;
                }
            default:
                break;
        }
        }
        
        if(piezaSeleccionada==4){   
        int xd= labelPieza4.getLocation().x;
        int yd =labelPieza4.getLocation().y;
        this.remove(labelPieza4);
        
        switch (Rotacion4) {
            case 0:
                labelPieza4=new JLabel();
                labelPieza4.setBounds(xd,yd,altoPanel,anchoPanel);
                labelPieza4.addMouseListener(this);
                labelPieza4.addMouseMotionListener(this);
                labelPieza4.setIcon(icon1);
                this.add(labelPieza4);
                Rotacion4=1;
                break;
            case 1:
                if(LastRota==1){
                
                labelPieza4=new JLabel();
                labelPieza4.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza4.addMouseListener(this);
                labelPieza4.addMouseMotionListener(this);
                labelPieza4.setIcon(icon);
                this.add(labelPieza4);
                Rotacion=0;
                break;
                }
                
                else{
                    
                labelPieza4=new JLabel();
                labelPieza4.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza4.addMouseListener(this);
                labelPieza4.addMouseMotionListener(this);
                labelPieza4.setIcon(icon2);
                this.add(labelPieza4);
                Rotacion4=2;
                break;
                }
            case 2:
                labelPieza4=new JLabel();
                labelPieza4.setBounds(xd,yd,altoPanel,anchoPanel);
                labelPieza4.addMouseListener(this);
                labelPieza4.addMouseMotionListener(this);
                labelPieza4.setIcon(icon3);
                this.add(labelPieza4);
                Rotacion4=3;
                break;
            case 3:
                if(LastRota==0){
                
                labelPieza4=new JLabel();
                labelPieza4.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza4.addMouseListener(this);
                labelPieza4.addMouseMotionListener(this);
                labelPieza4.setIcon(icon);
                this.add(labelPieza4);
                Rotacion4=0;
                break;
                }
                
                else{
                    
                labelPieza4=new JLabel();
                labelPieza4.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza4.addMouseListener(this);
                labelPieza4.addMouseMotionListener(this);
                labelPieza4.setIcon(icon2);
                this.add(labelPieza4);
                Rotacion4=2;
                break;
                }
            default:
                break;
        }
        }
        
        LastRota=0;
        repaint();
    }
    
    /**
     * Rota la pieza a la izquierda en 90 grados
     */
    public void RotarIzq(){
            
        if(piezaSeleccionada==1){
        
        int xd= labelPieza1.getLocation().x;
        int yd =labelPieza1.getLocation().y;
        this.remove(labelPieza1);
        
        switch (Rotacion) {
            case 0:
                labelPieza1=new JLabel();
                labelPieza1.setBounds(xd,yd,altoPanel,anchoPanel);
                labelPieza1.addMouseListener(this);
                labelPieza1.addMouseMotionListener(this);
                labelPieza1.setIcon(icon3);
                this.add(labelPieza1);
                Rotacion=1;
                break;
            case 1:
                
                if(LastRota==0){
                
                labelPieza1=new JLabel();
                labelPieza1.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza1.addMouseListener(this);
                labelPieza1.addMouseMotionListener(this);
                labelPieza1.setIcon(icon);
                this.add(labelPieza1);
                Rotacion=0;
                break;
                }
                
                else{
                    
                labelPieza1=new JLabel();
                labelPieza1.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza1.addMouseListener(this);
                labelPieza1.addMouseMotionListener(this);
                labelPieza1.setIcon(icon2);
                this.add(labelPieza1);
                Rotacion=2;
                break;
                }
            case 2:
                labelPieza1=new JLabel();
                labelPieza1.setBounds(xd,yd,altoPanel,anchoPanel);
                labelPieza1.addMouseListener(this);
                labelPieza1.addMouseMotionListener(this);
                labelPieza1.setIcon(icon1);
                this.add(labelPieza1);
                Rotacion=3;
                break;
            case 3:
                
                if(LastRota==1){
                
                labelPieza1=new JLabel();
                labelPieza1.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza1.addMouseListener(this);
                labelPieza1.addMouseMotionListener(this);
                labelPieza1.setIcon(icon);
                this.add(labelPieza1);
                Rotacion=0;
                break;
                }
                
                else{
                    
                labelPieza1=new JLabel();
                labelPieza1.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza1.addMouseListener(this);
                labelPieza1.addMouseMotionListener(this);
                labelPieza1.setIcon(icon2);
                this.add(labelPieza1);
                Rotacion=2;
                break;
                }
            default:
                break;
        }
        }
        
        if(piezaSeleccionada==2){
        
        int xd= labelPieza2.getLocation().x;
        int yd =labelPieza2.getLocation().y;
        this.remove(labelPieza2);
        
        switch (Rotacion2) {
            case 0:
                labelPieza2=new JLabel();
                labelPieza2.setBounds(xd,yd,altoPanel,anchoPanel);
                labelPieza2.addMouseListener(this);
                labelPieza2.addMouseMotionListener(this);
                labelPieza2.setIcon(icon3);
                this.add(labelPieza2);
                Rotacion2=1;
                break;
            case 1:
                
                if(LastRota==0){
                
                labelPieza2=new JLabel();
                labelPieza2.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza2.addMouseListener(this);
                labelPieza2.addMouseMotionListener(this);
                labelPieza2.setIcon(icon);
                this.add(labelPieza2);
                Rotacion2=0;
                break;
                }
                
                else{
                    
                labelPieza2=new JLabel();
                labelPieza2.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza2.addMouseListener(this);
                labelPieza2.addMouseMotionListener(this);
                labelPieza2.setIcon(icon2);
                this.add(labelPieza2);
                Rotacion2=2;
                break;
                }
            case 2:
                labelPieza2=new JLabel();
                labelPieza2.setBounds(xd,yd,altoPanel,anchoPanel);
                labelPieza2.addMouseListener(this);
                labelPieza2.addMouseMotionListener(this);
                labelPieza2.setIcon(icon1);
                this.add(labelPieza2);
                Rotacion2=3;
                break;
            case 3:
                
                if(LastRota==1){
                
                labelPieza2=new JLabel();
                labelPieza2.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza2.addMouseListener(this);
                labelPieza2.addMouseMotionListener(this);
                labelPieza2.setIcon(icon);
                this.add(labelPieza2);
                Rotacion2=0;
                break;
                }
                
                else{
                    
                labelPieza2=new JLabel();
                labelPieza2.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza2.addMouseListener(this);
                labelPieza2.addMouseMotionListener(this);
                labelPieza2.setIcon(icon2);
                this.add(labelPieza2);
                Rotacion2=2;
                break;
                }
            default:
                break;
        }
        }
        
        if(piezaSeleccionada==3){
        
        int xd= labelPieza3.getLocation().x;
        int yd =labelPieza3.getLocation().y;
        this.remove(labelPieza3);
        
        switch (Rotacion3) {
            case 0:
                labelPieza3=new JLabel();
                labelPieza3.setBounds(xd,yd,altoPanel,anchoPanel);
                labelPieza3.addMouseListener(this);
                labelPieza3.addMouseMotionListener(this);
                labelPieza3.setIcon(icon3);
                this.add(labelPieza3);
                Rotacion3=1;
                break;
            case 1:
                
                if(LastRota==0){
                
                labelPieza3=new JLabel();
                labelPieza3.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza3.addMouseListener(this);
                labelPieza3.addMouseMotionListener(this);
                labelPieza3.setIcon(icon);
                this.add(labelPieza3);
                Rotacion3=0;
                break;
                }
                
                else{
                    
                labelPieza3=new JLabel();
                labelPieza3.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza3.addMouseListener(this);
                labelPieza3.addMouseMotionListener(this);
                labelPieza3.setIcon(icon2);
                this.add(labelPieza3);
                Rotacion3=2;
                break;
                }
            case 2:
                labelPieza3=new JLabel();
                labelPieza3.setBounds(xd,yd,altoPanel,anchoPanel);
                labelPieza3.addMouseListener(this);
                labelPieza3.addMouseMotionListener(this);
                labelPieza3.setIcon(icon1);
                this.add(labelPieza3);
                Rotacion3=3;
                break;
            case 3:
                
                if(LastRota==1){
                
                labelPieza3=new JLabel();
                labelPieza3.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza3.addMouseListener(this);
                labelPieza3.addMouseMotionListener(this);
                labelPieza3.setIcon(icon);
                this.add(labelPieza3);
                Rotacion3=0;
                break;
                }
                
                else{
                    
                labelPieza3=new JLabel();
                labelPieza3.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza3.addMouseListener(this);
                labelPieza3.addMouseMotionListener(this);
                labelPieza3.setIcon(icon2);
                this.add(labelPieza3);
                Rotacion3=2;
                break;
                }
            default:
                break;
        }
        }
        
        if(piezaSeleccionada==4){
        
        int xd= labelPieza4.getLocation().x;
        int yd =labelPieza4.getLocation().y;
        this.remove(labelPieza4);
        
        switch (Rotacion4) {
            case 0:
                labelPieza4=new JLabel();
                labelPieza4.setBounds(xd,yd,altoPanel,anchoPanel);
                labelPieza4.addMouseListener(this);
                labelPieza4.addMouseMotionListener(this);
                labelPieza4.setIcon(icon3);
                this.add(labelPieza4);
                Rotacion4=1;
                break;
            case 1:
                
                if(LastRota==0){
                
                labelPieza4=new JLabel();
                labelPieza4.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza4.addMouseListener(this);
                labelPieza4.addMouseMotionListener(this);
                labelPieza4.setIcon(icon);
                this.add(labelPieza4);
                Rotacion4=0;
                break;
                }
                
                else{
                    
                labelPieza4=new JLabel();
                labelPieza4.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza4.addMouseListener(this);
                labelPieza4.addMouseMotionListener(this);
                labelPieza4.setIcon(icon2);
                this.add(labelPieza4);
                Rotacion4=2;
                break;
                }
            case 2:
                labelPieza4=new JLabel();
                labelPieza4.setBounds(xd,yd,altoPanel,anchoPanel);
                labelPieza4.addMouseListener(this);
                labelPieza4.addMouseMotionListener(this);
                labelPieza4.setIcon(icon1);
                this.add(labelPieza4);
                Rotacion4=3;
                break;
            case 3:
                
                if(LastRota==1){
                
                labelPieza4=new JLabel();
                labelPieza4.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza4.addMouseListener(this);
                labelPieza4.addMouseMotionListener(this);
                labelPieza4.setIcon(icon);
                this.add(labelPieza4);
                Rotacion4=0;
                break;
                }
                
                else{
                    
                labelPieza4=new JLabel();
                labelPieza4.setBounds(xd,yd,anchoPanel, altoPanel);
                labelPieza4.addMouseListener(this);
                labelPieza4.addMouseMotionListener(this);
                labelPieza4.setIcon(icon2);
                this.add(labelPieza4);
                Rotacion4=2;
                break;
                }
            default:
                break;
        }
        }
        
        
        LastRota=1;
        repaint();
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
          
            
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
            
            if(e.getSource() == labelPieza1){
            p1.getBounds();
            System.out.println(p1.getBounds());
            initialClick = e.getPoint();
            icx1=e.getX()+labelPieza1.getLocation().x;
            icy1=e.getY()+labelPieza1.getLocation().y;
            
            System.out.println(icx1+ "xd"+ icy1);
            System.out.println(e.toString());
            }
            
            if(e.getSource() == labelPieza2){
            p2.getBounds();
            System.out.println(p2.getBounds());
            initialClick = e.getPoint();
            icx1=e.getX()+labelPieza2.getLocation().x;
            icy1=e.getY()+labelPieza2.getLocation().y;
            
            System.out.println(icx1+ "xd"+ icy1);
            System.out.println(e.toString());
            }
            
            if(e.getSource() == labelPieza3){
            p3.getBounds();
            System.out.println(p3.getBounds());
            initialClick = e.getPoint();
            icx1=e.getX()+labelPieza3.getLocation().x;
            icy1=e.getY()+labelPieza3.getLocation().y;
            
            System.out.println(icx1+ "xd"+ icy1);
            System.out.println(e.toString());
            }
            
            if(e.getSource() == labelPieza4){
            p4.getBounds();
            System.out.println(p4.getBounds());
            initialClick = e.getPoint();
            icx1=e.getX()+labelPieza4.getLocation().x;
            icy1=e.getY()+labelPieza4.getLocation().y;
            
            System.out.println(icx1+ "xd"+ icy1);
            System.out.println(e.toString());
            }
            
            
            count=0;
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        
                
        if(e.getSource() == labelPieza1 && p1.contains(icx1,icy1)==false && count==0){
        
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
        
        else if(e.getSource() == labelPieza1 && count==1){
        
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
        
        else if(e.getSource() == labelPieza2 && p2.contains(icx1,icy1)==false && count==0){
        
            int thisX = labelPieza2.getLocation().x;
            int thisY = labelPieza2.getLocation().y;


            // Determine how much the mouse moved since the initial click
            int xMoved = (labelPieza2.getLocation().x + e.getX()) - (thisX + initialClick.x);
            int yMoved = (labelPieza2.getLocation().y + e.getY()) - (thisY + initialClick.y);

            // Move picture to this position
            int X = thisX + xMoved;
            int Y = thisY + yMoved;

            p2.translate(xMoved, yMoved);

            labelPieza2.setLocation(X,Y);
            
            count=1;

            repaint();
        
        }
        
        else if(e.getSource() == labelPieza2 && count==1){
        
            int thisX = labelPieza2.getLocation().x;
            int thisY = labelPieza2.getLocation().y;


            // Determine how much the mouse moved since the initial click
            int xMoved = (labelPieza2.getLocation().x + e.getX()) - (thisX + initialClick.x);
            int yMoved = (labelPieza2.getLocation().y + e.getY()) - (thisY + initialClick.y);

            // Move picture to this position
            int X = thisX + xMoved;
            int Y = thisY + yMoved;

            p2.translate(xMoved, yMoved);

            labelPieza2.setLocation(X,Y);
            
            count=1;

            repaint();
  
        }
        
        else if(e.getSource() == labelPieza3 && p3.contains(icx1,icy1)==false && count==0){
        
            int thisX = labelPieza3.getLocation().x;
            int thisY = labelPieza3.getLocation().y;


            // Determine how much the mouse moved since the initial click
            int xMoved = (labelPieza3.getLocation().x + e.getX()) - (thisX + initialClick.x);
            int yMoved = (labelPieza3.getLocation().y + e.getY()) - (thisY + initialClick.y);

            // Move picture to this position
            int X = thisX + xMoved;
            int Y = thisY + yMoved;

            p3.translate(xMoved, yMoved);

            labelPieza3.setLocation(X,Y);
            
            count=1;

            repaint();
        
        }
        
        else if(e.getSource() == labelPieza3 && count==1){
        
            int thisX = labelPieza3.getLocation().x;
            int thisY = labelPieza3.getLocation().y;


            // Determine how much the mouse moved since the initial click
            int xMoved = (labelPieza3.getLocation().x + e.getX()) - (thisX + initialClick.x);
            int yMoved = (labelPieza3.getLocation().y + e.getY()) - (thisY + initialClick.y);

            // Move picture to this position
            int X = thisX + xMoved;
            int Y = thisY + yMoved;

            p3.translate(xMoved, yMoved);

            labelPieza3.setLocation(X,Y);
            
            count=1;

            repaint();
  
        }
        
        else if(e.getSource() == labelPieza4 && p2.contains(icx1,icy1)==false && count==0){
        
            int thisX = labelPieza4.getLocation().x;
            int thisY = labelPieza4.getLocation().y;


            // Determine how much the mouse moved since the initial click
            int xMoved = (labelPieza4.getLocation().x + e.getX()) - (thisX + initialClick.x);
            int yMoved = (labelPieza4.getLocation().y + e.getY()) - (thisY + initialClick.y);

            // Move picture to this position
            int X = thisX + xMoved;
            int Y = thisY + yMoved;

            p4.translate(xMoved, yMoved);

            labelPieza4.setLocation(X,Y);
            
            count=1;

            repaint();
        
        }
        
        else if(e.getSource() == labelPieza4 && count==1){
        
            int thisX = labelPieza4.getLocation().x;
            int thisY = labelPieza4.getLocation().y;


            // Determine how much the mouse moved since the initial click
            int xMoved = (labelPieza4.getLocation().x + e.getX()) - (thisX + initialClick.x);
            int yMoved = (labelPieza4.getLocation().y + e.getY()) - (thisY + initialClick.y);

            // Move picture to this position
            int X = thisX + xMoved;
            int Y = thisY + yMoved;

            p4.translate(xMoved, yMoved);

            labelPieza4.setLocation(X,Y);
            
            count=1;

            repaint();
  
        }
        
        
    }
    
    public void mouseMoved(MouseEvent e){
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = labelPieza1.getLocation().x;
        int y = labelPieza1.getLocation().y;
        if(x<xImagenPanel+20 && x>xImagenPanel-20 && y<yImagenPanel+20 && y>yImagenPanel-20 && Rotacion==0) {
            
            labelPieza1.setLocation(-500, 0);
            p1.translate(-1000, 0);
            pieza1terminada=1;
            System.out.println("pieza 1 lista");
        }
        x = labelPieza2.getLocation().x;
        y = labelPieza2.getLocation().y;
        
        if(x<xImagenPanel+20 && x>xImagenPanel-20 && y<yImagenPanel+20 && y>yImagenPanel-20 && Rotacion==0) {
            
            labelPieza2.setLocation(-500, 0);
            p2.translate(-1000, 0);
            pieza2terminada=1;
            System.out.println("pieza 2 lista");
        }
        
        x = labelPieza3.getLocation().x;
        y = labelPieza3.getLocation().y;
        
        if(x<xImagenPanel+20 && x>xImagenPanel-20 && y<yImagenPanel+20 && y>yImagenPanel-20 && Rotacion==0) {
            
            labelPieza3.setLocation(-500, 0);
            p3.translate(-1000, 0);
            pieza3terminada=1;
            System.out.println("pieza 3 lista");
        }
        x = labelPieza4.getLocation().x;
        y = labelPieza4.getLocation().y;
        
        if(x<xImagenPanel+20 && x>xImagenPanel-20 && y<yImagenPanel+20 && y>yImagenPanel-20 && Rotacion==0) {
            
            labelPieza4.setLocation(-500, 0);
            p4.translate(-1000, 0);
            pieza4terminada=1;
            System.out.println("pieza 4 lista");
        }
        
        repaint();
        
        

                
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