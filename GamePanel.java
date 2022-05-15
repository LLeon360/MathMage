/**
 * GamePanel.java
 * 
 * Runs the main game by drawing all the
 * sprites in paintcomponent and has a main timer
 * which updates all game objects. Also passes the necessary
 * parameters from player input.
 * 
 * Game includes fighting off waves of enemies then
 * swapping to the math panel to ask the user a math
 * question
 * 
 *   *INSPIRATION: Top down shooter gameplay inspired by 
 *    Enter the Gungeon by Dodge Roll
 * 
 * @author Leon Liu
 * @since 4/6/20
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements MouseMotionListener, MouseListener, KeyListener, ActionListener, FocusListener
{
	/**
	 * Field variables:
	 * 
	 * keyCodeSet is an array with all the active keys which are used
	 * by GameObjects and allows user to input multiple keys at the same
	 * time
	 * 
	 * objects is an array of all objects created which are drawn
	 * 
	 * player is the instance of PlayerObject which the player controls using
	 * keys
	 * 
	 * updater is the main timer which calls the update function of all gameobjects
	 * and redraws the panel
	 * 
	 * time is the number of updates
	 * 
	 * main is the class containing the card layout which manages the panels
	 * 
	 * mx, my is mouse x and mouse y
	 * 
	 * leftMouseDown is true if the left mouse button is pressed
	 * 
	 * wave is the number of waves of enemies
	 */
	private int[] keyCodeSet;
	private GameObject[] objects;
	private PlayerObject player;
	private Timer updater;
	private int time;
	private Game main;
	private int mX,mY;
	private boolean leftMouseDown;
	private int wave;
	private GameObject healthBar;
	private HealthBar healthBarContent;
	private int enemyCount;
	private int difficulty;
	private String bossName;
	private int bossIntroTime;
	
	/**
	 * Sets up the main timer
	 * player is instantiated with coordinates of the middle of the screen
	 * sets default values for the arrays and field variables
	 * Stores an alias of the main game manager
	 */
	public GamePanel(Game main)
	{
		keyCodeSet = new int[0];

		setBackground(new Color(200, 235, 180));
		setFocusable(true);
		
		this.main = main;

		updater = new Timer(1, this);
		updater.setActionCommand("Main Timer");
		objects = new GameObject[0];

		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		addFocusListener(this);

		mX = mY = -1;

		enemyCount = -1;

		leftMouseDown = false;

		healthBar = new GameObject(this, 20, 20, "HealthBarContainer.png");
		healthBarContent = new HealthBar(this, 44, 25);
		removeObj(healthBar); //removes these because they are UI and to be drawn afterwords above all game objects
		removeObj(healthBarContent);

		player = new PlayerObject(this,main.getWidth()/2, main.getHeight()/2, 15);
	}

	/**
	 * Starts the update timer and with
	 * it the game
	 */
	public void start()
	{
		updater.start();
	}

	/**
	 * Stops the update timer and pauses
	 * the game
	 * 
	 * Also clears the keyCodeSet so that key presses
	 * do not persist while the panel switches where
	 * it is unknown if the key is released
	 */
	public void stop()
	{
		updater.stop();
		keyCodeSet = new int[0];
	}

	/**
	 * Draws the game objects and 
	 * then draws UI components seperately
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		drawSprites(g);
		
		g.drawImage(healthBar.getSprite(), (int)healthBar.getX(), (int)healthBar.getY(), this);
		g.drawImage(healthBarContent.getSprite(), (int)healthBarContent.getX(), (int)healthBarContent.getY(), healthBarContent.getWidth(), healthBarContent.getHeight(), this);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Monospaced", Font.BOLD, 10));
		g.drawString(""+player.getHealth()+"/"+player.getMaxHealth(), 44, 38);
		g.setColor(Color.BLACK);
		g.drawRect(mX-3, mY-3, 6, 6);

		g.setFont(Game.SMALLFONT);
		g.drawString("Wave: "+wave, 20, 100);
		g.drawString("Time: "+convertTime(time), 20, 150);
		
		/**
		 * Shows the intro text for a short amount of time
		 */
		if(bossIntroTime + 1200 > time)
		{
			String text = "BOSS MONSTER INCOMING!: " + bossName;
			int xOffset = getWidth()/2 - text.length()/2 * 15; // Centers the text with a couple magic numbers
			int yOffset = (bossIntroTime+1200-time)/4;  // Makes the text rise up
			g.setFont(new Font("Monospaced", Font.BOLD, 25));
			
			/**
			 * Black border around the white text
			 */
			g.setColor(Color.BLACK);
			g.drawString(text, xOffset+2, yOffset);
			g.drawString(text, xOffset-2, yOffset);
			g.drawString(text, xOffset, yOffset+2);
			g.drawString(text, xOffset, yOffset-2);
			g.drawString(text, xOffset+2, yOffset-2);
			g.drawString(text, xOffset-2, yOffset-2);
			g.drawString(text, xOffset+2, yOffset+2);
			g.drawString(text, xOffset-2, yOffset+2);
			

			g.setColor(Color.WHITE);
			g.drawString(text, xOffset, yOffset);
		}
	}

	/**
	 * Draws all game objects by using getters
	 * to get the image and x y coordinates and
	 * also centers it using the dimensions
	 * Waves and casts the x and y coordinates
	 * because they are saved as doubles for
	 * accuracy
	 */
	public void drawSprites(Graphics g)
	{
		for(int i = 0; i < objects.length; i++)
		{
			GameObject obj = objects[i];
			g.drawImage(obj.getSprite(), (int)(Math.round(obj.getX())-obj.getWidth()/2), (int)(Math.round(obj.getY())-obj.getHeight()/2), this);
		}
	}

	public void keyPressed(KeyEvent e) 
	{
		addKey(e.getKeyCode());
		if(e.getKeyChar() == 'p' || e.getKeyChar() == 'P')
		{
			main.swap("pause");
		}
	}
	public void keyReleased(KeyEvent e)
	{
		removeKey(e.getKeyCode());
	}
	public void keyTyped(KeyEvent e) 
	{
	}
	public void mouseClicked(MouseEvent e) 
	{
	}
	public void mouseEntered(MouseEvent e) 
	{
	}
	public void mouseExited(MouseEvent e) 
	{
	}
	public void mousePressed(MouseEvent e) 
	{
		if(e.getButton() == 1) //left button
			leftMouseDown = true;
	}
	public void mouseReleased(MouseEvent e)
	{
		if(e.getButton() == 1) //left button
			leftMouseDown = false;
	}

	/**
	 * Updates the mouse position variables whenever
	 * the mouse moves
	 */
	public void mouseDragged(MouseEvent e)
	{
		mX = e.getX();
		mY = e.getY();
	}
	public void mouseMoved(MouseEvent e) 
	{
		mX = e.getX();
		mY = e.getY();
	}

	/**
	 * Repaints and requests focus every time
	 * the main timer updates
	 * Also calls the update function of all game
	 * objects using a for-each loop
	 * 
	 * Starts the next wave if there are no enemies
	 * left and clears all the projectiles on screen
	 * so that the player isn't damaged from them after
	 * the round ends
	 * 
	 * The player is then returned to the center of the
	 * screen
	 */
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand().equals("Main Timer"))
		{
			time++;
			requestFocus();
			repaint();
			GameObject obj;
			for(int i = 0; i < objects.length; i++)
			{
				obj = objects[i];
				obj.update();
			}

			if(enemyCount <= 0)
			{
				clearProjectiles();
				nextWave();
				player.setX(getWidth()/2);
				player.setY(getHeight()/2);
			}
		}
	}

	/**
	 * Resets the enemy count and spawns 
	 * enemies at the corners of the screen
	 * with slight offsets to prevent stacking.
	 * Increments the enemy count with every
	 * spawn. Increments the wave.
	 * Different enemy types have different
	 * intervals that they increase by every wave
	 * and they spawn in specified waves.
	 * 
	 * Swaps to the math panel inbetween waves
	 * 
	 * At Wave 31 the game ends
	 */
	public void nextWave()
	{

		enemyCount = 0;
		if(wave < 9)
		{
			for(int i = 0; i < wave+8; i+=3)  //Skeletons: Start: Count: 4 Wave 1, Increase: 1 per 3 wave(s) until wave 10
			{
				new Skeleton(this, (int)(Math.random()*2)*900+Math.random()*100-50, (int)(Math.random()*2)*500+Math.random()*100-50);
			}
			for(int i = 2; i < wave; i+=4) //Zombies: Start: Count: 1 Wave 3, Increase: 1 per 4 wave(s) until wave 10
			{
				new Zombie(this, (int)(Math.random()*2)*900+Math.random()*100-50, (int)(Math.random()*2)*500+Math.random()*100-50);
			}
			for(int i = 4; i < wave; i+=3) //Bats: Start: Count: 1 Wave 5, Increase: 1 per 3 wave(s) until wave 10
			{
				new Bat(this, (int)(Math.random()*2)*900+Math.random()*100-50, (int)(Math.random()*2)*500+Math.random()*100-50);
			}
		}
		else if(wave == 9)
		{
			new Scarecrow(this, getWidth()/2, getHeight()/2 + 200); //Scarecrow Boss
			bossName = "The Scarecrow";
			bossIntroTime = time; //sets the time when the boss appears to time the boss text
		}
		else if(wave < 19)
		{
			for(int i = 9; i < wave; i+=2)  //Skeletons: Count: 1 Start: Wave 10, Increase: 1 per 2 wave(s) until wave 20	
			{
				new Skeleton(this, (int)(Math.random()*2)*900+Math.random()*100-50, (int)(Math.random()*2)*500+Math.random()*100-50);
			}
			for(int i = 9; i < wave; i+=4) //Zombies: Count: 1 Start: Wave 10, Increase: 1 per 4 wave(s) until wave 20
			{
				new Zombie(this, (int)(Math.random()*2)*900+Math.random()*100-50, (int)(Math.random()*2)*500+Math.random()*100-50);
			}
			for(int i = 9; i < wave; i+=4)//Giant Spider: Count: 1 Start: Wave 10, Increase: 1 per 4 wave(s) until wave 20
			{
				new GiantSpider(this, (int)(Math.random()*2)*900+Math.random()*100-50, (int)(Math.random()*2)*500+Math.random()*100-50);
			}
			for(int i = 11; i < wave; i+=4)//Bats: Count: 1 Start: Wave 12, Increase: 1 per 4 wave(s) until wave 20
			{
				new Bat(this, (int)(Math.random()*2)*900+Math.random()*100-50, (int)(Math.random()*2)*500+Math.random()*100-50);
			}
			for(int i = 16; i < wave; i+=3) //Ghosts: Count: 1 Start: Wave 17, Increase: 1 per 3 wave(s) until wave 20
			{
				new Ghost(this, (int)(Math.random()*2)*900+Math.random()*100-50, (int)(Math.random()*2)*500+Math.random()*100-50);
			}
		}else if(wave == 19)
		{
			new Rook(this, getWidth()/2, getHeight()/2 + 200); //Rook Boss
			bossName = "The Rook";
			bossIntroTime = time; //sets the time when the boss appears
		}
		else if(wave < 29)
		{
			for(int i = 19; i < wave; i+=2) //Zombies: Count: 1 Start: Wave 20, Increase: 1 per 2 wave(s) until wave 30
			{
				new Zombie(this, (int)(Math.random()*2)*900+Math.random()*100-50, (int)(Math.random()*2)*500+Math.random()*100-50);
			}
			for(int i = 19; i < wave; i+=2) //Bats: Count: 1 Start: Wave 20, Increase: 1 per 2 wave(s) until wave 30
			{
				new Bat(this, (int)(Math.random()*2)*900+Math.random()*100-50, (int)(Math.random()*2)*500+Math.random()*100-50);
			}
			for(int i = 20; i < wave; i+=4) //Giant Spiders: Count: 1 Start: Wave 21, Increase: 1 per 4 wave(s) until wave 30
			{
				new GiantSpider(this, (int)(Math.random()*2)*900+Math.random()*100-50, (int)(Math.random()*2)*500+Math.random()*100-50);
			}
			for(int i = 22; i < wave; i+=5) //Ghosts: Count: 1 Start: Wave 23, Increase: 1 per 5 wave(s) until wave 30
			{
				new Ghost(this, (int)(Math.random()*2)*900+Math.random()*100-50, (int)(Math.random()*2)*500+Math.random()*100-50);
			}
			for(int i = 19; i < wave; i+=3) //Willow Wisps: Count: 1 Start: Wave 20, Increase: 1 per 3 wave(s) until wave 30
			{
				new WillowWisp(this, (int)(Math.random()*800)+100, (int)(Math.random()*400)+100);
			}
			for(int i = 21; i < wave; i+=4)//Gravestones: Count: 1 Start: Wave 22, Increase: 1 per 4 wave(s) until wave 30
			{
				new Gravestone(this, (int)(Math.random()*800)+100, (int)(Math.random()*400)+100);
			}
		}
		else if(wave == 29)
		{
			new Cyclops(this, getWidth()/2, getHeight()/2 + 200); //Cyclops Boss
			bossName = "The Cyclops";
			bossIntroTime = time; //sets the time when the boss appears
		}
		else if(wave >= 30)
		{
			GameOverPanel gameOverPanel = main.getGameOverPanel();
			gameOverPanel.setup(true); //Switches to the game over panel and passes "true" for completed the game or not
			main.swap("game over");
		}
		wave++;

		if(wave != 1 && wave < 31)
		{
			main.swap("math");
		}
	}

	/**
	 * Swaps the panel to the end screen
	 * for a loss
	 */
	public void gameOver()
	{
		GameOverPanel gameOverPanel = main.getGameOverPanel();
		gameOverPanel.setup(false);
		main.swap("game over");
	}

	/**
	 * Adds objects to the object array
	 * 
	 * Edits enemyCount if it is an enemy
	 */
	public void addObj(GameObject obj)
	{
		GameObject[] temp = new GameObject[objects.length + 1];
		for(int i = 0; i < objects.length; i++)
		{
			temp[i] = objects[i];
		}
		temp[temp.length -1] = obj;
		objects = temp;

		if(obj instanceof DamageableObject && !(obj instanceof PlayerObject))
			enemyCount++;
	}
	/**
	 * Removes objects from the object array
	 * 
	 * Edits enemyCount if it was an enemy
	 */
	public void removeObj(GameObject obj)
	{
		GameObject[] temp = new GameObject[objects.length - 1];
		int count = 0;
		for(int i = 0; i < objects.length; i++)
		{
			if(objects[i] != obj)
			{
				temp[count] = objects[i];
				count++;
			}
		}
		objects = temp;

		if(obj instanceof DamageableObject)
			enemyDown();
	}

	/**
	 * Returns whether a keyCode is already in
	 * the keyCode array using a basic scan of 
	 * the array
	 */
	public boolean keyActive(int keyCode)
	{
		for(int i = 0; i < keyCodeSet.length; i++)
		{
			if(keyCode == keyCodeSet[i])
				return true;
		}
		return false;
	}

	/**
	 * Adds a keyCode to the keyCode array if
	 * it doesn't already exist in the array
	 */
	public void addKey(int keyCode)
	{
		if(!keyActive(keyCode))
		{
			int[] temp = new int[keyCodeSet.length + 1];
			for(int i = 0; i < keyCodeSet.length; i++)
			{
				temp[i] = keyCodeSet[i];
			}
			temp[temp.length -1] = keyCode;
			keyCodeSet = temp;
		}
	}

	/**
	 * Removes a keyCode from the array
	 */
	public void removeKey(int keyCode)
	{
		if(keyActive(keyCode))
		{
			int[] temp = new int[keyCodeSet.length - 1];
			int count = 0;
			for(int i = 0; i < keyCodeSet.length; i++)
			{
				if(keyCodeSet[i] != keyCode)
				{
					temp[count] = keyCodeSet[i];
					count++;
				}
			}
			keyCodeSet = temp;
		}
	}

	/**
	 * Clears the object list and resets
	 * the time, enemyCount, and wave count.
	 * Sets up the player and also determines
	 * starting health based on selected difficulty
	 * provided by the MenuPanel
	 * 
	 * Resets the player damage and other stats within the reset() method of
	 * the PlayerObject.
	 */
	public void setup(int difficulty)
	{
		enemyCount = 0;
		objects = new GameObject[0];
		addObj(player);
		int startHealth = 0;
		this.difficulty = difficulty;
		switch(difficulty)
		{
		case 1:
			startHealth = 70;
			break;
		case 2:
			startHealth = 30;
			break;
		case 3:
			startHealth = 20;
			break;
		}
		//resets player stats
		player.reset(startHealth);
		
		//resets game variables
		time = 0;
		wave = 0;
		
		bossIntroTime = -99999;
		
		requestFocus();
	}

	/**
	 * Returns the number of elapsed
	 * timer updates
	 */
	public int getTime()
	{
		return time;
	}

	/**
	 * Getters for mouse x and y
	 */

	public int getMX() 
	{
		return mX;
	}

	public int getMY() 
	{
		return mY;
	}

	public boolean isLeftMouseDown()
	{
		return leftMouseDown;
	}

	public PlayerObject getPlayer()
	{
		return player;
	}

	public GameObject[] getObjList() 
	{
		return objects;
	}

	/*
	 * Returns the wave number
	 */
	public int getWave()
	{
		return wave;
	}

	/**
	 * Reduces the enemy count by
	 * 1 when an enemy is defeated
	 */
	public void enemyDown()
	{
		enemyCount--;
	}

	/**
	 * Clears all projectiles from the screen
	 */
	public void clearProjectiles()
	{
		int projCount = 0;
		for(int i = 0; i < objects.length; i++)
		{
			if(objects[i] instanceof Projectile)
			{
				projCount++;
			}
		}
		GameObject[] temp = new GameObject[objects.length - projCount];
		int index = 0;
		for(int i = 0; i < objects.length; i++)
		{
			if(!(objects[i] instanceof Projectile))
			{
				temp[index] = objects[i];
				index++;
			}
		}
		objects = temp;
	}

	/**
	 * Converts the time in milliseconds 
	 * to minutes:seconds.centiseconds like
	 * a stopwatch format
	 */
	public String convertTime(int totalMS)
	{
		int minutes = totalMS/60000;
		int seconds = totalMS%60000/1000;
		int centisecond = totalMS%1000/10;
		return ""+minutes+":"+seconds+"."+centisecond;
	}

	/**
	 * Returns the difficulty
	 */
	public int getDifficulty()
	{
		return difficulty;
	}

	
	public void focusGained(FocusEvent e) 
	{
		
	}

	public void focusLost(FocusEvent e) 
	{
	}
}
