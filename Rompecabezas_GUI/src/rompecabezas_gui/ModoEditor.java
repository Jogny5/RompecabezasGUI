package rompecabezas_gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Modo para editar las piezas
 * @author Jorge
 * @author Diego
 */
public class ModoEditor extends JPanel{
    /**
     * Posicion x del primer punto
     */
    private int puntox1=0;
    /**
     * Posicion y del primer punto
     */
    private int puntoy1=0;
    /**
     * Posicion x del segundo punto
     */
    private int puntox2=0;
    /**
     * Posicion y del segundo punto
     */
    private int puntoy2=0;
    /**
     * Constructor del modo editor
     */
    public ModoEditor(){
        this.setLayout(null);
        this.setBackground(Color.white);
        this.setBounds(0,0,1000,700);
        
        Texto();
    }
    /**
     * Metodo paint
     * @param g paint
     */
    public void paint(Graphics g){ 
       g.setColor(Color.cyan);      
       g.fillRect(250,80,500,500);   
       
        
        
    }
    /**
     * Texto para saber que esta en el modo editor
     */
    public void Texto(){
        JLabel texto=new JLabel("Modo editor");
        texto.setBounds(350,100,150,40);
        this.add(texto);

    }
    /**
     * Guarda las coordenadas del primer punto
     * @param x1 Pasa a ser la coordenada x1 del punto
     * @param y1 Pasa a ser la coordenada y1 del punto
     */
    public void setPuntox1y1(int x1,int y1) {
        this.puntox1 = x1;
        this.puntoy1 = y1;
    }
    /**
     * Guarda las coordenadas del segundo punto
     * @param x2 Pasa a ser la coordenada x2 del punto
     * @param y2 Pasa a ser la coordenada y2 del punto
     */
    public void setPuntox2y2(int x2,int y2) {
        this.puntox2 = x2;
        this.puntoy2 = y2;
    }
    
}
