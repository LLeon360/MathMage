/**
 * MeteorSpell.java
 * 
 * Launches a meteor from the corner of the screen towards
 * the mouse cursor position which deals heavy damage
 * Extends the Weapon class to use its fire
 * cooldown system
 * 
 * @author Leon Liu
 * @since 5/13/20
 */
public class MeteorSpell extends Weapon
{
	/**
	 * Calls the superclass constructor
	 * and sets the weapon damage and fire rate
	 * and name
	 */
	public MeteorSpell(PlayerObject player)
	{
		super(player, 600, 6);
		name = "Meteor Spell";
	}
	
	/**
	 * Resets the cooldown using
	 * the superclass method.
	 * Spawns a single meteor projectile
	 * which begins at the top right corner of
	 * the screen
	 */
	public void fire()
	{
		super.fire();
		UnitVector attackDir = new UnitVector(game.getMX(), game.getMY());
		new Projectile(game, 16.5, 16.5, "Meteor.png", damage, 2, attackDir.getAngle(), "player");
	}

}
