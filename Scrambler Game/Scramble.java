import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.Random;

/**
 * Manages words for the game the Scrambler. It reads in words from a file and
 * adds them to an array. Once the player starts a game it will randomly select a word
 * from the array and scramble the word. It keeps track of which words are used 
 * and doesn't pick a new word until the most recent word is scrambled.
 */

public class Scramble {
	
	private ArrayList<String> wordList;
	int lock;
	String currentWord;
	String currentScrambledWord;
	
	/**
	 * Default class constructor for Scramble. Takes in a text file containing words.
	 * Reads in all of the words from the text file into an array list.
	 *
	 * @param file text file containing words to be scrambled
	 **/
	Scramble(String file) {
		try {
			wordList = new ArrayList<>();
			lock = 0;
			Scanner in = new Scanner(new File(file));
			while(in.hasNext()) {
				wordList.add(in.next());
			}
		}
		catch(FileNotFoundException ex){
			
		}
	}
	
	/**
	 * Gets the next word from the array list, but only if the previous word has been scrambled. 
	 * If the scramble method has not been called on the previous word, then it will repeat the 
	 * previous word.
	 *
	 * @return currentWord the next word in the array or previous word if it hasn't been scrambled.
   *         It will return null if there are no words left.
	 */
	public String getRealWord() {
		if(lock == 0) {
			if(!wordList.isEmpty()) {
				Random r = new Random();
				int index = r.nextInt(wordList.size());
				currentWord = wordList.get(index);
				wordList.remove(index);
				lock = 1;
			}
			else {
				currentWord = null;
			}
		}
		return currentWord;
	}
	
	/**
	 * Scrambles the current word using the Random class.
	 *
	 * @return currentScrambleWord the current word scrambled 
	 * @return null if the current word is null.
	 */
	public String getScrambledWord() {
		Random random = new Random(7);
		if (currentWord != null) {
			if(lock == 1) {
				char[] chars = currentWord.toCharArray();
				for(int i = 0; i < chars.length; i++) {
					int j = random.nextInt(chars.length);
					
					char temp = chars[i];
					chars[i] = chars[j];
					chars[j] = temp;
				}
				currentScrambledWord = new String(chars);
				lock = 0;
			}
			return currentScrambledWord;
		}
		else {
			return null;
		}
	}
}