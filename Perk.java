/**
 * Perk.java
 * 
 * Generates a perk for when the player gets
 * a question correct and wants to choose the
 * perk bonus instead of the weapon bonus.
 * Modifies the players stats by randomly
 * selecting a perk and augmenting the stat 
 * based on a randomly generated amount. 
 * Also can generate a debuff for if the
 * question is answered incorrectly.
 * 
 * @author Leon Liu
 * @since 5/7/20
 */
public class Perk 
{
	/**
	 * player is an alias of PlayerObject
	 * 
	 * rng is the index of the perk and is
	 * randomly selected
	 * 
	 * perks is a String array of the different
	 * perks
	 * 
	 * debuffs is a String array of the different
	 * debuff types
	 * 
	 * amount is the degree of the stat upgrade
	 * that is randomly generated depending on
	 * the perk type
	 */
	private PlayerObject player;
	private int rng;
	private String[] perks;
	private String[] debuffs;
	private double amount;
	
	/**
	 * Sets the player object reference
	 * and also sets the available perks
	 * which include: healing, max health increase,
	 * and speed increase.
	 */
	public Perk(PlayerObject player) 
	{
		this.player = player;
		perks = new String[] 
		{
			"Heal", "Max Health Up", "Speed Up", "Damage Up"
		};
		debuffs = new String[]
		{
			"Hurt By", "Max Health Down", "Speed Down", "Damage Down"
		};
	}
	
	/**
	 * Generates a random perk and description
	 * string of text
	 * 
	 * Method is called when a question is
	 * answered correctly and the player is
	 * given the perk or a weapon.
	 */
	public String choosePerk()
	{
		rng = (int)(Math.random()*perks.length);
		switch(perks[rng])
		{
		case "Heal":
			amount = (int)(Math.random()*6)*.5+5;
			amount = Math. round(amount * 10.0) / 10.0;
			break;
		case "Max Health Up":
			amount = (int)(Math.random()*11)*0.5+1;
			amount = Math. round(amount * 10.0) / 10.0;
			break;
		case "Speed Up":
			amount = 0.05;
			break;
		case "Damage Up":
			amount = (int)(Math.random()*4)*0.1+0.1;
			amount = Math. round(amount * 10.0) / 10.0;
			break;
		default:
			System.out.println(perks[rng] + " not caught by switch statement.");
			break;	
		}
		
		return perks[rng] + " " + amount; 
	}
	
	/**
	 * Selects the amount based on a randomly
	 * selected debuff and generates a String 
	 * describing the debuff.
	 * 
	 * Method is called when the answer is selected
	 * incorrectly
	 */
	public String chooseDebuff()
	{
		rng = (int)(Math.random()*debuffs.length);
		switch(debuffs[rng])
		{
		case "Hurt By":
			amount = 1;
			break;
		case "Max Health Down":
			amount = 1;
			break;
		case "Speed Down":
			amount = 0.05;
			break;
		case "Damage Down":
			amount = 0.1;
			break;
		default:
			System.out.println(debuffs[rng] + " not caught by switch statement.");
			break;	
		}
		return debuffs[rng] + " " + amount;
	}
	
	/**
	 * Applies the stat changes
	 * Will be called when the player selects to
	 * get the perk instead of the weapon.
	 * 
	 * Updates the player's weapon to change the damage in
	 * accordance to the player's stats
	 */
	public void activatePerk()    
	{
		switch(perks[rng])
		{
		case "Heal":
			player.heal(amount);
			break;
		case "Max Health Up":
			player.setMaxHealth(Math.round((player.getMaxHealth()+amount) * 10.0) / 10.0);
			break;
		case "Speed Up":
			player.setSpeed(Math.round((player.getSpeed()+amount) * 100.0) / 100.0);
			break;
		case "Damage Up":
			player.setDamage(Math.round((player.getDamage()+amount) * 10.0) / 10.0);
			player.getWeapon().updateDamage();
			break;
		default:
			System.out.println(perks[rng] + " not caught by switch statement.");
			break;	
		}
	}
	
	/**
	 * Applies the stat changes
	 * 
	 * Updates the player's weapon to change the damage in
	 * accordance to the player's stats
	 */
	public void activateDebuff()
	{
		switch(debuffs[rng])
		{
		case "Hurt By":
			if(player.getHealth() > 2)
				player.damage(amount);
			break;
		case "Max Health Down":
			if(player.getMaxHealth() > 5)
			{
				player.setMaxHealth(Math.round((player.getMaxHealth()-amount) * 10.0) / 10.0);
				if(player.getHealth() > player.getMaxHealth())
				{
					player.setHealth(player.getMaxHealth()); // Make sure that if the player's health doesn't exceed max health if max health drops
				}
			}
			break;
		case "Speed Down":
			if(player.getSpeed() > 0.6)
				player.setSpeed(Math.round((player.getSpeed()-amount) * 100.0) / 100.0);
			break;
		case "Damage Down":
			if(player.getDamage() > -0.5)
				player.setDamage(Math.round((player.getDamage()-amount) * 10.0) / 10.0);
				player.getWeapon().updateDamage();
			break;
		default:
			System.out.println(debuffs[rng] + " not caught by switch statement.");
			break;	
		}
	}
}
