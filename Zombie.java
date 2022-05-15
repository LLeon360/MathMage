/**
 * Zombie.java
 * 
 * A basic starter enemy that vomits
 * a barrage of slow moving projectiles
 * towards the player
 * at a close range and pursues with the 
 * PursuitEnemy AI.
 * 
 * @author Leon Liu
 * @since 4/20/20
 */
public class Zombie extends PursuitEnemy 
{

	/**
	 * Calls the superclass constructor
	 * and sets the PursuitEnemy image to
	 * the zombie sprite and sets
	 * values to parameters
	 */
	public Zombie(GamePanel game, double x, double y) 
	{
		super(game, x, y, "Zombie.png");
		pursuitRange = 200;
		fireRange = 370;
		speed = adjustmentSpeed = 0.6;
		fireRate = 600;
		
		health = maxHealth = 10;
	}

	/**
	 * Sprays 5 bullets at the player
	 * at varying angles which disappear
	 * after about a second
	 */
	protected void fire()
	{
		super.fire();
		UnitVector attackDir = new UnitVector(player.getX()-x, player.getY()-y);
		for(int i = 0; i < 5; i++)
		{
			Projectile temp = new Projectile(game, x, y, "EnemyShot.png", 1, Math.random()*0.3+0.1, attackDir.getAngle()+Math.toRadians((int)(Math.random()*60)-30) ,"enemy");
			temp.setLifetime(1300);
		}
	}
	
}
