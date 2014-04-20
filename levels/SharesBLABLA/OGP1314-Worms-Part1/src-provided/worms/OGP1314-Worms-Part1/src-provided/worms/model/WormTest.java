package worms.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WormTest {
	
    /**
     * References a Worm with "normal" values.
     */
	private static Worm testWormImm;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testWormImm = new Worm(0,0,0,0.25,"James o'Hara");
		
	}
	
	private Worm testWormMut;

	@Before
	public void setUp() throws Exception {
		testWormMut = new Worm(0,0,0,0.25,"Jimmy o'Hara");
	}
	
	/**
	 * Creates a new worm and checks whether it's values are correct.
	 */
	@Test
	public final void constructor_SingleCase() {
		Worm newWorm = new Worm(5,10,3,1,"Mike o'Brien");
		assertEquals(5,newWorm.getX(),0);
		assertEquals(10,newWorm.getY(),0);
		assertEquals(3,newWorm.getDirection(),0);
		assertEquals(1,newWorm.getRadius(),0);
		assertEquals("Mike o'Brien",newWorm.getName());
	}
	
	/**
	 * Test to check whether isValidDirection returns true in the legal case.
	 */
	@Test
	public final void isValidDirection_TrueCase(){
		assertTrue(Worm.isValidDirection(0));
	}
	
	/**
	 * Test to check whether isValidDirection returns false in the case where the direction is greater than 2*PI.
	 */
	@Test
	public final void isValidDirection_DirectionExceedingLimit(){
		assertFalse(Worm.isValidDirection(2*Math.PI));
	}
	
	/**
	 * Test to check whether isValidDirection returns false in the case where the direction is negative.
	 */
	@Test
	public final void isValidDirection_NegativeDirection(){
		assertFalse(Worm.isValidDirection(-1));
	}
	
	/**
	 * Test to check whether isValidRadius returns true in the legal case.
	 */
	@Test
	public final void isValidRadius_TrueCase(){
		assertTrue(Worm.isValidRadius(0.25));
	}
	
	/**
	 * Test to check whether isValidRadius returns false in the illegal case.
	 */
	@Test
	public final void isValidRadius_FalseCase(){
		assertFalse(Worm.isValidRadius(0.24));
	}
	
	/**
	 * Test to check whether isValidName returns true in the legal case.
	 */
	@Test
	public final void isValidName_TrueCase(){
		assertTrue(Worm.isValidName("James o'Hara"));
	}
	
	/**
	 * Test to check whether isValidName returns false in the case where 
	 * the name does not start with a capital letter.
	 */
	@Test
	public final void isValidName_NoCapital(){
		assertFalse(Worm.isValidName("james o'Hara"));
	}
	
	/**
	 * Test to check whether isValidName returns false in the case where
	 * the name contains an illegal character.
	 */
	@Test
	public final void isValidName_IllegalCharacter(){
		assertFalse(Worm.isValidName("J@mes o'Hara"));
	}
	
	/**
	 * Test to check whether isValidName returns false in the case where the name is too short.
	 */
	@Test
	public final void isValidName_TooShort(){
		assertFalse(Worm.isValidName("j"));
	}
	
	/**
	 * Test to check whether canTurn returns true in the legal case.
	 */
	@Test
	public final void canTurn_TrueCase(){
		assertTrue(testWormImm.canTurn(5.44));
	}
	
	/**
	 * Test to check whether canTurn returns false in the case where the angle is greater than 2*PI.
	 */
	@Test
	public final void canTurn_AngleExceedingLimit(){
		assertFalse(testWormImm.canTurn(2*Math.PI));
	}
	
	/**
	 * Test to check whether canTurn returns false in the case where 
	 * the AP of the worm is insufficient to turn.
	 */
	@Test
	public final void canTurn_InsufficientAP(){
		assertFalse(testWormImm.canTurn(5.5));
	}
	
	/**
	 * Test to check whether the worm turns to the right angle and his current AP is altered correspondingly.
	 */
	@Test
	public final void turn_SingleCase(){//singlecase?
		testWormMut.turn(5);
		assertEquals(5,testWormMut.getDirection(),0);//Vraag of dit correct is qua precisie.
		assertEquals(4,testWormMut.getCurrentAP());
	}
	
	/**
	 * Test to check whether, after moving, the new coordinates of the worm are correct and whether
	 * the AP is decreased with the right amount.
	 */
	@Test
	public final void move_LegalCase(){
		testWormMut.turn(Math.PI/4);
		testWormMut.move(5);
		assertEquals(((Math.sqrt(2)/2))*1.25,testWormMut.getX(),0.1E-10); //precisie
		assertEquals(((Math.sqrt(2)/2))*1.25,testWormMut.getY(),0.1E-10); //precisie
		assertEquals(27,testWormMut.getCurrentAP());//afronding: 60/8=7?
	}

}

