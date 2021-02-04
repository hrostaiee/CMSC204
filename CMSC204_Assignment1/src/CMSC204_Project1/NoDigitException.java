package CMSC204_Project1;


public class NoDigitException extends RuntimeException{

	//Default Constructor
	public NoDigitException() {
		super("The password must contain at least one digit");
	}
	
	public NoDigitException(String message) {
		super(message);
	}
}//End of class
