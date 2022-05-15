/**
 * ScoreHolderPanel.java
 * 
 * A panel which hold the information for a 
 * single highscore used in the Score Panel
 * for a top 10 leaderboard
 * 
 * @author Leon Liu
 * @since 5/14/20
 */
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreHolderPanel extends JPanel
{
	private JLabel rank, name, score, time, difficulty;
	
	/**
	 * Sets up the flow layout with fixed dimensions according
	 * to expected length
	 */
	public ScoreHolderPanel()
	{
		setLayout(new FlowLayout(FlowLayout.LEFT, 0,0));
		this.rank = new JLabel("-");
		this.name = new JLabel("-");
		this.score = new JLabel("-");
		this.time = new JLabel("-");
		this.difficulty = new JLabel("-");
		rank.setFont(Game.NORMALFONT);
		name.setFont(Game.NORMALFONT);
		score.setFont(Game.NORMALFONT);
		time.setFont(Game.NORMALFONT);
		difficulty.setFont(Game.NORMALFONT);
		rank.setPreferredSize(new Dimension(70, 50));
		name.setPreferredSize(new Dimension(400, 50));
		score.setPreferredSize(new Dimension(150, 50));
		time.setPreferredSize(new Dimension(200, 50));
		difficulty.setPreferredSize(new Dimension(125, 50));
		add(rank);
		add(name);
		add(score);
		add(time);
		add(difficulty);

		setOpaque(false);
	}
	
	/**
	 * Setters
	 */
	public void setRank(String rankText) 
	{
		rank.setText(rankText);
	}
	public void setName(String nameText) 
	{
		name.setText(nameText);
	}
	public void setScore(String scoreText) 
	{
		score.setText(scoreText);
	}
	public void setTime(String timeText) 
	{
		time.setText(timeText);
	}
	public void setDifficulty(String difficultyText) 
	{
		difficulty.setText(difficultyText);
	}
	
}
