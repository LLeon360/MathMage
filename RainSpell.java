/**
 * RainSpell.java
 * 
 * Weapon that spawns projectiles
 * at the top of the screen that fall
 * downward
 * Extends the Weapon class to use its fire
 * cooldown system
 * 
 * @author Leon Liu
 * @since 5/15/20
 */
public class RainSpell extends Weapon
{
	/**
	 * Calls the superclass constructor
	 * and sets the millisecond delay to
	 * 225.
	 */
	public RainSpell(PlayerObject player)
	{
		super(player, 25, 1);
		name = "Rain Spell";
	}
	
	/**
	 * Resets the cooldown using
	 * the superclass method.
	 * Spawns rain projectiles at the top of the screen at the 
	 * player x position that rain down
	 */
	public void fire()
	{
		super.fire();
		new Projectile(game, player.getX()+(int)(Math.random()*30)-15, 20, "Rain.png", damage, 1.5, Math.toRadians(90), "player");
	}

}
