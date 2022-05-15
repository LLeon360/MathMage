/**
 * Scarecrow.java
 * 
 * The first boss that appears on Wave 10
 * with moderate health and slow speed. Moves to random
 * positions and shoots different types of attacks and switches
 * between after a set amount of repetitions.
 * 
 * @author Leon Liu
 * @since 5/20/20
 */
public class Scarecrow extends Boss 
{

	/*
	 * Saves the angle for the previous attack on the player
	 */
	private UnitVector pastAngle;
	
	/**
	 * Calls the superclass constructor
	 * and sets the Boss image to
	 * the scarecrow sprite and sets
	 * values to parameters
	 */
	public Scarecrow(GamePanel game, double x, double y) {
		super(game, x, y, "Scarecrow.png");
		speed = adjustmentSpeed = 0.3;
		nextFire = 2000; //starting delay, allows time to read boss text
		fireRate = 2000;

		health = maxHealth = 200;
	}

	/**
	 * Shoots 3 bird shaped attacks and then shoots 4 rings of 8 bullets
	 * 2 times for the first phase and then switches to a rapid fire line of shots
	 * for 50 shots and swaps back to the first phase
	 * 
	 *
	 * Calls the method of the superclass after because it
	 * has to come after the changes to firerate.
	 */
	protected void fire()
	{
		UnitVector attackDir = new UnitVector(player.getX()-x, player.getY()-y);
		switch(phase)
		{
		case 0:
			switch(attackNum)
			{
			case 0:
				for(int i = 0; i < 3; i++)
				{
					new Projectile(game, x, y, "EnemyShot.png", 1, 1, attackDir.getAngle()+Math.toRadians(30*i-30) ,"enemy");
				}
				fireRate = 50;
				break;
			case 1:
				for(int i = 0; i < 2; i++)
				{
					new Projectile(game, x, y, "EnemyShot.png", 1, 1, attackDir.getAngle()+Math.toRadians(30*i-15) ,"enemy");
				}
				fireRate = 200;
				break;
			case 2:
				for(int i = 0; i < 3; i++)
				{
					new Projectile(game, x, y, "EnemyShot.png", 1, 1, attackDir.getAngle()+Math.toRadians(30*i-30) ,"enemy");
				}
				fireRate = 50;
				break;
			case 3:
				for(int i = 0; i < 2; i++)
				{
					new Projectile(game, x, y, "EnemyShot.png", 1, 1, attackDir.getAngle()+Math.toRadians(30*i-15) ,"enemy");
				}
				fireRate = 200;
				break;
			case 4:
				for(int i = 0; i < 3; i++)
				{
					new Projectile(game, x, y, "EnemyShot.png", 1, 1, attackDir.getAngle()+Math.toRadians(30*i-30) ,"enemy");
				}
				fireRate = 50;
				break;
			case 5:
				for(int i = 0; i < 2; i++)
				{
					new Projectile(game, x, y, "EnemyShot.png", 1, 1, attackDir.getAngle()+Math.toRadians(30*i-15) ,"enemy");
				}
				fireRate = 850;
				break;
			case 6:
				for(int i = 0; i < 8; i++)
				{
					new Projectile(game, x, y, "EnemyShot.png", 1, 0.8, attackDir.getAngle()+Math.toRadians(45*i) ,"enemy");
				}
				fireRate = 100;
				pastAngle = attackDir;
				break;
			case 7:
				for(int i = 0; i < 8; i++)
				{
					new Projectile(game, x, y, "EnemyShot.png", 1, 0.8, pastAngle.getAngle()+Math.toRadians(45*i+22.5) ,"enemy");
				}
				break;
			case 8:
				for(int i = 0; i < 8; i++)
				{
					new Projectile(game, x, y, "EnemyShot.png", 1, 0.8, pastAngle.getAngle()+Math.toRadians(45*i) ,"enemy");
				}
				break;
			case 9:
				for(int i = 0; i < 8; i++)
				{
					new Projectile(game, x, y, "EnemyShot.png", 1, 0.8, pastAngle.getAngle()+Math.toRadians(45*i+22.5) ,"enemy");
				}
				fireRate = 700;
				break;
			default://once the cycle is complete and attackNum is not caught by any attack it is reset to -1(because attackNum is incremeted right after so effectively 0) for new cycle
				attackNum = -1;
				phaseCount++;
				if(phaseCount > 2)
				{
					phase++; //Swaps to next phase if this phase has been repeated 3 times
					phaseCount = 0;
				}
				break;
			}
			break;
		case 1:
			switch(attackNum)
			{
			case 0:
				new Projectile(game, x, y, "EnemyShot.png", 1, 2, attackDir.getAngle(), "enemy");
				fireRate = 20;
				break;
			default://once the cycle is complete and attackNum is not caught by any attack it is reset to -1(because attackNum is incremeted right after so effectively 0) for new cycle
				attackNum = -1;
				phaseCount++;
				if(phaseCount > 50)
				{
					phase++; //Swaps to next phase if this phase has been repeated 3 times
					phaseCount = 0;
					fireRate = 300;
				}
				break;
			}
			break;
		default://once the cycle is complete and phase is not caught by any attack it is reset for new cycle
			phase = 0;
			attackNum = -1;
			break;
		}
		
		attackNum++;//moves onto the next attack
		super.fire();
	}
}
