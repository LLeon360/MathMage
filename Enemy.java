/**
 * Enemy.java
 * 
 * A damageable object with the enemy tag
 * and has a fire function to be overwritten.
 * 
 * @author Leon Liu
 * @since 5/6/20
 */
public class Enemy extends DamageableObject 
{
	/**
	 * player is an alias of the player object to track position
	 * 
	 * nextFire is the next timestep that the enemy can fire
	 * 
	 * fireRate is the minimum cooldown between shots and determines
	 * the nextFire
	 */
	protected PlayerObject player;
	protected int nextFire, fireRate;
	
	/**
	 * Sets the player alias and superclass variables for the constructor
	 * 
	 * Sets the tag to enemy
	 * 
	 * randomizes the nextFire to stagger attacks.
	 */
	public Enemy(GamePanel game, double x, double y, String imgSource) 
	{
		super(game, x, y, imgSource);
	
		player = game.getPlayer();
		
		fireRate = -1;
		nextFire = (int)(Math.random()*1000);
		
		tag = "enemy";
	}

	/**
	 * Sets the sprite depending on position
	 * relative to player and calls the superclass
	 * update function to check health and other things
	 */
	public void update()
	{
		super.update();
		
		if(player.getX() < x)
			setSprite("l_"+filepath);
		else if(player.getX() > x)
			setSprite(filepath);
	}
	
	/**
	 * Resets the next possible
	 * fire time using the cooldown.
	 * Method will be overridden by
	 * Enemy child classes which will
	 * define the specific attack that is
	 * done.
	 */
	protected void fire()
	{
		nextFire = game.getTime() + fireRate;//sets the next available fire based of fireRate cooldown value
	}
	
}
