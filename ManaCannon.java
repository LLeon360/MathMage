/**
 * ManaCannon.java
 * 
 * Overwrites the Weapon class fire method
 * to fire a cluster of shots with fast speed
 * at a moderate rate
 * 
 * Extends the Weapon Class
 * 
 * @author Leon Liu
 * @since 5/22/20
 */
public class ManaCannon extends Weapon
{

	/**
	 * Calls the superclass constructor
	 * and sets the weapon fire speed and damage and name
	 */
	public ManaCannon(PlayerObject player) 
	{
		super(player, 500, 1);
		name = "Mana Cannon";
	}

	/**
	 * Resets the cooldown using
	 * the superclass method.
	 * Spawns cluster of 4 shots with a fast speed that goes towards the mouse
	 */
	public void fire()
	{
		super.fire();
		UnitVector attackDir = new UnitVector(game.getMX()-player.getX(), game.getMY()-player.getY());
		new Projectile(game, player.getX() + 20, player.getY(), "ManaShot.png", damage, 1.5, attackDir.getAngle(), "player");

		new Projectile(game, player.getX() - 20, player.getY(), "ManaShot.png", damage, 1.5, attackDir.getAngle(), "player");

		new Projectile(game, player.getX(), player.getY() + 20, "ManaShot.png", damage, 1.5, attackDir.getAngle(), "player");

		new Projectile(game, player.getX(), player.getY() - 20, "ManaShot.png", damage, 1.5, attackDir.getAngle(), "player");
	}
	
}
