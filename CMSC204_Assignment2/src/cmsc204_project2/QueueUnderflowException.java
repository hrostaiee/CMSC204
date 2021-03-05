package cmsc204_project2;

public class QueueUnderflowException extends RuntimeException{

	public QueueUnderflowException() {
		super("Queue is Empty!");
	}

	public QueueUnderflowException(String message) {
		super(message);
		
	}

}
