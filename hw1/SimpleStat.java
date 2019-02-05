// Homework 1: Simple Statistics Program
// Student Name: Mathew Walker
// Course: CIS357, Winter 2017
// Instructor: Dr. Cho
// Date finished: ..
// Program Discription: ..

import java.util.Scanner;
import java.lang.Math;

/**
 *@author Mathew Walker 
 * 
 *  The SimpleStat class contains methods that read input from user, calculate the mean and standard
 *  deviation and print the results.
 */

public class SimpleStat
{
	/**
	 *
	 * Main method for class SimpleStat
	 */
	public static void main (String args[])
	{
		// declare variables
		/** dblArray array of double for user input */
		double[] dblArray = new double[5];
		/** Double mean to hold mean variable */
		Double mean;
		/** Double std to hold standard deviation variable */
		Double std;
		
		// read input from user by calling readInput()
		dblArray = readInput();
		
		//calculate mean by calling findMean()
		mean = SimpleStat.findMean(dblArray);
		
		//calculate the standard deviation by calling findStd()
		std = SimpleStat.findStd(dblArray, mean);
		
		// print out the result by calling printResult()
		SimpleStat.printResult(mean,std);
		
	}
	
    /**
	 *Class method takes length of array from user and array values
	 *
	 *
	 *@return returns an array
	 */
	 public static double[] readInput() {
		 Scanner in = new Scanner(System.in);
		 System.out.println("hellois fun/rJava");
		 int n = in.nextInt();
		 double[] ary = new double[n];
		 for (int i = 0; i < n; i++)
			 ary[i] = in.nextDouble();
		   return ary;
	 }
	 
	 /** 
	  *Class method calculates mean from user input array
	  *
	  *@param takes double type array
	  *@return returns a double
	  */
	  private static double findMean(double[] dblArray) {
		  double sum = 0;
		  for(int i = 0; i < dblArray.length; i++) {
			  sum += dblArray[i];
		  }
		  return sum / dblArray.length;
	  }
	 
	  /**
       *Private class method calculates std from user input and mean also uses Math.pow method
       *
	   *@param takes double type array and Double type mean
       *@return returns a double
       */
       private static double findStd(double[] dblArray, Double mean) {
		   double sum = 0;
		   for (int i = 0; i < dblArray.length; i++) {
			   sum += Math.pow((dblArray[i] - mean), 2);	
		   }
		   return Math.sqrt(sum / dblArray.length);
	   }
	   
	   /**
	    *Private class method prints results to user
		*
		*@params takes Double type mean and std
		*/
		private static void printResult(Double mean, Double std) {
			System.out.printf("Mean: %1.2f\n", mean);
			System.out.printf("Standard Deviation: %1.2f\n", std);
		}  
}
