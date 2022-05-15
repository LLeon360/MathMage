/**
 * BackgroundPanel.java
 * 
 * Draws a given image as a 
 * background
 * 
 * @author Leon Liu
 * @since 4/28/20
 */

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class BackgroundPanel extends JPanel 
{
	
	private Image bg;
	
	/**
	 * Sets the field variable image
	 * to the image at the given filepath
	 */
	public BackgroundPanel(String filepath) 
	{
		bg = Toolkit.getDefaultToolkit().getImage(filepath);
	}

	/**
	 * Draws the background of the panel and
	 * then the given image
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, this);
	}
	
	/**
	 * Sets the field variable image
	 * to the image at the given filepath
	 * and then repaints the panel to update 
	 * it.
	 */
	public void setImage(String filepath)
	{
		bg = Toolkit.getDefaultToolkit().getImage(filepath);
		repaint();
	}
	
}
