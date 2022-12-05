package rompecabezas_gui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Ventana extends JFrame{
    
    private static final Ventana instancia = new Ventana();
    private JPanel principal = new PanelPrincipal();
    private JPanel mjuego = new ModoJuego();
    private JPanel meditor = new ModoEditor();
    
    private Ventana(){
        super();
        setTitle("Rompecabezas"); 
        this.setLayout(new BorderLayout());
        this.add(principal);
        this.add(new PanelBotones(),BorderLayout.SOUTH);        
        this.setSize(1000,800);              
        setLocationRelativeTo(null);                    
        this.getContentPane().setBackground(Color.white);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);       
        this.setVisible(true);
        
    }
    
    public static Ventana getInstancia(){
        
        return instancia;
    }
    
    public void ModoJuego(){
        
        this.remove(principal);
        this.remove(meditor);
        this.add(mjuego);
        
        repaint();
    }
    
    public void ModoEditor(){
        
        this.remove(principal);
        this.remove(mjuego);
        this.add(meditor);
        
        repaint();
    }
    
}
