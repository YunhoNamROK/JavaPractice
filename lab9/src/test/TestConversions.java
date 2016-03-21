package test;

import static org.junit.Assert.*;

import org.junit.Test;

import game.MyConverter;

public class TestConversions {
	public static double EPSILON=0.0001;

	@Test
	public void testFeetToMeters() {
		double expected = 0.0508;
		double actual = MyConverter.englishToMeters(0, 2);
		assertEquals(expected, actual, EPSILON);
		
		expected = 0.3556;
		actual = MyConverter.englishToMeters(1, 2);
		assertEquals(expected, actual, EPSILON);
	}

}
