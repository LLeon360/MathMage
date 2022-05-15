/**
 * FireworkShot.java
 * 
 * Projectile that splits into other projectiles when destroyed
 * like a firework which is used in the Firework Spell
 * 
 * @author Leon Liu
 * @since 5/22/20
 */
public class FireworkShot extends Projectile 
{

	/**
	 * Constructor same as superclass projectile
	 * constructor
	 */
	public FireworkShot(GamePanel game, double x, double y, String imgSource, double damage, double speed, double angle, String tag) 
	{
		super(game, x, y, imgSource, damage, speed, angle, tag);
	}


	/**
	 * Overwrites the destroy method to spawn some colored
	 * projectiles before it gets destroyed
	 */
	public void destroy()
	{
		for(int i = 0; i < 10; i++)
		{
			new Projectile(game, x, y, "FireworkRed.png", damage, 0.6, Math.toRadians(36*i) ,"player");
			new Projectile(game, x, y, "FireworkYellow.png", damage, 0.5, Math.toRadians(36*i + 9) ,"player");
			new Projectile(game, x, y, "FireworkGreen.png", damage, 0.8, Math.toRadians(36*i + 18) ,"player");
			new Projectile(game, x, y, "FireworkBlue.png", damage, 0.7, Math.toRadians(36*i + 27) ,"player");
		}
		super.destroy();
	}
	
}
