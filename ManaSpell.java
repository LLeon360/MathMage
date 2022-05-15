/**
 * ManaSpell.java
 * 
 * Basic weapon that the player
 * starts with. Shoots a projectile every
 * 150 milliseconds that deals 1 damage on
 * hit.
 * Extends the Weapon class to use its fire
 * cooldown system
 * 
 * @author Leon Liu
 * @since 4/9/20
 */
public class ManaSpell extends Weapon
{
	/**
	 * Calls the superclass constructor
	 * and sets the millisecond delay to
	 * 150.
	 */
	public ManaSpell(PlayerObject player)
	{
		super(player, 150, 1);
		name = "Mana Spell";
	}
	
	/**
	 * Resets the cooldown using
	 * the superclass method.
	 * Summons a projectile at the
	 * player position which moves
	 * towards the mouse and tags
	 * it as the player's
	 */
	public void fire()
	{
		super.fire();
		UnitVector attackDir = new UnitVector(game.getMX()-player.getX(), game.getMY()-player.getY());
		Projectile manaShot = new Projectile(game, player.getX(), player.getY(), "ManaShot.png", damage, 1, attackDir.getAngle(), "player");
		manaShot.setLifetime(250);
	}

}
