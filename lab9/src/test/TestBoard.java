package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import game.Board;

public class TestBoard {
	static Board board;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		System.out.println("In @BeforeClass");
		board = new Board();
		board.init();
	}

	@Test
	public void test1(){
		assertEquals(board.getGameStatus(), "Long game set up is done!");
	}
	
	@Test
	public void test2(){
		assertEquals(board.getGameStatus(), "Long game set up is done!");
	}

}
