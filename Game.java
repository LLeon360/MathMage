//5943 lines including comments and empty lines
/**
 * Game.java
 * 
 * Manages all the interactions between the 
 * panels and manages the CardLayout
 * Creates the Frame and runs everything
 * 
 * @author Leon Liu
 * @since 4/8/20
 */
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game 
{
	private JPanel current;
	private GamePanel game;
	private MenuPanel menu;
	private InfoPanel info;	
	private MathPanel math;
	private ScorePanel score;
	private ResultsPanel results;
	private GameOverPanel gameOver;
	private PausePanel pause;
	private CardLayout card;
	private final int WIDTH = 1000, HEIGHT = 625;
	
	private static String font = "Arial";
	public static Font TITLEFONT = new Font(font, Font.BOLD, 60),
						NORMALFONT = new Font(font, Font.BOLD, 27),
						SMALLERFONT = new Font(font, Font.BOLD, 22),
						SMALLFONT = new Font(font, Font.BOLD, 20);
	public static Color BORDER_COLOR = new Color(60, 60, 110); //Navy Blue
	
	/**
	 * Initializes the different panels
	 * and adds them to the current panel
	 * which uses a card layout.
	 * Then sets the current panel to the
	 * main menu.
	 */
	public Game() 
	{	
		card = new CardLayout();
		current = new JPanel();
		current.setLayout(card);

		gameOver = new GameOverPanel(this);
		game = new GamePanel(this);
		menu = new MenuPanel(this);
		info = new InfoPanel(this);
		results = new ResultsPanel(this);
		math = new MathPanel(this);
		score = new ScorePanel(this);
		pause = new PausePanel(this);
		current.add(game, "game");
		current.add(menu, "menu");
		current.add(info, "info");
		current.add(math, "math");
		current.add(score, "score");
		current.add(results, "results");
		current.add(gameOver, "game over");
		current.add(pause, "pause");
		swap("menu");
	}
	
	/**
	 * Creates a JFrame and sets it up.
	 * Then adds the main panel to the frame.
	 */
	public static void main(String[] args) 
	{
		Game mainGame = new Game();
		JFrame f = new JFrame("Math Mage");
		f.setVisible(true);
		f.setSize(mainGame.getWidth(), mainGame.getHeight());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(mainGame.getCurrent());
		f.setResizable(false);
	}
	
	/**
	 * Returns the width of the frame
	 */
	public int getWidth()
	{
		return WIDTH;
	}
	
	/**
	 * Returns the height of the frame
	 */
	public int getHeight()
	{
		return HEIGHT;
	}
	
	/**
	 * Returns the current panel
	 */
	public JPanel getCurrent()
	{
		return current;
	}
	
	/**
	 * Swaps panels using the
	 * string corresponding to the panel
	 * 
	 * Pauses the game if the game
	 * is not the shown panel
	 * 
	 * If necessary, updates the information
	 * on the panel
	 */
	public void swap(String panel)
	{
		card.show(current, panel);
		
		if(panel.equals("game"))
			game.start();
		else
			game.stop();
		if(panel.equals("score"))
			score.updateScores();
		else if(panel.equals("pause"))
			pause.updateStats();
	}
	
	/**
	 * Passes a reference to the game
	 * panel
	 */
	public GamePanel getGame()
	{
		return game;
	}
	
	/**
	 * Passes a reference to the
	 * results panel
	 */
	public ResultsPanel getResultsPanel()
	{
		return results;
	}
	
	/**
	 * Return a reference to the 
	 * game over panel
	 */
	public GameOverPanel getGameOverPanel()
	{
		return gameOver;
	}
	
	/**
	 * Updates the scores on the score panel
	 */
	public void updateScoreboard()
	{
		score.updateScores();
	}
}
