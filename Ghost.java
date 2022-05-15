/**
 * Ghost.java
 * 
 * Flies around to random locations and
 * attacks once it reach their target.
 * Attacks with a volley of 3 projectiles
 * 8 times
 * 
 * Fades out when is not attacking which prevents the
 * player from damaging it. Can only be damaged when
 * faded in when it stops and starts attacking
 * 
 * @author Leon Liu
 * @since 5/6/20
 */
public class Ghost extends PatrolEnemy 
{
	/**
	 * burstCount counts the number of
	 * groups of projectiles are fired
	 * so that the enemy can attack in bursts
	 */
	private int burstCount;
	private boolean invulnerable;
	
	/**
	 * Calls the superclass constructor
	 * and sets the PatrolEnemy image to
	 * the ghost sprite and sets
	 * values to parameters
	 */
	public Ghost(GamePanel game, double x, double y) {
		super(game, x, y, "FadedGhost.png");
		speed = adjustmentSpeed = 0.3;
		fireRate = 1000;
		
		health = maxHealth = 30;
		
		burstCount = 0;
		invulnerable = true;
	}

	/**
	 * Shoots 24 projectiles in 8 volleys so
	 * 3 per
	 * 
	 * Calls the method of the superclass after because it
	 * has to come after the changes to firerate.
	 */
	protected void fire()
	{
		UnitVector attackDir = new UnitVector(player.getX()-x, player.getY()-y);
		for(int i = 0; i < 3; i++)
		{
			new Projectile(game, x, y, "EnemyShot.png", 1, 0.4, attackDir.getAngle()+Math.toRadians(15*i-22.5) ,"enemy");
		}
		burstCount++;
		if(burstCount < 8)
		{
			filepath = "Ghost.png";
			invulnerable = false;
			
			finishedPatrol = true;
			fireRate = 100;
		}
		else
		{
			filepath = "FadedGhost.png";
			invulnerable = true;
			
			burstCount = 0;
			finishedPatrol = false;
			fireRate = 1000;
		}
		super.fire();
	}
	
	/**
	 * Reduces health value by the
	 * specified amount only when
	 * the enemy isn't invulnerable
	 */
	public void damage(double damageAmt)
	{
		if(!invulnerable)
		{
			super.damage(damageAmt);
		}
	}
	
}
