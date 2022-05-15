/**
 * MenuPanel.java
 * 
 * The first panel that appears when the game starts
 * and allows user to navigate to other panels using 
 * JButtons and set settings with other JComponents.
 * 
 * Extends the ScrollingBGPanel class to have a scrolling background
 * 
 * @author Leon Liu
 * @since 4/8/20
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MenuPanel extends ScrollingBGPanel implements ActionListener
{
	private JButton beginButton, tutorialButton, scoreButton;
	private JComboBox<String> difficultySelect;
	private Game main;
	
	/**
	 * Stores an alias of the main
	 * game.
	 * Sets the layout of the Menu and
	 * gives everything a border. Sets up fonts 
	 * and adds Action listeners and calls the superclass
	 * constructor to set the image for the scrolling 
	 * background
	 */
	public MenuPanel(Game main)
	{
		super("MenuBG.png"); // sets the scrolling background
		
		this.main = main;
		setLayout(new GridLayout(5, 1));
		
		Color borderColor = Game.BORDER_COLOR; //Navy Blue
		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
		JLabel title = new JLabel("Math Mage", JLabel.CENTER);
		title.setFont(Game.TITLEFONT);
		title.setBackground(Color.WHITE);
		title.setOpaque(true);
		title.setPreferredSize(new Dimension(635, 95));
		title.setBorder(BorderFactory.createLineBorder(borderColor, 10));
		titlePanel.add(title);
		titlePanel.setOpaque(false);
		add(titlePanel);
		
		JPanel difficultyPanel = new JPanel();
		difficultyPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		JLabel difficultyLabel = new JLabel("Difficulty  >");
		difficultyLabel.setFont(Game.NORMALFONT);
		difficultyLabel.setPreferredSize(new Dimension(180, 70));
		difficultyLabel.setBackground(Color.WHITE);
		difficultyLabel.setOpaque(true);
		difficultyLabel.setBorder(BorderFactory.createLineBorder(borderColor, 10));
		difficultyPanel.add(difficultyLabel);
		difficultySelect = new JComboBox<String>();
		difficultySelect.setPreferredSize(new Dimension(150,70));
		difficultySelect.addItem("Novice");
		difficultySelect.addItem("Skilled");
		difficultySelect.addItem("Master");
		difficultySelect.setBackground(Color.WHITE);
		difficultySelect.setFont(Game.NORMALFONT);
		difficultySelect.setBorder(BorderFactory.createLineBorder(borderColor, 10));
		difficultyPanel.add(difficultySelect);
		difficultyPanel.setOpaque(false);
		add(difficultyPanel);
		

		beginButton = new JButton("BEGIN");
		beginButton.setPreferredSize(new Dimension(335,70));
		beginButton.setBorder(BorderFactory.createLineBorder(borderColor, 10));
		tutorialButton = new JButton("TUTORIAL");
		tutorialButton.setPreferredSize(new Dimension(335,70));
		tutorialButton.setBorder(BorderFactory.createLineBorder(borderColor, 10));
		scoreButton = new JButton("SCORES");
		scoreButton.setPreferredSize(new Dimension(335,70));
		scoreButton.setBorder(BorderFactory.createLineBorder(borderColor, 10));
		
		beginButton.setFont(Game.NORMALFONT);
		tutorialButton.setFont(Game.NORMALFONT);
		scoreButton.setFont(Game.NORMALFONT);
		
		JPanel beginPanel = new JPanel();
		beginPanel.setOpaque(false);
		beginPanel.add(beginButton);
		beginButton.addActionListener(this);
		JPanel tutorialPanel = new JPanel();
		tutorialPanel.add(tutorialButton);
		tutorialButton.addActionListener(this);
		tutorialPanel.setOpaque(false);
		JPanel scoresPanel = new JPanel();
		scoresPanel.add(scoreButton);
		scoreButton.addActionListener(this);
		scoresPanel.setOpaque(false);
		
		add(beginPanel);
		add(tutorialPanel);
		add(scoresPanel);
	}

	/**
	 * Makes buttons go to corresponding panels
	 */
	public void actionPerformed(ActionEvent e)
	{
		super.actionPerformed(e); //Calls actionPerformed in superclass which relies on timer action event to scroll
		String cmd = e.getActionCommand();
		if(cmd.equals("BEGIN"))
		{
			int difficulty;
			switch((String)(difficultySelect.getSelectedItem()))
			{
			case "Novice":
				difficulty = 1;
				break;
			case "Skilled":
				difficulty = 2;
				break;
			case "Master":
				difficulty = 3;
				break;
			default:
				difficulty = 0;
				break;
			}
			main.getGame().setup(difficulty); //Passes a number based of selected difficulty string to the game panel to select the starting health of the player
			main.swap("game");
		}
		else if(cmd.equals("TUTORIAL"))
		{
			main.swap("info");
		}
		else if(cmd.equals("SCORES"))
		{
			main.swap("score");
		}
	}
	
	/**
	 * Calls the superclass paintComponenet to scroll
	 * the background and also draws name at the bottom in
	 * small font
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setFont(Game.NORMALFONT.deriveFont(15f));
		g.drawString("- Leon Liu :)", 870, 560);
	}
}
