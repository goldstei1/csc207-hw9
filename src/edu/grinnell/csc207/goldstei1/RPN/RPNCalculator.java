package edu.grinnell.csc207.goldstei1.RPN;

import java.util.Stack;

/**
 * An implementation of a reverse polish notation calculator
 * @author Daniel Goldstein
 * @author Mark Lewis
 * @author Tiffany Nguyen
 * @author Earnest Wheeler
 */
public class RPNCalculator {

    /**
     * The stack used for the values in the calculator
     */
    Stack<Double> vals;

    /**
     * Constructor for an RPNCalculator
     */
    public RPNCalculator() {
	vals = new Stack<Double>();
    } // RPNCalculator()

    /**
     * Method to evaluate (and put on the stack) a mathematical 
     * expression in reverse polish notation form
     * @param str
     * @pre real numbers and operands are only terms present and are separated by spaces
     * @throws Exception: if preconditions are not met
     */
    public void evaluate(String str) throws Exception {
	String[] splitEntries = str.split(" ");

	// Check that all input is acceptable
	for (int i = 0; i < splitEntries.length; i++) {
	    if (!splitEntries[i].matches("[\\Q-*+/^\\E]")
		    && !checkNumber(splitEntries[i])) {
		throw new Exception(splitEntries[i]);
	    } // if
	} // for

	// Perform actions based on input
	for (int i = 0; i < splitEntries.length; i++) {
	    try {
		switch (splitEntries[i]) {
		case "+":
		    this.add();
		    break;
		case "-":
		    this.subtract();
		    break;
		case "*":
		    this.multiply();
		    break;
		case "/":
		    this.divide();
		    break;
		case "^":
		    this.power();
		    break;
		default:
		    this.vals.push(new Double(splitEntries[i]));
		    break;
		} // switch
	    } catch (Exception e) {
		StringBuffer rest = new StringBuffer("");
		for (int j = i; j < splitEntries.length; j++) {
		    rest.append(splitEntries[j] + " ");
		} // for
		throw new Exception(e.getMessage() + " Stopped at " + rest.toString());
	    } // catch
	} // for
    } // evaluate(String str)
    
    /**
     * peek
     * @pre this.vals is non-empty
     * @exception if precondition is not met
     * @returns the top value of the stack
     */
    public Double p() throws Exception {
	if (this.vals.empty()) {
	    throw new Exception("Stack is empty");
	} // if
	return this.vals.peek();
    } // p()
    
    /**
     * @returns the string representation of the stack
     */
    public String s() {
	StringBuffer sb = new StringBuffer();
	for (int i = 0; i < this.vals.size(); i++) {
	    sb.append(this.vals.get(i) + "\n");
	} // for
	return sb.toString();
    } // s()
	
    /**
     * removes all values from the stack
     */
    public void c() {
	this.vals.clear();
    } // c()
    
    /**
     * Adds the top two elements of the stack
     * @pre there are at least two elements in the stack
     * @exception if precondition is not met
     */ 
    private void add() throws Exception {
	if (this.vals.size() >= 2) {
	    this.vals.push(this.vals.pop() + this.vals.pop());
	} else {
	    throw new Exception(
		    "There must be at least 2 elements on the stack to add");
	} // else
    } // add()
    
    /**
     * Subtracts the top element of the stack from the second to last element
     * @pre there are at least two elements in the stack
     * @exception precondition is not met
     */ 
    private void subtract() throws Exception {
	if (this.vals.size() >= 2) {
	    Double tmp = this.vals.pop();
	    this.vals.push(this.vals.pop() - tmp);
	} else {
	    throw new Exception(
		    "There must be at least 2 elements on the stack to subtract");
	} // else
    } // subtract()
    
    /**
     * Multiplies the top two elements of the stack
     * @pre there are at least two elements in the stack
     * @exception precondition is not met
     */ 	
    private void multiply() throws Exception {
	if (this.vals.size() >= 2) {
	    this.vals.push(this.vals.pop() * this.vals.pop());
	} else {
	    throw new Exception(
		    "There must be at least 2 elements on the stack to multiply");
	} // else
    } // multiply()
    
    /**
     * Divides the top two elements of the stack
     * @pre there are at least two elements in the stack
     * @exception precondition is not met
     */ 
    private void divide() throws Exception {
	if (this.vals.size() >= 2) {
	    Double tmp = this.vals.pop();
	    this.vals.push(this.vals.pop() / tmp);
	} else {
	    throw new Exception(
		    "There must be at least 2 elements on the stack to divide");
	} // else
    } // divide()
    
    /**
     * The second to last element of the stack is raised to the power of the
     * last element in the stack
     * @pre there are at least two elements in the stack
     * @exception precondition is not met
     */ 	
    private void power() throws Exception {
	if (this.vals.size() >= 2) {
	    Double tmp = this.vals.pop();
	    this.vals.push(Math.pow(this.vals.pop(), tmp));
	} else {
	    throw new Exception(
		    "There must be at least 2 elements on the stack to use pow");
	} // else
    } // power()
	
    /**
     * Check if a string can be represented as a double
     * @param str
     * @return true if the string can be represented as a double
     * @return false otherwise
     */
    private boolean checkNumber(String str) {
	int periodCount = 0; // if more than one '.' invalid input
	int ifNegative = 0; // variable to skip check of first value if negative

	// check for negative
	if (str.charAt(0) == '-') {
	    ifNegative++;
	} // if

	// check every character to make sure its a digit or a period
	for (int i = ifNegative; i < str.length(); i++) {
	    if ((!Character.isDigit(str.charAt(i))) && (str.charAt(i) != '.')) {
		return false;
	    } // if

	    if (str.charAt(i) == '.') {
		periodCount++;
	    } // if
	} // for
	return (periodCount <= 1 );
    } // checkNumber(String str)
    
    // testing
    public static void main(String[] args) {
	String str = "*";
	System.out.println(str.matches("[\\Q.-*+/\\E]"));
	System.out.println(str.matches("[0-9.]+"));
	try {
	    RPNCalculator rpn = new RPNCalculator();
	    rpn.evaluate("5 3 + 4.5 7 * 5 - /");
	    System.out.println(rpn.s());
	    System.out.println(rpn.p());
	    rpn.evaluate("10.7 +");
	    System.out.println(rpn.p());
	    rpn.c();
	    rpn.evaluate("5 3.27 ^");
	    System.out.println(rpn.s());
	    rpn.evaluate("* 4 8");
	    System.out.flush();
	} catch (Exception e) {
	    System.out.println("malformed input: " + e.getMessage());
	} // catch
	
	try {
	    RPNCalculator rpn2 = new RPNCalculator();
	    rpn2.evaluate("5 3 + 4.5* 9 - 7 *");
	} catch (Exception e) {
	    System.out.println("malformed input: " + e.getMessage());
	}
    } // main
} // class RPNCalulator
