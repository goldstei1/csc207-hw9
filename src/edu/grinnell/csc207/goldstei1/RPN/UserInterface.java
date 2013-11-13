package edu.grinnell.csc207.goldstei1.RPN;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * User interface class for an implementation of a rpn calculator
 * 
 * @author Daniel Goldstein
 * @author Tiffany Nguyen
 * @author Mark Lewis
 * @author Earnest Wheeler
 *
 */
public class UserInterface {
    
    /**
     * The main method that runs the user interface
     * @param args
     */
    public static void main(String[] args) {
	PrintWriter pen = new PrintWriter(System.out, true); 
	BufferedReader eyes = new BufferedReader(new InputStreamReader(
		System.in));
	RPNCalculator calc = new RPNCalculator();

	// print the startup message
	pen.println("RPNCalculator Commands:\nEnter an Expression: A mathematical"
		+ " expression in rpn form with spaces separating numbers and operands."
		+ "\n\t\t     Accepted opperands include: +, -, *, /, ^\np: print"
		+ " the last value on the stack\ns: print the entire stack\nc: clear"
		+ " the entire stack\nq: Quit the program");

	// Start checking for user input
	while (true) {
	    try {
		String command = eyes.readLine();
		switch (command) {
		// quit
		case "q":
		    pen.println("GoodBye");
		    return;
		// clear the stack
		case "c":
		    calc.c();
		    break;
		// Print top value of stack
		case "p":
		    pen.println(calc.p());
		    break;
		// Print the whole stack
		case "s":
		    pen.println(calc.s());
		    break;
		// Attempt to evaluate if none of the above commands
		default:
		    calc.evaluate(command);
		    break;
		} // switch
	    } catch (Exception e) {
		pen.println("Invalid input: " + e.getMessage());
	    } // catch
	} // while (true)
    } // main
} // UserInterface
