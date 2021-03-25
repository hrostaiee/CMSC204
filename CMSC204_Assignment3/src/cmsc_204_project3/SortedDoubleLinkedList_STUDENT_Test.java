
//package _solution;
package cmsc_204_project3;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class SortedDoubleLinkedList_STUDENT_Test {
	SortedDoubleLinkedList<String> sortedLinkedString;
	SortedDoubleLinkedList<Double> sortedLinkedDouble;
	SortedDoubleLinkedList<TV> sortedlinkedTV;
	StringComparator comparator;
	DoubleComparator comparatorD;
	TVComparator comparatorTV;
	
	public TV a=new TV("Panasonic", "FLAT", 32);
	public TV b=new TV("Sony", "LED", 32);
	public TV c=new TV("Remood", "FHD", 32);
	public TV d=new TV("Toshiba", "HD", 32);
	public TV e=new TV("Apple", "Curved", 32);
	public TV f=new TV("HP", "FHD", 32);
	//alphabetic order: e f a c b d
	
	@Before
	public void setUp() throws Exception {
		comparator = new StringComparator();
		sortedLinkedString = new SortedDoubleLinkedList<String>(comparator);
		
		comparatorD = new DoubleComparator();
		sortedLinkedDouble = new SortedDoubleLinkedList<Double>(comparatorD);
		
		comparatorTV = new TVComparator();
		sortedlinkedTV = new SortedDoubleLinkedList<TV>(comparatorTV);
		
	}

	@After
	public void tearDown() throws Exception {
		comparator = null;
		comparatorD = null;
		comparatorTV = null;
		sortedLinkedString = null;
		sortedLinkedDouble = null;
		sortedlinkedTV = null;
	}

	@Test
	public void testAddToEnd() {
		try {
			sortedLinkedString.addToEnd("Hello");
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddToFront() {
		try {
			sortedLinkedString.addToFront("Hello");
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testIteratorSuccessfulNext() {
		sortedlinkedTV.add(a);
		sortedlinkedTV.add(b);
		sortedlinkedTV.add(c);
		sortedlinkedTV.add(d);
		ListIterator<TV> iterator = sortedlinkedTV.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(a, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(true, iterator.hasNext());
	}

	@Test
	public void testIteratorSuccessfulStringPrevious() {
		sortedLinkedString.add("Begin");
		sortedLinkedString.add("World");
		sortedLinkedString.add("Hello");
		sortedLinkedString.add("Zebra");
		ListIterator<String> iterator = sortedLinkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Begin", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("World", iterator.next());
		assertEquals("Zebra", iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals("Zebra", iterator.previous());
		assertEquals("World", iterator.previous());
		assertEquals("Hello", iterator.previous());
	}
	@Test
	public void testIteratorSuccessfulCarPrevious() {
		sortedlinkedTV.add(e);
		sortedlinkedTV.add(c);
		sortedlinkedTV.add(b);
		sortedlinkedTV.add(d);
		//ArrayList<TV> carList = sortedlinkedTV.toArrayList();
		//alphabetic order: e f a c b d
		ListIterator<TV> iterator = sortedlinkedTV.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(e, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(d, iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals(d, iterator.previous());
		assertEquals(b, iterator.previous());
		assertEquals(c, iterator.previous());
	}
	@Test
	public void testIteratorSuccessfulDoubleNext() {
		sortedLinkedDouble.add(new Double(8));
		sortedLinkedDouble.add(new Double(6));
		sortedLinkedDouble.add(new Double(1));
		sortedLinkedDouble.add(new Double(2));
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(new Double(1), iterator.next());
		assertEquals(new Double(2), iterator.next());
		assertEquals(new Double(6), iterator.next());
		assertEquals(true, iterator.hasNext());	}
	
	@Test
	public void testIteratorSuccessfulDoublePrevious() {
		sortedLinkedDouble.add(new Double(5));
		sortedLinkedDouble.add(new Double(10));
		sortedLinkedDouble.add(new Double(8));
		sortedLinkedDouble.add(new Double(2));
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		assertEquals(new Double(2), iterator.next());
		assertEquals(new Double(5), iterator.next());
		assertEquals(new Double(8), iterator.next());
		assertEquals(true, iterator.hasPrevious());
		//assertEquals(new Double(10), iterator.previous());
		assertEquals(new Double(8), iterator.previous());
		assertEquals(true, iterator.hasPrevious());
	}
	
	@Test
	public void testIteratorNoSuchElementException() {
		sortedlinkedTV.add(e);
		sortedlinkedTV.add(c);
		sortedlinkedTV.add(b);
		sortedlinkedTV.add(d);
		//ArrayList<TV> carList = sortedlinkedTV.toArrayList();
		//alphabetic order: e f a c b d
		ListIterator<TV> iterator = sortedlinkedTV.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(e, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals(d, iterator.next());
		try{
			//no more elements in list
			iterator.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}
	
	@Test
	public void testIteratorUnsupportedOperationExceptionString() {
		sortedlinkedTV.add(e);
		sortedlinkedTV.add(c);
		sortedlinkedTV.add(b);
		sortedlinkedTV.add(d);
		//ArrayList<TV> carList = sortedlinkedTV.toArrayList();
		ListIterator<TV> iterator = sortedlinkedTV.iterator();
		
		try{
			//remove is not supported for the iterator
			iterator.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddCar() {
		//alphabetic order: e f a c b d
		sortedlinkedTV.add(a);
		sortedlinkedTV.add(b);
		sortedlinkedTV.add(c);
		assertEquals(a, sortedlinkedTV.getFirst());
		assertEquals(b, sortedlinkedTV.getLast());
		sortedlinkedTV.add(d);
		sortedlinkedTV.add(e);
		assertEquals(e, sortedlinkedTV.getFirst());
		assertEquals(d, sortedlinkedTV.getLast());
		//deletes Elephant from linked list
		assertEquals(d,sortedlinkedTV.retrieveLastElement());
		assertEquals(b, sortedlinkedTV.getLast());
	}

	@Test
	public void testRemoveFirstCar() {
		//alphabetic order: e f a c b d
		sortedlinkedTV.add(b);
		sortedlinkedTV.add(c);
		assertEquals(c, sortedlinkedTV.getFirst());
		assertEquals(b, sortedlinkedTV.getLast());
		sortedlinkedTV.add(a);
		assertEquals(a, sortedlinkedTV.getFirst());
		// remove the first
		sortedlinkedTV.remove(a, comparatorTV);
		assertEquals(c, sortedlinkedTV.getFirst());
	}
	
	@Test
	public void testRemoveEndCar() {
		//alphabetic order: e f a c b d
		sortedlinkedTV.add(b);
		sortedlinkedTV.add(f);
		assertEquals(f, sortedlinkedTV.getFirst());
		assertEquals(b, sortedlinkedTV.getLast());
		sortedlinkedTV.add(d);
		assertEquals(d, sortedlinkedTV.getLast());
		//remove from the end of the list
		sortedlinkedTV.remove(d, comparatorTV);
		assertEquals(b, sortedlinkedTV.getLast());
	}

	@Test
	public void testRemoveMiddleCar() {
		//alphabetic order: e f a c b d
		sortedlinkedTV.add(a);
		sortedlinkedTV.add(b);
		assertEquals(a, sortedlinkedTV.getFirst());
		assertEquals(b, sortedlinkedTV.getLast());
		sortedlinkedTV.add(f);
		assertEquals(f, sortedlinkedTV.getFirst());
		assertEquals(b, sortedlinkedTV.getLast());
		assertEquals(3,sortedlinkedTV.getSize());
		//remove from middle of list
		sortedlinkedTV.remove(a, comparatorTV);
		assertEquals(f, sortedlinkedTV.getFirst());
		assertEquals(b, sortedlinkedTV.getLast());
		assertEquals(2,sortedlinkedTV.getSize());
	}

	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class DoubleComparator implements Comparator<Double>
	{

		@Override
		public int compare(Double arg0, Double arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class TVComparator implements Comparator<TV>
	{

		@Override
		public int compare(TV arg0, TV arg1) {
			// Just put TVs in alphabetic order by make
			return arg0.getMake().compareTo(arg1.getMake());
		}
		
	}
	
	private class TV {
		
		String make;
		String type;
		int size;
		
		public TV (String make, String type, int size) {
			this.make = make;
			this.type = type;
			this.size = size;
		}
		public String getMake() {
			return make;
		}

		public String getType() {
			return type;
		}

		public int getSize() {
			return size;
		}

		@Override
		public String toString() {
			return (getMake()+" "+getType()+" "+getSize());
		}
	}
}
