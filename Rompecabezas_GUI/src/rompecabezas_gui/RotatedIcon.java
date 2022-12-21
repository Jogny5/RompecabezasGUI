package rompecabezas_gui;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import javax.swing.Icon;

/**
 *  El icono rotado le permite cambiar la orientación de un icono
  * rotar el icono antes de pintarlo. Esta clase admite lo siguiente
  * orientaciones:
  *

 */
public class RotatedIcon implements Icon
{
        /**
        * DOWN - girado 90 grados
        * UP (defecto) - girado -90 grados
        * UPSIDE_DOWN - girado 180 grados
        * ABOUT_CENTER - el icono gira los grados especificados sobre su centro.
         */
	public enum Rotate
	{
		DOWN,
		UP,
		UPSIDE_DOWN,
		ABOUT_CENTER;
	}

	
        /**
         * Icono que contendra el icono entregado en constructor
         */
        private Icon icon;
        
        /**
         * Esta clase representa un objeto afín que gira las coordenadas alrededor de un punto de anclaje.
         */
	private Rotate rotate;
        
        /**
         * Grados a rotar
         */
	private double degrees;
        
        /**
         * Boolean para revisar si el icono es o no circular
         */
	private boolean circularIcon;


	/**
	 *  Crea un RotatedIcon
	 *
	 *  @param icon	Icono a rotar
	 *  @param rotate  Direccion de la rotacion
	 */
	public RotatedIcon(Icon icon, Rotate rotate)
	{
		this.icon = icon;
		this.rotate = rotate;
	}

	/**
        * Crear un icono rotado. El icono rotará sobre su centro. Este
        * el constructor establecerá automáticamente la enumeración Rotar en ABOUT_CENTER.
	 *
	 *  @param icon	Icono a rotar
	 *  @param degrees  Grado de la rotacion
	 */
	public RotatedIcon(Icon icon, double degrees)
	{
		this(icon, degrees, false);
	}

	/**
        * Crear un icono rotado. El icono rotará sobre su centro. Este
        * el constructor establecerá automáticamente la enumeración Rotar en ABOUT_CENTER.
	 *
	 *  @param icon	Icono a rotar
	 *  @param degrees   Grados de la rotacion
	 *  @param circularIcon trate el ícono como circular para que su tamaño no cambie
	 */
	public RotatedIcon(Icon icon, double degrees, boolean circularIcon)
	{
		this(icon, Rotate.ABOUT_CENTER);
		setDegrees( degrees );
		setCircularIcon( circularIcon );
	}

	/**
	 *  Obtiene el icono a rotar
	 *
	 *  @return Icono a rotar
	 */
	public Icon getIcon()
	{
		return icon;
	}

	/**
         * Obtiene la enumeración de Rotate que indica la dirección de rotación
	 *
	 *  @return La enumaracion de rotate
	 */
	public Rotate getRotate()
	{
		return rotate;
	}

	/**
         * Obtiene los grados de rotación. solo usado para Rotate.ABOUT_CENTER.
	 *
	 *  @return grados de la rotacion
	 */
	public double getDegrees()
	{
		return degrees;
	}

	/**
        * Establecer los grados de rotación. Solo se usa para Rotar.ABOUT_CENTER.
        * Este método solo establece el grado de rotación, no causará
        * el Icono para ser repintado. Debe invocar repaint() en cualquier
        * componente que usa este ícono para que sea repintado.
	 *
	 *  @param degrees grados de la rotacion
	 */
	public void setDegrees(double degrees)
	{
		this.degrees = degrees;
	}

	/**
        * ¿La imagen es circular o rectangular? Solo se usa para Rotar.ABOUT_CENTER.
        * Cuando es verdadero, el ancho/alto del icono no cambiará a medida que se gire el icono.
        *
        * @return verdadero para un icono circular, falso en caso contrario
	 */
	public boolean isCircularIcon()
	{
		return circularIcon;
	}

