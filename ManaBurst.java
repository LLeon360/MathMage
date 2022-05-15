/**
 * ManaBurst.java
 * 
 * Overwrites the Weapon class fire method
 * to shoot a burst of 16 projectiles in all directions
 * 
 * Extends the Weapon Class
 * 
 * @author Leon Liu
 * @since 5/22/20
 */
public class ManaBurst extends Weapon
{

	/**
	 * Calls the superclass constructor
	 * and sets the weapon fire speed and damage and name
	 */
	public ManaBurst(PlayerObject player) 
	{
		super(player, 800, 1);
		name = "Mana Burst";
	}

	/**
	 * Resets the cooldown using
	 * the superclass method.
	 * Spawns 16 shots in 
	 */
	public void fire()
	{
		super.fire();
		UnitVector attackDir = new UnitVector(game.getMX()-player.getX(), game.getMY()-player.getY());

		for(int i = 0; i < 8; i++)
		{
			new Projectile(game, player.getX(), player.getY(), "SmallManaShot.png", damage, 1, attackDir.getAngle()+Math.toRadians(45*i), "player");
			new Projectile(game, player.getX(), player.getY(), "ManaShot.png", damage, 1, attackDir.getAngle()+Math.toRadians(45*i + 22.5), "player");
		}
	}
	
}
