package CMSC204_Project1;



/**
 * @author Hasib
 */
public class LengthException extends RuntimeException{

	//Default Constructor
	public LengthException() {
		super("The password must be at least 6 characters long");
		
	}

	public LengthException(String message) {
		super(message);
	}



}//End of Class
