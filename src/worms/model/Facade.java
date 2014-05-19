package worms.model;

import java.util.Collection;
import java.util.Random;

import programs.*;
import worms.gui.game.IActionHandler;
import worms.model.expressions.Expression;
import worms.model.programs.*;
import worms.model.statements.Statement;
import worms.model.types.Type;

public class Facade implements IFacade{
	
	public Facade(){
		
	}



	@Override
	public boolean canTurn(Worm worm, double angle) {	
		return worm.canTurn(angle);
	}

	@Override
	public void turn(Worm worm, double angle) throws ModelException {
		if (!worm.canTurn(angle))
			throw new ModelException("No AP");
		worm.turn(angle);	
	}



	@Override
	public double[] getJumpStep(Worm worm, double t) throws ModelException {
		if (! worm.canJumpAP())
			throw new ModelException("THE WORM HAS NO ACTION POINTS LEFT");
		if (! worm.canHaveAsTime(t))
			throw new ModelException("Illegal time");
		return worm.jumpStep(t);
	}

	@Override
	public double getX(Worm worm) {
		return worm.getX();
	}

	@Override
	public double getY(Worm worm) {
		return worm.getY();
	}

	@Override
	public double getOrientation(Worm worm) {
		return worm.getDirection();
	}

	@Override
	public double getRadius(Worm worm) {
		return worm.getRadius();
	}

	@Override
	public void setRadius(Worm worm, double newRadius) {
		if (! Worm.isValidRadius(newRadius))
			throw new ModelException("THE WORM IS TOO SMALL");
		worm.setRadius(newRadius);
		
	}

	@Override
	public double getMinimalRadius(Worm worm) {
		return Worm.getMinimalRadius();
	}

	@Override
	public int getActionPoints(Worm worm) {
		return worm.getCurrentAP();
	}

	@Override
	public int getMaxActionPoints(Worm worm) {
		return worm.getMaxAP();
	}

	@Override
	public String getName(Worm worm) {
		return worm.getName();
	}

	@Override
	public void rename(Worm worm, String newName) {
		if (! Worm.isValidName(newName))
			throw new ModelException("THE NAME IS NOT VALID");
		worm.setName(newName);
	}

	@Override
	public double getMass(Worm worm) {
		return worm.getMass();
	}

	@Override
	public void addEmptyTeam(World world, String newName) throws ModelException {
		if (! Team.isValidName(newName))
			throw new ModelException("Illegal name");
		if (world.getTeam().size()>=10)
			throw new ModelException("too many teams");
		world.createTeam(newName);
		
	}

	@Override
	public void addNewFood(World world) {
		world.addNewFood();
	}

	@Override
	public void addNewWorm(World world, Program program) {
		world.addNewWorm(program);
	}

	@Override
	public boolean canFall(Worm worm) {
		return worm.canFall();
	}

	@Override
	public boolean canMove(Worm worm) {
		return worm.canMove();
	}

	@Override
	public Food createFood(World world, double x, double y) throws ModelException {
		if ((world==null)||world.isOutOfBounds(x, y, Food.getRadius()))
				throw new ModelException("Can't create this food.");
		return world.createFood(x, y);
	}

	
	@Override
	public World createWorld(double width, double height,
			boolean[][] passableMap, Random random) throws ModelException {
		if (! World.isValidWidth(width) || ! World.isValidHeight(height) )
			throw new ModelException("Cannot create this world.");
		return new World(width, height, passableMap, random);
	}

	@Override
	public Worm createWorm(World world, double x, double y, double direction,
			double radius, String name,Program program) throws ModelException{
		if (world==null || !Worm.isValidCoordinate(x) || !Worm.isValidCoordinate(y) || !Worm.isValidDirection(direction)
				|| !Worm.isValidName(name) || !Worm.isValidRadius(radius))
			throw new ModelException("Cannot create this worm.");
		return world.createWorm(x, y, direction, radius, name, program);
	}

	@Override
	public void fall(Worm worm) throws ModelException {
		if (worm.getWorld().isAdjacent(worm.getX(),worm.getY(), worm.getRadius()))
			throw new ModelException("Can't fall.");
		worm.fall();
	}

	@Override
	public Projectile getActiveProjectile(World world) {
		return world.getActiveProjectile();
	}

	@Override
	public Worm getCurrentWorm(World world) throws ModelException {
		if (world.getCurrentTurn()>=world.getWorms().size())
			throw new ModelException("Cannot select this worm");
		return world.getCurrentWorm();
	}

	@Override
	public Collection<Food> getFood(World world) {
		return world.getFood();
	}

