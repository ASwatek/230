package evaluator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class InfixEvaluator extends Evaluator {

	private HashMap<Character, Integer> operatorsMap;

	public InfixEvaluator() {	
		this.operatorsMap = new HashMap<Character, Integer>();
		this.operatorsMap.put('(', 0);
		this.operatorsMap.put('+', 1);
		this.operatorsMap.put('-', 1);
		this.operatorsMap.put('*', 2);
		this.operatorsMap.put('/', 2);
		this.operatorsMap.put('^', 3);
	}

	@Override
	public int evaluate(String expression) throws ArithmeticException {
		PostfixEvaluator eval = new PostfixEvaluator();
		String postFix = this.convertToPostfix(expression);
		return eval.evaluate(postFix);
	}
	
	public String convertToPostfix(String string) {
		
		if (!verifyEquation(string)) { // Use a seperate method to verify the input
			throw new ArithmeticException();
		}
		
		int startChar = 97; // Arbitrary start character, I picked 'a' so the intermediate steps would be easy to read and closely match actual variable usage in equations
		HashMap<Integer, Integer> numToVarMap = new HashMap<Integer, Integer>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(" ");
		
		while (scanner.hasNext()) { // Finds all integers in the supplied String
			if (scanner.hasNextInt()) {
				int num = scanner.nextInt();
				if (!numToVarMap.containsKey(num)) {
					numToVarMap.put(num, startChar++); // Map numbers to their respective character variables for re-conversion later
				}
			} else {
				scanner.next();
			}
		}
		
		Object[] mapArray = numToVarMap.keySet().toArray();
		int[] keyArray = new int[numToVarMap.size()];
		for (int i = 0; i < keyArray.length; i++) {
			keyArray[i] = (int) mapArray[i];
		}
		Arrays.sort(keyArray); // Sort array so it can be reverse without any side-effects when replacing numbers with letters later
		
		// Convert ints to letters to avoid issues with multiple digit numbers
		// Going from largest to smallest when converting from int to letters allows me to avoid partially replacing
		// some digits of a bigger number with the one for a smaller number. 
		// Example: [15, 5] -> [a, b] versus [15, 5] -> [1a, a] -> [ba, a]
		for (int i = keyArray.length - 1; i >= 0; i--) { 
			int key = keyArray[i];                        
			char replacement = (char) ((int) numToVarMap.get(key));
			string = string.replace(key + "", replacement + ""); // StringBuilder doesn't have a .replace() that switch sequences
		}														 // of characters/numbers with another sequence, so I use the regular String one instead
		
		ArrayList<Character> result = new ArrayList<Character>();
		Stack<Character> operatorStack = new Stack<Character>();
		
		for (int i = 0; i < string.length(); i++) { // Loop through the input, due to replacing the numbers, I can assume everything is 1 character
			
			char ch = string.charAt(i);
			
			if (Character.isLetter(ch)) { // Letters are now valid, unlike in the verifyEquation() method
				result.add(ch);
			} else if (Character.isWhitespace(ch)) {
				continue;
			} else if (ch == '(') { // Treat '(' as an operator since it makes everything inside of it a higher precedence
				operatorStack.push(ch);
			} else if (ch == ')') { // When we hit a close parentheses, go back through the operator stack

				char top = operatorStack.pop();
				
				while (top != '(') { // Add all the operators to the result until we come across the matching open parentheses
					result.add(top);
					top = operatorStack.pop();
				}
			} else { // Uses the operatorStack as a buffer for higher precedence operators and then removes them until it finds one that is lower than the current character/operator

				while (!operatorStack.isEmpty()) { // Enforce PEMDAS to make sure division/multiplication/exponents happen before addition/subtraction
					int precedence1 = this.operatorsMap.get(operatorStack.peek());
					int precedence2 = this.operatorsMap.get(ch);
					
					if (precedence1 < precedence2) { // If the current character has a greater precedence, don't add the top of the operatorStack to the result just yet
						break;
					}
					result.add(operatorStack.pop()); // Add the lower precedented operator to the result so it's evaluated after all higher operators
				}
				operatorStack.push(ch); // Add the current character/operator to the operatorStack
			}
		}

		StringBuilder builder = new StringBuilder();
		for (Character ch : result) { // Put all characters/parenthesied operators in before trailing operators
			builder.append(ch + " ");
		}
		while (!operatorStack.isEmpty()) { // Add in trailing operators
			builder.append(operatorStack.pop() + " ");
		}
		builder.deleteCharAt(builder.length() - 1); // Delete the last space or the tests will get mad
		
		String stringResult = builder.toString();
		
		for (Integer key : numToVarMap.keySet()) { // Replace all letter characters with their numerical equivalents from the conversion map created earlier
			char replacement = (char) ((int) numToVarMap.get(key));
			stringResult = stringResult.replace(replacement + "", (int) key + "");
		}
		return stringResult;
	}
	
	public boolean verifyEquation(String string) { // Quick method to do a basic check to see if the equation has any pre-existing letters or mismatched parentheses
		int openParenCount = 0;                    // Letters are checked before conversion since it would cause issues with the integer -> letter conversion step later
		int closeParenCount = 0;                  
		for (int i = 0; i < string.length(); i++) {
			char ch = string.charAt(i);
			if (ch == '(') {
				openParenCount++;
			} else if (ch == ')') {
				closeParenCount++;
			} else if (Character.isLetter(ch)) { // If there are any letters present before conversion, then the equation isn't valid
				return false;
			}
		}
		if (openParenCount != closeParenCount) { // Having an unequal amount of parentheses makes an invalid equation
			return false;
		}
		return true;
	}

}
