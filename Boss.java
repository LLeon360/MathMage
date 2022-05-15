/**
 * Boss.java
 * 
 * Enemy type that patrols to random 
 * locations and fires upon having available 
 * shots. This AI is used for the Rook and Scarecrow boss
 * 
 * Different from PatrolEnemy because it is able to fire and
 * shoot at the same time.
 * 
 * @author Leon Liu
 * @since 5/20/20
 */
public class Boss extends Enemy
{

	/**
	 * patrolX and patrolY are the x and y coordinates
	 * of the patrol target
	 * 
	 * speed is the speed they move towards the patrol point
	 * 
	 * finishedPatrol is whether the patrol point is reached
	 * 
	 * attackNum is the type of attack that will be used for the specific fire
	 * 
	 * phase is the type of attack sequence
	 * 
	 * phaseCount is the number of times an attack phase has been repeated
	 */
	protected int patrolX;
	protected int patrolY;
	protected double speed;
	protected boolean finishedPatrol;
	protected int attackNum, phase, phaseCount;
	
	/**
	 * Calls the superclass constructor Enemy
	 * and sets a new patrol point and sets default values
	 */
	public Boss(GamePanel game, double x, double y, String imgSource)
	{
		super(game, x, y, imgSource);
		newPatrol();
		attackNum = phase = phaseCount = 0;
	}

	/**
	 * On update, moves to the patrol point
	 * and tries to fire at the player
	 */
	public void update()
	{
		super.update();
		move();
		if(game.getTime() > nextFire)//Makes sure the amount of time elapsed is greater than the next available fire
		{
			fire();
		}
	}
	
	/**
	 * Moves towards the patrol point
	 */
	public void move()
	{
		double distance = getDistanceTo(patrolX, patrolY);
		if(finishedPatrol)//finished patrol
		{
			newPatrol();
			finishedPatrol = false;
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
		patrolX = (int)(Math.random()*750)+100;
		patrolY = (int)(Math.random()*300)+100;
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