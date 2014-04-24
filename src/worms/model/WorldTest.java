package worms.model;

import static org.junit.Assert.*;

import worms.util.Util;

import java.util.Random;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WorldTest {
	
	/**
	 * Variable registering an immutable world with 10 as its width and height, a completely passable map 
	 * and a random generator with 1 as its seed.
	 */
	private static World testWorldImm;
	
	/**
	 * Variable representing a 'checkered' map.
	 */
	private static boolean[][] testCheckered;
	
	/**
	 * Variable registering a Random with seed 1;
	 */
	private static Random randomGenerator1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testCheckered = new boolean[][] {{ true, false, true, false}, {false, true, false, true}, {true, false, true, false}};
		randomGenerator1 = new Random(1);
		testWorldImm = new World(10,10, testCheckered, randomGenerator1);
	}
	
	@Before
	public void setUp() throws Exception {
		
	}

	/**
	 * Test to check whether the constructor works properly under legal circumstances.
	 */
	@Test
	public void constructor_LegalCase(){
		Random newRandom = new Random();
		boolean[][] newMap = new boolean[][] {{true,true,true},{false,false,false}};
		World newWorld = new World(10,10,newMap,newRandom);
		Util.fuzzyEquals(10.0, newWorld.getWidth());
		Util.fuzzyEquals(10.0, newWorld.getWidth());
		assert(newMap == newWorld.getPassableMap());
		assertEquals(newRandom, newWorld.getRandomGenerator());
	}
	
	/**
	 * Test to check whether isValidWidth returns true in the legal case.
	 */
	@Test
	public void isValidWidth_TrueCase(){
		assertTrue(World.isValidWidth(Double.MAX_VALUE));
	}
	
	/**
	 * Test to check whether isValidWidth returns false in the case where
	 * a negative width is given.
	 */
	@Test
	public void isValidWidth_NegativeWidth(){
		assertFalse(World.isValidWidth(-1));
	}
	
	/**
	 * Test to check whether isValidHeight returs true in the legal case.
	 */
	@Test
	public void isValidHeight_TrueCase(){
		assertTrue(World.isValidHeight(Double.MAX_VALUE));
	}
	
	/**
	 * Test to check whether isValidHeight returns false in the case where
	 * a negative height is given.
	 */
	@Test
	public void isValidHeight_NegativeHeight(){
		assertFalse(World.isValidWidth(-1));
	}
	
	/**
	 * Test to check whether getPixelWidth returns the right amount of pixels.
	 */
	@Test
	public void getPixelWidth_SingleCase(){
		assertEquals(4,testWorldImm.getPixelWidth());
	}
	
	/**
	 * Test to check whether getPixelWidth returns the right amount of pixels.
	 */
	@Test
	public void getPixelHeight_SingleCase(){
		assertEquals(3,testWorldImm.getPixelHeight());
	}
	
	/**
	 * Test to check whether getWidthScale returns the proper scale.
	 */
	@Test
	public void getWidthScale_SingleCase(){
		Util.fuzzyEquals(10.0/3, testWorldImm.getWidthScale());
	}
	
	/**
	 * Test to check whether getHeightScale returns the proper scale.
	 */
	@Test
	public void getHeightScale_SingleCase(){
		Util.fuzzyEquals(2.5,testWorldImm.getHeightScale());
	}
	
	/**
	 * Test to check whether getDisance returns the right distance.
	 */
	@Test
	public void getDistance_SingleCase(){
		Util.fuzzyEquals(1.0, World.getDistance(1, 1, 2, 2));
	}
	
	/**
	 * Test to check whether isOutOfBounds returns true in the case where the worm is
	 * at the origin and has a radius greater than zero.
	 */
	@Test
	public void isOutOfBounds_NearOrigin(){
		assertTrue(testWorldImm.isOutOfBounds(0, 0, 0.1));
	}
	
	/**
	 * Test to check whether isOutOfBounds returns true in the case wher the worm is
	 * near the end of the map and has a radius greater than zero.
	 */
	@Test
	public void isOutOfBounds_NearMax(){
		assertTrue(testWorldImm.isOutOfBounds(10, 10, 0.1));
	}
	
	/**
	 * Test to check whether isOutOfBounds returns false in the case where the worm is completely
	 * between the boundaries of the world.
	 */
	@Test
	public void isOutOfBounds_FalseCase(){
		assertFalse(testWorldImm.isOutOfBounds(9, 9, 1));
	}

}
