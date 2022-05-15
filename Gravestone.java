/**
 * Gravestone.java
 * 
 * Enemy that stays still and spawns
 * more skeleton and zombie enemies
 * 
 * @author Leon Liu
 * @since 5/15/20
 */
public class Gravestone extends StillEnemy
{

	/**
	 * Calls the superclass constructor
	 * and sets the sprite to the
	 * Gravestone sprite and sets
	 * specific value to the enemy 
	 * data
	 */
	public Gravestone(GamePanel game, double x, double y) 
	{
		super(game, x, y, "Gravestone.png");
		fireRate = 3500;
		
		health = maxHealth = 60;
	}

	/**
	 * Spawns a zombie and skeleton
	 * and then resets the fire time
	 */
	protected void fire()
	{
		super.fire();
		new Skeleton(game, x, y);
		new Zombie(game, x, y);
	}

}
