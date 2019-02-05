/**
 * Class handles the game results of the Scrambler game. It keeps a current record
 * with the players name, rounds, wins, losses, and guesses. It has a toString method
 * to summarize all of the data at the end of the game. It also manages all time player
 * data which is saved to a file.
 *
 * @author Mathew Walker
 */

public class Player {
	private String name;
	private Integer rounds;
	private Integer wins;
	private Integer losses;
	private Integer guesses;
	
	/**  
	 * default class constructor for Results takes a filename for results and players name
	 * @param file filename for the program to save results
	 * @param playerName name of the player playing the game
	 */
	public Player(String playerName) {
		name = playerName;
		rounds = new Integer(0);
		wins = new Integer(0);
		losses = new Integer(0);
		guesses = new Integer(0);
	}
	
	/** increments wins and rounds */
	public void win() {
		wins = new Integer(wins.intValue() + 1);
		rounds = new Integer(rounds.intValue() + 1);
	}
	
	/** increments losses and rounds */
	public void lose() {
		losses = new Integer(losses.intValue() + 1);
		rounds = new Integer(rounds.intValue() + 1);
	}
	
	/** increments guesses */
	public void guess() {
		guesses = new Integer(guesses.intValue() + 1);
	}
	
	// all Get and Set methods after this point
	public void setRounds(Integer rounds) {
		this.rounds = rounds;
	}
	
	public void setWins(Integer wins) {
		this.wins = wins;
	}
	
	public void setLosses(Integer losses) {
		this.losses = losses;
	}
	
	public void setGuesses(Integer guesses) {
		this.guesses = guesses;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getRounds() {
		return rounds;
	}
	
	public Integer getWins() {
		return wins;
	}
	
	public Integer getLosses() {
		return losses;
	}
	
	public Integer getGuesses() {
		return guesses;
	}

}