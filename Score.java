/**
 * Score.java
 * 
 * Stores all information related to a
 * saved score
 * 
 * @author Leon Liu
 * @since 5/14/20
 */
public class Score 
{
	private int score, time, difficulty;
	private String name;
	
	/**
	 * Default constructor for field
	 * variables to be set to default values
	 */
	public Score() 
	{
		score = time = difficulty = -1;
		name = "";
	}
	
	/**
	 * Constructor for field variables in the order than they
	 * are read in from a score data file
	 */
	public Score(String name, int score, int time, int difficulty) 
	{
		this.name = name;
		this.score = score;
		this.time = time;
		this.difficulty = difficulty;
	}

	/**
	 * Getters and setters
	 */
	public int getScore() 
	{
		return score;
	}

	public void setScore(int score) 
	{
		this.score = score;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) 
	{
		this.time = time;
	}

	public int getDifficulty() 
	{
		return difficulty;
	}

	public void setDifficulty(int difficulty) 
	{
		this.difficulty = difficulty;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	/**
	 * Compares this score to another score to see
	 * which is higher placed. First compares
	 * which has a greater score then greater difficulty
	 * and then shortest time completed
	 * 
	 * Returns true if this objects score is greater than
	 * the other and false if it is lesser and true if they
	 * are equal because a order just needs to be decided and it doesn't
	 * affect a selection sort because objects aren't recompared and it
	 * really isn't likely for the exact same score to appear
	 * 
	 */
	public boolean compareTo(Score other)
	{
		if(score < other.getScore())
			return false;
		else if(score > other.getScore())
			return true;
		else
		{
			if(difficulty < other.getDifficulty()) 
				return false;
			else if(difficulty > other.getDifficulty())
				return true;
			else
			{
				if(time > other.getTime()) //unlike other score components a lesser time is desirable like golf!
					return false;
				else 
					return true;
			}
		}
		
	}
	
}
