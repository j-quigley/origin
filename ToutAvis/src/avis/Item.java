package avis;

import java.util.Collection;


public abstract class Item {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/** 
	 * @uml.property name="reviews"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="item:avis.Review"
	 */
	private Collection<Review> reviews;

}
