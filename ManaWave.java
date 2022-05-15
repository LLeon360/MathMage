/**
 * ManaWave.java
 * 
 * Weapon that shoots 5 projectiles
 * every 225 ms. Requires players to 
 * move in close to use because of short bullet
 * lifespan.
 * Extends the Weapon class to use its fire
 * cooldown system
 * 
 * @author Leon Liu
 * @since 4/9/20
 */
public class ManaWave extends Weapon
{
	/**
	 * Calls the superclass constructor
	 * and sets the millisecond delay to
	 * 225.
	 */
	public ManaWave(PlayerObject player)
	{
		super(player, 225, 0.5);
		name = "Mana Wave";
	}
	
	/**
	 * Resets the cooldown using
	 * the superclass method.
	 * Summons 5 player projectiles in
	 * a fan shape towards
	 * the mouse that disappear
	 * after a short time 
	 */
	public void fire()
	{
		super.fire();
		UnitVector attackDir = new UnitVector(game.getMX()-player.getX(), game.getMY()-player.getY());
		Projectile tempBullet = new Projectile(game, player.getX(), player.getY(), "SmallManaShot.png", damage, 1, attackDir.getAngle(), "player");
		tempBullet.setLifetime(195);
		tempBullet = new Projectile(game, player.getX(), player.getY(), "SmallManaShot.png", damage, 1, attackDir.getAngle()+Math.toRadians(5), "player");
		tempBullet.setLifetime(190);
		tempBullet = new Projectile(game, player.getX(), player.getY(), "SmallManaShot.png", damage, 1, attackDir.getAngle()+Math.toRadians(10), "player");
		tempBullet.setLifetime(185);
		tempBullet = new Projectile(game, player.getX(), player.getY(), "SmallManaShot.png", damage, 1, attackDir.getAngle()-Math.toRadians(5), "player");
		tempBullet.setLifetime(190);
		tempBullet = new Projectile(game, player.getX(), player.getY(), "SmallManaShot.png", damage, 1, attackDir.getAngle()-Math.toRadians(10), "player");
		tempBullet.setLifetime(185);
	}

}
