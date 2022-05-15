/**
 * PatrolEnemy.java
 * 
 * Enemy type that patrols to random 
 * locations and fires upon reaching the location.
 * 
 * @author Leon Liu
 * @since 5/6/20
 */
public class PatrolEnemy extends Enemy
{

	/**
	 * patrolX and patrolY are the x and y coordinates
	 * of the patrol target
	 * 
	 * speed is the speed they move towards the patrol point
	 * 
	 * finishedPatrol is whether the patrol point is reached
	 */
	private int patrolX, patrolY;
	protected double speed;
	protected boolean finishedPatrol;
	
	/**
	 * Calls the superclass constructor Enemy
	 * and sets a new patrol point and sets default values
	 */
	public PatrolEnemy(GamePanel game, double x, double y, String imgSource)
	{
		super(game, x, y, imgSource);
		newPatrol();
	}

	/**
	 * On update, patrols to the 
	 * target spot or shoots and sets
	 * a new patrol point
	 */
	public void update()
	{
		super.update();
		double distance = getDistanceTo(patrolX, patrolY);
		if(finishedPatrol)//finished patrol
		{
			if(game.getTime() > nextFire)//Makes sure the amount of time elapsed is greater than the next available fire
			{
				newPatrol();
				finishedPatrol = false;
				fire();
			}
		}
		else if(distance > speed && !finishedPatrol)
		{
			UnitVector movement = new UnitVector(patrolX-x, patrolY-y);
			x += movement.getX() * speed;
			y += movement.getY() * speed;
			finishedPatrol = distance <= speed;
		}
		else
		{
			finishedPatrol = distance <= speed;
		}
	}
	
	/**
	 * Sets a new random coordinate
	 * to go to
	 */
	public void newPatrol()
	{
		patrolX = (int)(Math.random()*900)+50;
		patrolY = (int)(Math.random()*400)+50;
	}
	
	/**
	 * Returns the distance between this and a 
	 * coordinate
	 */
	private double getDistanceTo(double x2, double y2)
	{
		double distance;
		distance = Math.sqrt( Math.pow((x-x2),2) + Math.pow((y-y2),2)); //Distance formula for two points
		return distance;
	}
	
}