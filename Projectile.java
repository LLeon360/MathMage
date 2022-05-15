/**
 * Projectile.java
 * 
 * Moves in the specified direction at
 * the specified speed using vectors.
 * Also stores the amount of damage
 * it deals on hit.
 * 
 * @author Leon Liu
 * @since 4/8/20
 */

public class Projectile extends GameObject 
{

	private double speed;
	protected double damage;
	protected UnitVector movement;
	protected String tag;

	/**
	 * Constructor derived from GameObject allows
	 * creation of vector using speed and angle
	 */
	public Projectile(GamePanel game, double x, double y, String imgSource, double damage, double speed, double angle, String tag) 
	{
		super(game, x, y, imgSource);
		movement = new UnitVector(angle);
		this.speed = speed;
		this.damage = damage;
		this.tag = tag;
	}

	/**
	 * Constructor derived from GameObject allows
	 * creation of vector using speed and 2 components
	 * x and y
	 */
	public Projectile(GamePanel game, double x, double y, String imgSource, double damage, double speed, double targetX, double targetY) 
	{
		super(game, x, y, imgSource);
		movement = new UnitVector(targetX, targetY);
		this.speed = speed;
		this.damage = damage;
	}

	/**
	 * Moves the projectile in the given direction and
	 * then deletes itself if it goes outside the screen
	 * dimensions
	 * 
	 * If the projectile is an enemy projectile
	 * it checks if it is colliding with the player
	 * object and if so damages the player and
	 * destroys itself
	 * 
	 * If the projectile is a player projectile
	 * it checks if it is colliding with any damageable
	 * objects that are tagged as enemy and if so
	 * damages that object and destroys itself
	 * 
	 * Breaks if it collides with enemy so that
	 * multiple destroy() methods aren't called
	 * which would lead to array errors
	 */
	public void update()
	{
		super.update();
		x += movement.getX() * speed;
		y += movement.getY() * speed;

		checkCollisions();
	}
	
	/**
	 * Checks collisions with the window boundaries and other
	 * game objects to check if it should destroy and possibly
	 * deal damage
	 */
	public void checkCollisions()
	{
		if(x > game.getWidth() || x < 0 || y > game.getHeight() || y < 0)
		{
			destroy();
		}
		else if(tag.equals("enemy"))
		{
			if(collides(game.getPlayer()))
			{
				game.getPlayer().damage(damage);
				destroy();
			}
		}
		else if(tag.equals("player"))
		{
			GameObject[] objs = game.getObjList();
			for(int i = 0; i < objs.length; i++)
			{
				if(objs[i] instanceof DamageableObject)	
				{
					if(((DamageableObject)objs[i]).getTag().equals("enemy"))
					{
						if(collides(objs[i]))
						{
							((DamageableObject)objs[i]).damage(damage);
							destroy();
							break;
						}
					}
				}
			}
		}
	}
}
