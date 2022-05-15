/**
 * ManaFan.java
 * 
 * Shoots projectiles in a fan shape in
 * bursts of 6.
 * 
 * Extends the weapon class
 * 
 * @author Leon Liu
 * @since 5/13/20
 *
 */
public class ManaFan extends Weapon
{

	private int burstCount;
	
	/**
	 * Calls the superclass constructor
	 * and sets the millisecond delay to
	 * 50 and damage to 1.
	 * Also sets the burstCount to 0
	 */
	public ManaFan(PlayerObject player)
	{
		super(player, 50, 1);
		name = "Mana Fan";
		burstCount = 0;
	}
	
	/**
	 * Resets the cooldown using
	 * the superclass method.
	 * Spawns several bullets to shoot towards the mouse at
	 * different speeds
	 * Increments the burst count and resets it
	 * at 6 and extends the fireRate inbetween bursts
	 * to give it the burst fire effect.
	 */
	public void fire()
	{
		UnitVector attackDir = new UnitVector(game.getMX()-player.getX(), game.getMY()-player.getY());
		Projectile manaShot = new Projectile(game, player.getX(), player.getY(), "SmallManaShot.png", damage, 1.2, attackDir.getAngle() + Math.toRadians(10*burstCount-25), "player");
		manaShot.setLifetime(1000);
		burstCount++;
		if(burstCount < 6)
		{
			fireRate = 50;
		}
		else
		{		
			burstCount = 0;
			fireRate = 200;
		}
		super.fire();
	}

}
