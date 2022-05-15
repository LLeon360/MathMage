/**
 * InfoPanel.java
 * 
 * Panel that holds instructions
 * for gameplay with buttons to
 * navigate through the pages.
 * Each page has an accompanying
 * tutorial image.
 * 
 * Uses FileIO to read in tutorial information and accompanying images
 * 
 * @author Leon Liu
 * @since 4/8/20
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InfoPanel extends JPanel implements ActionListener
{

	/**
	 * main is an alias of the Game instance
	 * 
	 * tutorialText is the large text area displaying
	 * the tutorial text based on the page index
	 * 
	 * index is the page index which decides which image
	 * and text is displayed
	 * 
	 * pageText is an array of tutorial text split
	 * by page number and read in from the data
	 * file
	 * 
	 * pageIndex is the text box that displays the page index
	 * and allows the user to edit it to skip to different pages
	 * 
	 * bottomHalf is the panel that displays images based on the page index
	 */
	private Game main;
	private JTextArea tutorialText;
	private int index;
	private String[] pageText;
	private JTextField pageIndex;
	private BackgroundPanel bottomHalf;
	
	/**
	 * Stores an alias of the main
	 * game.
	 * 
	 * Reads in the tutorial information into a String array
	 * from the data file
	 * 
	 * Setups the layout of the panel and important buttons and other components.
	 * 
	 * Sets fonts to the static fonts defined in the Game.java main class
	 * 
	 * Sets up buttons and text box with action commands for
	 * user navigation
	 */
	public InfoPanel(Game main) 
	{
		this.main = main; 
		
		setupText();//Reads in all the tutorial data from the TutorialData.txt file
		
		setLayout(new BorderLayout()); //Uses a border layout so that the panel completely fills the screen
		
		JPanel topHalf = new JPanel();
		bottomHalf = new BackgroundPanel("src/InfoScreens/1.png");
		/*
		 * the first tutorial image. tutorial images are named corresponding to the text index 
		 * so code implementation and swapping of images is easier
		 */
		
		topHalf.setLayout(new BorderLayout());
		
		JButton back = new JButton("<");
		back.setActionCommand("back");
		back.setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 5));
		back.setPreferredSize(new Dimension(80, 235));
		JButton next = new JButton(">");
		next.setActionCommand("next");
		next.setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 5));
		next.setPreferredSize(new Dimension(80, 235));
		tutorialText = new JTextArea();
		tutorialText.setFont(Game.NORMALFONT);
		tutorialText.setEditable(false);
		tutorialText.setLineWrap(true);
		tutorialText.setWrapStyleWord(true);
		tutorialText.setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 5));
		next.addActionListener(this);
		back.addActionListener(this);
		next.setFont(Game.TITLEFONT);
		back.setFont(Game.TITLEFONT);
		
		topHalf.add(back, BorderLayout.WEST);
		topHalf.add(tutorialText, BorderLayout.CENTER);
		topHalf.add(next, BorderLayout.EAST);
		
		JPanel pageDisplayPanel = new JPanel();
		pageDisplayPanel.setLayout(new FlowLayout());
		JLabel pageLabel1 = new JLabel("Page ");
		pageIndex = new JTextField(index);
		pageIndex.setPreferredSize(new Dimension(50, 40));
		JLabel pageLabel2 = new JLabel("/"+pageText.length);
		
		pageLabel1.setFont(Game.SMALLFONT);
		pageIndex.setFont(Game.SMALLFONT);
		pageLabel2.setFont(Game.SMALLFONT);
		//adds action listener and action command so user can jump to page using custom input
		pageIndex.addActionListener(this);
		pageIndex.setActionCommand("page index");
		
		pageDisplayPanel.add(pageLabel1);
		pageDisplayPanel.add(pageIndex);
		pageDisplayPanel.add(pageLabel2);
		pageDisplayPanel.setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 5));
		
		topHalf.add(pageDisplayPanel, BorderLayout.SOUTH);
		
		index = 0;
		tutorialText.setText(pageText[index]);
		pageIndex.setText(""+(index+1));
		
		JButton exitToMenu = new JButton("Return to Menu");
		exitToMenu.setPreferredSize(new Dimension(200, 30));
		exitToMenu.setFont(Game.SMALLFONT);
		exitToMenu.addActionListener(this);
		pageDisplayPanel.add(exitToMenu);
		pageDisplayPanel.setBackground(Color.LIGHT_GRAY);
		
		bottomHalf.setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 5));
		
		setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 5));
		
		bottomHalf.setBackground(Color.WHITE);
		add(topHalf, BorderLayout.NORTH);
		add(bottomHalf, BorderLayout.CENTER);
	}

	/**
	 * Gives back and next buttons functionality
	 * to navigate through the pages of the tutorial
	 * also works with wraparound action
	 * 
	 * Sets the page text and images based on the
	 * index in the array that changes based on button input or
	 * text box changes.
	 * 
	 * Also allows the user to enter a page to 
	 * jump to in the text box to quickly navigate.
	 * Uses try-catch to prevent error from invalid
	 * input of non-integers or out of bounds numbers
	 */
	public void actionPerformed(ActionEvent e) 
	{
		switch(e.getActionCommand())
		{
		case "back":
			if(index>0)
				index--;
			else
				index = pageText.length-1;
			break;
		case "next":
			if(index<pageText.length-1)
				index++;
			else
				index = 0;
			break;
		case "page index":
			try //tries to convert the user input in the text field to a number as the page index if possible 
			{
				int temp = Integer.parseInt(pageIndex.getText())-1;
				if(temp >= 0 && temp < pageText.length) // only changes index if given number is within valid bounds
					index = temp;
			}
			catch(NumberFormatException error)
			{}
			break;
		case "Return to Menu":
			main.swap("menu");
			break;
		default:
			System.out.print(e.getActionCommand() + " not used"); //prints out unused action command
			break;
		}
		
		tutorialText.setText(pageText[index]);
		bottomHalf.setImage("src/InfoScreens/"+(index+1)+".png"); //tutorial pages corresponding images are named (index).png for easy use
		//TODO not all images are completed at the moment but will work once added
		pageIndex.setText(""+(index+1));  
	}
	
	/**
	 * Reads in tutorial text from the TutorialData.txt
	 * file
	 * 
	 * Initializes the array by counting the pages defined in the
	 * text file using "---".
	 * Seperates different pages into different indexes of the
	 * pageText array. The pages are split up by "---" in the text file.
	 */
	public void setupText()
	{
		File data = new File("src/Data/TutorialData.txt");
		Scanner read = null;
		String allText = "";
		try
		{
			read = new Scanner(data);
		}
		catch(FileNotFoundException e)
		{
			System.out.print(e);
			System.exit(2);
		}
		
		do
		{
			allText += read.nextLine();
		}
		while(read.hasNext());
		
		int count = 0;
		
		for(int i = 0; i != -1; i = allText.indexOf("---", i+1))
		{
			count++;
		}
		
		pageText = new String[count];
		
		count = 0;
		for(int i = 0; i != -1; i = allText.indexOf("---", i+1))
		{
			int startIndex = i;
			if(i != 0)
				startIndex += 3;
			if(allText.indexOf("---", i+1) != -1)
				pageText[count] = allText.substring(startIndex, allText.indexOf("---", i+1));
			else
				pageText[count] = allText.substring(startIndex);
			count++;
		}
	}
}
