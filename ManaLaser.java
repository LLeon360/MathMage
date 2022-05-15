/**
 * ManaLaser.java
 * 
 * Overwrites the Weapon class fire method
 * to rapid fire shots straight towards the mouse
 * 
 * Extends the Weapon Class
 * 
 * @author Leon Liu
 * @since 5/22/20
 */
public class ManaLaser extends Weapon
{

	/**
	 * Calls the superclass constructor
	 * and sets the weapon fire speed and damage and name
	 */
	public ManaLaser(PlayerObject player) 
	{
		super(player, 50, 0.4);
		name = "Mana Laser";
	}

	/**
	 * Resets the cooldown using
	 * the superclass method.
	 * Spawns a bullet with a moderate speed that goes towards the mouse
	 */
	public void fire()
	{
		super.fire();
		UnitVector attackDir = new UnitVector(game.getMX()-player.getX(), game.getMY()-player.getY());
		new Projectile(game, player.getX(), player.getY(), "SmallManaShot.png", damage, 1, attackDir.getAngle(), "player");
	}
	
}
