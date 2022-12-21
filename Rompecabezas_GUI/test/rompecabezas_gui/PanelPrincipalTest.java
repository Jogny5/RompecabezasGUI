/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rompecabezas_gui;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author diego
 */
public class PanelPrincipalTest {
    
    public PanelPrincipalTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getInstancia method, of class PanelPrincipal.
     */
    @Test
    public void testGetInstancia() {
        System.out.println("getInstancia");
        PanelPrincipal expResult = null;
        PanelPrincipal result = PanelPrincipal.getInstancia();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPiezaelegir method, of class PanelPrincipal.
     */
    @Test
    public void testSetPiezaelegir() {
        System.out.println("setPiezaelegir");
        int x = 0;
        PanelPrincipal instance = null;
        instance.setPiezaelegir(x);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of paint method, of class PanelPrincipal.
     */
    @Test
    public void testPaint() {
        System.out.println("paint");
        Graphics g = null;
        PanelPrincipal instance = null;
        instance.paint(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of EstadoEditor method, of class PanelPrincipal.
     */
    @Test
    public void testEstadoEditor() {
        System.out.println("EstadoEditor");
        PanelPrincipal instance = null;
        instance.EstadoEditor();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of EstadoJuego method, of class PanelPrincipal.
     */
    @Test
    public void testEstadoJuego() {
        System.out.println("EstadoJuego");
        PanelPrincipal instance = null;
        instance.EstadoJuego();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of interseccion method, of class PanelPrincipal.
     */
    @Test
    public void testInterseccion() {
        System.out.println("interseccion");
        Polygon linea1 = null;
        Polygon linea2 = null;
        PanelPrincipal instance = null;
        instance.interseccion(linea1, linea2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dividirPiezas method, of class PanelPrincipal.
     */
    @Test
    public void testDividirPiezas() {
        System.out.println("dividirPiezas");
        PanelPrincipal instance = null;
        instance.dividirPiezas();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of CreadorPiezas method, of class PanelPrincipal.
     */
    @Test
    public void testCreadorPiezas() {
        System.out.println("CreadorPiezas");
        BufferedImage image = null;
        Polygon p = null;
        PanelPrincipal instance = null;
        BufferedImage expResult = null;
        BufferedImage result = instance.CreadorPiezas(image, p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of RotarIzq method, of class PanelPrincipal.
     */
    @Test
    public void testRotarIzq() {
        System.out.println("RotarIzq");
        PanelPrincipal instance = null;
        instance.RotarIzq();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of RotarDer method, of class PanelPrincipal.
     */
    @Test
    public void testRotarDer() {
        System.out.println("RotarDer");
        PanelPrincipal instance = null;
        instance.RotarDer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseClicked method, of class PanelPrincipal.
     */
    @Test
    public void testMouseClicked() {
        System.out.println("mouseClicked");
        MouseEvent e = null;
        PanelPrincipal instance = null;
        instance.mouseClicked(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mousePressed method, of class PanelPrincipal.
     */
    @Test
    public void testMousePressed() {
        System.out.println("mousePressed");
        MouseEvent e = null;
        PanelPrincipal instance = null;
        instance.mousePressed(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseDragged method, of class PanelPrincipal.
     */
    @Test
    public void testMouseDragged() {
        System.out.println("mouseDragged");
        MouseEvent e = null;
        PanelPrincipal instance = null;
        instance.mouseDragged(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseMoved method, of class PanelPrincipal.
     */
    @Test
    public void testMouseMoved() {
        System.out.println("mouseMoved");
        MouseEvent e = null;
        PanelPrincipal instance = null;
        instance.mouseMoved(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseReleased method, of class PanelPrincipal.
     */
    @Test
    public void testMouseReleased() {
        System.out.println("mouseReleased");
        MouseEvent e = null;
        PanelPrincipal instance = null;
        instance.mouseReleased(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseEntered method, of class PanelPrincipal.
     */
    @Test
    public void testMouseEntered() {
        System.out.println("mouseEntered");
        MouseEvent e = null;
        PanelPrincipal instance = null;
        instance.mouseEntered(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseExited method, of class PanelPrincipal.
     */
    @Test
    public void testMouseExited() {
        System.out.println("mouseExited");
        MouseEvent e = null;
        PanelPrincipal instance = null;
        instance.mouseExited(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
