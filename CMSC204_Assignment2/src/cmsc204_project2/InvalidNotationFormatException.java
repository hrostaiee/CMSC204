package cmsc204_project2;

public class InvalidNotationFormatException extends RuntimeException{

	public InvalidNotationFormatException() {
		super("Format is not valid");
	}
	public InvalidNotationFormatException(String message) {
		super(message);
	}
}
