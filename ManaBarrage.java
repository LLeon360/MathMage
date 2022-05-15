/**
 * ManaBarrage.java
 * 
 * Rapid fires bullets with spread
 * which do average damage and
 * have moderate lifespan
 * 
 * Extends the Weapon Class
 * 
 * @author Leon Liu
 * @since 5/13/20
 */
public class ManaBarrage extends Weapon
{

	/**
	 * Calls the superclass constructor
	 * and sets the millisecond delay to
	 * 75 and damage to 1.
	 */
	public ManaBarrage(PlayerObject player) 
	{
		super(player, 75, 1);
		name = "Mana Barrage";
	}

	/**
	 * Resets the cooldown using
	 * the superclass method.
	 * Spawns a bullet with a randomized offset to
	 * its angle
	 */
	public void fire()
	{
		super.fire();
		UnitVector attackDir = new UnitVector(game.getMX()-player.getX(), game.getMY()-player.getY());
		Projectile manaShot = new Projectile(game, player.getX(), player.getY(), "ManaShot.png", damage, (int)(Math.random()*0.5)+0.8, attackDir.getAngle()+Math.toRadians((int)(Math.random()*60)-30), "player");
		manaShot.setLifetime(600);
	}
	
}
