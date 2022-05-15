/**
 * DamageableObject.java
 * 
 * A GameObject that tracks the amount
 * of health with the capability
 * of being damaged. Also keeps
 * a tag to determine if it
 * is a player or enemy
 * 
 * @author Leon Liu
 * @since 4/10/20
 */

public class DamageableObject extends GameObject 
{

	/**
	 * Field Variables:
	 * health is the amount of health the object
	 * has left
	 * 
	 * tag catagorizes the enemies and players
	 * to make collision checking more efficient
	 * 
	 * adjustmentSpeed is the speed at which the object is pulled back
	 * to the center of the screen if it goes out of bounds
	 */
	protected double health, maxHealth;
	protected String tag;
	protected double adjustmentSpeed;
	/**
	 * GameObject constructor with an
	 * additional health parameter
	 */
	public DamageableObject(GamePanel game, double x, double y, String imgSource) 
	{
		super(game, x, y, imgSource);
		adjustmentSpeed = 2;
	}

	/**
	 * Checks if the health
	 * is below or equal to
	 * zero in which case
	 * it calls its defeated
	 * method
	 * 
	 * Also moves the object towards the center if it 
	 * is out of bounds
	 */
	public void update()
	{
		super.update();
		if(health <= 0)
		{
			defeated();
		}
		
		double xDisplacement = 0, yDisplacement = 0;
		if(x - getWidth()/2 < 0 || x + getWidth()/2> game.getWidth())
		{
			xDisplacement = (game.getWidth()/2 - x)/Math.abs(game.getWidth()/2 - x);
		}
		if(y - getHeight()/2 < 0 || y + getHeight()/2 > game.getHeight())
		{
			yDisplacement = (game.getHeight()/2 - y)/Math.abs(game.getHeight()/2 - y);
		}
		x+= xDisplacement * adjustmentSpeed;
		y+= yDisplacement * adjustmentSpeed;

	}

	/**
	 * Destroys itself on
	 * defeat so it is no
	 * longer drawn
	 */
	public void defeated()
	{
		destroy();
	}

	/**
	 * Sets the tag of the
	 * damageable object
	 */
	public void setTag(String tag)
	{
		this.tag = tag;
	}

	/**
	 * Returns the tag
	 */
	public String getTag()
	{
		return tag;
	}

	/**
	 * Returns the amount of health
	 */
	public double getHealth()
	{
		return health;
	}

	/**
	 * Returns the max health
	 */
	public double getMaxHealth()
	{
		return maxHealth;
	}

	/**
	 * Reduces health value by the
	 * specified amount rounded to the tenths decimal place
	 */
	public void damage(double damageAmt)
	{
		setHealth(Math.round((getHealth()-damageAmt) * 10.0) / 10.0);
	}

	/**
	 * Sets the health value
	 */
	public void setHealth(double health)
	{
		this.health = health;
	}

	/**
	 * Increases health but not past
	 * the max health. Rounds health to the
	 * tenths decimal place
	 */
	public void heal(double healAmount)
	{
		if(health + healAmount > maxHealth)
			health = maxHealth;
		else 
			health = (Math.round((health+healAmount) * 10.0) / 10.0);
	}

	/**
	 * Setter for max health
	 */
	public void setMaxHealth(double maxHealth) 
	{
		this.maxHealth = maxHealth;
	}
	
}
