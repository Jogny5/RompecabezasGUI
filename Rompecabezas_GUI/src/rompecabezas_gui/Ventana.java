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
public class Ventana extends JFrame{
    /**
     * Crea una nueva instancia estatica a ventana
     */
    private static final Ventana instancia = new Ventana();
    /**
     * Obtiene la instancia de panelprincipal desde el propio panel principal
     */
    private JPanel principal = PanelPrincipal.getInstancia();
    /**
     * Crea una instancia a modo juego
     */
    private JPanel mjuego = new ModoJuego();
    /**
     * Crea una instancia a modo editor
     */
    private JPanel meditor = new ModoEditor();
    /**
     * Constructor de ventana
     */
    private Ventana(){
        super();
        setTitle("Rompecabezas"); 
        this.setLayout(new BorderLayout());
        this.add(principal);
        this.add(new PanelBotones(),BorderLayout.SOUTH);        
        this.setSize(1300,800);              
        setLocationRelativeTo(null);                    
        this.getContentPane().setBackground(Color.white);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);       
        this.setVisible(true);
        
    }
    /**
     * Devuelve una instancia de la ventana
     * @return instancia a la ventana
     */
    public static Ventana getInstancia(){
        
        return instancia;
    }
    /**
     * Funcion para poder cambiar a modo de juego en el panelprincipal
     */
    public void ModoJuego(){
        
        this.remove(principal);
        this.remove(meditor);
        this.add(mjuego);
        
        repaint();
    }
    /**
     * Funcion para poder cambiar a modo de editor en el panelprincipal
     */
    public void ModoEditor(){
        
        this.remove(principal);
        this.remove(mjuego);
        this.add(meditor);
        
        repaint();
    }
    
}
