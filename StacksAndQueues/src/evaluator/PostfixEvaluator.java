package evaluator;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class PostfixEvaluator extends Evaluator {
	Stack<Integer> operands;
	ArrayList<Character> operatorsList;
	
	public PostfixEvaluator(){
		this.operands=new Stack<Integer>();
		//this arraylist is used for quick checking to see if an input is an operator, else to throw an error
		this.operatorsList = new ArrayList<Character>();
		this.operatorsList.add('/');
		this.operatorsList.add('*');
		this.operatorsList.add('+');
		this.operatorsList.add('-');
		this.operatorsList.add('^');
	}
	
	//puts ints in stack; hits operator then pops the top two and pushes the result to stack; continues to next operator
	@Override
	public int evaluate(String expression) throws ArithmeticException {
		Scanner charChecker = new Scanner(expression);
		charChecker.useDelimiter(" ");//stated that everything is divided by spaces
		int size=0;	//keeps track of amount of operands for error checking later
		int operatorsCount=0;//keeps track of amount of operators for error checking later
		
		for(int i=0; i<expression.length(); i++){//O(N) runtime, since 0-end
			if(charChecker.hasNextInt()){//if its an operand, push that to the stack of operands.
				operands.push(charChecker.nextInt());
				size++;
			}
			else if(charChecker.hasNext()){//else its an operator, so do the proposed function on the 2 operands
				char current = charChecker.next().charAt(0);
				int second = operands.pop();
				if(operands.isEmpty()) throw new ArithmeticException();//not enough operands for the operators
				int first = operands.pop();
				if(!operatorsList.contains(current)) throw new ArithmeticException();//invalid operator in input
				
				//maybe make switch cases?
				if(current=='/') operands.push(first/second);//cases used to figure out what operation to do
				else if(current=='*') operands.push(first*second);
				else if(current=='+') operands.push(first+second);
				else if(current=='-') operands.push(first-second);
				else if(current=='^') operands.push((int)(Math.pow(first, second)));
				operatorsCount++;
			}
		}
		charChecker.close();
		
		if(size!=operatorsCount+1) throw new ArithmeticException();
		//there should always be 1 more operand than there are operators. if this isnt true, there is incorrect format
		return operands.peek();
	}

}
