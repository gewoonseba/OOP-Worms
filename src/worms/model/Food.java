package worms.model;

public class Food {
	public Food(World world,double x,double y){
		this.setX(x);
	    this.setY(y);
	}
	private double y;
	
	private double x;
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}

}
