import java.io.*;
import java.util.*;

public class ScramTest {
  public static void main(String[] args) throws Exception {
    System.out.println("Testing normal use...\n");
    Scramble theScramble = new Scramble("scramwords.txt"); 
    String word = theScramble.getRealWord();
    while (word != null) {
      System.out.println("Real word is : " + 	word);
      System.out.println("Scrambled word : " + theScramble.getScrambledWord());
      word = theScramble.getRealWord();
    }
        
    System.out.println("\nTesting special cases...\n");
    theScramble = new Scramble("scramwords.txt"); 

    System.out.println("Two calls to getRealWord(): ");
    String word1 = theScramble.getRealWord();
    String word2 = theScramble.getRealWord();
    System.out.println("Word 1: " + word1);
    System.out.println("Word 2: " + word2);

    System.out.println("\nTwo calls to getScrambledWord(): ");
    word1 = theScramble.getScrambledWord();
    word2 = theScramble.getScrambledWord();
    System.out.println("Scram word 1: " + word1);
    System.out.println("Scram word 2: " + word2);

    System.out.println("\nNow a call to each: " );
    word1 = theScramble.getRealWord();
    word2 = theScramble.getScrambledWord();
    System.out.println("Word: " + word1);
    System.out.println("Scram word: " + word2);
  }
}
