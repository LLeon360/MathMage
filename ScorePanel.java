/**
 * ScorePanel.java
 * 
 * Reads in scores from a text file
 * and displays the top scores in this
 * panel. Has buttons to allow the user
 * to return to the menu.
 * 
 * Extends ScrollingBGPanel to having a scrolling
 * background
 * 
 * @author Leon Liu
 * @since 4/8/20
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
public class ScorePanel extends ScrollingBGPanel implements ActionListener
{

	private JButton backButton;
	private ScoreHolderPanel[] top;
	private Game main;
	
	/**
	 * Creates a back button and an
	 * array of 10 JLabels for the top 10 
	 * scores.
	 * 
	 * Also keeps an alias of the main game
	 * to use the card layout and other functions.
	 */
	public ScorePanel(Game main) 
	{
		//Sets the scrolling background image
		super("ScoreBG.png");
		
		this.main = main;
		setLayout(new BorderLayout());
		JPanel scores = new JPanel();
		scores.setLayout(new GridLayout(10, 1, 0, 0));
		scores.setOpaque(false);
		scores.setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 5));
		top = new ScoreHolderPanel[10];
		for(int i = 0; i < top.length; i++)
		{
			ScoreHolderPanel label = new ScoreHolderPanel();
			top[i] = label;
			top[i].setRank((i+1) + ". ");
			scores.add(top[i]);
		}
		backButton = new JButton("BACK TO MENU");
		backButton.setPreferredSize(new Dimension(600, 55));
		backButton.setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 5));
		backButton.addActionListener(this);
		backButton.setFont(Game.NORMALFONT);
		add(scores, BorderLayout.CENTER);
		add(backButton, BorderLayout.SOUTH);
		setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 5));
		updateScores();
	}
	
	/**
	 * Reads scores from ScoreData.txt
	 * using Scanner and adds them to 
	 * the JLabels
	 */
	public void updateScores()
	{
		Scanner scoreReader = null;
		String filePath = "src/Data/ScoreData.txt";
		try
		{
			scoreReader = new Scanner(new File(filePath));
			for(int i = 0; i < 10 && scoreReader.hasNext(); i++)
			{
				top[i].setName(scoreReader.nextLine());
				top[i].setScore("Wave " + scoreReader.nextLine());
				top[i].setTime(main.getGame().convertTime(Integer.parseInt(scoreReader.nextLine())));
				top[i].setDifficulty(scoreReader.nextLine());
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e);
			System.exit(1);
		}
		scoreReader.close();
	}
	
	/**
	 * Returns to the menu panel if the back 
	 * button is pressed
	 */
	public void actionPerformed(ActionEvent e)
	{
		super.actionPerformed(e);
		if(e.getActionCommand().equals("BACK TO MENU"))
		{
			main.swap("menu");
		}
	}	
}
