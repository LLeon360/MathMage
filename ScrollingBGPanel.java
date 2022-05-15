/**
 * ScrollingBGPanel.java
 * 
 * JPanel with a scrolling background effect
 * created by using 4 images that are repeated over and
 * over by resetting the x and y once they reach the corner
 * of the panel to give the illusion of wrapparound.
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ScrollingBGPanel extends JPanel implements ActionListener
{
	/**
	 * bg is the background image that scrolls. Should be 1000 x 600 as 
	 * to cover the entire screen
	 * 
	 * bgX and bgY are the coordinates of the center image that all the other images
	 * are drawn relative to. They increment to give a scrolling effect
	 * 
	 * bgTimer is the timer that updates to move the background bu incrementing
	 * coordinates
	 */
	private Image bg;
	private int bgX, bgY;
	private Timer bgTimer;

	/**
	 * Uses toolkit to get the image. Sets up the timer and sets
	 * default values to bgX and bgY to start at screen top right corner
	 */
	public ScrollingBGPanel(String bgFile) 
	{
		bg = Toolkit.getDefaultToolkit().getImage("src/BG/"+bgFile);

		bgTimer = new Timer(15, this);
		bgTimer.start();
		bgTimer.setActionCommand("BG Shift");

		bgX = bgY = 0;
	}

	/**
	 * Draws the center image and then draws 1 above
	 * 1 to the left then 1 diagonally up and right
	 * so that when the image moves there will be images behind
	 * it to cover up
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(bg, bgX, bgY, this);
		g.drawImage(bg, bgX-1000, bgY, this);
		g.drawImage(bg, bgX, bgY-600, this);
		g.drawImage(bg, bgX-1000, bgY-600, this);
	}

	/**
	 * Increments the x and y to move the background
	 * and resets them if either reach the corner of the screen
	 */
	public void actionPerformed(ActionEvent e) 
	{
		String cmd = e.getActionCommand();
		if(cmd.equals("BG Shift"))
		{
			bgX+=2;
			bgY++;
			if(bgX > 1000)
			{
				bgX = 0;
			}
			if(bgY > 600)
			{
				bgY = 0;
			}
			repaint();
		}

	}
	
	/**
	 * Setter to change the background image with 
	 * the image name as parameter
	 */
	public void setBG(String bgFile)
	{
		bg = Toolkit.getDefaultToolkit().getImage("src/BG/"+bgFile);
	}

}
