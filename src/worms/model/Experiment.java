package worms.model;

import java.util.Arrays;

public class Experiment {

	public static void main(String[] args) {
		Worm Harrie = new Worm(0,0,Math.PI/4,0.25,"Harrie");
		System.out.println(Arrays.toString(Harrie.jumpStep(0.1)));
		
	}

}
