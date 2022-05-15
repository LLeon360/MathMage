/**
 * HealthBar.java
 * 
 * The width of this object changes
 * based on what fraction of the
 * total player health the player is
 * at to display the percentage of health
 * 
 * @author Leon Liu
 * @since 4/23/20
 *
 */
public class HealthBar extends GameObject 
{
	public HealthBar(GamePanel game, double x, double y)
	{
		super(game, x, y, "HealthBarContent.png"); //23 4
	}

	/**
	 * Returns the width as a fraction of the
	 * total width to scale of the player health
	 * percentage
	 */
	public int getWidth()
	{
		double playerHealth = game.getPlayer().getHealth();
		double playerMaxHealth = game.getPlayer().getMaxHealth();
		return (int)(super.getWidth()*(playerHealth/playerMaxHealth));
	}
}
