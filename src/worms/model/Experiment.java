package worms.model;


public class Experiment {

	public static void main(String[] args) {
		int test = 7;
		System.out.println(testFunction(test) instanceof Integer);
		
	}
	
	public static Object testFunction(Object integer) {
		return integer;
	}

}
