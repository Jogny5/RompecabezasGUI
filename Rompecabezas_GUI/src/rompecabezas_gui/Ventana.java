package rompecabezas_gui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
/**
 * Clase jframe donde ocurre el rompecabezas
 * @author Jorge
 * @author Diego
 */
class Ventana extends JFrame{
   
    /**
     * Obtiene la instancia de panelprincipal desde el propio panel principal
     */
    private JPanel principal = PanelPrincipal.getInstancia();
 
    public Ventana(){
        super();
        setTitle("Rompecabezas"); 
        this.setLayout(new BorderLayout());
        this.add(principal);
        this.add(new PanelBotones(),BorderLayout.SOUTH);        
        this.setSize(1500,1000);              
        setLocationRelativeTo(null);                    
        this.getContentPane().setBackground(Color.white);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);       
        this.setVisible(true);
        
    }
      
}
