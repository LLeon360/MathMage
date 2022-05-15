/**
 * Cyclops.java
 * 
 * Boss that uses PursuitEnemy AI instead of the
 * usual Boss AI so that it is more aggressive. Attacks with waves of
 * projectiles, some directed towards the player and others in a circle
 * around the boss.
 * 
 * @author Leon Liu
 * @since 5/22/20
 */
public class Cyclops extends PursuitEnemy
{


	/** 
	 * attackNum is the type of attack that will be used for the specific fire
	 * 
	 * phase is the type of attack sequence
	 * 
	 * phaseCount is the number of times an attack phase has been repeated
	 */
	private int attackNum, phase, phaseCount;
	
	/**
	 * Calls the superclass constructor
	 * and sets the sprite to the
	 * Cyclops sprite and sets
	 * specific value to the boss 
	 * data
	 */
	public Cyclops(GamePanel game, double x, double y) 
	{
		super(game, x, y, "Cyclops.png");
		pursuitRange = 250;
		fireRange = 800;
		speed = adjustmentSpeed = 0.6;
		fireRate = 400;
		
		health = maxHealth = 400;
	}

	
	/**
	 * Pursues the player at higher speeds to go for a close range wave attack and then slows down and 
	 * shoots a circle of shots instead and alternates between with homing attacks at the start of each phase.
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
				fireRate = 200;
				fireRange = 400; //Makes the boss move really close to the player by reducing fire range and increasing speed
				pursuitRange = 330;
				speed = adjustmentSpeed = 1;
				break;
			case 1:
				for(int i = 0; i < 7; i++)
					new Projectile(game, x, y, "EnemyShot.png", 1, 0.8, attackDir.getAngle() + Math.toRadians(20*i-60), "enemy");
				fireRange = 400; //Gives more fire range to leave more room
				break;
			case 2:
				for(int i = 0; i < 9; i++)
					new Projectile(game, x, y, "EnemyShot.png", 1, 0.8, attackDir.getAngle() + Math.toRadians(25*i - 100), "enemy");
				break;
			default://once the cycle is complete and attackNum is not caught by any attack it is reset to -1(because attackNum is incremented right after so effectively 0) for new cycle
				attackNum = -1;
				phaseCount++;
				if(phaseCount > 5)
				{
					phase++; //Swaps to next phase if this phase has been repeated 2 times
					phaseCount = 0;
					fireRate = 0;
					fireRange = 800;//Returns to normal fireRange until the boss goes to fire again
					pursuitRange = 450;
				}
				break;
			}
			break;
		case 1:
			switch(attackNum)
			{
			case 0:
				fireRate = 200;
				speed = adjustmentSpeed = 0.6;
				HomingEnemyShot homing = new HomingEnemyShot(game, x, y, 0.5, 0.6, game.getPlayer());
				homing.setLifetime(1000);
			case 1:
				for(int i = 0; i < 10; i++)
				{
					new Projectile(game, x, y, "EnemyShot.png", 1, 0.8, attackDir.getAngle()+Math.toRadians(36*i) ,"enemy");
				}            
				break;
			case 2:
				for(int i = 0; i < 10; i++)
				{
					new Projectile(game, x, y, "EnemyShot.png", 1, 0.8, attackDir.getAngle()+Math.toRadians(36*i + 18) ,"enemy");
				}
			default://once the cycle is complete and attackNum is not caught by any attack it is reset to -1(because attackNum is incremented right after so effectively 0) for new cycle
				attackNum = -1;
				phaseCount++;
				if(phaseCount > 5)
				{
					phase++; //Swaps to next phase if this phase has been repeated 50 times
					phaseCount = 0;
					fireRate = 0;
				}
				break;
			}
			break;
		default://once the cycle is complete and phase is not caught by any attack it is reset for new cycle
			phase = 0;
			attackNum = -1;
			fireRate = 700;
			break;
		}
		
		attackNum++;//moves onto the next attack
		super.fire();
	}

}
