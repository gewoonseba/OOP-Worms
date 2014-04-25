package worms.model;

import static org.junit.Assert.*;

import java.util.Random;

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
	private static Worm testWormImmWithWorld;
	
	/**
	 * References an immutable Team with TestTeam as its name.
	 */
	private static Team testTeam;
	
	/**
	 * References an immutable World with 10 as its height and width, a completely passable map and randomGenerator as 
	 * its random generator.
	 */
	private static World testWorld;
	
	private static World testWorld2;
	
	private static boolean[][] passableMap;
	
	// X X X X
	// . . . .
	// . . . .
	// X X X X
	private static boolean[][] passableMap2;
	
	/**
	 * References a random generator with seed 1
	 */
	private static Random randomGenerator;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testWormImmBasic = new Worm(0,0,0,0.25,"James o'Hara 1");
		testWormImmSmallDirection = new Worm(0,0,Math.PI/4,0.25,"William o'Hara 2");
		testWormImmWithWorld = new Worm(0,0,0,0.25,"Henry o' Hara 3");
		testTeam = new Team("TestTeam");
		passableMap = new boolean[][]{
				{ true, true, true, true, true, true, true, true }, { true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true }, { true, true, true, true, true, true, true, true }, 
				{ true, true, true, true, true, true, true, true }, { true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true }, { true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true }, { true, true, true, true, true, true, true, true }};
		passableMap2 = new boolean[][] {
				{ false, false, false, false }, { true, true, true, true },
				{ true, true, true, true }, { false, false, false, false } };
		randomGenerator = new Random(1);
		testWorld = new World(10,10,passableMap,randomGenerator);
		testWorld2 = new World(4.0, 4.0, passableMap, randomGenerator);
		testWormImmWithWorld.setWorldTo(testWorld);
		testWorld.addAsWorm(testWormImmWithWorld);
		testWormImmBasic.setTeamTo(testTeam);
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
	private Worm testWormMutWithWorld;

	@Before
	public void setUp() throws Exception {
		testWormMutBasic = new Worm(0,0,0,0.25,"Jimmy o'Hara 4");
		testWormMutSmallDirection = new Worm(0,0,Math.PI/4,0.25,"Ricky o'Hara 5");
		testWormMutWithWorld = new Worm(0,0,0,0.25,"Thomas o'Hara 6");
		testWormMutBasic.setTeamTo(testTeam);
		testWormMutWithWorld.setWorldTo(testWorld);
		testWorld.addAsWorm(testWormMutWithWorld);
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
	 * Test to check whether isValidWeapon returns true in the legal case.
	 */
	@Test
	public void isValidWeapon_TrueCase(){
		assertTrue(Worm.isValidWeapon("Rifle"));
	}
	
	/**
	 * Test to check whether isValidWeapon returns false in the case an illegal weapon is given.
	 */
	@Test
	public void isValidWeapon_FalseCase(){
		assertFalse(Worm.isValidWeapon("Catapult"));
	}
	
	/**
	 * Test to check whether isValidPropulsionYield returns true in the legal case.
	 */
	@Test
	public void isValidPropulsionYield_TrueCase(){
		assertTrue(Worm.isValidPropulsionYield(100));
	}
	
	/**
	 * Test to check whether isValidPropulsionYield returns false when a negative yield is given.
	 */
	@Test
	public void isValidPropulsionYield_NegativeYield(){
		assertFalse(Worm.isValidPropulsionYield(-1));
	}
	
	/**
	 * Test to check whether isValidPropulsionYield returns false when a yield greater than 100 is given.
	 */
	@Test
	public void isValidPropulsionYield_YieldExceedsMax(){
		assertFalse(Worm.isValidPropulsionYield(101));
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
	 * Test to check whether canHaveAsTeam returns true in the legal case.
	 */
	@Test
	public void canHaveAsTeam_TrueCase(){
		assertTrue(testWormImmSmallDirection.canHaveAsTeam(testTeam));
	}
	
	/**
	 * Test to check whether canHaveAsTeam returns false in the case null is given.
	 */
	@Test
	public void canHaveAsTeam_NullPointerTeam(){
		assertFalse(testWormImmSmallDirection.canHaveAsTeam(null));
	}
	
	/**
	 * Test to check whether canHaveAsTeam returns false in the case where
	 * the worm already belongs to that team.
	 */
	@Test
	public void canHaveAsTeam_AlreadyInTeam(){
		assertFalse(testWormImmBasic.canHaveAsTeam(testTeam));
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
	

	/**
	 * Test to check whether canMove returns true in the legal case.
	 */
	@Test
	public void canMove_TrueCase(){
		Worm worm = testWorld2.createWorm( 1, 2, 0, 1, "Test");
		assertTrue(worm.canMove());
	}
	
	/**
	 * Test to check whether canMove returns false in the case where the worm has insufficient AP.
	 */
	@Test
	public void canMove_FalseCase(){
		testWormMutWithWorld.setCurrentAP(0);
		assertFalse(testWormMutWithWorld.canMove());
	}
	
	@Test
	public void testMoveHorizontal() {
		Worm worm = testWorld2.createWorm( 1, 2, 0, 1, "Test");
		worm.move();
		Util.fuzzyEquals(2, worm.getX());
		Util.fuzzyEquals(2, worm.getY());
	}
	
	@Test
	public void testMoveVertical() {
		Worm worm = testWorld2.createWorm( 1, 1.5, Math.PI / 2, 0.5, "Test");
		worm.move();
		Util.fuzzyEquals(1, worm.getX());
		Util.fuzzyEquals(2.0, worm.getY());
	}
	
	@Test
	public void testMoveVerticalAlongTerrain() {
		// . . X
		// . w X
		World world = new World(3.0, 2.0, new boolean[][] {
				{ true, true, false }, { true, true, false } }, randomGenerator);
		Worm worm = world.createWorm( 1.5, 0.5,
				Math.PI / 2 - 10 * 0.0175, 0.5, "Test");
		worm.move();
		Util.fuzzyEquals(1.5, worm.getX());
		Util.fuzzyEquals(1.0, worm.getY());
	}

	@Test
	public void testFall() {
		// . X .
		// . w .
		// . . .
		// X X X
		World world = new World(3.0, 4.0, new boolean[][] {
				{ true, false, true }, { true, true, true },
				{ true, true, true }, { false, false, false } }, randomGenerator);
		Worm worm = world.createWorm( 1.5, 2.5, -Math.PI / 2, 0.5,
				"Test");
		assertFalse(worm.canFall());
		worm.move();
		assertTrue(worm.canFall());
		worm.fall();
		Util.fuzzyEquals(1.5, worm.getX());
		Util.fuzzyEquals(1.5, worm.getY());
	}


	/**
	 * Test to check whether the right exception is thrown when the worm has insufficient AP to move.
	 */
	@Test (expected = IllegalAPException.class)
	public void move_IllegalAPException(){
		testWormMutWithWorld.setCurrentAP(0);
		testWormMutWithWorld.move();
	}
	
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
	
	/**
	 * Test to check whether getMaxHitPoints returns the proper hitPoints
	 */
	@Test
	public void getMaxHitPoints_SingleCase(){
		assertEquals(70,testWormImmBasic.getMaxHitPoints());
	}
	
	/**
	 * Test to check whether selectNextWeapon works properly.
	 */
	@Test
	public void selectNextWeapon_SingleCase(){
		testWormMutBasic.selectNextWeapon();
		assertEquals(1,testWormMutBasic.getCurrentWeaponIndex());
		testWormMutBasic.selectNextWeapon();
		assertEquals(0,testWormMutBasic.getCurrentWeaponIndex());
	}
	
	/**
	 * Test to check whether getCurrentWeapon returns "Rifle" if the current weapon index is zero.
	 */
	@Test
	public void getCurrentWeapon_SingleCase(){
		assertEquals("Rifle",testWormImmBasic.getCurrentWeapon());
	}
	
	/**
	 * Test to check whether getShootAP returns the right AP in the case where
	 * the current weapon is a Rifle.
	 */
	@Test
	public void getShootAP_Rifle(){
		assertEquals(60,testWormImmBasic.getShootAP());
	}
	
	/**
	 * Test to check whether getShootAP returns the right AP in the case where 
	 * the current weapon is a Bazooka.
	 */
	@Test
	public void getShootAP_Bazooka(){
		testWormMutBasic.selectNextWeapon();
		assertEquals(20,testWormMutBasic.getShootAP());
	}
	
	/**
	 * Test to check whether getTeamName returns the proper name of the team.
	 */
	@Test
	public void getTeamName_SingleCase(){
		assertEquals("TestTeam",testWormImmBasic.getTeamName());
	}
	
	/**
	 * Test to check whether hasTeam returns true if the worm has a team.
	 */
	@Test
	public void hasTeam_TrueCase(){
		assertTrue(testWormImmBasic.hasTeam());
	}
	
	/**
	 * Test to check whether hasTeam returns false if the worm has no team.
	 */
	@Test
	public void hasTeam_FalseCase(){
		assertFalse(testWormImmSmallDirection.hasTeam());
	}
	
	/**
	 * Test to check whether removeTeam works correctly if the given worm has a Team.
	 */
	@Test
	public void removeTeam_LegalCase(){
		testWormMutBasic.removeTeam();
		assertEquals(null,testWormMutBasic.getTeam());
	}

	/**
	 * Test to check whether removeTeam throws a NullPointerException if the given worm
	 * has no team.
	 */
	@Test (expected = NullPointerException.class)
	public void removeTeam_NoTeam(){
		testWormImmSmallDirection.removeTeam();
	}
	
	/**
	 * Test to check whether isAlive returns true if the worm has more than zero hitPoints.
	 */
	@Test
	public void isAlive_TrueCase(){
		assertTrue(testWormImmBasic.isAlive());
	}
	
	/**
	 * Test to check whether isAlive returns false if the worm has zero hitPoints left.
	 */
	@Test
	public void isAlive_FalseCase(){
		testWormMutWithWorld.setHitPoints(0);
		assertFalse(testWormMutWithWorld.isAlive());
	}
	
	//TODO: Needs passableMap
/*	@Test
	public void shoot_SingleCase(){
		testWormMutWithWorld.shoot(10);
		assert(testWormMutWithWorld.getProjectile() != null);
		assertEquals(60,testWormMutWithWorld.getCurrentAP());
		assert(testWorld.getActiveProjectile() != null);
	}*/
	

}

