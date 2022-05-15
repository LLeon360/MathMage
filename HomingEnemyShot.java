/**
 * HomingEnemyShot.java
 * 
 * An enemy projectile that tracks and 
 * chases the player position. 
 * 
 * Extends the Projectile class
 * 
 * @author Leon Liu
 * @since 5/15/20
 */
public class HomingEnemyShot extends Projectile 
{

	private PlayerObject player;
	
	/**
	 * Calls the superclass constructor but does not include
	 * targetX and targetY parameters because it homes onto a player
	 * not a coordinate and also sets the tag to enemy by default
	 */
	public HomingEnemyShot(GamePanel game, double x, double y, double damage, double speed, PlayerObject player) 
	{
		super(game, x, y, "HomingEnemyShot.png", damage, speed, 0, 0);
		tag = "enemy";
		this.player = player;
	}

	/**
	 * Changes the target as the projectile updates to the player
	 * so that it moves towards the player's new position each update
	 */
	public void update()
	{
		movement = new UnitVector(player.getX()-x, player.getY()-y);
		super.update();
	}
	
}
