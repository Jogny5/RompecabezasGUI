package rompecabezas_gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Clase jpanel donde ocurre el juego
 * @author jorge
 * @author diego
 */

public class PanelPrincipal extends JPanel implements MouseListener,MouseMotionListener{
    
    /**
     * Instancia a panel principal 
     */
    private static final PanelPrincipal instancia = new PanelPrincipal();
    /**
     * Instancia a modo editor 
     */
    private ModoEditor Meditor;
    /**
     * Instancia a modo juego 
     */
    private ModoJuego Mjuego;
    /**
     * Variable que indica el modo de juego
     */
    private int ModoDeJuego=0;

    /**
     * Indica la posicion x del comienzo de la linea 1 
     */
    public int L1x1;
    /**
     * Indica la posicion y del comienzo de la linea 1 
     */
    public int L1y1;
    /**
     * Indica la posicion x del final de la linea 1 
     */
    public int L1x2=-1;
    /**
     * Indica la posicion y del final de la linea 1
     */
    public int L1y2;
    
    /**
     * Indica la posicion x del comienzo de la linea 2 
     */
    public int L2x1;
    /**
     * Indica la posicion y del comienzo de la linea 2 
     */
    public int L2y1;
    /**
     * Indica la posicion x del final de la linea 2 
     */
    public int L2x2;
    /**
     * Indica la posicion y del final de la linea 2 
     */
    public int L2y2;
    
    /**
     * Poligno que hace de una de las lineas que separan la imagen 
     */
    private Polygon Linea1;
    /**
     * Poligno que hace de una de las lineas que separan la imagen 
     */
    private Polygon Linea2;
    
    /**
     * Posicion x de la interseccion 
     */
    public int interseccionx=-1;
    /**
     * Posicion y de la interseccion 
     */
    public int intersecciony=-1;
    
    /**
     * Posicion x de la pieza 1 una vez hecha 
     */
    public int xImagen=0;   
    /**
     * Posicion y de la pieza 1 una vez hecha 
     */
    public int yImagen=0;
    /**Posicion x de la pieza 2 una vez hecha 
     */
    public int xImagen2=0;
    /**
     * Posicion y de la pieza 2 una vez hecha 
     */
    public int yImagen2=0;
    /**
     * Posicion x de la pieza 3 una vez hecha 
     */
    public int xImagen3=0;
    /**
     * Posicion y de la pieza 3 una vez hecha 
     */
    public int yImagen3=0;
    /**
     * Posicion x de la pieza 4 una vez hecha 
     */
    public int xImagen4=0;
    /**
     * Posicion y de la pieza 4 una vez hecha 
     */
    public int yImagen4=0;
    
    /**Posicion x de la imagen central a la que se le hacen los cortes 
     */
    public int xImagenPanel=200;            
    /**Posicion y de la imagen central a la que se le hacen los cortes 
     */
    public int yImagenPanel=400;
    /**Ancho de la imagen central a la que se le hacen los cortes 
     */
    public int anchoPanel=450;
    /**Alto de la imagen central a la que se le hacen los cortes 
     */
    public int altoPanel=250;        
    
    /**
     * Indica si la pieza 1 ya fue colocada en el rompecabezas y se aceptó 
     */
    public int pieza1terminada=0;
    /**
     * Indica si la pieza 2 ya fue colocada en el rompecabezas y se aceptó 
     */
    public int pieza2terminada=0;
    /**
     * Indica si la pieza 3 ya fue colocada en el rompecabezas y se aceptó 
     */
    public int pieza3terminada=0;
    /**
     * Indica si la pieza 4 ya fue colocada en el rompecabezas y se aceptó 
     */
    public int pieza4terminada=0;
    
    /**
     * Indica el estado las lineas para ver si se tienen que hacer de nuevo
     */
    public int pieza1estado=0;
    /**
     * Indica el estado las lineas para ver si se tienen que hacer de nuevo
     */
    public int pieza2estado=0;
    /**
     * Indica el estado las lineas para ver si se tienen que hacer de nuevo
     */
    public int pieza3estado=0;
    /**
     * Indica el estado las lineas para ver si se tienen que hacer de nuevo
     */
    public int pieza4estado=0;
    
