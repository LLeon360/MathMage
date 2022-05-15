/**
 * MathPanel.java
 * 
 * Displays math problems
 * and has a timer countdown.
 * Allows user to use JButtons 
 * to select the correct answer.
 * This panel is swapped to inbetween
 * waves.
 * 
 * Questions are randomly selected and
 * the options are generated from an assortment
 * of answers which are read in from a data files
 * 
 * Uses FileIO to read in questions and answers
 * 
 * @author Leon Liu and Scott DeRuiter (FileIO Game Module) and layout is inspired by Kahoot
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
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MathPanel extends ScrollingBGPanel implements ActionListener
{

	/**
	 * main is alias of the Game instance
	 * 
	 * questions is the array of questions read in
	 * from the data file
	 * 
	 * answers is the array of answers read in
	 * from the same data file
	 * 
	 * results is alias of ResultsPanel alias
	 * which is swapped in to display correct
	 * or incorrect as well as rewards or punishments
	 */
	private Game main;
	
	private String[] questions;
	private String[] answers;
	
	private JLabel trigQuestion;
	private JButton[] options;
	
	private ResultsPanel results;
	
	/**
	 * Sets up the layout of the panel 
	 * using grid layouts
	 * 
	 * Stores a reference to the main Game.java
	 * and another to the results panel
	 */
	public MathPanel(Game main) 
	{
		super("MathBG.png");
		
		this.main = main;
		results = main.getResultsPanel();
		
		//Setting up the panel layout
		setLayout(new BorderLayout());
		
		JPanel questionPanel, answers;
		questionPanel = new JPanel(); 
		answers = new JPanel();
		questionPanel.setLayout(new GridLayout(2,1));
		answers.setLayout(new GridLayout(2,2));
		answers.setPreferredSize(new Dimension(1000, 300));
		answers.setOpaque(false);
		
		JPanel title = new JPanel();
		JLabel titleText = new JLabel("Math Trial");
		titleText.setFont(Game.TITLEFONT);
		title.add(titleText);
		title.setOpaque(false);
		JPanel trigQuestionPanel = new JPanel();
		trigQuestion = new JLabel("(insert question)");
		trigQuestion.setFont(Game.NORMALFONT);
		trigQuestionPanel.add(trigQuestion);
		trigQuestionPanel.setOpaque(false);
		
		questionPanel.add(title);
		questionPanel.add(trigQuestionPanel);
		questionPanel.setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 5));
		questionPanel.setOpaque(false);
		
		options = new JButton[4];
		for(int i = 0; i < options.length; i++)
		{
			options[i] = new JButton("Button "+i);
			answers.add(options[i]);
			options[i].addActionListener(this);
			options[i].setFont(Game.NORMALFONT);
			options[i].setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 5));
		}
		add(questionPanel, BorderLayout.CENTER);
		add(answers, BorderLayout.SOUTH);
		
		setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 5));
		
		//Fills in question and answer arrays using QuestionData.txt
		readQuestionFiles();
		
		//generating a question using random()
		genQuestion();
		
	}

	/**
	 * Randomly pulls a question
	 * from the questions array
	 * and randomizes the button
	 * with the correct answer and
	 * fills the rest with other 
	 * incorrect options
	 * 
	 * Avoids repeats
	 * 
	 * Because buttons are
	 * randomized the correct
	 * answers and wrong answers
	 * are distinct by their action
	 * command
	 */
	public void genQuestion()
	{
		for(int i = 0; i < options.length; i++)
		{
			options[i].setText("");
		}
		
		int questionNum = (int)(Math.random()*questions.length);
		trigQuestion.setText(questions[questionNum]);
		int correctButton = (int)(Math.random()*4);
		options[correctButton].setText(answers[questionNum]);
		options[correctButton].setActionCommand("Correct");
		
		results.setQuestion(questions[questionNum]);
		results.setAnswer(answers[questionNum]);
		
		for(int i = 0; i < options.length; i++)
		{
			if(i != correctButton)
			{
				boolean alreadyUsed = false;
				int randomOption;
				do
				{
					alreadyUsed = false;
					randomOption = (int)(Math.random()*answers.length);
					for(int j = 0; j < i; j++)//compares random pulled answer with existing button options to avoid repeats
					{
						if(answers[randomOption].equals(options[j].getText()))
						{
							alreadyUsed = true;
						}
					}
					if(answers[randomOption].equals(options[correctButton].getText())) //checks the correct answer button
					{
						alreadyUsed = true;
					}
				}
				while(alreadyUsed); 
				options[i].setText(answers[randomOption]);
				options[i].setActionCommand("Wrong");
			}
		}
		
	}
	
	/**
	 * Fills the question, answer, and answerPool
	 * by reading a text file in the format:
	 * question\n answer\n question\n answer\n etc...
	 */
	public void readQuestionFiles()
	{
		String questionAnswerFile = "src/Data/QuestionData.txt";
		
		Scanner read = null;
		try
		{
			read = new Scanner(new File(questionAnswerFile));
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e);
			System.exit(1);
		}
		
		int count = 0;
		while(read.hasNext())
		{
			count++;
			read.nextLine();
		}
		questions = new String[count/2];
		answers = new String[count/2];
		read.close();
		
		read = null;
		try
		{
			read = new Scanner(new File(questionAnswerFile));
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e);
			System.exit(1);
		}
		
		int index = 0;
		while(read.hasNext())
		{
			String question = read.nextLine();
			String answer = read.nextLine();
			questions[index] = question;
			answers[index] = answer;
			index++;
		}
		read.close();
	}
	
	/**
	 * Reacts to correct and incorrect button
	 * presses using a switch statement,
	 * eventually will also react to a
	 * countdown timer
	 */
	public void actionPerformed(ActionEvent e)
	{
		super.actionPerformed(e);
		switch(e.getActionCommand())
		{
		case "Correct":
			main.swap("results");
			results.setCorrect(true);
			genQuestion();
			break;
		case "Wrong":
			main.swap("results");
			results.setCorrect(false);
			genQuestion();
			break;
		default:
			break;
		}
	}
	
}