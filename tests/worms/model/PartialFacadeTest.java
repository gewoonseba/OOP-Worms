package worms.model;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import worms.model.Facade;
import worms.model.Worm;
import worms.util.Util;

public class PartialFacadeTest {

	private static final double EPS = Util.DEFAULT_EPSILON;

	private IFacade facade;

	private Random random;

	private World world;

	// X X X X
	// . . . .
	// . . . .
	// X X X X
	private boolean[][] passableMap = new boolean[][] {
			{ false, false, false, false }, { true, true, true, true },
			{ true, true, true, true }, { false, false, false, false } };

	@Before
	public void setup() {
		facade = new Facade();
		random = new Random(7357);
		world = new World(4.0, 4.0, passableMap, random);
	}

	@Test
	public void testMaximumActionPoints() {
		System.out.println("ander");
		Worm worm = facade.createWorm(world, 1, 2, 0, 1, "Test");
		assertEquals(4448, facade.getMaxActionPoints(worm));
	}

	@Test
	public void testMoveHorizontal() {
		System.out.println("ander");
		Worm worm = facade.createWorm(world, 1, 2, 0, 1, "Test");
		facade.move(worm);
		assertEquals(2, facade.getX(worm), EPS);
		assertEquals(2, facade.getY(worm), EPS);
	}

	@Test
	public void testMoveVertical() {
		System.out.println("ander");
		Worm worm = facade.createWorm(world, 1, 1.5, Math.PI / 2, 0.5, "Test");
		facade.move(worm);
		assertEquals(1, facade.getX(worm), EPS);
		assertEquals(2.0, facade.getY(worm), EPS);
	}

	@Test
	public void testMoveVerticalAlongTerrain() {
		System.out.println("ander");
		// . . X
		// . w X
		World world = facade.createWorld(3.0, 2.0, new boolean[][] {
				{ true, true, false }, { true, true, false } }, random);
		Worm worm = facade.createWorm(world, 1.5, 0.5,
				Math.PI / 2 - 10 * 0.0175, 0.5, "Test");
		facade.move(worm);
		assertEquals(1.5, facade.getX(worm), EPS);
		assertEquals(1.0, facade.getY(worm), EPS);
	}

	@Test
	public void testFall() {
		System.out.println("enter");
		// . X .
		// . w .
		// . . .
		// X X X
		World world = facade.createWorld(3.0, 4.0, new boolean[][] {
				{ true, false, true }, { true, true, true },
				{ true, true, true }, { false, false, false } }, random);
		Worm worm = facade.createWorm(world, 1.5, 2.5, -Math.PI / 2, 0.5,
				"Test");
		System.out.println("nu");
		assertFalse(facade.canFall(worm));
		facade.move(worm);
		assertTrue(facade.canFall(worm));
		facade.fall(worm);
		assertEquals(1.5, facade.getX(worm), EPS);
		assertEquals(1.5, facade.getY(worm), EPS);
	}
}
