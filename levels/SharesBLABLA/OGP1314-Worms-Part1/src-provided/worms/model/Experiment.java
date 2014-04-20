package worms.model;

public class Experiment {

	public static void main(String[] args) {
		Worm Harrie = new Worm(0,0,1,1,"Harrie");
		System.out.println(Harrie.getMaxAP());
		System.out.println(Harrie.getCurrentAP());
		
		System.out.println(Harrie.getRadius());
	}

}
