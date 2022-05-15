/**
 * StillEnemy.java
 * 
 * A version of enemy that doesn't move but shoots
 * at the player from a fixed position
 * 
 * @author Leon Liu
 * @since 5/15/20
 */
public class StillEnemy extends Enemy
{

	/**
	 * Sets the player alias and superclass variables for the constructor
	 */
	public StillEnemy(GamePanel game, double x, double y, String imgSource) 
	{
		super(game, x, y, imgSource);
	}
	
	/**
	 * Calls the update method of the superclass
	 * to check health and flip sprites and then
	 * shoots if it is able to
	 */
	public void update()
	{
		super.update();
		if(game.getTime() > nextFire)
		{
			fire();
		}
	}

}
