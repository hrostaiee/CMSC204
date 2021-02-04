package CMSC204_Project1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Hasib Rostiaee
 *
 */
public class PasswordCheckerSTUDENT_Test {

	ArrayList<String> passwords;
	
	@Before
	public void setUp() throws Exception {
		String[] p = {"G00gl3.c0m", "G00gl", "G00g!3", "GOOGLE123#", "Google#c0m", "Google.com", 
				"goog1e#", "myGoogle123", "Gooogl3#", "G00ggll33##"};
		passwords = new ArrayList<String>();
		passwords.addAll(Arrays.asList(p)); // puts strings into the ArrayList
		
	}

	@After
	public void tearDown() throws Exception {
	passwords = null;
	}

	/**
	 * Test if the password is less than 8 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("G00gl3.c0m"));
			assertTrue(PasswordCheckerUtility.isValidPassword("G00g!3"));
			PasswordCheckerUtility.isValidPassword("G00gl");
			assertTrue("Did not throw lengthException",false);
		}
		catch(LengthException e)
		{
			assertTrue("Successfully threw a lengthExcepetion",true);
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("G00gle.c0m"));
			PasswordCheckerUtility.isValidPassword("g00gle.c0m");
			assertTrue("Did not throw NoUpperAlphaException", false);
		}
		catch(NoUpperAlphaException e)
		{
			assertTrue("Successfully threw a NoUpperAlphaExcepetion",true);
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("G00gle.c0m#"));
			PasswordCheckerUtility.isValidPassword("GOOGLE123");
			assertTrue("Did not throw NoLowerAlphaException",false);
		}
		catch(NoLowerAlphaException e)
		{
			assertTrue("Successfully threw a NoLowerAlphaExcepetion",true);
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try{
			assertEquals(true,PasswordCheckerUtility.isValidPassword("G00gl3#c0m"));
			boolean weakPwd = PasswordCheckerUtility.isWeakPassword("G00gl3#");
			assertTrue(weakPwd);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some incorrect exception",false);
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try{
			assertEquals(true,PasswordCheckerUtility.isValidPassword("Googl3#"));
			PasswordCheckerUtility.isValidPassword("Gooogl3.com");
			assertTrue("Did not throw an InvalidSequenceException",false);
		}
		catch(InvalidSequenceException e)
		{
			assertTrue("Successfully threw an InvalidSequenceExcepetion",true);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("G00gle.c0m#"));
			PasswordCheckerUtility.isValidPassword("Google");
			assertTrue("Did not throw an NoDigitException",false);
		}
		catch(NoDigitException e)
		{
			assertTrue("Successfully threw a NoDigitException",true);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		String[] validPasswords = {"G00gl3.c0M","G00g!3","googl3#COM"};
		for(int i =0; i<validPasswords.length; i++)
		{
		assertTrue(PasswordCheckerUtility.isValidPassword(validPasswords[i]));
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(passwords);
		Scanner scan = new Scanner(results.get(0)); //
		assertEquals(scan.next(), "G00gl");
		String nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("at least 6 characters"));
		
		scan = new Scanner(results.get(1)); //
		assertEquals(scan.next(), "GOOGLE123#");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("at least one lower case"));
		
		scan = new Scanner(results.get(2)); //
		assertEquals(scan.next(), "Google.com");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("at least one digit"));
		
		scan = new Scanner(results.get(3)); //
		assertEquals(scan.next(), "goog1e#");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("at least one uppercase"));
		
		scan = new Scanner(results.get(4)); //
		assertEquals(scan.next(), "myGoogle123");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("at least one special character"));
		
		scan = new Scanner(results.get(5)); //
		assertEquals(scan.next(), "Gooogl3#");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("more than two of the same character in sequence"));
	}
	
}
