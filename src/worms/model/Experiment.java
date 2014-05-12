package worms.model;


public class Experiment {

	public static void main(String[] args) {
		Worm johnny = new Worm(5,5,0.5,0.5,"Frank",null);
		Worm Frank = johnny;
		johnny.setX(10.0);
		System.out.println(Frank.getX());

		
	}
	
	

}
