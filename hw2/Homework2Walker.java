// Homework 2: Homework 2
// Student Name: Mathew Walker
// Course: CIS357, Winter 2017
// Instructor: Dr. Cho
// Date finished: 2/14/2017
// Program Discription: This program generates random or fixed 2D arrays based on user input and finds four consecutive numbers
//                      and prints the sum of those numbers.

import java.util.Scanner;
import java.lang.Math;

/**
 * Class Homework2Walker takes user input and creates fixed or random arrays, it reports consecutive fours and sums of consecutive
 * fours. 
 */
public class Homework2Walker {
  public static void main(String[] args)
  {
	// Ask for user input using scanner
    int input = userInput();
	
	// Test Case 1 or 2
	if (input == 1)  //Case 1
	{
      testWithFixedValues(); 
	}
	else if (input == 2)  //Case 2
    {
      testWithRandomValues();
    }
	else
    {
      System.out.print("Please enter a valid choice");
    }


	// if statement based on user input
	// function calls based on user input
    	
  }
  /**
   * This function takes user input deciding to use fixed values or random values for 2D array
   *
   */
  public static int userInput()
  {
    Scanner in = new Scanner(System.in);
	System.out.print("Enter a choice: (1 for fixed value, 2 for random values) ");
	int n = in.nextInt();
	return n;
  }
  /**
   * This function generates a random 2D array with specified row and column length from user input passed
   * to function 
   */
  public static int[][] generate2DArray(int row, int col)
  {
    int[][] random = new int [row][col];
	for(int i = 0; i < row; i++)
    {
	  for(int j = 0; j < col; j++)
      {
      random[i][j] = (int) (Math.random() * 10);
      }
	}
	return random;
  }
  /**
   * This function takes an array and checks for consecutive for horizontally and vertically and prints sum of 
   * consecutive four, or no consecutive four found
   */
  public static void checkConsecFour(int[][] array1)
  {
	  int found = 0;
	 //Check rows for Consecutive Four
     for(int i = 0; i < array1.length; i++) 
	 {
       for(int j = 0; j < array1[i].length - 3; j++)
       {
	     if(array1[i][j] == array1[i][j + 1])
         {
           if(array1[i][j] == array1[i][j + 2])
           {   
             if(array1[i][j] == array1[i][j + 3])
             {
			 found = 1;
             System.out.println("Consecutive four: found ([" + i + "," + j + "] - [" + i + "," + (j + 3) + "]");
		     System.out.println("Sum of Consecutive four: " + (array1[i][j] * 4));
             }
		   }
		 }
	   }
     }
	 //Check columns for Consecutive Four
     for(int i = 0; i < array1.length - 3; i++) 
	 {
       for(int j = 0; j < array1[i].length; j++)
       {
	     if(array1[i][j] == array1[i + 1][j])
         {  
           if(array1[i][j] == array1[i + 2][j])
           {   
             if(array1[i][j] == array1[i + 3][j])
             {
			 found = 1;
             System.out.println("Consecutive four: found ([" + i + "," + j + "] - [" + (i + 3) + "," + (j) + "]");
			 System.out.println("Sum of Consecutive four: " + (array1[i][j] * 4));
             }
		   }
		 }
	   }
     }
     if(found == 0)
     {
        System.out.println("No Consecutive four found");
	 }		 
  }

  
  /**
   * This function creates two fixed 2D Arrays checks for consecutive four with checkConsecFour function
   * and prints results to screen
   */
  public static void testWithFixedValues()
  {
    int[][] array1 = {
      {0,1,0,3,1,6,1},
      {0,1,6,8,6,0,1},
      {5,6,2,1,8,2,9},
      {6,5,6,1,1,9,1},
      {1,3,6,1,4,0,7},
      {3,3,3,3,4,0,7}
     };

    checkConsecFour(array1);
    for(int row = 0; row < array1.length; row++) 
	{ 
      for(int column = 0; column < array1[row].length; column++) 
	  {
        System.out.print(array1[row][column] + " "); 
      } 
	  System.out.println(); 
    }
	
	int[][] array2 = {
      {0,1,0,3,1,6,1},
      {0,2,6,8,6,0,1},
      {0,6,2,1,6,2,9},
      {0,5,6,6,1,9,1},
      {1,3,6,1,4,0,7},
      {3,6,3,3,4,0,7}
    };
	checkConsecFour(array2);
    for(int row = 0; row < array2.length; row++) 
	{ 
      for(int column = 0; column < array2[row].length; column++) 
	  {
        System.out.print(array2[row][column] + " "); 
      } 
	  System.out.println(); 
    }
  }
  
  /**
   * This function generates a random 2D array using generate2DArray function, finds consecutive four and sum with
   * checkConsecFour function and prints to screen.
   */
  public static void testWithRandomValues()
  {
	Scanner in = new Scanner(System.in);
	System.out.print("Enter row and column length(between 4 and 11): ");
	int r = in.nextInt();
	int l = in.nextInt();
    int[][] random;	
	random = generate2DArray(r,l);
	checkConsecFour(random);
	for(int row = 0; row < random.length; row++) 
	{ 
      for(int column = 0; column < random[row].length; column++) 
	  {
        System.out.print(random[row][column] + " "); 
      } 
	  System.out.println(); 
    }
  }

}