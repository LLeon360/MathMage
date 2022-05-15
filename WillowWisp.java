/**
 * WillowWisp.java
 * 
 * Enemy that stays still and spawns
 * homing projectiles
 * 
 * @author Leon Liu
 * @since 5/15/20
 */
public class WillowWisp extends StillEnemy
{

	/**
	 * Calls the superclass constructor
	 * and sets the sprite to the
	 * Willow Wisp sprite and sets
	 * specific value to the enemy 
	 * data
	 */
	public WillowWisp(GamePanel game, double x, double y) 
	{
		super(game, x, y, "WillowWisp.png");
		fireRate = 500;
		
		health = maxHealth = 55;
	}

	/**
	 * Shoots a single bullet at the
	 * player which homes in and resets the fire rate
	 * using the superclass method
	 */
	protected void fire()
	{
		super.fire();
		HomingEnemyShot chaseShot = new HomingEnemyShot(game, x, y, 3, 0.6, player);
		chaseShot.setLifetime(1200);
	}

}
