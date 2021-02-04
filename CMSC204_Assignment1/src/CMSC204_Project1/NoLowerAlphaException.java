package CMSC204_Project1;


public class NoLowerAlphaException extends RuntimeException{

	//Defualt contractor
	public NoLowerAlphaException() {
		super("The password must contain at least one lower case alphabetic character");
	}
	
	public NoLowerAlphaException(String message) {
		super(message);
	}
}
