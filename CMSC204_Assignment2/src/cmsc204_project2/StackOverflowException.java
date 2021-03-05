package cmsc204_project2;

public class StackOverflowException extends RuntimeException{

	public StackOverflowException() {
		super("Stack is Full!");
	}

	public StackOverflowException(String message) {
		super(message);
		
	}

}
