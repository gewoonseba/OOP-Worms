package worms.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import worms.util.Util;


public class WormTest {
	
    /**
     * References an immutable Worm with 0 as its x-coordinate, y-coordinate and direction, 0.25 as its radius and a valid name.
     */
	private static Worm testWormImmBasic;
	
	/**
	 * References an immutable Worm with 0 as its x-coordinate, y-coordinate, PI/4 as its direction,
	 *  0.25 as its radius and a valid name. 
	 */
	private static Worm testWormImmSmallDirection;
	
	/**
	 * References an immutable Worm with 0 as its x-coordinate, y-coordinate, PI+1 as its direction,
	 *  0.25 as its radius and a valid name. 
	 */
	private static Worm testWormImmBigDirection;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testWormImmBasic = new Worm(0,0,0,0.25,"James o'Hara 1");
		testWormImmSmallDirection = new Worm(0,0,Math.PI/4,0.25,"William o'Hara 2");
		testWormImmBigDirection = new Worm(0,0,Math.PI+1,0.25,"Henry o' Hara 3");
		
	}
	
	/**
	 * References a mutable Worm with 0 as its x-coordinate, y-coordinate and direction, 0.25 as its radius and a valid name. 
	 */
	private Worm testWormMutBasic;
	
	/**
	 * References a mutable Worm with 0 as its x-coordinate, y-coordinate, PI/4 as its direction,
	 *  0.25 as its radius and a valid name. 
	 */
	private Worm testWormMutSmallDirection;
	
	/**
	 * References a mutable Worm with 0 as its x-coordinate, y-coordinate, PI+1 as its direction,
	 *  0.25 as its radius and a valid name. 
	 */
	private Worm testWormMutBigDirection;

	@Before
	public void setUp() throws Exception {
		testWormMutBasic = new Worm(0,0,0,0.25,"Jimmy o'Hara 4");
		testWormMutSmallDirection = new Worm(0,0,Math.PI/4,0.25,"Ricky o'Hara 5");
		testWormMutBigDirection = new Worm(0,0,Math.PI+1,0.25,"Thomas o'Hara 6");
	}
	
	
	/**
	 * Creates a new worm and checks whether it's values are correct.
	 */
	@Test
	public  void constructor_LegalCase() {
		Worm newWorm = new Worm(5,10,3,1,"Mike o'Brien 2");
		assertEquals(5,newWorm.getX(),0);
		assertEquals(10,newWorm.getY(),0);
		assertEquals(3,newWorm.getDirection(),0);
		assertEquals(1,newWorm.getRadius(),0);
		assertEquals("Mike o'Brien 2",newWorm.getName());
	}
	
	/**
	 * Check whether the constructor throws the right exception when an invalid radius is given.
	 */
	@Test (expected = IllegalRadiusException.class)
	public void constructor_IllegalRadiusException(){
		new Worm(0,0,0,0.24,"Jimmy");
	}
	
	/**
	 * Check whether the constructor throws the right exception when an invalid name is given.
	 */
	@Test (expected = IllegalNameException.class)
	public void constructor_IllegalNameException(){
		new Worm(0,0,0,0.25,"J@mes");
	}
	
	/**
	 * Test to check whether isValidCoordinate returns true in the legal case.
	 */
	@Test
	public void isValidCoordinate_LegalCase(){
		assertTrue(Worm.isValidCoordinate(0));
	}
	
	/**
	 * Test to check whether isValidCoordinate returns false in the illegal case.
	 */
	@Test
	public void isValidCoordinate_IllegalCase(){
		assertFalse(Worm.isValidCoordinate(Double.NaN));
	}
	
	/**
	 * Test to check whether isValidDirection returns true in the legal case.
	 */
	@Test
	public void isValidDirection_TrueCase(){
		assertTrue(Worm.isValidDirection(0));
	}
	
	/**
	 * Test to check whether isValidDirection returns false in the case where the direction is greater than 2*PI.
	 */
	@Test
	public void isValidDirection_DirectionExceedingLimit(){
		assertFalse(Worm.isValidDirection(2*Math.PI));
	}
	
	/**
	 * Test to check whether isValidDirection returns false in the case where the direction is negative.
	 */
	@Test
	public void isValidDirection_NegativeDirection(){
		assertFalse(Worm.isValidDirection(-1));
	}
	
	/**
	 * Test to check whether isValidRadius returns true in the legal case.
	 */
	@Test
	public void isValidRadius_TrueCase(){
		assertTrue(Worm.isValidRadius(0.25));
	}
	
	/**
	 * Test to check whether isValidRadius returns false in the illegal case.
	 */
	@Test
	public void isValidRadius_FalseCase(){
		assertFalse(Worm.isValidRadius(0.24));
	}
	
	/**
	 * Test to check whether isValidName returns true in the legal case.
	 */
	@Test
	public void isValidName_TrueCase(){
		assertTrue(Worm.isValidName("James o'Hara 1"));
	}
	
	/**
	 * Test to check whether isValidName returns false in the case where 
	 * the name does not start with a capital letter.
	 */
	@Test
	public void isValidName_NoCapital(){
		assertFalse(Worm.isValidName("james o'Hara"));
	}
	
	/**
	 * Test to check whether isValidName returns false in the case where
	 * the name contains an illegal character.
	 */
	@Test
	public void isValidName_IllegalCharacter(){
		assertFalse(Worm.isValidName("J@mes o'Hara"));
	}
	
	/**
	 * Test to check whether isValidName returns false in the case where the name is too short.
	 */
	@Test
	public void isValidName_TooShort(){
		assertFalse(Worm.isValidName("j"));
	}
	
	/**
	 * Test to check whether isValidCurrentAP returns true in the legal case.
	 */
	@Test
	public void isValidCurrentAP_TrueCase(){
		assertTrue(testWormImmBasic.isValidCurrentAP(70));
	}
	
	/**
	 * Test to check whether isValidCurrentAP returns false in the case where a negative 
	 * value for currentAP is given.
	 */
	@Test
	public void isValidCurrentAP_NegativeAP(){
		assertFalse(testWormImmBasic.isValidCurrentAP(-1));
	}
	
	/**
	 * Test to check whether isValidCurrentAP returns false in the case where the given AP
	 * exceeds the MaxAP for the given worm.
	 */
	@Test
	public void isValidCurrentAP_APExceedingMax(){
		assertFalse(testWormImmBasic.isValidCurrentAP(71));
	}
	
	/**
	 * Test to check whether isValidHitPoints returns true in the legal case.
	 */
	@Test
	public void isValidHitPoints_LegalCase(){
		assertTrue(testWormImmBasic.isValidHitPoints(70));
	}
	
	/**
	 * Test to check whether isValidHitPoints returns false in the case where a negative
	 * value for hitPoints is given.
	 */
	@Test
	public void isValidHitPoints_NegativeHitPoints(){
		assertFalse(testWormImmBasic.isValidHitPoints(-1));
	}
	
	/**
	 * Test to check whether isValidHitPoints returns false in the caxe wher the given hitPoints
	 * exceeds the maxHitPoints for the given worm.
	 */
	@Test
	public void isValidHitPoints_HitPointsExceedingMax(){
		assertFalse(testWormImmBasic.isValidHitPoints(71));
	}
	
	/**
	 * Test to check whether canHaveAsTime returns true in the legal case.
	 */
	@Test
	public void canHaveAsTime_Truecase(){
		assertTrue(testWormImmSmallDirection.canHaveAsTime(0.1));
	}
	
	/**
	 * Test to check whether canHaveAsTime returns false in the case where the given time is negative.
	 */
	@Test
	public void canHaveAsTime_NegativeTime(){
		assertFalse(testWormImmSmallDirection.canHaveAsTime(-0.1));
	}
	
	//TODO: needs passableMap
