package jUnitLab;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Hasib Rostaiee
 * jUnit Test for GradBook class.
 */
class GradeBookTest {
	private GradeBook gb1;
	private GradeBook gb2;

	@BeforeEach
	void setUp() throws Exception {
		gb1 = new GradeBook(5);
		gb1.addScore(80);
		gb1.addScore(70);
		gb2 = new GradeBook(5);
		gb2.addScore(95);
		gb2.addScore(70);
		gb2.addScore(85);
	}

	@AfterEach
	void tearDown() throws Exception {
		gb1 = null;
		gb2 = null;
	}

	@Test
	void testAddScore() {
		assertTrue(gb1.toString().equals("80.0 70.0 0.0 0.0 0.0 "));
		assertEquals(gb1.getScoreSize(),2);
		assertTrue(gb2.toString().equals("95.0 70.0 85.0 0.0 0.0 "));
		assertEquals(gb2.getScoreSize(),3);
	}


	@Test
	void testSum() {
		assertEquals(150, gb1.sum());
		assertEquals(250, gb2.sum());
	}

	@Test
	void testMinimum() {
		assertEquals(70, gb1.minimum());
		assertEquals(70, gb2.minimum());
	}

	@Test
	void testFinalScore() {
		assertEquals(80, gb1.finalScore());
		assertEquals(180, gb2.finalScore());
	}

	@Test
	void testGetScoreSize() {
		assertEquals(2, gb1.getScoreSize());
		assertEquals(3, gb2.getScoreSize());
	}

	@Test
	void testToString() {
		assertEquals("80.0 70.0 0.0 0.0 0.0 ", gb1.toString());
		assertEquals("95.0 70.0 85.0 0.0 0.0 ", gb2.toString());
	}
}
