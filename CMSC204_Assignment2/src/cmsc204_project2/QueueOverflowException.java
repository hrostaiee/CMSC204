package cmsc204_project2;

public class QueueOverflowException extends RuntimeException{

	public QueueOverflowException() {
		super("Queue is Full!");
	}
	public QueueOverflowException(String message) {
		super(message);
	}
}
