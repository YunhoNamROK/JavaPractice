package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import game.Location;

public class TestLocation {
	private Location location;
	
	@Before
	public void setUp(){
		System.out.println("In @Before");
		location = new Location();
	}
	
	@Test
	public void testMove() {
		location.move(5, 10);
		assertEquals(5,location.getX());
		assertEquals(10,location.getY());
	}
	
	@Test
	public void testMove2() {
		location.move(5, 10);
		assertEquals(5,location.getX());
		assertEquals(10,location.getY());
		location.move(5, 10);
		assertEquals(10,location.getX());
		assertEquals(20,location.getY());
	}
	
	@Test
	public void testMove3(){
		location.move(5, 10);
		assertEquals(5, location.getX());
		assertEquals(10, location.getY());
	}

}
