package cmsc_204_assignment4;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**Test Class for CourseDBManager
 * @author Hasib
 *
 */
class CourseDBManager_STUDENT_Test {

private CourseDBManagerInterface dataManager = new CourseDBManager();
	
	/**
	 * Create instance of CoruseDBManager
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		dataManager = new CourseDBManager();
	}

	/**
	 * Resetting CourseDBManager
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		dataManager = null;
	}

	/**
	 * Test for add method
	 */
	@Test
	void testAdd() {
		try {
			dataManager.add("CMSC203",32715,4,"SC212","Farnaz Eivazi");
			dataManager.add("CMSC204",32461,4,"Online","Farnaz Eivazi");
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}

	/**
	 * Tests getCRN methode
	 */
	@Test
	void testGet() {
		try {
			dataManager.add("CMSC203",32715,4,"SC212","Farnaz Eivazi");
			CourseDBElement testElement = new CourseDBElement("CMSC203",32715,4,"SC212","Farnaz Eivazi");
			assertEquals(testElement.getCRN(), dataManager.get(32715).getCRN());
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}

	/**
	 * Tests read file method
	 */
	@Test
	void testReadFile() {
		try {
			File inputFile = new File("test.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("CMSC203 32715 4 SC212 Farnaz Eivazi");
			inFile.print("CMSC204 32461 4 Online Farnaz Eivazi");
			
			inFile.close();
			dataManager.readFile(inputFile);
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}

	/**
	 * Tests ShowAll Method
	 */
	@Test
	void testShowAll() {
		dataManager.add("CMSC203",32715,4,"SC212","Farnaz Eivazi");
		dataManager.add("CMSC204",32461,4,"Online","Farnaz Eivazi");
		dataManager.add("POLI101",22598,4,"Hybrid","James Furgol");
		ArrayList<String> list = dataManager.showAll();
		
		assertEquals(list.get(0),"\nCourse:CMSC204 CRN:32461 Credits:4 Instructor:Farnaz Eivazi Room:Online");
		assertEquals(list.get(1),"\nCourse:POLI101 CRN:22598 Credits:4 Instructor:James Furgol Room:Hybrid");
		assertEquals(list.get(2),"\nCourse:CMSC203 CRN:32715 Credits:4 Instructor:Farnaz Eivazi Room:SC212");
	}
}
