package movieclub;

public class Main {
	public static void main(String[] args) {

		Movie Twister = new Movie("Twister", Movie.REGULAR);
		Movie Mickey = new Movie("Mickey", Movie.CHILDRENS);
		
		Rental aRental = new Rental(Twister, 3);
		Rental bRental = new Rental(Mickey, 5);
		
		Customer Bob = new Customer("Bob");
		Bob.addRental(aRental);
		Bob.addRental(bRental);
		
		System.out.println(Bob.statement());
	}


	public void problematicMethod(){
		int z= 0;
		if(System.currentTimeMillis()< 50){
			z = 5;
		}

		int test = 150 / z;

	}

}
