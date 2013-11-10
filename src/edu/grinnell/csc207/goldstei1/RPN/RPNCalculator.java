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
	    if (!splitEntries[i].matches("[\\Q-*+/\\E]") && !checkNumber(splitEntries[i])) {
		throw new Exception(splitEntries[i]);
	    } // if 
	} // for
	
	for (int i = 0; i < splitEntries.length; i++) {
	    try {
		if (splitEntries.equals("+")) {
		    this.add();
		} else if (splitEntries[i].equals("-")) {
		    this.subtract();
		} else if (splitEntries[i].equals("*")) {
		    this.multiply();
		} else if (splitEntries[i].equals("/")) {
		    this.divide();
		} else {
		    vals.push(new Double(splitEntries[i]));
		}
	    } catch (Exception e) {
		StringBuffer rest = new StringBuffer("");
		for (int j = i; j < splitEntries.length; j++) {
		    rest.append(splitEntries[i] + " ");
		}
		throw new Exception(e.getMessage() + " Stopped at " + rest);
	    }
	}
	
	
    } // evaluate(String str)
    
    private void add() throws Exception {
	//Stub
    } // add()
    
    private void subtract() throws Exception {
	//Stub
    } // subtract()
    
    private void multiply() throws Exception {
	//Stub
    } // multiply()
    
    private void divide() throws Exception {
	//Stub
    } // divide()
    
    private boolean checkNumber(String str) {
	int periodCount = 0;
	int ifNegative = 0;
	
	if (str.charAt(0) == '-') {
	    ifNegative++;
	}
	
	for (int i = ifNegative; i < str.length(); i++) {
	    if ((!Character.isDigit(str.charAt(i))) && (str.charAt(i) != '.')) {
		return false;
	    }
	    
	    if (str.charAt(i) == '.') {
		periodCount++;
	    }
	}
	return (periodCount <= 1);
    }
    
    public double p() {
	//STUB
	return 0;
    } // p()
    
    public String s() {
	//STUB
	return "";
    } // s()
    
    public void c() {
	//STUB
    } // c()
    
    public static void main(String[] args) {
	String str = "*/";
	System.out.println(str.matches("[\\Q.-*+/\\E]"));
	
	System.out.println(str.matches("[0-9.]+"));
	try {
	    RPNCalculator rpn = new RPNCalculator();
	    rpn.evaluate("3 + 4.5 7 */ 5");
	    System.out.println("Pass");
	    System.out.flush();
	} catch (Exception e) {
	    System.out.println("malformed input: " + e.getMessage());
	}
    }
}
