package rompecabezas_gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ModoEditor extends JPanel{
    
    private int puntox1=0;
    private int puntoy1=0;
    private int puntox2=0;
    private int puntoy2=0;
    
    public ModoEditor(){
        this.setLayout(null);
        this.setBackground(Color.white);
        this.setBounds(0,0,1000,700);
        
        Texto();
    }
    
    public void paint(Graphics g){ 
       g.setColor(Color.cyan);      
       g.fillRect(250,80,500,500);   
       
        
        
    }
    
    public void Texto(){
        JLabel texto=new JLabel("Modo editor");
        texto.setBounds(350,100,150,40);
        this.add(texto);

    }
    
    public void setPuntox1y1(int x1,int y1) {
        this.puntox1 = x1;
        this.puntoy1 = y1;
    }

    public void setPuntox2y2(int x2,int y2) {
        this.puntox2 = x2;
        this.puntoy2 = y2;
    }
    
}