//	/**
//	 * Test check whether canHaveAsTime returns false in the case where the given time exceeds
//	 * the time the worm will actually jump.
//	 */
//	@Test
//	public void canHaveAsTime_TimeExceedingLimit(){
//		assertFalse(testWormImmSmallDirection.canHaveAsTime(1.08));
//	}
	
	/**
	 * Test to check whether canTurn returns true in the legal case.
	 */
	@Test
	public void canTurn_TrueCase(){
		assertTrue(testWormImmBasic.canTurn(5.44));
	}
	
	/**
	 * Test to check whether canTurn returns false in the case where the angle is equal to 2*PI.
	 */
	@Test
	public void canTurn_AngleExceedingLimit(){
		assertFalse(testWormImmBasic.canTurn(2*Math.PI));
	}
	
	/**
	 * Test to check whether canTurn returns false in the case where 
	 * the AP of the worm is insufficient to turn.
	 */
	@Test
	public void canTurn_InsufficientAP(){
		testWormMutBasic.setCurrentAP(20);
		assertFalse(testWormMutBasic.canTurn((2*Math.PI)/3.0+0.1));
	}
	
	/**
	 * Test to check whether the worm turns to the right angle and his current AP is altered correspondingly.
	 */
	@Test
	public void turn_SingleCase(){
		testWormMutBasic.turn(5);
		assert (Util.fuzzyEquals(5, testWormMutBasic.getDirection()));
		assertEquals(22,testWormMutBasic.getCurrentAP());
	}
	
	//TODO: Needs passableMap
//	/**
//	 * Test to check whether canMove returns true in the legal case.
//	 */
//	@Test
//	public void canMove_TrueCase(){
//		assertTrue(testWormImmBasic.canMove());
//	}
	
	//TODO: Needs passableMap
