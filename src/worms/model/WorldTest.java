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
	
	/**
	 * Variable registering a mutable legal food.
	 */
	private static Food testFoodMut;
	
	/**
	 * Variable registering a mutable legal team.
	 */
	private static Team testTeamMut;
	
	/**
	 * Variable registering a mutable legal worm.
	 */
	private static Worm testWormMut;
	
	/**
	 * Variable registering a mutable world with 10 as its width and height, a completely passable map 
	 * and a random generator with 1 as its seed.
	 */
	private static World testWorldMut;
	
	@Before
	public void setUp() throws Exception {
		testFoodMut = new Food(5,5);
		testWorldMut = new World(10,10, testCheckered, randomGenerator1);
		testWormMut = new Worm(5,5,0,0.25,"Johnny");
		testTeamMut = new Team("Random");
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
	 * Test to check whether isValidHeight returns true in the legal case.
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
	 * Test to check whether isOutOfBounds returns true in the case where the worm is
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
		assertFalse(testWorldImm.isOutOfBounds(9, 9, 0.25));
	}
	
	/**
	 * Test to check whether canHaveAsWorm returns true in the case where the world can have the 
	 * given worm.
	 */
	@Test
	public void canHaveAsWorm_TrueCase(){
		assertTrue(testWorldImm.canHaveAsWorm(testWormMut));
	}
	
	/**
	 *  Test to check whether canHaveAsWorm returns false in the case where the world can't have the 
	 * given worm.
	 */
	@Test
	public void canHaveAsWorm_FalseCase(){
		Worm falseTestWorm = null;
		assertFalse(testWorldImm.canHaveAsWorm(falseTestWorm));
	}
	
	/**
	 * Test to check whether addAsWorm returns true in the case where the world can add the worm.
	 */
	@Test
	public void addAsWorm_TrueCase(){
	    testWormMut.setWorldTo(testWorldMut);
	    testWorldMut.addAsWorm(testWormMut);
	    assertTrue(testWorldMut.hasAsWorm(testWormMut));
	}
	
	/**
	 * Test to whether addAsWorm throws an IllegalWormexception if the given world
	 * can't add the given worm.
	 */
	@Test (expected = IllegalWormException.class)
	public void addAsWorm_IllegalWormException(){
		Worm falseTestWorm = null;
		testWorldMut.addAsWorm(falseTestWorm);
	}
	
	/**
	 * Test to whether addAsWorm throws an IllegalWormexception if the given world
	 * can't add the given worm.
	 */
	@Test (expected = IllegalStateException.class)
	public void addAsWorm_IllegalStateException(){
		testWorldMut.addAsWorm(testWormMut);
	}
	
	/**
	 * Test to check whether removeAsWorm removes a worm from the world in a legal case.
	 */
	@Test
	public void RemoveAsWorm_TrueCase(){
		testWormMut.setWorldTo(testWorldMut);
	    testWorldMut.addAsWorm(testWormMut);
	    testWormMut.removeWorld();
	    assertFalse(testWorldMut.hasAsWorm(testWormMut));
	}
	
	/**
	 * Test to check whether removeAsWorm throws an IllegalWormException in the case the given Worm is null.
	 */
	@Test (expected = IllegalWormException.class)
	public void RemoveAsWorm_IllegalWormException(){
		Worm falseTestWorm = null;
	    testWorldMut.removeAsWorm(falseTestWorm);
	}
	
	/**
	 * Test to check whether removeAsWorm throws an IllegaStateException in the case the given Worm still
	 * has a world.
	 */
	@Test (expected = IllegalStateException.class)
	public void RemoveAsWorm_IllegalStateException(){
		testWormMut.setWorldTo(testWorldMut);
	    testWorldMut.addAsWorm(testWormMut);
	    testWorldMut.removeAsWorm(testWormMut);
	}
	
	/**
	 * Test to check whether getCurrentWorm returns the correct Worm in a true case.
	 */
	@Test
	public void getCurrentWorm_SingleCase(){
		testWormMut.setWorldTo(testWorldMut);
	    testWorldMut.addAsWorm(testWormMut);
	    assertTrue(testWorldMut.getCurrentWorm()==testWormMut);
	}
	
	/**
	 * Test to check whether getWinner returns the winner in the case where only 1 worm remains.
	 */
	@Test
	public void getWinner_OneWinner(){
		Worm winningWorm= new Worm(5,5,0,0.25,"Winner");
		winningWorm.setWorldTo(testWorldMut);
	    testWorldMut.addAsWorm(winningWorm);
		assertTrue(testWorldMut.getWinner()=="Winner");
	}
	

	/**
	 * Test to check whether getWinner returns the winner in the case where only 1 team remains.
	 */
	@Test
	public void getWinner_TeamWinner(){
		Worm winningWorm= new Worm(5,5,0,0.25,"Winner");
		Worm winningWorm2= new Worm(5,5,0,0.25,"Winner");
		Team winnaars = new Team("Winnaars");
		winnaars.setWorldTo(testWorldMut);
		winningWorm.setWorldTo(testWorldMut);
	    testWorldMut.addAsWorm(winningWorm);
	    winningWorm2.setWorldTo(testWorldMut);
	    testWorldMut.addAsWorm(winningWorm2);
	    winningWorm.setTeamTo(winnaars);
	    winningWorm2.setTeamTo(winnaars);
		assertTrue(testWorldMut.getWinner()=="Winnaars");
	}
	
	/**
	 * Test to check whether getWinner returns null in the case where there is no winner.
	 */
	@Test
	public void getWinner_NoWinner(){
		Worm losingWorm= new Worm(5,5,0,0.25,"Winner");
		Worm losingWorm2= new Worm(5,5,0,0.25,"Winner");
		losingWorm.setWorldTo(testWorldMut);
	    testWorldMut.addAsWorm(losingWorm);
	    losingWorm2.setWorldTo(testWorldMut);
	    testWorldMut.addAsWorm(losingWorm2);
		assertTrue(testWorldMut.getWinner()==null);
	}
	
	/**
	 * Test to see whether isGameFinished returns true if the game is finished.
	 */
	@Test
	public void isGameFinished_TrueCase(){
		Worm winningWorm= new Worm(5,5,0,0.25,"Winner");
		winningWorm.setWorldTo(testWorldMut);
	    testWorldMut.addAsWorm(winningWorm);
	    assertTrue(testWorldMut.isGameFinished());
	}
	
	/**
	 * test to check whether isGameFinished returns false if the game isn't finished.
	 */
	@Test
	public void isGameFinished_FalseCase(){
		Worm losingWorm= new Worm(5,5,0,0.25,"Winner");
		Worm losingWorm2= new Worm(5,5,0,0.25,"Winner");
		losingWorm.setWorldTo(testWorldMut);
	    testWorldMut.addAsWorm(losingWorm);
	    losingWorm2.setWorldTo(testWorldMut);
	    testWorldMut.addAsWorm(losingWorm2);
	    assertFalse(testWorldMut.isGameFinished());
	}
	
	/**
	 * Test to check whether canHaveAsWorm returns true in the case where the world can have the 
	 * given food.
	 */
	@Test
	public void canHaveAsFood_TrueCase(){
		assertTrue(testWorldImm.canHaveAsFood(testFoodMut));
	}
	
	/**
	 *  Test to check whether canHaveAsFood returns false in the case where the world can't have the 
	 * given food.
	 */
	@Test
	public void canHaveAsFood_FalseCase(){
		Food falseTestFood = null;
		assertFalse(testWorldImm.canHaveAsFood(falseTestFood));
	}
	
	/**
	 * Test to check whether addAsFood returns true in the case where the world can add the food.
	 */
	@Test
	public void addAsFood_TrueCase(){
	    testFoodMut.setWorldTo(testWorldMut);
	    testWorldMut.addAsFood(testFoodMut);
	    assertTrue(testWorldMut.hasAsFood(testFoodMut));
	}
	
	/**
	 * Test to whether addAsFood throws an IllegalFoodexception if the given world
	 * can't add the given food.
	 */
	@Test (expected = IllegalFoodException.class)
	public void addAsFood_IllegalFoodException(){
		Food falseTestFood = null;
		testWorldMut.addAsFood(falseTestFood);
	}
	
	/**
	 * Test to whether addAsFood throws an IllegalFoodexception if the given world
	 * can't add the given food.
	 */
	@Test (expected = IllegalStateException.class)
	public void addAsFood_IllegalStateException(){
		testWorldMut.addAsFood(testFoodMut);
	}
	
	/**
	 * Test to check whether removeAsFood removes a food from the world in a legal case.
	 */
	@Test
	public void RemoveAsFood_TrueCase(){
		testFoodMut.setWorldTo(testWorldMut);
	    testWorldMut.addAsFood(testFoodMut);
	    testFoodMut.removeWorld();
	    assertFalse(testWorldMut.hasAsFood(testFoodMut));
	}
	
	/**
	 * Test to check whether removeAsFood throws an IllegalFoodException in the case the given Food is null.
	 */
	@Test (expected = IllegalFoodException.class)
	public void RemoveAsFood_IllegalFoodException(){
		Food falseTestFood = null;
	    testWorldMut.removeAsFood(falseTestFood);
	}
	
	/**
	 * Test to check whether removeAsFood throws an IllegaStateException in the case the given Food still
	 * has a world.
	 */
	@Test (expected = IllegalStateException.class)
	public void RemoveAsFood_IllegalStateException(){
		testFoodMut.setWorldTo(testWorldMut);
	    testWorldMut.addAsFood(testFoodMut);
	    testWorldMut.removeAsFood(testFoodMut);
	}

	/**
	 * Test to check whether canHaveAsWorm returns true in the case where the world can have the 
	 * given food.
	 */
	@Test
	public void canHaveAsTeam_TrueCase(){
		assertTrue(testWorldImm.canHaveAsTeam(testTeamMut));
	}
	
	/**
	 *  Test to check whether canHaveAsTeam returns false in the case where the world can't have the 
	 * given food.
	 */
	@Test
	public void canHaveAsTeam_FalseCase(){
		Team falseTestTeam = null;
		assertFalse(testWorldImm.canHaveAsTeam(falseTestTeam));
	}
	
	/**
	 * Test to check whether addAsTeam returns true in the case where the world can add the food.
	 */
	@Test
	public void addAsTeam_TrueCase(){
	    testTeamMut.setWorldTo(testWorldMut);
	    testWorldMut.addAsTeam(testTeamMut);
	    assertTrue(testWorldMut.hasAsTeam(testTeamMut));
	}
	
	/**
	 * Test to whether addAsTeam throws an IllegalTeamexception if the given world
	 * can't add the given food.
	 */
	@Test (expected = IllegalTeamException.class)
	public void addAsTeam_IllegalTeamException(){
		Team falseTestTeam = null;
		testWorldMut.addAsTeam(falseTestTeam);
	}
	
	/**
	 * Test to whether addAsTeam throws an IllegalTeamexception if the given world
	 * can't add the given food.
	 */
	@Test (expected = IllegalStateException.class)
	public void addAsTeam_IllegalStateException(){
		testWorldMut.addAsTeam(testTeamMut);
	}
	
	/**
	 * Test to check whether removeAsTeam removes a food from the world in a legal case.
	 */
	@Test
	public void RemoveAsTeam_TrueCase(){
		testTeamMut.setWorldTo(testWorldMut);
	    testWorldMut.addAsTeam(testTeamMut);
	    testTeamMut.removeWorld();
	    assertFalse(testWorldMut.hasAsTeam(testTeamMut));
	}
	
	/**
	 * Test to check whether removeAsTeam throws an IllegalTeamException in the case the given Team is null.
	 */
	@Test (expected = IllegalTeamException.class)
	public void RemoveAsTeam_IllegalTeamException(){
		Team falseTestTeam = null;
	    testWorldMut.removeAsTeam(falseTestTeam);
	}
	
	/**
	 * Test to check whether removeAsTeam throws an IllegaStateException in the case the given Team still
	 * has a world.
	 */
	@Test (expected = IllegalStateException.class)
	public void RemoveAsTeam_IllegalStateException(){
		testTeamMut.setWorldTo(testWorldMut);
	    testWorldMut.addAsTeam(testTeamMut);
	    testWorldMut.removeAsTeam(testTeamMut);
	}
	
	/**
	 * Test to check whether createWorm creates a worm correctly.
	 */
	@Test
	public void createWorm_TrueCase(){
		testWorldMut.createWorm(5, 5, 0, 0.25, "Mike");
		assertTrue(testWorldMut.getWorms().size()==1);
	}
	
	/**
	 * Test to check whether createWorm throws IllegalArgumentException if given a wrong x coordinate.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void createWorm_XCoordinate_IllegalArgumentException(){
		testWorldMut.createWorm(-1.5, 5, 0, 0.25, "Mike");
	}
	
	/**
	 * Test to check whether createWorm throws IllegalArgumentException if given a wrong y coordinate.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void createWorm_YCoordinate_IllegalArgumentException(){
		testWorldMut.createWorm(5, -1, 0, 0.25, "Mike");
	}
	
	/**
	 * Test to check whether createWorm throws IllegalArgumentException if given a radius that is out of bounds.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void createWorm_Radius_IllegalArgumentException(){
		testWorldMut.createWorm(0.5, 0.5, 0, 0.6, "Mike");
	}
	
	/**
	 * Test to check whether createWorm throws IllegalRadiusException if given a radius that's too small.
	 */
	@Test (expected = IllegalRadiusException.class)
	public void createWorm_IllegalRadiusException(){
		testWorldMut.createWorm(5, 5, 0, 0.22, "Mike");
	}
	
	/**
	 * Test to check whether createWorm throws IllegalNameException if given an illegal name.
	 */
	@Test (expected = IllegalNameException.class)
	public void createWorm_IllegalNameException(){
		testWorldMut.createWorm(5, 5, 0, 0.25, "Mike@gmail.com");
	}
	
	/**
	 * Test to check whether createFood creates a food and puts it in the world in a legal case.
	 */
	@Test
	public void createFood_TrueCase(){
		testWorldMut.createFood(5, 5);
		assertTrue(testWorldMut.getFood().size()==1);
	}
	
	/**
	 * Test to check whether createFood throws IllegalArgumentException if given a wrong x coordinate.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void createFood_XCoordinate_IllegalArgumentException(){
		testWorldMut.createFood(-1.5, 5);
	}
	
	/**
	 * Test to check whether createFood throws IllegalArgumentException if given a wrong y coordinate.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void createFood_YCoordinate_IllegalArgumentException(){
		testWorldMut.createFood(5, -1);
	}
	
	/**
	 * Test to check whether createTeam creates a team and puts it in the world correctly in a legal case.
	 */
	@Test
	public void createTeam_TrueCase(){
		testWorldMut.createTeam("Helden");
		assertTrue(testWorldMut.getTeam().size()==1);
	}
	
	/**
	 * Test to check whether createTeam throws IllegalNameException if given an illegal name.
	 */
	@Test (expected = IllegalNameException.class)
	public void createTeam_IllegalNameException(){
		testWorldMut.createTeam("helden");
	}
	
	/**
	 * Test to check whether startNextTurn correctly starts the next turn.
	 */
	@Test
	public void startNextTurn_NormalCase(){
		testWorldMut.createWorm(5,5,0,0.25,"Test1");
		testWorldMut.createWorm(5,5,0,0.25,"Test2");
		testWorldMut.startGame();
		testWorldMut.startNextTurn();
		assertEquals(testWorldMut.getCurrentWorm().getName(),"Test2");
	}
	
	/**
	 * Test to check whether startNextTurn correctly starts the next turn 
	 * if the previous worm was the last worm in the list.
	 */
	@Test
	public void startNextTurn_LastWormCase(){
		testWorldMut.createWorm(5,5,0,0.25,"Test1");
		testWorldMut.createWorm(5,5,0,0.25,"Test2");
		testWorldMut.startGame();
		testWorldMut.startNextTurn();
		testWorldMut.startNextTurn();
		assertEquals(testWorldMut.getCurrentWorm().getName(),"Test1");
	}
}
