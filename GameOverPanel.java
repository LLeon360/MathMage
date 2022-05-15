/**
 * GameOverPanel.java
 * 
 * Displays information on the ended
 * attempt and shows the record of the
 * best attempt.
 * 
 * @author Leon Liu and Scott DeRuiter (File Output Score Saving Module)
 * @since 5/7/20
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameOverPanel extends ScrollingBGPanel implements ActionListener
{

	/**
	 * main is alias of Game instance used to swap panels
	 * 
	 * titleText is the main text that says game over
	 * 
	 * scoreText displays your wave count
	 * 
	 * highScoreText displays the highest wave count
	 * 
	 * difficultyText displays the difficulty
	 * 
	 * highDifficultyText displays the difficulty of the attempt that got 
	 * to the highest wave count
	 * 
	 * timeText displays the total gameplay time elapsed in the attempt
	 * 
	 * highTimeText displays the total gameplay time elapsed in the best attempt
	 */
	private JLabel titleText, scoreText, highScoreText, difficultyText, highDifficultyText, timeText, highTimeText, highscoreNameText;
	private Game main;
	private JTextField enterName;
	private JCheckBox doSave;	

	/**
	 * Sets up the layout of the panel
	 * and sorts the score file by comparing 
	 * the scores
	 */
	public GameOverPanel(Game main) 
	{
		super("VictoryBG.png"); // sets the scrolling background but it will change when the panel is actually shown, changes based on setup()
		
		this.main = main;
		
		setLayout(new BorderLayout());
		titleText = new JLabel("");
		JPanel titleTextPanel = new JPanel();
		titleTextPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
		titleTextPanel.add(titleText);
		titleText.setFont(Game.TITLEFONT);
		
		JPanel gameResultsPanel = new JPanel();
		gameResultsPanel.setLayout(new BorderLayout());
		JPanel scoresPanel = new JPanel();
		scoresPanel.setLayout(new GridLayout(4,2));
		JLabel yourScoreText = new JLabel("Your Score Below:");
		scoreText = new JLabel("");
		highScoreText = new JLabel("");
		difficultyText = new JLabel("");
		highDifficultyText = new JLabel("");
		timeText = new JLabel("");
		highTimeText = new JLabel("");
		highscoreNameText = new JLabel("");
		
		yourScoreText.setFont(Game.NORMALFONT);
		highscoreNameText.setFont(Game.NORMALFONT);
		scoreText.setFont(Game.NORMALFONT);
		highScoreText.setFont(Game.NORMALFONT);
		difficultyText.setFont(Game.NORMALFONT);
		highDifficultyText.setFont(Game.NORMALFONT);
		timeText.setFont(Game.NORMALFONT);
		highTimeText.setFont(Game.NORMALFONT);
		
		scoresPanel.add(yourScoreText);
		scoresPanel.add(highscoreNameText);
		scoresPanel.add(scoreText);
		scoresPanel.add(highScoreText);
		scoresPanel.add(timeText);
		scoresPanel.add(highTimeText);
		scoresPanel.add(difficultyText);
		scoresPanel.add(highDifficultyText);
		
		JPanel saveQueryPanel = new JPanel();
		saveQueryPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		
		JLabel saveScore  = new JLabel("Save Score? ");
		doSave = new JCheckBox();
		doSave.setSize(new Dimension(50,50));
		JLabel nameLabel = new JLabel("Enter Name: ");
		enterName = new JTextField("Insert Name");
		saveScore.setFont(Game.NORMALFONT);
		nameLabel.setFont(Game.NORMALFONT);
		enterName.setFont(Game.NORMALFONT);
		
		saveQueryPanel.add(saveScore);
		saveQueryPanel.add(doSave);
		saveQueryPanel.add(nameLabel);
		saveQueryPanel.add(enterName);
		
		gameResultsPanel.add(scoresPanel, BorderLayout.CENTER);
		gameResultsPanel.add(saveQueryPanel, BorderLayout.SOUTH);
		
		JPanel returnMenuPanel = new JPanel();
		returnMenuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
		JButton returnMenu = new JButton("Return to Menu");
		returnMenu.setPreferredSize(new Dimension(300, 150));
		returnMenu.addActionListener(this);
		returnMenu.setFont(Game.NORMALFONT);
		returnMenuPanel.add(returnMenu);
		returnMenu.setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 10));
		
		add(gameResultsPanel,BorderLayout.CENTER);
		add(titleTextPanel, BorderLayout.NORTH);
		add(returnMenuPanel, BorderLayout.SOUTH);
		
		//Sets them transparent so the background shows through
		saveQueryPanel.setOpaque(false);
		scoresPanel.setOpaque(false);
		gameResultsPanel.setOpaque(false);
		titleTextPanel.setOpaque(false);
		returnMenuPanel.setOpaque(false);
		
		sortAndUpdateScores();

		setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 10));
	}

	/**
	 * Swaps to the game menu if the return to menu
	 * button is selected
	 */
	public void actionPerformed(ActionEvent e)
	{
		super.actionPerformed(e);
		switch(e.getActionCommand())
		{
		case "Return to Menu":
			main.swap("menu");
			if(doSave.isSelected())
				storeScore();
			break;
		}
	}
	
	/**
	 * Changes the message at the top of the screen 
	 * depending on the game state(completed or defeated
	 */
	public void setup(boolean completed)
	{
		GamePanel game = main.getGame();
		
		if(completed)
		{
			titleText.setText("Congrats! You've WON!");
			setBG("VictoryBG.png"); //green for victory
		}
		else
		{
			titleText.setText("Game Over");
			setBG("DefeatBG.png"); //red for defeat
		}
		
		enterName.setText("Insert Name"); //Empties the text field so that the previous name doesn't show
		int score = game.getWave();
		
		String difficulty = "";
		
		switch(game.getDifficulty())
		{
		case 1:
			difficulty = "Novice";
			break;
		case 2:
			difficulty = "Skilled";
			break;
		case 3:
			difficulty = "Master";
			break;
		}
		
		scoreText.setText("Wave : " + score);
		timeText.setText("Time : " + game.convertTime(game.getTime()));
		difficultyText.setText("Difficulty : " + difficulty);
		
		setHighScores();//reads in the highest score to put its side by side the current score
	}
	
	/**
	 * Stores the score by reading all the scores in the data file
	 * into an array of Score objects which stores the various lines
	 * as field variables and also creates a new Score object for the current
	 * score and adds that to the array.
	 * 
	 * Then the array is sorted and the file is rewritten in order
	 */
	public void storeScore()
	{
		/**
		 * scores are an array of individual score
		 * objects which contain all information pertaining
		 * to a saved score: name, wave, time, difficulty...
		 */
		Score[] scores;
		
		PrintWriter write = null;
		File output = new File("src/Data/ScoreData.txt");
		
		Scanner scoreReader = null;
		int count = 0;//counter for the lines to determine the array length
		try
		{
			scoreReader = new Scanner(output);
			
			while(scoreReader.hasNext())
			{
				count++;
				scoreReader.nextLine();
				//adds to the line count and moves the scanner index
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e);
			System.exit(1);
		}
		scoreReader.close();
		
		scores = new Score[count/4 + 1]; //divided by 4 because 1 score takes up 4 lines and adds 1 to include the new score
		
		scoreReader = null; //reopens the file to read in the values
		try
		{
			scoreReader = new Scanner(output);
			
			for(int i = 0; i < count/4; i++)
			{
				scores[i] = new Score();
				scores[i].setName(scoreReader.nextLine());
				scores[i].setScore(Integer.parseInt(scoreReader.nextLine()));
				scores[i].setTime(Integer.parseInt(scoreReader.nextLine()));
				int difficulty;
				switch(scoreReader.nextLine())
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
				scores[i].setDifficulty(difficulty);//uses switch statement to translate the difficulty into a int for comparison to sort the array
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e);
			System.exit(1);
		}
		scoreReader.close();
		
		GamePanel game = main.getGame();
		scores[scores.length-1] = new Score(enterName.getText(), game.getWave(), game.getTime(), game.getDifficulty()); // adds the recent score to the array as the last element
		
		/*
		 * After reading the scores into an array
		 * scorts it using selection sort
		 */
		selectionSortScore(scores);
		
		try
		{
			write = new PrintWriter(output);
		}
		catch(IOException e) 
		{
			System.out.println(e);
			System.exit(2);
		}
		
		for(int i = 0; i < scores.length; i++)
		{
			write.println(scores[i].getName());
			write.println(scores[i].getScore());
			write.println(scores[i].getTime());
			String difficulty = "";
			switch(scores[i].getDifficulty())
			{
			case 1:
				difficulty = "Novice";
				break;
			case 2:
				difficulty = "Skilled";
				break;
			case 3:
				difficulty = "Master";
				break;
			}
			write.println(difficulty);//converts int back into String
		}
		
		write.close();
	}
    
	/**
	 * Sorts and updates the scores without adding one
	 */
	public void sortAndUpdateScores()
	{
		/**
		 * scores are an array of individual score
		 * objects which contain all information pertaining
		 * to a saved score: name, wave, time, difficulty...
		 */
		Score[] scores;
		
		PrintWriter write = null;
		File output = new File("src/Data/ScoreData.txt");
		
		Scanner scoreReader = null;
		int count = 0;//counter for the lines to determine the array length
		try
		{
			scoreReader = new Scanner(output);
			
			while(scoreReader.hasNext())
			{
				count++;
				scoreReader.nextLine();
				//adds to the line count and moves the scanner index
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e);
			System.exit(1);
		}
		scoreReader.close();
		
		scores = new Score[count/4]; //divided by 4 because 1 score takes up 4 lines
		
		scoreReader = null; //reopens the file to read in the values
		try
		{
			scoreReader = new Scanner(output);
			
			for(int i = 0; i < count/4; i++)
			{
				scores[i] = new Score();
				scores[i].setName(scoreReader.nextLine());
				scores[i].setScore(Integer.parseInt(scoreReader.nextLine()));
				scores[i].setTime(Integer.parseInt(scoreReader.nextLine()));
				int difficulty;
				switch(scoreReader.nextLine())
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
				scores[i].setDifficulty(difficulty);//uses switch statement to translate the difficulty into a int for comparison to sort the array
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e);
			System.exit(1);
		}
		scoreReader.close();
		
		/*
		 * After reading the scores into an array
		 * scorts it using selection sort
		 */
		selectionSortScore(scores);
		
		try
		{
			write = new PrintWriter(output);
		}
		catch(IOException e) 
		{
			System.out.println(e);
			System.exit(2);
		}
		
		for(int i = 0; i < scores.length; i++)
		{
			write.println(scores[i].getName());
			write.println(scores[i].getScore());
			write.println(scores[i].getTime());
			String difficulty = "";
			switch(scores[i].getDifficulty())
			{
			case 1:
				difficulty = "Novice";
				break;
			case 2:
				difficulty = "Skilled";
				break;
			case 3:
				difficulty = "Master";
				break;
			}
			write.println(difficulty);//converts int back into String
		}
		
		write.close();
	}
	
	/**
	 * Uses a selection sort to sort a score array to sort from
	 * greatest to least so that in the text document, the scores at the top
	 * are the greatest
	 * 
	 * The sort algorithm:
	 * For each unsorted element, it iterates through the remainder elements
	 * of the array and finds the maximum value by comparing them(array[j]) to the original unsorted
	 * element(array[i]) and then swaps it with the original index(the unsorted element i)
	 */
	public void selectionSortScore(Score[] array) 
	{
	    for (int i = 0; i < array.length; i++) 
	    {
	    	Score max = array[i]; //sets the unsorted element to the max
	        int maxID = i;
	        for (int j = i+1; j < array.length; j++) 
	        {
	            if (array[j].compareTo(max)) //Score class has a compareTo method which compares the wave, then difficulty, then time to see which is greater & lesser
	            { 
	            	max = array[j]; //replaces the max if its not the max
	                maxID = j; //sets the maxIndex for later swapping of elements
	            }
	        }
	        
	        Score temp = array[i];//swaps the elements using a temp Score object
	        array[i] = max;
	        array[maxID] = temp;
	    }
	}
	
	/**
	 * Sorts the score list and then
	 * reads in the top score
	 */
	public void setHighScores()
	{
		sortAndUpdateScores();
		Scanner scoreReader = null;
		String filePath = "src/Data/ScoreData.txt";
		try
		{
			scoreReader = new Scanner(new File(filePath));
			if(scoreReader.hasNext())//reads the top score if there is one
			{
				highscoreNameText.setText("Highscore Holder : " + scoreReader.nextLine());
				highScoreText.setText("Wave : " + scoreReader.nextLine());
				highTimeText.setText("Time : " + main.getGame().convertTime(Integer.parseInt(scoreReader.nextLine())));
				highDifficultyText.setText("Difficulty : " + scoreReader.nextLine());
			}
			else //if there isn't top score, uses the current player's score instead
			{
				highscoreNameText.setText("Highscore Holder : " + "YOU! No scores yet!");
				highScoreText.setText(scoreText.getText());
				highTimeText.setText(timeText.getText());
				highDifficultyText.setText(difficultyText.getText());
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e);
			System.exit(1);
		}
		scoreReader.close();
	}
	
}
