/**
 * FlameSpell.java
 * 
 * Overwrites the Weapon class fire method
 * to spray short lived projectiles at a rapid
 * pace.
 * 
 * Extends the Weapon Class
 * 
 * @author Leon Liu
 * @since 5/22/20
 */
public class FlameSpell extends Weapon
{

	/**
	 * Calls the superclass constructor
	 * and sets the weapon fire speed and damage and name
	 */
	public FlameSpell(PlayerObject player) 
	{
		super(player, 20, 1.5);
		name = "Flame Spell";
	}

	/**
	 * Resets the cooldown using
	 * the superclass method.
	 * Spawns a short life bullet with a moderate speed that goes towards the mouse with
	 * random offset
	 */
	public void fire()
	{
		super.fire();
		UnitVector attackDir = new UnitVector(game.getMX()-player.getX(), game.getMY()-player.getY());
		Projectile flame = new Projectile(game, player.getX(), player.getY(), "FireShot.png", damage, 0.7, attackDir.getAngle() + Math.toRadians((int)(Math.random() * 80)-40), "player");
		flame.setLifetime(100);
	}
	
}
