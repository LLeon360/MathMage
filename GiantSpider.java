/**
 * GiantSpider.java
 * 
 * Flies around to random locations and
 * attacks once it reach their target.
 * Attacks with a burst of 16 projectiles
 * in a circle like a web
 * 
 * @author Leon Liu
 * @since 5/6/20
 */
public class GiantSpider extends PatrolEnemy 
{
	/**
	 * Calls the superclass constructor
	 * and sets the PatrolEnemy image to
	 * the giant spider sprite and sets
	 * values to parameters
	 */
	public GiantSpider(GamePanel game, double x, double y) {
		super(game, x, y, "GiantSpider.png");
		speed = adjustmentSpeed = 0.2;
		fireRate = 1000;
		
		health = maxHealth = 40;
	}

	/**
	 * Shoots 16 projectiles in a circle like a web
	 */
	protected void fire()
	{
		super.fire();
		UnitVector attackDir = new UnitVector(player.getX()-x, player.getY()-y);
		for(int i = 0; i < 8; i++)
		{
			new Projectile(game, x, y, "EnemyShot.png", 1, 0.4, attackDir.getAngle()+Math.toRadians(45*i) ,"enemy");
			new Projectile(game, x, y, "EnemyShot.png", 1, 0.7, attackDir.getAngle()+Math.toRadians(45*i+22.5) ,"enemy");
		}
	}
	
}
