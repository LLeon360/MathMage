/**
 * GameObject.java
 * 
 * Class made for a template game object class
 * which other game objects inherit from.
 * Stores position and dimensions of its sprite
 * and the sprite itself. Also stores a reference to
 * the GamePanel in which it is instantiated in.
 * 
 * Adds itself as an action listener of the main 
 * update timer in the GamePanel which calls and update
 * function which doesn't do anything.
 * 
 *   *Inspired by GameObjects from Unity Game Engine 
 *    and general modular design
 * 
 * @author Leon Liu and Unity
 * @since 4/6/20
 */
import java.awt.Image;
import java.awt.Toolkit;

public class GameObject
{

	/**
	 * Field Variables which store information on position 
	 * and dimensions of the game object's sprite. Alias of
	 * main game panel is stored as well.
	 * 
	 * lifetime is the amount of time before
	 * the object deletes itself in ms only if
	 * hasLifetime is true
	 * 
	 * destroyed keeps track of if the object already
	 * called to be destroyed to make sure it doesn't
	 * call multiple times
	 */
	protected double x, y;
	private Image sprite;
	protected GamePanel game;
	protected String filepath;

	private boolean hasLifetime;
	private int lifetime;
	
	private boolean destroyed;
	
	/**
	 * Constructor to set field variables. Image
	 * is passed as a String filepath for ease of use.
	 * Every gameobject is also added to an array using addObj() method
	 * of GamePanel so that they are drawn in every update.
	 */
	public GameObject(GamePanel game, double x, double y, String imgSource) 
	{
		this.game = game;
		this.x = x;
		this.y = y;
		this.filepath = imgSource;
		setSprite(imgSource); //Uses src in file path because using Eclipse IDE
		game.addObj(this);
		hasLifetime = false;
		lifetime = -1;
		destroyed = false;
	}

	/**
	 * Removes this object from the object array
	 * in the GamePanel
	 */
	public void destroy()
	{
		if(!destroyed)
		{
			destroyed = true;
			game.removeObj(this);
		}
	}
	
	/**
	 * Checks if an game object collides with another game
	 * object.
	 */
	public boolean collides(GameObject obj)
	{
		return (y<=obj.getY()+(obj.getHeight()/2)+(getHeight())/2)
				&&y>=obj.getY()-(obj.getHeight()/2)-(getHeight()/2)
				&&(x<=obj.getX()+(obj.getWidth()/2)+(getWidth()/2)
				&&x>=obj.getX()-(obj.getWidth()/2)-(getWidth()/2));
	}
	
	/**
	 * Method which is called for every event
	 * coming from the main game timer. Will be 
	 * overridden to specialize for specific
	 * game objects' tasks. 
	 * If this object has a lifetime it will delete
	 * itself after it runs out
	 */
	public void update()
	{
		if(hasLifetime)
		{
			lifetime --;
			if(lifetime < 0)
			{
				destroy();
			}
		}
	}

	/**
	 * Getters and Setters for private/protected
	 * field variables below. Mostly used for drawing the objects.
	 */
	public double getX()
	{
		return x;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public double getY() 
	{
		return y;
	}

	public void setY(double y)
	{
		this.y = y;
	}

	public int getWidth()
	{
		return sprite.getWidth(game);
	}

	public int getHeight()
	{
		return sprite.getHeight(game);
	}

	public Image getSprite() 
	{
		return sprite;
	}

	//Setter uses string for filepath
	public void setSprite(String imgSource) 
	{
		sprite = Toolkit.getDefaultToolkit().getImage("src/Sprites/"+imgSource);
	}

	public GamePanel getGame()
	{
		return game;
	}

	public boolean hasLifetime() 
	{
		return hasLifetime;
	}

	public void setHasLifetime(boolean hasLifetime) 
	{
		this.hasLifetime = hasLifetime;
	}

	public int getLifetime() 
	{
		return lifetime;
	}

	public void setLifetime(int lifetime) 
	{
		this.lifetime = lifetime;
		hasLifetime = true;
	}
	
	
}
