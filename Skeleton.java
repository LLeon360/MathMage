/**
 * Skeleton.java
 * 
 * A basic starter enemy that shoots
 * projectiles at a medium pace towards
 * the player and pursues with the 
 * PursuitEnemy AI at a medium range.
 * 
 * @author Leon Liu
 * @since 4/20/20
 */
public class Skeleton extends PursuitEnemy 
{
	
	/**
	 * Calls the superclass constructor
	 * and sets the sprite to the
	 * Skeleton sprite and sets
	 * specific value to the enemy 
	 * data
	 */
	public Skeleton(GamePanel game, double x, double y) 
	{
		super(game, x, y, "Skeleton.png");
		pursuitRange = 330;
		fireRange = 400;
		speed = adjustmentSpeed = 0.3;
		fireRate = 400;
		
		health = maxHealth = 10;
	}

	/**
	 * Shoots a single bullet at the
	 * player and resets the fire rate
	 * using the superclass method and
	 */
	protected void fire()
	{
		super.fire();
		UnitVector attackDir = new UnitVector(player.getX()-x, player.getY()-y);
		new Projectile(game, x, y, "EnemyShot.png", 1, 0.7, attackDir.getAngle(), "enemy");
	}
	
}