	/**
        * Establecer el icono como circular o rectangular. Solo se usa para Rotar.ABOUT_CENTER.
        * Cuando es verdadero, el ancho/alto del icono no cambiará a medida que se gire el icono.
        *
        * @param circularIcon verdadero para un icono circular, falso en caso contrario
	 */
	public void setCircularIcon(boolean circularIcon)
	{
		this.circularIcon = circularIcon;
	}

//
//  Implemante la interfaz Icon
//

	/**
	 *  Obtiene el ancho del icono
	 *
	 *  @return Ancho del icono en pixeles
	 */
	@Override
	public int getIconWidth()
	{
		if (rotate == Rotate.ABOUT_CENTER)
		{
			if (circularIcon)
				return icon.getIconWidth();
			else
			{
				double radians = Math.toRadians( degrees );
				double sin = Math.abs( Math.sin( radians ) );
				double cos = Math.abs( Math.cos( radians ) );
				int width = (int)Math.floor(icon.getIconWidth() * cos + icon.getIconHeight() * sin);
				return width;
			}
		}
		else if (rotate == Rotate.UPSIDE_DOWN)
			return icon.getIconWidth();
		else
			return icon.getIconHeight();
	}

	/**
	 *  Obtiene el alto del icono
	 *
	 *  @return Alto del icono en pixeles.
	 */
	@Override
	public int getIconHeight()
	{
		if (rotate == Rotate.ABOUT_CENTER)
		{
			if (circularIcon)
				return icon.getIconHeight();
			else
			{
				double radians = Math.toRadians( degrees );
				double sin = Math.abs( Math.sin( radians ) );
				double cos = Math.abs( Math.cos( radians ) );
				int height = (int)Math.floor(icon.getIconHeight() * cos + icon.getIconWidth() * sin);
				return height;
			}
		}
		else if (rotate == Rotate.UPSIDE_DOWN)
			return icon.getIconHeight();
		else
			return icon.getIconWidth();
	}

        /**
        * Pinta los íconos de este ícono compuesto en la ubicación especificada
        *
        * @param c El componente sobre el que se pinta el icono
        * @param g el contexto gráfico
        * @param x la coordenada X de la esquina superior izquierda del icono
        * @param y la coordenada Y de la esquina superior izquierda del icono
	*/
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y)
	{
		Graphics2D g2 = (Graphics2D)g.create();

		int cWidth = icon.getIconWidth() / 2;
		int cHeight = icon.getIconHeight() / 2;
		int xAdjustment = (icon.getIconWidth() % 2) == 0 ? 0 : -1;
		int yAdjustment = (icon.getIconHeight() % 2) == 0 ? 0 : -1;

		if (rotate == Rotate.DOWN)
		{
			g2.translate(x + cHeight, y + cWidth);
			g2.rotate( Math.toRadians( 90 ) );
			icon.paintIcon(c, g2,  -cWidth, yAdjustment - cHeight);
		}
		else if (rotate == Rotate.UP)
		{
			g2.translate(x + cHeight, y + cWidth);
			g2.rotate( Math.toRadians( -90 ) );
			icon.paintIcon(c, g2,  xAdjustment - cWidth, -cHeight);
		}
		else if (rotate == Rotate.UPSIDE_DOWN)
		{
			g2.translate(x + cWidth, y + cHeight);
			g2.rotate( Math.toRadians( 180 ) );
			icon.paintIcon(c, g2, xAdjustment - cWidth, yAdjustment - cHeight);
		}
		else if (rotate == Rotate.ABOUT_CENTER)
		{
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setClip(x, y, getIconWidth(), getIconHeight());
			g2.translate((getIconWidth() - icon.getIconWidth()) / 2, (getIconHeight() - icon.getIconHeight()) / 2);
			g2.rotate(Math.toRadians(degrees), x + cWidth, y + cHeight);
			icon.paintIcon(c, g2, x, y);
		}

		g2.dispose();
	}
}