//	/**
//	 * Test to check whether canMove returns false in the case where the worm has insufficient AP.
//	 */
//	@Test
//	public void canMove_FalseCase(){
//		assertFalse(testWormImmBasic.canMove());
//	}
	
	//TODO: Needs passableMap
//	/**
//	 * Test to check whether, after moving, the new coordinates of the worm are correct and whether
//	 * the AP is decreased with the right amount.
//	 */
//	@Test
//	public void move_LegalCase(){
//		testWormMutSmallDirection.move();
//		assert (Util.fuzzyEquals(((Math.sqrt(2)/2))*1.25,testWormMutSmallDirection.getX()));
//		assert (Util.fuzzyEquals(((Math.sqrt(2)/2))*1.25,testWormMutSmallDirection.getY()));
//		assertEquals(52,testWormMutSmallDirection.getCurrentAP());
//	}
	
	//TODO: Needs passableMap
/*	*//**
	 * Test to check whether the right exception is thrown when the worm has insufficient AP
	 * to move the given number of steps.
	 *//*
	@Test (expected = IllegalAPException.class)
	public void move_IllegalAPException(){
		testWormMutBasic.move();
	}*/
	
	/**
	 * Test to check whether canJumpAP returns true in the legal case.
	 */
	@Test
	public void canJumpAP_TrueCase(){
		assertTrue(testWormImmBasic.canJumpAP());
	}
	
	/**
	 * Test to check whether canJumpAP returns false in the case where the worm has zero AP left.
	 */
	@Test
	public void canJumpAP_FalseCase(){
		testWormMutBasic.setCurrentAP(0);
		assertFalse(testWormMutBasic.canJumpAP());
	}
	
	//TODO: Needs passableMap
/*	*//**
	 * Test to check whether Jump jumps to the correct x coordinate in a legal case.
	 *//*
	@Test
	public void jump_LegalCase(){
		testWormMutSmallDirection.jump();
		assert (Util.fuzzyEquals(5.615749611534734,testWormMutSmallDirection.getX()));
		assertEquals(0,testWormMutSmallDirection.getCurrentAP());
	}*/
	
	/**
	 * Test to check whether jump returns an IllegalAPException if the given worm has 0 AP.
	 */
	@Test (expected = IllegalAPException.class)
	public void jump_IllegalAPException(){
		testWormMutSmallDirection.setCurrentAP(0);
		testWormMutSmallDirection.jump(0.0001);
	}
	
	//TODO: Needs passableMap
/*	*//**
	 * Test to check if jumpTime returns the correct time in a legal case.
	 *//*
	@Test
	public void jumpTime_LegalCase(){
		assert (Util.fuzzyEquals(1.070184182924273,testWormMutSmallDirection.jumpTime(0.0001)));
	}*/
	
	/**
	 * Test to check whether jumpTime returns an IllegalAPException if the given worm has 0 AP.
	 */
	@Test (expected = IllegalAPException.class)
	public void jumpTime_IllegalAPException(){
		testWormMutSmallDirection.setCurrentAP(0);
		testWormMutSmallDirection.jumpTime(0.0001);
	}
	
	//TODO: Needs passableMap
/*	*//**
	 * Test to check if jumpStep returns the correct position or a given time in a legal case.
	 *//*
	@Test
	public void jumpStep_LegalCase(){
		double[] actuals = {0.5247460858737162, 0.47571283587371604};
		assertArrayEquals(testWormMutSmallDirection.jumpStep(0.1), actuals, 0.00001);
	}*/
	
	/**
	 * Test to check whether jumpStep returns an IllegalAPException if the given worm has 0 AP.
	 */
	@Test (expected = IllegalAPException.class)
	public void jumpStep_IllegalAPException(){
		testWormMutSmallDirection.setCurrentAP(0);
		testWormMutSmallDirection.jumpStep(0.0001);
	}
	
	/**
	 * Test to check whether jumpStep returns an IllegalTimeException if the given time is impossible.
	 */
	@Test (expected = IllegalTimeException.class)
	public void jumpStep_IllegalTimeException(){
		testWormImmBasic.jumpStep(-0.1);
	}

	/**
	 * Test to check whether canJumpDirection returns True in the legal case.
	 */
	@Test
	public void canJumpDirection_TrueCase(){
		assertTrue(testWormImmBasic.canJumpDirection());
	}
	
	/**
	 * Test to check whether getMass returns the proper mass.
	 */
	@Test
	public void getMass_SingleCase(){
		assert (Util.fuzzyEquals(testWormImmBasic.getMass(),69.5077));
	}
	
	/**
	 * Test to check whether getMaxAP returns the proper AP.
	 */
	@Test
	public void getMaxAP_SingleCase(){
		assertEquals(testWormImmBasic.getMaxAP(),70);
	}
	
	

}

