/**
 * ResultsPanel.java
 * 
 * Shows the result of a completed
 * math trial. Displays if the selected
 * answer was correct or incorrect. Also shows
 * rewards or punishments for the respective
 * result.
 * 
 * @author Leon Liu
 * @since 5/1/20
 */
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultsPanel extends ScrollingBGPanel implements ActionListener
{
	/**
	 * player is an alias of the PlayerObject instance
	 * 
	 * resultText displays whether the answer was correct
	 * 
	 * answerText displays the correct answer
	 * 
	 * statBonus is the perk available
	 * 
	 * weaponChocie is the weapon available
	 * 
	 * effects is the bootom half panel which
	 * will be a downgrade if the answer is wrong
	 * or a reward choice if the answer is correct
	 * 
	 * card is the card layout for the JPanel effects
	 * 
	 * main is an alias of the Game instance
	 * 
	 * question and answer are the question and correct
	 * answer from the MathPanel instance
	 * 
	 * perkGen is a Perk object which can generate random perks and applies them to
	 * the player. Can also generate random debuffs and apply them to the player
	 * 
	 * lowTierWeapon is an array of lesser weapons which are available in the lower waves
	 * 
	 * weaponChoice is a randomly chosen weapon from a weapon array
	 */
	private PlayerObject player;
	private JLabel resultText;
	private JLabel answerText;
	private JLabel perkText;
	private JLabel weaponText;
	private JPanel effects;
	private JLabel debuffText;
	private CardLayout card;
	
	private GamePanel game;
	private Game main;
	
	private String question,answer;
	
	private Perk perkGen;
	private Weapon[] lowTierWeapons;
	private Weapon[] midTierWeapons;
	private Weapon[] highTierWeapons;
	private Weapon weaponChoice;
	
	/**
	 * Sets the weapon arrays and
	 * the perk generator using the player
	 * alias for the parameter.
	 * 
	 * Sets up the panel layout and gives
	 * placeholder text
	 */
	public ResultsPanel(Game main) 
	{
		super("MenuBG.png");
		
		question = answer = "";
		this.main = main;
		game = main.getGame();
		player = game.getPlayer();
		
		//Sets the array of possible weapon choices after the player instance is set

		lowTierWeapons = new Weapon[] {
										new ManaSpell(player), new ManaWave(player), new ManaSnipe(player), new ManaFan(player),
										new ManaBarrage(player)
									  };
		midTierWeapons = new Weapon[] {
										new RainSpell(player), new MeteorSpell(player), new ManaLaser(player), new ManaCannon(player),
										new ManaBurst(player)
									  };
		highTierWeapons = new Weapon[] {
										new FlameSpell(player), new MeteorSpell(player), new ManaLaser(player), new FireworkSpell(player)
									  };
		
		perkGen = new Perk(player);
		
		setLayout(new BorderLayout());
		
		//placeholder values which aren't seen because values are set when it is switched in
		resultText = new JLabel("Placeholder");
		answerText = new JLabel("Placeholder");
		perkText = new JLabel("Placeholder");
		weaponText = new JLabel("Placeholder");
		
		resultText.setFont(Game.TITLEFONT);
		answerText.setFont(Game.NORMALFONT);
		
		JPanel topHalf = new JPanel();
		topHalf.setLayout(new GridLayout(2, 1));
		
		JPanel resultTextHolder = new JPanel();
		resultTextHolder.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		resultTextHolder.setOpaque(false);
		JPanel answerTextHolder = new JPanel();
		answerTextHolder.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
		answerTextHolder.setOpaque(false);
		resultTextHolder.add(resultText);
		answerTextHolder.add(answerText);
		
		topHalf.add(resultTextHolder);
		topHalf.add(answerTextHolder);
		topHalf.setOpaque(false);
		
		effects = new JPanel();
		
		card = new CardLayout();
		effects.setLayout(card);
		
		JPanel correct = new JPanel();
		JPanel incorrect = new JPanel();
		
		correct.setLayout(new GridLayout(2, 1));
		
		JPanel selectionTextPanel = new JPanel();
		selectionTextPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
		JLabel selectionText = new JLabel("Select your boon:");
		selectionText.setFont(Game.NORMALFONT);
		selectionTextPanel.add(selectionText);
		selectionTextPanel.setOpaque(false);
		
		correct.add(selectionTextPanel);
		
		//button panel to select perk or weapon
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 0));
		buttonPanel.setOpaque(false);

		JButton boon1 = new JButton("Recieve Perk");
		JButton boon2 = new JButton("New Weapon");
		boon1.addActionListener(this);
		boon2.addActionListener(this);
		
		boon1.setFont(Game.NORMALFONT);
		boon2.setFont(Game.NORMALFONT);
		
		boon1.setPreferredSize(new Dimension(400, 60));
		boon2.setPreferredSize(new Dimension(400, 60));
		boon1.setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 10));
		boon2.setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 10));
		
		buttonPanel.add(boon1);
		buttonPanel.add(boon2);
		
		JPanel choicePanel = new JPanel();
		choicePanel.setLayout(new GridLayout(2, 1));
		choicePanel.add(buttonPanel);
		choicePanel.setOpaque(false);
		
		JPanel descPanel = new JPanel();
		descPanel.setLayout(new BorderLayout());
		descPanel.setOpaque(false);
		
		//Panels with weapon and perk option
		JPanel weaponTextPanel = new JPanel();
		weaponTextPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		weaponTextPanel.setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 5));
		weaponTextPanel.setPreferredSize(new Dimension(500, 100));
		weaponText.setFont(Game.NORMALFONT);
		weaponTextPanel.add(weaponText);
		weaponTextPanel.setBackground(new Color(135, 210, 105));
		JPanel perkTextPanel = new JPanel();
		perkTextPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		perkTextPanel.setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 5));
		perkTextPanel.setPreferredSize(new Dimension(495, 100));
		perkText.setFont(Game.NORMALFONT);
		perkTextPanel.add(perkText);
		perkTextPanel.setBackground(new Color(135, 210, 105));
		
		descPanel.add(perkTextPanel, BorderLayout.WEST);
		descPanel.add(weaponTextPanel, BorderLayout.CENTER);
		
		choicePanel.add(descPanel);
		
		correct.add(choicePanel);
		correct.setOpaque(false);
		
		//incorrect panel which has the debuff text and continue button
		incorrect.setLayout(new GridLayout(2,1));
		JPanel debuffTextHolder = new JPanel();
		debuffTextHolder.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
		debuffText = new JLabel("");
		debuffText.setFont(Game.NORMALFONT);
		debuffTextHolder.add(debuffText);
		debuffTextHolder.setOpaque(false);
		JButton continueButton = new JButton("Continue");
		continueButton.setFont(Game.NORMALFONT);
		continueButton.addActionListener(this);
		continueButton.setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 5));
		incorrect.add(debuffTextHolder);
		incorrect.add(continueButton);
		
		incorrect.setOpaque(false);
		
		//adds incorrect and correct panels to card layout to switch between accordingly
		effects.add(correct, "correct");
		effects.add(incorrect, "incorrect");
		effects.setOpaque(false);
		
		add(topHalf, BorderLayout.CENTER);
		add(effects, BorderLayout.SOUTH);
		setBorder(BorderFactory.createLineBorder(Game.BORDER_COLOR, 10));
		
	}
	
	/**
	 * Responds to button presses for weapon or perk choice
	 * and then swaps back to the game panel
	 */
	public void actionPerformed(ActionEvent e)
	{
		super.actionPerformed(e);
		switch(e.getActionCommand())
		{
		case "New Weapon":
			weaponChoice.setNextFire(0);
			player.setWeapon(weaponChoice);
			main.swap("game");
			break;
		case "Recieve Perk":
			perkGen.activatePerk();
			main.swap("game");
			break;
		case "Continue":
			perkGen.activateDebuff();
			main.swap("game");
			break;
		}
		//updates the weapon damage whenever any changes are made to
		//ensure that the stats of the weapon are accurate
		player.getWeapon().updateDamage();
	}
	
	/**
	 * 
	 * Shows a correct or wrong panel
	 * depending on if the answer was 
	 * correct which is determined 
	 * by the math panel
	 * 
	 * If correct, randomizes the boons.
	 * If incorrect randomizes the punishment
	 */
	public void setCorrect(boolean isCorrect)
	{
		if(isCorrect)
		{
			card.show(effects, "correct");
			resultText.setText("Correct");
			setBG("CorrectBG.png");
			
			randomBoons();
		}
		else 
		{
			card.show(effects, "incorrect");
			resultText.setText("Incorrect");
			setBG("IncorrectBG.png");
			
			randomPunishment();
		}
		answerText.setText(question.substring(0, question.length()-1) + answer);
	}
	
	/**
	 * Sets the question
	 */
	public void setQuestion(String question)
	{
		this.question = question;
 	}
	
	/**
	 * Sets the answer
	 */
	public void setAnswer(String answer)
	{
		this.answer = answer;
	}
	
	/**
	 * Selects a random perk and weapon depending
	 * on the wave number to balance the strength
	 * 
	 * Updates the text labels to display the 
	 * new perk and weapon
	 */
	public void randomBoons()
	{	
		Weapon[] weaponArr = new Weapon[0];
		//selects the correct weapon array based on wave number
		if(game.getWave() <= 10)
		{
			weaponArr = lowTierWeapons;
		}
		else if(game.getWave() <= 20)
		{
			weaponArr = midTierWeapons;
		}
		else
		{
			weaponArr = highTierWeapons;
		}
		
		do
		{
			weaponChoice = weaponArr[(int)(Math.random()*weaponArr.length)];	
		}while(weaponChoice.getName().equals(player.getWeapon().getName()));
		
		perkText.setText(perkGen.choosePerk());
		weaponText.setText(weaponChoice.getName());
	}
	
	/**
	 * Selects a random debuff and sets the display
	 * label to describe it
	 */
	public void randomPunishment()
	{
		debuffText.setText(perkGen.chooseDebuff());
	}
}