    /**
     * Poligono que tapa la pieza 1 para formar la pieza 
     */
    private Polygon p1;
    /**
     * Poligono que tapa la pieza 2 para formar la pieza 
     */
    private Polygon p2;
    /**
     * Poligono que tapa la pieza 3 para formar la pieza 
     */
    private Polygon p3;
    /**
     * Poligono que tapa la pieza 4 para formar la pieza 
     */
    private Polygon p4;
    
    /**
     * Label para la imagen central del rompecabezas 
     */
    public JLabel label;
    /**
     * Label para la imagen de la pieza 1 
     */
    public JLabel labelPieza1;
    /**
     * Label para la imagen de la pieza 2 
     */
    public JLabel labelPieza2;
    /**
     * Label para la imagen de la pieza 3 
     */
    public JLabel labelPieza3;
    /**
     * Label para la imagen de la pieza 4 
     */
    public JLabel labelPieza4;
    /**
     * Label con el mensaje de fin de juego 
     */
    public JLabel labelfinal;   
 
    /**
     * Ubicacion del clickeo cuando se va a arrastrar una pieza para que se mueva junto al mouse 
     */
    Point initialClick;
    
    /**
     * Guarda la posicion en la que se encontraba el poligono y que cuando se traslade siga correctamente al label 
     */
    int icx1;
    /**
     * Guarda la posicion en la que se encontraba el poligono y que cuando se traslade siga correctamente al label 
     */    
    int icy1;
    /**
     Indica que se clickeo la pieza y no el poligono alrededor
     */
    int count=0;
    
    /**
     * Inicializa el panel y los label de las piezas
     */
    int piezaSeleccionada=1;
    
    /**
     * Constructor de la clase panelprincipal
     */
    
    BufferedImage img;
    
    URL url = getClass().getResource("/kirby.png");
   
    ImageIcon icon;
    
    BufferedImage img1;
    BufferedImage img2;
    BufferedImage img3;
    BufferedImage img4;
    
