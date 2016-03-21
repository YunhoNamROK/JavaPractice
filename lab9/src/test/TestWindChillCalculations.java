package test;

import static org.junit.Assert.*;

import org.junit.Test;

import windchill.BadInputException;
import windchill.MyTempConverter;

public class TestWindChillCalculations {

	@Test
	public void testWindchill() {
		try{
			long expected = -11;
			long actual = MyTempConverter.windchill(5, 0);
			assertEquals(expected, actual);
			expected = 3;
			actual = MyTempConverter.windchill(10, 15);
			assertEquals(expected, actual);
		} catch (BadInputException e){
			
		}
	}

	@Test(expected = BadInputException.class)
	public void testWindchillLowSpeed() throws BadInputException {
		long actual = MyTempConverter.windchill(4, 10);
	}

}
