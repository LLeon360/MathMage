/**
 * PursuitEnemy.java
 * 
 * Basic enemy which can be damaged. 
 * Moves towards the player until within a certain threshold 
 * and then shoots until the player leaves a certain
 * threshold that defines the maximum firing range
 * 
 * @author Leon Liu
 * @since 4/18/20
 */
public class PursuitEnemy extends Enemy 
{	
	/**
	 * pursuitRange is how far away the player must
	 * be for the enemy to begin moving towards the player
	 * only if the player is outside the fireRange
	 * 
	 * fireRange is how far the away the player must be to begin
	 * shooting at the player. this value will always be greater than
	 * the pursuitRange
	 * 
	 * finishedPursuit is pursuit state, whether the enemy has made it to within the pursuitRange
	 * to begin firing after pursuit is begun. This allows the enemy to be closer than
	 * necessary to shoot allowing it more time before the player will leave its firing range
	 */
	protected double pursuitRange, fireRange, speed;
	private boolean finishedPursuit;
	
	/**
	 * DamagableObject constructor in addition to
	 * default values for nextFire and fireRate also
	 * sets an alias of the player
	 * 
	 * Sets its tag to enemy
	 */
	public PursuitEnemy(GamePanel game, double x, double y, String imgSource) 
	{
		super(game, x, y, imgSource);
	}

	/**
	 * On update, pursues the player or
	 * fires at the player depending on the distance
	 * between them.
	 */
	public void update()
	{
		super.update();
		double distance = getDistanceTo(player);
		if(distance < fireRange && finishedPursuit)
		{
			if(game.getTime() > nextFire)//Makes sure the amount of time elapsed is greater than the next available fire
			{
				fire();
			}
		}
		else if(distance > pursuitRange && !finishedPursuit)
		{
			UnitVector movement = new UnitVector(player.getX()-x, player.getY()-y);
			x += movement.getX() * speed;
			y += movement.getY() * speed;
			finishedPursuit = distance <= pursuitRange+speed;
		}
		else
		{
			finishedPursuit = distance <= pursuitRange+speed;
		}
		
		if(player.getX() < x)
			setSprite("l_"+filepath);
		else if(player.getX() > x)
			setSprite(filepath);
	}
	
	/**
	 * Returns the distance between this and another game object
	 * using the distance formula with the coordinates of both gameobjects
	 */
	private double getDistanceTo(GameObject g)
	{
		double distance;
		distance = Math.sqrt( Math.pow((x-g.getX()),2) + Math.pow((y-g.getY()),2)); //Distance formula for two points
		return distance;
	}
	
}

