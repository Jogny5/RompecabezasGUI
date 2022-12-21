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
     * Indica el modo de juego
     */
    public int ModoDeJuego=0;
    /**
     * Indica la posicion x del comienzo de la linea 1 
     */
    private int L1x1;
    /**
     * Indica la posicion y del comienzo de la linea 1 
     */
    private int L1y1;
    /**
     * Indica la posicion x del final de la linea 1 
     */
    private int L1x2=-1;
    /**
     * Indica la posicion y del final de la linea 1
     */
    private int L1y2;
    
    /**
     * Indica la posicion x del comienzo de la linea 2 
     */
    private int L2x1;
    /**
     * Indica la posicion y del comienzo de la linea 2 
     */
    private int L2y1;
    /**
     * Indica la posicion x del final de la linea 2 
     */
    private int L2x2;
    /**
     * Indica la posicion y del final de la linea 2 
     */
    private int L2y2;
    
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
    private int interseccionx=-1;
    /**
     * Posicion y de la interseccion 
     */
    private int intersecciony=-1;
    
    /**
     * Posicion x de la pieza 1 una vez hecha 
     */
    private int xImagen=0;   
    /**
     * Posicion y de la pieza 1 una vez hecha 
     */
    private int yImagen=0;
    /**
     * Posicion x de la pieza 2 una vez hecha 
     */
    private int xImagen2=0;
    /**
     * Posicion y de la pieza 2 una vez hecha 
     */
    private int yImagen2=0;
    /**
     * Posicion x de la pieza 3 una vez hecha 
     */
    private int xImagen3=0;
    /**
     * Posicion y de la pieza 3 una vez hecha 
     */
    private int yImagen3=0;
    /**
     * Posicion x de la pieza 4 una vez hecha 
     */
    private int xImagen4=0;
    /**
     * Posicion y de la pieza 4 una vez hecha 
     */
    private int yImagen4=0;
    
    /**Posicion x de la imagen central a la que se le hacen los cortes 
     */
    private int xImagenPanel=200;            
    /**Posicion y de la imagen central a la que se le hacen los cortes 
     */
    private int yImagenPanel=400;
    /**Ancho de la imagen central a la que se le hacen los cortes 
     */
    private int anchoPanel=450;
    /**Alto de la imagen central a la que se le hacen los cortes 
     */
    private int altoPanel=250;        
    
    /**
     * Indica si la pieza 1 ya fue colocada en el rompecabezas y se aceptó 
     */
    private int pieza1terminada=0;
    /**
     * Indica si la pieza 2 ya fue colocada en el rompecabezas y se aceptó 
     */
    private int pieza2terminada=0;
    /**
     * Indica si la pieza 3 ya fue colocada en el rompecabezas y se aceptó 
     */
    private int pieza3terminada=0;
    /**
     * Indica si la pieza 4 ya fue colocada en el rompecabezas y se aceptó 
     */
    private int pieza4terminada=0;
    
    /**
     * Indica el estado las lineas para ver si se tienen que hacer de nuevo
     */
    private int pieza1estado=0;
    /**
     * Indica el estado las lineas para ver si se tienen que hacer de nuevo
     */
    private int pieza2estado=0;
    /**
     * Indica el estado las lineas para ver si se tienen que hacer de nuevo
     */
    private int pieza3estado=0;
    /**
     * Indica el estado las lineas para ver si se tienen que hacer de nuevo
     */
    private int pieza4estado=0;
    
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
    private JLabel label;
    /**
     * Label para la imagen de la pieza 1 
     */
    private JLabel labelPieza1;
    /**
     * Label para la imagen de la pieza 2 
     */
    private JLabel labelPieza2;
    /**
     * Label para la imagen de la pieza 3 
     */
    private JLabel labelPieza3;
    /**
     * Label para la imagen de la pieza 4 
     */
    private JLabel labelPieza4;
    /**
     * Label para la imagen central del rompecabezas 
     */
    public JLabel labeleditor;
    /**
     * Label para la imagen central del rompecabezas 
     */
    public JLabel labeljuego;
    /**
     * Label con el mensaje de fin de juego 
     */
    private JLabel labelfinal;   
 
    /**
     * Ubicacion del clickeo cuando se va a arrastrar una pieza para que se mueva junto al mouse 
     */
    private Point initialClick;
    
    /**
     * Guarda la posicion en la que se encontraba el poligono y que cuando se traslade siga correctamente al label 
     */
    private int icx1;
    /**
     * Guarda la posicion en la que se encontraba el poligono y que cuando se traslade siga correctamente al label 
     */    
    private int icy1;
    /**
     Indica que se clickeo la pieza y no el poligono alrededor
     */
    private int count=0;
    
    /**
     * Inicializa el panel y los label de las piezas
     */
    private int piezaSeleccionada=1;
    
    /**
     * BufferedImage en la cual se guardara el png original
     */
    private BufferedImage img;
    
    /**
     * URL el cual obtiene el png que sera la imagen a usar del rompecabezas
     */
    private URL url = getClass().getResource("/kirby.png");
   
    /**
     * Icono el cual se usara para el label en el que se divide la imagen
     */
    private ImageIcon icon;
    
    
    /**
     * Imagen que se guardara la pieza1 despues de hacer los pixeles transparentes en base al poligono p1
     */
    private BufferedImage img1;
    
    /**
     * Imagen que se guardara la pieza2 despues de hacer los pixeles transparentes en base al poligono p2
     */
    private BufferedImage img2;
    
    /**
     * Imagen que se guardara la pieza3 despues de hacer los pixeles transparentes en base al poligono p3
     */
    private BufferedImage img3;
    
    /**
     * Imagen que se guardara la pieza4 despues de hacer los pixeles transparentes en base al poligono p4
     */
    private BufferedImage img4;
    
    
    /**
     * Icono a usar para hacer un RotatedIcon con la imagen de la pieza 1
     */
    private ImageIcon icon1;
    
    /**
     * Icono a usar para hacer un RotatedIcon con la imagen de la pieza 2
     */
    private ImageIcon icon2;
    
    /**
     * Icono a usar para hacer un RotatedIcon con la imagen de la pieza 3
     */
    private ImageIcon icon3;
    
    /**
     * Icono a usar para hacer un RotatedIcon con la imagen de la pieza 4
     */
    private ImageIcon icon4;
    
    
    /**
     * RotatedIcon hecho en base de un ImageIcon, permite rotar la imagen de icon1
     */
    private RotatedIcon icono1;
    
    /**
     * RotatedIcon hecho en base de un ImageIcon, permite rotar la imagen de icon2
     */
    private RotatedIcon icono2;
    
    /**
     * RotatedIcon hecho en base de un ImageIcon, permite rotar la imagen de icon3
     */
    private RotatedIcon icono3;
    
    /**
     * RotatedIcon hecho en base de un ImageIcon, permite rotar la imagen de icon4
     */
    private RotatedIcon icono4;    
       
    /**
     * Constructor de la clase panelprincipal
     */
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
        
        labeleditor=new JLabel("Modo editor");
        labeleditor.setBounds(-600,100, 400, 100);
        labeleditor.setFont(new Font("arial",Font.PLAIN,50));
        this.add(labeleditor);
        
        labeljuego=new JLabel("Modo juego");
        labeljuego.setBounds(-600,100, 400, 100);
        labeljuego.setFont(new Font("arial",Font.PLAIN,50));
        this.add(labeljuego);
                
        label=new JLabel();
        label.setBounds(xImagenPanel, yImagenPanel, anchoPanel, altoPanel);
        label.setIcon(icon);
        label.addMouseListener(this);
        this.add(label);
        
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
     * Metodo para definir que pieza esta seleccionada para rotarla
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
       
        if(pieza1terminada==1 && pieza2terminada==1 && 
                pieza3terminada==1 && pieza4terminada==1 && ModoDeJuego==1){
            labelfinal.setLocation(500,100);
            labeleditor.setLocation(-500,100);
            labeljuego.setLocation(-500,100);
            
        }else if(ModoDeJuego==0){
            labeleditor.setLocation(500,100);
            labeljuego.setLocation(-500,100);
        }else{
            labeljuego.setLocation(500,100);
            labeleditor.setLocation(-500,100);
        }
               
        
        g.setColor(Color.blue);
        if(L1x2>=0){
            g.setColor(Color.red);
            g.drawPolygon(Linea1);
            
            if(L2x2>=0){
                g.drawPolygon(Linea2);
            
            }
            if(interseccionx>0){
                g.fillOval((int)interseccionx,(int)intersecciony,15,15);
                g.drawOval((int)interseccionx,(int)intersecciony,15,15);
            }
            
        }
        
        if(L1x2!=-1 && pieza2estado==1 && ModoDeJuego==1){
            
            
            g.drawImage(img, 900, 900, anchoPanel, altoPanel,this); 
            g.drawImage(img1, 800, 800, anchoPanel, altoPanel,this); 
            
        }
    }
    /**
     * Clase que cambia al modo editor
     */
    public void EstadoEditor(){
        ModoDeJuego=0;
        
        L1x1=-1;
        L1y1=-1;
        L1x2=-1;
        L1y2=-1;
        L2x1=-1;
        L2y1=-1;
        L2x2=-1;
        L2y2=-1;
        Linea1=new Polygon();
        Linea2=new Polygon();
        
        interseccionx=-1;
        intersecciony=-1;
        xImagen=0;   
        yImagen=0;
        xImagen2=0;
        yImagen2=0;
        xImagen3=0;
        yImagen3=0;
        xImagen4=0;
        yImagen4=0;
        xImagenPanel=200;            
        yImagenPanel=400;
        anchoPanel=450;
        altoPanel=250;        
        pieza1terminada=0;
        pieza2terminada=0;
        pieza3terminada=0;
        pieza4terminada=0;
        
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
        
        p1.translate(-1000,0 );
        labelPieza1.setLocation(-800,0);
        p2.translate(-1000,0 );
        labelPieza2.setLocation(-800,0);
        p3.translate(-1000,0 );
        labelPieza3.setLocation(-800,0);
        p4.translate(-1000,0 );
        labelPieza4.setLocation(-800,0);
        
        labelPieza1.setIcon(null);
        labelPieza2.setIcon(null);
        labelPieza3.setIcon(null);
        labelPieza4.setIcon(null);
        
        p1.reset();
        p2.reset();
        p3.reset();
        p4.reset();
        
        labelPieza1.removeAll();
        labelPieza2.removeAll();
        labelPieza3.removeAll();
        labelPieza4.removeAll();
        
        labelPieza1=new JLabel();
        
        labelPieza2=new JLabel();
        
        labelPieza3=new JLabel();
        
        labelPieza4=new JLabel();
        
        pieza1estado=0;
        pieza2estado=0;
        pieza3estado=0;
        pieza4estado=0;
        
        labelfinal.setLocation(-600,100);
        
        p1=new Polygon();
        p2=new Polygon();
        p3=new Polygon();
        p4=new Polygon();
        Linea1=new Polygon();
        Linea2=new Polygon();
        
        icx1=-1;
        icy1=-1;
        count=0;
    
        piezaSeleccionada=1;
        
        icon1 = new ImageIcon(img1);
        icon2 = new ImageIcon(img2);
        icon3 = new ImageIcon(img3);
        icon4 = new ImageIcon(img4);
            
        icono1 = new RotatedIcon(icon1,RotatedIcon.Rotate.UPSIDE_DOWN);
        icono1 = new RotatedIcon(icono1,RotatedIcon.Rotate.UPSIDE_DOWN);
            
        icono2 = new RotatedIcon(icon2,RotatedIcon.Rotate.UPSIDE_DOWN);
        icono2 = new RotatedIcon(icono2,RotatedIcon.Rotate.UPSIDE_DOWN);
            
        icono3 = new RotatedIcon(icon3,RotatedIcon.Rotate.UPSIDE_DOWN);
        icono3 = new RotatedIcon(icono3,RotatedIcon.Rotate.UPSIDE_DOWN);
          
        icono4 = new RotatedIcon(icon4,RotatedIcon.Rotate.UPSIDE_DOWN);
        icono4 = new RotatedIcon(icono4,RotatedIcon.Rotate.UPSIDE_DOWN);
        
        repaint();
    }
    /**
     * Clase que cambia al modo juego
     */
    public void EstadoJuego(){
        ModoDeJuego=1;
        repaint();
    }
    
    
    /**
     * Calcula la interseccion entre 2 lineas
     */
    public void interseccion(){
        System.out.println(pieza2estado);
        if(pieza2estado!=0 && ModoDeJuego==0 && L2x2!=-1){
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
            
        if(interseccionx>0){
            dividirPiezas();
        }
        
    }
    /**
     * Crea 4 piezas separadas por las 2 lineas hechas tomando todos los posibles casos de lineas
     */
    public void dividirPiezas(){
       
        //sacar las 4 figuras 
        
        
        //Primer caso principal
        if(L1y1>intersecciony && L2y1<intersecciony && pieza2estado!=0 && ModoDeJuego==0){
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
        }else if(L1y1<intersecciony && L2y1>intersecciony && pieza2estado!=0 && ModoDeJuego==0){
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
                        System.out.println("falta otro caso 2");
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
        }else if(L2y1>intersecciony && L1y1>intersecciony && pieza2estado!=0 && ModoDeJuego==0){
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
        }else if(L2y1<intersecciony && L1y1<intersecciony && pieza2estado!=0 && ModoDeJuego==0){
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
        
        if(pieza2estado!=0 && ModoDeJuego==0){

            img1 = CreadorPiezas(img1,p1);
            img2 = CreadorPiezas(img2,p2);
            img3 = CreadorPiezas(img3,p3);
            img4 = CreadorPiezas(img4,p4);
            
            img1 = trimImage(img1);
            img2 = trimImage(img2);
            img3 = trimImage(img3);
            img4 = trimImage(img4);
            
            icon1 = new ImageIcon(img1);
            icon2 = new ImageIcon(img2);
            icon3 = new ImageIcon(img3);
            icon4 = new ImageIcon(img4);
   
            icono1 = new RotatedIcon(icon1,RotatedIcon.Rotate.UPSIDE_DOWN);
            icono1 = new RotatedIcon(icono1,RotatedIcon.Rotate.UPSIDE_DOWN);
            
            icono2 = new RotatedIcon(icon2,RotatedIcon.Rotate.UPSIDE_DOWN);
            icono2 = new RotatedIcon(icono2,RotatedIcon.Rotate.UPSIDE_DOWN);
            
            icono3 = new RotatedIcon(icon3,RotatedIcon.Rotate.UPSIDE_DOWN);
            icono3 = new RotatedIcon(icono3,RotatedIcon.Rotate.UPSIDE_DOWN);
            
            icono4 = new RotatedIcon(icon4,RotatedIcon.Rotate.UPSIDE_DOWN);
            icono4 = new RotatedIcon(icono4,RotatedIcon.Rotate.UPSIDE_DOWN);
            
            labelPieza1=new JLabel();
            labelPieza1.setBounds(xImagen,yImagen,img1.getWidth(),img1.getHeight());
            labelPieza1.addMouseListener(this);
            labelPieza1.addMouseMotionListener(this);
            this.add(labelPieza1);

            labelPieza2=new JLabel();
            labelPieza2.setBounds(xImagen2, yImagen2,img2.getWidth(),img2.getHeight());
            labelPieza2.addMouseListener(this);
            labelPieza2.addMouseMotionListener(this);
            this.add(labelPieza2);

            labelPieza3=new JLabel();
            labelPieza3.setBounds(xImagen3, yImagen3,img3.getWidth(),img3.getHeight());
            labelPieza3.addMouseListener(this);
            labelPieza3.addMouseMotionListener(this);
            this.add(labelPieza3);

            labelPieza4=new JLabel();
            labelPieza4.setBounds(xImagen4,yImagen4,img4.getWidth(),img4.getHeight());
            labelPieza4.addMouseListener(this);
            labelPieza4.addMouseMotionListener(this);
            this.add(labelPieza4);
        

            labelPieza1.setIcon(icono1);
            labelPieza2.setIcon(icono2);
            labelPieza3.setIcon(icono3);
            labelPieza4.setIcon(icono4);
            
            
        }
        
        repaint();
        
    }
  
    /**
     * Metodo que devuelve la imagen entregada con los pixeles que tambien son contenidos por el poligono de color transparente
     * 
     * @param image imagen a la que los pixeles se les hacen transparentes para darle la forma de la pieza
     * @param p poligono el cual determina el area en que los poligonos de la imagen pasaran a ser transparentes
     * @return la imagen original con los pixeles que coincidian con el poligono transparentes
     */
    public BufferedImage CreadorPiezas(BufferedImage image, Polygon p) {
            
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
     * Recorta una imagen en los pixeles que son transparentes
     * 
     * @param image Imagen a recortar
     * @return Imagen recortada haciendola lo mas pequeña posible 
     */
    
    private static BufferedImage trimImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int top = height / 2;
        int bottom = top;
        int left = width / 2 ;
        int right = left;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (image.getRGB(x, y) != 0){
                    top    = Math.min(top, y);
                    bottom = Math.max(bottom, y);
                    left   = Math.min(left, x);
                    right  = Math.max(right, x);
                }
            }
        }
        return image.getSubimage(left, top, right - left + 1, bottom - top + 1);
    }
    
    
    /**
     * Metodo que rota la pieza seleccionada en -90 grados 
     */
    public void RotarIzq(){
      if(ModoDeJuego==1){   
        if(piezaSeleccionada==1 ){
            
            icono1 = new RotatedIcon(icono1,-90.0);
            labelPieza1.setIcon(icono1);
            repaint();
   
        }
        
        if(piezaSeleccionada==2){
            
            icono2 = new RotatedIcon(icono2,-90.0);
            labelPieza2.setIcon(icono2);
            repaint();
   
        }
        
        if(piezaSeleccionada==3){
            
            icono3 = new RotatedIcon(icono3,-90.0);
            labelPieza3.setIcon(icono3);
            repaint();
   
        }
        
        if(piezaSeleccionada==4){
            
            icono4 = new RotatedIcon(icono4,-90.0);
            labelPieza4.setIcon(icono4);
            repaint();
   
        }
      }
    }
    
    /**
     * Metodo que rota la pieza seleccionada en 90 grados 
     */
    public void RotarDer(){
      if(ModoDeJuego==1){   
        if(piezaSeleccionada==1 ){
            
            icono1 = new RotatedIcon(icono1,90.0);
            labelPieza1.setIcon(icono1);
            repaint();
   
        }
        
        if(piezaSeleccionada==2){
            
            icono2 = new RotatedIcon(icono2,90.0);
            labelPieza2.setIcon(icono2);
            repaint();
   
        }
        
        if(piezaSeleccionada==3){
            
            icono3 = new RotatedIcon(icono3,90.0);
            labelPieza3.setIcon(icono3);
            repaint();
   
        }
        
        if(piezaSeleccionada==4){
            
            icono4 = new RotatedIcon(icono4,90.0);
            labelPieza4.setIcon(icono4);
            repaint();
   
        }
      }  
        
    }
    
    
    /**
     * Metodo vacio que debe ser sobreescrito
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
        if(ModoDeJuego==1){     
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
    }
    /**
     * Metodo que arrastra la pieza
     * @param e mouseevent
     */
    @Override
    public void mouseDragged(MouseEvent e) {
      if(ModoDeJuego==1){   
                
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
      if(ModoDeJuego==1){   
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
    }

    /**
     * metodo para crear las lineas que separan las piezas
     * guardando los puntos de entrada de las lineas 
     * @param e mouseevent
     */
    @Override
    public void mouseEntered(MouseEvent e) {
      if(ModoDeJuego==0){   
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
    }

    /**
     * Metodo para guardar los puntos finales de las lineas
     * @param e mouseevent
     */
    @Override
    public void mouseExited(MouseEvent e) {
      if(ModoDeJuego==0){  
        
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
    
   
}