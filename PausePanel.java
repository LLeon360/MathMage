/**
 * PausePanel.java
 *  
 * Panel that shows up when the player
 * pauses the game and gives the player the option
 * to restart the run, return to the game session, or exit to
 * the menu             
 *  
 * @author Leon Liu
 * @since 5/15/20
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PausePanel extends JPanel implements ActionListener
{

	/**
	 * JLabels for the different character stats 
	 * and and alias of the main Game
	 */
	private JLabel scoreText, weaponText, healthText, speedText, damageText, weaponDamageText;
	private Game main;
	
	
	/**
	 * Sets up the layout, fonts, borders
	 * and buttons
	 */
	public PausePanel(Game main) 
	{
		this.main = main;
		
		setLayout(new BorderLayout());
		JPanel statPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		statPanel.setLayout(new GridLayout(7, 1));
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 30));
		
		JLabel statTitle = new JLabel("Character Stats : ");
		scoreText = new JLabel();
		weaponText = new JLabel();
		healthText = new JLabel();
		speedText = new JLabel();
		damageText = new JLabel();
		weaponDamageText = new JLabel();
		
		statTitle.setFont(Game.SMALLERFONT);
		scoreText.setFont(Game.SMALLERFONT);
		weaponText.setFont(Game.SMALLERFONT);
		healthText.setFont(Game.SMALLERFONT);
		speedText.setFont(Game.SMALLERFONT);
		damageText.setFont(Game.SMALLERFONT);
		weaponDamageText.setFont(Game.SMALLERFONT);
		
		statPanel.add(statTitle);
		statPanel.add(scoreText);
		statPanel.add(weaponText);
		statPanel.add(healthText);
		statPanel.add(speedText);
		statPanel.add(damageText);
		statPanel.add(weaponDamageText);
		statPanel.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 10));
		statPanel.setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 5));
		statPanel.setBackground(new Color(230, 240, 240));
		
		JButton returnToGame = new JButton("Return to Game");
		JButton exitToMenu = new JButton("Exit to Menu");
		JButton restart = new JButton("Restart");
		returnToGame.addActionListener(this);
		exitToMenu.addActionListener(this);
		restart.addActionListener(this);
		returnToGame.setFont(Game.NORMALFONT);
		exitToMenu.setFont(Game.NORMALFONT);
		restart.setFont(Game.NORMALFONT);
		returnToGame.setPreferredSize(new Dimension(450, 100));
		exitToMenu.setPreferredSize(new Dimension(450, 100));
		restart.setPreferredSize(new Dimension(450, 100));
		returnToGame.setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 10));
		exitToMenu.setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 10));
		restart.setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 10));
		buttonPanel.add(returnToGame);
		buttonPanel.add(exitToMenu);
		buttonPanel.add(restart);
		buttonPanel.setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 5));
		buttonPanel.setBackground(new Color(160, 220, 220));
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1, 2));
		centerPanel.add(statPanel);
		centerPanel.add(buttonPanel);
	
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(200, 240, 240));
		titlePanel.setPreferredSize(new Dimension(1000, 100));
		JLabel titleText = new JLabel("Paused");
		titleText.setFont(Game.TITLEFONT);
		titlePanel.add(titleText);
		titlePanel.setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 5));
		
		add(titlePanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 5));
	}
	
	/**
	 * Updates the text fields to
	 * reflect the latest stat changes
	 */
	public void updateStats()
	{
		PlayerObject player = main.getGame().getPlayer();
		scoreText.setText("Wave : " + main.getGame().getWave());
		weaponText.setText("Weapon : " + player.getWeapon().getName());
		healthText.setText("Health : " + player.getHealth() + "/" + player.getMaxHealth());
		speedText.setText("Speed : " + player.getSpeed());
		damageText.setText("Bonus Damage : " + player.getDamage());
		weaponDamageText.setText("Weapon Damage : " + player.getWeapon().getBaseDamage());
	}

	
	/**
	 * Responds to button presses and swaps the card layout
	 * to the relevant panel
	 */
	public void actionPerformed(ActionEvent e) 
	{
		switch(e.getActionCommand())
		{
		case "Return to Game":
			main.swap("game");
			break;
		case "Exit to Menu":
			main.swap("menu");
			break;
		case "Restart":
			main.swap("game");
			main.getGame().setup(main.getGame().getDifficulty());//setups another game with the same difficulty          
			break;
		}
	}


}
