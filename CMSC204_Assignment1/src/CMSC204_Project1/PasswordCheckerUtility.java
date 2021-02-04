package CMSC204_Project1;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**This class contains methods that check if a provided password is valid based on specification or not and giving message accordingly.
 * @author Hasib Rostaiee
 *
 */
public class PasswordCheckerUtility {

	/**
	 * Constructor
	 */
	public PasswordCheckerUtility() {

	}
	

	/**This method check the password and verify if all the requirements are met and return true or false
	 * @param passwordText				Password String
	 * @return isValid					Returns true if password meet all requirement or false if any of requirements is not met
	 * @throws LengthException			The Exception is thrown when password length is less than 6
	 * @throws NoUpperAlphaException	The Exception is thrown when there is no lower case alpha char in the password
	 * @throws NoLowerAlphaException	The Exception is thrown when there is no upper case alpha char in the password
	 * @throws NoDigitException			The Exception is thrown when there is no number character in the password
	 * @throws NoSpecialCharacterException	The Exception is thrown when there is no special character
	 * @throws InvalidSequenceException	The Exception is thrown when any char is in sequence more than 2 times
	 */
	public static boolean isValidPassword(String passwordText)
			throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException,
			NoSpecialCharacterException, InvalidSequenceException {
		
			boolean isValid = false;
			if (isValidLength(passwordText) && 
					hasDigit(passwordText) && 
					hasLowerAlpha(passwordText)&& 
					hasUpperAlpha(passwordText) && 
					hasSpecialChar(passwordText) &&
					!hasSameCharInSequence(passwordText)
			)
				isValid = true;
			
		return isValid;

	}

	/**This method checks if a password is between 6 and 6 Characters and return true or false
	 * @param passwordText The password to be checked
	 * @return weekPass true if password is weak and false otherwise
	 */
	public static boolean isWeakPassword(String passwordText) {
		boolean weekPass = false;
		if(passwordText.length()>=6 && passwordText.length()<=9)
			weekPass = true;
		return weekPass;
	}

	/**This method read password text from a list and list out the invlaid password along with its problem
	 * @param passwords	List of Passwords
	 * @return invalidPasswords: List of invalid passwords
	 */
	public static ArrayList<String> getInvalidPasswords (ArrayList<String> passwords) {

		ArrayList <String> invalidPasswords = new ArrayList <String>();

		//loop through list of passwords
		for(int i=0; i<passwords.size(); i++){

			//check if password at the index is valid by calling isValidPassword
			try {
				if(!isValidPassword(passwords.get(i)));
			}
			catch (LengthException e) {
			invalidPasswords.add(passwords.get(i) + " -> " + e.getMessage());
			}
			catch (NoUpperAlphaException e) {
				invalidPasswords.add(passwords.get(i) + " -> " + e.getMessage());
				}
			catch (NoLowerAlphaException e) {
				invalidPasswords.add(passwords.get(i) + " -> " + e.getMessage());
				}
			catch (NoDigitException e) {
				invalidPasswords.add(passwords.get(i) + " -> " + e.getMessage());
				}
			catch (NoSpecialCharacterException e) {
				invalidPasswords.add(passwords.get(i) + " -> " + e.getMessage());
				}
			catch (InvalidSequenceException e) {
				invalidPasswords.add(passwords.get(i) + " -> " + e.getMessage());
				}
		}

		return invalidPasswords;
	}

	
	
	
//Other methods implementations	
	
	/**Checks the two password provided and if didn't match throws the exception.
	 * @param password initial password
	 * @param passwordConfirm	re-typed password
	 * @throws UnmatchedException thrown if not same (case sensitive)
	 */
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
			if (password.compareTo(passwordConfirm)!=0)
				throw new UnmatchedException();
		}
		
	/**Checks the two password provided and if it didn't match return false, otherwise true
	 * @param password	Initiation given Password
	 * @param passwordConfirm	Re-Typed Password
	 * @return true if passwords match and false otherwise
	 */
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm){
		if (password.compareTo(passwordConfirm)!=0)
		return false;
		else 
			return true;
		}
	/**
	 * This method check for length of the password and return false if password
	 * length is less than 6 characters and return false if length of the password
	 * is 6 or more than 6 characters.
	 * 
	 * @param passwordText - Text of the password passed into the method
	 * @return isValidLength - True or false based on the length requirement.
	 */
	public static boolean isValidLength(String passwordText) {
		boolean isValidLength = true;

		if (passwordText.length() < 6) {
			isValidLength = false;
			throw new LengthException();
		}
			
			return isValidLength;
	}

	/**
	 * This method verify if the password has at least one number character on it
	 * 
	 * @param passwordText the password text to be checked
	 * @return true if password contains number and false if password doesn't have
	 *         at least one number
	 */
	public static boolean hasDigit(String passwordText) {
		boolean hasDigit = true;

		if (!(passwordText.matches(".*[0-9].*"))) {
			hasDigit = false;
			throw new NoDigitException();
		}
			
		return hasDigit;
	}

	/**
	 * This method check the password text to see if there is at least one lower
	 * case alphabet in it
	 * 
	 * @param passwordText the password to be checked
	 * @return lowerAlphaExist which is true if there is lower case in the password
	 *         and false otherwise
	 */
	public static boolean hasLowerAlpha(String passwordText) {
		boolean lowerAlphaExist = true;

		if (!passwordText.matches(".*[a-z].*")) {
			lowerAlphaExist = false;
			throw new NoLowerAlphaException();
		}
		return lowerAlphaExist;
	}

	/**
	 * This method check the password text to see if there is at least one upper
	 * case alphabet in it
	 * @param passwordText given password
	 * @return upperAlphaExist true if password contains Upper case and false if not.
	 */
	public static boolean hasUpperAlpha(String passwordText) {
		boolean upperAlphaExist = true;

		if (!passwordText.matches(".*[A-Z].*")) {
			upperAlphaExist = false;
			throw new NoUpperAlphaException();
		}

		return upperAlphaExist;
	}

	/**
	 * Check if the password contains at least one special characters
	 * @param passwordText given password
	 * @return specialCharExist true or false
	 */
	public static boolean hasSpecialChar(String passwordText) {
		boolean specialCharExist = true;
		if (!passwordText.matches(".*[ !\\\"#$%&'()*+,-./:;<=>?@^_`\\[\\]{|}~].*")) {
			specialCharExist = false;
			throw new NoSpecialCharacterException();
		}
		return specialCharExist;
	}
	
	/**This method checks if a password contains more than 2 same character in sequence and throw exception
	 * @param passwordText The password String to be checked
	 * @return invalidSeq 	True if there are more than 2 same chars in sequence and false otherwise
	 */
	public static boolean hasSameCharInSequence(String passwordText) {
		boolean invalidSeq = false;
		for(int i =0; i<passwordText.length()-2; i++) {
			if(passwordText.charAt(i) == passwordText.charAt(i+1) 
					&& passwordText.charAt(i) == passwordText.charAt(i+2)) {
				invalidSeq = true;
				throw new InvalidSequenceException();
			}
		}
		
		return invalidSeq;
	}
	
	/**This method checks if a password is between 6 and 6 Characters and return true or false
	 * @param passwordText The password to be checked
	 * @return weekPass true if password is weak and false otherwise
	 */
	public static boolean hasBetweenSixAndNineChars(String passwordText) {
		boolean weekPass = false;
		if(passwordText.length()>=6 && passwordText.length()<=9)
			weekPass = true;
		return weekPass;
	}
	
}// End of Class
