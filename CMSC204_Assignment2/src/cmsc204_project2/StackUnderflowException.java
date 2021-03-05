package cmsc204_project2;

public class StackUnderflowException extends RuntimeException{

	public StackUnderflowException() {
		super("Stack is Empty!");
	}
	public StackUnderflowException(String message) {
		super(message);
	}
	
}
