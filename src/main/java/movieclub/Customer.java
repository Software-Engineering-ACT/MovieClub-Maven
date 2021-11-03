package movieclub;

import java.util.Vector;
import java.util.Enumeration;

public class Customer {

	private String name;
	private Vector rentals = new Vector();
	
	public Customer (String name) {
		this.name = name;
	}
	
	public void addRental(Rental arg) {
		rentals.addElement(arg);
	}
	
	public String getName() {
		return name;
	}
	
	public String statement() {
		double totalAmount = 0;
		Enumeration rentals = this.rentals.elements();
		String result = "Rental Record for " + getName() + "\n";
		while(rentals.hasMoreElements()) {
			Rental each = (Rental) rentals.nextElement();
			double thisAmount = getThisAmount(each);
			//show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t" +
			String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;
		}
		
		//add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += getFrequentRenterPoints();
		return result;
	}

	public String getFrequentRenterPoints(){
		int frequentRenterPoints = 0;

		Enumeration rentals = this.rentals.elements();
		while(rentals.hasMoreElements()) {
			Rental each = (Rental) rentals.nextElement();
			frequentRenterPoints++;
			//add bonus for a two day new release rental
			if((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
					each.getDaysRented() > 1)
				frequentRenterPoints++;
		}

		return "You earned " + frequentRenterPoints +
				" frequent renter points";
	}

	private double getThisAmount(Rental each) {
		double totalCost = 0;
		//determine amounts for each line
		switch(each.getMovie().getPriceCode()) {
			case Movie.REGULAR:
			   totalCost += 2;
			   if (each.getDaysRented() > 2)
				  totalCost += (each.getDaysRented() - 2) * 1.5;
				  break;
			case Movie.NEW_RELEASE:
				totalCost += each.getDaysRented() * 3;
				break;
			case Movie.CHILDRENS:
				totalCost += 1.5;
				if (each.getDaysRented() > 3)
					totalCost += (each.getDaysRented() - 3) * 1.5;
				break;
			}
		return totalCost;
	}

}
