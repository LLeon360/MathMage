/**
 * ManaSnipe.java
 * 
 * Shoots a line of bullets at varying
 * speeds with a high fire cooldown
 * Extends the Weapon class to use its fire
 * cooldown system
 * 
 * @author Leon Liu
 * @since 5/13/20
 */
public class ManaSnipe extends Weapon
{
	/**
	 * Calls the superclass constructor
	 * and sets the millisecond delay to
	 * 650 and damage to 1.
	 */
	public ManaSnipe(PlayerObject player)
	{
		super(player, 650, 1);
		name = "Mana Snipe";
	}
	
	/**
	 * Resets the fire cooldown using
	 * the superclass method.
	 * Spawns several bullets to shoot towards the mouse at
	 * decreasing speeds so a line of 3 bullets are shot
	 */
	public void fire()
	{
		super.fire();
		UnitVector attackDir = new UnitVector(game.getMX()-player.getX(), game.getMY()-player.getY());
		for(int i = 0; i < 3; i++)
		{
			Projectile manaShot = new Projectile(game, player.getX(), player.getY(), "ManaShot.png", damage, 3-i, attackDir.getAngle(), "player");
			manaShot.setLifetime(1000-300*i);
		}
	}

}
