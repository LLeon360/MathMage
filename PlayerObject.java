/**
 * PlayerObject.java
 * 
 * Extends the GameObject class and allows
 * player to control their character's movement
 * using the WASD keys.
 * 
 * @author Leon Liu
 * @since 4/6/20
 */

import java.awt.event.KeyEvent;

public class PlayerObject extends DamageableObject
{
	/**
	 * Field Variables:
	 * horizontalMove and verticalMove are the directional
	 * input for the WASD keys where WS make verticalMove 1 or -1 
	 * AD make horizontalMove 1 or -1 to determine which direction the player moves
	 * 
	 * speed is the scalar that the unit vector is multiplied by which determines
	 * the magnitude of displacement
	 * 
	 * Starts with the ManaSpell as a weapon
	 */
	private int horizontalMove, verticalMove;
	private double speed;
	private Weapon weapon;
	private double damage;
	
	/**
	 * Calls DamageableObject constructor
	 * and sets its sprite to the player sprite
	 * Sets default values to movement variables
	 * 
	 * Sets specific value to speed
	 * Sets the weapon to the starting ManaSpell weapon
	 * 
	 * Sets its health based on the GamePanel's instruction
	 * 
	 * Sets the tag to player because it is enemy by default
	 * which allows it to get hit by enemy projectiles and
	 * not by its own
	 */
	public PlayerObject(GamePanel game, double x, double y, int health) 
	{
		super(game, x, y, "Player.png");
		horizontalMove = verticalMove = 0;
		
		speed = adjustmentSpeed = 0.75;
		weapon = new ManaSpell(this);
		
		damage = 0;
		
		this.health = maxHealth = health;
		tag = "player";
	}
	
	/**
	 * Resets the player to the starting
	 * state
	 */
	public void reset(double health)
	{
		weapon = new ManaSpell(this);
		speed = adjustmentSpeed = 0.75;
		horizontalMove = verticalMove = 0;
		x=game.getWidth()/2;
		y=game.getHeight()/2;
		damage = 0;
		this.health = maxHealth = health;
	}
	
	/**
	 * Uses the keyActive method in the GamePanel
	 * to check if a key is pressed. Allows multiple
	 * keys to be inputed at once because they are stored
	 * in an active array instead of individual KeyEvents.
	 * 
	 * Moves the player with WASD by converting inputs into
	 * directional inputs and then a unit vector to make sure
	 * displacement is roughly equal in all directions
	 */
	public void update()
	{
		super.update();
		getDirectionalInput();
		
		//converts directional input into unit vector so that diagonal movement
		//isn't more advantageous
		UnitVector movement = new UnitVector(horizontalMove, verticalMove);
		x += movement.getX() * speed;
		y += movement.getY() * speed;
		
		//flips the player depending on the mouse position so that 
		//the player faces towards the cursor
		faceCursor();
		
		
		//Tries to fire a projectile if the left mouse button
		//is down
		 
		if(game.isLeftMouseDown())
		{
			weapon.tryFire();
		}
	}
	
	/**
	 * Swaps the player sprite
	 * depending on their position
	 * compared to the mouse position
	 * so that the player always
	 * faces towards the mouse
	 */
	public void faceCursor()
	{
		if(game.getMX() < x)
			setSprite("l_"+filepath);
		else if(game.getMX() > x)
			setSprite(filepath);
	}
	
	/**
	 * Sets Horizonatal and vertical 
	 * move according to the active keys
	 */
	public void getDirectionalInput()
	{
		if(game.keyActive(KeyEvent.VK_W))  //game keeps an array of keys that are pressed down and can check if a certain key is pressed
		{
			verticalMove = -1;
		}
		else if(game.keyActive(KeyEvent.VK_S))
		{
			verticalMove = 1;
		}
		else
		{
			verticalMove = 0;
		}
		if(game.keyActive(KeyEvent.VK_D))
		{
			horizontalMove = 1;
		}
		else if(game.keyActive(KeyEvent.VK_A))
		{
			horizontalMove = -1;
		}
		else
		{
			horizontalMove = 0;
		}
	}
	
	/**
	 * Getter for speed
	 */
	public double getSpeed() 
	{
		return speed;
	}

	/**
	 * Setter for speed
	 */
	public void setSpeed(double speed)
	{
		this.speed = speed;
		adjustmentSpeed = speed;
	}

	/**
	 * Changes the weapon of the player
	 * to a new weapon
	 */
	public void setWeapon(Weapon newWeapon)
	{
		weapon = newWeapon;
	}
	
	/**
	 * Returns the current weapon
	 */
	public Weapon getWeapon()
	{
		return weapon;
	}
	
	/**
	 * Returns the additional player damage
	 */
	public double getDamage() 
	{
		return damage;
	}

	/**
	 * Sets the player damage
	 */
	public void setDamage(double damage) 
	{
		this.damage = damage;
	}

	/**
	 * Overrides the defeated function
	 * so that when health reaches zero
	 * or below the game ends
	 */
	public void defeated()
	{
		game.gameOver();
	}
	
	
}