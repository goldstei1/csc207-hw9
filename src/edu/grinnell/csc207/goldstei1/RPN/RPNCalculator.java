package edu.grinnell.csc207.goldstei1.RPN;

import java.util.Stack;

public class RPNCalculator {

    Stack<Double> vals;
    
    public RPNCalculator() {
	vals = new Stack<Double>();
    } // RPNCalculator()
    
    public void evaluate(String str) throws Exception {
	String[] splitEntries = str.split(" ");
	
	// Check that all input is acceptable
	for (int i = 0; i < splitEntries.length; i++) {
	    if (!splitEntries[i].matches("[\\Q-*+/^\\E]") && !checkNumber(splitEntries[i])) {
		throw new Exception(splitEntries[i]);
	    } // if
	} // for
	
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
		throw new Exception(e.getMessage() + " Stopped at " + rest);
	    } // catch
	} // for
    } // evaluate(String str)
    
    private void add() throws Exception {
	if (this.vals.size() >= 2) {
	    this.vals.push(this.vals.pop() + this.vals.pop());
	} else {
	    throw new Exception("There must be at least 2 elements on the stack to add");
	} // else
    } // add()
    
    private void subtract() throws Exception {
	if (this.vals.size() >= 2) {
	    Double tmp = this.vals.pop();
	    this.vals.push(this.vals.pop() - tmp);
	} else {
	    throw new Exception("There must be at least 2 elements on the stack to subtract");
	} // else
    } // subtract()
    
    private void multiply() throws Exception {
	if (this.vals.size() >= 2) {
	    this.vals.push(this.vals.pop() * this.vals.pop());
	} else {
	    throw new Exception("There must be at least 2 elements on the stack to multiply");
	} // else
    } // multiply()
    
    private void divide() throws Exception {
	if (this.vals.size() >= 2) {
	    Double tmp = this.vals.pop();
	    this.vals.push(this.vals.pop() / tmp);
	} else {
	    throw new Exception("There must be at least 2 elements on the stack to divide");
	} // else
    } // divide()
    
    private void power() throws Exception {
	if (!this.vals.empty()) {
	    Double tmp = this.vals.pop();
	    this.vals.push(Math.pow(this.vals.pop(), tmp));
	} // if
    } // power()
    
    private boolean checkNumber(String str) {
	int periodCount = 0;
	int ifNegative = 0;
	
	if (str.charAt(0) == '-') {
	    ifNegative++;
	} // if
	
	for (int i = ifNegative; i < str.length(); i++) {
	    if ((!Character.isDigit(str.charAt(i))) && (str.charAt(i) != '.')) {
		return false;
	    } // if
	    
	    if (str.charAt(i) == '.') {
		periodCount++;
	    } // if
	} // for
	return (periodCount <= 1);
    } // checkNumber(String str)
    
    public Double p() throws Exception {
	if (this.vals.empty()) {
	    throw new Exception("Stack is empty");
	} // if
	return this.vals.peek();
    } // p()
    
    public String s() {
	StringBuffer sb = new StringBuffer();
	for (int i = 0; i < this.vals.size(); i++) {
	    sb.append(this.vals.get(i) + "\n");
	} // for
	return sb.toString();
    } // s()
    
    public void c() {
	this.vals.clear();
    } // c()
    
    public static void main(String[] args) {
	String str = "*";
	System.out.println(str.matches("[\\Q.-*+/\\E]"));
	System.out.println(str.matches("[0-9.]+"));
	try {
	    RPNCalculator rpn = new RPNCalculator();
	    rpn.evaluate("5 3 + 4.5 7 * 5 - /");
	    System.out.println(rpn.s());
	    rpn.c();
	    rpn.evaluate("5 3.27 ^");
	    System.out.println(rpn.s());
	    System.out.flush();
	} catch (Exception e) {
	    System.out.println("malformed input: " + e.getMessage());
	}
    }
}
