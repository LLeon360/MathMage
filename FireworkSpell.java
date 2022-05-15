/**
 * FlameSpell.java
 * 
 * Overwrites the Weapon class fire method
 * to slowly fire projectiles that burst into
 * smaller projectiles in all directions
 * 
 * Extends the Weapon Class
 * 
 * @author Leon Liu
 * @since 5/22/20
 */
public class FireworkSpell extends Weapon
{

	/**
	 * Calls the superclass constructor
	 * and sets the weapon fire speed and damage and name
	 */
	public FireworkSpell(PlayerObject player) 
	{
		super(player, 800, 0.2);
		name = "Firework Spell";
	}

	/**
	 * Resets the cooldown using
	 * the superclass method.
	 * Spawns a bullet with a moderate speed that goes towards the mouse with
	 * random offset. The projectile has a lifetime which when it destroys itself spawns
	 * other bullets in a circle to simulate a firework
	 */
	public void fire()
	{
		super.fire();
		UnitVector attackDir = new UnitVector(game.getMX()-player.getX(), game.getMY()-player.getY());
		FireworkShot firework = new FireworkShot(game, player.getX(), player.getY(), "FireworkShot.png", damage, 1, attackDir.getAngle() + Math.toRadians((int)(Math.random() * 70)-35), "player");
		firework.setLifetime(200);
	}
	
}
