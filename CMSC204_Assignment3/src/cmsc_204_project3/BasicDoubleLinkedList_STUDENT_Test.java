package cmsc_204_project3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




class BasicDoubleLinkedList_STUDENT_Test {
	
	BasicDoubleLinkedList<TV> linkedTV;
	TVComparator comparatorTV;
	
	public TV a=new TV("Sharp", "LCD", 32);
	public TV b=new TV("Sony", "LED", 24);
	public TV c=new TV("Sony", "OLED", 43);
	public TV d=new TV("Toshiba", "LCD", 52);
	public TV e=new TV("Samsung", "Curved", 52);
	public TV f=new TV("Panasonic", "Curved", 63);

	@BeforeEach
	void setUp() throws Exception {
		linkedTV= new BasicDoubleLinkedList<TV>();
		linkedTV.addToEnd(b);
		linkedTV.addToEnd(c);
		comparatorTV = new TVComparator();
	}

	@AfterEach
	void tearDown() throws Exception {
		linkedTV = null;
	}

	@Test
	public void testGetSize() {
		assertEquals(2,linkedTV.getSize());
	}
	
	@Test
	public void testAddToEnd() {
		assertEquals(c,linkedTV.getLast());
		linkedTV.addToEnd(d);
		assertEquals(d,linkedTV.getLast());
	}
	
	@Test
	public void testAddToFront() {	
		assertEquals(b,linkedTV.getFirst());
		linkedTV.addToFront(a);
		assertEquals(a,linkedTV.getFirst());
	}
	
	@Test
	public void testGetFirst() {	
		assertEquals(b,linkedTV.getFirst());
		linkedTV.addToFront(a);
		assertEquals(a,linkedTV.getFirst());
	}

	@Test
	public void testGetLast() {
		assertEquals(c,linkedTV.getLast());
		linkedTV.addToEnd(d);
		assertEquals(d,linkedTV.getLast());
	}
	
	@Test
	public void testToArrayList()
	{
		ArrayList<TV> list;
		linkedTV.addToFront(a);
		linkedTV.addToEnd(d);
		list = linkedTV.toArrayList();
		assertEquals(a,list.get(0));
		assertEquals(b,list.get(1));
		assertEquals(c,list.get(2));
		assertEquals(d,list.get(3));
	}
	
	@Test
	public void testIteratorSuccessfulNext() {
		linkedTV.addToFront(a);
		linkedTV.addToEnd(d);
		ListIterator<TV> iteratorTV = linkedTV.iterator();
		assertEquals(true, iteratorTV.hasNext());
		assertEquals(a, iteratorTV.next());
		assertEquals(b, iteratorTV.next());
		assertEquals(c, iteratorTV.next());
		assertEquals(true, iteratorTV.hasNext());
		assertEquals(d, iteratorTV.next());
	}
	
	@Test
	public void testIteratorSuccessfulPrevious() {
		linkedTV.addToFront(a);
		linkedTV.addToEnd(d);
		ListIterator<TV> iteratorTV = linkedTV.iterator();
		assertEquals(true, iteratorTV.hasNext());
		assertEquals(a, iteratorTV.next());
		assertEquals(b, iteratorTV.next());
		assertEquals(c, iteratorTV.next());
		assertEquals(d, iteratorTV.next());
		assertEquals(true, iteratorTV.hasPrevious());
		assertEquals(d, iteratorTV.previous());
		assertEquals(c, iteratorTV.previous());
		assertEquals(b, iteratorTV.previous());
		assertEquals(a, iteratorTV.previous());
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionNext() {
		linkedTV.addToFront(a);
		linkedTV.addToEnd(d);
		ListIterator<TV> iteratorTV = linkedTV.iterator();		
		assertEquals(true, iteratorTV.hasNext());
		assertEquals(a, iteratorTV.next());
		assertEquals(b, iteratorTV.next());
		assertEquals(c, iteratorTV.next());
		assertEquals(true, iteratorTV.hasNext());
		assertEquals(d, iteratorTV.next());
		
		try{
			//no more elements in list
			iteratorTV.next();
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
	public void testIteratorNoSuchElementExceptionPrevious() {
		linkedTV.addToFront(a);
		linkedTV.addToEnd(d);
		ListIterator<TV> iteratorTV = linkedTV.iterator();		
		assertEquals(true, iteratorTV.hasNext());
		assertEquals(a, iteratorTV.next());
		assertEquals(b, iteratorTV.next());
		assertEquals(c, iteratorTV.next());
		assertEquals(d, iteratorTV.next());
		assertEquals(true, iteratorTV.hasPrevious());
		assertEquals(d, iteratorTV.previous());
		assertEquals(c, iteratorTV.previous());
		assertEquals(b, iteratorTV.previous());
		assertEquals(a, iteratorTV.previous());
		
		try{
			//no more elements in list
			iteratorTV.previous();
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
	public void testIteratorUnsupportedOperationException() {
		linkedTV.addToFront(a);
		linkedTV.addToEnd(d);
		ListIterator<TV> iteratorTV = linkedTV.iterator();		
		assertEquals(true, iteratorTV.hasNext());
		assertEquals(a, iteratorTV.next());
		assertEquals(b, iteratorTV.next());
		assertEquals(c, iteratorTV.next());
		assertEquals(true, iteratorTV.hasNext());
		assertEquals(d, iteratorTV.next());
		
		try{
			//remove is not supported for the iterator
			iteratorTV.remove();
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
	public void testRemove() {
		// remove the first
		assertEquals(b, linkedTV.getFirst());
		assertEquals(c, linkedTV.getLast());
		linkedTV.addToFront(a);
		assertEquals(a, linkedTV.getFirst());
		linkedTV.remove(a, comparatorTV);
		assertEquals(b, linkedTV.getFirst());
		//remove from the end of the list
		linkedTV.addToEnd(d);
		assertEquals(d, linkedTV.getLast());
		linkedTV.remove(d, comparatorTV);
		assertEquals(c, linkedTV.getLast());
		//remove from middle of list
		linkedTV.addToFront(a);
		assertEquals(a, linkedTV.getFirst());
		assertEquals(c, linkedTV.getLast());
		linkedTV.remove(b, comparatorTV);
		assertEquals(a, linkedTV.getFirst());
		assertEquals(c, linkedTV.getLast());
		
	}

	@Test
	public void testRetrieveFirstElement() {
		assertEquals(b, linkedTV.getFirst());
		linkedTV.addToFront(a);
		assertEquals(a, linkedTV.getFirst());
		assertEquals(a, linkedTV.retrieveFirstElement());
		assertEquals(b,linkedTV.getFirst());
		assertEquals(b, linkedTV.retrieveFirstElement());
		assertEquals(c,linkedTV.getFirst());
	}

	@Test
	public void testRetrieveLastElement() {
		assertEquals(c, linkedTV.getLast());
		linkedTV.addToEnd(d);
		assertEquals(d, linkedTV.getLast());
		assertEquals(d, linkedTV.retrieveLastElement());
		assertEquals(c,linkedTV.getLast());
	}

	
	
	
	private class TVComparator implements Comparator<TV>
	{

		@Override
		public int compare(TV arg0, TV arg1) {
			// Just put TVs in alphabetic order by make
			return arg0.toString().compareTo(arg1.toString());
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
			return "TV [make=" + make + ", type=" + type + ", size=" + size + "]";
		}
	}
}
