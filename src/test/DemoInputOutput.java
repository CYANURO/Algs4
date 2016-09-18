package test;

import java.util.Random;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.Out;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class DemoInputOutput {
	
	/**
	 * Demo how to use StdIn to read user input and write to the screen
	 */
	private static void demoReadingUserInput() {
		StdOut.println("Reading user input: ");
		StdOut.print("Please enter 3 numbers separated by a space: ");
		double n1 = StdIn.readDouble();
		double n2 = StdIn.readDouble();
		double n3 = StdIn.readDouble();
		StdOut.printf("%.1f + %.1f + %.1f = %.1f %n", n1, n2, n3, n1 + n2 + n3 );
	}
	
	/**
	 * Writes letters a - z to the file specified in the argument
	 * @param fileName
	 */
	public static void demoWritingToFile(String fileName) {
		StdOut.println("Writing to a File.");
		Out out = new Out(fileName);
		for (char c = 'a'; c <= 'z'; c++) {
			out.print(c + " ");
		}
		out.println();
	}
	
	/**
	 * Reads in text from a file and prints it to the screen
	 * @param fileName
	 */
	public static void demoReadingFromFile(String fileName) {
		StdOut.print("Reading from a File: ");
		In in = new In(fileName);
		while (in.hasNextLine()) {
			StdOut.println(in.readLine());
		}
		StdOut.println();
	}
	
	private static void inputOutputSample(String fileName) {
		
		StdOut.println("\t\t\tWelcome to my program!");
		StdOut.println("It will ask for some information, "
				+ "and assign a random number (0 - 100) to your profile.");
		
		StdOut.println();
		StdOut.print("Enter first name & favorite color separated by a space: ");
		
		String reading = "Reading from a file:";
		String name = StdIn.readString().toUpperCase();
		String favColor = StdIn.readString().toUpperCase();
		Random randNumber = new Random();
		int randomUserNumber = randNumber.nextInt(100);
		
		StdOut.println("Reading user's input...");
		StdOut.println();
		StdOut.println("Randomly assign number: " + randomUserNumber);
		StdOut.println("Writing to a file...");
		StdOut.println();
		
		Out out = new Out(fileName);
		out.printf("Name: %s%n" + "Favorite color: %s%n" + "Assigned number: %d", 
				name, favColor, randomUserNumber);
		
		StdOut.println(reading);
		printUnderLine(reading);
		
		In in = new In(fileName);
		while(in.hasNextLine()) {
			
			StdOut.println(in.readLine());
		}
			
		StdOut.println();
		
	}
	
	public static void printUnderLine(String stringName) {
		
		for(int i = 0; i < stringName.length(); i++) {
			
			StdOut.print("-");
		}
		
		StdOut.println();
	}
	
	/**
	 * Demonstrates how to use StdIn, StdOut, In, and Out
	 * @param args
	 */
	public static void main(String[] args) {		
		String fileName = "src/test/Letters.txt";
	   
		/*demoReadingUserInput();		    
	    demoWritingToFile(fileName);	    	    
	    demoReadingFromFile(fileName);*/
		
		inputOutputSample(fileName);
	    
	    
	}
}
