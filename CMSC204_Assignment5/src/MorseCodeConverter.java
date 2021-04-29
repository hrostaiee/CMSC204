package cmsc204_assignment5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**The MorseCodeConverter contains a static MorseCodeTree object and constructs (calls the constructor for) the MorseCodeTree.
 * These static methods use the MorseCodeTree to convert from morse code to English characters.  
 * Each method returns a string object of English characters
 * @author Hasib Rostaiee
 *
 */
public class MorseCodeConverter {
	
	private static MorseCodeTree tree = new MorseCodeTree();
	
	
	/**This method convert from morse code to English given a file.
	 * @param codeFile file which contains the morse code
	 * @return String represntation of the english version 
	 * @throws FileNotFoundException
	 */
	static String convertToEnglish(File codeFile) throws FileNotFoundException {
		StringBuilder all = new StringBuilder();
		try {
			Scanner reader = new Scanner(codeFile);
			
			while (reader.hasNextLine()) {
				all.append(reader.nextLine());
			}
			reader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
	}
		return convertToEnglish(all.toString());
		
	}
	
	/**This method convert from morse code to English given a string of morse code
	 * @param code the string in morse code format
	 * @return String represnation of coverted code in English
	 */
	static String convertToEnglish(String code) {
	
		String[] letters;
	    String[] words = code.split(" / ");
	    StringBuilder englishFormat = new StringBuilder();

	    for (String ch : words) {
	      letters = ch.split(" ");
	      for (String temp : letters) {
	        englishFormat.append(tree.fetch(temp));
	      }
	      englishFormat.append(" ");
	    }
	    return englishFormat.toString().trim();
	}
	
	/**This is used for testing purposes – to make sure the tree for MorseCodeTree was built properly.
	 * @return String represnation of list of the tree in the array.
	 */
	static String printTree() {
		ArrayList<String> ArrayOfTree = tree.toArrayList();
	    StringBuilder all = new StringBuilder();

	    for (String letters : ArrayOfTree) {
	    	if(letters !=null)
	      all.append(letters).append(" ");
	    }
	    return all.toString().trim();
	  }
	}
	

