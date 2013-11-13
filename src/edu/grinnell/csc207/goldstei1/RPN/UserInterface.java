package edu.grinnell.csc207.goldstei1.RPN;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class UserInterface {
    
    public static void main(String[] args) {
	PrintWriter pen = new PrintWriter(System.out, true);
	BufferedReader eyes = new BufferedReader(new InputStreamReader(
		System.in));
	RPNCalculator calc = new RPNCalculator();

	pen.println("RPNCalculator Commands:\nEnter an Expression: A mathematical expression in rpn form with spaces separating numbers and operands."
		+ "\n\t\t     Accepted opperands include: +, -, *, /, ^\np: print the last value on the stack\ns: print the entire stack\nc: clear the entire stack\nq: Quit the program");

	while (true) {
	    try {
		String command = eyes.readLine();
		switch (command) {
		case "q":
		    pen.println("Exiting...");
		    return;
		case "c":
		    calc.c();
		    break;
		case "p":
		    pen.println(calc.p());
		    break;
		case "s":
		    pen.println(calc.s());
		    break;
		default:
		    calc.evaluate(command);
		    break;
		}
	    } catch (Exception e) {
		pen.println("Invalid input: " + e.getMessage());
	    }
	}
    }
}
