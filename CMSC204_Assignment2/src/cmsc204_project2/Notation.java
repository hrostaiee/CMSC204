package cmsc204_project2;

/**The Notation class have a method to convert infix notation to postfix notation that takes in a string and return a string, 
 * a method postfixToInfix to convert postfix notation to infix notation that takes in a string and return a string, 
 * and a method that evaluates the postfix expression. It takes in a string and return a double.
 * @author Hasib Rostaiee
 */
public class Notation {
	
	//Constructor
	protected Notation() {
		
	}

	/**This method evaluates the postfix expression. It takes in a string and return a double.
	 * The method loops through each character of the posfix espression, and evaluate them according to the algoritm
	 * @param postfixExpr the expression in postfix format
	 * @return evaluated result in double
	 * @throws InvalidNotationFormatException thrown when postfix expression is invalid
	 */
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
		double evaluation = 0;
		NotationStack<Integer> stack = new NotationStack<>(postfixExpr.length()); //Create a stack

		//Read through each character of the provided postfix expression and store it in a temp variable
		for (int i = 0; i < postfixExpr.length(); i++) {
			char temp = postfixExpr.charAt(i);
			
			//if the character is an operand or if it's a left parentheses, push it to the sack
			if (isOperand(temp) || temp == '(')
			{
				String c = String.valueOf(temp); //Changing char to String to cast it as Integer in next line
				stack.push(Integer.valueOf(c));
			}

			/*
			 * If the current character is an operator, 1. Pop the top 2 values from the
			 * stack. If there are fewer than 2 values throw an error 2. Perform the
			 * arithmetic calculation of the operator with the first popped value as the
			 * right operand and the second popped value as the left operand 3. Push the
			 * resulting value onto the stack
			 */
			else if (isOperatorSign(temp)) {
				int op2 = (int) stack.pop();
				if (stack.isEmpty())
					throw new InvalidNotationFormatException();
				int op1 = (int) stack.pop();
				int result = performOperation(temp, op1, op2);
				stack.push(result);
			}
		}
		
		/*
		 * If there is only one value in the stack – it is the result of the postfix
		 * expression, if more than one value, throw an error
		 */
		if (stack.size() > 1)
			throw new InvalidNotationFormatException();
		evaluation = stack.top();
		return evaluation;
	}

	
	/**This method convert postfix notation to infix notation that takes in a string and return a string.
	 * The method loops through each character of the postfix expression and convert it to infix according to algoritm.
	 * @param postfix the expression in posfix format
	 * @return infix expression in String
	 * @throws InvalidNotationFormatException thrown when postfix expression is invalid
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
		NotationStack<String> stack = new NotationStack<>(postfix.length());
		//Read through each character of the provided postfix expression and store it in a temp variable
		for (int i = 0; i < postfix.length(); i++) {
			char temp = postfix.charAt(i);
			
			//If the current character is an operand, push it on the stack
			if (isOperand(temp)) {
				stack.push(String.valueOf(temp));
				} 
			/*
			 * If the current character is an operator, 
			 * 1. Pop the top 2 values from the stack. If there are fewer than 2 values throw an error 
			 * 2. Create a string with 1st value and then the operator and then the 2nd value. 
			 * 3. Encapsulate the resulting string within parenthesis 
			 * 4. Push the resulting string back to the stack
			 */
			else if (isOperatorSign(temp)) {
				String op2 = stack.pop();
				if (stack.isEmpty())
					throw new InvalidNotationFormatException();
				String op1 = stack.pop();
				String both = "(" + op1 + temp + op2 + ")";
				stack.push(both);
			}
		}
		/*
		 * If there is only one value in the stack – it is the infix string, if more
		 * than one value, throw an error
		 */
		if (stack.size() > 1)
			throw new InvalidNotationFormatException();
		else
			return stack.top();
	}

	/**This method converts infix notation to postfix notation that takes in a string and return a string
	 * @param infix given expression in infix format
	 * @return postfix expression
	 * @throws InvalidNotationFormatException thrown if infix expression is invalid
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
		NotationStack<Character> stack = new NotationStack<>(infix.length());
		NotationQueue<Character> queue = new NotationQueue<>(infix.length());
		char temp; // variable to hold characters of the sting

		for (int i = 0; i < infix.length(); i++) // scanning infix chars one by one
		{
			temp = infix.charAt(i);
			//if character is an Operand add it it to the queue.
			if (isOperand(temp))  
				queue.enqueue(temp);

			// if character is an Open Parenthesis push it to the stack
			else if (temp == '(') 
				stack.push(temp);
			/*
			 * If the current character in the infix is a right parenthesis 
			 * 1. Pop operators from the top of the stack and insert them in postfix solution queue until a
			 * left parenthesis is at the top of the stack, if no left parenthesis-throw an error 
			 * 2. Pop (and discard) the left parenthesis from the stack
			 */ 
			else if (temp == ')') 
			{
				while (!stack.isEmpty() && (stack.top() != '('))
					queue.enqueue(stack.pop());
				if (stack.isEmpty())
					throw new InvalidNotationFormatException();
				stack.pop();
			}
			/*
			 * If the current character in the infix is an operator, 
			 * 1. Pop operators (if there are any) at the top of the stack while they have equal or higher 
			 * precedence than the current operator, and insert the popped operators in postfix solution queue 
			 * 2. Push the current character in the infix onto the stack
			 */
			else if (isOperatorSign(temp)){
				while (!stack.isEmpty() && precedence(temp) <= precedence(stack.top())) {
					queue.enqueue(stack.pop());
				}
				stack.push(temp);
			}
		}
		/*
		 * When the infix expression has been read, Pop any remaining operators and
		 * insert them in postfix solution queue.
		 */
		while (!stack.isEmpty()) {
			if (stack.top() == ')')
				throw new InvalidNotationFormatException();
			queue.enqueue(stack.top());
		}
		
		return queue.toString(); //toString representation of the postfix queue solution
	}

	//Additional Methods
	
	/**This method check if a given character is Operand or not
	 * @param c givin character to be checked
	 * @return true if it's operand and false if it's not
	 */
	public static boolean isOperand(char c) { 
		return (Character.isLetterOrDigit(c));

	}

	/**This method checks if a given character is an operator sign
	 * @param c given character
	 * @return true if it's an operator (+, -, *, /)
	 */
	public static boolean isOperatorSign(char c) {
		return (c == '+' || c == '-' || c == '*' || c == '/');
	
	}

	/**Set precedence of given operator
	 * @param c given operator
	 * @return p a number representation of precedence, highest being 3 and lowest being 1
	 */
	public static int precedence(char c) {
		int p = 0;
		switch (c) {
		case '^':
			p = 3;
			break;
		case '*':
		case '/':
			p = 2;
			break;
		case '+':
		case '-':
			p = 1;
			break;
		default:
			p = 0;
			break;
		}

		return p;
	}

	/**This method perform arithmetic operations between two given operands
	 * @param operation the operation to take place
	 * @param operand1 first operand
	 * @param operand2 second operand
	 * @return result of operation as integer
	 */
	public static int performOperation(char operation, int operand1, int operand2) {
		if (operation == '+')
			return operand1 + operand2;
		else if (operation == '-')
			return operand1 - operand2;
		else if (operation == '*')
			return operand1 * operand2;
		else if (operation == '/')
			return operand1 / operand2;
		else
			throw new InvalidNotationFormatException();

	}
}