    private PanelPrincipal(){
        
        try {
            img = ImageIO.read(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            img1 = ImageIO.read(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            img2 = ImageIO.read(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            img3 = ImageIO.read(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            img4 = ImageIO.read(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        icon = new ImageIcon(img);    

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
     * Dvuelve una instancia de la propia panelprincipal
     * @return Una instancia de panelprincipal
     */
    public static PanelPrincipal getInstancia(){
        
        return instancia;
    }    
    /**
     * Clase para definir que pieza esta seleccionada para rotarla
     * @param x el numero de la pieza para la rotacion
     */
    public void setPiezaelegir(int x){
        
        piezaSeleccionada=x;
    }
    
  

    /**
     * Metodo paint
     * @param g Son todos los graficos que usan
     */
    public void paint(Graphics g){
        super.paint(g);
        
        g.drawRect(xImagenPanel, yImagenPanel, anchoPanel, altoPanel);
        g.drawImage(img, xImagenPanel, yImagenPanel, anchoPanel, altoPanel,this); 
        //pintar las piezas una vez las 4 esten hechas
       
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
        
        if(L1x2!=-1 && pieza2estado==1){
            
            
            g.drawImage(img, 900, 900, anchoPanel, altoPanel,this); 
            g.drawImage(img1, 800, 800, anchoPanel, altoPanel,this); 
            
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
                            if(L1x2!=xImagenPanel+anchoPanel){
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(L2x2+xImagen2-xImagenPanel, yImagen2+altoPanel);
                                
                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(L2x2+xImagen3-xImagenPanel, yImagen3+altoPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+L1x2-xImagenPanel, yImagen3);
                                p3.addPoint(xImagen3, yImagen3);
                                
                                p4.addPoint(xImagen4, yImagen4+L2y1-yImagenPanel);
                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+L1y2-yImagenPanel);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                            
                            }else{
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
                                
                                p4.addPoint(xImagen4, yImagen4+L2y1-yImagenPanel);
                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+L1y2-yImagenPanel);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                            
                            }
                            
                        // chequeado arriba    
                        }else{
                            if(L1y2==yImagenPanel){
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+L2y2-yImagenPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            
                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+L1y2-xImagenPanel, yImagen3);
                                p3.addPoint(xImagen3, yImagen3);
                            
                                p4.addPoint(xImagen4, yImagen4+L2y1-yImagenPanel);
                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4);
                                p4.addPoint(xImagen4+L1x2-xImagenPanel, yImagen4);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                            
                                System.out.println("hdhddhhd");    
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
                            
                                p4.addPoint(xImagen4, yImagen4+L2y1-yImagenPanel);
                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4);
                                p4.addPoint(xImagen4+L1x2-xImagenPanel, yImagen4);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                            
                                System.out.println("yigggig");    
                            }
                                             

                        }
                    
                    }else if(L1x1==xImagenPanel){
                        p1.addPoint(xImagen, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(L2x1+xImagen-xImagenPanel, yImagen);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+L1y1-yImagenPanel);
                        
                        
                        if(L2x2!=xImagenPanel+anchoPanel){
                            if(L1y2==yImagenPanel){
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(L2x2+xImagen2-xImagenPanel, yImagen2+altoPanel);
                            
                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(L2x2+xImagen3-xImagenPanel, yImagen3+L2y2-yImagenPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+L1x2-xImagenPanel, yImagen3);
                                p3.addPoint(xImagen3, yImagen3);
                                
                                p4.addPoint(xImagen4, yImagen4);
                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4);
                                p4.addPoint(xImagen4+L1x2-xImagenPanel, yImagen4);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                p4.addPoint(xImagen4+L2x1-xImagenPanel, yImagen4);
                                
                                
                            }else{
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
                                
                                p4.addPoint(xImagen4, yImagen4);
                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+L1y2-yImagenPanel);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                p4.addPoint(xImagen4+L2x1-xImagenPanel, yImagen4);
                                
                            }
                        //chequeado arriba    
                        }else{
                            if(L1y2==yImagenPanel){
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+L2y2-yImagenPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L1y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                            
                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+L1x2-xImagenPanel, yImagen3);
                                p3.addPoint(xImagen3, yImagen3);
                                
                                p4.addPoint(xImagen4, yImagen4);
                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4);
                                p4.addPoint(xImagen4+L1x2-xImagenPanel, yImagen4);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                p4.addPoint(xImagen4+L2x1-xImagenPanel, yImagen4);
                                
                                
                                System.out.println("rhryray");
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
                                
                                p4.addPoint(xImagen4, yImagen4);
                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+L1y2-yImagenPanel);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                p4.addPoint(xImagen4+L2x1-xImagenPanel, yImagen4);
                                
                                
                                System.out.println("óiói");
                            }
                            
                        }
                        
                        
                        
                        
                    }else if(L2x1==xImagenPanel){
                        p1.addPoint(L1x1+xImagen-xImagenPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(xImagen, yImagen);
                        p1.addPoint(xImagen, yImagen+L2y1-yImagenPanel);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);
                        
                        if(L1x2!=xImagenPanel+anchoPanel){
                            if(L2x2!=xImagenPanel+anchoPanel){
                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+altoPanel);
                                p2.addPoint(L1x1+xImagen2-xImagenPanel, yImagen2+altoPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(L2x2+xImagen2-xImagenPanel, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                                
                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+L2x2-xImagenPanel, yImagen3+altoPanel);
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
                                
                                
                            }
                        //check   
                        }else{
                            
                            if(L2x2==xImagenPanel+anchoPanel){
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
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L1y2-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3);
                                p3.addPoint(xImagen3, yImagen3);
                            
                            
                                p4.addPoint(xImagen4, yImagen4+L2y1-yImagenPanel);
                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+L1y2-yImagenPanel);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                
                                
                            }else{
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+altoPanel);
                                p2.addPoint(L1x1+xImagen2-xImagenPanel, yImagen2+altoPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(L2x2+xImagen2-xImagenPanel, yImagen2+altoPanel);

                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+L2x2+-xImagenPanel, yImagen3+altoPanel);
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
                            
                            
                        }
                        
                        
                    }else{
                        System.out.println("falta otro caso 1");
                    }
                    
                //check    
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

                        p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                        p2.addPoint(xImagen2+anchoPanel, yImagen2);
                        p2.addPoint(xImagen2, yImagen2);
                        p2.addPoint(xImagen2, yImagen2+altoPanel);
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
                        p4.addPoint(xImagen4+anchoPanel, yImagen4);
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
                            if(L1y2==yImagenPanel+altoPanel){
                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(xImagen2+L1x2-xImagenPanel, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);

                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+L1x2-xImagenPanel, yImagen3+altoPanel);
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
                                
                            }
                            
                            
                        //arriba check
                        }else{
                            
                            if(L1y2==yImagenPanel+altoPanel){
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(L1x2+xImagen2-xImagenPanel, yImagen2+altoPanel);
                            

                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+L1x2-xImagenPanel, yImagen3+altoPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3);
                                p3.addPoint(xImagen3, yImagen3);
                            

                                p4.addPoint(xImagen4, yImagen4+L1y1-yImagenPanel);
                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+L2y2-yImagenPanel);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                
                                System.out.println("qaytyqa");
                                
                            }else{
                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+L2y1-yImagenPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+L1y2-yImagenPanel);
                            

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
                                
                                System.out.println("zggzgzg");
                                
                            }
                            
                            
                        }
                        
                    }else if(L1x1==xImagenPanel){
                        
                        p1.addPoint(xImagen+L2x1-xImagenPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen+altoPanel);
                        p1.addPoint(xImagen+anchoPanel, yImagen);
                        p1.addPoint(xImagen, yImagen);
                        p1.addPoint(xImagen, yImagen+L1y1-yImagenPanel);
                        p1.addPoint(interseccionx+xImagen-xImagenPanel, intersecciony+yImagen-yImagenPanel);

                        if(L2x2==xImagenPanel+anchoPanel){
                            
                            if(L1y2==yImagenPanel+altoPanel){
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+L2x1-xImagenPanel, yImagen2+altoPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(xImagen2+L1x2-xImagenPanel, yImagen2+altoPanel);
                                
                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+L1x2-xImagenPanel, yImagen3+altoPanel);
                                p3.addPoint(interseccionx+xImagen3-xImagenPanel, intersecciony+yImagen3-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3);
                                p3.addPoint(xImagen3, yImagen3);
                            
                                p4.addPoint(xImagen4, yImagen4+L1y1-yImagenPanel);
                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+L2y2-yImagenPanel);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                                                
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
                                p3.addPoint(xImagen3+anchoPanel, yImagen3+L2y2-yImagenPanel);
                                p3.addPoint(xImagen3+anchoPanel, yImagen3);
                                p3.addPoint(xImagen3, yImagen3);
                            
                                p4.addPoint(xImagen4, yImagen4+L1y1-yImagenPanel);
                                p4.addPoint(xImagen4, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+altoPanel);
                                p4.addPoint(xImagen4+anchoPanel, yImagen4+L2y2-yImagenPanel);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                                
                            }
                            
                            
                        //arriba check    
                        }else{
                            
                            if(L1y2==yImagenPanel+altoPanel){
                                p2.addPoint(xImagen2+anchoPanel, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+anchoPanel, yImagen2);
                                p2.addPoint(xImagen2, yImagen2);
                                p2.addPoint(xImagen2, yImagen2+altoPanel);
                                p2.addPoint(xImagen2+L2x1-xImagenPanel, yImagen2+altoPanel);
                                p2.addPoint(interseccionx+xImagen2-xImagenPanel, intersecciony+yImagen2-yImagenPanel);
                                p2.addPoint(xImagen2+L1x2-xImagenPanel, yImagen2+altoPanel);

                                p3.addPoint(xImagen3, yImagen3+altoPanel);
                                p3.addPoint(xImagen3+L1x2-xImagenPanel, yImagen3+altoPanel);
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
                                p4.addPoint(xImagen4+anchoPanel, yImagen4);
                                p4.addPoint(xImagen4+L2x2-xImagenPanel, yImagen4);
                                p4.addPoint(interseccionx+xImagen4-xImagenPanel, intersecciony+yImagen4-yImagenPanel);
                            

                            }
                            
                            
                            
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

            img1 = CreadorPiezas(img1,p1);
            img2 = CreadorPiezas(img2,p2);
            img3 = CreadorPiezas(img3,p3);
            img4 = CreadorPiezas(img4,p4);
            
            ImageIcon icono1 = new ImageIcon(img1);
            ImageIcon icono2 = new ImageIcon(img2);
            ImageIcon icono3 = new ImageIcon(img3);
            ImageIcon icono4 = new ImageIcon(img4);

            labelPieza1.setIcon(icono1);
            labelPieza2.setIcon(icono2);
            labelPieza3.setIcon(icono3);
            labelPieza4.setIcon(icono4);
            
            
        }
        
        repaint();
        
    }
  
    private BufferedImage CreadorPiezas(BufferedImage image, Polygon p) {
            
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        int width = image.getWidth();
        int height = image.getHeight();
            
        for (int i = 0; i < height; i++) {
             for (int j = 0; j < width; j++) {
                   
                if(p.contains(j,i)){

                    newImage.setRGB(j, i,0);

                    }                     
                }
            }   
        return newImage; 
    }
    
    
    
    
    /**
     * Metdo vacio
     * @param e mouseevent
     */
    @Override
    public void mouseClicked(MouseEvent e) {
          
            
        
    }
    /**
     * Metodo usado para seleccionar la pieza a arrrastrar
     * @param e mouseevent
     */
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
    /**
     * Metodo que arrastra la pieza
     * @param e mouseevent
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        
                
        if(e.getSource() == labelPieza1 ){
        
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
            
           

            repaint();
        
        }
        
       
        else if(e.getSource() == labelPieza2 ){
        
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
            
         
            repaint();
        
        }
        
        
        
        else if(e.getSource() == labelPieza3 ){
        
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
            
            

            repaint();
        
        }
        
        
        
        else if(e.getSource() == labelPieza4){
        
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
            
           

            repaint();
        
        }
        
       
        
        
    }
    /**
     * Metodo vacio
     * @param e mouseevent
     */
    public void mouseMoved(MouseEvent e){
    }
    /**
     * Meetodo que suelta la pieza arratrada
     * @param e mouseevent
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        int x = labelPieza1.getLocation().x;
        int y = labelPieza1.getLocation().y;
        if(x<xImagenPanel+20 && x>xImagenPanel-20 && y<yImagenPanel+20 && y>yImagenPanel-20 ) {
            
            labelPieza1.setLocation(-500, 0);
            p1.translate(-1000, 0);
            pieza1terminada=1;
            System.out.println("pieza 1 lista");
        }
        x = labelPieza2.getLocation().x;
        y = labelPieza2.getLocation().y;
        
        if(x<xImagenPanel+20 && x>xImagenPanel-20 && y<yImagenPanel+20 && y>yImagenPanel-20 ) {
            
            labelPieza2.setLocation(-500, 0);
            p2.translate(-1000, 0);
            pieza2terminada=1;
            System.out.println("pieza 2 lista");
        }
        
        x = labelPieza3.getLocation().x;
        y = labelPieza3.getLocation().y;
        
        if(x<xImagenPanel+20 && x>xImagenPanel-20 && y<yImagenPanel+20 && y>yImagenPanel-20 ) {
            
            labelPieza3.setLocation(-500, 0);
            p3.translate(-1000, 0);
            pieza3terminada=1;
            System.out.println("pieza 3 lista");
        }
        x = labelPieza4.getLocation().x;
        y = labelPieza4.getLocation().y;
        
        if(x<xImagenPanel+20 && x>xImagenPanel-20 && y<yImagenPanel+20 && y>yImagenPanel-20 ) {
            
            labelPieza4.setLocation(-500, 0);
            p4.translate(-1000, 0);
            pieza4terminada=1;
            System.out.println("pieza 4 lista");
        }
        
        repaint();
        
        

                
        initialClick = null;
        
    }

    /**
     * metodo para crear las lineas que separan las piezas
     * guardando los puntos de entrada de las lineas 
     * @param e mouseevent
     */
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

    /**
     * Metodo para guardar los puntos finales de las lineas
     * @param e mouseevent
     */
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