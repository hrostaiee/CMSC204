package cmsc_204_assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**Implements the CourseDBManagerInterface that is provided.
   The data manager allows the user to read the courses from a file or to enter the data by hand, and uses an Alert to print out the database elements. 
   The input is read from a file or read from the textfields and is added to the data structure through the add method.  
 * @author Hasib Rostaiee
 *
 */
public class CourseDBManager implements CourseDBManagerInterface{
	 private CourseDBStructure CDS = new CourseDBStructure(10);
	 
	
	/**The add method uses the CDS add method and add given attribute of CDE to the CDS
	 * @param id Course ID
	 * @param crn Course CRN
	 * @param credits Number of Credits
	 * @param roomNum Room Number of the Class
	 * @param instructor Name of the Instructor
	 */	public void add(String id, int crn, int credits, String roomNum, String instructor) {	
		CourseDBElement CDE = new CourseDBElement(id, crn, credits, roomNum, instructor);
		CDS.add(CDE);
		
		
	}

	/* (non-Javadoc)
	 * @see cmsc_204_assignment4.CourseDBManagerInterface#get(int)
	 */
	@Override
	public CourseDBElement get(int crn) {
		try {
			return CDS.get(crn);
		} 
		catch (IOException e) {
			System.out.println("No Course Found for this CRN");
			e.getMessage();
		}
		return null;
	}

	/**This method read content of a file line by line, convert each line to CDE element and added it to the CDS
	 * @param input given file
	 * @throws FileNotFoundException thrown when file is not found
	 */
	public void readFile(File input) throws FileNotFoundException {

		try {
			//read lines from the file
			Scanner reader = new Scanner(input);
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] eachLine = line.split(" ", 5); //separate eachLine into array of string using spaces
				CourseDBElement CDE = new CourseDBElement(eachLine[0], Integer.parseInt(eachLine[1]),
						Integer.parseInt(eachLine[2]), eachLine[3], eachLine[4]);
				CDS.add(CDE);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	/** This method shows an String ArrayList representation of the content of the HashTable
	 */
	public ArrayList<String> showAll() {
				ArrayList<String> output = new ArrayList<String>();
				for (LinkedList<CourseDBElement> list : CDS.hashTable) {
					if(list != null) {
						for (int i = 0; i < list.size(); i++) {
							String course = list.get(i).toString();
							output.add(course);
						}
					}
				}
				return output;
			}
				
	}

