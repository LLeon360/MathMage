/**
 * Weapon.java
 * 
 * Defines a weapon which can be used 
 * by the player whose attack function 
 * is called when the left mouse
 * button is pressed. Has a cooldown
 * to moderate attack speed
 * 
 * The player has a Weapon object which is set
 * to whatever active weapon is selected. At the start 
 * the player is given a ManaSpell weapon. Polymorphism is
 * used to allow the player to use different weapons defined in
 * different classes through the same Weapon object
 * 
 * @author Leon Liu
 * @since 4/9/20
 */
public class Weapon 
{
	/**
	 * Field variables:
	 * nextFire is the next time update
	 * before the weapon can be fired
	 * 
	 * fireRate is the minimum cooldown
	 * between attacks in milliseconds
	 * 
	 * damage is the amount of damage the
	 * weapon does on hit
	 * 
	 * game is an alias of the GamePanel
	 * 
	 * player is an alias of the PlayerObject
	 */
	protected int nextFire, fireRate;
	protected PlayerObject player;
	protected GamePanel game;
	protected double damage;
	protected String name;
	protected double baseDamage;

	/**
	 * Constructor to be called by
	 * the Player object with a parameter
	 * of "this".
	 * Sets default fire rate and also
	 * sets the GamePanel alias. Sets
	 * nextFire to 0 so the weapon
	 * can immediately be used.
	 */
	public Weapon(PlayerObject player)
	{
		fireRate = -1;
		nextFire = 0;
		game = player.getGame();
		this.player = player;
	}

	/**
	 * Constructor allowing the weapon to specify fire
	 * rate and damage to be called in the subclass
	 */
	public Weapon(PlayerObject player, int fireRate, double damage)
	{
		this(player);
		this.fireRate = fireRate;
		this.damage = damage;
		baseDamage = damage;
	}

	/**
	 * Calls the fire method if the cooldown is
	 * finished
	 */
	public void tryFire()
	{
		if(game.getTime() > nextFire)//Makes sure the amount of time elapsed is greater than the next available fire
		{
			fire();
		}
	}

	/**
	 * Resets the next possible
	 * fire time using the cooldown.
	 * Method will be overridden by
	 * weapon child classes which will
	 * define the specific attack that is
	 * done.
	 */
	public void fire()
	{
		nextFire = game.getTime() + fireRate;//sets the next available fire based of fireRate cooldown value
	}

	/**
	 * Returns the weapon's name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Calculates the weapon damage by
	 * adding the weapon's base damage to 
	 * the player's additional damage
	 */
	public void updateDamage()
	{
		damage = baseDamage + player.getDamage();
	}

	/**
	 * Sets the next fire
	 */
	public void setNextFire(int nextFire) 
	{
		this.nextFire = nextFire;
	}

	public double getDamage() 
	{
		return damage;
	}
	
	public double getBaseDamage() 
	{
		return baseDamage;
	}
}
