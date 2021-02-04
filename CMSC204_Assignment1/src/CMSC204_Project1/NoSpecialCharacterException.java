package CMSC204_Project1;


public class NoSpecialCharacterException extends RuntimeException{

	public NoSpecialCharacterException() {
		super("The password must contain at least one special character");
	}
	
	public NoSpecialCharacterException(String message) {
		super(message);
	}
}