	@Override
	public int getHitPoints(Worm worm) {
		return worm.getHitPoints();
	}

	@Override
	public double[] getJumpStep(Projectile projectile, double t) throws ModelException {
		if (! projectile.canHaveAsTime(t))
			throw new ModelException("illegal time");
		return projectile.jumpStep(t);
	}

	@Override
	public double getJumpTime(Projectile projectile, double timeStep) {
		return projectile.jumpTime(timeStep);
	}

	@Override
	public double getJumpTime(Worm worm, double timeStep) throws ModelException{
		if (!worm.canJumpAP())
			throw new ModelException("this worm has no AP");
		return worm.jumpTime(timeStep);
	}

	@Override
	public int getMaxHitPoints(Worm worm) {
		return worm.getMaxHitPoints();
	}

	@Override
	public double getRadius(Food food) {
		return Food.getRadius();
	}

	@Override
	public double getRadius(Projectile projectile) {
		return projectile.getRadius();
	}

	@Override
	public String getSelectedWeapon(Worm worm) {
		return worm.getCurrentWeapon();
	}

	@Override
	public String getTeamName(Worm worm) {
		return worm.getTeamName();
	}

	@Override
	public String getWinner(World world) {
		return world.getWinner();
	}

	@Override
	public Collection<Worm> getWorms(World world) {
		return world.getWorms();
	}

	@Override
	public double getX(Food food) {
		return food.getX();
	}

	@Override
	public double getX(Projectile projectile) {
		return projectile.getX();
	}

	@Override
	public double getY(Food food) {
		return food.getY();
	}

	@Override
	public double getY(Projectile projectile) {
		return projectile.getY();
	}

	@Override
	public boolean isActive(Food food) {
		return food.isActive();
	}

	@Override
	public boolean isActive(Projectile projectile) {
		return projectile.isActive();
	}

	@Override
	public boolean isAdjacent(World world, double x, double y, double radius) throws ModelException {
		if(!Worm.isValidCoordinate(x) || !Worm.isValidCoordinate(y) || ! (radius > 0) )
			throw new ModelException("Cannot determine if it's adjacent.");
		return world.isAdjacent(x, y, radius);
	}

	@Override
	public boolean isAlive(Worm worm) {
		return worm.isAlive();
	}

	@Override
	public boolean isGameFinished(World world) {
		return world.isGameFinished();
	}

	@Override
	public boolean isImpassable(World world, double x, double y, double radius) throws ModelException {
		if(!Worm.isValidCoordinate(x) || !Worm.isValidCoordinate(y) || ! (radius > 0) )
			throw new ModelException("Cannot determine if it's impassable.");
		return (!world.isPassable(x, y, radius));
	}

	@Override
	public void jump(Projectile projectile, double timeStep) {
		projectile.jump(timeStep);
	}

	@Override
	public void jump(Worm worm, double timeStep) throws ModelException {
		if (!worm.canJumpAP())
			throw new ModelException("Not enough AP to jump");
		worm.jump(timeStep);
	}

	@Override
	public void move(Worm worm) throws ModelException {
		if (!worm.canMove())
			throw new ModelException("This worm can't move");
		worm.move();
	}

	@Override
	public void selectNextWeapon(Worm worm) {
		worm.selectNextWeapon();
	}

	@Override
	public void shoot(Worm worm, int yield) throws ModelException{
		if (!Worm.isValidPropulsionYield(yield))
			throw new ModelException("Yield is incorrect");
		if (! worm.getWorld().isPassable(worm.getX(), worm.getY(), worm.getRadius()))
			throw  new ModelException("This worm is standing on impassable terrain.");
		if (worm.getShootAP()<0)
			throw new ModelException("Not enough AP to shoot.");
		worm.shoot(yield);
	}

	@Override
	public void startGame(World world) {
		world.startGame();
	}

	@Override
	public void startNextTurn(World world) {
		world.startNextTurn();
	}



	@Override
	public ParseOutcome<?> parseProgram(String programText,
			IActionHandler handler) {
		ProgramParser<Expression,Statement,Type> parser= new ProgramParser<Expression,Statement,Type>(new ProgramFactoryImpl());
		parser.parse(programText);
		if (!parser.getErrors().isEmpty())
			return ParseOutcome.failure(parser.getErrors());
		Program program = new Program(programText,handler,parser.getGlobals(),parser.getStatement());
		
		return ParseOutcome.success(program);
	}



	@Override
	public boolean hasProgram(Worm worm) {
		return worm.hasProgram();
	}



	@Override
	public boolean isWellFormed(Program program) {
		return program.wellFormed();
	}
	

}
