package cmsc_204_assignment4;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

/**Implements the CourseDBStructureInterface that is provided.
   Contains a hash table with Linked Lists (buckets).  It will be an array of linked lists of CourseDBElements.  
   Each CDE will have a hash code that comes from the CRN, since the CRN is unique for courses.  
   Two constructors for the CDS will be required, one that takes in an integer that is the estimated number of courses, the other is used for testing purposes.  The comments in the CourseDBStructureInterface (provided) should help you figure out how to set the length of the hash table
 * @author Hasib Rostaiee
 */
/**
 * @author Hasib
 *
 */
public class CourseDBStructure implements CourseDBStructureInterface{
	//Hash Table (a linkedlist of Course DB Elements)
	protected LinkedList<CourseDBElement>[] hashTable;	
	
	
	/**Constructor, getting an estimated size of hash table and creates a new linked list hashTable with the size provided;
	 * @param estSize size of HashTable
	 */
	public CourseDBStructure(int estSize) {
		hashTable = new LinkedList[estSize];
	}
	

	/**Constructor, same as the other constructor with an opting of taking a testing string
	 * @param testing a test string
	 * @param estSize size of Hash Table
	 */
	public CourseDBStructure(String testing, int estSize) {
		hashTable = new LinkedList[estSize];
	}

	
	/**
	 * This method will take a CourseDBElement and add it to the data structure. If a linked
	 * list at the relevant hash code doesn’t exist, it creates a LinkedList with the
	 * first element being the CDE and add it to the HashTable. If the LinkedList
	 * already exists, add the CDE to the existing list
	 * @param element CourseDBElement object
	 */	public void add(CourseDBElement element) {
		
		//get hashcode index in which the element exist
		int index = element.hashCode() % hashTable.length;	
		if(hashTable[index] ==null) {
			LinkedList<CourseDBElement> entry = new LinkedList<>();
			entry.add(element);
			hashTable[index] = entry;
		} else {
			hashTable[index].add(element);
		}
		
	}
	
	/**
	 * This method takes a CRN and return its mapped Course DB element, if the
	 * course doesn't exists it throws an IOException
	 * @param crn CRN of the course
	 * @return CourseDBElement object for the given CRN
	 */	public CourseDBElement get(int crn) throws IOException {
		
		String crnText = Integer.toString(crn);
		int index = crnText.hashCode() %getTableSize();
		if(hashTable[index]==null) {
			throw new IOException();
		} else {
			for (int i =0; i<hashTable[index].size(); i++) {
				if(hashTable[index].get(i).getCRN() ==crn)
					return hashTable[index].get(i);
			}
		}
		throw new IOException();
	}
	 
	/* (non-Javadoc)
	 * @see cmsc_204_assignment4.CourseDBStructureInterface#getTableSize()
	 */
	@Override
	public int getTableSize() {
		
		return hashTable.length;
	}
	

}
