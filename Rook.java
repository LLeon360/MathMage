/**
 * Rook.java
 *	
 * Boss with unique movement pattern of only being able to move in one
 * of the four cardinal directions like a rook chesspiece. Moves randomly
 * in the four directions.
 * Shoots bullets from the sides of the screen and also in a x shaped
 * pattern from itself. Cycles between these 2 attack patterns.
 * 
 * The second Boss that is encountered.
 * 
 * @author Leon liu
 * @since 5/20/20
 */
public class Rook extends Boss 
{
	
	/**
	 * Calls the boss constructor to set up gameobject
	 * and then sets the sprite to Rook sprite.
	 * Sets the speed and fireRate and health and other values that
	 * are the boss's stats
	 */
	public Rook(GamePanel game, double x, double y) 
	{
		super(game, x, y, "Rook.png");
		speed = adjustmentSpeed = 0.4;
		nextFire = 2000; //starting delay, allows time to read boss text
		fireRate = 2000;

		health = maxHealth = 300;
	}

	/**
	 * On update, moves to the patrol point
	 * and tries to fire at the player
	 */
	public void update()
	{
		super.update();

		if(game.getTime() > nextFire)//Makes sure the amount of time elapsed is greater than the next available fire
		{
			fire();
		}
	}
	
	/**
	 * Sets a new random coordinate
	 * to go to. Randomly picks between
	 * moving horizontally and vertically and then generates a coordinate either
	 * with a change in x or y that is its new patrol target.
	 */
	public void newPatrol()
	{
		if((int)(Math.random()*2) == 0)
		{
			patrolX = (int)(Math.random()*(850))+75;
			patrolY = (int) y;
		}
		else
		{
			patrolY = (int)(Math.random()*(425))+175/2;
			patrolX = (int) x;
		}
	}
	
	/**
	 * Shoots shots from the top and sides of the screen and then also shoots
	 * diagonally from the actual Rook
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
				new Projectile(game, player.getX(), 20, "EnemyShot.png", 1, 1, Math.toRadians(90), "enemy");
				fireRate = 30;
				break;
			case 1:
				new Projectile(game, player.getX(), 580, "EnemyShot.png", 1, 1,  Math.toRadians(270), "enemy");
				break;
			default://once the cycle is complete and attackNum is not caught by any attack it is reset to -1(because attackNum is incremented right after so effectively 0) for new cycle
				attackNum = -1;
				phaseCount++;
				if(phaseCount > 7)
				{
					phase++; //Swaps to next phase if this phase has been repeated 2 times
					phaseCount = 0;
					fireRate = 500;
				}
				break;
			}
			break;
		case 1:
			switch(attackNum)
			{
			case 0:
				for(int i = 0; i < 600/200; i++)
				{
					attackDir = new UnitVector(player.getX(), player.getY()-i*200);
					new Projectile(game, 0, i*200, "EnemyShot.png", 1, 1, attackDir.getAngle(), "enemy");
				}
				fireRate = 20;
				break;
			case 1:
				for(int i = 0; i < 600/200; i++)
				{
					attackDir = new UnitVector(player.getX()-970, player.getY()-i*200);
					new Projectile(game, 970, i*200, "EnemyShot.png", 1, 1, attackDir.getAngle(), "enemy");
				}
				break;
			default://once the cycle is complete and attackNum is not caught by any attack it is reset to -1(because attackNum is incremented right after so effectively 0) for new cycle
				attackNum = -1;
				phaseCount++;
				if(phaseCount > 5)
				{
					phase++; //Swaps to next phase if this phase has been repeated 50 times
					phaseCount = 0;
					fireRate = 500;
				}
				break;
			}
			break;
		case 2:
			switch(attackNum)
			{
			case 0:
				new Projectile(game, x, y, "EnemyShot.png", 1, 0.5, Math.toRadians(45), "enemy");
				fireRate = 35;
				break;
			case 1:
				new Projectile(game, x, y, "EnemyShot.png", 1, 0.5, Math.toRadians(135), "enemy");
				break;
			case 2:
				new Projectile(game, x, y, "EnemyShot.png", 1, 0.5, Math.toRadians(225), "enemy");
				break;
			case 3:
				new Projectile(game, x, y, "EnemyShot.png", 1, 0.5, Math.toRadians(315), "enemy");
				break;
			default://once the cycle is complete and attackNum is not caught by any attack it is reset to -1(because attackNum is incremented right after so effectively 0) for new cycle
				attackNum = -1;
				phaseCount++;
				if(phaseCount > 40)
				{
					phase++; //Swaps to next phase if this phase has been repeated 50 times
					phaseCount = 0;
					fireRate = 500;
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
