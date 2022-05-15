/**
 * Bat.java
 * 
 * Flies around to random locations and
 * attacks once it reach its target.
 * Attacks with a salvo of 4 projectiles
 * 
 * @author Leon Liu
 * @since 5/6/20
 */
public class Bat extends PatrolEnemy 
{
	/**
	 * Calls the superclass constructor
	 * and sets the PatrolEnemy image to
	 * the bat sprite and sets
	 * values to parameters
	 */
	public Bat(GamePanel game, double x, double y) {
		super(game, x, y, "Bat.png");
		speed = adjustmentSpeed = 0.8;
		fireRate = 1000;
		
		health = maxHealth = 22;
	}

	/**
	 * Shoots a scattered 4 projectiles around the player
	 * but not directly towards them
	 */
	protected void fire()
	{
		super.fire();
		UnitVector attackDir = new UnitVector(player.getX()-x, player.getY()-y);
		for(int i = 0; i < 4; i++)
		{
			new Projectile(game, x, y, "EnemyShot.png", 1, 0.4, attackDir.getAngle()+Math.toRadians(30*i-45) ,"enemy");
		}
	}
	
}
