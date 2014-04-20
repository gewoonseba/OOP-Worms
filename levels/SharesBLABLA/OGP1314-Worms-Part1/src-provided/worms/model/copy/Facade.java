package model.copy;

public class Facade implements IFacade{

	@Override
	public Worm createWorm(double x, double y, double direction, double radius,
			String name) {	
		return new Worm(x,y,direction,radius,name);
	}

	@Override
	public boolean canMove(Worm worm, int nbSteps) {	
		return worm.canMove(nbSteps);
	}

	@Override
	public void move(Worm worm, int nbSteps) {
	    worm.move(nbSteps);
		
	}

	@Override
	public boolean canTurn(Worm worm, double angle) {	
		return worm.canTurn(angle);
	}

	@Override
	public void turn(Worm worm, double angle) {
		worm.turn(angle);	
	}

	@Override
	public void jump(Worm worm) throws ModelException {
		if (! worm.canJumpAP())
			throw new ModelException("THE WORM HAS NO ACTION POINTS LEFT");
		if (! worm.canJumpDirection())
			throw new ModelException("THE DIRECTION OF THE WORM IS NOT VALID");
		worm.jump();
	}

	@Override
	public double getJumpTime(Worm worm) {
		if (! worm.canJumpAP())
			throw new ModelException("THE WORM HAS NO ACTION POINTS LEFT");
		if (! worm.canJumpDirection())
			throw new ModelException("THE DIRECTION OF THE WORM IS NOT VALID");
		return worm.jumpTime();
	}

	@Override
	public double[] getJumpStep(Worm worm, double t) {
		if (! worm.canJumpAP())
			throw new ModelException("THE WORM HAS NO ACTION POINTS LEFT");
		if (! worm.canJumpDirection())
			throw new ModelException("THE DIRECTION OF THE WORM IS NOT VALID");
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
		// TODO Auto-generated method stub
		return worm.getMass();
	}
	

}
